<%@page import="controladores.CtrlLeyenda"%>
<%@page import="controladores.CtrlReferencia"%>
<%@page import="controladores.CtrlConvenio"%>
<%@page import="controladores.CtrlArticulo"%>
<%@page import="controladores.CtrlVenta" %>
<%@page import="entidades.Articulo" %>
<%@page import="entidades.Convenio" %>
<%@page import="entidades.Referencia" %>
<%@page import="entidades.Leyenda" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	CtrlVenta cv = new CtrlVenta();
	int mov = cv.ultimoID();
	
	CtrlArticulo ca = new CtrlArticulo();
	ArrayList<Articulo> lArticulo = ca.listarArticulos();
	
	CtrlConvenio cc = new CtrlConvenio();
	ArrayList<Convenio> lConvenio = cc.listarConvenio();
	
	CtrlReferencia cr = new CtrlReferencia();
	ArrayList<Referencia> lReferencia = cr.listarReferencias();
	
	CtrlLeyenda cl = new CtrlLeyenda();
	ArrayList<Leyenda> lLeyenda = cl.listarLeyendas();
%>


<form action="/amema/MovimientoFijo" method="post">
		<div class="w3-container w3-padding w3-card-4">
			<div class="w3-container w3-row w3-padding">
				<div class="w3-container w3-half w3-padding">
					<label><strong class="w3-text-indigo">Socio: </strong></label><input class="w3-input" type="text" name="socio" id="socio" readonly>
				</div>
				<div class="w3-container w3-half w3-padding">
					<label><strong class="w3-text-indigo">Movimiento Nro: </strong></label><input class="w3-input"type="text" name="movimiento" value="<%=mov%>" readonly>
				</div>
			</div>
			<div class="w3-indigo w3-padding w3-center"><strong> - DATOS DEL MOVIMIENTO - </strong></div>
			<br>
			<div class="w3-container w3-padding">
				<div class="w3-container w3-col">
					<label><strong class="w3-text-indigo">Cod. Movimiento: </strong></label>
				</div>
				<div class="w3-container w3-rest">
					<select class="w3-select" name="articulo" onchange="muestraDatos()" id="articulo" required>
					<% for(Articulo a: lArticulo){%>
						<option value="<%=a.getCGRUPO()%><%=a.getCSUBF()%><%=a.getNROART()%>"><%=a.getDESART()%></option>
					<%}%>
					</select>
				</div>
				<br>
				<div class="w3-container w3-padding w3-third">
					<div class="w3-container w3-padding">
						<label><strong class="w3-text-indigo">Cuotas: </strong></label>
					</div>
					<div class="w3-container w3-padding w3-rest">
						<input class="w3-input" type="text" id="cuotas" readonly>
					</div>
				</div>
				<div class="w3-container w3-padding w3-third">
					<div class="w3-container w3-padding w3-col">
						<label><strong class="w3-text-indigo">Cantidad: </strong></label>
					</div>
					<div class="w3-container w3-padding w3-rest">
						<input class="w3-input" type="text" id="cantidad" name="cantidad">
					</div>
				</div>
				<div class="w3-container w3-padding w3-third">
					<div class="w3-container w3-padding w3-col">
						<label><strong class="w3-text-indigo">Importe: $</strong></label>
					</div>
					<div class="w3-container w3-padding w3-rest">
						<input class="w3-input" type="text" name="importe" id="importe" required>
					</div>
				</div>
			</div>
			<div class="w3-container w3-padding">
				<div class="w3-container w3-half">
					<label><strong class="w3-text-indigo">Fecha Inicio: </strong></label><input class="w3-input" type="date" name="fecIni" id="fecIni">
				</div>
				<div class="w3-container w3-half">
					<label><strong class="w3-text-indigo">Fecha Fin: </strong></label><input class="w3-input" type="date" name="fecFin" id="fecFin"></div>
			</div>
			<div class="w3-container w3-padding">
				<label><strong class="w3-text-indigo">Modo de Generacion: </strong></label>
				<select class="w3-select" name="modoGeneracion" required>
					<option value="T">Generar todas las cuotas</option>
					<option value="M">Generar mensualmente las cuotas</option>
				</select>
			</div>
			<div class="w3-container w3-padding">
				<label><strong class="w3-text-indigo">Referencia: </strong></label>
				<select class="w3-select" name="referencia" required>
				<%for(Referencia r: lReferencia){ %>
					<option value="<%=r.getCREF()%>"><%=r.getCREF()%> - <%=r.getNREF()%></option>
				<%} %>
				</select>
			</div>
			<div class="w3-container w3-padding">
				<div class="w3-container w3-padding w3-half">
					<label><strong class="w3-text-indigo">Prioridad de Cobro: </strong></label><input class="w3-input" type="text" name="prioridad" required></div>
				<div class="w3-container w3-padding w3-rest">
					<label><strong class="w3-text-indigo">Estado: </strong></label><input class="w3-input" type="text" name="estado" value="A"></div>
			</div>
			<div class="w3-container w3-padding">
				<label><strong class="w3-text-indigo">Convenio: </strong></label>
				<select class="w3-select" name="convenio" required>
				<%for(Convenio conv: lConvenio){ %>
					<option value="<%=conv.getCCOND()%>"><%=conv.getCCOND()%> - <%=conv.getDESCOND()%></option>
				<%} %>
				</select>
			</div>
			<div class="w3-container w3-padding">
				<div class="w3-container w3-padding w3-third">
					<label><strong class="w3-text-indigo">Nro Cheque: </strong></label><input class="w3-input" type="text" name="nroCheque" value="-"><label>(Para pago de ayuda económica)</label>	
				</div>
				<div class="w3-container w3-padding w3-third">
					<label><strong class="w3-text-indigo">Cheque Banco: </strong></label>
					<select class="w3-select" name="banco">
						<option value="-">Ningun dato</option>
						<option value="-">cualquier cosa</option>
					</select>
					<label>(Para pago de ayuda económica)</label>	
				</div>
				<div class="w3-container w3-padding w3-third">
					<label><strong class="w3-text-indigo">Importe Cheque: </strong></label><input class="w3-input" type="text" name="impCheque" value="0"><label>(Para pago de ayuda económica)</label>	
				</div>
			</div>
			<div class="w3-container w3-padding">
				<div class="w3-container w3-padding w3-half">
					<label><strong class="w3-text-indigo">Cancela deuda Anterior: </strong></label>
					<select class="w3-select" name="cancelaDeuda">
						<option value="S">SI</option>
						<option value="N">NO</option>
					</select>
				</div>
				<div class="w3-container w3-padding w3-half">
					<label><strong class="w3-text-indigo">Importe por retención por Canc. Deuda Anterior</strong></label><input class="w3-input" type="text" name="impCancela" value="0">
				</div>
				<fieldset>
					<legend class="w3-text-indigo"><strong>Observaciones</strong></legend>
					<div class="w3-container w3-padding">
						<textarea class="w3-textarea" rows="4" cols="50" id="observaciones" name="observaciones"></textarea>
					</div>
					<div class="w3-container w3-padding">
						<label><strong class="w3-text-indigo">Textos Fijos: </strong></label>
						<select class="w3-select" id="textoFijo" onchange="cargaObservaciones()" required>
							<option value="">...</option>
						<%for(Leyenda l: lLeyenda){ %>
							<option value="<%=l.getTLEY()%>"><%=l.getNLEY()%></option>
						<%} %>
						</select>
					</div>
				</fieldset>
			</div>
			<br>
			<div class="w3-container w3-padding w3-half w3-center">
				<button class="w3-button w3-green w3-hover-indigo" name="evento_alta"><strong>Confirmar Movimiento</strong></button>
			</div>
			<div class="w3-container w3-padding w3-half w3-center">
				<a href="/amema/views/movimientosfijos.jsp" class="w3-button w3-red w3-hover-indigo"><strong>Volver</strong></a>
			</div>
		</div>
</form>


<%
	cv = null;
	ca = null;
	cc = null;
	cr = null;
	cl = null;
%>

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