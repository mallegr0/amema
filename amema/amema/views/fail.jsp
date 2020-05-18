<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web</title>
		<%@include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		<% request.getSession().removeAttribute("error"); %>
		<!-- Navbar -->
		<div class="w3-top">
			<div class="w3-bar w3-theme-d2 w3-left-align">
				<h1 class="w3-center"> AMEMA </h1>
			</div>
		</div>

		<!-- ACA VA EL CUERPO DE LAS ACCIONES -->
		<div class="w3-container w3-padding-64">	

			<div class="w3-card w3-red">
				<ol>
					<li>Manejo de session</li>
					<li>Bloquear el manejo de los botones</li>
					<li>Manejo de errores</li>
					<li>Testing Login</li>
				</ol>
			</div>

			<div class="w3-row">
				<div class="w3-col w3-container" style="width: 25%"></div>
				<div class="w3-col w3-container" style="width: 50%">
					<div class="w3-center w3-container w3-card-4 w3-padding-16">

						<!-- Mensaje de Error -->
						<% String msj = (String) request.getSession().getAttribute("msg"); %>
			
						<div class="w3-panel w3-yellow w3-display-container">
  							<span onclick="this.parentElement.style.display='none'" class="w3-button w3-large w3-display-topright">&times;</span>
  							<p><%=msj  %></p>
						</div>

						<!-- Form del Login -->
<!--
						<form action="Login" name="Login" method="post">
							<div class="w3-container">
								<div class="w3-row w3-section">
									<div class="w3-col" style="width: 50px"><span class="w3-text-indigo fas fa-user fa-lg"></span></div>
									<div class="w3-rest">
										<input class="w3-input w3-border w3-round-xlarge" type="text" name="usuario" placeholder="Usuario" required>
									</div>
								</div>
								<div class="w3-row w3-section">
									<div class="w3-col" style="width: 50px"><span class="w3-text-indigo fas fa-key fa-lg"></span></div>
									<div class="w3-rest">
										<input class="w3-input w3-border w3-round-xlarge" type="password" name="password" placeholder="Contraseña" required>
									</div>
								</div>
								<div class="w3-row w3-section">
									<div class="w3-center">
										<button class="w3-button w3-green w3-round-xlarge" name="login">Ingresar</button>
									</div>
								</div>
							</div>
						</form>-->
						<%@ include file="../forms/loginForm.jsp"%>
					</div>
				</div>
				<div class="w3-col w3-container" style="width: 25%"></div>
			</div>


		</div>
		
		<%@ include file="/views/footer.jsp"%>

	</body>
</html>

