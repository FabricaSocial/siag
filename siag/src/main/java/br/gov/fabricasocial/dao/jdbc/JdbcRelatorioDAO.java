package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.gov.fabricasocial.dao.RelatorioDAO;
import br.gov.fabricasocial.models.SchedulingReport;
import br.gov.fabricasocial.utils.FormatString;

public class JdbcRelatorioDAO extends JdbcBaseDAO implements RelatorioDAO{
	private static final String username = "Cadastro";
	private static final String password = "cadastro";
	
	FormatString formatString = new FormatString();
	
	@Override
	public List<SchedulingReport> schedulingReport() {
		Connection connection = this.getConnection(username, password);
		
		String selectSQL =	"SELECT * FROM siag.RelatorioAgendamento;";
		
		List<SchedulingReport> schedulingReports = new ArrayList<SchedulingReport>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(selectSQL);

			ResultSet resultSet = statement.executeQuery();
			
			schedulingReports = setSchedulingReports(resultSet, schedulingReports);
			
			resultSet.close();
			statement.close();
			this.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schedulingReports;
	}

	private List<SchedulingReport> setSchedulingReports(ResultSet resultSet,
			List<SchedulingReport> schedulingReports) {
		try {
			while(resultSet.next()) {
				SchedulingReport schedulingReport = new SchedulingReport();
				
				schedulingReport.setCandidate(resultSet.getString(1));
				schedulingReport.setCandidateCpf(resultSet.getString(2));
				schedulingReport.setUser(resultSet.getString(3));
				schedulingReport.setDate(resultSet.getString(4));
				schedulingReport.setHour(resultSet.getString(5));
				
				System.out.println(schedulingReport.getCandidateCpf());
				
				schedulingReport.setCandidateCpf(formatString.formatCPF(schedulingReport.getCandidateCpf()));
				
				schedulingReports.add(schedulingReport);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return schedulingReports;
	}

}
