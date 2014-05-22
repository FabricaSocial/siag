package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.gov.fabricasocial.dao.AuditoriaDAO;

public class JdbcAuditoriaDAO extends JdbcBaseDAO implements AuditoriaDAO {
	private static final String username = "Cadastro";
	private static final String password = "cadastro";
	
	@Override
	public void scheduling(int candidate, int userId, int auditStatus) {
		System.out.println("------" + candidate);
		System.out.println(userId);
		System.out.println(auditStatus);
		
		Connection connection = this.getConnection(username, password);

		String insertSQL =	"INSERT INTO `siag`.`AgendamentoAudit` " +
				"(`idCandidato`, `idUsuario`, `idStatusAudit`) " +
				"VALUES (?, ?, ?);";
		
		try{
			
			PreparedStatement statement = connection.prepareStatement(insertSQL);
			statement.setInt(1, candidate);
			statement.setInt(2, userId);
			statement.setInt(3, auditStatus);
			
			statement.executeUpdate();
			
			statement.close();
			this.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
