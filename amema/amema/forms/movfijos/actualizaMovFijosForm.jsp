<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="/amema/MovimientoFijo" method="post">
	<div class="w3-container w3-padding w3-card-4">
		<br>
		<h3 class="w3-center w3-text-indigo">Generar los movimientos planificados en Cuentas Corrientes de Socios</h3>
		<br>

		<%@include file="../../errores/errMovimiento.jsp"%>
		
		<div class="w3-container w3-padding w3-half">
			<div class="w3-container w3-padding w3-quarter">
				<label><strong class="w3-text-indigo">Entre el: </strong></label>
			</div>
			<div class="e3-container w3-padding w3-rest">
				<input type="Date" name="fecIni" class="w3-input" required>
			</div>
		</div>
		<div class="w3-container w3-padding w3-half">
			<div class="w3-container w3-padding w3-quarter">
				<label><strong class="w3-text-indigo"> y el: </strong></label>
			</div>
			<div class="e3-container w3-padding w3-rest">
				<input type="Date" name="fecFin" class="w3-input" required>
			</div>
		</div>
		<br>
		<br>
		<div class="w3-container w3-padding">
			<div class="w3-container w3-padding w3-third">
				<label><strong class="w3-text-indigo">Tipos de Movimientos a Actualizar: </strong></label>
			</div>
			<div class="w3-container w3-padding w3-rest">
				<select class="w3-select" name="modo">
					<option value="M">Mensuales</option>
					<option value="T">Todas las cuotas</option>
					<option value="A">Ambos</option>
				</select>
			</div>
		</div>
		<div class="w3-container w3-padding">
			<div class="w3-container w3-padding w3-half w3-center">
				<button class="w3-button w3-green w3-hover-indigo" name="evento_actualizar"><strong>Actualizar</strong></button>
			</div>
			<div class="w3-container w3-padding w3-half w3-center">
				<a href="/amema/views/ppal.jsp" class="w3-button w3-red w3-hover-indigo"><strong>Volver</strong></a>
			</div>
		</div>
	</div>
</form>