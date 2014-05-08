<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>
	
	<div class="row">
		<div class="large-12" align="center">
			<img src="resources/img/gdf-logo.jpg" alt="gdf-logo" align="left" width="15%">
			<div align="right">
				<br><h2><b>Fabrica Social</b></h2>
				<h4>Centro de Capacitacao Profissional - CIAS</h4>
			</div>
			<hr />
		</div>
	</div>

	<div class="row">
		<div class="large-4 medium-4 columns">
			<p></p>
		</div>
		<div class="large-4 medium-4 columns panel" align="center">
			<h4><b>Login SIAG</b></h4><hr/>
			<form action="login" method="post">
			<c:choose>
				<c:when test="${localUsername != ''}">
					<input type="text" value="${localUsername}" id="User" name="username">
				</c:when>
				<c:otherwise>
					<input type="text" placeholder="Usuário" id="User" name="username">
				</c:otherwise>
			</c:choose>
				<input type="password" placeholder="Senha" id="Password" name="password">
				<button class=" postfix radius">Entrar</button>
			</form>
		</div>
		<div class="large-4 medium-4 columns">
			<p></p>
		</div>
	</div>
	
	<c:if test="${errorMessage == 1}">
		<div id="errorModal" class="small reveal-modal" data-reveal>
			<h2>Erro!</h2>
			<p><b>Erro de Autenticação:</b>
			Usuário ou senha inválidos.</p>
			<a class="close-reveal-modal">&#215;</a>
		</div>
	</c:if>

<jsp:include page="footer.jsp"></jsp:include>
