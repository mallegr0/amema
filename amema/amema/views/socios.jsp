<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Cliente"%>
<%@page import="controladores.CtrlCliente"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestion de Socios</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		
		<%@ include file="menu.jsp" %>

		<!-- ACA VA EL CUERPO DE LAS ACCIONES -->
		<% 
			CtrlCliente cc = new CtrlCliente();
			List<Cliente> listaSocios = cc.listarCliente();
			request.getSession().setAttribute("id", cc.ultimoID());
		%>

		<div class="w3-container w3-padding-64">			
			
			<!-- FORMULARIOS PARA EL ABM DE PRODUCTOS CON MODALES -->
			<div class="w3-container w3-center w3-card-4">
				<button class="w3-jumbo fas fa-user-plus w3-button w3-hover-indigo w3-text-green w3-circle" onclick="document.getElementById('id01').style.display='block'"></button>
			</div>
				
			<!-- MODAL DEL ALTA DE SOCIOS -->
			
			<%@include file="../modal/socio/modalAltaSocio.jsp" %>
			
			<%@ include file="../errores/errSocio.jsp" %>

			<br><br>


			<h1 class="w3-center"> Filtrado de Socios </h1>
			<!--Formulario de busqueda de Socio-->
			<form class="w3-container w3-card-4">
				<div class="w3-quarter w3-container">
					<p>
						<input class="w3-check" id="todos" type="checkbox" name="todos" onclick="habilitar('todos')">
					    <label>Listar Todos los Socios</label>
					</p>
					<p>
						<input class="w3-check" id="nombre" type="checkbox" name="nombre" onclick="habilitar('nombre')">
						<label>Buscar por Nombre y Apellido</label>
					</p>
					<p>
						<input class="w3-check" id="doc" type="checkbox" name="doc" onclick="habilitar('doc')">
						<label>Buscar por Documento</label>
					</p>
				</div>
				<div class="w3-half w3-container">
					<p></p>
					<p>
						<input class="w3-input" id="dato" type="text" name="dato" placeholder="Ingrese el dato a buscar" disabled="true">
					</p>
					<p></p>
				</div>
				<div class="w3-quarter w3-container">
					<p></p>
					<p>
						<button class="w3-button w3-green w3-hover-indigo fas fa-search w3-round-xxlarge fa-3x"></button>
					</p>
					<p></p>
				</div>
 			</form>
			
			<br><br>
			<!-- EMPIEZA LA TABLA DE SOCIOS CON LOS BOTONES MODIFICAR , ELIMINAR E IMPRIMIR-->
			<h1 class="w3-center"> Socios </h1>
			<div class="w3-responsive w3-card-4 w3-round-xlarge">
				<table class="w3-table-all w3-round-xlarge">
					<thead>
						<tr class="w3-indigo">
							<th>Legajo</th>
							<th>Apellido y Nombre</th>
							<th>Domicilio</th>
							<th>Tpo y Nro de Documento</th>
							<th>Telefonos</th>
							<th colspan="3" class="w3-center">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<% for(Cliente c : listaSocios){
						%>	
							<tr class="w3-hover-pale-green">
								<td><%=c.getDNRP() %></td>
								<td><%=c.getNOMCLI() %></td>
								<td><%=c.getDOMCLI() %></td>
								<td><%=c.getTIPO_DOC()%>: <%=c.getCUITCLI()%></td>
								<td>
									<%if (c.getTELCLI_1() == null){%> --
									<%}else{%> <%=c.getTELCLI_1()%> <%}%>
									/ 
									<%if (c.getTELCLI_2() == null) {%> --
									<%}else{ %> <%=c.getTELCLI_2() %> <%} %>
									
								</td>
								<td><a href="../forms/socio/modificaSocioForm.jsp?id=<%=c.getCODCLI()%>" class="w3-xlarge fas fa-user-edit w3-button w3-hover-indigo w3-text-blue w3-round-xxlarge"></a></td>
								<td><button class="w3-xlarge fas fa-user-times w3-button w3-hover-indigo w3-text-red w3-round-xxlarge" onclick="abroModalSocio('id02','<%=c.getCODCLI()%>')"></button></td>
								<td><button class="w3-xlarge fas fa-print w3-button w3-hover-indigo w3-text-yellow w3-round-xxlarge" onclick="abroModalSocio('id03','<%=c.getCODCLI()%>')"></button></td>
							</tr>
						<%}%>
						
					</tbody>
				</table>
			</div>
			<!-- FIN TABLA DE SOCIOS-->

			<!-- MODAL PARA ELIMINAR SOCIOS -->
			<%@ include file="../modal/socio/modalEliminarSocio.jsp" %>
			
			<!-- MODAL PARA IMPRIMIR LOS DATOS DE SOCIO -->
			<%@include file="../modal/socio/modalImprimirSocio.jsp" %>

		</div>

		<!-- FIN CUERPO -->

		<!-- Footer -->
		<%@ include file="footer.jsp" %>
		
		<script type="text/javascript">
			function habilitar(dato){
				if (dato == "nombre") {
					document.getElementById("todos").checked = false;
					document.getElementById("doc").checked = false;
					document.getElementById("dato").disabled = false;
				}
				else if (dato == "doc"){
					document.getElementById("todos").checked = false;
					document.getElementById("nombre").checked = false;
					document.getElementById("dato").disabled = false;
				}
				else{
					document.getElementById("nombre").checked = false;
					document.getElementById("doc").checked = false;
				}
				
			}

		</script>


	</body>
</html>