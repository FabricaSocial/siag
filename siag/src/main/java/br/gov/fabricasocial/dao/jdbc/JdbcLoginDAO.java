package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.fabricasocial.controllers.UserController;
import br.gov.fabricasocial.dao.LoginDAO;
import br.gov.fabricasocial.models.User;

public class JdbcLoginDAO extends JdbcBaseDAO implements LoginDAO{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private static final int FIRST_ELEMENT = 0; 
	
	private String username;
	private String password;
	
	@Override
	public void insert(User user) {
		Connection connection = this.getConnection(this.username, this.password);
		
		List<User> users = this.findByUserName(user.getUsername());
		
		if(users.get(FIRST_ELEMENT) == null) {
			String insertSQL = 	"INSERT INTO `siag`.`usuario`" +
					   	"(`nomeUsuario`,`senha`)" +
						"VALUES (?,?);";
			
			try {
				PreparedStatement statement = connection.prepareStatement(insertSQL);
				statement.setString(1, user.getUsername());
				statement.setString(2, user.getPassword());
				
				statement.execute();
				statement.close();
				
				this.closeConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.error("Problema em conex�o com banco de dados.");
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
									resultSet.getString("senha"));
				user.setUserType(resultSet.getInt("idTipoUsuario"));
				user.setIdUser(resultSet.getInt("idUsuario"));
				users.add(user);
			}
			resultSet.close();
			statement.close();
			
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
}
