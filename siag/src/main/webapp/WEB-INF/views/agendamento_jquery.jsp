<script>
$( "#datepicker" ).change(function() {
	var date = $('#datepicker').val();
	
	var url = 'getTime/' + date;
	
	$.ajax({
		url: url,
		dataType: 'json',
		success: function(data){
			//var html = "<fieldset ><legend>Horários Disponíveis</legend><div>";
			var html = "<center><h6>Horários Disponíveis</h6></center><hr/>";
			var div_data = ""
			
			$.each(data, function(i,data){
				div_data = "<input type=\"radio\" name=\"hour\" value=\"" + data.idTime + "\"required><label>" + data.time + "</label>";
				
				$('#idDay').val(data.idDate);
				
				var div = "<div class=\"large-6 columns\" align=\"center\">";
				
				html = html + div + div_data + "</div>";
			});
			
			if(div_data == ""){
				html = html + "<p><i>Não existem mais horários disponíveis para esse dia...</i></p>";
				$('#agendar').attr('disabled', 'disabled');
			} else {
				$('#agendar').removeAttr('disabled');
			}
			
			html = html + "</div>";
			//html = html + "</div></fieldset>";
			$('#hourSelect').html(html);
		}
	});
});
</script>

<!-- 
<script>
$( "#hourSelect" ).change(function() {
	var date = $('#idDay').val();
	var hour = $('#hourSelect').val();
	
	var url = 'getVacancy/' + date + '/' + hour;
	
	$.ajax({
		url: url,
		dataType: 'text',
		success: function(data){
			$('#vacancy').val(data);
		}
	});
});
</script>
 -->