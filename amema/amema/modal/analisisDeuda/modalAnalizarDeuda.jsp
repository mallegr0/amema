<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="AnalisisDeuda" class="w3-modal">
	<div class="w3-modal-content w3-animate-zoom w3-card-4">
		<header class="w3-container w3-indigo">
			<span onclick="document.getElementById('AnalisisDeuda').style.display='none'" class="w3-button w3-display-topright">&times;</span>
			<h2 class="w3-center"><strong id="modalTitulo"></strong></h2>
		</header>
		<div class="w3-container">
			<%@ include file="../../forms/analisisDeuda/generarArchivoDeudaForm.jsp"%>
		</div>
	</div>
</div>