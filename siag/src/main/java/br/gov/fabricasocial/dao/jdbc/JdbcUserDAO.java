package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.gov.fabricasocial.dao.UserDAO;
import br.gov.fabricasocial.models.User;

public class JdbcUserDAO extends JdbcBaseDAO implements UserDAO{
	private String username;
	private String password;
	
	@Override
	public void insert(User user) {
		Connection connection = this.getConnection(this.username, this.password);
		
		List<User> users = this.findByUserName(user.getUsername());
		
		if(users.size() == 0) {
			String insertSQL = 	"INSERT INTO `siag`.`usuario`" +
					   	"(`nomeUsuario`,`senha`,`idTipoUsuario`)" +
						"VALUES (?,?,?);";
			
			try {
				PreparedStatement statement = connection.prepareStatement(insertSQL);
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
		} else {
			// Nothing to do
		}
	}
	
	@Override
	public void setUserLogin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public List<User> findByUserName(String username) {
		// TODO Auto-generated method stub
		Connection connection = this.getConnection(this.username, this.password);
		
		String selectSQL =	"SELECT `usuario`.`idUsuario`, " +
						    "`usuario`.`nomeUsuario`, " +
						    "`usuario`.`senha`, " +
						    "`usuario`.`idTipoUsuario` " +
						    "FROM `siag`.`usuario`" +
						    "WHERE `usuario`.`nomeUsuario`=?;";
		
		List<User> users = new ArrayList<User>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(selectSQL);
			statement.setString(1, username);
			
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				User user = new User(resultSet.getString("nomeUsuario"),
									resultSet.getString("senha"),
									resultSet.getInt("idTipoUsuario"));
				
				users.add(user);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}

}
