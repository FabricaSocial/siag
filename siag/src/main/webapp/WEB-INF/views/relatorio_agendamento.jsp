<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<hr/><h4>Agendamentos</h4><hr/>

<table>
  	<thead>
    	<tr>
    		<th width="130">DATA</th>
      		<th width="130">HORA</th>
      		<th width="150">CPF</th>
      		<th width="350">NOME</th>
    		<th width="150">AGENDADO POR</th>
		</tr>
  	</thead>
  	<tbody>
  		<c:forEach items="${schedulingReports}" var="scheduling">
	    	<tr>
	    		<td><c:out value="${scheduling.getDate()}"></c:out></td>
	      		<td><c:out value="${scheduling.getHour()}"></c:out></td>
	      		<td><c:out value="${scheduling.getCandidateCpf()}"></c:out></td>
	      		<td><c:out value="${scheduling.getCandidate()}"></c:out></td>
	      		<td><c:out value="${scheduling.getUser()}"></c:out></td>
	    	</tr>
		</c:forEach>
  	</tbody>
</table>