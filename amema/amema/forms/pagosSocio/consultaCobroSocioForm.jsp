<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.ReciboM" %>
<%@page import="java.util.ArrayList" %>

<% 
	ArrayList<ReciboM> recibos = (ArrayList<ReciboM>) request.getSession().getAttribute("recibos"); 

%>
<div class="w3-container w3-padding w3-card-4">
	<form action="/amema/CobroSocio" method="post">
		<div class="w3-container w3-padding w3-quarter" style="text-align: right;">
			<label class="w3-text-indigo"><strong>Nro de Recibo: </strong></label>
		</div>
		<div class="w3-container w3-padding w3-quarter">
			<input class="w3-input" type="text" name="tpoComp" value="0006" required>
		</div>
		<div class="w3-container w3-padding w3-quarter">
			<select class="w3-select" name="recibo" required>
				<%
				if(recibos != null) {
					for(ReciboM r: recibos){%>
				<option value="<%=r.getNRECIBO()%>"><%=r.getNRECIBO()%></option>
				<%}
					}%>
			</select>
		</div>
		<div class="w3-container w3-padding w3-quarter">
			<button class="w3-button w3-green w3-hover-indigo" name="evento_buscarCobro"><i class="fas fa-search fa-2x"></i></button> 
		</div>
	</form>
</div>

<% request.getSession().removeAttribute("recibos"); %>