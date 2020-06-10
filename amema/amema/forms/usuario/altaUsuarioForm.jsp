<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="../Usuario" name ="Usuario" method="post">
	<div class="w3-container">
		<div class="w3-row w3-section">
	  		<div class="w3-col" style="width:120px"><label>Usuario: </label></div>
	    	<div class="w3-rest"><input class="w3-input w3-border" name="usuario" type="text" required></div>
		</div>
		<div class="w3-row w3-section">
	  		<div class="w3-col" style="width:120px"><label>Password: </label></div>
	    	<div class="w3-rest"><input class="w3-input w3-border" name="password" type="password" min="6" maxlength="20" required></div>
		</div>
		<div class="w3-row w3-section">
	  		<div class="w3-col" style="width:120px"><label>Nombre y Apellido: </label></div>
	    	<div class="w3-rest"><input class="w3-input w3-border" name="nombreyapellido" type="text" required></div>
		</div>
		<div class="w3-row w3-section">
			<div class="w3-half w3-center"><button class="w3-button w3-green w3-round-large" name="evento_alta">Agregar</button></div>
			<div class="w3-rest w3-center"><a href="../views/usuarios.jsp" class="w3-button w3-red w3-round-large">Volver</a></div>
		</div>
	</div>
</form>