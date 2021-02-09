<%@page import="java.util.List"%>
<%@page import="entidades.Familia" %>
<%@page import="controladores.CtrlFamilia" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestion de Productos</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		
		<%@ include file="menu.jsp" %>

		<!-- ACA VA EL CUERPO DE LAS ACCIONES -->
		<%
			CtrlFamilia cf = new CtrlFamilia();
			List<Familia> listaFlias = cf.listarFamilia();
			session.setAttribute("lf", listaFlias);
		%>

		<div class="w3-container w3-padding-64">			
			
			<!-- FORMULARIOS PARA EL ABM DE PRODUCTOS CON MODALES -->
			<div class="w3-container w3-center w3-card-4">
				<button class="w3-jumbo fas fa-plus-circle w3-button w3-hover-indigo w3-text-green w3-circle w3-border-0" onclick="document.getElementById('id01').style.display='block'"></button>
			</div>
				
			<!-- MODAL DEL ALTA DE PRODUCTO -->
			
			<%@ include file="../modal/producto/modalAltaProducto.jsp" %>
			
			<%@ include file="../errores/errProducto.jsp" %>

				
			<!-- EMPIEZA LA TABLA DE PRODUCTOS CON LOS BOTONES MODIFICAR Y ELIMINAR-->
			<h1 class="w3-center  w3-text-indigo"> Productos </h1>
			<div class="w3-responsive w3-card-4">
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo">
							<th>Código</th>
							<th>Denominación de familias</th>
							<th>Bonificación</th>
							<th colspan="2" class="w3-center">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<% for(Familia f : listaFlias){%>
							<tr class="w3-hover-light-blue">
								<td><%=f.getCFAMI() %></td>
								<td><%=f.getNFAMI() %></td>
								<td><%=f.getBFAMI() %></td>
								<td><a href="../forms/producto/modificaProductoForm.jsp?id=<%=f.getCFAMI()%>" class="fas fa-edit w3-button w3-hover-indigo w3-text-blue" ></a></td>
								<td><button class="fas fa-times w3-button w3-hover-indigo w3-text-red" onclick="abroModalProducto('id02','<%=f.getCFAMI()%>')"></button></td>
							</tr>
						<%}%>
					</tbody>
				</table>
			</div>
			<!-- FIN TABLA DE PRODUCTOS-->

			<!-- MODAL PARA ELIMINAR PRODUCTOS -->
			<%@ include file="../modal/producto/modalEliminarProducto.jsp" %>
			
		</div>
		<% 
			session.removeAttribute("lf");
			cf = null;
		%>

		<!-- FIN CUERPO -->

		<!-- Footer -->
		<%@ include file="footer.jsp" %>
	</body>
</html>