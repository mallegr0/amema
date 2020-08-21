<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		
		<!-- Navbar -->
		<div class="w3-top">
			<div class="w3-bar w3-theme-d2 w3-center">
				<img src="static/imgenes/logo-amema.png">
			</div>
		</div>
		<div style="padding-top: 5%;"></div>
		<!-- ACA VA EL CUERPO DE LAS ACCIONES -->
		<div class="w3-container w3-padding-64"  >	

			<!--<div class="w3-card w3-red">
				<ol>
					<li>Manejo de session -- Falta invalidar en el index</li>
				</ol>
			</div>-->
			<div class="w3-row">
				<div class="w3-col w3-container" style="width: 25%"></div>
				<div class="w3-col w3-container" style="width: 50%">
					<div class="w3-center w3-container w3-card-4 w3-padding-16">
						
						<!-- Error -->
						<% String msg = (String) request.getAttribute("msg"); %>
					
						
						<% if(msg != null){ %>
							<div class="w3-panel w3-yellow w3-display-container">
	  								<span onclick="this.parentElement.style.display='none'" class="w3-button w3-large w3-display-topright">&times;</span>
	  								<p><%=msg  %></p>
								</div>
						<%} %>
	
						<!-- Form del Login -->
						<%@ include file="../forms/loginForm.jsp" %>

					</div>
				</div>
				<div class="w3-col w3-container" style="width: 25%"></div>
			</div>
			
		</div>

		<%@ include file="../views/footer.jsp" %>
		
	</body>
</html>

									
								