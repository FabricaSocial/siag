package br.gov.fabricasocial.models;

import java.util.List;

public class SchedulingReport {
	private String Time;
	private List<Integer> vacancies;
	
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public List<Integer> getVacancies() {
		return vacancies;
	}
	public void setVacancies(List<Integer> vacancies) {
		this.vacancies = vacancies;
	}
}
