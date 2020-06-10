<%@page import="java.util.List"%>
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
		

		<div class="w3-container w3-padding-64">			
			<div class="3-container w3-red w3-card-4 w3-hide">
				<ol>
					<li>Acomodar errSocio.jsp</li>
					<li>testear</li>
				</ol>
			</div>
			
			<!-- FORMULARIOS PARA EL ABM DE PRODUCTOS CON MODALES -->
			<div class="w3-container w3-center w3-card-4">
				<button class="w3-jumbo fas fa-user-plus w3-button w3-hover-indigo w3-text-green w3-circle" onclick="document.getElementById('id01').style.display='block'"></button>
			</div>
				
			<!-- MODAL DEL ALTA DE SOCIOS -->
			
			<%@include file="../modal/socio/modalAltaSocio.jsp" %>
			
			<%@ include file="../errores/errSocio.jsp" %>

				
			<!-- EMPIEZA LA TABLA DE PRODUCTOS CON LOS BOTONES MODIFICAR Y ELIMINAR-->
			<h1 class="w3-center"> Socios </h1>
			<div class="w3-responsive w3-card-4 w3-round-xlarge">
				<table class="w3-table-all w3-round-xlarge">
					<thead>
						<tr class="w3-indigo">
							<th>Código</th>
							<th>Razón Social</th>
							<th>Domicilio</th>
							<th>Localidad</th>
							<th colspan="4" class="w3-center">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>58866</td>
							<td>Allegranza Matias</td>
							<td>Entre Rios 1339</td>
							<td>Rosario</td>
							<td><button class="w3-xlarge fas fa-user-edit w3-button w3-hover-indigo w3-text-yellow w3-round-xxlarge" onclick="abroModalSocio('id02','Aca iria el nro de prodcuto')"></button></td>
								<td><button class="w3-xlarge fas fa-user-times w3-button w3-hover-indigo w3-text-red w3-round-xxlarge" onclick="abroModalSocio('id03','aca iria el nro de producto')"></button></td>
								<td><button class="w3-xlarge fas fa-print w3-button w3-hover-indigo w3-text-green w3-round-xxlarge" onclick="abroModalSocio('id04', 'Aca iria el nro de socios')"></button></td>
								<td><button class="w3-xlarge fas fa-search w3-button w3-hover-indigo w3-text-blue w3-round-xxlarge" onclick="abroModalSocio('id05', 'Aca iria el nro de socios')"></button></td>
							</tr>
						</tr>
						<%--<% for(Usuario u : listaUsers){%
							<tr class="w3-hover-light-green">
								<td><%=u.getUsuario() %></td>
								<td><%=u.getNombreyapellido() %></td>
								<td><%=u.getPassword() %></td>
								<td><button class="w3-xlarge fas fa-unlock w3-button w3-hover-indigo w3-text-yellow w3-round-xxlarge" onclick="abroModal('id02','<%=u.getUsuario()%>')"></button></td>
								<td><button class="w3-xlarge fas fa-user-edit w3-button w3-hover-indigo w3-text-blue w3-round-xxlarge" onclick="abroModal('id03','<%=u.getUsuario()%>')"></button></td>
								<td><button class="w3-xlarge fas fa-user-times w3-button w3-hover-indigo w3-text-red w3-round-xxlarge" onclick="abroModal('id04','<%=u.getUsuario()%>')"></button></td>
							</tr>
						<%}%>--%>
					</tbody>
				</table>
			</div>
			<!-- FIN TABLA DE PRODUCTOS-->

			
			<!-- MODAL PARA MODIFICAR PRODUCTOS -->
			<%@ include file="../modal/socio/modalAltaSocio.jsp" %>


			<!-- MODAL PARA ELIMINAR PRODUCTOS -->
			<%@ include file="../modal/socio/modalEliminarUsuario.jsp" %>
			
		</div>

		<!-- FIN CUERPO -->

		<!-- Footer -->
		<%@ include file="footer.jsp" %>

	</body>
</html>