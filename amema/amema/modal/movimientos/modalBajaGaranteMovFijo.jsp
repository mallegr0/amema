<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<div id="id01" class="w3-modal">
		<div class="w3-modal-content w3-animate-zoom w3-card-4">
		   	<header class="w3-container w3-indigo"> 
		       	<span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
	        	<h2 class="w3-center"> Baja del garante del Movimiento Fijo </h2>
	      	</header>
	      	
	      	<form action="/amema/MovimientoFijo" method="post">
	      		<div class="w3-container w3-padding">
	      			<label><strong class="w3-text-indigo">Desea eliminar al garante: </strong></label><input type="text" name="socio" class="w3-input" id="codgarante">
	      		</div>
	      		<br>
	      		<div class="w3-container w3-padding">
	      			<div class="w3-container w3-half w3-center">
	      				<button class="w3-button w3-green w3-hover-indigo" name="evento_eliminaGarante"><strong>ELIMINAR</strong></button>
	      			</div>
	      			<div class="w3-container w3-half w3-center">
	      				<a href="../../forms/movfijos/ABCGarantesForm.jsp" class="w3-button w3-red w3-hover-indigo"><strong>VOLVER</strong></a>
	      			</div>
	      		</div>

	      	</form>
		</div>
	</div>