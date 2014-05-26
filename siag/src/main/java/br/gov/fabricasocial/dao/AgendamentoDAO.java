package br.gov.fabricasocial.dao;

import java.util.List;

import br.gov.fabricasocial.models.Candidate;
import br.gov.fabricasocial.models.Schedule;
import br.gov.fabricasocial.models.Scheduling;

/**
 * Interface de acesso ao banco de dados com relacao aos Agendamentos
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */
public interface AgendamentoDAO {
	/**
	 * Procura por candidatos com o CPF informado
	 * @param cpf CPF do candidato
	 * @return Lista de candidatos com o CPF
	 */
	public List<Candidate> findByCPF(String cpf);
	
	/**
	 * Obtem horarios disponiveis de um determinado dia
	 * @param date Data desejada
	 * @return Lista com os horarios disponiveis
	 */
	public List<Schedule> getScheduleAvailable(String date);
	
	/**
	 * Realiza o agendamento
	 * @param scheduling Informacoes do agendamento
	 * @param userId Usuario que realizou o agendamento
	 * @return true: agendamento realizado, false: agendamento nao realizado
	 */
	public boolean schedule(Scheduling scheduling, int userId);
	
	/**
	 * Exclui agendamento
	 * @param candidate Candidato que tera seu agendamento excluido
	 * @param date Data do agendamento
	 * @param time Horario do agendamento
	 * @param userId ID do usuario que realiza o cancelamento
	 */
	public void unschedule(Candidate candidate, int date, int time, int userId);
	
	/**
	 * Obtem a quantidade de vagas para determinados horarios
	 * @param date Data desejada
	 * @param hour Horario desejado
	 * @return Objeto com informacoes do horario informado
	 */
	public Schedule getVacancy(int date, int hour);
	
	/**
	 * Verifica se o candidato possui algum agendamento
	 * @param idCandidate ID do candidato
	 * @return Objeto com data e horario do agendamento, null se o candidato nao possui agendamento
	 */
	public Schedule getCandidateScheduling(int idCandidate);
}
