<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web</title>
		<%@ include file="meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		
		<%@ include file="views/menu.jsp"%>
		
		

		<!-- ACA VA EL CUERPO DE LAS ACCIONES -->
		<div class="w3-container w3-padding-64">			
			<div class="w3-card w3-red">
				<ol>
					<li>recuperar informacion de la bbdd -- Conectar con el servlet</li>
					<li>hacer menu de usuario</li>
				</ol>
			</div>
			
			<form action="Usuario" method="post">
				<div class="w3-container w3-center w3-card-4">
					<button class="w3-jumbo fas fa-user-plus w3-button w3-hover-indigo w3-text-green w3-round-xxlarge"></button>
				</div>

				<h1 class="w3-center"> Usuarios </h1>
				<div class="w3-responsive">
					<table class="w3-table-all w3-card-4">
						<thead>
							<tr  class="w3-indigo">
								<th>Nombre y Apellido</th>
								<th>Usuario</th>
								<th colspan="2" class="w3-center">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<td>
								Matias Allegranza
							</td>
							<td>mallegr0</td>
							<td class="w3-center">
								<!-- Modificar usuario -->
								<button class="fas fa-user-edit w3-xlarge w3-button w3-hover-indigo w3-round-xxlarge" ></button>

								<button class="fas fa-user-edit w3-xlarge w3-button w3-hover-indigo w3-round-xxlarge" onclick="document.getElementById('id01').style.display='block'"></button>
							</td>
							<td class="w3-center">
								<!-- Eliminar Usuario -->
								<button class="fas fa-user-times w3-xlarge w3-button w3-hover-indigo w3-text-red w3-round-xxlarge" onclick="document.getElementById('id02').style.display='block'"></button>
							</td>
						</tbody>
					</table>
				</div>

				<!-- MODAL MODIFICAR-->
				<div id="id01" class="w3-modal">
					<div class="w3-modal-content w3-animate-zoom w3-card-4">
						<header class="w3-container w3-indigo"> 
							<span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
							<h2>Modificar usuario </h2>
						</header>
						<div class="w3-container">
							<div>Nombre y Apellido: <input type="text" name="nombreyapellido"></div>
							<div>Password: <input type="password" name="password"></div>
							
							<a href="index.jsp" class="w3-button">Volver</a>			
						</div>
					</div>
				</div>

				  <!-- FIN MODAL -->

				  <!-- MODAL ELIMINAR-->
				<div id="id02" class="w3-modal">
					<div class="w3-modal-content w3-animate-zoom w3-card-4">
				    	<header class="w3-container w3-indigo"> 
				        	<span onclick="document.getElementById('id02').style.display='none'" class="w3-button w3-display-topright">&times;</span>
				        	<h2>Eliminar usuario </h2>
				      	</header>
				      	<div class="w3-container">
							Desea eliminar al usuario: aca va el nombre de usuario
							
							<a href="index.jsp" class="w3-button">Volver</a>			
				      	</div>
				    </div>
				</div>
			</form>
		  <!-- FIN MODAL -->
		</div>

		<!-- FIN CUERPO -->

		<!-- Footer -->
		<footer class="w3-container w3-theme-d1 w3-center w3-bottom">
			<p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a> // Development by Matias Allegranza</p>
		</footer>
		
		<script type="text/javascript" src="JS/scripts.js"></script>
	</body>
</html>