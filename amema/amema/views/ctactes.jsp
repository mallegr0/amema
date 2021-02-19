<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Cliente" %>
<%@page import="entidades.VentasM" %>
<%@page import="entidades.CtacteGral"%>
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestion de Cuentas Corrientes</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<%@ include file="../views/menu.jsp"%>
		
		<!-- AREA DE TRABAJO -->
		<div class="w3-container w3-padding-64">
			<br>
			<div class="w3-container w3-card-4">
				<h3 class="w3-center w3-text-indigo">Datos del Socio</h3>
				<% 
					Cliente c = (Cliente) request.getSession().getAttribute("persona");
					if(c != null){	
				%>
				<br>
				<div class="w3-container w3-half">
					<p><label class="w3-text-indigo"><strong>Nro Socio: </strong></label><input type="text" class="w3-input" value="<%=c.getCODCLI()%>"></p>
					<p><label class="w3-text-indigo"><strong>Apellido y Nombre: </strong></label><input type="text" class="w3-input" value="<%=c.getNOMCLI()%>"></p>
					<p><label class="w3-text-indigo"><strong>Dirección: </strong></label><input type="text" class="w3-input" value="<%=c.getDOMCLI()%>"></p>
					<p><label class="w3-text-indigo"><strong>Telefono/s: </strong></label><input type="text" class="w3-input" value="<%=c.getTELCLI_1()%> - <%=c.getTELCLI_2()%>"></p>
					<p><label class="w3-text-indigo"><strong>DNI: </strong></label><input type="text" class="w3-input" value="<%=c.getTIPO_DOC()%>-<%=c.getCUITCLI()%>"></p>
				</div>
				<div class="w3-container w3-half">
					<p><label class="w3-text-indigo"><strong>Convenio: </strong></label><input type="text" class="w3-input" value="<%=c.getCCOND()%>"></p>
					<p><label class="w3-text-indigo"><strong>Empresa: </strong></label><input type="text" class="w3-input" value="<%=c.getCPCCP()%>"></p>
					<p><label class="w3-text-indigo"><strong>Legajo: </strong></label><input type="text" class="w3-input" value="<%=c.getDNRP()%>"></p>
					<p><label class="w3-text-indigo"><strong>Lugar de Trabajo: </strong></label><input type="text" class="w3-input" value="<%=c.getCONTACTO()%> - <%=c.getCONTACTO2()%>"></p>
					<p><label class="w3-text-indigo"><strong>Observaciones: </strong></label><input type="text" class="w3-input" value="<%=c.getOBSCLI()%>"></p>
				</div>
				<br>
			</div>
			<br><br>
			<% VentasM v = (VentasM) request.getSession().getAttribute("movimiento"); 
			if(v == null){%>
				<div class="w3-container w3-card-4 w3-responsive">
					<br>
					<table class="w3-table w3-bordered">
						<thead>
							<tr class="w3-indigo">
								<th>Fecha</th>
								<th>Concepto</th>
								<th>Nro Movimiento</th>
								<th>Debe</th>
								<th>haber</th>
								<th>Saldo</th>
								<th colspan="3" class="w3-center">Acciones</th>
							</tr>
						</thead>
						<tbody>
						<%
							DecimalFormat df = new DecimalFormat("#0.00");
							ArrayList<CtacteGral> lista = (ArrayList<CtacteGral>) request.getSession().getAttribute("movimientos");
							if(lista != null){
								for(CtacteGral a: lista){%>
								<tr class="w3-hover-light-blue">
									<td><%=a.getFMOV()%></td>
									<td><%=a.getTMOV() %></td>
									<td><%=a.getNCOMP() %></td>
									<td><%=df.format(a.getDEBE()) %></td>
									<td><%=df.format(a.getHABER()) %></td>
									<td><%=df.format(a.getSALDO())%></td>
									<td>
										<form action="/amema/Cuenta" method="post">
											<button class="w3-button w3-green w3-hover-indigo" name="evento_detalle" value="<%=a.getNCOMP()%>"><i class="fas fa-search fa-2x"></i></button>
										</form>
									</td>
									<td><button class="w3-button w3-orange w3-hover-indigo w3-text-white" onclick="abrirModalCta('id02','<%=a.getNCOMP()%>')"><i class="fas fa-print fa-2x"></i></button></td>
									<td><button class="w3-button w3-purple w3-hover-indigo" onclick="abrirModalCta('id04','<%=a.getNCOMP()%>')"><i class="fas fa-users fa-2x"></i></button></td>
								</tr>
								<%}%>
							<%}
							else{%>
								<tr>
									<td colspan="14"><h3 class="w3-center  w3-text-indigo">No hay movimientos para el socio seleccionado.</h3></td>
								</tr>
							<%}%>
						</tbody>
					</table>
					<br>
				</div>
			<%}
			else {%>
				<div class="w3-container w3-padding w3-twothird">
					<div class="w3-container w3-card-4 w3-responsive">
						<br>
						<table class="w3-table w3-bordered">
							<thead>
								<tr class="w3-indigo">
									<th>Fecha</th>
									<th>Concepto</th>
									<th>Nro Movimiento</th>
									<th>Debe</th>
									<th>haber</th>
									<th>Saldo</th>
									<th colspan="3" class="w3-center">Acciones</th>
								</tr>
							</thead>
							<tbody>
							<%
							DecimalFormat df = new DecimalFormat("#0.00");
							ArrayList<CtacteGral> lista = (ArrayList<CtacteGral>) request.getSession().getAttribute("movimientos");
							if(lista != null){
								for(CtacteGral a: lista){%>
								<tr class="w3-hover-light-blue">
									<td><%=a.getFMOV()%></td>
									<td><%=a.getTMOV() %></td>
									<td><%=a.getNCOMP() %></td>
									<td><%=df.format(a.getDEBE()) %></td>
									<td><%=df.format(a.getHABER()) %></td>
									<td><%=df.format(a.getSALDO())%></td>
									<td>
										<form action="/amema/Cuenta" method="post">
											<button class="w3-button w3-green w3-hover-indigo" name="evento_detalle" value="<%=a.getNCOMP()%>"><i class="fas fa-search fa-2x"></i></button>
										</form>
									</td>
									<td><button class="w3-button w3-orange w3-hover-indigo w3-text-white" onclick="abrirModalCta('id02','<%=a.getNCOMP()%>')"><i class="fas fa-print fa-2x"></i></button></td>
									<td><button class="w3-button w3-purple w3-hover-indigo" onclick="abrirModalCta('id04','<%=a.getNCOMP()%>')"><i class="fas fa-users fa-2x"></i></button></td>
								</tr>
							<%}%>
							</tbody>
						</table>
						<br>
					</div>
				</div>
				<div class="w3-container w3-rest w3-responsive">
					<h3 class="w3-center  w3-text-indigo"> Detalle del movimiento <%=v.getNCOMP()%></h3>
					<br> 
					<div class="w3-container w3-card-4">
						<div class="w3-half">
							<p style="width: 80%"><label class="w3-text-indigo"><strong>Origen del Movimiento: </strong></label><input class="w3-input" type="text" value="<%=v.getNCOMP()%>" readonly></p>
							<p style="width: 80%"><label class="w3-text-indigo"><strong>Cantidad de Cuotas: </strong></label><input class="w3-input" type="text" value="<%=v.getOBSERV()%>" readonly></p>
							<p style="width: 80%"><label class="w3-text-indigo"><strong>Referencia: </strong></label><input class="w3-input" type="text" value="<%=v.getREFERENCIA()%>" readonly></p>
							<p style="width: 80%"><label class="w3-text-indigo"><strong>Nro Planificación: </strong></label><input class="w3-input" type="text" value="<%=v.getNROMOVPLANIF()%> (Nro Actualiz: <%=v.getNROACTUALIZ()%>)" readonly></p>
						</div>
						<div class="w3-half">
							<p style="width: 80%"><label class="w3-text-indigo"><strong>Fec. Movimiento: </strong></label><input class="w3-input" type="date" value="<%=v.getFMOV()%>" readonly></p>
							<p style="width: 80%"><label class="w3-text-indigo"><strong>Pagado?: </strong></label><input class="w3-input" type="text" value="<%=v.getPAGADO()%>" readonly></p>
							<p style="width: 80%"><label class="w3-text-indigo"><strong>Anulado?: </strong></label><input class="w3-input" type="text" value="<%=v.getANULADO()%>" readonly></p>
							<p style="width: 80%"><label class="w3-text-indigo"><strong>Observaciones: </strong></label><textarea class="w3-input" rows="6" cols="60" readonly><%=v.getTEXTLIB()%></textarea></p>
						</div>
					</div>
				</div>
				<%}}} %>
			<br><br>
			<div class="w3-container w3-card-4 w3-padding">
				<div class="w3-container w3-third w3-center">
					<a href="buscactactes.jsp" class="w3-button w3-green w3-hover-indigo"><strong>VOLVER</strong></a>
				</div>
				<div class="w3-container w3-third w3-center">
					<button class="w3-button w3-orange w3-hover-indigo w3-text-white" onclick="abrirModalCta('id03', '<%=c.getCODCLI()%>')"><strong>TRANSF. A EXCEL</strong></button>
				</div>
				<div class="w3-container w3-third w3-center">
					<button class="w3-button w3-blue w3-hover-indigo w3-text-white" onclick="abrirModalCta('id05', '<%=c.getCODCLI()%>')"><strong>RECONSTRUIR</strong></button>
				</div>
			</div>
			<br><br>

			<!-- AREA DE MODAL DE IMPRESION DE MOVIMIENTO-->

			<%@include file="../modal/ctacte/modalImprimircta.jsp"%>

			<!-- FIN AREA DE MODAL DE IMPRESION DE MOVIMIENTO-->

			<!-- AREA DE MODAL DE GARANTES DEL MOVIMIENTO-->

			<%@include file="../modal/ctacte/modalGarantesCtacte.jsp"%>

			<!-- FIN AREA DE MODAL DE GARANTES DEL MOVIMIENTO-->


			<!-- AREA DE MODAL DE IMPRESION TODO-->

			<%@include file="../modal/ctacte/modalImprimirTodo.jsp"%>

			<!-- FIN AREA DE MODAL DE IMPRESION TODO-->

			<!-- AREA DE MODAL DE RECONSTRUIR -->

			<%@include file="../modal/ctacte/modalReconstruir.jsp"%>

			<!-- FIN AREA DE MODAL DE RECONSTRUIR -->


<%request.getSession().removeAttribute("movimiento"); %>
		</div>

		<!-- FIN AREA DE TRABAJO -->

		<%@ include file="footer.jsp" %>
		<script type="text/javascript">
			function abrirModalCta(modal, codigo){
				document.getElementById(modal).style.display='block';
				if(modal == 'id02'){
					document.getElementById("printadherente").value = codigo;
				}
				if(modal == 'id03'){
					document.getElementById("migrarExcel").value = codigo;
				}
				if(modal == 'id04'){
					document.getElementById("nrog").innerHTML = codigo;
					document.getElementById("datog").value = codigo;
				}
				if(modal ==  'id05'){
					document.getElementById("socioCta").value = codigo;
				}
			}	

		</script>
	</body>
</html>