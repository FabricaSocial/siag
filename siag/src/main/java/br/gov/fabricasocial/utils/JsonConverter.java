package br.gov.fabricasocial.utils;

import java.util.List;

import br.gov.fabricasocial.dao.AgendamentoDAO;
import br.gov.fabricasocial.dao.jdbc.JdbcAgendamentoDAO;
import br.gov.fabricasocial.models.Schedule;

import com.google.gson.Gson;

public class JsonConverter {

	public String getTimeJson(List<Schedule> schedules) {
		Gson gson = new Gson();
		
		String json = gson.toJson(schedules);
		
		return json;
	}
}
