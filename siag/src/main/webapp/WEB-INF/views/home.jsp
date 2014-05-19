<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>

	<div class="row">
		<div class="large-2 medium-2 columns ">
			<p></p>
		</div>
		<div class="large-8 medium-8 columns">
			<div class="row">
				<div class="large-6 medium-6 columns " >
					<a class="button large expand radius" href="#">CADASTRO</a>
				</div>
				
				<div class="large-6 medium-6 columns">
					<a class="button large expand radius" data-reveal-id="agendamento">AGENDAMENTO</a>
				</div>
			</div>
			
			<div class="row">
				<div class="large-6 medium-6 columns " >
					<a class="button large expand radius" href="#">SORTEIOS</a>
				</div>
				
				<div class="large-6 medium-6 columns">
					<a class="button large expand radius" href="#">RELATÓRIOS</a>
				</div>
			</div>
			
		</div>
		<div class="large-2 medium-2 columns">
			<p></p>
		</div>
	</div>
	
	${cpfNaoCadastrado}

<jsp:include page="agendamento_modal.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>