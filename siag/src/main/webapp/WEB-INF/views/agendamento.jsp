<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>

	<div class="row">
		<div align="center">
			<h2>Agendamento</h2>
			<hr/>
		</div>
	</div>

	<div class="row">
		<div class="large-1 columns">
			<p></p>
		</div>
		<div class="large-5 columns">
			<ul class="pricing-table">
				<li class="title"><b>${candidate.nome}</b></li>
				<li class="bullet-item"><b>CPF: </b> ${candidate.cpf}</li>
				<li class="bullet-item"><b>Programa:</b> ${candidate.programa}</li>
				<li class="bullet-item">${candidate.necessidadeEspecial }</li>
				<li class="description">Inscrição em: ${candidate.dataInscricao}</li>
			</ul>
		</div>
		<div class="large-1 columns">
			<p></p>
		</div>
		<div class="large-4 columns">
			<br>
			<form>
				<div class="row collapse">
					<div class="small-3 large-2 columns">
						<span class="prefix">Dia</span>
					</div>
					<div class="small-9 large-10 columns">
						<input type="text" placeholder="Selecione um dia" readonly id="datepicker" name="day">
					</div>
			  	</div>
			  	
			  	<div class="row collapse">
					<div class="small-3 large-2 columns">
						<span class="prefix">Hora</span>
					</div>
					<div class="small-9 large-10 columns">
						<input type="text" placeholder="Selecione o horário" readonly id="timepicker" name="hour">
					</div>
			  	</div>
			  	
			  	<div class="row">
				  	<div class="large-2 columns">
				  		<p></p>
				  	</div>
				  	<div class="large-8 columns">
				  		<p><b>Vagas Disponíveis:</b> 20</p>
				  	</div>
				  	<div class="large-2 columns">
				  		<p></p>
				  	</div>
			  	</div>
			  	
			  	<div class="row">
			  		<div class="large-3 columns"><p></p></div>
			  		<div class="large-6 columns">
			  			<button id="agendar" class="postfix radius">Agendar</button>
			  		</div>
			  		<div class="large-3 columns"><p></p></div>
			  	</div>
			</form>
		</div>
		<div class="large-1 columns">
			<p></p>
		</div>
	</div>

<script>

jQuery('#datepicker').datetimepicker({
	lang:'pt',
	 i18n:{
	  pt:{
	   months:[
	    'Janeiro','Fevereiro','Março','Abril',
	    'Maio','Junho','Julho','Agosto',
	    'Setembro','Outubro','Novembro','Dezembro',
	   ],
	   dayOfWeek:[
	    "Dom", "Seg", "Ter", "Qua", 
	    "Qui", "Sex", "Sáb",
	   ]
	  }
	 },
	 
	  onGenerate:function( ct ){
	    jQuery(this).find('.xdsoft_date')
	      .toggleClass('xdsoft_disabled');
	  },
	  onGenerate:function( ct ){
	    jQuery(this).find('.xdsoft_date.xdsoft_weekend')
	      .addClass('xdsoft_disabled');
	  },
	  weekends:['01.01.2014','02.01.2014','03.01.2014','04.01.2014','05.01.2014','06.01.2014'],
	  closeOnDateSelect: true,
	  minDate:'2014/06/10',
	  maxDate:'2014/06/18',
	  startDate:'2014/06/10',
	  beforeShowDay: '2014/06/12',
	  timepicker:false,
	  format: 'Y/m/d',
	});	

jQuery('#timepicker').datetimepicker({
	 datepicker:false,
	 allowTimes:[
	  '09:00', '10:00', '11:00', 
	  '14:00', '15:00', '16:00',
	 ],
	 format:'H:i',
	});
</script>

<jsp:include page="footer.jsp"></jsp:include>