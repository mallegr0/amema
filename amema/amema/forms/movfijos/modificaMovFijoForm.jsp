<%@page import="java.text.SimpleDateFormat"%>
<%@page import="controladores.CtrlCliente"%>
<%@page import="controladores.CtrlLeyenda"%>
<%@page import="controladores.CtrlReferencia"%>
<%@page import="controladores.CtrlConvenio"%>
<%@page import="controladores.CtrlArticulo"%>
<%@page import="controladores.CtrlVenta" %>
<%@page import="entidades.Articulo" %>
<%@page import="entidades.Convenio" %>
<%@page import="entidades.Referencia" %>
<%@page import="entidades.Leyenda" %>
<%@page import="entidades.Cliente" %>
<%@page import="entidades.Venta" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	CtrlVenta cv = new CtrlVenta();
	int mov = Integer.parseInt(request.getParameter("id"));
	String codcli = cv.consultarClientePorNroMov(mov);
	Venta v  = cv.ConsultaVentaPorNroMov(mov);
	
	CtrlCliente cs = new CtrlCliente();
	Cliente cli = cs.consultaCliente(codcli);
	
	CtrlArticulo ca = new CtrlArticulo();
	ArrayList<Articulo> lArticulo = ca.listarArticulos();
	Articulo art = ca.consultarArticulo(v.getCODART().substring(0,2), v.getCODART().substring(2,4), v.getCODART().substring(4));
	String codigo = v.getCODART();
	
	CtrlConvenio cc = new CtrlConvenio();
	ArrayList<Convenio> lConvenio = cc.listarConvenio();
	
	CtrlReferencia cr = new CtrlReferencia();
	ArrayList<Referencia> lReferencia = cr.listarReferencias();
	Referencia ref = cr.consultaReferencia(v.getREFERENCIA());
	
	CtrlLeyenda cl = new CtrlLeyenda();
	ArrayList<Leyenda> lLeyenda = cl.listarLeyendas();
	
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	String valor;
	if(v.getINNCTACTE() == "T"){ valor = "Generar todas las cuotas";}
	else {valor = "Generar mensualmente las cuotas"; }
