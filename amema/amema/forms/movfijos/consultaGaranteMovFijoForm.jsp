<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="/amema/MovimientoFijo" method="post">
	<div class="w3-container">
		<div class="w3-row w3-section">
			<div class="w3-col" style="width:25%"><p>Desea consultar los garantes: </p></div>
		    <div class="w3-rest">
		    	<input id="consultagarantemov" class="w3-input w3-border" name="codigo" type="text" readonly>
		    </div>
		</div>
		<div class="w3-row w3-section">
			<div class="w3-half w3-center">
				<button class="w3-button w3-green" name="evento_consultaGarante">Consultar</button>
			</div>
			<div class="w3-rest w3-center">
				<a href="/amema/views/movimientosfijos.jsp" class="w3-button w3-red">Volver</a>
			</div>
		</div>
	</div>
</form>