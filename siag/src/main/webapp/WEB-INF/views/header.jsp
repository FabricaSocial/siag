<%@ page isELIgnored ="false" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html class="no-js" lang="en">
  	<head>
    	<meta charset="utf-8" />
    	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		
		<link rel="stylesheet" href="resources/css/foundation.css" />
    	<script src="resources/js/vendor/modernizr.js"></script>
    	
    	<link rel="stylesheet" type="text/css" href="resources/datepicker/jquery.datetimepicker.css"/>
		<script src="resources/js/jquery-2.1.1.min.js"></script>
		
		<script src="resources/datepicker/jquery.datetimepicker.js"></script>
		<script src="resources/js/jquery.maskedinput.js"></script>
		
		<title>${mensagem}</title>
	</head>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$("input.data").mask("99/99/9999");
	    $("input.cpf").mask("999.999.999-99");
	});
	</script>
<body>

<nav class="top-bar" data-topbar>
	<ul class="title-area">
	  <li class="name">
	    <h1><a href="/fabricasocial">SIAG</a></h1>
	  </li>
	  <li class="toggle-topbar menu-icon"><a href="#"></a></li>
	</ul>
	<c:if test="${sessionScope['loggedUser'] != null}">
		<section class="top-bar-section">
		  <!-- Right Nav Section -->
		  <ul class="right">
		  	<li class="has-dropdown"><a href="">Olá, <b>${sessionScope['loggedUser']}</b></a>
			  	<ul class="dropdown">
			  		<li><a data-reveal-id="trocar_senha">Alterar Senha</a></li>
			  	</ul>
			</li>
		    <li class="active"><a href="/fabricasocial/sair">Sair</a></li>
		  </ul>
		</section>
	</c:if>
</nav>
	
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
	
	
	<div id="trocar_senha" class="reveal-modal small" data-reveal>
	  <div class="row">
	  	<h2>Alterar Senha</h2><hr/>
	  	
	  	<form action="/fabricasocial/alterar-senha" method="post" data-abide>
	  	<div class="row">
	  		<div class="large-3 medium-3 columns"><p></p></div>
	  		<div class="large-6 medium-6 columns">
				<div class="password-field">
				  <input type="password" id="password" name="password" placeholder="Nova Senha" required>
				  <small class="error">Campo obrigatório</small>
				</div>
				<div class="password-confirmation-field">
				  <input type="password" placeholder="Confirme a senha" data-equalto="password">
				  <small class="error">Senhas não conferem</small>
				</div>
	  		</div>
	  		<div class="large-3 medium-3 columns"><p></p></div>
	  	</div>
	  	<div class="row" align="center">
	  		<button>Alterar</button>
	  	</div>
	  	</form>
	  </div>
	</div>
