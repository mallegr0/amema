<%@page import="entidades.VentasM"%>
<%@page import="entidades.Cliente"%>
<%@page import="java.util.ArrayList" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Cobros a Socios</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		<%@ include file="menu.jsp"%>
		<div class="w3-container w3-padding-64">
			<!-- AREA DE BUSQUEDA -->
			<%@ include file="../forms/pagosSocio/buscaSocioForm.jsp"%>
			<% 
			Cliente cli = (Cliente) request.getSession().getAttribute("socio");
			   if(cli != null){%>
			   <br>
			   		<h3 class="w3-text-indigo w3-center"><strong><%=cli.getCODCLI()%> - <%=cli.getNOMCLI() %></strong></h3>
			   		<br>
				<%}%>		

			<br>
			<%@ include file="../errores/errPagoSocio.jsp"%>
			<br>
			<form action="/amema/PagoSocio" method="post">
				<div class="w3-container w3-padding w3-card-4">
					<h3 class="w3-center  w3-text-indigo"><strong>Movimientos adeudados</strong></h3>
					<table class="w3-table w3-bordered">
						<thead>
							<tr class="w3-indigo">
								<td>Tipo Comprobante</td>
								<td>Nro comprobante</td>
								<td>Fecha</td>
								<td>Importe total</td>
								<td>importe Adeudado</td>
								<td>A pagar</td>
								<td>Descuento</td>
								<td>Cancela?</td>
							</tr>
						</thead>
						<tbody>
							<% ArrayList<VentasM> movimientos = (ArrayList<VentasM>) request.getSession().getAttribute("deudas");  
							if(movimientos != null){
								for(VentasM v: movimientos){%>
								<tr>
									<td><%=v.getTCOMP() %></td>
									<td><%=v.getNCOMP() %></td>
									<td><%=v.getFMOV() %></td>
									<td><%=v.getSUBTOTAL() %></td>
									<td><%=v.getA_CUENTAD() %></td>
									<td><%=v.getA_CUENTA() %></td>
									<td><%=v.getIMPDESCTO() %></td>
									<td><input type="checkbox" name="opc"  id="opc" class="w3-input" onclick="actualizarValor(this.checked, '<%=v.getSUBTOTAL() %>')" value="<%=v.getNCOMP()%>"></td>
								</tr>
							<% }
							}
							else {%>
							<tr><td colspan="8" class="w3-center"><strong class="w3-text-indigo">No hay Movimientos adeudados</strong></td></tr>
							<%} %>
						</tbody>
					</table>
					<br>
					<div class="w3-container w3-padding w3-card-4">
						<div class="w3-container w3-padding w3-quarter">
							<label><strong class="w3-text-indigo">Total Deuda: </strong></label><input class="w3-input" type="text" name="total" id="total" value="$0">
						</div>
						<div class="w3-container w3-padding w3-rest">
							<div class="w3-container w3-padding w3-quarter">
								<label><strong class="w3-text-indigo" style="text-align: right;">Forma de cobro: </strong></label>
							</div>
							<div class="w3-container w3-padding w3-rest">
								<select class="w3-select" name="cobro">
									<option value="efectivo">Efectivo</option>
									<option value="transferecia">Transf. Bancaria</option>
								</select>
							</div>
						</div>
					</div>
					<br>
					<div class="w3-container w3-padding w3-center">
						<button class="w3-button w3-green w3-hover-indigo" name="evento_pago"><strong>Confirmar</strong></button>
					</div>
				</div>
			</form>
		</div>
		<!-- AREA DEL FOOTER -->
		<%@ include file="footer.jsp"%>
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

			var sumaActual = 0;
			function actualizarValor(check, monto) {
				valor = parseInt(monto);
				
				if(check == true) { sumaActual += valor; }
				else { sumaActual -= valor; }
				
				document.getElementById("total").value ="$"+sumaActual;
			}
		</script>
		
		<% request.getSession().removeAttribute("socio");
		   request.getSession().removeAttribute("deudas");
		   request.getSession().removeAttribute("msj");
		%>
	</body>
</html>