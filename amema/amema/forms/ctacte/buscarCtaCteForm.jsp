<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="controladores.CtrlCliente" %>
<%@page import="entidades.Cliente" %>
<%@page import="java.util.ArrayList" %>
	

	<% 
		CtrlCliente cc = new CtrlCliente();
		ArrayList<Cliente> lista = cc.listarCliente();%>
	<h4 class="w3-center w3-text-indigo"> Búsqueda de Cuenta</h4>
	<form action="/amema/Cuenta" method="post">
		<div class="w3-container">
			<div class="w3-container w3-half">
				<select class="w3-select" name="socio">
					<option value="" disabled>...</option>
					<% for (Cliente c : lista) {%>
					<option value="<%=c.getCODCLI()%>"><%=c.getCODCLI()%> - <%=c.getNOMCLI()%></option>
					<%}%>
				</select>
			</div>
			<div class="w3-container w3-quarter">
				<input type="date" name="fecha" class="w3-input" >
			</div>
			<div class="w3-container w3-quarter">
				<br>
				<button class="fas fa-search fa-2x w3-button w3-green w3-hover-indigo" name="evento_buscar1"></button>
			</div>
		</div>	
	</form>