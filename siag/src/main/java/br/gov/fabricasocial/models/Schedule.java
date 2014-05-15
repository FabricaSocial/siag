package br.gov.fabricasocial.models;

import java.sql.Time;
import java.sql.Date;

public class Schedule {
	
	private Date date;
	private Time time;
	private int vacancy;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getVacancy() {
		return vacancy;
	}
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
}
