<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Cliente" %>
<%@page import="entidades.Venta" %>
<%@page import="controladores.CtrlCliente" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	

<div class="w3-container w3-padding">
	<h3 class="w3-center"><strong class="w3-text-indigo">Busqueda de los movimientos del socio</strong></h3>
	<br>

	<% 
		String socio = (String) request.getSession().getAttribute("socio");
		if(socio == null){
			CtrlCliente c = new CtrlCliente();
		    ArrayList<Cliente> lista = c.listarCliente();
   	%>
					
	<div class="w3-card-4">
		<form action="/amema/MovimientoFijo" method="post">
			<div class="w3-container w3-padding">
				<div class="w3-container w3-padding w3-quarter">
					<input type="text" id="search" class="w3-input" onkeyup="filter(this.value)">
				</div>
				<div class="w3-container w3-padding w3-half">
					<select class="w3-select" name="select" id="select" size="5" required>
					<% for(Cliente l : lista){ %>
	        			<option value="<%=l.getCODCLI()%>-<%=l.getNOMCLI() %>"><%=l.getNOMCLI() %></option>
	            	<%} %>
					</select>
				</div>
				<div class="w3-container w3-padding w3-quarter">
					<button class="w3-button w3-green w3-hover-indigo" name="evento_eliminaBuscar1"><i class="fas fa-search fa-2x"></i></button>
				</div>
			</div>
		</form>
	</div>
	<%}
	else {
		ArrayList<Venta> ventas = (ArrayList<Venta>) request.getSession().getAttribute("ventas");%>
	<form action="/amema/MovimientoFijo" method="post">
		<div class="w3-container w3-padding w3-card-4">
			<div class="w3-container w3-padding w3-third">
				<input type="text" name="nombreSocio" class="w3-input" value="<%=socio%>" readonly>
			</div>
			<div class="w3-container w3-padding w3-third">
				<select class="w3-select" name="nroMov">
				<%for(Venta v: ventas){%>
					<option value="<%=v.getNROMOV()%>"><%=v.getNROMOV()%></option>
				<%}%>
				</select>
			</div>
			<div class="w3-container w3-padding w3-third">
				<button class="w3-button w3-green w3-hover-indigo" name="evento_detalle"><i class="fas fa-search fa-2x"></i></button>
			</div>
		</div>
	</form>
	<%}%>
</div>


<script>
	function filter( keyword) {
		var select = document.getElementById("select");
		select.show = true;
		for (var i = 0; i < select.length; i++) {
		    var txt = select.options[i].text;
		    var include = txt.toLowerCase().startsWith(keyword.toLowerCase());
		    select.options[i].style.display = include ? 'list-item':'none';
		}
	}
</script>
<% request.getSession().removeAttribute("socio");
   request.getSession().removeAttribute("ventas");%>
			
