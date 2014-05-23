<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript">
$(function () {
        $('#agendamento').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Candidatos Agendados Por Dia e Horário'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: [
					<c:forEach var="date" items="${dates}">
						'<c:out value="${date}"/>',
					</c:forEach>
                ]
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Quantidade de Agendamentos'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.0f}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [
			<c:forEach var="time" items="${schedulingReports}">
				{
					name: '<c:out value="${time.getTime()}"/>',
					data: [
					<c:forEach var="vacancy" items="${time.getVacancies()}">
						<c:out value="${vacancy}"/>,
					</c:forEach>
					]
				},
			</c:forEach>   
            ]
        });
    });
</script>