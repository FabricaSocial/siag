package br.gov.fabricasocial.dao;

import java.util.List;

import br.gov.fabricasocial.models.SchedulingReport;

public interface RelatorioDAO {
	public List<String> schedulingDays();
	public List<SchedulingReport> schedulingTimes();
}
