<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="controladores.CtrlConvenio"%>
<%@page import="entidades.Convenio"%>
<%@page import="java.util.ArrayList" %>

<%
	CtrlConvenio cc = new CtrlConvenio();
	ArrayList<Convenio> convenios = cc.listarConvenio();
%>
<h4 class="w3-pale-red">*Ingresar los datos correctamente, ya que pueden modificar los datos guardados previamente</h4>
<div class="w3-container w3-padding w3-card-4">
	<form action="/amema/GeneraTXT" method="post"> 
		<div class="w3-container w3-padding">
			<div class="w3-container w3-padding w3-half">
				<label><strong class="w3-center w3-text-indigo">Generar deuda al: </strong></label>
				<input type="Date" name="fecha" class="w3-input" required>
			</div>
			<div class="w3-container w3-padding w3-half">
				<label><strong class="w3-center w3-text-indigo">Convenio: </strong></label>
				<select class="w3-select" name="convenio">
					<%for (Convenio c : convenios) {%>
					<option value="<%=c.getCCOND()%>"><%=c.getCCOND()%> - <%=c.getDESCOND()%></option>
					<%}%>
				</select>
			</div>
		</div>
		<br>
		<div class="w3-container w3-padding w3-center">
			<button class="w3-button w3-green w3-hover-indigo" name="evento_generaTXT" onclick="abrospinner()">Generar TXT</button>
		</div>
	</form>
</div>