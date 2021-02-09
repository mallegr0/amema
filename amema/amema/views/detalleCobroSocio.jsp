<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="entidades.Cliente" %>
<%@page import="entidades.ReciboM" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DecimalFormat" %>
<%@page import="java.util.ArrayList" %>
<%@page import="entidades.DatosCobroFact" %>
<%@page import="entidades.DatosCobroGral" %>


<!DOCTYPE html>
<html>

	<head>
		<title>AMEMA web - Detalle de consulta cobro a Socio</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<% 
			ReciboM rec = (ReciboM) request.getSession().getAttribute("dataRecibo");
			Cliente cli = (Cliente) request.getSession().getAttribute("dataCliente"); 
			ArrayList<DatosCobroGral> anticipos = (ArrayList<DatosCobroGral>) request.getSession().getAttribute("dataAnticipos");
			ArrayList<DatosCobroGral> creditos = (ArrayList<DatosCobroGral>) request.getSession().getAttribute("dataCreditos");
			ArrayList<DatosCobroFact> facturas = (ArrayList<DatosCobroFact>) request.getSession().getAttribute("dataFacturas");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			DecimalFormat dformat = new DecimalFormat("#0.00");
			String txtTipoPago, txtProv, txtciva, txtRecibo, fecImputacion, impEfec;
			String impCheque, impActa, impOp, impTotal; 
			double totalF = 0;
			double descF = 0;
			double retenF = 0; 
			double totalA = 0;
			double totalC = 0; 
			
			if(rec != null){
				txtRecibo = rec.getPREFIJO()+" - "+rec.getNRECIBO();
				txtTipoPago = rec.getTRECIBO();
				fecImputacion = format.format(rec.getFRECIBO());
				txtProv = cli.getCODCLI()+"-"+cli.getNOMCLI();
				txtciva = cli.getREGCLI();
				impEfec = dformat.format(rec.getEFECTIVO());
				impCheque = dformat.format(rec.getCHEQUE()); 
				impActa = dformat.format(rec.getA_CTA()); 
				impOp = dformat.format(0);
				impTotal = dformat.format(rec.getEFECTIVO()+rec.getCHEQUE()+rec.getA_CTA());
			}
			else { 
				txtRecibo = "S/D";
				txtTipoPago = "S/D";
				fecImputacion = "S/D";
				txtProv = "S/D";
				txtciva = "S/D";
				impEfec = dformat.format(0);
				impCheque = dformat.format(0); 
				impActa = dformat.format(0); 
				impOp = dformat.format(0);
				impTotal = dformat.format(0);
			}
			
			//calculo los totales
			if(facturas != null) {
				for(DatosCobroFact f: facturas){
					totalF += f.getImppagado();
					descF += f.getDescuento();
				}
			}
			
			if(anticipos != null) {
				for(DatosCobroGral a: anticipos){
					totalA += Double.parseDouble(a.getImporte());
				}
			}
			
			if(creditos != null) {
				for(DatosCobroGral c: creditos){
					totalC += Double.parseDouble(c.getImporte());
				}
			}
		%>
		<!-- AREA DEL MENU -->
		<%@ include file="menu.jsp"%>
		<div class="w3-container w3-padding-64">
			<div class="w3-container w3-padding w3-card-4">
				<h3 class="w3-text-indigo w3-center"><strong>Detalle del cobro</strong></h3>
			</div> 
			<br>
			<div class="w3-container w3-padding w3-card-4">
				<div class="w3-container w3-padding">
					<div class="w3-container w3-padding w3-quarter">
						<label class="w3-text-indigo"><strong>Nro Recibo: </strong></label>
						<input id="recibo" type="text" name="nrecibo" class="w3-input" value="<%=txtRecibo %>" readonly>
					</div>
					<div class="w3-container w3-padding w3-quarter">
						<label class="w3-text-indigo"><strong>Tipo de Pago: </strong></label>
						<input type="text" class="w3-input" value="<%=txtTipoPago%>" readonly>
					</div>
					<div class="w3-container w3-padding w3-quarter">
						<label class="w3-text-indigo"><strong>Fecha de Imputacion: </strong></label>
						<input type="text" class="w3-input" value="<%=fecImputacion%>" readonly>
					</div>
					<div class="w3-container w3-padding w3-quarter">
						<label class="w3-text-indigo"><strong>Fecha de Pago: </strong></label>
						<input type="text" class="w3-input" value="<%=fecImputacion%>" readonly>
					</div>
				</div>
				<div class="w3-container w3-padding">
					<div class="w3-container w3-padding w3-quarter">
						<label class="w3-text-indigo"><strong>Cliente: </strong></label>
						<input type="text" class="w3-input" value="<%=txtProv%>" readonly>
					</div>
					<div class="w3-container w3-padding w3-quarter">
						<label class="w3-text-indigo"><strong>Cond. IVA: </strong></label>
						<input type="text" class="w3-input" value="<%=txtciva%>" readonly>
					</div>
				</div>
			</div>
			<br>
			<div class="w3-container w3-padding w3-card-4">
				<h3 class="w3-text-indigo w3-center"><strong>DATOS DEL PAGO</strong></h3>
				<br>
				<div class="w3-container w3-padding">
					<div class="w3-container w3-padding w3-quarter">
						<label class="w3-text-indigo"><strong>Importe en Efectivo: </strong></label>
						<input type="text" class="w3-input" value="$<%=impEfec%>" readonly>
					</div>
					<div class="w3-container w3-padding w3-quarter">
						<label class="w3-text-indigo"><strong>Importe en Cheques: </strong></label>
						<input type="text" class="w3-input" value="$<%=impCheque%>" readonly>
					</div>
					<div class="w3-container w3-padding w3-quarter">
						<label class="w3-text-indigo"><strong>A cuenta: </strong></label>
						<input type="text" class="w3-input" value="$<%=impActa%>" readonly>
					</div>
					<div class="w3-container w3-padding w3-quarter">
						<label class="w3-text-indigo"><strong>Aplicado: </strong></label>
						<input type="text" class="w3-input" value="$<%=impOp%>" readonly>
					</div>
				</div>
				<div class="w3-container w3-padding">
					<div class="w3-container w3-padding w3-quarter">
						<label class="w3-text-indigo" style="text-decoration: underline;"><strong>Total Pago: </strong></label>
						<input type="text" class="w3-input" value="$<%=impTotal%>" readonly>
					</div>
				</div>
			</div>
			<br>
			<div class="w3-container w3-padding w3-card-4">
				<h3 class="w3-center w3-text-indigo"><strong>En concepto de </strong></h3>
				<br>
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo w3-center">
							<td><strong>Tpo Comprobante</strong></td>
							<td><strong>Letra</strong></td>
							<td><strong>Prefijo</strong></td>
							<td><strong>Nro Comprobante</strong></td>
							<td><strong>Fecha</strong></td>
							<td><strong>Imp. Pagado</strong></td>
							<td><strong>Descuento</strong></td>
							<td><strong>Tasa</strong></td>
							<td><strong>Observaciones</strong></td>
						</tr>
					</thead>
					<tbody>
					<%if(facturas != null) {
						for(DatosCobroFact f: facturas){ %>
						<tr>
							<td><%=f.getTcomp() %></td>
							<td><%=f.getLetra() %></td>
							<td><%=f.getPrefijo() %></td>
							<td><%=f.getNcomp() %></td>
							<td><%=format.format(f.getFecha()) %></td>
							<td>$<%=dformat.format(f.getImppagado()) %></td>
							<td>$<%=dformat.format(f.getDescuento()) %></td>
							<td>$<%=dformat.format(f.getTasa()) %></td>
							<td><%=f.getObs() %></td>
						</tr>
						<%}
						}else {%>
						<tr>
							<td colspan="9">
								<h3 class="w3-text-indigo w3-center"><strong>No hay datos para mostrar</strong></h3>
							</td>
						</tr>
						<%} %>
						<tr>
							<td colspan="8" class="w3-right-align w3-text-indigo"><strong>Total de Comprobante: 
							</strong></td>
							<td><strong>$<%=dformat.format(totalF) %></strong></td>
						</tr>
						<tr>
							<td colspan="8" class="w3-right-align w3-text-indigo"><strong>Descuentos Efectuados: 
							</strong></td>
							<td><strong>$<%=dformat.format(descF) %></strong></td>
						</tr>
						<tr>
							<td colspan="8" class="w3-right-align w3-text-indigo"><strong>Retenciones Aplicadas: 
							</strong></td>
							<td><strong>$<%=dformat.format(retenF) %></strong></td>
						</tr>
					</tbody>
				</table>
			</div>
			<br>
			<div class="w3-container w3-padding w3-card-4">
				<h3 class="w3-text-indigo w3-center"><strong>Notas del Crédito Descontadas</strong></h3>
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo w3-center">
							<td><strong>Nro comprobante</strong></td>
							<td><strong>Fecha</strong></td>
							<td><strong>Importe Aplicado</strong></td>
						</tr>
					</thead>
					<tbody>
					<%if(creditos != null){
						for(DatosCobroGral c: creditos){%>
						<tr>
							<td><%=c.getnRecibo() %></td>
							<td><%=c.getFecha() %></td>
							<td>$<%=c.getImporte() %></td>
						</tr>
						<%}
						} else {%>
						<tr> 
							<td colspan="3">
								<h3 class="w3-text-indigo w3-center"><strong>No hay Créditos para mostrar</strong></h3>
							</td>
						</tr>
						<%} %>
						<tr>
							<td colspan="2" class="w3-right-align w3-text-indigo"><strong>Total N. Cred. Descontadas: </strong></td>
							<td><strong>$<%=dformat.format(totalC) %></strong></td>
						</tr>
					</tbody>
				</table>
			</div>
			<br>
			<div class="w3-container w3-padding w3-card-4">
				<h3 class="w3-text-indigo w3-center"><strong>Total Anticipos</strong></h3>
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo w3-center">
							<td><strong>Nro Recibo</strong></td>
							<td><strong>Fecha</strong></td>
							<td><strong>Importe Aplicado</strong></td>
						</tr>
					</thead>
					<tbody>
					<%if(anticipos != null){
						for(DatosCobroGral a: anticipos){%>
						<tr>
							<td><%=a.getnRecibo() %></td>
							<td><%=a.getFecha() %></td>
							<td>$<%=a.getImporte() %></td>
						</tr>
						<%}
						} else {%>
						<tr> 
							<td colspan="3">
								<h3 class="w3-text-indigo w3-center"><strong>No hay Anticipos para mostrar</strong></h3>
							</td>
						</tr>
						<%} %>
						<tr>
							<td colspan="2" class="w3-right-align w3-text-indigo"><strong>Total Anticipos Aplicados: </strong></td>
							<td><strong>$<%=dformat.format(totalA) %></strong></td>
						</tr>
					</tbody>
				</table>
			</div>			
			<br>
			<div class="w3-container w3-padding w3-card-4 w3-center">
				<a href="../views/consultaCobroSocio.jsp" class="w3-button w3-red w3-hover-indigo"><strong>Volver</strong></a>
			</div>
		</div>
		<!-- AREA DEL FOOTER -->
		<%@ include file="footer.jsp"%>
		<%
			request.getSession().removeAttribute("dataRecibo");
			request.getSession().removeAttribute("dataCliente"); 
			request.getSession().removeAttribute("dataAnticipos");
			request.getSession().removeAttribute("dataCreditos");
			request.getSession().removeAttribute("dataFacturas");
		%>
	</body>
</html>