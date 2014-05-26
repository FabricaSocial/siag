<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>

	<div class="row">
		<div class="large-4 medium-4 columns">
			<p></p>
		</div>
		<div class="large-4 medium-4 columns panel" align="center">
			<h4><b>Login SIAG</b></h4><hr/>
			<form action="/fabricasocial/entrar" method="post">
				<input type="text" placeholder="Usu�rio" id="User" name="username">
				<input type="password" placeholder="Senha" id="Password" name="password">
				<button class=" postfix radius">Entrar</button>
			</form>
		</div>
		<div class="large-4 medium-4 columns">
			<p></p>
		</div>
	</div>
	
	<c:choose>
		<c:when  test="${errorMessage == 1}">
			<div id="errorModal" class="small reveal-modal" data-reveal>
				<h2>Erro!</h2>
				<p><b>Erro de Autentica��o:</b>
				Usu�rio ou senha inv�lidos.</p>
				<a class="close-reveal-modal">&#215;</a>
			</div>
		</c:when>
		<c:when  test="${errorMessage == 2}">
			<div id="errorModal" class="small reveal-modal" data-reveal>
				<h2>Erro!</h2>
				<p><b>Erro de Conex�o:</b>
				N�o foi poss�vel conectar-se ao Active Directory.</p>
				<a class="close-reveal-modal">&#215;</a>
			</div>
		</c:when>
	</c:choose>
	
	<div id="invalidAuth" class="reveal-modal small" data-reveal>
		<h2>Falha na autentica��o</h2>
		<p>Usu�rio ou senha inv�lida.</p>
		<a class="close-reveal-modal">&#215;</a>
	</div>
	
	<div id="unregistredUser" class="reveal-modal small" data-reveal>
		<h2>Usu�rio Inexistente</h2>
		<p>Usu�rio informado n�o est� cadastrado.</p>
		<a class="close-reveal-modal">&#215;</a>
	</div>

<jsp:include page="footer.jsp"></jsp:include>
