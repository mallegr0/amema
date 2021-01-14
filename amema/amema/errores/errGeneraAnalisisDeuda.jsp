<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
	String msj = (String) request.getSession().getAttribute("msj"); 
	if(msj != null){
		String clase = null;
		String titulo = null;
		String msg = null;
		
		switch(msj){
			case "siExcel": clase = "w3-pale-green";
						   titulo = "Éxito";
						   msg = "La Generación del archivo de Excel fue correcta!!";
						   break;
			case "noExcel": clase = "w3-pale-red";
		   				   titulo = "Fracaso";
		   				   titulo = "Fracaso";
		   				   msg = "La Generación del archivo de Excel ha fallado!!";
		   				   break;
		   	case "siPDF": clase = "w3-pale-green";
						   titulo = "Éxito";
						   msg = "La Generación del archivo PDF fue correcta!!";
						   break;
			case "noPDF": clase = "w3-pale-red";
		   				   titulo = "Fracaso";
		   				   titulo = "Fracaso";
		   				   msg = "La Generación del archivo PDF ha fallado!!";
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