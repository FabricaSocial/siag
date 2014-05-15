package br.gov.fabricasocial.controllers;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.fabricasocial.dao.AgendamentoDAO;
import br.gov.fabricasocial.dao.jdbc.JdbcAgendamentoDAO;
import br.gov.fabricasocial.models.Candidate;
import br.gov.fabricasocial.utils.FormatString;

@Controller
public class AgendamentoController {
	private static final int FIST_ELEMENT = 0;
	private static String username = "Cadastro";
	private static String password = "cadastro";

	@RequestMapping(value = "/agendamento", method = RequestMethod.POST)
	public String agendamento(Locale locale, Model model, @RequestParam("cpf") String cpf) {
		AgendamentoDAO dao = new JdbcAgendamentoDAO();
		dao.setUserLogin(username, password);
		
		FormatString formatString = new FormatString();
		
		cpf = formatString.unformatCPF(cpf);
		
		List<Candidate> candidates = dao.findByCPF(cpf);
		
		if(candidates.size() > 0) {
			Candidate candidate = candidates.get(FIST_ELEMENT);
			candidate.setCpf(formatString.formatCPF(cpf));
			model.addAttribute(candidate);
		}
		return "agendamento";
	}
}
