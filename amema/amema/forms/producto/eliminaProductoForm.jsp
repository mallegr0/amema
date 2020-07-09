<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<form action="/amema/Producto" name="Producto" method="post">
		<div class="w3-container">
			<div class="w3-row w3-section">
				<div class="w3-col" style="width:25%"><p>Desea eliminar el Producto: </p></div>
		    	<div class="w3-rest">
		    		<input id="deleteprod" class="w3-input w3-border" name="codigo" type="text" readonly="readonly">
		    	</div>
			</div>
			<div class="w3-row w3-section">
				<div class="w3-half w3-center">
					<button class="w3-button w3-green w3-round-large" name="evento_eliminar">Eliminar</button>
				</div>
				<div class="w3-rest w3-center">
					<a href="../views/productos.jsp" class="w3-button w3-red w3-round-large">Volver</a>
				</div>
			</div>
		</div>
	</form>