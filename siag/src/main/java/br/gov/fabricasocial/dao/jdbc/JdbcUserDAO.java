package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.gov.fabricasocial.dao.UserDAO;
import br.gov.fabricasocial.models.User;

public class JdbcUserDAO extends JdbcBaseDAO implements UserDAO{
	private String username;
	private String password;
	
	@Override
	public void insert(User user) {
		Connection connection = this.getConnection(username, password);
		
		String insertSQL = 	"INSERT INTO `siag`.`usuario`" +
				   	"(`nomeUsuario`,`senha`,`idTipoUsuario`)" +
					"VALUES (?,?,?);";
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(insertSQL);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getUserType());
			
			statement.execute();
			statement.close();
			
			this.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public User findByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setUserLogin(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
