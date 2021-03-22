<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="entidades.MovimientoDetalleGral" %>
<%@page import="java.text.DecimalFormat"%>

<!DOCTYPE html>
<html>
<head>
	<title>AMEMA web - Cancelar Movim. Por Número de Movim. Fijo</title>
	<%@ include file="../meta/metadata.jsp"%>
</head>
<body>
	<!-- AREA DEL MENU -->
	<%@ include file="menu.jsp" %>

	<div class="w3-container w3-padding-64">
		<%@ include file="../forms/movfijos/EliminaMovFijoForm.jsp"%>
		<br>
		<%@ include file="../../errores/errMovimiento.jsp"%> 
		<div class="w3-container w3-padding w3-card-4">
			<h3 class="w3-center"><strong class="w3-text-indigo">Anulación de Movimiento por Nro. Planificado</strong></h3>
			<br>
			<table class="w3-table w3-bordered">
				<thead>
					<tr class="w3-indigo w3-center">
						<td>Nro. Movimiento</td>
						<td>Fecha</td>
						<td>Descripción</td>
						<td>Imp. Total</td>
						<td>Imp. pagado</td>
						<td>Acción</td>
					</tr>
				</thead>
				<tbody>
					<% MovimientoDetalleGral mov = (MovimientoDetalleGral) request.getSession().getAttribute("detalle");
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					DecimalFormat df = new DecimalFormat("#0.00");
					if(mov != null){%>
					<tr>
						<td><%=mov.getNroMov() %></td>
						<td><%=sdf.format(mov.getFecMov()) %></td>
						<td><%=mov.getDescMov() %></td>
						<td><%=df.format(mov.getSaldoDebe()) %></td>
						<td><%=df.format(mov.getSaldoPago()) %></td>
						<td><button class="w3-button w3-red w3-hover-indigo" onclick="muestraModal('id01', '<%=mov.getNroMov() %>')"><i class="fas fa-minus fa-lg"></i></button></td>
						</tr>
					<%} %>
				</tbody>
			</table>
		</div>
	</div>

	<%@include file="../modal/movimientos/modalEliminaMovFijo.jsp"%>

	<%@ include file="footer.jsp"%>
	<script type="text/javascript">
		function muestraModal(modal, codigo){
			document.getElementById(modal).style.display='block';
			document.getElementById("movEliminar").value = codigo;
		}
	</script>
	
	<% request.getSession().removeAttribute("detalle"); %>
</body>
</html>