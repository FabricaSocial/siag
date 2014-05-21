package br.gov.fabricasocial.models;

public class SchedulingReport {
	private String Candidate;
	private String CandidateCpf;
	private String User;
	private String Date;
	private String Hour;
	
	public String getCandidate() {
		return Candidate;
	}
	public void setCandidate(String candidate) {
		Candidate = candidate;
	}
	public String getCandidateCpf() {
		return CandidateCpf;
	}
	public void setCandidateCpf(String candidateCpf) {
		CandidateCpf = candidateCpf;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getHour() {
		return Hour;
	}
	public void setHour(String hour) {
		Hour = hour;
	}
}
