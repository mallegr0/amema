<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
	String msj = (String) request.getSession().getAttribute("msg"); 
	if(msj != null){
		String clase = null;
		String titulo = null;
		String msg = null;
		
		switch(msj){
			case "noPass": clase = "w3-pale-yellow";
						   titulo = "Fracaso";
						   msg = "El usuario o la Contraseña es incorrecta!!";
						   break;
			case "siLogout": clase = "w3-pale-green";
		   				   titulo = "Exito";
		   				   titulo = "Exito";
		   				   msg = "La sesión se ha cerrado correctamente!!";
		   				   break;
			}%>
		<div class="w3-panel <%=clase %> w3-display-container">
			<span onclick="this.parentElement.style.display='none'" class="w3-button w3-large w3-display-topright">&times;</span>
			<h3><%=titulo %></h3>
			<p><%=msg %></p>
		</div>
		
	<%}
	request.getSession().removeAttribute("msg");
	%>