package br.gov.fabricasocial.dao.jdbc;

import java.sql.Connection;
import java.util.List;

import br.gov.fabricasocial.dao.RelatorioDAO;
import br.gov.fabricasocial.models.SchedulingReport;

public class JdbcRelatorioDAO extends JdbcBaseDAO implements RelatorioDAO{
	private static final String username = "Cadastro";
	private static final String password = "cadastro";
	
	@Override
	public List<SchedulingReport> schedulingReport() {
		Connection connection = this.getConnection(username, password);
		
		String selectSQL =	"SELECT cand.nome, cand.cpf, user.nomeUsuario, d.`data`, h.horario" +
							"FROM Agendamento AS a " +
							"INNER JOIN Candidato AS cand ON cand.idCandidato=a.idCandidato " +
							"INNER JOIN Usuario AS user ON user.idUsuario=a.idUsuario " +
							"INNER JOIN Dia AS d ON d.idDia=a.idDia " +
							"INNER JOIN Hora AS h ON h.idHora=a.idHora " +
							"ORDER BY d.`data`, h.horario";
		
		return null;
	}

}
