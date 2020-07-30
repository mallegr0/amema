<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="/amema/Producto" name ="Producto" method="post">
	<div class="w3-container">
		<div class="w3-row w3-section">
	  		<div class="w3-col" style="width:120px"><label>Código: </label></div>
	    	<div class="w3-rest"><input class="w3-input w3-border" name="codigo" type="text" required></div>
		</div>
		<div class="w3-row w3-section">
	  		<div class="w3-col" style="width:120px"><label>Denominación: </label></div>
	    	<div class="w3-rest"><input class="w3-input w3-border" name="nombre" type="text" required></div>
		</div>
		<div class="w3-row w3-section">
	  		<div class="w3-col" style="width:120px"><label>Bonificación: </label></div>
	    	<div class="w3-rest"><input class="w3-input w3-border" name="bonificacion" type="text" required></div>
		</div>
		<div class="w3-row w3-section">
			<div class="w3-half w3-center"><button class="w3-button w3-green w3-round-large" name="evento_alta">Agregar</button></div>
			<div class="w3-rest w3-center"><a href="../views/productos.jsp" class="w3-button w3-red w3-round-large">Volver</a></div>
		</div>
	</div>
</form>