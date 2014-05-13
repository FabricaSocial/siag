package br.gov.fabricasocial.controllers;

import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AgendamentoController {

	@RequestMapping(value = "/agendamento", method = RequestMethod.POST)
	public String agendamento(Locale locale, Model model, @RequestParam("cpf") String cpf) {
		Date date = new Date();
				
		model.addAttribute("data", date);
		model.addAttribute("cpf", cpf);
		
		return "agendamento";
	}
}
