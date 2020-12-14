<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="controladores.CtrlConvenio" %>
<%@page import="controladores.CtrlPeriodoDeudaGen" %>
<%@page import="entidades.Convenio" %>
<%@page import="java.util.ArrayList" %>


<%
CtrlConvenio cc = new CtrlConvenio();
ArrayList<Convenio> convenios = cc.listarConvenio();

CtrlPeriodoDeudaGen cp = new CtrlPeriodoDeudaGen();
ArrayList<String> periodos = cp.listarPeriodos();%>
<form action="/amema/IOMasivo" method="post">
	<fieldset><legend class="w3-text-indigo"><strong>  Generación de Recibos  </strong></legend>
		<div class="w3-container w3-padding w3-half">
			<div class="w3-container w3-padding w3-twothird">
				<label class="w3-center"><strong class="w3-text-indigo">Pagos correspondiente al Período: </strong></label>
			</div>
			<div class="w3-container w3-padding w3-rest">
				<select class="w3-select" name="periodo">
					<%for(String p: periodos){%>
					<option value="<%=p%>"><%=p%></option>	
					<%}%>
				</select>
			</div>
		</div>
		<div class="w3-container w3-padding w3-half">
			<div class="w3-container w3-padding w3-quarter">
				<label class="w3-center"><strong class="w3-text-indigo">Convenio: </strong></label>
			</div>
			<div class="w3-container w3-padding w3-rest">
				<select class="w3-select" name="convenio">
					<%for(Convenio con: convenios){%>
					<option value="<%=con.getCCOND()%>"><%=con.getCCOND()%> - <%=con.getDESCOND()%></option>
					<%}%>
				</select>
			</div>
		</div>
		<div class="w3-container w3-padding w3-half">
			<div class="w3-container w3-padding w3-half">
					<label><strong class="w3-text-indigo">Genera Recibos con Fecha: </strong></label>
				</div>
				<div class="w3-container w3-padding w3-rest">
					<input type="Date" name="fecha" class="w3-input">
				</div>
			</div>
		<div class="w3-container w3-padding w3-half">
			<button class="w3-button w3-green w3-hover-indigo" name="evento_generaRecibos" onclick="abrospinner()"><strong>ACEPTAR</strong></button>
		</div>
	</fieldset>
</form>

<% 
	cc = null; 
	cp = null; 
%>