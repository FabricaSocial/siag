package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.gov.fabricasocial.dao.AuditoriaDAO;
/**
 * Objeto de Acesso ao Banco de Dados, com referencia a auditoria
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */
public class JdbcAuditoriaDAO extends JdbcBaseDAO implements AuditoriaDAO {

	@Override
	public void scheduling(int candidate, int userId, int auditStatus) {
		System.out.println("------" + candidate);
		System.out.println(userId);
		System.out.println(auditStatus);
		
		Connection connection = this.getConnection();

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
