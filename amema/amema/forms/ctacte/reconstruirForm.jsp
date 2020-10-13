<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<form action="/amema/Cuenta" method="post">
		<div class="w3-container">
			<div class="w3-row w3-section">
				<p>Desea reconstruir los movimientos de las Cuentas Corrientes del Socio: <input type="text" name="socio" id="socioCta"></p>
			</div>
			<div class="w3-row w3-section">
				<div class="w3-half w3-center">
					<button class="w3-button w3-green w3-hover-indigo" name="evento_reconstruir">Reconstruir</button>
				</div>
				<div class="w3-rest w3-center">
					<a href="/amema/views/ctactes.jsp" class="w3-button w3-red">Volver</a>
				</div>
			</div>
		</div>
	</form>