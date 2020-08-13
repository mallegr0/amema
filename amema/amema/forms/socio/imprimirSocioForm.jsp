<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<form action="/amema/Socio" name="Socio" method="get">
		<div class="w3-container">
			<div class="w3-row w3-section">
				<div class="w3-col" style="width:35%"><p>Desea imprimir los datos del Socio: </p></div>
		    	<div class="w3-rest">
		    		<input id="printsocio" class="w3-input w3-border" name="idSocio" type="text" readonly>
		    	</div>
			</div>
			<div class="w3-row w3-section">
				<div class="w3-half w3-center">
					<button class="w3-button w3-green w3-hover-indigo" name="evento_imprimirPDF">Imprimir</button>
				</div>
				<div class="w3-rest w3-center">
					<a href="../views/socios.jsp" class="w3-button w3-red">Volver</a>
				</div>
			</div>
		</div>
	</form>