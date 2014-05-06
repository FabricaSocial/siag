package br.gov.fabricasocial.dao;

import br.gov.fabricasocial.models.User;

public interface UserDAO {
	
	public void setUserLogin(String username, String password);
	public void insert(User user);
	public User findByUserId(int userId);
}
