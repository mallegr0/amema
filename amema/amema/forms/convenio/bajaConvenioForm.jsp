<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="/amema/Convenio" method="post">
	<div class="w3-container w3-padding">
		<label class="w3-text-indigo"><strong>Desea modificar el Convenio: </strong></label>
		<input class="w3-input" type="text" name="codConvenio" id="deleteConvenio" readonly>
	</div>
		
	<br>
	<div class="w3-container w3-padding">
		<div class="w3-container w3-padding w3-center w3-half">
			<button class="w3-button w3-red w3-hover-indigo" name="evento_bajaConvenio">Eliminar</button>
		</div>
		<div class="w3-container w3-padding w3-center w3-half">
			<a href="/amema/views/convenio.jsp" class="w3-button w3-green w3-hover-indigo">Volver</a>
		</div>
	</div>
</form>