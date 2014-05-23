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
	RelatorioDAO dao = new JdbcRelatorioDAO();
	
	@RequestMapping(value="/relatorios")
	public String schedulingReport(Model model) {
		List<String> dates = dao.schedulingDays();
		List<SchedulingReport> schedulingReports = dao.schedulingTimes();
		
		model.addAttribute("dates", dates);
		model.addAttribute("schedulingReports", schedulingReports);
		
		return "relatorios";
	}

}
