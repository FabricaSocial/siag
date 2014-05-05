package br.gov.fabricasocial.dao;

import br.gov.fabricasocial.models.User;

public interface UserDAO {

	public void insert(User user);
	public User findByUserId(int userId);
}
