package br.gov.fabricasocial.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.fabricasocial.dao.LoginDAO;
import br.gov.fabricasocial.dao.jdbc.JdbcLoginDAO;
import br.gov.fabricasocial.models.User;

/**
 * Controla o modulo de login
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */
@Controller
public class LoginController {
	private static final int FIRST_ELEMENT = 0;
	private SessionController sessionController = new SessionController();
	private LoginDAO loginDAO = new JdbcLoginDAO();

	/**
	 * Chama pagina de login
	 * @return Pagina de login ou redireciona para home
	 */
	@RequestMapping(value = "/")
	public String login(Model model, HttpServletRequest request){
		if(sessionController.checkSession(request)){
			return "redirect:/home";
		} else {
			return "login";
		}
	}
	
	/**
	 * Efetua login na aplicacao
	 * @param username Nome de usuario
	 * @param password Senha do usuario
	 * @return Redireciona para home ou chama pagina de login
	 */
	@RequestMapping(value="/entrar", method = RequestMethod.POST)
	public String enter(Model model, HttpServletRequest request,
									@RequestParam("username") String username,
									@RequestParam("password") String password) {
		
		List<User> users = loginDAO.findByUserName(username);
		
		if(!users.isEmpty()){
			User user = users.get(FIRST_ELEMENT);
			
			String userPassword = user.getPassword();

			if(userPassword.equals(password)) {
				sessionController.startSession(request, user);
				
				return "redirect:/home";
			}
			
			model.addAttribute("invalidAuth", true);
			return "login";
			
		} else {
			model.addAttribute("unregistredUser", true);
			return "login";
		}
	}
	
	/**
	 * Altera a senha do usuario
	 * @param password Nova senha do usuario
	 * @return Home ou redireciona para tela de login
	 */
	@RequestMapping(value = "/alterar-senha", method = RequestMethod.POST)
	public String updatePassword(Model model, HttpServletRequest request, 
											@RequestParam("password") String password){
		if(sessionController.checkSession(request)){
			List<User> users = loginDAO.findByUserName(sessionController.getLoggedUser(request));
			User user = users.get(FIRST_ELEMENT);
			
			loginDAO.updatePassword(user, password);
			model.addAttribute("updatePassword", true);
			return "home";
		} else {
			return "redirect:/";
		}
	}
}
