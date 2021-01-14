<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="entidades.VentasM" %>
<%@page import="java.text.DecimalFormat" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Analisis de Deuda</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>

		<% 
			String socio = (String) request.getSession().getAttribute("cliente"); 
			ArrayList<VentasM> deudas = (ArrayList<VentasM>) request.getSession().getAttribute("datos");
			double st, acta; 
			DecimalFormat df = new DecimalFormat("#0.00");
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
			
			if(socio == null) { socio = "Socio"; }
			if(request.getSession().getAttribute("subtotal") == null) { st = 0.00; }
			else { st = Double.parseDouble((String) request.getSession().getAttribute("subtotal")); }
			if(request.getSession().getAttribute("acta") == null) { acta = 0.00; }
			else { acta = Double.parseDouble((String) request.getSession().getAttribute("acta")); }
			
			st = st - acta; 
		%>

		<!-- AREA DEL MENU -->
		<%@ include file="menu.jsp"%>
		<div class="w3-container w3-padding-64">
			<div class="w3-container w3-padding w3-card-4">
				<%@ include file="../forms/analisisDeuda/buscarSocioADForm.jsp"%>
			</div>
			<br>
			<%@ include file="../errores/errGeneraAnalisisDeuda.jsp"%>
			<br>
			<div class="w3-container w3-padding w3-card-4">
				<h3 class="w3-text-indigo w3-center"><strong>Comprobantes a Pagar de <%=socio%></strong></h3>
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo">
							<td>Nro. Comp.</td>
							<td>Tipo de Comp.</td>
							<td>Fecha</td>
							<td>Imp. Total</td>
							<td>Imp. Pagado</td>
							<td>Imp. Adeudado</td>
						</tr>
					</thead>
					<tbody>
					<%if(deudas != null){
						for(VentasM v: deudas){%>
						<tr>
							<td><%=v.getNCOMP() %></td>
							<td><%=v.getOBSERV() %></td>
							<td><%=sf.format(v.getFMOV()) %></td>
							<td>$<%=df.format(v.getSUBTOTAL()) %></td>
							<td>$<%=df.format(v.getA_CUENTA()) %></td>
							<td>$<%=df.format(v.getSUBTOTAL() - v.getA_CUENTA()) %></td>
						</tr>
						<% }
					}
					else {%>
						<tr>
							<td colspan="6" class="w3-center"><strong class="w3-text-indigo">No hay Datos para mostrar</strong></td>
						</tr>
				<%}%>
					</tbody>
				</table>
				<br>
				<div class="w3-container w3-padding">
					<p>
						<div class="w3-container w3-padding w3-quarter">
							<label class="w3-text-indigo w3-light-gray"><strong>Total Adeudado ........... $</strong></label>
						</div>
						<div class="w3-container w3-padding w3-rest">
							<input type="text" value="<%=df.format(st)%>">
						</div>
					</p>
					<p>
						<div class="w3-container w3-padding w3-quarter">
							<label class="w3-text-indigo"><strong>Anticipos no aplicados ... $</strong></label>
						</div>
						<div class="w3-container w3-padding w3-rest">
							<input type="text" value="0.00">
						</div>
					</p>
					<p>
						<div class="w3-container w3-padding w3-quarter">
							<label class="w3-text-indigo"><strong>Créditos no Desc ......... $</strong></label>
						</div>
						<div class="w3-container w3-padding w3-rest">
							<input type="text" value="0.00">
						</div>
					</p>
					<p>
						<div class="w3-container w3-padding w3-quarter">
							<label class="w3-text-indigo"><strong>Constancias de retencion . $</strong></label>
						</div>
						<div class="w3-container w3-padding w3-rest">
							<input type="text" value="0.00">
						</div>
					</p>
					<p>
						<div class="w3-container w3-padding w3-quarter">
							<label class="w3-text-indigo w3-light-gray"><strong>Saldo .................... $</strong></label>
						</div>
						<div class="w3-container w3-padding w3-rest">
							<input type="text" value="<%=df.format(st)%>">
						</div>
					</p>
					<p>
						<div class="w3-container w3-padding w3-quarter">
							<label class="w3-text-indigo"><strong>Cheques diferidos ........ $</strong></label>
						</div>
						<div class="w3-container w3-padding w3-rest">
							<input type="text" value="0.00"> 
						</div>
					</p>
					<p>
						<div class="w3-container w3-padding w3-quarter">
							<label class="w3-text-indigo w3-pale-yellow"><strong>Crédito máx. permitidos .. $</strong></label>
						</div>
						<div class="w3-container w3-padding w3-rest">
							<input type="text" value="0.00">
						</div>
					</p>
					<p>
						<div class="w3-container w3-padding w3-quarter">
							<label class="w3-text-indigo w3-light-gray"><strong>Excendencia de crédito ........ $</strong></label>
						</div>
						<div class="w3-container w3-padding w3-rest">
							<input type="text" value="<%=df.format(st)%>">
						</div>
					</p>
				</div>
			</div>
			<br>
			<div class="w3-container w3-padding w3-card-4">
				<div class="w3-container w3-padding w3-half w3-center">
					<button class="w3-button w3-green w3-hover-indigo" onclick="abroModalAnalisis('<%=socio%>', 'migrarExcel')"><strong>Migrar</strong> <i class="fas fa-file-excel fa-2x"></i></button>
				</div>
				<div class="w3-container w3-padding w3-half w3-center">
					<button class="w3-button w3-purple w3-hover-indigo" onclick="abroModalAnalisis('<%=socio%>', 'imprimirPDF')"><strong>Imprimir </strong><i class="fas fa-file-pdf fa-2x"></i></button>
				</div>
			</div>
		</div>

		<!-- AREA DEL MODAL -->
		<%@ include file="../modal/analisisDeuda/modalAnalizarDeuda.jsp"%>

	<% 
		request.getSession().removeAttribute("cliente"); 
		request.getSession().removeAttribute("datos"); 
		request.getSession().removeAttribute("subtotal");
		request.getSession().removeAttribute("acuenta"); 
		request.getSession().removeAttribute("msj");
	%>
		<!-- AREA DEL FOOTER -->
		<%@ include file="footer.jsp"%>

		<script>
			function abroModalAnalisis(cliente, evento){
				var titulo; 
				
				if(evento === "migrarExcel") { titulo = "Migrar a hoja de Excel"; }
				else { titulo = "Imprimir en PDF"; }
				
				document.getElementById('AnalisisDeuda').style.display = 'block';
				document.getElementById('modalTitulo').innerHTML =  titulo; 
				document.getElementById("cliente").value = cliente;
				var y = document.getElementById("btnEvento").name = evento;
			}
		</script>
	</body>
</html>