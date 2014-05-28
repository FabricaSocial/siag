package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Realiza a conexao com o banco de dados
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */
public class JdbcBaseDAO {
	/**
	 * realiza a conexao do banco de dados com o sistema
	 * @return se a conexao foi realizada com sucesso no banco de dados
	 */
	public Connection getConnection() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection newConnection = DriverManager.getConnection("jdbc:mysql://localhost/siag",
																	"root", "root");
			return newConnection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Realiza a conexao com o banco de dados
	 * @param connection realiza a conexao com o banco
	 * @throws SQLException abre a conexao
	 */
	public void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}

}
