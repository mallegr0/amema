<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Convenio"%>
<%@page import="controladores.CtrlConvenio"%>
<%@page import="entidades.PeriodoDeudaGen"%>
<%@page import="controladores.CtrlPeriodoDeudaGen"%>


<% 
	CtrlConvenio cConvenio = new CtrlConvenio();
	ArrayList<Convenio> convenios = cConvenio.listarConvenio();
	CtrlPeriodoDeudaGen cPeriodoGen = new CtrlPeriodoDeudaGen();
	ArrayList<String> periodos = cPeriodoGen.listarPeriodos();
%>

<div class="w3-container w3-padding-64 w3-card-4">
	<h3 class="w3-center w3-text-indigo"><strong>Parámetros para Listar los datos ingresados</strong></h3>
	<br>
	<form action="/amema/IOMasivo" method="post">
		<div class="w3-container w3-padding w3-third">
			<label class="w3-center"><strong class="w3-text-indigo">Seleccione el periodo: </strong></label>
			<select class="w3-select" name="periodo">
				<% for (String p: periodos){%>
				<option value="<%=p%>"><%=p%></option>
				<% }%>
			</select>
		</div>
		<div class="w3-container w3-padding w3-third">
			<label class="w3-center"><strong class="w3-text-indigo">Seleccione el convenio: </strong></label>
			<select class="w3-select" name="convenio">
				<%for(Convenio c: convenios){%>
				<option value="<%=c.getCCOND()%>"><%=c.getCCOND()%> - <%=c.getDESCOND()%></option>
				<%}%>
				<option value="999">999 - TODOS LOS CONVENIOS</option>
			</select>
		</div>
		<div class="w3-container w3-padding w3-third">
			<button class="w3-button w3-green w3-hover-indigo" name="evento_buscar"><i class="fas fa-search fa-2x"></i></button>
		</div>
	</form>
</div>

<%
	cConvenio = null; 
	cPeriodoGen = null; 
%>