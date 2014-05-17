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
	  startDate:'2014-06-10',
	  beforeShowDay: '2014/06/12',
	  timepicker:false,
	  format: 'Y-m-d',
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