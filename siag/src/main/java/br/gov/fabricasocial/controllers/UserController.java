package br.gov.fabricasocial.controllers;

import java.util.ArrayList;
import java.util.List;

import br.gov.fabricasocial.dao.UserDAO;
import br.gov.fabricasocial.dao.jdbc.JdbcUserDAO;
import br.gov.fabricasocial.models.User;

public class UserController {
	private static int USUARIO_CADASTRO = 1;
	
	public static void main(String[] args) {
		UserDAO dao = new JdbcUserDAO();
		dao.setUserLogin("Cadastro", "cadastro");

		
	}
}
