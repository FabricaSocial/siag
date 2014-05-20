package br.gov.fabricasocial.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	private SessionController sessionController = new SessionController();
	private FormatString formatString = new FormatString();
	AgendamentoDAO dao = new JdbcAgendamentoDAO();
	
	private static final int FIST_ELEMENT = 0;
	private static String username = "Cadastro";
	private static String password = "cadastro";

	@RequestMapping(value = "/agendamento", method = RequestMethod.POST)
	public String scheduling(HttpServletRequest request, Model model, @RequestParam("cpf") String cpf) {
		if(sessionController.checkSession(request)) {
			dao.setUserLogin(username, password);
			
			cpf = formatString.unformatCPF(cpf);
			List<Candidate> candidates = dao.findByCPF(cpf);
			
			Candidate candidate = candidates.get(FIST_ELEMENT);
			candidate.setCpf(formatString.formatCPF(cpf));
			model.addAttribute(candidate);
			
			Schedule schedule = dao.getCandidateScheduling(candidate.getIdCandidato());
			
			if(schedule != null) {
				model.addAttribute("scheduledCandidate", true);
				model.addAttribute(schedule);
				
				return "reagendamento";
			} else {
				return "agendamento";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value ="/getTime/{date}", method=RequestMethod.GET)  
	public @ResponseBody String getJsonTimes(@PathVariable String date, Model model){
		dao.setUserLogin(username, password);
		
		List<Schedule> schedules = dao.getScheduleAvailable(date);
		
		JsonConverter converter = new JsonConverter();
		
		String json = converter.getTimeJson(schedules);
		
		return json;
	}
	
	@RequestMapping(value ="/getVacancy/{date}/{hour}", method=RequestMethod.GET)  
	public @ResponseBody String getVacancy(	Model model,
											@PathVariable int date,
											@PathVariable int hour){
		dao.setUserLogin(username, password);
		
		Schedule vacancy = dao.getVacancy(date, hour);
		String vacancyAvailable = Integer.toString(vacancy.getVacancy());
		
		return vacancyAvailable;
	}
	
	@RequestMapping(value="/agendar", method=RequestMethod.POST)
	public String schedule(Model model, HttpServletRequest request,
										@RequestParam("idDay") int day,
										@RequestParam("hour") int hour,
										@RequestParam("candidate") int candidate) {		
		if(sessionController.checkSession(request)) {
			dao.setUserLogin(username, password);
			
			Scheduling scheduling = new Scheduling(candidate, 1, day, hour);

			if(dao.schedule(scheduling)) {
				return "agendamento_sucesso";
			} else {
				return "erro";
			}
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value ="/cpfValidate/{field1}.{field2}.{field3}-{digit}", method=RequestMethod.GET)  
	public @ResponseBody String cpfValidate(Model model, 	@PathVariable String field1,
															@PathVariable String field2,
															@PathVariable String field3,
															@PathVariable String digit){ 
		dao.setUserLogin(username, password);
		
	    String cpf = field1 + field2 + field3 + digit;
	    
	    List<Candidate> candidates = dao.findByCPF(cpf);
		
		if(candidates.size() > 0) {
			Candidate candidate = candidates.get(FIST_ELEMENT);
			return candidate.getNome();
		}
		return "CPF não cadastrado";

	}
	
	@RequestMapping(value="/reagendamento", method=RequestMethod.POST)
	public String reSchedule(HttpServletRequest request, Model model,
														@RequestParam("idCandidate") int idCandidate,
														@RequestParam("date") int date,
														@RequestParam("time") int time,
														@RequestParam("cpf") String cpf){
		
		if(sessionController.checkSession(request)) {
			dao.setUserLogin(username, password);
			
			dao.unschedule(idCandidate, date, time);
			
			cpf = formatString.unformatCPF(cpf);
			List<Candidate> candidates = dao.findByCPF(cpf);
			
			Candidate candidate = candidates.get(FIST_ELEMENT);
			candidate.setCpf(formatString.formatCPF(cpf));
			model.addAttribute(candidate);
			
			return "agendamento";
		} else{
			return "redirect:/";
		}
		
	}
}
