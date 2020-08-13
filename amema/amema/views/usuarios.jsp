<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Usuario" %>
<%@page import="controladores.CtrlUsuario" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestion de Usuarios</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		
		<%@ include file="menu.jsp" %>

		<!-- ACA VA EL CUERPO DE LAS ACCIONES -->
		<%
			CtrlUsuario cu = new CtrlUsuario();
			List<Usuario> listaUsers = cu.listarUsuarios();
		%>

		<div class="w3-container w3-padding-64">			
	
			
			
			<!-- FORMULARIOS PARA EL ABM DE USUARIOS CON MODALES -->
			<div class="w3-container w3-center w3-card-4">
				<button class="w3-jumbo fas fa-user-plus w3-button w3-hover-indigo w3-text-green w3-circle w3-border-0" onclick="document.getElementById('id01').style.display='block'"></button>
			</div>
				
			<!-- MODAL DEL ALTA DE USUARIO -->
			
			<%@include file="../modal/usuario/modalAltaUsuario.jsp" %>
			
			<%@ include file="../errores/errUsuario.jsp" %>

			<!-- FIN MODAL ALTA USUARIO -->
				
			<!-- EMPIEZA LA TABLA DE USUARIOS CON LOS BOTONES CAMBIAR PASS, MODIFICAR Y ELIMINAR-->
			<h1 class="w3-center  w3-text-indigo"> Usuarios </h1>
			<div class="w3-responsive w3-card-4">
				<table class="w3-table-all">
					<thead>
						<tr class="w3-indigo">
							<th>Usuario</th>
							<th>Nombre y Apellido</th>
							<th>Perfil</th>
							<th colspan="3" class="w3-center">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<% for(Usuario u : listaUsers){%>
							<tr class="w3-hover-light-green">
								<td><%=u.getLogIn() %></td>
								<td><%=u.getNomUs() %></td>
								<td><%=cu.consultaPerfil(u.getCperfil()) %></td>
								<td><button class="w3-xlarge fas fa-unlock w3-button w3-hover-indigo w3-text-yellow w3-round-xxlarge" onclick="abroModalUsuario('id02','<%=u.getLogIn()%>')"></button></td>
								<td><a href="../forms/usuario/modificaUsuarioForm.jsp?id=<%=u.getLogIn()%>" class="w3-xlarge fas fa-user-edit w3-button w3-hover-indigo w3-text-blue w3-round-xxlarge"></a></td>
								<td><button class="w3-xlarge fas fa-user-times w3-button w3-hover-indigo w3-text-red w3-round-xxlarge" onclick="abroModalUsuario('id03','<%=u.getLogIn()%>')"></button></td>
							</tr>
						<%}%>
					</tbody>
				</table>
			</div>
			<!-- FIN TABLA DE USUARIOS-->

		
			<!-- MODAL PARA CAMBIO DE PASS -->
			<%@ include file="../modal/usuario/modalCambioPass.jsp" %>
			
			<!-- MODAL PARA ELIMINAR USUARIO -->
			<%@ include file="../modal/usuario/modalEliminarUsuario.jsp" %>
			
		</div>

		<!-- FIN CUERPO -->

		<!-- Footer -->
		<%@ include file="footer.jsp" %>

	</body>
</html>