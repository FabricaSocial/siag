package br.gov.fabricasocial.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AgendamentoController {

	@RequestMapping(value = "agendamento/", method = RequestMethod.GET)
	public String agendamento(Model model) {
		
		return "agendamento";
	}
}
