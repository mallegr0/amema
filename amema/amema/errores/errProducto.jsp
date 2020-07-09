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
						   msg = "La Familia ha sido creado correctamente";
						   break;
			case "noAlta": clase = "w3-pale-red";
		   				   titulo = "Fracaso";
		   				   msg = "La Familia no se ha podido crear";
		   				   break;
		   
		    case "siBaja": clase = "w3-pale-green";
		                   titulo = "Éxito";
		                   msg = "La Familia ha sido eliminado correctamente";
		                   break;
		    case "noBaja": clase = "w3-pale-red";
			    	       titulo = "Fracaso";	
			               msg = "La Familia no se ha podido eliminar";
						   break;
			case "siModifica": clase = "w3-pale-green";
						       titulo = "Éxito";
							   msg = "La Familia ha sido modificado correctamente";
						       break;
			case "noModifica": clase = "w3-pale-red";
			                   titulo = "Fracaso";
			                   msg = "La Familia no se ha podido modificar";
			                   break;
			}%>
		<div class="w3-panel <%=clase %> w3-display-container w3-round-xlarge">
			<span onclick="this.parentElement.style.display='none'" class="w3-button w3-large w3-display-topright w3-round-xlarge">&times;</span>
			<h3><%=titulo %></h3>
			<p><%=msg %></p>
		</div>
		
	<%}
	request.getSession().removeAttribute("msj");
	%>