package br.gov.fabricasocial.controllers;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.gov.fabricasocial.dao.AgendamentoDAO;
import br.gov.fabricasocial.dao.jdbc.JdbcAgendamentoDAO;
import br.gov.fabricasocial.models.Candidate;
import br.gov.fabricasocial.models.Schedule;
import br.gov.fabricasocial.models.Scheduling;
import br.gov.fabricasocial.utils.FormatString;
import br.gov.fabricasocial.utils.JsonConverter;

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

	@RequestMapping(value ="/getTime/{date}", method=RequestMethod.GET)  
	public @ResponseBody String getJsonTimes(@PathVariable String date, Model model){ 
	    AgendamentoDAO dao = new JdbcAgendamentoDAO();
	    dao.setUserLogin(username, password);
	    
		List<Schedule> schedules = dao.getScheduleAvailable(date);
		
		JsonConverter converter = new JsonConverter();
		
		String json = converter.getTimeJson(schedules);
		
		return json;
	}
	
	@RequestMapping(value="/agendamento/agendar", method=RequestMethod.POST)
	public String schedule(Model model, @RequestParam("idDay") int day,
										@RequestParam("hour") int hour,
										@RequestParam("candidate") int candidate) {
		
		AgendamentoDAO dao = new JdbcAgendamentoDAO();
		dao.setUserLogin(username, password);
		
		Scheduling scheduling = new Scheduling(candidate, 1, day, hour);
	
		if(dao.schedule(scheduling)) {
			return "sucesso";
		} else {
			return "erro";
		}
	}	
}
