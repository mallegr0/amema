<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.VentasM"%>
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
			<% VentasM v = (VentasM) request.getSession().getAttribute("movimiento");
				if(v != null){%>
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
			<br>
			<div class="w3-container w3-center">
				<a href="ctactes.jsp" class="w3-button w3-green w3-hover-indigo"><strong>VOLVER</strong></a>
			</div>
			<%}%>
		</div>

		<% request.getSession().removeAttribute("movimiento"); %>

		<%@ include file="../views/footer.jsp"%>

	</body>
</html>