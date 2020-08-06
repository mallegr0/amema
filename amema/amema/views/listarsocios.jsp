<%@page import="java.util.ArrayList"%>
<%@page import="controladores.CtrlConvenio"%>
<%@page import="entidades.Convenio" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>AMEMA web - Listado de Socio</title>
	<%@ include file="../meta/metadata.jsp"%>
</head>
<body>

	<% 
		CtrlConvenio cc = new CtrlConvenio();
		ArrayList<Convenio> lc = cc.listarConvenio();
	%>

	/*AREA DEL MENU*/
	<%@ include file="../views/menu.jsp" %>
	<br>
	<div class="w3-container w3-padding-64 w3-card-4">
	
		<!-- Error -->
		<% String msg = (String) request.getSession().getAttribute("msj"); %>
		<% if(msg != null){ %>
			<div class="w3-panel w3-green w3-display-container w3-rounded">
	  			<span onclick="this.parentElement.style.display='none'" class="w3-button w3-large w3-display-topright">&times;</span>
	  			<p class="w3-center"><Strong><%=msg  %></Strong></p>
			</div>
		<%} %>
	
		<form method="post" action="/amema/ListarSocio"> 
			<div class="w3-container w3-padding-64 w3-card-4">
				<div class="w3-half w3-container">
					<input type="checkbox" name="activos" class="w3-check" onclick="habilitar('activos')" id="activos"> <label> Listar todos los socios Activos</label>
				</div>
				<div class="w3-half w3-container">
					<input type="checkbox" name="todos" class="w3-check" onclick="habilitar('todos')" id="todos	"> <label> Listar todos los socios</label>
				</div>
			</div>
			<br>
			<div class="w3-container w3-padding-64 w3-card-4">
				<div class="w3-half w3-container">
					<div class="w3-quarter w3-container">
						<input type="checkbox" name="Check" class="w3-check" onclick="habilitar('check')" id="check"> <label> Seleccionar: </label>
					</div>
					<div class="w3-rest w3-container">
						<select class="w3-select" name ="convenio" id="selector" disabled>
							<option value="" disabled>...</option>
							<%for(Convenio c: lc){ %>
							<option value="<%=c.getCCOND()%>"><%=c.getCCOND()%> - <%=c.getDESCOND()%></option>	
							<%} %>
						</select>
					</div>
				</div>
				<div class="w3-half w3-container">
					<input type="checkbox" name="todosconv" class="w3-check" onclick="habilitar('todosconv')" id="todosconv	"> <label> Listar todos los convenios</label>
				</div>
			</div>
			<br>
			<div class="w3-container w3-center">
				<button class="w3-button w3-green w3-round-large">Listar</button>
			</div>
		</form> 
		<% request.getSession().removeAttribute("msj"); %>
	</div>

	<%@ include file="footer.jsp" %>
	<script type="text/javascript">
			function habilitar(dato){
				if (dato == "activos" && dato <> "todos") {
					document.getElementById("todos").checked = false;
				}
				else if(dato == "todos" && dato <> "activos") {
					document.getElementById("activos").checked = false;
				} 
				else{
					document.ggetElementById("activos").checked = true;
					document.getElementById("todos").checked = false;
				}
				if (dato == "check") {
					document.getElementById("selector").disabled = false;
					document.getElementById("todosconv").checked = false;
				}
				else if(dato == "todosconv") {
					document.getElementById("check").checked = false;
					document.getElementById("selector").disabled = true;
				}
			}
		</script>
</body>
</html>