<%@ page isELIgnored ="false" %> 

<!doctype html>
<html class="no-js" lang="en">
  	<head>
    	<meta charset="utf-8" />
    	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		
		<link rel="stylesheet" href="resources/css/foundation.css" />
    	<script src="resources/js/vendor/modernizr.js"></script>
    	
    	<link rel="stylesheet" type="text/css" href="resources/datepicker/jquery.datetimepicker.css"/>
		<script src="resources/datepicker/jquery.js"></script>
		<script src="resources/datepicker/jquery.datetimepicker.js"></script>
		<script src="resources/js/jquery.maskedinput.js"></script>
		<script src="resources/js/jquery.js"></script>
		
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
				<h1><a href="#">SIAG</a></h1>
			</li>
			<li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
		</ul>
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