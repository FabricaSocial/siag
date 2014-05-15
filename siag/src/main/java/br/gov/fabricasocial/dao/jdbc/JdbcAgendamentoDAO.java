package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.gov.fabricasocial.dao.AgendamentoDAO;
import br.gov.fabricasocial.models.Candidate;
import br.gov.fabricasocial.models.Schedule;

public class JdbcAgendamentoDAO extends JdbcBaseDAO implements AgendamentoDAO {
	private String username;
	private String password;
	
	@Override
	public List<Candidate> findByCPF(String cpf) {
		Connection connection = this.getConnection(username, password);
		
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		return candidates;
	}

	@Override
	public void setUserLogin(String username, String password) {
		this.username = username;
		this.password = password;		
	}
	
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

	@Override
	public List<Schedule> getScheduleAvailable(String date) {
		Connection connection = this.getConnection(username, password);
		
		String selectSQL = 	"SELECT d.data, h.horario, dh.vagas " + 
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
				Schedule schedule = new Schedule();
				schedule.setDate(resultSet.getDate(1));
				schedule.setTime(resultSet.getTime(2));
				schedule.setVacancy(resultSet.getInt(3));
				
				schedules.add(schedule);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return schedules;
	}
}
