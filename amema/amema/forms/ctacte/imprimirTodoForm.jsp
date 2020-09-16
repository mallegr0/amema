<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<form action="/amema/Cuenta" method="get">
		<div class="w3-container">
			<div class="w3-row w3-section">
				<p>Desea imprimir todos los movimientos del Socio:<input class="w3-input" type="text" id="migrarExcel" name="migrarExcel" readonly> </p>
			</div>
			<div class="w3-row w3-section">
				<div class="w3-half w3-center">
					<button class="w3-button w3-green w3-hover-indigo" name="evento_todoPDF">Imprimir</button>
				</div>
				<div class="w3-rest w3-center">
					<a href="/amema/views/ctactes.jsp" class="w3-button w3-red">Volver</a>
				</div>
			</div>
		</div>
	</form>