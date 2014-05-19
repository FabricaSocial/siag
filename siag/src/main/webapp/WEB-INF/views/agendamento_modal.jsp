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
					<div class="large-12 columns" id="validationResult" align="center">
					</div>
				</div>
				
				<div class="row">
					<div class="large-4 columns">
						<p></p>
					</div>
					
					<div class="large-4 columns" align="center">
						<button id="agendar" class="postfix" disabled>Agendar</button>
					</div>
					
					<div class="large-4 columns">
						<p></p>
					</div>
				</div>
			</form>
		</div>
		
		<a class="close-reveal-modal">&#215;</a>
	</div>
	
	<script>
	$( "#cpf" ).change(function() {
		var cpf = $('#cpf').val();
		
		var url = 'cpfValidate/' + cpf;
		
		$.ajax({
			url: url,
			dataType: 'text',
			success: function(data){
				var html = "";
				
				if(data != "CPF não cadastrado") {
					html = "<p><strong>Candidato encontrado:</strong> " + data + "</p>";
					$('#agendar').removeAttr('disabled');
				} else {
					html = "<p>CPF não cadastrado ou Inválido</p>";
				}
				
				$('#validationResult').html(html);
			}
		});
	});
	
	</script>