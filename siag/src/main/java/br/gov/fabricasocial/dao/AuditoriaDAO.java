package br.gov.fabricasocial.dao;

/**
 * Interface de acesso ao banco de dados com relacao a Auditoria
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */
public interface AuditoriaDAO {
	/**
	 * Registro de alteracoes, exclusoes e inclusoes de agendamentos
	 * @param candidate ID do candidato
	 * @param userId ID do usuario
	 * @param auditStatus Inclusao ou Exclusao
	 */
	public void scheduling(int candidate, int userId, int auditStatus);
}
