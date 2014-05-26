package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcBaseDAO {
	
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
	
	public void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}

}
