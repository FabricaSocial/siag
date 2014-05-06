package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcBaseDAO {
	
	public Connection getConnection(String username, String password) {
		try {
			Connection newConnection = DriverManager.getConnection("jdbc:mysql://localhost/siag",
																	username, password);
			return newConnection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}