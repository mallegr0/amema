<%@page import="entidades.AuxListaMasivo"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList"%>


<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Listado de Pagos Menusuales</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<% ArrayList<AuxListaMasivo> lista = (ArrayList<AuxListaMasivo>) request.getSession().getAttribute("listado");%>

	<body>
		<!-- AREA DEL MENU -->
		<%@ include file="menu.jsp"%>
		<div class="w3-container w3-padding-64">
			<!-- form de busqueda -->
			<%@ include file="../forms/actualizacionesPagos/buscaOpcionesListaMasivoForm.jsp"%>

			<!-- tabla de resultados -->
			<br>
			<div class="w3-container w3-padding w3-card-4">
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo">
							<td>Periodo</td>
							<td>Convenio</td>
							<td>Nro Socio</td>
							<td>Empresa</td>
							<td>Legajo/Benef</td>
							<td>Apellido y Nombre</td>
							<td>Concepto</td>
							<td>Importe</td>
						</tr>
					</thead>
					<tbody>
						<% if(lista != null){
						for(AuxListaMasivo l: lista){%>
						<tr>
							<td><%=l.getPeriodo() %></td>
							<td><%=l.getConvenio() %></td>
							<td><%=l.getCodcli() %></td>
							<td><%=l.getEmpresa() %></td>
							<td><%=l.getLegajo() %></td>
							<td><%=l.getNombre() %></td>
							<td><%=l.getConcepto() %></td>
							<td><%=l.getImporte() %></td>
						</tr>
						<%}
					}
					else {%>
						<tr>
							<td colspan="8" class="w3-center  w3-text-indigo"><strong>No hay pagos ingresados</strong></td>
						</tr>
						<%}%>
					</tbody>
				</table>
			</div>
		</div>




		<!-- AREA DEL FOOTER -->
		<%@ include file="footer.jsp"%>
		
		<% request.getSession().removeAttribute("listado"); %>
	</body>
</html>