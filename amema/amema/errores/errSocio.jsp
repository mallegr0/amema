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
						   msg = "El usuario ha sido creado correctamente";
						   break;
			case "noAlta": clase = "w3-pale-red";
		   				   titulo = "Fracaso";
		   				   msg = "El usuario no se ha podido crear";
		   				   break;
		   
		    case "siBaja": clase = "w3-pale-green";
		                   titulo = "Éxito";
		                   msg = "El usuario ha sido eliminado correctamente";
		                   break;
		    case "noBaja": clase = "w3-pale-red";
			    	       titulo = "Fracaso";	
			               msg = "El usuario no se ha podido eliminar";
						   break;
			case "siModifica": clase = "w3-pale-green";
						       titulo = "Éxito";
							   msg = "El usuario ha sido modificado correctamente";
						       break;
			case "noModifica": clase = "w3-pale-red";
			                   titulo = "Fracaso";
			                   msg = "El usuario no se ha podido modificar";
			                   break;
			case "siPass": clase = "w3-pale-green";
		                   titulo = "Éxito";
			               msg = "La contraseña se cambio correctamente";
			               break;
			case "noPass": clase = "w3-pale-red";
            			   titulo = "Fracaso";
                           msg = "La contraseña no se cambio";
            break;
			}%>
		<div class="w3-panel <%=clase %> w3-display-container w3-round-xlarge">
			<span onclick="this.parentElement.style.display='none'" class="w3-button w3-large w3-display-topright">&times;</span>
			<h3><%=titulo %></h3>
			<p><%=msg %></p>
		</div>
		
		
		
		
		<%--if(msj == "OK"){%>
			<div class="w3-panel w3-pale-green w3-display-container w3-round-xlarge">
  				<span onclick="this.parentElement.style.display='none'" class="w3-button w3-large w3-display-topright">&times;</span>
  				<h3>Éxito!</h3>
  				<p>El usuario ha sido creado correctamente</p>
			</div>
	<%}
		else
		{%>
			<div class="w3-panel w3-pale-red w3-display-container w3-round-xlarge">
  				<span onclick="this.parentElement.style.display='none'" class="w3-button w3-large w3-display-topright">&times;</span>
  				<h3>Error!</h3>
  				<p>El usuario no ha sido creado correctamente</p>
			</div>
	<%}--%>
	<%}
	request.getSession().removeAttribute("msj");
	%>