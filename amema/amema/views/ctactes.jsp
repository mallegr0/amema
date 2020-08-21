<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Cliente" %>
<%@page import="java.util.ArrayList" %>
<%@page import="entidades.CtacteGral" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>AMEMA web - Gestion de Cuentas Corrientes</title>
	<%@ include file="../meta/metadata.jsp"%>

</head>
<body>
	<!-- AREA DEL MENU-->
	<%@ include file="../views/menu.jsp" %>

	<div class="w3-container w3-padding-64 w3-card-4">
		<br>
		<h2 class="w3-center  w3-text-indigo">Consulta de Cuentas Corrientes</h2>
		<div class="w3-container w3-card-4">
			<%@ include file="/forms/ctacte/buscarCtaCteForm.jsp"%>
		</div>
		<br>
		<% 	
			if(request.getSession().getAttribute("persona") == null && request.getSession().getAttribute("movimientos") == null){%>
			<h1 class="w3-center"> No hay datos que mostrar</h1><%}
			else {
				Cliente c = (Cliente) request.getSession().getAttribute("persona");
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
										<th>Saldo</th>
										<th>Estado</th>
										<th colspan="2" class="w3-center">Acciones</th>
									</tr>
								</thead>
								<tbody>
								<%
								ArrayList<CtacteGral> mov = (ArrayList<CtacteGral>) request.getSession().getAttribute("movimientos");
									if(mov != null){
										for(CtacteGral a : mov){%>
										<tr class="w3-hover-pale-green">
											<td><%=a.getFMOV() %></td>
											<td><%=a.getTMOV() %></td>
											<td><%=a.getNCOMP() %></td>
											<td><%=a.getDEBE() %></td>
											<td><%=a.getHABER() %></td>
											<td><%=a.getSALDO() %></td>
											<td><button class="w3-button w3-green w3-hover-indigo"><i class="fas fa-search"></i></button></td>
											<td><button class="w3-button  w3-indigo w3-hover-green"><i class="fas fa-print"></i></button></td>
										</tr>
									<%}
									}%>
									</tbody>
								</table>
							</div>
						</form>
					</div>
			
			<% }
			request.getSession().removeAttribute("persona");
			request.getSession().removeAttribute("movimientos");%>
	</div>

	<%@ include file="footer.jsp" %>
</body>
</html>