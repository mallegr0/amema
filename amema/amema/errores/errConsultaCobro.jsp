<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
	String msj = (String) request.getSession().getAttribute("msg"); 
	if(msj != null){
		String clase = null;
		String titulo = null;
		String msg = null;
		
		switch(msj){
			case "siBaja": clase = "w3-pale-green";
						   titulo = "Exito";
						   msg = "El cobro a sido eliminado correctamente!!";
						   break;
			case "noBaja": clase = "w3-pale-red";
		   				   titulo = "Fracaso";
		   				   titulo = "Fracaso";
		   				   msg = "El cobro no ha sido eliminado!!";
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