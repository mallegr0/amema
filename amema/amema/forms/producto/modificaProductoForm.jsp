<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Familia"%>
<%@page import="controladores.CtrlFamilia"%>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestion de Productos</title>
		<%@include file="../../meta/metadata.jsp" %>
	</head>
	<body>

		<%@ include file="../../views/menu.jsp"%>

		<%
			CtrlFamilia cf = new CtrlFamilia();
			Familia tmp = cf.consultaFamilia(request.getParameter("id"));
		%>


		<div class="w3-container w3-padding-64">
			<div class="w3-card-4">
				<header class="w3-container w3-indigo"><h2 class="w3-center"> Modificación de Producto </h2></header>
				<form action="/amema/Producto" name="Producto" method="post">
					<div class="w3-container">
						<div class="w3-row w3-section">
					  		<div class="w3-col" style="width:120px"><label>Código: </label></div>
					    	<div class="w3-rest">
					    		<input class="w3-input w3-border" name="codigo" type="text" value="<%=tmp.getCFAMI()%>"readonly="readonly">
					    	</div>
						</div>
						
						<div class="w3-row w3-section">
					  		<div class="w3-col" style="width:120px"><label>Denominación: </label></div>
					    	<div class="w3-rest">
					    		<input class="w3-input w3-border" name="nombre" type="text" value="<%=tmp.getNFAMI()%>" required>
					    	</div>
						</div>
						<div class="w3-row w3-section">
							<div class="w3-col" style="width: 120px"><label>Bonificación: </label></div>
							<div class="w3-rest">
								<input class="w3-input w3-border"  name="bonificacion" type="text" value="<%=tmp.getBFAMI()%>" required>
							</div>
						</div>
						<div class="w3-row w3-section">
							<div class="w3-half w3-center">
								<button class="w3-button w3-green w3-hover-indigo" name="evento_modificar">Modificar</button>
							</div>
							<div class="w3-rest w3-center">
								<a href="../../views/productos.jsp" class="w3-button w3-red">Volver</a>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>

	<script type="text/javascript" src="../../JS/scripts.js"></script>
	<%@ include file="../../views/footer.jsp"%>
</html>