<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Cliente" %>
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
	<!-- AREA DEL MENU-->
	<%@ include file="../views/menu.jsp" %>

	<div class="w3-container w3-padding-64 w3-card-4">
		<br>
		<h2 class="w3-center">Consulta de Adherentes</h2>
		<div class="w3-container w3-card-4">
			<%@ include file="/forms/adherente/buscarAdherenteForm.jsp"%>
		</div>
		<br>
		<% 
			if(request.getSession().getAttribute("socio") == null && request.getSession().getAttribute("lista") == null){%>
			<h1 class="w3-center"> No hay datos que mostrar</h1><%}
			else {
				if(request.getSession().getAttribute("lista") != null) {
				%>
				<div class="w3-container w3-card-4">
					<br>
					<%@ include file="/forms/adherente/seleccionarSAdherenteForm.jsp"%>
				</div>
			<%}
				else {
					Cliente c = (Cliente) request.getSession().getAttribute("socio");
				%> 
					<div class="w3-container w3-card-4">
						<h3 class="w3-center"> Datos del Socio</h3>
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
				<%}
			}
			
			request.getSession().removeAttribute("lista");
			request.getSession().removeAttribute("socio");%>
	</div>

	<%@ include file="footer.jsp" %>
	<script type="text/javascript">
		function habilitar(dato){
			var doc = document.getElementById("doc");
			var socio = document.getElementById("socio");
			var input = document.getElementById("input");

			if (dato == "socio") {
				doc.checked = false;
				input.disabled = false;
			}
			else{
				socio.checked = false;
				input.disabled = false;
			}

			if(dato == "socio" && doc.checked == true || dato == "doc" && socio.checked == true){
				doc.checked = false;
				socio.checked = false;
				input.disabled = true;
			}
		}
	</script>
</body>
</html>