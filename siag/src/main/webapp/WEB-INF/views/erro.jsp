<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>

<div class="row" align="center">
	<h2>Hor�rio Indispon�vel</h2>
	<hr>
	<p>Desculpe, j� n�o existem vagas dispon�veis nesse hor�rio.</p>
	
	<a href="" data-reveal-id="agendamento">Efetuar novo agendamento</a>
</div>
<jsp:include page="agendamento_modal.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>