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
		<!-- AREA DEL MENU -->
		
		<!-- Navbar -->
		<div class="w3-top">
			<div class="w3-bar w3-theme-d2 w3-left-align">
			<!-- ES EL ICONO DEL MENU CUANDO SE ACHICA LA PANTALLA -->
		  		<a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-hover-white w3-theme-d2" href="javascript:void(0);" onclick="openNav()"><i class="fas fa-bars"></i></a>
			<!-- -->
				  		
				<%@ include file="menu.jsp"%>
				<div class="w3-dropdown-hover w3-hide-small w3-right">
					<button class="w3-button w3-large" title="usuario"><i class="fas fa-user"></i></button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block" style="right: 0">
				   		<a class="w3-bar-item w3-button">Matias Allegranza</a>
				   		<a href="#id03" class="w3-bar-item w3-button">Cambio contraseña</a>
				   		<a href="#" class="w3-bar-item w3-button">salir  <i class="w3-large fas fa-sign-out-alt"></i></a>
					</div>
				</div>
				<div class="w3-clear"></div>
			</div>

			<!-- Navbar on small screens -->
			<div id="navDemo" class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium">
				<a href="#team" class="w3-bar-item w3-button">Team</a>
			    <a href="#work" class="w3-bar-item w3-button">Work</a>
			    <a href="#pricing" class="w3-bar-item w3-button">Price</a>
			    <a href="#contact" class="w3-bar-item w3-button">Contact</a>
			    <a href="#" class="w3-bar-item w3-button">Search</a>
			</div>
		</div>

		<!-- ACA VA EL CUERPO DE LAS ACCIONES -->
		<div class="w3-container w3-padding-64">			
			<div class="w3-card w3-red">
				<ol>
					<li>recuperar informacion de la bbdd -- Conectar con el servlet</li>
					<li>hacer menu de usuario</li>
					<li>hacer menu gral</li>
				</ol>
			</div>
			
			<!-- FORMULARIOS PARA EL ABM DE USUARIOS CON MODALES -->
			<div class="w3-container w3-center w3-card-4">
				<button class="w3-jumbo fas fa-user-plus w3-button w3-hover-indigo w3-text-green w3-round-xxlarge" onclick="document.getElementById('id01').style.display='block'"></button>
			</div>
				
			<!-- Modal del Alta -->
			<div id="id01" class="w3-modal w3-round-xlarge">
				<div class="w3-modal-content w3-animate-zoom w3-card-4">
			    	<header class="w3-container w3-indigo"> 
			        	<span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
			        	<h2 class="w3-center"> Alta de Usuario </h2>
			      	</header>
			      	<form action="Usuario" method="post">
			      		<div class="w3-container">
				      		<div class="w3-row w3-section">
	  							<div class="w3-col" style="width:120px"><label>Usuario: </label></div>
	    						<div class="w3-rest">
	    							<input class="w3-input w3-border" name="usuario" type="text" required>
	    						</div>
							</div>
							<div class="w3-row w3-section">
	  							<div class="w3-col" style="width:120px"><label>Password: </label></div>
	    						<div class="w3-rest">
	      							<input class="w3-input w3-border" name="password" type="password" min="6" maxlength="20" required>
	    						</div>
							</div>
							<div class="w3-row w3-section">
	  							<div class="w3-col" style="width:120px"><label>Nombre y Apellido</label></div>
	    						<div class="w3-rest">
	      							<input class="w3-input w3-border" name="nombreyapellido" type="text" required>
	    						</div>
							</div>
							<div class="w3-row w3-section">
								<div class="w3-half w3-center">
									<button class="w3-button w3-green w3-round-large" name="evento_alta">Agregar</button>
								</div>
								<div class="w3-rest w3-center">
									<a href="index.jsp" class="w3-button w3-red w3-round-large">Volver</a>
								</div>
				    		</div>
				    	</div>
				    </form>
				</div>
			</div>
			
			<% String msj = (String) request.getSession().getAttribute("msj"); %>
			
			<c:choose>
				<c:when test="${msj == 'OK'}">
					<div class="w3-panel w3-green w3-display-container">
  						<span onclick="this.parentElement.style.display='none'" class="w3-button w3-large w3-display-topright">&times;</span>
  						<h3>Éxito!</h3>
  						<p>El usuario ha sido creado correctamente</p>
					</div>
				</c:when>
				<c:when test="${msj == 'NO' }">
					<div class="w3-panel w3-yellow w3-display-container">
  						<span onclick="this.parentElement.style.display='none'" class="w3-button w3-large w3-display-topright">&times;</span>
  						<h3>Error!</h3>
  						<p>El usuario no ha sido creado correctamente</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="w3-panel w3-red w3-display-container">
  						<span onclick="this.parentElement.style.display='none'" class="w3-button w3-large w3-display-topright">&times;</span>
  						<h3>Error Grave!</h3>
  						<p><%= msj %></p>
					</div>
				</c:otherwise>
			</c:choose>
			
			
			<!-- FIN MODAL ALTA USUARIO -->
				
			<!-- EMPIEZA LA TABLA DE USUARIOS CON LOS BOTONES CAMBIAR PASS, MODIFICAR Y ELIMINAR-->
			<h1 class="w3-center"> Usuarios </h1>
			<div class="w3-responsive w3-card-4">
				<table class="w3-table-all w3-round-xlarge">
					<thead>
						<tr class="w3-indigo">
							<th>Usuario</th>
							<th>Nombre y Apellido</th>
							<th colspan="3" class="w3-center">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<tr class="w3-hover-light-green">
							<td>mallegr0</td>
							<td>Matias Allegranza</td>
							<td><button class="w3-xlarge fas fa-unlock w3-button w3-hover-indigo w3-text-yellow w3-round-xxlarge" onclick="document.getElementById('id02').style.display='block'"></button></td>
							<td><button class="w3-xlarge fas fa-user-edit w3-button w3-hover-indigo w3-text-blue w3-round-xxlarge" onclick="document.getElementById('id03').style.display='block'"></button></td>
							<td><button class="w3-xlarge fas fa-user-times w3-button w3-hover-indigo w3-text-red w3-round-xxlarge" onclick="document.getElementById('id04').style.display='block'"></button></td>
						</tr>
						<tr class="w3-hover-light-green">
							<td>pipi</td>
							<td>Amelie Allegranza</td>
							<td><button class="w3-xlarge fas fa-unlock w3-button w3-hover-indigo w3-text-yellow w3-round-xxlarge" onclick="document.getElementById('id02').style.display='block'"></button></td>
							<td><button class="w3-xlarge fas fa-user-edit w3-button w3-hover-indigo w3-text-blue w3-round-xxlarge" onclick="document.getElementById('id03').style.display='block'"></button></td>
							<td><button class="w3-xlarge fas fa-user-times w3-button w3-hover-indigo w3-text-red w3-round-xxlarge" onclick="document.getElementById('id04').style.display='block'"></button></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- FIN TABLA DE USUARIOS-->

			<!-- Modal del cambio de PASS -->
			<div id="id02" class="w3-modal w3-round-xlarge">
				<div class="w3-modal-content w3-animate-zoom w3-card-4 w3-round-xlarge">
			    	<header class="w3-container w3-indigo"> 
			        	<span onclick="document.getElementById('id02').style.display='none'" class="w3-button w3-display-topright">&times;</span>
			        	<h2 class="w3-center"> Cambio de Password </h2>
			      	</header>
			      	<form action="Usuario" method="post">
				      	<div class="w3-container">
					      	<div class="w3-row w3-section">
		  						<div class="w3-col" style="width:120px"><label>Password: </label></div>
		    					<div class="w3-rest">
		    						<input class="w3-input w3-border" name="password" type="password" minlength="6" maxlength="20" required>
		    					</div>
							</div>
							<div class="w3-row w3-section">
		  						<div class="w3-col" style="width:120px"><label>Repite Password: </label></div>
		    					<div class="w3-rest">
		    						<input class="w3-input w3-border" name="password2" type="password" minlength="6" maxlength="20" required>
		    					</div>
							</div>
							<div class="w3-row w3-section">
								<div class="w3-half w3-center">
									<button class="w3-button w3-green w3-round-large" name="evento_CambiaPassword">Cambiar</button>
								</div>
								<div class="w3-rest w3-center">
									<a href="index.jsp" class="w3-button w3-red w3-round-large">Volver</a>
								</div>
					    	</div>
					    </div>
					</form>
				</div>
			</div>
			<!-- FIN MODAL CAMBIA PASS -->

			<!-- Modal del Modificar -->
			<div id="id03" class="w3-modal w3-round-xlarge">
				<div class="w3-modal-content w3-animate-zoom w3-card-4 w3-round-xlarge">
			    	<header class="w3-container w3-indigo"> 
			        	<span onclick="document.getElementById('id03').style.display='none'" class="w3-button w3-display-topright">&times;</span>
			        	<h2 class="w3-center"> Modificar Usuario </h2>
			      	</header>
			      	<form action="Usuario" method="post">
				      	<div class="w3-container">
					      	<div class="w3-row w3-section">
		  						<div class="w3-col" style="width:120px"><label>Usuario: </label></div>
		    					<div class="w3-rest">
		    						<input class="w3-input w3-border" name="usuario" type="text" required>
		    					</div>
							</div>
							<div class="w3-row w3-section">
		  						<div class="w3-col" style="width:120px"><label>Password: </label></div>
		    					<div class="w3-rest">
		    						<input class="w3-input w3-border" name="password" type="password" minlength="6" maxlength="20" required>
		    					</div>
							</div>
							<div class="w3-row w3-section">
		  						<div class="w3-col" style="width:120px"><label>Nombre y Apellido</label></div>
		    					<div class="w3-rest">
		    						<input class="w3-input w3-border" name="nombreyapellido" type="text" required>
		    					</div>
							</div>
							<div class="w3-row w3-section">
								<div class="w3-half w3-center">
									<button class="w3-button w3-green w3-round-large" name="evento_modificar">Modificar</button>
								</div>
								<div class="w3-rest w3-center">
									<a href="index.jsp" class="w3-button w3-red w3-round-large">Volver</a>
								</div>
					    	</div>
					    </div>
					</form>
				</div>
			</div>
			<!-- FIN MODAL MODIFICA USUARIO -->

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
		  						<p>Desea eliminar al usuario: IRIA EÑ USUARIO</p>
							</div>
							<div class="w3-row w3-section">
								<div class="w3-half w3-center">
									<button class="w3-button w3-green w3-round-large" name="evento_eliminar">Eliminar</button>
								</div>
								<div class="w3-rest w3-center">
									<a href="index.jsp" class="w3-button w3-red w3-round-large">Volver</a>
								</div>
					    	</div>
					    </div>
					</form>
				</div>
			</div>
			<!-- FIN MODAL MODIFICA USUARIO -->
		</div>

		<!-- FIN CUERPO -->

		<!-- Footer -->
		<%@ include file="footer.jsp" %>
		
		<script type="text/javascript" src="JS/scripts.js"></script>
	</body>
</html>