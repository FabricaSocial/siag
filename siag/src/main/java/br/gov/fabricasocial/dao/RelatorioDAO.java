package br.gov.fabricasocial.dao;

import java.util.List;

import br.gov.fabricasocial.models.SchedulingReport;

/**
 * Interface de acesso ao banco de dados com relacao aos Relatorios
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */
public interface RelatorioDAO {
	/**
	 * Sera realizado a busca da data
	 * @return lista de string com as datas disponiveis
	 */
	public List<String> schedulingDays();
	
	public List<SchedulingReport> schedulingTimes();
}
