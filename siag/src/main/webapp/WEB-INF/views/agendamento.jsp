<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>

	<div class="row">
		<div align="center">
			<h2>Agendamento</h2>
			<hr/>
		</div>
	</div>

	<div class="row">
		<div class="large-1 columns">
			<p></p>
		</div>
		<div class="large-5 columns">
			<ul class="pricing-table">
				<li class="title"><b>${candidate.nome}</b></li>
				<li class="bullet-item"><b>CPF: </b> ${candidate.cpf}</li>
				<li class="bullet-item"><b>Programa:</b> ${candidate.programa}</li>
				<li class="bullet-item">${candidate.necessidadeEspecial }</li>
				<li class="description">Inscrição em: ${candidate.dataInscricao}</li>
			</ul>
		</div>
		<div class="large-1 columns">
			<p></p>
		</div>
		<div class="large-4 columns">
			<br>
			<form action="agendamento/agendar/" method="post">
				<div class="row collapse">
					<div class="small-3 large-2 columns">
						<span class="prefix">Dia</span>
					</div>
					<div class="small-9 large-10 columns">
						<input type="text" placeholder="Selecione um dia" readonly id="datepicker" name="day">
						<input type="hidden" id="idDay" name="idDay">
						<input type="hidden" value="${candidate.idCandidato}" name="candidate">
					</div>
			  	</div>
			  	
			  	<div class="row collapse">
					<div class="small-3 large-2 columns">
						<span class="prefix">Hora</span>
					</div>
					<div class="small-9 large-10 columns">
						<select id="hourSelect" name="hour">
							<option DISABLED>Selecione um horário</option>
						</select>
						<!-- 
						<input type="text" placeholder="Selecione o horário" readonly id="timepicker" name="hour">
						<input type="text" id="teste">
						 -->
					</div>
			  	</div>

			  	<div class="row">
			  		<div class="large-3 columns"><p></p></div>
			  		<div class="large-6 columns">
			  			<button id="agendar" class="postfix radius">Agendar</button>
			  		</div>
			  		<div class="large-3 columns"><p></p></div>
			  	</div>
			</form>
		</div>
		<div class="large-1 columns">
			<p></p>
		</div>
	</div>

<script>
$( "#datepicker" ).blur(function() {
	var date = $('#datepicker').val()
	
	var url = 'getTime/' + date;
	
	$.ajax({
		url: url,
		dataType: 'json',
		success: function(data){
			var html = "";
			
			$.each(data, function(i,data){
				var div_data = "<option value=" + data.idTime + ">" + data.time + " - " + data.vacancy + " vagas restantes</option>";
				
				$('#idDay').val(data.idDate);
				html = html + div_data;
			});
			if(html == ""){
				html = "<option DISABLED>Sem horários disponíveis.</option>";
			}
			$('#hourSelect').html(html);
		}
	});
	
	
});
</script>
	
<jsp:include page="agendamento_datepicker.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>