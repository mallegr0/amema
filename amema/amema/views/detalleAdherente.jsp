<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="entidades.VentasM" %>

<!DOCTYPE html>
<html>
<head>
	<title>AMEMA web - Detalle Garante</title>
	<%@ include file="../meta/metadata.jsp"%>
</head>
<body>

	/* AREA DEL MENU */
	<%@ include file="../views/menu.jsp"%>

	<div class="w3-container w3-card-4">
		<br>
		<br>
		<h3 class="w3-center w3-text-indigo">Detalle del movimiento seleccionado</h3>
			<table class="w3-table-all">
				<thead>
					<tr class="w3-indigo">
						<th>Fecha Movimiento</th>
						<th>Nro Comprobante</th>
						<th>Total</th>
						<th>A cuenta</th>
						<th>Adeudado</th>
					</tr>
				</thead>
				<tbody>
				<% 
				ArrayList<VentasM> lista = (ArrayList<VentasM>) request.getSession().getAttribute("detalle");	
				if(lista != null) {
					double total = 0.0;
					double tfavor = 0.0;
					double tdebe = 0.0;
					for(VentasM v: lista) {%>
					<tr>
						<td><%=v.getFMOV()%></td>
						<td><%=v.getNCOMP() %></td>
						<td><%=v.getSUBTOTAL() %></td>
						<td><%=v.getA_CUENTA() %></td>
						<td><%=v.getA_CUENTAD() %></td>
					</tr>
					<%
						total += v.getSUBTOTAL();
						tfavor += v.getA_CUENTA();
						tdebe += v.getA_CUENTAD();
					}%>
					<tr><td colspan="5"></td></tr>
					<tr>
						<td></td>
						<td class="w3-right">Totales: </td>
						<td><%=total %></td>
						<td><%=tfavor%></td>
						<td><%=tdebe%></td>
					</tr>
				<%}%>
				</tbody>
			</table>
			<br>
			<div class="w3-container w3-padding-8 w3-center">
				<br>
				<a href="/amema/views/adherentes.jsp" class="w3-button w3-green w3-hover-indigo"><strong>VOLVER</strong></a>
				<br><br>
			</div>
		</div>

		<% request.getSession().removeAttribute("detalle");%>
</body>
</html>