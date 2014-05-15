<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>

	<div class="row">
		<div class="large-2 columns">
			<p></p>
		</div>
		<div class="large-8 columns">
			<div class="row">
				<div class="large-6 columns " >
					<a class="button large expand radius" href="#">CADASTRO</a>
				</div>
				
				<div class="large-6 columns">
					<a class="button large expand radius" data-reveal-id="agendamento">AGENDAMENTO</a>
				</div>
			</div>
			
			<div class="row">
				<div class="large-6 columns " >
					<a class="button large expand radius" href="#">SORTEIOS</a>
				</div>
				
				<div class="large-6 columns">
					<a class="button large expand radius" href="#">RELATÓRIOS</a>
				</div>
			</div>
			
		</div>
		<div class="large-2 columns">
			<p></p>
		</div>
	</div>
	
	
	<div id="agendamento" class="small reveal-modal" data-reveal>
		<h3>Agendamento</h3><hr/>
		<p>Digite o CPF do inscrito:</p>
		
		<div class="row">
			<form action="/fabricasocial/agendamento" method="POST">
				<div class="row">
					<div class="large-3 columns"> <p></p>
					</div>
					<div class="large-6 columns">
						
						<input type="text" id="cpf" name="cpf" required>
						<script type="text/javascript">
							jQuery("#cpf").mask("999.999.999-99");
						</script>
					</div>
					<div class="large-3 columns">
						<p></p>
					</div>
				</div>
				<div class="row">
					<div class="large-4 columns">
						<p></p>
					</div>
					
					<div class="large-4 columns">
						<button class="postfix">Agendar</button>
					</div>
					
					<div class="large-4 columns">
						<p></p>
					</div>
				</div>
			</form>
		</div>
		
		<a class="close-reveal-modal">&#215;</a>
	</div>

<jsp:include page="footer.jsp"></jsp:include>