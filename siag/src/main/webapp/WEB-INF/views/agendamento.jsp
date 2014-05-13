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
			<form>
				<div class="row collapse">
					<div class="small-3 large-2 columns">
						<span class="prefix">Dia</span>
					</div>
					<div class="small-9 large-10 columns">
						<select>
							<option>10/06/2014</option>
						</select>
					</div>
			  	</div>
			  	
			  	<div class="row collapse">
					<div class="small-3 large-2 columns">
						<span class="prefix">Hora</span>
					</div>
					<div class="small-9 large-10 columns">
						<select>
							<option>09:00</option>
						</select>
					</div>
			  	</div>
			  	
			  	<div class="row">
				  	<div class="large-2 columns">
				  		<p></p>
				  	</div>
				  	<div class="large-8 columns">
				  		<p><b>Vagas Disponíveis:</b> 20</p>
				  	</div>
				  	<div class="large-2 columns">
				  		<p></p>
				  	</div>
			  	</div>
			  	
			  	<div class="row">
			  		<div class="large-3 columns"><p></p></div>
			  		<div class="large-6 columns">
			  			<button class="postfix radius">Agendar</button>
			  		</div>
			  		<div class="large-3 columns"><p></p></div>
			  	</div>
			</form>
		</div>
		<div class="large-1 columns">
			<p></p>
		</div>
	</div>

<jsp:include page="footer.jsp"></jsp:include>