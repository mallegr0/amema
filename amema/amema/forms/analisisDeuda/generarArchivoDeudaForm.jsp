<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="w3-container w3-padding">
	<form action="/amema/AnalisisDeuda" method="get">
		<div class="w3-container">
			<label class="w3-text-indigo">Desea generar el archivo para el socio: </label><input id="cliente" type="text" name="cliente" class="w3-input" readonly> 
		</div>
		<div class="w3-container w3-padding">
			<div class="w3-container w3-padding w3-half w3-center">
				<button class="w3-button w3-green w3-hover-indigo" id="btnEvento"><strong>Generar</strong></button>
			</div>
			<div class="w3-container w3-padding w3-half w3-center">
				<a href="/amema/views/analisisDeuda.jsp" class="w3-button w3-red w3-hover-indigo"><strong>Volver</strong></a>
			</div>
		</div>
	</form>
</div>