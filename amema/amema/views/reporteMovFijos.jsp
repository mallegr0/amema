<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Reportes Movimientos Fijos por periodos</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		<%@ include file="menu.jsp"%>
		<div class="w3-container w3-padding-64">
			<div class="w3-container w3-padding w3-card-4">
				<h3 class="w3-center w3-text-indigo"><strong>Selección de párametros para el Reporte</strong></h3>
				<br>
				<%@ include file="../forms/movfijos/reporteMovFijoForm.jsp"%>
			</div>
		</div>

		<!-- AREA DEL FOOTER -->
		<%@ include file="footer.jsp"%>
	</body>
</html>