<form action="/amema/PeriodoDeudaGenerado" method="post">
		<div class="w3-container">
			<div class="w3-row w3-section">
				<div class="w3-half"><p class="w3-text-indigo">Desea mostrar el detalle de la deuda Generada Nro: </p></div>
		    	<div class="w3-rest">
		    		<input id="periodo" class="w3-input w3-border" name="nroDeuda" type="text" readonly>
		    	</div>
			</div>
			<div class="w3-row w3-section">
				<div class="w3-half w3-center">
					<button class="w3-button w3-green w3-hover-indigo" name="evento_buscar">Consultar</button>
				</div>
				<div class="w3-rest w3-center">
					<a href="/amema/views/periodosDeuda.jsp" class="w3-button w3-red">Volver</a>
				</div>
			</div>
		</div>
	</form>



