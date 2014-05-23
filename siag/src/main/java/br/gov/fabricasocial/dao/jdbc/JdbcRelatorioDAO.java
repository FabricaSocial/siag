package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.gov.fabricasocial.dao.RelatorioDAO;
import br.gov.fabricasocial.models.SchedulingReport;

public class JdbcRelatorioDAO extends JdbcBaseDAO implements RelatorioDAO{
	private static final String username = "Cadastro";
	private static final String password = "cadastro";

	@Override
	public List<String> schedulingDays() {
		Connection connection= this.getConnection(username, password);
		
		String selectDatesSQL = "SELECT `data` FROM Dia ORDER BY idDia;";
		
		List<String> days = new ArrayList<String>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(selectDatesSQL);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				days.add(resultSet.getString(1));
			}
			
			resultSet.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return days;
	}

	@Override
	public List<SchedulingReport> schedulingTimes() {
		Connection connection= this.getConnection(username, password);
		
		String selectTimesSQL = "SELECT idHora, horario FROM Hora ORDER BY idHora;";
		
		List<SchedulingReport> report = new ArrayList<SchedulingReport>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(selectTimesSQL);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				SchedulingReport schedulingReport = new SchedulingReport();
				schedulingReport.setTime(resultSet.getString(2));
				
				int idTime = resultSet.getInt(1);
				
				schedulingReport.setVacancies(this.getVacancies(idTime));
				
				report.add(schedulingReport);
			}
			
			resultSet.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return report;
	}
	
	private List<Integer> getVacancies(int idTime) {
		Connection connection = this.getConnection(username, password);
		
		String selectVacanciesSQL = "SELECT SUM(20 - vagas) AS Agendamentos " +
									"FROM Dia_Hora " +
									"WHERE idHora=? " +
									"GROUP BY idDia, idHora " +
									"ORDER BY idDia, idHora;";
		
		List<Integer> vacancies = new ArrayList<Integer>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(selectVacanciesSQL);
			statement.setInt(1, idTime);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				vacancies.add(resultSet.getInt(1));
			}
			
			resultSet.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vacancies;
	}

}
