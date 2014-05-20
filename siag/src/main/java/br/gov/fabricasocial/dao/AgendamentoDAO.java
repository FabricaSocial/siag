package br.gov.fabricasocial.dao;

import java.util.List;

import br.gov.fabricasocial.models.Candidate;
import br.gov.fabricasocial.models.Schedule;
import br.gov.fabricasocial.models.Scheduling;

public interface AgendamentoDAO {
	public void setUserLogin(String username, String password);
	public List<Candidate> findByCPF(String cpf);
	public List<Schedule> getScheduleAvailable(String date);
	public boolean schedule(Scheduling scheduling);
	public void unschedule(int idCandidate, int date, int time);
	public Schedule getVacancy(int date, int hour);
	public Schedule getCandidateScheduling(int idCandidate);
}
