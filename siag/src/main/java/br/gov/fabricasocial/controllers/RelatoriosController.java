package br.gov.fabricasocial.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.gov.fabricasocial.dao.RelatorioDAO;
import br.gov.fabricasocial.dao.jdbc.JdbcRelatorioDAO;
import br.gov.fabricasocial.models.SchedulingReport;

/**
 * Controla o modulo de Relatorio
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */
@Controller
public class RelatoriosController {
	RelatorioDAO dao = new JdbcRelatorioDAO();
	SessionController sessionController = new SessionController();
	
	/**
	 * Chama pagina de relatorios
	 * @return Pagina de relatorios ou redireciona para tela de login
	 */
	@RequestMapping(value="/relatorios")
	public String schedulingReport(Model model, HttpServletRequest request) {
		if(sessionController.checkSession(request)){
			List<String> dates = dao.schedulingDays();
			List<SchedulingReport> schedulingReports = dao.schedulingTimes();
			
			model.addAttribute("dates", dates);
			model.addAttribute("schedulingReports", schedulingReports);
			
			return "relatorios";
		} else {
			return "redirect:/";
		}
	}

}
