package br.gov.fabricasocial.controllers;

import java.util.List;
import java.util.Locale;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.fabricasocial.dao.LoginDAO;
import br.gov.fabricasocial.dao.jdbc.JdbcLoginDAO;
import br.gov.fabricasocial.dao.ldap.LdapAuth;
import br.gov.fabricasocial.models.User;

@Controller
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private static final int FIRST_ELEMENT = 0; 
	
	private static String DBUSER = "Cadastro";
	private static String DBPASSWORD = "cadastro";
	
	private SessionController sessionController = new SessionController();
	
	private static int AUTHENTICATION_ERROR = 1;
	private static int AD_CONNECTION_ERROR = 2;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest request) {
		LoginDAO dao = new JdbcLoginDAO();
		dao.setUserLogin(DBUSER, DBPASSWORD);
		
		/*
		String localUsername = System.getProperty("user.name");
		List<User> users = dao.findByUserName(localUsername);

		if(!users.isEmpty()) {
			try {
				User user = users.get(FIRST_ELEMENT);				
				
				LdapAuth ldapAuth = new LdapAuth();
				ldapAuth.authenticateUser(user);
				
				sessionController.startSession(request, user);
			} catch (AuthenticationException authEx) {
				LOGGER.error("Authentication error");
			} catch (NamingException namEx) {
				LOGGER.error("AD connection error");
			}
			
			return "redirect:/home";
		} else {
			model.addAttribute("localUsername", localUsername);
		}
		
		return "login";
		*/
		List<User> users = dao.findByUserName("matheus.fernandes");
		User user = users.get(FIRST_ELEMENT);
		sessionController.startSession(request, user);
		return "home";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String enter(Locale locale, Model model,
						@RequestParam("username") String username,
						@RequestParam("password") String password) {
		
		LoginDAO dao = new JdbcLoginDAO();
		dao.setUserLogin(DBUSER, DBPASSWORD);
		
		User user = new User(username, password);
		
		int errorMessage = 0;
		try {
			LdapAuth ldapAuth = new LdapAuth();
			ldapAuth.authenticateUser(user);
			
			dao.insert(user);
		} catch (AuthenticationException authEx) {
			errorMessage = AUTHENTICATION_ERROR;
			LOGGER.error("Authentication error");
		} catch (NamingException namEx) {
			errorMessage = AD_CONNECTION_ERROR;
			LOGGER.error("AD connection error");
		}
		
		if (errorMessage == 0) {
			return "redirect:/";
		} else {
			model.addAttribute("errorMessage", errorMessage);
			model.addAttribute("localUsername", username);
			return "login";
		}
	}
}
