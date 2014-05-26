package br.gov.fabricasocial.dao;

import java.util.List;

import br.gov.fabricasocial.models.User;

/**
 * Interface de acesso ao banco de dados com relacao ao Sistema de Login
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */
public interface LoginDAO {
	public void updatePassword(User user, String password);
	public void insert(User user);
	public List<User> findByUserName(String username);
}
