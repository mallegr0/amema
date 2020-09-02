<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Cliente" %>

	<h4 class="w3-center w3-text-indigo"> Listar Movimientos</h4>
	<br>
	<% Cliente s = (Cliente) request.getSession().getAttribute("socio"); %>
	<form action="/amema/Cuenta" method="post">
		<div class="w3-container">
			<div class="w3-container w3-half">
				<input type="text" name="socio" value="<%=s.getCODCLI()%> - <%=s.getNOMCLI()%>" readonly  style="width: 100%;">
			</div>
			<div class="w3-container w3-quarter">
				<input type="date" name="fecha" value="2050-01-01">
			</div>
			<div class="w3-container w3-quarter">
				<button class="fas fa-search fa-2x w3-button w3-green w3-hover-indigo" name="evento_buscar3"></button>
			</div>
			<br>
		</div>	
	</form>
	
	<% request.getSession().removeAttribute("socio");%>