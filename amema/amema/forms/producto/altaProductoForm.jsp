<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="/amema/Producto" name ="Producto" method="post">
	<div class="w3-container">
		<div class="w3-row w3-section">
	  		<div class="w3-col" style="width:120px"><label class="w3-text-indigo"><strong>Código: </strong></label></div>
	    	<div class="w3-rest"><input class="w3-input w3-border" name="codigo" type="text" required></div>
		</div>
		<div class="w3-row w3-section">
	  		<div class="w3-col" style="width:120px"><label class="w3-text-indigo"><strong>Denominación: </strong></label></div>
	    	<div class="w3-rest"><input class="w3-input w3-border" name="nombre" type="text" required></div>
		</div>
		<div class="w3-row w3-section">
	  		<div class="w3-col" style="width:120px"><label class="w3-text-indigo"><strong>Bonificación: </strong></label></div>
	    	<div class="w3-rest"><input class="w3-input w3-border" name="bonificacion" type="text" required></div>
		</div>
		<div class="w3-row w3-section">
			<div class="w3-half w3-center"><button class="w3-button w3-green" name="evento_alta">Agregar</button></div>
			<div class="w3-rest w3-center"><a href="../views/productos.jsp" class="w3-button w3-red">Volver</a></div>
		</div>
	</div>
</form>