package br.gov.fabricasocial.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.gov.fabricasocial.dao.RelatorioDAO;
import br.gov.fabricasocial.dao.jdbc.JdbcRelatorioDAO;
import br.gov.fabricasocial.models.SchedulingReport;

@Controller
public class RelatoriosController {
	
	@RequestMapping(value="/relatorios")
	public String schedulingReport(Model model) {
		RelatorioDAO dao = new JdbcRelatorioDAO();
		
		List<SchedulingReport> schedulingReports = dao.schedulingReport();
		
		for(SchedulingReport report : schedulingReports){
			System.out.println(report.getCandidate());
		}
		
		model.addAttribute("schedulingReports", schedulingReports);
		
		
		return "relatorios";
	}

}
