package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.gov.fabricasocial.dao.LoginDAO;
import br.gov.fabricasocial.models.User;
/**
 * Responsavel pelo o login do usuario cadastro no sistema
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */
public class JdbcLoginDAO extends JdbcBaseDAO implements LoginDAO{
	
	@Override
	public void insert(User user) {
		Connection connection = this.getConnection();
		
		List<User> users = this.findByUserName(user.getUsername());
		
		if(users.isEmpty()) {
			String insertSQL = 	"INSERT INTO `siag`.`Usuario`" +
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
				e.printStackTrace();
			}
		} else {
			// Nothing to do
		}
	}

	@Override
	public List<User> findByUserName(String username) {
		// TODO Auto-generated method stub
		Connection connection = this.getConnection();
		
		String selectSQL =	"SELECT idUsuario, nomeUsuario, senha, idTipoUsuario " +
						    "FROM Usuario " +
						    "WHERE nomeUsuario=?;";
		
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

	@Override
	public void updatePassword(User user, String password) {
		Connection connection = this.getConnection();
		
		String updateSQL =	"UPDATE `siag`.`Usuario` SET `senha`=? WHERE `idUsuario`=?;";
		
		try {
			PreparedStatement statement = connection.prepareStatement(updateSQL);
			statement.setString(1, password);
			statement.setInt(2, user.getIdUser());
			
			statement.execute();
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
