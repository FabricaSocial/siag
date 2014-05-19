package br.gov.fabricasocial.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	private SessionController sessionController = new SessionController();

	@RequestMapping(value = "/home")
	public String home(Locale locale, Model model, HttpServletRequest request) {
		if(sessionController.checkSession(request)){
			return "home";
		} else {
			return "redirect:/";
		}
	}
	
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
