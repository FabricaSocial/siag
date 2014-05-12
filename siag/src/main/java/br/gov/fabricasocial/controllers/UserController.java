package br.gov.fabricasocial.controllers;

import java.util.List;
import java.util.Locale;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.fabricasocial.dao.UserDAO;
import br.gov.fabricasocial.dao.jdbc.JdbcUserDAO;
import br.gov.fabricasocial.dao.ldap.LdapAuth;
import br.gov.fabricasocial.models.User;

@Controller
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class); 
	
	private static int FIRST_ELEMENT = 0;
	
	private static String DBUSER = "Cadastro";
	private static String DBPASSWORD = "cadastro";
	
	private static int AUTHENTICATION_ERROR = 1;
	private static int AD_CONNECTION_ERROR = 2;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpSession session) {
		UserDAO dao = new JdbcUserDAO();
		dao.setUserLogin(DBUSER, DBPASSWORD);
		
		String localUsername = System.getProperty("user.name");
		
		List<User> users = dao.findByUserName(localUsername);
	
		if(users.size() != 0) {
			User user = users.get(FIRST_ELEMENT);
			try {
				LdapAuth ldapAuth = new LdapAuth();
				ldapAuth.authenticateUser(user);
				
				session.setAttribute("user", user);
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
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String enter(Locale locale, Model model, HttpSession session,
						@RequestParam("username") String username,
						@RequestParam("password") String password) {
		
		UserDAO dao = new JdbcUserDAO();
		dao.setUserLogin(DBUSER, DBPASSWORD);
		
		User user = new User(username, password);
		
		int errorMessage = 0;
		try {
			LdapAuth ldapAuth = new LdapAuth();
			ldapAuth.authenticateUser(user);
			
			dao.insert(user);
			
			session.setAttribute("user", user);
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
