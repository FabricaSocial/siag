package br.gov.fabricasocial.dao;

import java.util.List;

import br.gov.fabricasocial.models.User;

/**
 * Interface de acesso ao banco de dados com relacao ao Sistema de Login
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */
public interface LoginDAO {
	/**
	 * Atualiza a senha do usuario
	 * @param user Usuario que deseja alterar senha
	 * @param password Nova senha
	 */
	public void updatePassword(User user, String password);
	
	/**
	 * Insere um novo usuario na base de dados
	 * @param user Usuario que sera inserido
	 */
	public void insert(User user);
	
	/**
	 * Procura por usuarios com o determinado username
	 * @param username Nome do usuario
	 * @return Lista de usuarios com o nome definido
	 */
	public List<User> findByUserName(String username);
}
