package br.gov.fabricasocial.dao;

import java.util.List;

import br.gov.fabricasocial.models.Candidate;
import br.gov.fabricasocial.models.Schedule;
import br.gov.fabricasocial.models.Scheduling;

public interface AgendamentoDAO {
	public List<Candidate> findByCPF(String cpf);
	public List<Schedule> getScheduleAvailable(String date);
	public boolean schedule(Scheduling scheduling, int userId);
	public void unschedule(Candidate candidate, int date, int time, int userId);
	public Schedule getVacancy(int date, int hour);
	public Schedule getCandidateScheduling(int idCandidate);
}
