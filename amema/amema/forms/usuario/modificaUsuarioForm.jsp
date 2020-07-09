<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Usuario"%>
<%@page import="controladores.CtrlUsuario"%>
	
<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestion de Usuarios</title>
		<%@include file="../../meta/metadata.jsp" %>
	</head>
	<body>

		<%@include file="../../views/menu.jsp" %>

		<%
			CtrlUsuario cu = new CtrlUsuario();
			Usuario tmp = cu.consultaUsuario(request.getParameter("id"));
		%>

		<div class="w3-container w3-padding-64">
			<div class="w3-card-4">
				<header class="w3-container w3-indigo">
					<h2 class="w3-center">Modificación de Usuario</h2>
				</header>
				<form action="/amema/Usuario" method="post">
					<div class="w3-container">
						<div class="w3-row w3-section">
					  		<div class="w3-col" style="width:120px"><label>Usuario: </label></div>
					    	<div class="w3-rest">
					    		<input id="updateuser" class="w3-input w3-border" name="usuario" value="<%=tmp.getLogIn()%>" type="text" readonly="readonly">
					    	</div>
						</div>
						<div class="w3-row w3-section">
					  		<div class="w3-col" style="width:120px"><label>Nombre y Apellido</label></div>
					    	<div class="w3-rest">
					    		<input class="w3-input w3-border" name="nombre" type="text" value="<%=tmp.getNomUs()%>"required>
					    	</div>
						</div>
						<div class="w3-row w3-section">
							<div class="w3-half w3-center">
								<button class="w3-button w3-green w3-round-large" name="evento_modificar">Modificar</button>
							</div>
							<div class="w3-rest w3-center">
								<a href="../../views/usuarios.jsp" class="w3-button w3-red w3-round-large">Volver</a>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<script type="text/javascript" src="../../JS/scripts.js"></script>
		<%@include file="../../views/footer.jsp"%>
	</body>
</html>