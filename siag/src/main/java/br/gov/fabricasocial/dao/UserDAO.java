package br.gov.fabricasocial.dao;

import java.util.List;

import br.gov.fabricasocial.models.User;

public interface UserDAO {
	
	public void setUserLogin(String username, String password);
	public void insert(User user);
	public List<User> findByUserName(String username);
}
