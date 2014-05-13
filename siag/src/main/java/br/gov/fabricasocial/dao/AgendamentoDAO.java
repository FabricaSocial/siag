package br.gov.fabricasocial.dao;

import java.util.List;

import br.gov.fabricasocial.models.Candidate;

public interface AgendamentoDAO {
	public void setUserLogin(String username, String password);
	public List<Candidate> findByCPF(String cpf);

}
