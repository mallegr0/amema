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
						   msg = "La Generación de Intereses ha finalizado correctamente!!";
						   break;
			case "noAlta": clase = "w3-pale-red";
		   				   titulo = "Fracaso";
		   				   titulo = "Fracaso";
		   				   msg = "La Generación de Intereses ha fallado!!";
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