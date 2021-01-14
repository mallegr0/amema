<%@page import="entidades.PeriodoDeudaArch"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList" %>
<%@page import="controladores.CtrlPeriodoDeudaGen" %>
<%@page import="entidades.PeriodoDeudaGen" %>
<%@page import="entidades.PeriodoGenGral" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Periodos de Deuda</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		<%@ include file="menu.jsp"%>
		<!-- AREA DEL CUERPO -->
		<div class="w3-container w3-padding-64">
			<%
				CtrlPeriodoDeudaGen cPeriodo = new CtrlPeriodoDeudaGen();
				ArrayList<PeriodoDeudaGen> periodos = cPeriodo.listarPeriodoDeudaGen();
				ArrayList<PeriodoGenGral> datos = (ArrayList<PeriodoGenGral>) request.getSession().getAttribute("datos");
			%>
			
			<%if(datos == null) {%>
			<h3 class="w3-center w3-text-indigo"><strong>Períodos de Deudas Generados</strong></h3>
			<div class="w3-container w3-padding w3-card-4">
				<input type="text" id="myInput" onkeyup="myFunction()" class="w3-input" placeholder="Ingrese periodo a consultar">
				<br>
				<table class="w3-table w3-bordered" id="myTable">
					<thead>
						<tr class="w3-indigo">
							<td>Nro. Generación Deuda</td>
							<td>Anulado</td>
							<td>Fecha Gen.</td>
							<td>Nro. Conv</td>
							<td>Periodo</td>
							<td>Inf. Ingresada?</td>
							<td>Recibos Gen.?</td>
							<td>Genera Deuda al</td>
							<td>Consultar</td>
						</tr>
					</thead>
					<tbody>
						<%for(PeriodoDeudaGen p: periodos) {%>
						<tr>
							<td><%=p.getNro_gen_deuda() %></td>
							<td><%=p.getAnulado() %></td>
							<td><%=p.getFecha_gen() %></td>
							<td><%=p.getNroconv() %></td>
							<td><%=p.getPeriodo() %></td>
							<td><%=p.getInform_ingresada() %></td>
							<td><%=p.getRecibos_gen()%></td>
							<td><%=p.getFechaHasta() %></td>
								<td><button class="w3-button w3-green w3-hover-indigo" onclick="abroModalPeriodo('modalPeriodo','<%=p.getNro_gen_deuda()%>')"><i class="fas fa-search fa-2x"></i></button></td>
						</tr>
						<%}%>
					</tbody>
				</table>
			</div>
			<%} %>
			<!-- Este es el area para mostrar los datos que selecciono desde el form -->
			<br>
			<br>
			<h3 class="w3-center w3-text-indigo"><strong>Archivos leidos según la selección: </strong></h3>
			<br>
			<div class="w3-container w3-padding w3-card-4">
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo">
							<td>Nro. Generación</td>
							<td>Nro. Convenio</td>
							<td>Período</td>
							<td>Nombre Archivo</td>
						</tr>
					</thead>
					<tbody>
					<%if(datos != null){
						for(PeriodoGenGral d: datos) {%>
						<tr>
							<td><%=d.getNroGen() %></td>
							<td><%=d.getNroConv() %></td>
							<td><%=d.getPeriodo() %></td>
							<td><%=d.getNombArchivo() %></td>
						</tr>
						<%}
						}
						else {%>
						<tr>
							<td colspan="4"><h4 class="w3-center"><strong class="w3-text-indigo">No hay datos en el período seleccionado</strong></h4></td>
						</tr>
						<%}%>
					</tbody>
				</table>
			</div>
			<br>
			<div class="w3-container w3-padding w3-center">
				<a href="/amema/views/periodosDeuda.jsp" class="w3-button w3-green w3-hover-indigo"><strong>VOLVER</strong></a>
			</div>
		</div>
		<%
			request.getSession().removeAttribute("datos");
		%>
		<!-- AREA DEL MODAL --> 
		<%@ include file="../modal/periodos/periodosDeudaModal.jsp"%>
		<!-- AREA DEL FOOTER -->
		<%@ include file="footer.jsp"%>

		<script>
			function abroModalPeriodo(id, nroDeuda){
				document.getElementById(id).style.display='block'; 
				if(id == 'modalPeriodo'){
					document.getElementById("periodo").value = nroDeuda;
				}
			}

			function myFunction() {
			  // Declare variables
			  var input, filter, table, tr, td, i, txtValue;
			  input = document.getElementById("myInput");
			  filter = input.value.toUpperCase();
			  table = document.getElementById("myTable");
			  tr = table.getElementsByTagName("tr");

			  // Loop through all table rows, and hide those who don't match the search query
			  for (i = 0; i < tr.length; i++) {
			    td = tr[i].getElementsByTagName("td")[4];
			    if (td) {
			      txtValue = td.textContent || td.innerText;
			      if (txtValue.toUpperCase().indexOf(filter) > -1) {
			        tr[i].style.display = "";
			      } else {
			        tr[i].style.display = "none";
			      }
			    }
			  }
			}
		</script>
	</body>
</html>