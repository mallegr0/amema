<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Cliente" %>
<%@page import="controladores.CtrlCliente" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestión de Movimientos Fijos</title>
		<%@ include file="../../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		<%@ include file="../../views/menu.jsp" %>

		

		<div class="w3-container w3-padding-64">
			<%@ include file="../../errores/errMovimientoGarante.jsp"%> 
			<div class="w3-container w3-padding w3-card-4">
				<h3 class="w3-text-indigo w3-center">Nro Movimiento Fijo: <%=request.getSession().getAttribute("nro") %> - <%=request.getSession().getAttribute("articulo") %></h3>
				<br>
				<% ArrayList<Cliente> garantes = (ArrayList<Cliente>)request.getSession().getAttribute("garantes");%>
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo w3-center">
							<th>Nro de Socio</th>
							<th>Nombre y Apellido</th>
							<th>Acción</th>
						</tr>
					</thead>
					<tbody>
						<%if(garantes != null){
							for(Cliente c : garantes){%>
						<tr>
							<td><%=c.getCODCLI() %></td>
							<td><%=c.getNOMCLI() %></td>
							<td><button class="w3-button w3-red w3-hover-indigo" onclick="abremodal('id01','<%=c.getCODCLI() %>')"><i class="fas fa-minus fa-lg"></i></button></td>
						</tr>
					<%}
						}
					else {%>
						<tr>
							<td colspan="3"><h2 class="w3-text-indigo w3-center">No hay garantes para el movimiento seleccionado</h2></td>
						</tr><%}%>
					</tbody>
				</table>
			</div>
			<br>
			<form action="/amema/MovimientoFijo" method="post">
				<div class="w3-container w3-padding w3-card-4">
					<h3 class="w3-text-indigo w3-center">Agregar Garantes</h3>
					<br>
					<div class="w3-container">
					    <div class="w3-container w3-padding w3-half">
					        <input type="text" id="search" name="search" class="w3-input" onkeyup="filter(this.value)">
					    </div>
					    <div class="w3-container w3-padding w3-half">
					        <select id="select" name="select" class="w3-select" size="5" required>
					            <%
					            CtrlCliente c = new CtrlCliente();
					            ArrayList<Cliente> lista = c.listarCliente();
					            for(Cliente l : lista){ %>
					            <option value="<%=l.getCODCLI()%>"><%=l.getNOMCLI() %></option>
					            <%} %>
					        </select>
					    </div>
					</div>
					<br>
					<div class="w3-container w3-padding">
						<div class="w3-container w3-padding w3-center w3-half">
							<button class="w3-button w3-green w3-hover-indigo" name="agregar_garante"><i class="fas fa-plus fa-2x"></i></button>
						</div>
						<div class="w3-container w3-padding w3-center w3-half">
							<a href="../../views/movimientosfijos.jsp" class="w3-button w3-red w3-hover-indigo"><strong>VOLVER</strong></a>
						</div>
					</div>
				</div>
			</form>
		</div>
	
		<%@include file="../../modal/movimientos/modalBajaGaranteMovFijo.jsp" %>
		<!-- Area del footer -->
		<%@ include file="../../views/footer.jsp"%>
		<script>
			function filter( keyword) {
			    var select = document.getElementById("select");
			    select.show = true;
			    for (var i = 0; i < select.length; i++) {
			        var txt = select.options[i].text;
			        var include = txt.toLowerCase().startsWith(keyword.toLowerCase());
			        select.options[i].style.display = include ? 'list-item':'none';
			    }
			}

			function abremodal(modal, codigo) {
				document.getElementById(modal).style.display='block';
				document.getElementById("codgarante").value = codigo;
			}
		</script>
			
	</body>
</html>