package br.gov.fabricasocial.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RelatoriosController {
	
	@RequestMapping(value="/relatorios")
	public String schedulingReport() {
		
		
		return "relatorio";
	}

}
