<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Generación de Txt con descuentos</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		<%@ include file="menu.jsp"%>
		<div class="w3-container w3-padding-64">
			<h3 class="w3-center  w3-text-indigo">Generación de Archivos</h3>
			<!-- Mensajes de error -->
			<%@ include file="../errores/errGeneraTXT.jsp"%>
			<!-- Formulario de generacion de TXT -->
			<%@ include file="../forms/actualizacionesPagos/generaTxtForm.jsp"%>
		</div>

		<!-- Modal para el spiner -->
		<div id="spinner" class="w3-modal">
			<div class="w3-modal-content w3-animate-zoom w3-card-4">
				<header class="w3-container w3-indigo"> 
					<span onclick="document.getElementById('spinner').style.display='none'" class="w3-button w3-display-topright">&times;</span>
				    <h2 class="w3-center"> Actualizando la Base de Datos y Generando los archivos TXT. Aguarde un momento!! </h2>
			    </header>
				<div class="loader"></div>
			</div>
		</div>

		<!-- AREA DEL FOOTER -->
		<%@ include file="footer.jsp"%>
		<script type="text/javascript">
			function abrospinner(){
				document.getElementById('spinner').style.display='block'; 
			}

		</script>
	</body>
</html>