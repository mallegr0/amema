<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<div id="id04" class="w3-modal">
		<div class="w3-modal-content w3-animate-zoom w3-card-4">
		   	<header class="w3-container w3-indigo"> 
		       	<span onclick="document.getElementById('id04').style.display='none'" class="w3-button w3-display-topright">&times;</span>
	        	<h2 class="w3-center"> Consulta los Garantes del Movimiento Fijo </h2>
	      	</header>
	      	
	      	<%@include file="../../forms/movfijos/consultaGaranteMovFijoForm.jsp" %>
		</div>
	</div>