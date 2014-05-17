package br.gov.fabricasocial.models;


public class Schedule {
	
	private int idDate;
	private String date;
	private int idTime;
	private String time;
	private int vacancy;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getVacancy() {
		return vacancy;
	}
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
	public int getIdTime() {
		return idTime;
	}
	public void setIdTime(int idTime) {
		this.idTime = idTime;
	}
	public int getIdDate() {
		return idDate;
	}
	public void setIdDate(int idDate) {
		this.idDate = idDate;
	}
}
