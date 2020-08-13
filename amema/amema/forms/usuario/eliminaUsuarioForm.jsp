<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<form action="/amema/Usuario" method="post">
		<div class="w3-container">
			<div class="w3-row w3-section">
				<div class="w3-col" style="width:25%"><p>Desea eliminar al usuario: </p></div>
		    	<div class="w3-rest">
		    		<input id="deleteuser" class="w3-input w3-border" name="usuario" type="text" readonly="readonly">
		    	</div>
			</div>
			<div class="w3-row w3-section">
				<div class="w3-half w3-center">
					<button class="w3-button w3-green w3-hover-indigo" name="evento_eliminar">Eliminar</button>
				</div>
				<div class="w3-rest w3-center">
					<a href="../views/usuarios.jsp" class="w3-button w3-red">Volver</a>
				</div>
			</div>
		</div>
	</form>