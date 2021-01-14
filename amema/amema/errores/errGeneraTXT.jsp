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
						   msg = "La Generación del TXT ha finalizado correctamente!!";
						   break;
			case "noAlta": clase = "w3-pale-red";
		   				   titulo = "Fracaso";
		   				   msg = "La actualización de la Base de Datos para la generación del txt ha fallado!!";
		   				   break;
			case "no2109": clase = "w3-pale-red";
		   				   titulo = "Fracaso";
		   				   msg = "La Generación del txt ENTI2109 ha fallado!!";
		   				   break;
			case "no2206": clase = "w3-pale-red";
		   				   titulo = "Fracaso";
		   				   msg = "La Generación del txt ENTI2206 ha fallado!!";
		   				   break;
			case "no3000": clase = "w3-pale-red";
						   titulo = "Fracaso";
						   msg = "La Generación del txt ENTI3000 ha fallado!!";
						   break;
			case "no0": clase = "w3-pale-red";
						   titulo = "Fracaso";
						   msg = "La Generación del txt de Jubilados ha fallado!!";
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