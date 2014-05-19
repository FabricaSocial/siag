<script>
$( "#datepicker" ).change(function() {
	var date = $('#datepicker').val();
	
	var url = 'getTime/' + date;
	
	$.ajax({
		url: url,
		dataType: 'json',
		success: function(data){
			var html = "";
			
			$.each(data, function(i,data){
				var div_data = "<option value=" + data.idTime + ">" + data.time + "</option>";
				
				$('#idDay').val(data.idDate);
				html = html + div_data;
			});
			if(html == ""){
				html = "<option DISABLED>Sem horários disponíveis.</option>";
			}
			$('#hourSelect').html(html);
		}
	});
});
</script>

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