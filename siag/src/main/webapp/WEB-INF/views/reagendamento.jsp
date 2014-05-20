<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>	
	
	<div class="row" align="center">
		<h2>Atenção</h2><hr/>
	</div>
	
	<div class="row">
		<div class="large-3 columns"><p></p></div>
		<div class="large-6 columns">
			<div class="row">
				<p>Candidato já está agendado para: </p>
			</div>
			
			<div class="row" align="center">
				<p><strong>${schedule.getDate()}</strong> às <strong>${schedule.getTime()}</strong></p>
			</div>
			
			<div class="row">
				<p>Deseja alterar o agendamento?</p>
			</div>
			
			<div class="row" align="center">
				<div class="large-6 columns">
					<form action="/fabricasocial/reagendamento" method="POST">
						<input type="hidden" name="cpf" value="${candidate.getCpf()}">
						<input type="hidden" name="idCandidate" value="${candidate.getIdCandidato()}">
						<input type="hidden" name="date" value="${schedule.getIdDate()}">
						<input type="hidden" name="time" value="${schedule.getIdTime()}">
						<button class="postfix">SIM</button>
					</form>
				</div>
				<div class="large-6 columns">
					<a href="/fabricasocial/home" class="button postfix">NÃO</a>
				</div>
			</div>
		</div>
		<div class="large-3 columns"><p></p></div>
	</div>
	
	
<jsp:include page="footer.jsp"></jsp:include>