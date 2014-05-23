<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>

	<div class="row" align="center">
		<h2>Sucesso!</h2>
		<hr>
		
		<div class="row">
			<ul class="breadcrumbs">
				<li><a href="/fabricasocial/">Home</a></li>
				<li><a href="" data-reveal-id="agendamento">Agendamento</a></li>
				<li class="current">Sucesso</li>
			</ul>
		</div>
		
		<p>Agendamento realizado com sucesso.</p>
		
		<a href="" data-reveal-id="agendamento">Efetuar novo agendamento</a>
	</div>
<jsp:include page="agendamento_modal.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>