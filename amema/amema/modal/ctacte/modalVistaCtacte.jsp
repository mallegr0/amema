<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="id01" class="w3-modal">
	<div class="w3-modal-content w3-animate-zoom w3-card-4">
		<header class="w3-container w3-indigo"> 
			<span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
			<h2 class="w3-center"> Detalle de los garantes del movimiento <label id="nro"></label></h2>
		</header>
		<div class="w3-container w3-card-4">
			<br>
		
			<%@include file="../../forms/ctacte/vistaCtaCteForm.jsp"%>
			
			<div class="w3-container w3-padding-8 w3-center">
				<br>
				<a href="/amema/views/ctactes.jsp" class="w3-button w3-red w3-hover-indigo"><strong>VOLVER</strong></a>
				<br><br>
			</div>
		</div>
	</div>
</div>