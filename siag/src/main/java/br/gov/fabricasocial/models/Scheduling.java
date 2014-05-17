package br.gov.fabricasocial.models;

public class Scheduling {
	private int Candidate;
	private int User;
	private int Date;
	private int Hour;
	
	public Scheduling(int candidate, int user, int date, int hour) {
		this.Candidate = candidate;
		this.User = user;
		this.Date = date;
		this.Hour = hour;
	}
	
	public int getCandidate() {
		return Candidate;
	}
	public void setCandidate(int candidate) {
		Candidate = candidate;
	}
	public int getUser() {
		return User;
	}
	public void setUser(int user) {
		User = user;
	}
	public int getDate() {
		return Date;
	}
	public void setDate(int date) {
		Date = date;
	}
	public int getHour() {
		return Hour;
	}
	public void setHour(int hour) {
		Hour = hour;
	}
}
