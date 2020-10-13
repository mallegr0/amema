<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Cliente"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestion de Cuentas Corrientes</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		<%@ include file="../views/menu.jsp" %>

		<div class="w3-container w3-padding-64 w3-card-4">
			<br>
			<% ArrayList<Cliente> clientes = (ArrayList<Cliente>) request.getSession().getAttribute("garantes");
				if(clientes != null){%>
			<h3 class="w3-center  w3-text-indigo"> Detalle del movimiento: <%
				if(request.getSession().getAttribute("comprobante") != null){%><%=request.getSession().getAttribute("comprobante")%><%} else {%>S/Nro<%}%></h3>
			<br>
			<div class="w3-container w3-card-4 w3-responsive w3-padding">
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo">
							<td>Nro Socio</td>
							<td>Apellido y Nombre</td>
							<td>Domicilio</td>
							<td>Cod. Postal</td>
							<td>Localidad</td>
							<td>Telefonos</td>
						</tr>
					</thead>
					<tbody>
						<%for(Cliente c: clientes){%>
						<tr class="w3-hover-light-blue">
							<td><%= c.getCODCLI()%></td>
							<td><%= c.getNOMCLI() %></td>
							<td><%= c.getDOMCLI() %></td>
							<td><%= c.getCODPOS() %></td>
							<td><%= c.getLOCCLI() %></td>
							<td><%= c.getTELCLI_1() %> - <%= c.getTELCLI_2() %></td>
						</tr>
						<%}%>
					</tbody>
				</table>
			</div>
			<br>
			<%}
			else{%>
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo">
							<td>Nro Socio</td>
							<td>Apellido y Nombre</td>
							<td>Domicilio</td>
							<td>Cod. Postal</td>
							<td>Localidad</td>
							<td>Telefonos</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="6"><h2 class="w3-text-indigo w3-center"><strong>No hay datos para mostrar</strong></h2></td>
						</tr>
					</tbody>
				</table>
			<%}%>
			<br>
			<br>
			<div class="w3-container w3-center">
				<a href="ctactes.jsp" class="w3-button w3-green w3-hover-indigo"><strong>VOLVER</strong></a>
			</div>
		</div>

		<% request.getSession().removeAttribute("movimiento"); %>

		<%@ include file="../views/footer.jsp"%>

	</body>
</html>