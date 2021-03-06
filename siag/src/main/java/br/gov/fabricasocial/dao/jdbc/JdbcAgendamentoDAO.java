package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.gov.fabricasocial.dao.AgendamentoDAO;
import br.gov.fabricasocial.dao.AuditoriaDAO;
import br.gov.fabricasocial.models.Candidate;
import br.gov.fabricasocial.models.Schedule;
import br.gov.fabricasocial.models.Scheduling;

/**
 * Objeto de Acesso ao Banco de Dados, com referencia aos Agendamentos
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */
public class JdbcAgendamentoDAO extends JdbcBaseDAO implements AgendamentoDAO {
	AuditoriaDAO auditoriaDAO = new JdbcAuditoriaDAO();
	
	private static final int FIRST_ELEMENT = 0;
	private static final int INSERT = 1;
	private static final int DELETE = 2;
	
	@Override
	public List<Candidate> findByCPF(String cpf) {		
		Connection connection = this.getConnection();
		
		String selectSQL =	"SELECT cand.idCandidato, cand.cpf, cand.nome, cand.dataInscricao," +
							"cid.Nome, prog.descricao, nec.descricao, tdef.descricao " +
							"FROM Candidato as cand " + 
							"INNER JOIN Cidade as cid ON cand.idCidade = cid.idCidade " +
							"INNER JOIN Programa as prog ON cand.idPrograma = prog.idPrograma " +
							"INNER JOIN NecessidadeEspecial as nec ON cand.idNecessidadeEspecial = nec.idNecessidadeEspecial " +
							"INNER JOIN TipoDeficiencia as tdef ON nec.idTipoDeficiencia = tdef.idTipoDeficiencia " +
							"INNER JOIN Ordem as ord ON cand.idCandidato = ord.idCandidato " +
							"WHERE cand.cpf=?";
		
		List<Candidate> candidates = new ArrayList<Candidate>();

		try {
			PreparedStatement statement = connection.prepareStatement(selectSQL);
			statement.setString(1, cpf);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Candidate candidate = this.setCandidate(resultSet);
				candidates.add(candidate);
			}
			
			resultSet.close();
			statement.close();
			this.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		return candidates;
	}
	
	@Override
	public List<Schedule> getScheduleAvailable(String date) {
		Connection connection = this.getConnection();
		
		String selectSQL = 	"SELECT dh.idDia, d.data, dh.idHora, h.horario, dh.vagas " + 
							"FROM Dia_Hora AS dh " +
							"INNER JOIN Dia AS d ON dh.idDia = d.idDia " +
							"INNER JOIN Hora AS h ON dh.idHora = h.idHora " +
							"WHERE dh.vagas > 0 " +
							"AND d.data = ?" +
							"GROUP BY h.horario;";
		
		List<Schedule> schedules = new ArrayList<Schedule>();
				
		try {
			PreparedStatement statement = connection.prepareStatement(selectSQL);
			statement.setString(1, date);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Schedule schedule = this.setSchedule(resultSet);
				
				schedules.add(schedule);
			}
			
			resultSet.close();
			statement.close();
			this.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return schedules;
	}
	
	@Override
	public Schedule getVacancy(int date, int hour) {
		Connection connection = this.getConnection();
		
		String selectSQL = "SELECT idDia, idHora, vagas FROM siag.Dia_Hora WHERE idDia=? AND idHora=?;";
		
		List<Schedule> schedules = new ArrayList<Schedule>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(selectSQL);
			
			statement.setInt(1, date);
			statement.setInt(2, hour);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Schedule schedule = new Schedule();
				schedule.setIdDate(resultSet.getInt(1));
				schedule.setIdTime(resultSet.getInt(2));
				schedule.setVacancy(resultSet.getInt(3));
				
				schedules.add(schedule);
			}
			