%>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestión de Movimientos Fijos</title>
		<%@ include file="../../meta/metadata.jsp"%>
	</head>
	<body>

		<%@include file="../../views/menu.jsp"%>

		<div class="w3-container w3-padding-64">
			<div class="w3-card-4">	
				<header class="w3-container w3-indigo">
					<h2 class="w3-center">Modificación de Movimiento Fijo</h2>
				</header>
				<form action="/amema/MovimientoFijo" method="post">
					<div class="w3-container w3-padding w3-card-4">
						<div class="w3-container w3-row w3-padding">
							<div class="w3-container w3-half w3-padding">
								<label><strong class="w3-text-indigo">Socio: </strong></label><input class="w3-input" type="text" name="socio" value="<%=cli.getCODCLI()%> - <%=cli.getNOMCLI()%>" readonly>
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
								<select class="w3-select" name="articulo" onchange="muestraDatos()" id="articulo" >
									
								<% for(Articulo a: lArticulo){%>
									<option value="<%=a.getCGRUPO()%><%=a.getCSUBF()%><%=a.getNROART()%>"><%=a.getDESART() %></option>
								<%} %>
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
									<input class="w3-input" type="text" id="cantidad" value="<%=v.getUNIDADES()%>" name="cantidad">
								</div>
							</div>
							<div class="w3-container w3-padding w3-third">
								<div class="w3-container w3-padding w3-col">
									<label><strong class="w3-text-indigo">Importe: $</strong></label>
								</div>
								<div class="w3-container w3-padding w3-rest">
									<input class="w3-input" type="text" name="importe" id="importe" value="<%=v.getPRECIO()%>">
								</div>
							</div>
						</div>
						<div class="w3-container w3-padding">
							<div class="w3-container w3-half">
								<label><strong class="w3-text-indigo">Fecha Inicio: </strong></label><input class="w3-input" type="date" name="fecIni" id="fecIni" value="<%=f.format(v.getFCOMP())%>">
							</div>
							<div class="w3-container w3-half">
								<label><strong class="w3-text-indigo">Fecha Fin: </strong></label><input class="w3-input" type="date" name="fecFin" id="fecFin" value="<%=f.format(v.getFVTO())%>"></div>
						</div>
						<div class="w3-container w3-padding">
							<label><strong class="w3-text-indigo">Modo de Generacion: </strong></label>
							<select class="w3-select" name="modoGeneracion" >
								<option value="<%=v.getINNCTACTE()%>"><%=valor %></option>
								<option value="T">Generar todas las cuotas</option>
								<option value="M">Generar mensualmente las cuotas</option>
							</select>
						</div>
						<div class="w3-container w3-padding">
							<label><strong class="w3-text-indigo">Referencia: </strong></label>
							<select class="w3-select" name="referencia" >
								<option value="<%=v.getREFERENCIA() %>"><%=ref.getCREF() %> - <%=ref.getNREF() %></option>
							<%for(Referencia r: lReferencia){ %>
								<option value="<%=r.getCREF()%>"><%=r.getCREF()%> - <%=r.getNREF()%></option>
							<%} %>
							</select>
						</div>
						<div class="w3-container w3-padding">
							<div class="w3-container w3-padding w3-half">
								<label><strong class="w3-text-indigo">Prioridad de Cobro: </strong></label><input class="w3-input" type="text" name="prioridad" value="<%=v.getANALISIS() %>" ></div>
							<div class="w3-container w3-padding w3-rest">
								<label><strong class="w3-text-indigo">Estado: </strong></label><input class="w3-input" type="text" name="estado" value="<%=v.getVA_DTO()%>"></div>
						</div>
						<div class="w3-container w3-padding">
							<label><strong class="w3-text-indigo">Convenio: </strong></label>
							<select class="w3-select" name="convenio" >
							<%for(Convenio conv: lConvenio){ %>
								<option value="<%=conv.getCCOND()%>"><%=conv.getCCOND()%> - <%=conv.getDESCOND()%></option>
							<%} %>
							</select>
						</div>
						<div class="w3-container w3-padding">
							<div class="w3-container w3-padding w3-third">
								<label><strong class="w3-text-indigo">Nro Cheque: </strong></label><input class="w3-input" type="text" name="nroCheque" value="<%=v.getUBICAC1()%>"><label>(Para pago de ayuda económica)</label>	
							</div>
							<div class="w3-container w3-padding w3-third">
								<label><strong class="w3-text-indigo">Cheque Banco: </strong></label>
								<select class="w3-select" name="banco">
									<option value="<%=v.getUBICAC2()%>"><%=v.getUBICAC2()%></option>
									<option value="-">Ningun dato</option>
									<option value="-">cualquier cosa</option>
								</select>
								<label>(Para pago de ayuda económica)</label>	
							</div>
							<div class="w3-container w3-padding w3-third">
								<label><strong class="w3-text-indigo">Importe Cheque: </strong></label><input class="w3-input" type="text" name="impCheque" value="<%=v.getIMPCH()%>"><label>(Para pago de ayuda económica)</label>	
							</div>
						</div>
						<div class="w3-container w3-padding">
							<div class="w3-container w3-padding w3-half">
								<label><strong class="w3-text-indigo">Cancela deuda Anterior: </strong></label>
								<select class="w3-select" name="cancelaDeuda">
									<option value="<%=v.getCANCDEUANT()%>"><%=v.getCANCDEUANT()%></option>
									<option value="S">SI</option>
									<option value="N">NO</option>
								</select>
							</div>
							<div class="w3-container w3-padding w3-half">
								<label><strong class="w3-text-indigo">Importe por retención por Canc. Deuda Anterior</strong></label><input class="w3-input" type="text" name="impCancela" value="<%=v.getIMPCANCDEUANT()%>">
							</div>
							<fieldset>
								<legend class="w3-text-indigo"><strong>Observaciones</strong></legend>
								<div class="w3-container w3-padding">
									<textarea class="w3-textarea" rows="4" cols="50" id="observaciones" name="observaciones"><%=v.getTEXTLIB()%></textarea>
								</div>
								<div class="w3-container w3-padding">
									<label><strong class="w3-text-indigo">Textos Fijos: </strong></label>
									<select class="w3-select" id="textoFijo" onchange="cargaObservaciones()" >
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
							<button class="w3-button w3-green w3-hover-indigo" name="evento_modifica"><strong>Confirmar Modificación</strong></button>
						</div>
						<div class="w3-container w3-padding w3-half w3-center">
							<a href="/amema/views/movimientosfijos.jsp" class="w3-button w3-red w3-hover-indigo"><strong>Volver</strong></a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<%
			cv = null;
			ca = null;
			cc = null;
			cr = null;
			cl = null;
			cs = null;
		%>

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