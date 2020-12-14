<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.AuxAnDeudaCli" %>
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA Web - Actualiza Pagos por convenio</title>
		<%@include file="../meta/metadata.jsp"%>
		
	</head>
	<body>
		<!-- Menu -->
		<%@include file="../views/menu.jsp"%>

		
		<!-- Cuerpo -->
		<div class="w3-container w3-padding-64">
			<div class="w3-container w3-padding w3-card-4">
				<%@include file="../forms/actualizacionesPagos/actualizaMasivoForm.jsp"%>
			</div>
			<br>
			<div class="w3-container w3-padding w3-card-4">

				<% String msj1 = (String) request.getSession().getAttribute("mensaje");
				   ArrayList<AuxAnDeudaCli> errores = (ArrayList<AuxAnDeudaCli>) request.getSession().getAttribute("errores");
				%>
				<% if(msj1 != null) { %> <h3 class="w3-center  w3-text-indigo"><strong><%=msj1%></strong></h3> <%}%>
				<% if(errores != null){%> 
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo">
							<td colspan="3" class="w3-center"><strong>Detalle del error</strong></td>
						</tr>
					</thead>
					<tbody>
						<%for(AuxAnDeudaCli aux: errores ){%>
						<tr class="w3-pale-red">
							<td><%=aux.getCODCLI() %></td>
							<td><%=aux.getNOMCLI() %></td>
							<td>No pudo ser ingresado</td>
						</tr>
						<%}%>
					</tbody>
				</table>
				
			<%}%>
			
			<!-- Modal para el spiner -->
			<div id="spinner" class="w3-modal">
				<div class="w3-modal-content w3-animate-zoom w3-card-4">
				   	<header class="w3-container w3-indigo"> 
				       	<span onclick="document.getElementById('spinner').style.display='none'" class="w3-button w3-display-topright">&times;</span>
			        	<h2 class="w3-center"> Leyendo Archivo y actualizando la Base de Datos. Aguarde un momento!! </h2>
			      	</header>
			      	<div class="loader"></div>
				</div>
			</div>
			
			</div>
		</div>
		<!-- Footer -->
		<%@include file="footer.jsp"%>
		<script type="text/javascript">
			function completaRuta(){
				var nombre = document.getElementById("texto").value;
				var ruta = "C:/Municipalidad/"+nombre.substring(12);
				document.getElementById("ruta").value = ruta;
			}
			
			function abrospinner(){
				document.getElementById('spinner').style.display='block'; 
			}
		</script>

		<% 
		request.getSession().removeAttribute("mensaje");
		request.getSession().removeAttribute("errores");
		%>
	</body>
</html>