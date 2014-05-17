<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>

	<div class="row">
		<div align="center">
			<h2>Agendamento</h2>
			<hr/>
		</div>
	</div>

	<div class="row">
		<div class="large-1 medium-1 columns">
			<p></p>
		</div>
		<div class="large-5 medium-5 columns">
			<ul class="pricing-table">
				<li class="title"><b>${candidate.nome}</b></li>
				<li class="bullet-item"><b>CPF: </b> ${candidate.cpf}</li>
				<li class="bullet-item"><b>Programa:</b> ${candidate.programa}</li>
				<li class="bullet-item">${candidate.necessidadeEspecial }</li>
				<li class="description">Inscrição em: ${candidate.dataInscricao}</li>
			</ul>
		</div>
		<div class="large-1 medium-1 columns">
			<p></p>
		</div>
		<div class="large-4 medium-4 columns">
			<br>
			<form action="agendar" method="post">
				<div class="row collapse">
					<div class="small-3 large-2 medium-2 columns">
						<span class="prefix">Dia</span>
					</div>
					<div class="small-9 medium-10 large-10 columns">
						<input type="text" placeholder="Selecione um dia" readonly id="datepicker" name="day">
						<input type="hidden" id="idDay" name="idDay">
						<input type="hidden" value="${candidate.idCandidato}" name="candidate">
					</div>
			  	</div>
			  	
			  	<div class="row collapse">
					<div class="small-3 large-2 medium-2 columns">
						<span class="prefix">Hora</span>
					</div>
					<div class="small-9 large-10 medium-10 columns">
						<select id="hourSelect" name="hour">
							<option DISABLED>Selecione um horário</option>
						</select>
					</div>
			  	</div>
			  	
			  	<div class="row collapse">
					<div class="small-3 large-7 medium-7 columns">
						<span class="prefix">Vagas Disponíveis</span>
					</div>
					<div class="small-9 large-5 medium-5 columns">
						<input type="text" readonly id="vacancy" name="vacancy">
					</div>
			  	</div>

			  	<div class="row">
			  		<div class="large-3 medium-3 columns"><p></p></div>
			  		<div class="large-6 medium-6 columns">
			  			<button id="agendar" class="postfix radius">Agendar</button>
			  		</div>
			  		<div class="large-3 medium-3 columns"><p></p></div>
			  	</div>
			</form>
		</div>
		<div class="large-1 medium-1 columns">
			<p></p>
		</div>
	</div>


<jsp:include page="agendamento_jquery.jsp"></jsp:include>	
<jsp:include page="agendamento_datepicker.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>