			resultSet.close();
			statement.close();
			this.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return schedules.get(FIRST_ELEMENT);
	}
	
	@Override
	public boolean schedule(Scheduling scheduling, int userId){	
		if(validateScheduling(scheduling)) {
			this.decreaseVacancies(scheduling);
			this.insertScheduling(scheduling);
		
			auditoriaDAO.scheduling(scheduling.getCandidate(), userId, INSERT);

			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Schedule getCandidateScheduling(int idCandidate) {
		Connection connection = this.getConnection();
		
		String selectSQL = 	"SELECT d.data, h.horario, d.idDia, h.idHora FROM Agendamento AS ag " +
							"INNER JOIN Dia AS d ON ag.idDia = d.idDia " +
							"INNER JOIN Hora AS h ON ag.idHora = h.idHora " +
							"INNER JOIN Candidato AS cand ON cand.idCandidato = ag.idCandidato " +
							"WHERE cand.idCandidato =? ;";
		
		List<Schedule> schedules = new ArrayList<Schedule>();
		try {
			PreparedStatement statement = connection.prepareStatement(selectSQL);
			statement.setInt(1, idCandidate);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Schedule schedule = new Schedule();
				schedule.setDate(resultSet.getString(1));
				schedule.setTime(resultSet.getString(2));
				schedule.setIdDate(resultSet.getInt(3));
				schedule.setIdTime(resultSet.getInt(4));
				
				schedules.add(schedule);
			}
			
			resultSet.close();
			statement.close();
			this.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Schedule schedule;
		
		if(schedules.size() > 0) {
			schedule = schedules.get(FIRST_ELEMENT);
		} else {
			schedule = null;
		}
		
		return schedule;
	}
	
	@Override
	public void unschedule(Candidate candidate, int date, int time, int userId) {
		this.increseVacancies(date, time);
		this.cancelScheduling(date, time, candidate.getIdCandidato());
		
		auditoriaDAO.scheduling(candidate.getIdCandidato(), userId, DELETE);
	}
	/**
	 * Realiza a update de vagas do dia atraves da data e da hora, assim aumentando o numero de vagas disponiveis naquela data e naquela hora.
	 * @param date referencia o id da data no banco
	 * @param time referencia o id da hora no banco
	 */
	private void increseVacancies(int date, int time) {
		Connection connection = this.getConnection();
		
		String updateSQL = "UPDATE `siag`.`Dia_Hora` SET `vagas`=vagas+1 WHERE `idDia`=? and`idHora`=?;";
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(updateSQL);
			statement.setInt(1, date);
			statement.setInt(2, time);
			
			statement.executeUpdate();
			
			statement.close();
			this.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Realiza a busca pelos os id para realiza a exclus�o do agendamento
	 * @param date - referencia o ID da data no banco de dados.
	 * @param time - referencia o ID da hora no banco de dados.
	 * @param idCandidate - referencia o ID do candidato no banco de dados.
	 */
	private void cancelScheduling(int date, int time, int idCandidate) {
		Connection connection = this.getConnection();
		
		String deleteSQL =	"DELETE FROM `siag`.`Agendamento` " +
							"WHERE `idCandidato`=? AND `idDia`=? AND `idHora`=?;";
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(deleteSQL);
			statement.setInt(1, idCandidate);
			statement.setInt(2, date);
			statement.setInt(3, time);
			
			statement.executeUpdate();
			
			statement.close();
			this.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Realiza a update de vagas do dia atraves da data e da hora, assim diminuindo agendamentos disponiveis naquela data e naquela hora.
	 * @param scheduling informacoes do agendamento.
	 */
	private void decreaseVacancies(Scheduling scheduling){
		Connection connection = this.getConnection();
		
		String updateSQL = "UPDATE `siag`.`Dia_Hora` SET `vagas`=vagas-1 WHERE `idDia`=? and`idHora`=?;";
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(updateSQL);
			statement.setInt(1, scheduling.getDate());
			statement.setInt(2, scheduling.getHour());
			
			statement.executeUpdate();
			
			statement.close();
			this.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Realiza a insercao do agendamento do candidato.
	 * @param scheduling informcoes do agendamento.
	 */
	private void insertScheduling(Scheduling scheduling) {
		Connection connection = this.getConnection();
		
		String insertSQL = 	"INSERT INTO `siag`.`Agendamento` " +
							"(`idCandidato`, `idUsuario`, `idDia`, `idHora`) " +
							"VALUES (?, ?, ?, ?);";
		
		try {
			PreparedStatement statement = connection.prepareStatement(insertSQL);
			statement.setInt(1, scheduling.getCandidate());
			statement.setInt(2, scheduling.getUser());
			statement.setInt(3, scheduling.getDate());
			statement.setInt(4, scheduling.getHour());
			
			statement.executeUpdate();
			
			statement.close();
			this.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Valida a criacao de agendamento
	 * @param scheduling Objeto com dados do agendamento
	 * @return True se horario estiver disponivel e false caso contrario
	 */
	private boolean validateScheduling(Scheduling scheduling) {
		Connection connection = this.getConnection();
		
		String selectSQL = 	"SELECT * FROM Dia_Hora WHERE idDia=? AND idHora=? AND vagas>0;";
		
		try {
			PreparedStatement statement = connection.prepareStatement(selectSQL);
			statement.setInt(1, scheduling.getDate());
			statement.setInt(2, scheduling.getHour());
			
			ResultSet resultSet = statement.executeQuery();
			boolean validate = resultSet.first();
			
			resultSet.close();
			statement.close();
			this.closeConnection(connection);
			
			return validate;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return true;	
		}
	}
	/**
	 * busca no banco a a data os horarios que tem vaga disponivel
	 * @param resultSet mostra as horarios disponiveis na data desejada
	 * @return a data com os horarios disponiveis.
	 * @throws SQLException recebe os dados da data, hora e da vagas.
	 */
	private Schedule setSchedule(ResultSet resultSet) throws SQLException {
		Schedule schedule = new Schedule();
		schedule.setIdDate(resultSet.getInt(1));
		schedule.setDate(resultSet.getString(2));
		schedule.setIdTime(resultSet.getInt(3));
		schedule.setTime(resultSet.getString(4));
		schedule.setVacancy(resultSet.getInt(5));
		
		return schedule;
	}
	/**
	 * buscar o candidato para verificar se ele esta com seu cadastro todo completo para esta realizado o agendamento
	 * @param resultSet todas as informacoes dos candidato
	 * @return todas as informacoes cadastrada no banco do candidato
	 * @throws SQLException recebe as informacoes do candidato
	 */
	private Candidate setCandidate(ResultSet resultSet) throws SQLException {
		Candidate candidate = new Candidate();
		candidate.setIdCandidato(resultSet.getInt(1));
		candidate.setCpf(resultSet.getString(2));
		candidate.setNome(resultSet.getString(3));
		candidate.setDataInscricao(resultSet.getDate(4));
		candidate.setCidade(resultSet.getString(5));
		candidate.setPrograma(resultSet.getString(6));
		candidate.setNecessidadeEspecial(resultSet.getString(7));
		candidate.setTipoDeficiencia(resultSet.getString(8));
		
		return candidate;
	}
}
