<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
	String msj = (String) request.getSession().getAttribute("msj"); 
	if(msj != null){
		String clase = null;
		String titulo = null;
		String msg = null;
		
		switch(msj){
			case "siAlta": clase = "w3-pale-green";
						   titulo = "Éxito";
						   msg = "El Garante ha sido agregado al Movimiento Fijo";
						   break;
			case "noAlta": clase = "w3-pale-red";
		   				   titulo = "Fracaso";
		   				   titulo = "Fracaso";
		   				   msg = "El Garante no ha sido agregado al Movimiento Fijo";
		   				   break;
		   
		    case "siBaja": clase = "w3-pale-green";
		                   titulo = "Éxito";
		                   msg = "El Garante ha sido eliminado del Movimiento Fijo";
		                   break;
		    case "noBaja": clase = "w3-pale-red";
			    	       titulo = "Fracaso";	
			               msg = "El Garante no ha sido eliminado del Movimiento Fijo";
						   break;
			}%>
		<div class="w3-panel <%=clase %> w3-display-container">
			<span onclick="this.parentElement.style.display='none'" class="w3-button w3-large w3-display-topright">&times;</span>
			<h3><%=titulo %></h3>
			<p><%=msg %></p>
		</div>
		
	<%}
	request.getSession().removeAttribute("msj");
	%>