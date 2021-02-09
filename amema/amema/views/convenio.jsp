<%@page import="java.util.ArrayList"%>
<%@page import="controladores.CtrlConvenio" %>
<%@page import="entidades.Convenio" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Trabajar con Convenios</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	
	<%
		CtrlConvenio cConvenio = new CtrlConvenio();
		ArrayList<Convenio> convenios = cConvenio.listarConvenio();
	%>
	<body>
		<!-- AREA DEL MENU -->
		<%@ include file="menu.jsp"%>
		<div class="w3-container w3-padding-64">
			<div class="w3-container w3-padding w3-card-4 w3-center">
				<h3 class="w3-center w3-text-indigo"><strong>Alta de Convenio</strong></h3>
				<br>
				<button class="w3-jumbo fas fa-plus-circle w3-button w3-hover-indigo w3-text-green w3-circle w3-border-0" onclick="abroModalConvenio('altaConvenio', '')"></button>
			</div>
			<br>
			<%@ include file="../errores/errConvenio.jsp"%>
			<br>
			<h3 class="w3-center w3-text-indigo"><strong>Trabajar con Convenios</strong></h3>
			<br>
			<div class="w3-card-4">
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo w3-center">
							<td>Código</td>
							<td>Descripción</td>
							<td>Concepto 1</td>
							<td>Concepto 2</td>
							<td>Concepto 3</td>
							<td colspan="2" class="w3-center">Acciones</td>
						</tr>
					</thead>
					<tbody>
						<%if(convenios != null) {
							for(Convenio convenio: convenios){ %>
						<tr>
							<td><%=convenio.getCCOND() %></td>
							<td><%=convenio.getDESCOND() %></td>
							<td><%=convenio.getConc1() %></td>
							<td><%=convenio.getConc2() %></td>
							<td><%=convenio.getConc3() %></td>
							<td><a href="/amema/forms/convenio/modificaConvenioForm.jsp?id=<%=convenio.getCCOND() %>" class="w3-button w3-green w3-hover-indigo"><i class="fas fa-edit"></i></a></td>
							<td><button class="w3-button w3-red w3-hover-indigo" onclick="abroModalConvenio('bajaConvenio','<%=convenio.getCCOND() %>')"><i class="fas fa-times"></i></button></td>
						</tr>
							<%}
						}
						else {%>
						<tr class="w3-text-indigo w3-center" colspan="7">
							<td><strong>No hay convenios para mostrar</strong></td>
						</tr>
						<%} %>
					</tbody>
				</table>
			</div>
		
			<!-- AREA DE LOS MODALES -->
			<%@include file="../modal/convenio/modalAltaConvenio.jsp" %>
			<%@include file="../modal/convenio/modalBajaConvenio.jsp" %>
		
		</div>

		<!-- AREA DEL FOOTER -->
		<%@ include file="footer.jsp"%>

		<script>
			function abroModalConvenio(id, nro){
				document.getElementById(id).style.display= 'block' ;

				if(id == "bajaConvenio"){
					document.getElementById('deleteConvenio').value = nro;
				}
			}
		</script>
	</body>
</html>