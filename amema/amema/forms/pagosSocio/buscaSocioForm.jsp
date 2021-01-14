<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="controladores.CtrlCliente" %>
<%@page import="entidades.Cliente" %>
<%@page import="java.util.ArrayList" %>


<div class="w3-container w3-padding w3-card-4">
	<form action="/amema/PagoSocio" method="post">
		<div class="w3-container">
			<div class="w3-container w3-padding w3-third">
				<input type="text" id="search" name="search" class="w3-input" onkeyup="filter(this.value)">
			</div>
			<div class="w3-container w3-padding w3-third">
			    <select id="select" name="select" class="w3-select" size="5" required>
			    <%
			    CtrlCliente c = new CtrlCliente();
			    ArrayList<Cliente> lista = c.listarCliente();
			    for(Cliente l : lista){ %>
				    <option value="<%=l.getCODCLI()%>"><%=l.getNOMCLI() %></option>
				<%} %>
			    </select>
			</div>
			<div class="w3-container w3-padding w3-third">
				<button class="w3-button w3-green w3-hover-indigo" name="evento_buscarSocio"><i class="fas fa-search fa-2x"></i></button>
			</div>
		</div>
	</form>
</div>