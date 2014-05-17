package br.gov.fabricasocial.controllers;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping(value = "/home")
	public String agendamento(Locale locale, Model model) {
		return "home";
	}
}
