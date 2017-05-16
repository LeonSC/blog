<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="header.jsp"%>
<body>
	<div class="container-fluid">
		<%@ include file="nav.jsp"%>
		<div class="row">
			<div class="col col-12">
			<canvas id="infoChart" width="90%" height="20"></canvas>
			<script>
			/**
			var data = {
				    labels: ["January", "February", "March", "April", "May", "June", "July"],
				    datasets: [
				        {
				            label: "七天内数据",
				            fill: false,
				            data: [65, 59, 80, 81, 56, 55, 40]
				        }
				    ]
				};
			**/
			var data = ${data};
			var ctx = document.getElementById("infoChart");
			var myLineChart = Chart.Line(ctx, {
			    data: data
			});
			</script>
			</div>
		</div>
	</div>
</body>
</html>