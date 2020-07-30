<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestion de Usuarios</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		
		<%@ include file="menu.jsp" %>
		
		<div class="w3-container">
    <!-- Modal de la baja -->
			<div id="id04" class="w3-modal w3-round-xlarge">
				<div class="w3-modal-content w3-animate-zoom w3-card-4">
			    	<header class="w3-container w3-indigo"> 
				       	<span onclick="document.getElementById('id04').style.display='none'" class="w3-button w3-display-topright">&times;</span>
			        	<h2 class="w3-center"> Eliminar Usuario </h2>
			      	</header>
			      	<form action="Usuario" method="post">
				      	<div class="w3-container">
					      	<div class="w3-row w3-section">
		  						<p>Desea eliminar al usuario: IRIA EL USUARIO</p>
							</div>
							<div class="w3-row w3-section">
								<div class="w3-half w3-center">
									<button class="w3-button w3-green w3-round-large" name="evento_eliminar">Eliminar</button>
								</div>
								<div class="w3-rest w3-center">
									<a href="usuarios.jsp" class="w3-button w3-red w3-round-large">Volver</a>
								</div>
					    	</div>
					    </div>
					</form>
				</div>
			</div></div>
			
	<%@include file="footer.jsp"%>
	</body>
	</html>
	