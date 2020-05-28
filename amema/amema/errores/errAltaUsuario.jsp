<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
	String msj = (String) request.getSession().getAttribute("msj"); 
	if(msj != null){
		if(msj == "OK"){%>
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
	<%}
	}
	request.getSession().removeAttribute("msj");
	%>