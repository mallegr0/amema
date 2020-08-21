<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Cliente" %>
<%@page import="entidades.AdherentesGral"%>
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>AMEMA web - Gestion de Adherentes</title>
	<%@ include file="../meta/metadata.jsp"%>

</head>
<body>
	<!-- AREA DEL MENU-->
	<%@ include file="../views/menu.jsp" %>

	<div class="w3-container w3-padding-64 w3-card-4">
		<br>
		<h2 class="w3-center  w3-text-indigo">Consulta de Adherentes</h2>
		<div class="w3-container w3-card-4">
			<%@ include file="/forms/adherente/buscarAdherenteForm.jsp"%>
		</div>
		<br>
		<%if(request.getSession().getAttribute("lista") != null) {%>
			<div class="w3-container w3-card-4">
				<br>
				<%@ include file="/forms/adherente/seleccionarSAdherenteForm.jsp"%>
			</div>
		<%}
		else {%> 
			<div class="w3-container w3-card">
				<h1 class="w3-center  w3-text-indigo">No hay datos para mostrar</h1>
			</div>
		<%}%>
			<% request.getSession().removeAttribute("lista"); %>

</div>
	<%@ include file="footer.jsp" %>

	
</body>
</html>