<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date" %>
<%@page import="controladores.CtrlCliente" %>
<%@page import="entidades.Cliente" %>
<%@page import="entidades.ReciboM" %>


<%
	ReciboM rec = (ReciboM) request.getSession().getAttribute("dataRecibo");
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat dformat = new DecimalFormat("#0.00");
	String txtTipoPago, txtProv, txtciva, txtRecibo, fecImputacion, impEfec;
	String impCheque, impActa, impOp, impTotal; 
	CtrlCliente cCliente = new CtrlCliente();
			
	if(rec != null){
		txtRecibo = rec.getPREFIJO()+" - "+rec.getNRECIBO();
		txtTipoPago = rec.getTRECIBO();
		fecImputacion = format.format(rec.getFRECIBO());
		Cliente cli = cCliente.consultaCliente(rec.getCODCLI()); 
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

%>

<form action="/amema/CobroSocio" method="post">
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
		<div class="w3-container w3-padding w3-third w3-center">
			<button id="btn1" class="w3-button w3-green w3-hover-indigo" name="evento_VerDetalle" disabled>
				<i class="fas fa-search fa-2x"></i>
			</button>
		</div>
		<div class="w3-container w3-padding w3-third w3-center">
			<button id="btn2" class="w3-button w3-red w3-hover-indigo" name="evento_eliminarPago" disabled>
				<i class="fas fa-times fa-2x"></i>
			</button>
		</div>
		<div class="w3-container w3-padding w3-third w3-center">
			<button id="btn3" class="w3-button w3-light-blue w3-hover-indigo" name="evento_Imprimir" disabled>
				<i class="fas fa-print fa-2x"></i>
			</button>
		</div>
	</div>
</form>


<%
	request.getSession().removeAttribute("dataRecibo");
%>
