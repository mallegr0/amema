<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="controladores.CtrlConvenio"%>
<%@page import="entidades.Convenio" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%
	CtrlConvenio cConvenio = new CtrlConvenio();
	ArrayList<Convenio> convenios = cConvenio.listarConvenio();
	Date fecha = new Date();
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
%>
<div class="w3-container w3-padding w3-card-4">
	<form action="/amema/AnalisisSaldo" name="AnalisisSaldo" method="post">
		<div class="w3-container w3-padding">
			<div class="w3-container w3-padding w3-third">
				<label class="w3-text-indigo">
					<strong>Listado de saldos deudores de socios al: </strong>
				</label>
			</div>
			<div class="w3-container w3-padding w3-quarter">
				<input class="w3-input" type="Date" name="fecha" value="<%=sdFormat.format(fecha) %>" required>
			</div>
		</div>
		<div class="w3-container w3-padding">
			<div class="w3-container w3-padding w3-half">
				<label class="w3-text-indigo">
					<strong>Desea listar a todos los Socios: </strong>
				</label>
				<input class="w3-check" type="checkbox" name="socios" id="socios" onclick="validoOpcion()">
			</div>
			<div class="w3-container w3-padding w3-half">
				<label class="w3-text-indigo">
					<strong>Listar solo los socios con convenio: </strong>
					<input type="checkbox" id="ckConvenio" class="w3-check" onclick="validoOpcion()">
				</label>
				<select class="w3-select" name="convenio" id="sltConvenio">
					<option value="todos">Todos los convenios</option>
					<%for(Convenio c: convenios) {%>
						<option value="<%=c.getCCOND()%>"><%=c.getCCOND()%> - <%=c.getDESCOND()%></option>
					<%} %>
				</select>
			</div>
		</div>
		<br>
		<div class="w3-container w3-padding w3-center">
			<button class="w3-button w3-green w3-hover-indigo" name="evento_buscarSaldos" onclick="abrospinner()">
				<i class="fas fa-search fa-2x"></i>
			</button></div>
	</form>
</div>

<script type="text/javascript">
	function validoOpcion(){
		checkSocio = document.getElementById("socios");
		checkConvenio = document.getElementById("ckConvenio");
		select = document.getElementById("sltConvenio");

		if (checkSocio.checked == true) {
			checkConvenio.disabled = true;
			checkConvenio.checked = false;
			select.disabled = true;
		}
		if(checkConvenio.checked == true) {
			checkSocio.disabled = true; 
			checkSocio.checked = false;
		}
		if (checkSocio.checked == false) {
			checkConvenio.disabled = false;
			select.disabled = false;
		}
		if(checkConvenio.checked == false) {
			checkSocio.disabled = false; 
		}
	}
</script>