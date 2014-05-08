package br.gov.fabricasocial.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.gov.fabricasocial.dao.UserDAO;
import br.gov.fabricasocial.dao.jdbc.JdbcUserDAO;
import br.gov.fabricasocial.dao.ldap.LdapAuth;
import br.gov.fabricasocial.models.User;

@Controller
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class); 
	
	private static int USUARIO_CADASTRO = 1;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		UserDAO dao = new JdbcUserDAO();
		dao.setUserLogin("Cadastro", "cadastro");
		
		String localUsername = System.getProperty("user.name");
		
		model.addAttribute("localUsername", localUsername);
		
		return "login";
	}
}
