<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Cliente" %>
<%@page import="entidades.AdherentesGral"%>
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestion de Adherentes</title>
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
					Cliente c = (Cliente) request.getSession().getAttribute("socio");
					if(c != null){	
				%>
				<br>
				<div class="w3-container w3-half">
					<p><label class="w3-text-indigo"><strong>Apellido y Nombre: </strong></label><input type="text" class="w3-input" value="<%=c.getNOMCLI()%>"></p>
					<p><label class="w3-text-indigo"><strong>Dirección: </strong></label><input type="text" class="w3-input" value="<%=c.getDOMCLI()%>"></p>
					<p><label class="w3-text-indigo"><strong>Telefono/s: </strong></label><input type="text" class="w3-input" value="<%=c.getTELCLI_1()%> - <%=c.getTELCLI_2()%>"></p>
					<p><label class="w3-text-indigo"><strong>Convenio: </strong></label><input type="text" class="w3-input" value="<%=c.getCCOND()%>"></p>
				</div>
				<div class="w3-container w3-half">
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
							<th>Nro MC</th>
							<th>Fecha Desde</th>
							<th>Fecha Hasta</th>
							<th>Prioridad</th>
							<th>Cód. Movimiento</th>
							<th>Referencia</th>
							<th>Desc. Movimiento</th>
							<th>Importe</th>
							<th>Cuotas</th>
							<th>Cantidad Mensual</th>
							<th>Modo</th>
							<th>Estado</th>
							<th colspan="2" class="w3-center">Acciones</th>
						</tr>
					</thead>
					<tbody>
					<%
						ArrayList<AdherentesGral> lista = (ArrayList<AdherentesGral>) request.getSession().getAttribute("movimientos");
						if(lista != null){
							for(AdherentesGral a: lista){%>
							<tr>
								<td><%=a.getNROMC() %></td>
								<td><%=a.getFDESDE() %></td>
								<td><%=a.getFHASTA() %></td>
								<td><%=a.getPRIOR() %></td>
								<td><%=a.getCODMOV() %></td>
								<td><%=a.getREF() %></td>
								<td><%=a.getDESMOV() %></td>
								<td><%=a.getIMPORTE() %></td>
								<td><%=a.getCUOTAS() %></td>
								<td><%=a.getCANTMENS() %></td>
								<td><%=a.getMODO() %></td>
								<td><%=a.getESTADO() %></td>
								<td><button class="w3-button w3-green w3-hover-indigo" onclick="abrirModalAdherente('id01','<%=a.getNROMC()%>')"><i class="fas fa-search fa-2x"></i></button></td>
								<td><button class="w3-button w3-orange w3-hover-indigo w3-text-white"><i class="fas fa-print fa-2x"></i></button></td>
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
			
			<!-- AREA DE MODAL DE VISTA -->

			<%@include file="../modal/adherente/modalVistaAdherente.jsp"%>

			<!-- FIN AREA DE MODAL DE VISTA -->

				<%}%>
		</div>

		<!-- FIN AREA DE TRABAJO -->

		<%@ include file="footer.jsp" %>
		<script type="text/javascript">
			function abrirModalAdherente(modal, codigo){
				document.getElementById("id01").style.display='block';
				document.getElementById("nro").innerHTML = codigo;
			}

		</script>
	</body>
</html>