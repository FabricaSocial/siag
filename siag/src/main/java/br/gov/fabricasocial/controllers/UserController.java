package br.gov.fabricasocial.controllers;

import java.util.List;
import java.util.Scanner;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;

import br.gov.fabricasocial.dao.UserDAO;
import br.gov.fabricasocial.dao.jdbc.JdbcUserDAO;
import br.gov.fabricasocial.dao.ldap.LdapAuth;
import br.gov.fabricasocial.models.User;

public class UserController {
	private static int USUARIO_CADASTRO = 1;
	
	public static void main(String[] args) {
		UserDAO dao = new JdbcUserDAO();
		dao.setUserLogin("Cadastro", "cadastro");
		
		String localUsername = System.getProperty("user.name");
		System.out.println("Usuário logado na máquina: " + localUsername);
		
		List<User> users = dao.findByUserName(localUsername);
		User user;
		
		String mensagem = "";
		
		if(users.size() != 0) {
			user = users.get(0);
		} else {
			System.out.println("\nPrimeiro Acesso!\nDigite sua senha: ");
				
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String password = scanner.nextLine();
				
			user = new User(localUsername, password, USUARIO_CADASTRO);				
		}
		
		try {
			LdapAuth ldapAuth = new LdapAuth();
			
			ldapAuth.authenticateUser(user);
			dao.insert(user);
			
			mensagem = "Autenticado com sucesso!";
		} catch (AuthenticationException authEx) {
			authEx.getCause().printStackTrace();
			
			mensagem = "Erro na autenticação!";
		} catch (NamingException namEx) {
			mensagem = "Problema na conexão!";
			namEx.getCause().printStackTrace();
		}
		
		System.out.println(mensagem);
	}
}
