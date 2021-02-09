<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="/amema/Convenio" method="post">
	<div class="w3-container w3-padding">
		<div class="w3-container w3-padding w3-half">
			<label class="w3-text-indigo"><strong>Cód. Convenio: </strong></label>
			<input class="w3-input" type="text" name="codConvenio" required>
		</div>
		<div class="w3-container w3-padding w3-half">
			<label class="w3-text-indigo"><strong>Desc. Convenio: </strong></label>
			<input class="w3-input" type="text" name="descConvenio" required>
		</div>
	</div>
	<div class="w3-container w3-padding">
		<div class="w3-container w3-padding w3-half">
			<label class="w3-text-indigo"><strong>Concepto 1 (Cuota): </strong></label>
			<input class="w3-input" type="text" name="cpto1" required>
		</div>
		<div class="w3-container w3-padding w3-half">
			<label class="w3-text-indigo"><strong>Tope 1: $</strong></label>
			<input type="text" name="tope1" class="w3-input" required>
		</div>
	</div>
	<div class="w3-container w3-padding">
		<div class="w3-container w3-padding w3-half">
			<label class="w3-text-indigo"><strong>Concepto 2 (Varios): </strong></label>
			<input class="w3-input" type="text" name="cpto2" required>
		</div>
		<div class="w3-container w3-padding w3-half">
			<label class="w3-text-indigo"><strong>Tope 2: $</strong></label>
			<input class="w3-input" type="text" name="tope2" required>
		</div>
	</div>
	<div class="w3-container w3-padding">
		<div class="w3-container w3-padding w3-half">
			<label class="w3-text-indigo"><strong>Concepto 3: </strong></label>
			<input class="w3-input" type="text" name="cpto3" required>
		</div>
		<div class="w3-container w3-padding w3-half">
			<label class="w3-text-indigo"><strong>Tope 3: $</strong></label>
			<input class="w3-input" type="text" name="tope3" required>
		</div>
	</div>
	<div class="w3-container w3-padding">
		<div class="w3-container w3-padding w3-half">
			<label class="w3-text-indigo"><strong>Genera Interes?: </strong></label>
			<input class="w3-input" type="text" name="ginteres" required>
		</div>
		<div class="w3-container w3-padding w3-half">
			<label class="w3-text-indigo"><strong>Tasa Interes Mensual: </strong></label>
			<input class="w3-input" type="text" name="tasa" required>
		</div>
	</div>
	<div class="w3-container w3-padding">
		<div class="w3-container w3-padding w3-half">
			<label class="w3-text-indigo"><strong>Cód. Producto para generación de Intereses: </strong></label>
			<input class="w3-input" type="text" name="producto" required>
		</div>
		<div class="w3-container w3-padding w3-half">
			<label class="w3-text-indigo"><strong>Modo para ingreso de Información: </strong></label>
			<select class="w3-select" name="modo">
				<option value="1">Municipalidad</option>
				<option value="0">Jubilados</option>
			</select>
		</div>
	</div>
	<br>
	<div class="w3-container w3-padding">
		<div class="w3-container w3-padding w3-center w3-half">
			<button class="w3-button w3-green w3-hover-indigo" name="evento_altaConvenio">Agregar</button>
		</div>
		<div class="w3-container w3-padding w3-center w3-half">
			<a href="/amema/views/convenio.jsp" class="w3-button w3-red w3-hover-indigo">Volver</a>
		</div>
	</div>
</form>