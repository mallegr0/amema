<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Genera Interés por Convenio</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		<%@ include file="menu.jsp"%>
		<div class="w3-container w3-padding-64">
			<!-- Mensajes de error -->
			<%@ include file="../errores/errGeneraInteres.jsp"%>

			<!-- Formulario de la generación de Intereses -->
			<%@ include file="../forms/convenioSocio/generaInteresConvenio.jsp"%>	

		</div>
		<!-- AREA DEL FOOTER -->
		<%@ include file="footer.jsp"%>
	</body>
</html>