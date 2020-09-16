<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Cliente" %>
<%@page import="java.util.ArrayList" %>
	<h4 class="w3-center w3-text-indigo"> Selección de Socio</h4>
	<br>
	<% ArrayList<Cliente> lista = (ArrayList<Cliente>) request.getSession().getAttribute("lista"); %>
	<form action="/amema/ConvenioSocio" method="post">
		<div class="w3-container">
			<div class="w3-container w3-quarter"></div>
			<div class="w3-container w3-half">
				<select class="w3-select" name="socio">
					<option value="" disabled></option>
					<% for(Cliente c : lista){%>
						<option value="<%=c.getCODCLI()%>"><%=c.getCODCLI()%> - <%=c.getNOMCLI()%></option>
				<%}%>
				</select>
			</div>
			<div class="w3-container w3-rest">
				<button class="fas fa-search fa-2x w3-button w3-green w3-hover-indigo" name="evento_buscar2"></button>
			</div>
			<br>
		</div>	
	</form>