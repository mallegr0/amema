<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="controladores.CtrlConvenio" %>
<%@page import="entidades.Convenio" %>
<%@page import="java.util.ArrayList" %>


<%
CtrlConvenio cc = new CtrlConvenio();
ArrayList<Convenio> convenios = cc.listarConvenio();%>
<form>
	<fieldset><legend class="w3-text-indigo"><strong>  Importación del Archivo  </strong></legend>
		<div class="w3-container w3-padding w3-half">
			<div class="w3-container w3-padding w3-third">
				<label class="w3-center"><strong class="w3-text-indigo">Nombe del Archivo: </strong></label>
			</div>
			<div class="w3-container w3-padding w3-rest">
				<input type="text" id="texto" class="w3-input" onchange="completaRuta()">
			</div>
		</div>
		<div class="w3-container w3-padding w3-half">
			<div class="w3-container w3-padding w3-quarter">
				<label class="w3-center"><strong class="w3-text-indigo">Carpeta: </strong></label>
			</div>
			<div class="w3-container w3-padding w3-rest">
				<input type="text" id="ruta" class="w3-input" readonly>
			</div>
		</div>
		<div class="w3-container w3-padding w3-half">
			<div class="w3-container w3-padding w3-twothird">
				<label class="w3-center"><strong class="w3-text-indigo">Pagos correspondiente al Período: </strong></label>
			</div>
			<div class="w3-container w3-padding w3-rest">
				<input type="text" name="periodo" class="w3-input">
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
			<div class="w3-container w3-padding w3-third">
				<label class="w3-center"><strong class="w3-text-indigo">Leer Archivo en modo: </strong></label>
			</div>
			<div class="w3-container w3-padding w3-rest">
				<select class="w3-select" name="convenio">
					<option value="municipalidad">Municipalidad</option>
					<option value="jubilados">Jubilados</option>
				</select>
			</div>
		</div>
		<div class="w3-container w3-padding w3-half">
			<button class="w3-button w3-green w3-hover-indigo" name="evento_actualizaMasivo"><strong>ACEPTAR</strong></button>
		</div>
	</fieldset>
</form>