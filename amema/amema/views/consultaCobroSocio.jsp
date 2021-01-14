<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Consulta cobro a Socio</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body onload="habilitaBtn()">
		<% 
			String hayData = (String) request.getSession().getAttribute("hayData");
		%>
		<!-- AREA DEL MENU -->
		<%@ include file="menu.jsp"%>
		<div class="w3-container w3-padding-64">
			<!-- formulario de busqueda de cobro -->
			<%if(hayData == null){%> <%@ include file="../forms/socio/buscaSocioForm.jsp"%>
			<%}
			else {%> 
			<br>
			<%@ include file="../forms/pagosSocio/consultaCobroSocioForm.jsp"%> <%}%>
			<br>
			<%@ include file="../forms/pagosSocio/opcionesPagosForm.jsp"%>
		</div>
		<!-- AREA DEL FOOTER -->
		<%@ include file="footer.jsp"%>
		<script>
			function habilitaBtn(){
				if(document.getElementById("recibo").value != "S/D"){
					document.getElementById("btn1").disabled = false;
					document.getElementById("btn2").disabled = false;
					document.getElementById("btn3").disabled = false;
				}
			}
		
		</script>
		<% request.getSession().removeAttribute("hayData"); %>
	</body>
</html>