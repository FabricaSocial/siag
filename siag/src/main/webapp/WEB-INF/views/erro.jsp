<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>

<div class="row" align="center">
	<h2>Horário Indisponível</h2>
	<hr>
	<p>Desculpe, já não existem vagas disponíveis nesse horário.</p>
	
	<a href="" data-reveal-id="agendamento">Efetuar novo agendamento</a>
</div>
<jsp:include page="agendamento_modal.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>