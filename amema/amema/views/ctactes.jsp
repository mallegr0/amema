<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="entidades.Ctactecliente" %>

<!DOCTYPE html>
<html>
<head>
	<title>AMEMA web - Cuentas Corrientes</title>
	<%@ include file="../meta/metadata.jsp"%>
</head>
<body>
	<%-- AREA DEL MENU --%>
	<%@include file="../views/menu.jsp"%>
	
	<div class="w3-container w3-padding-64 w3-card-4">
		<br>
		<h2 class="w3-center  w3-text-indigo">Consulta de Cuentas Corrientes</h2>
		<div class="w3-container w3-card-4">
			<%@include file="/forms/ctacte/buscarCtaCteForm.jsp" %>
		</div>
		<br>
		<% 
			if(request.getSession().getAttribute("doc") == null && request.getSession().getAttribute("socios") == null){%>
			<h1 class="w3-center"> No hay datos que mostrar</h1><%}
			else {
				if(request.getSession().getAttribute("socios") != null) {
				%>
				<div class="w3-container w3-card-4">
					<br>
					<%@include file="/forms/ctacte/seleccionarSCtaCteForm.jsp" %>
				</div>
			<%}
				else {
					Cliente c = (Cliente) request.getSession().getAttribute("doc");
				%> 
					<div class="w3-container w3-card-4">
						<h3 class="w3-center w3-text-indigo"> Datos del Socio</h3>
						<br>
						<div class="w3-container w3-half">
							<p><label>Apellido y Nombre: </label><input class="w3-input" value="<%=c.getNOMCLI()%>"></p>
							<p><label>Dirección: </label><input class="w3-input" value="<%=c.getDOMCLI()%>"></p>
							<p><label>Telefonos: </label><input class="w3-input" value="<%=c.getTELCLI_1()%> - <%=c.getTELCLI_2()%>"></p>
							<p><label>Convenio: </label><input class="w3-input" value="<%=c.getCCOND()%>"></p>
						</div>
						<div class="w3-container w3-half">
							<p><label>Empresa: </label><input class="w3-input" value="<%=c.getCPCCP()%>"></p>
							<p><label>Legajo: </label><input class="w3-input" value="<%=c.getDNRP()%>"></p>
							<p><label>Lugar de trabajo: </label><input class="w3-input" value="<%=c.getCONTACTO()%> - <%=c.getCONTACTO2()%>"></p>
							<p><label>Observaciones: </label><input class="w3-input" value="<%=c.getOBSCLI()%>"></p>
						</div>
					</div>
					<br>
					<h4 class="w3-center w3-text-indigo"> Seleccione la fecha a listar</h4>
					<div class="w3-container w3-card-4">
						<form action="/amema/Cuenta" method="post">
							<div class="w3-container w3-third w3-hide">
								<input type="text" name="socio" value="<%=c.getCODCLI()%>">
							</div>
							<div class="w3-container w3-third">
								<input class="w3-input" type="date" name="fecha">
							</div>
							<div class="w3-container w3-third">
								<button class="w3-button w3-green w3-hover-indigo" name="evento_buscar3"><i class="fas fa-search fa-f2"></i></button>
							</div>
						</form>
					</div>
					<br>
					<div class="w3-responsive w3-container">
						<form>
							<div class="w3-container">
								<table class="w3-table-all">
									<thead>
										<tr class="w3-indigo">
											<th>Fecha</th>
											<th>Concepto</th>
											<th>Nro Movimiento</th>
											<th>Debe</th>
											<th>Haber</th>
											<th>Deuda</th>
											<th colspan="2" class="w3-center">Acciones</th>
										</tr>
									</thead>
									<tbody>
									<%
									ArrayList<Ctactecliente> lista = new ArrayList<>();
									lista = (ArrayList<Ctactecliente>) request.getSession().getAttribute("movimientos");
									if(lista != null){
										for(Ctactecliente a : lista){%>
										<tr class="w3-hover-pale-green">
											<td><%=a.getFMOV() %></td>
											<td><%=a.getTCOMP() %></td>
											<td><%=a.getNCOMP() %></td>
											<td><%=a.getDEBE() %></td>
											<td><%=a.getHABER() %></td>
											<td></td>
											<td><button class="w3-button w3-green w3-hover-indigo"><i class="fas fa-search"></i></button></td>
											<td><button class="w3-button  w3-indigo w3-hover-green"><i class="fas fa-print"></i></button></td>
										</tr>
									<%}
									}%>
									</tbody>
								</table>
							
						</form>
					</div>
				<%} 
			}
			
			request.getSession().removeAttribute("socios");
			request.getSession().removeAttribute("persona");
			request.getSession().removeAttribute("movimientos");%>
	</div>

	<%@ include file="footer.jsp" %>
	<script type="text/javascript">
		function habilitar(dato){
			var doc = document.getElementById("doc");
			var socio = document.getElementById("socio");
			var input = document.getElementById("input");
			var inputFecha = document.getElementById("inputFecha");

			if (dato == "socio") {
				doc.checked = false;
				input.disabled = false;
				inputFecha.disabled = false;
			}
			else{
				socio.checked = false;
				input.disabled = false;
				inputFecha.disabled = false;
			}

			if(dato == "socio" && doc.checked == true || dato == "doc" && socio.checked == true){
				doc.checked = false;
				socio.checked = false;
				input.disabled = true;
				inputFecha.disabled = true;
			}
		}
	</script>
</body>
</html>