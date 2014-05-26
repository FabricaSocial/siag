package br.gov.fabricasocial.dao;

import java.util.List;

import br.gov.fabricasocial.models.User;

public interface LoginDAO {
	public void updatePassword(User user, String password);
	public void insert(User user);
	public List<User> findByUserName(String username);
}
