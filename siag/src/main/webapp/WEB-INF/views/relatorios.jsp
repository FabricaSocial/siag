<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>
	
	<div class="row">
		<ul class="breadcrumbs">
			<li><a href="/fabricasocial/">Home</a></li>
			<li class="current">Relatórios</li>
		</ul>
	</div>
	
	<div class="row">
		<dl class="tabs" data-tab>
			<dd class="active"><a href="#agendamento">Agendamentos</a></dd>
			<dd><a href="">Relatório 2</a></dd>
			<dd><a href="">Relatório 3</a></dd>
		</dl>
	</div>

	<div class="row">
		<div class="tabs-content">
		<div class="content active" id="agendamento" align="center">
	  	</div>
	</div>
	</div>

<jsp:include page="relatorio_agendamento.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>