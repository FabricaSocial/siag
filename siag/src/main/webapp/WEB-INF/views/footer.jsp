<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<script src="resources/js/vendor/jquery.js"></script>
    <script src="resources/js/foundation.min.js"></script>
    
    <script src="resources/highcharts/js/highcharts.js"></script>
	<script src="resources/highcharts/js/modules/exporting.js"></script>
    <script>
      $(document).foundation();
    </script>
    <script>
			$('#errorModal').foundation('reveal', 'open');
	</script>
	<c:if test="${invalidAuth == true}">
		<script>
			$('#invalidAuth').foundation('reveal', 'open');
		</script>
	</c:if>
	
	<c:if test="${unregistredUser == true}">
		<script>
			$('#unregistredUser').foundation('reveal', 'open');
		</script>
	</c:if>
	
	<c:if test="${updatePassword == true}">
		<script>
			$('#updatePassword').foundation('reveal', 'open');
		</script>
	</c:if>
  </body>
</html>
