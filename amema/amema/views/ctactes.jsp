<%@page import="java.text.DecimalFormat"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Cliente" %>
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
			<div class="w3-container w3-card-4 w3-responsive">
				<br>
				<table class="w3-table-all">
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
							<tr>
								<td><%=a.getFMOV() %></td>
								<td><%=a.getTMOV() %></td>
								<td><%=a.getNCOMP() %></td>
								<td><%=df.format(a.getDEBE()) %></td>
								<td><%=df.format(a.getHABER()) %></td>
								<td><%=df.format(a.getSALDO())%></td>
								<td><button class="w3-button w3-green w3-hover-indigo" onclick="abrirModalCta('id01','<%=a.getNCOMP()%>')" name="evento_detalle"><i class="fas fa-search fa-2x"></i></button></td>
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
			<br><br>
			<div class="w3-container w3-card-4 w3-padding">
				<div class="w3-container w3-half w3-center">
					<a href="buscactactes.jsp" class="w3-button w3-green w3-hover-indigo"><strong>VOLVER</strong></a>
				</div>
				<div class="w3-container w3-half w3-center">
					<button class="w3-button w3-orange w3-hover-indigo w3-text-white" onclick="abrirModalCta('id03', '<%=c.getCODCLI()%>')"><strong>TRANSF. A EXCEL</strong></button>
				</div>
			</div>
			<br><br>
			
			<!-- AREA DE MODAL DE VISTA -->

			<%@include file="../modal/ctacte/modalVistaCtacte.jsp"%>

			<!-- FIN AREA DE MODAL DE VISTA -->

			<!-- AREA DE MODAL DE IMPRESION DE MOVIMIENTO-->

			<%@include file="../modal/ctacte/modalImprimircta.jsp"%>

			<!-- FIN AREA DE MODAL DE IMPRESION DE MOVIMIENTO-->

			<!-- AREA DE MODAL DE GARANTES DEL MOVIMIENTO-->

			<%@include file="../modal/ctacte/modalGarantesCtacte.jsp"%>

			<!-- FIN AREA DE MODAL DE GARANTES DEL MOVIMIENTO-->


			<!-- AREA DE MODAL DE IMPRESION TODO-->

			<%@include file="../modal/ctacte/modalImprimirTodo.jsp"%>

			<!-- FIN AREA DE MODAL DE IMPRESION TODO-->

				<%}%>
		</div>

		<!-- FIN AREA DE TRABAJO -->

		<%@ include file="footer.jsp" %>
		<script type="text/javascript">
			function abrirModalCta(modal, codigo){
				document.getElementById(modal).style.display='block';
				if(modal == 'id01'){
					document.getElementById("nro").innerHTML = codigo;
					document.getElementById("dato").value = codigo;
				}
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
			}	

		</script>
	</body>
</html>