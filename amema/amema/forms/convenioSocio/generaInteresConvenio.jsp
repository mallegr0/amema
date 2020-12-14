<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="controladores.CtrlConvenio" %>
<%@page import="entidades.Convenio" %>
<%@page import="java.util.ArrayList" %>

<%
	CtrlConvenio cc = new CtrlConvenio();
	ArrayList<Convenio> convenios = cc.listarConvenio();
%>
<form action="/amema/GeneraInteres" method="post">
	<div class="w3-container w3-padding w3-card-4">
		<br>
		<h3 class="w3-center w3-text-indigo"> Generar Intereses por Convenio </h3>
		<div class="w3-container w3-padding">
			<div class="w3-container w3-padding w3-half">
				<div class="w3-container w3-padding w3-twothird">
					<label><strong class="w3-text-indigo w3-right">Generar Intereses con deuda al: </strong></label>
				</div>
				<div class="w3-container w3-padding w3-rest">
					<input type="text" name="periodo" class="w3-input w3-left" placeholder="Periodo" required>
				</div>
			</div>
			<div class="w3-container w3-padding w3-half">
				<div class="w3-container w3-padding w3-quarter">
					<label><strong class="w3-text-indigo w3-right">Convenio: </strong></label>
				</div>
				<div class="w3-container w3-padding w3-half">
					<select class="w3-select w3-left" name="convenio">
						<%for(Convenio c: convenios){%>
						<option value="<%=c.getCCOND()%>"><%=c.getCCOND()%> - <%=c.getDESCOND()%></option>
						<%}%>
					</select>
				</div>
			</div>
		</div>
		<div class="w3-container w3-padding">
			<div class="w3-container w3-padding w3-half">
				<div class="w3-container w3-padding w3-twothird">
					<label><strong class="w3-text-indigo w3-right">Generar movimientos con fecha: </strong></label>
				</div>
				<div class="w3-container w3-padding w3-rest">
					<input type="Date" name="fecMovimiento" class="w3-input w3-left" required>
				</div>
			</div>
			<div class="w3-container w3-padding w3-half">
				<div class="w3-container w3-padding w3-twothird">
					<label><strong class="w3-text-indigo w3-right">Generar movimientos con prioridad: </strong></label>
				</div>
				<div class="w3-container w3-padding w3-rest">
					<input type="text" name="prioridad" class="w3-input w3-left" required>
				</div>
			</div>
		</div>
		<br>
		<div class="w3-container w3-padding w3-center">
			<button class="w3-button w3-green w3-hover-indigo" name="evento_GeneraInteres"><strong>Generar interes</strong></button>
		</div>
	</div>
</form>