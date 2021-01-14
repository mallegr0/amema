<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="modalPeriodo" class="w3-modal">
	<div class="w3-modal-content w3-animate-zoom w3-card-4">
	   	<header class="w3-container w3-indigo"> 
	       	<span onclick="document.getElementById('modalPeriodo').style.display='none'" class="w3-button w3-display-topright">&times;</span>
	       	<h2 class="w3-center"> Desea ver el detalle de la deuda? </h2>
	   	</header>
	      	
	   	<%@include file="../../forms/periodos/periodosDeudaForm.jsp" %>
	</div>
</div>