package br.gov.fabricasocial.controllers;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controla paginas basicas da aplicacao
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */

@Controller
public class AppController {
	private SessionController sessionController = new SessionController();

	/**
	 * Chama a pagina inicial da aplicacao
	 * @return Retorna a pagina home ou redireciona para tela de login
	 */
	@RequestMapping(value = "/home")
	public String home(Locale locale, Model model, HttpServletRequest request) {
		if(sessionController.checkSession(request)){
			return "home";
		} else {
			return "redirect:/";
		}
	}
	
	/**
	 * Efetua logoff da aplicacao
	 * @return Redireciona para tela de login
	 */
	@RequestMapping(value = "/sair")
	public String logout(HttpServletRequest request) {
		if(sessionController.checkSession(request)){
			sessionController.finishSession(request);
		} else {
			//Nothing to do
		}
		return "redirect:/";
	}
}
