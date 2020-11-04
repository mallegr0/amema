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
						   msg = "El Articulo ha sido agregado al Movimiento Fijo";
						   break;
			case "noAlta": clase = "w3-pale-red";
		   				   titulo = "Fracaso";
		   				   titulo = "Fracaso";
		   				   msg = "El Articulo no ha sido agregado al Movimiento Fijo";
		   				   break;
		   
		    case "siBaja": clase = "w3-pale-green";
		                   titulo = "Éxito";
		                   msg = "El Articulo ha sido eliminado del Movimiento Fijo";
		                   break;
		    case "noBaja": clase = "w3-pale-red";
			    	       titulo = "Fracaso";	
			               msg = "El Articulo no ha sido eliminado del Movimiento Fijo";
						   break;
			case "siModifica": clase = "w3-pale-green";
		                   titulo = "Éxito";
		                   msg = "El Articulo ha sido modificado en el Movimiento Fijo";
		                   break;
		    case "noModifica": clase = "w3-pale-red";
			    	       titulo = "Fracaso";	
			               msg = "El Articulo no ha sido modificado en el Movimiento Fijo";
						   break;
			case "siActualiza": clase = "w3-pale-green";
		                   titulo = "Éxito";
		                   msg = "Los Movimientos Fijos realizados en las fechas establecidas fueron actualizados";
		                   break;
		    case "noActualiza": clase = "w3-pale-red";
			    	       titulo = "Fracaso";	
			               msg = "Los Movimientos Fijos realizados en las fechas establecidas no fueron actualizados";
						   break;
			case "siElimina": clase = "w3-pale-green";
						   titulo = "Éxito";
						   msg = "El movimiento seleccionado fue eliminado";
						   break;
			case "noElimina": clase = "w3-pale-red";
						   titulo = "Fracaso";
						   msg = "El movimiento seleccionado no fue eliminado";
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