<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Parámetros Convenio/Socio</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		<%@ include file="../views/menu.jsp"%>
		
		<div class="w3-container w3-padding-64 w3-card-4">
		<br>
		<h2 class="w3-center  w3-text-indigo">Consulta parámetros Socio / Convenio</h2>
		<div class="w3-container w3-card-4">
		
			<%@ include file="../errores/errSocioConvenio.jsp"%>
				
			<%@ include file="../forms/convenioSocio/buscarConvenioSocioForm.jsp"%>
		</div>
		<br>
		<%if(request.getSession().getAttribute("lista") != null || request.getSession().getAttribute("socio") != null) {%>
			<% if(request.getSession().getAttribute("lista") != null){%>
			<div class="w3-container w3-card-4">
				<br>
				<%@ include file="/forms/convenioSocio/seleccionarSConvenioSocioForm.jsp"%>
			</div>
			<%}
			if(request.getSession().getAttribute("socio") != null){%>
			<div class="w3-container w3-card-4">
				<br>
				<%@ include file="../forms/convenioSocio/listarConvenioSocioForm.jsp"%>
			</div>	

			<%}
		}
		else {%> 
			<div class="w3-container w3-card">
				<h1 class="w3-center  w3-text-indigo">No hay datos para mostrar</h1>
			</div>
		<%}%>
			
</div>


	<%
		request.getSession().removeAttribute("lista");
		request.getSession().removeAttribute("socio");
		request.getSession().removeAttribute("datos"); 
	%>
	


		<%@include file="../views/footer.jsp" %>
	</body>
</html>