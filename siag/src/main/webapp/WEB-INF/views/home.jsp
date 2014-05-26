<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>

	<div class="row">
		<div class="large-2 medium-2 columns ">
			<p></p>
		</div>
		<div class="large-8 medium-8 columns">
			<div class="row">
				<div class="large-6 medium-6 columns " >
					<a class="button large expand radius disabled" href="">CADASTRO</a>
				</div>
				
				<div class="large-6 medium-6 columns">
					<a class="button large expand radius" data-reveal-id="agendamento">AGENDAMENTO</a>
				</div>
			</div>
			
			<div class="row">
				<div class="large-6 medium-6 columns " >
					<a class="button large expand radius disabled" href="">SORTEIOS</a>
				</div>
				
				<div class="large-6 medium-6 columns">
					<a class="button large expand radius" href="/fabricasocial/relatorios">RELATÓRIOS</a>
				</div>
			</div>
			
		</div>
		<div class="large-2 medium-2 columns">
			<p></p>
		</div>
	</div>
	
	<div id="updatePassword" class="small reveal-modal" data-reveal>
		<h2>Alterar Senha</h2><hr/>
		<p>Senha alterada com sucesso!</p>
		<a class="close-reveal-modal">&#215;</a>
	</div>

<jsp:include page="agendamento_modal.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>