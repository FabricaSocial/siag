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

/**
 * Controla o modulo de agendamento.
 * 
 * @author DETI - Departamento de Tecnologia de Informacao
 */

@Controller
public class AgendamentoController {
	private SessionController sessionController = new SessionController();
	private FormatString formatString = new FormatString();
	AgendamentoDAO dao = new JdbcAgendamentoDAO();
	
	private static final int FIST_ELEMENT = 0;

	/**
	 * Metodo que verifica se o candidato ja possui um agendamento e redireciona para a pagina correta.
	 * @param cpf CPF do candidato
	 * @return Pagina de agendamento, reagendamento ou redireciona para tela de login
	 */
	@RequestMapping(value = "/agendamento", method = RequestMethod.POST)
	public String scheduling(HttpServletRequest request, Model model, @RequestParam("cpf") String cpf) {
		if(sessionController.checkSession(request)) {
			
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

	/**
	 * (AJAX) Obtem os horarios disponiveis para o dia selecionado.
	 * @param date Data selecionada
	 * @return JSON com os horarios disponiveis
	 */
	@RequestMapping(value ="/getTime/{date}", method=RequestMethod.GET)  
	public @ResponseBody String getJsonTimes(@PathVariable String date){
		
		List<Schedule> schedules = dao.getScheduleAvailable(date);
		
		JsonConverter converter = new JsonConverter();
		
		String json = converter.getTimeJson(schedules);
		
		return json;
	}
	
	/**
	 * (AJAX) Obtem a quantidade de vagas disponiveis para o horario selecionado
	 * @param date ID do dia selecionado
	 * @param hour ID do horario selecionado
	 * @return Quantidade de vagas em formato de String
	 */
	@RequestMapping(value ="/getVacancy/{date}/{hour}", method=RequestMethod.GET)  
	public @ResponseBody String getVacancy(	@PathVariable int date,
											@PathVariable int hour){
		
		Schedule vacancy = dao.getVacancy(date, hour);
		String vacancyAvailable = Integer.toString(vacancy.getVacancy());
		
		return vacancyAvailable;
	}
	
	
	/**
	 * Realiza o agendamento
	 * @param day ID do dia selecionado
	 * @param hour ID do horario selecionado
	 * @param candidate ID do candidato
	 * @return Pagina de Sucesso, Erro ou redirecionamento para tela de login
	 */
	@RequestMapping(value="/agendar", method=RequestMethod.POST)
	public String schedule(HttpServletRequest request,
							@RequestParam("idDay") int day,
							@RequestParam("hour") int hour,
							@RequestParam("candidate") int candidate) {		
		if(sessionController.checkSession(request)) {
			
			Scheduling scheduling = new Scheduling(candidate, 1, day, hour);
			
			int userId = sessionController.getLoggedUserId(request);
			if(dao.schedule(scheduling, userId)) {
				return "agendamento_sucesso";
			} else {
				return "erro";
			}
		} else {
			return "redirect:/";
		}
	}
	
	/**
	 * Verifica se o CPF informado foi sorteado
	 * @param field1 3 primeiros digitos do CPF
	 * @param field2 3 segundos digitos do CPF
	 * @param field3 3 ultimos digitos do CPF
	 * @param digit Digitos verificadores do CPF
	 * @return Nome do Candidato ou mensagem informando CPF inexistente
	 */
	@RequestMapping(value ="/cpfValidate/{field1}.{field2}.{field3}-{digit}", method=RequestMethod.GET)  
	public @ResponseBody String cpfValidate(@PathVariable String field1,
											@PathVariable String field2,
											@PathVariable String field3,
											@PathVariable String digit){ 
		
	    String cpf = field1 + field2 + field3 + digit;
	    
	    List<Candidate> candidates = dao.findByCPF(cpf);
		
		if(candidates.size() > 0) {
			Candidate candidate = candidates.get(FIST_ELEMENT);
			return candidate.getNome();
		}
		return "CPF não cadastrado";

	}
	
	/**
	 * Cancela o agendamento atual do candidato e realiza o reagendamento
	 * @param idCandidate ID do candidato
	 * @param date ID do dia que o candidato estava agendado
	 * @param time ID do horario que o candidato estava agendado
	 * @param cpf CPF do candidato
	 * @return Pagina de agendamento ou redireciona para tela de login
	 */
	@RequestMapping(value="/reagendamento", method=RequestMethod.POST)
	public String reSchedule(HttpServletRequest request, Model model,
														@RequestParam("idCandidate") int idCandidate,
														@RequestParam("date") int date,
														@RequestParam("time") int time,
														@RequestParam("cpf") String cpf){
		
		if(sessionController.checkSession(request)) {
			int userId = sessionController.getLoggedUserId(request);
			
			cpf = formatString.unformatCPF(cpf);
			List<Candidate> candidates = dao.findByCPF(cpf);
			
			Candidate candidate = candidates.get(FIST_ELEMENT);
			candidate.setCpf(formatString.formatCPF(cpf));
			model.addAttribute(candidate);
			
			dao.unschedule(candidate, date, time, userId);
			
			return "agendamento";
		} else{
			return "redirect:/";
		}
		
	}
}
