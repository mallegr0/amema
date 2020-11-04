<%@page import="entidades.VentasGral" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestión de Movimientos Fijos</title>
		<%@ include file="../../meta/metadata.jsp"%>
	</head>
	<body>
	<% VentasGral vg = (VentasGral) request.getSession().getAttribute("venta"); %>
		<%@include file="../../views/menu.jsp"%>

		<div class="w3-container w3-padding-64">
			<div class="w3-card-4">	
				<header class="w3-container w3-indigo">
					<h2 class="w3-center">Consulta de Movimiento Fijo</h2>
				</header>
				<form action="/amema/MovimientoFijo" method="post">
					<div class="w3-container w3-padding w3-card-4">
						<div class="w3-container w3-row w3-padding">
							<div class="w3-container w3-half w3-padding">
								<label><strong class="w3-text-indigo">Socio: </strong></label><input class="w3-input" type="text" name="socio" value="<%=vg.getCliente()%>" readonly>
							</div>
							<div class="w3-container w3-half w3-padding">
								<label><strong class="w3-text-indigo">Movimiento Nro: </strong></label><input class="w3-input" type="text" name="movimiento" value="<%=vg.getNromov()%>" readonly>
							</div>
						</div>
						<div class="w3-indigo w3-padding w3-center"><strong> - DATOS DEL MOVIMIENTO - </strong></div>
						<br>
						<div class="w3-container w3-padding">
							<div class="w3-container w3-col">
								<label><strong class="w3-text-indigo">Cod. Movimiento: </strong></label>
							</div>
							<div class="w3-container w3-rest">
								<input class="w3-input" type="text" name="articulo" value="<%=vg.getArticulo()%>" readonly>
							</div>
							<br>
							<div class="w3-container w3-padding w3-third">
								<div class="w3-container w3-padding">
									<label><strong class="w3-text-indigo">Cuotas: </strong></label>
								</div>
								<div class="w3-container w3-padding w3-rest">
									<input class="w3-input" type="text" id="cuotas" value="<%=vg.getCuotas()%>" readonly>
								</div>
							</div>
							<div class="w3-container w3-padding w3-third">
								<div class="w3-container w3-padding w3-col">
									<label><strong class="w3-text-indigo">Cantidad: </strong></label>
								</div>
								<div class="w3-container w3-padding w3-rest">
									<input class="w3-input" type="text" id="cantidad" value="<%=vg.getCantidad()%>" name="cantidad" readonly>
								</div>
							</div>
							<div class="w3-container w3-padding w3-third">
								<div class="w3-container w3-padding w3-col">
									<label><strong class="w3-text-indigo">Importe: $</strong></label>
								</div>
								<div class="w3-container w3-padding w3-rest">
									<input class="w3-input" type="text" name="importe" id="importe" value="<%=vg.getImporte()%>" readonly>
								</div>
							</div>
						</div>
						<div class="w3-container w3-padding">
							<div class="w3-container w3-half">
								<label><strong class="w3-text-indigo">Fecha Inicio: </strong></label><input class="w3-input" type="text" name="fecIni"  value="<%=vg.getFecDesde()%>" readonly>
							</div>
							<div class="w3-container w3-half">
								<label><strong class="w3-text-indigo">Fecha Fin: </strong></label><input class="w3-input" type="text" name="fecFin" value="<%=vg.getFecHasta()%>" readonly>
							</div>
						</div>
						<div class="w3-container w3-padding">
							<label><strong class="w3-text-indigo">Modo de Generacion: </strong></label>
							<input class="w3-input" type="text" name="modoGeneracion" value="<%=vg.getModoGeneracion() %>" readonly>
						</div>
						<div class="w3-container w3-padding">
							<label><strong class="w3-text-indigo">Referencia: </strong></label>
							<input class="w3-input" type="text" name="referencia" value="<%=vg.getReferencia() %>" readonly>
						</div>
						<div class="w3-container w3-padding">
							<div class="w3-container w3-padding w3-half">
								<label><strong class="w3-text-indigo">Prioridad de Cobro: </strong></label><input class="w3-input" type="text" name="prioridad" value="<%= vg.getAnalisis() %>" readonly></div>
							<div class="w3-container w3-padding w3-rest">
								<label><strong class="w3-text-indigo">Estado: </strong></label><input class="w3-input" type="text" name="estado" value="<%= vg.getEstado()%>" readonly></div>
						</div>
						<div class="w3-container w3-padding">
							<label><strong class="w3-text-indigo">Convenio: </strong></label>
							<input class="w3-input" type="text" name="convenio" value="<%=vg.getConvenio()%>" readonly>
						</div>
						<div class="w3-container w3-padding">
							<div class="w3-container w3-padding w3-third">
								<label><strong class="w3-text-indigo">Nro Cheque: </strong></label><input class="w3-input" type="text" name="nroCheque" value="<%=vg.getNroCheque()%>" readonly><label>(Para pago de ayuda económica)</label>	
							</div>
							<div class="w3-container w3-padding w3-third">
								<label><strong class="w3-text-indigo">Cheque Banco: </strong></label>
								<input class="w3-input" type="text" name="banco" value="<%=vg.getBancoCheque()%>" readonly> 
								<label>(Para pago de ayuda económica)</label>	
							</div>
							<div class="w3-container w3-padding w3-third">
								<label><strong class="w3-text-indigo">Importe Cheque: </strong></label><input class="w3-input" type="text" name="impCheque" value="<%=vg.getImpCheque()%>" readonly><label>(Para pago de ayuda económica)</label>	
							</div>
						</div>
						<div class="w3-container w3-padding">
							<div class="w3-container w3-padding w3-half">
								<label><strong class="w3-text-indigo">Cancela deuda Anterior: </strong></label>
								<input class="w3-input" type="text" name="cancelaDeuda" value="<%=vg.getCancelaDeuda()%>" readonly>
							</div>
							<div class="w3-container w3-padding w3-half">
								<label><strong class="w3-text-indigo">Importe por retención por Canc. Deuda Anterior</strong></label><input class="w3-input" type="text" name="impCancela" value="<%=vg.getImpCancelaDeuda()%>" readonly>
							</div>
							<fieldset>
								<legend class="w3-text-indigo"><strong>Observaciones</strong></legend>
								<div class="w3-container w3-padding">
									<textarea class="w3-textarea" rows="4" cols="50" id="observaciones" name="observaciones"><%=vg.getObservaciones()%></textarea>
								</div>
							</fieldset>
						</div>
						<br>
						<div class="w3-container w3-padding w3-half w3-center">
							<button class="w3-button w3-green w3-hover-indigo" name="evento_imprimir"><i class="fas fa-print fa-2x"></i></button>
						</div>
						<div class="w3-container w3-padding w3-half w3-center">
							<a href="/amema/views/movimientosfijos.jsp" class="w3-button w3-red w3-hover-indigo"><strong>Volver</strong></a>
						</div>
					</div>
				</form>
			</div>
		</div>

		<%@include file="../../views/footer.jsp"%>

		<script type="text/javascript">
			function muestraDatos() {
				
				// segun lo que selecciono en el combo me rellena la cantidad de cuotas y la cantidad de cuotas.
				var codart = document.getElementById("articulo").value;
				document.getElementById("cuotas").value = codart.substring(4);
				document.getElementById("cantidad").value = 1;
				
				// seteo las fecha de inicio y de fin del prestamo.
				//Fecha actual

				var fechaActual = new Date();
				var dia = fechaActual.getDate();
				var mes = fechaActual.getMonth()+1;
				var año = fechaActual.getFullYear();
				
				if(dia < 10){ dia = "0"+dia;}
				if(mes < 10){ mes = "0"+mes;}
				document.getElementById("fecIni").value = año+"-"+mes+"-"+dia;
				
				
				if(parseInt(codart.substring(0,2)) == 10){
					document.getElementById("importe").value = 9.5;
					document.getElementById("fecFin").value = "2050-12-31";
				}
				

				if(parseInt(codart.substring(0,2)) == 01){
					var c = parseInt(codart.substring(4));
					var fechaFin = new Date();
					fechaFin.setMonth(fechaFin.getMonth()+c);
					
					var d = fechaFin.getDate();
					var m = fechaFin.getMonth()+1;
					var a = fechaFin.getFullYear();
					
					if(d < 10){d = "0"+d;}
					if(m < 10){m = "0"+m;}
					document.getElementById("fecFin").value = a+"-"+m+"-"+d;
					
					
				}
			}

			function cargaObservaciones() {
				var texto = document.getElementById("textoFijo").value;
				if(texto != null){
					document.getElementById("observaciones").innerHTML = texto;
				}
			}

		</script>
	</body>
</html>