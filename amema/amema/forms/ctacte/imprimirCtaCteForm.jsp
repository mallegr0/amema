<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<form action="/amema/Cuenta" method="get">
		<div class="w3-container">
			<div class="w3-row w3-section">
				<div class="w3-col" style="width:35%"><p>Desea imprimir el detalle del Comprobante: </p></div>
		    	<div class="w3-rest">
		    		<input id="printadherente" class="w3-input w3-border" name="printadherente" type="text" readonly>
		    	</div>
			</div>
			<div class="w3-row w3-section">
				<div class="w3-half w3-center">
					<button class="w3-button w3-green w3-hover-indigo" name="evento_imprimirPDF">Imprimir</button>
				</div>
				<div class="w3-rest w3-center">
					<a href="/amema/views/ctactes.jsp" class="w3-button w3-red">Volver</a>
				</div>
			</div>
		</div>
	</form>