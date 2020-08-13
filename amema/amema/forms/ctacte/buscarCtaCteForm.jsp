<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<h4 class="w3-center w3-text-indigo"> Búsqueda de Socio</h4>
	<form action="/amema/Cuenta" method="post">
		<div class="w3-container">
			<div class="w3-container w3-quarter">
				<p><input class="w3-check" type="checkbox" name="socio" id="socio" onclick="habilitar('socio')"><label> Buscar por nombre y apellido</label></p>
				<p><input class="w3-check" type="checkbox" name="doc" id="doc" onclick="habilitar('doc')"><label> Buscar por Nro de Documento</label></p>
			</div>
			<div class="w3-half w3-container">
				<br>
				<input class="w3-input" type="text" name="dato" id="input" disabled>
			</div>
			<div class="w3-container w3-quarter">
				<br>
				<button class="fas fa-search fa-2x w3-button w3-green w3-hover-indigo" name="evento_buscar1"></button>
			</div>
		</div>	
	</form>