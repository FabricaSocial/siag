<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>

	<div class="row">
		<div align="center">
			<h2>Agendamento</h2>
			<hr/>
		</div>
	</div>
	
	<div class="row">
		<ul class="breadcrumbs">
			<li><a href="/fabricasocial/">Home</a></li>
			<li class="current">Agendamento</li>
		</ul>
	</div>

	<div class="row" data-equalizer>
		<div class="large-1 medium-1 columns">
			<p></p>
		</div>
		<div class="large-5 medium-5 columns" >
			<ul class="pricing-table">
				<li class="title"><b>${candidate.nome}</b></li>
				<li class="bullet-item"><b>CPF: </b> ${candidate.cpf}</li>
				<li class="bullet-item"><b>Programa:</b> ${candidate.programa}</li>
				<li class="bullet-item">${candidate.necessidadeEspecial }</li>
				<li class="description">Inscrição em: ${candidate.dataInscricao}</li>
			</ul>
		</div>
		
		<div class="large-1 medium1 columns"><p></p></div>
		
		<div class="large-4 medium-4 columns">
			<br>
			<form action="agendar" method="post">
			<div class="row collapse">
				<div class="small-3 large-2 medium-2 columns">
					<span class="prefix">Dia</span>
				</div>
				<div class="small-9 medium-10 large-10 columns">
					<input type="text" placeholder="Clique para escolher um dia..." readonly id="datepicker" name="day">
					<input type="hidden" id="idDay" name="idDay">
					<input type="hidden" value="${candidate.idCandidato}" name="candidate">
				</div>
		  	</div>
		  	
		  	<div class="row">
		  		<div class="large-12 columns" id="hourSelect" data-equalizer-watch>
		  		</div>
		  	</div>
		</div>
		<div class="large-1 medium-1 columns">
			<p></p>
		</div>
	</div>
	
	<div class="row">
		<div class="row" >
	  		<div class="large-4 medium-4 columns"><p></p></div>
	  		<div class="large-4 medium-4 columns">
	  			<button id="agendar" class="postfix radius" disabled>Agendar</button>
	  		</div>
	  		<div class="large-4 medium-4 columns"><p></p></div>
	  	</div>
	</form>
	</div>
	
<jsp:include page="agendamento_modal.jsp"></jsp:include>
<jsp:include page="agendamento_jquery.jsp"></jsp:include>
<jsp:include page="agendamento_datepicker.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>