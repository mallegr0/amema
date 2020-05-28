<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Usuario" %>
<%@page import="controladores.ctrlUsuario" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestion de Usuarios</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- AREA DEL MENU -->
		
		<%@ include file="menu.jsp" %>

		<!-- ACA VA EL CUERPO DE LAS ACCIONES -->
		<% 
			ctrlUsuario cu = new ctrlUsuario();
			List<Usuario> listaUsers = cu.listarUsuarios();
		%>

		<div class="w3-container w3-padding-64">			
			<div class="w3-card w3-red">
				<ol>
					<li>manejar devolucion de errores generales</li>
					<li>manejar un solo modal para los botones de cambia pass, modifica usuario y elimina usuario</li>
					<li>ver de inhabilitar el input con el nombre del usuario para que no lo cambien</li>
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
			        	<span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright w3-round-xlarge">&times;</span>
			        	<h2 class="w3-center"> Alta de Usuario </h2>
			      	</header>
			      	<%@ include file="../forms/altaUsuarioForm.jsp"%>
				</div>
			</div>
			
			<%@ include file="../errores/errAltaUsuario.jsp" %>

			<!-- FIN MODAL ALTA USUARIO -->
				
						<!-- EMPIEZA LA TABLA DE USUARIOS CON LOS BOTONES CAMBIAR PASS, MODIFICAR Y ELIMINAR-->
			<h1 class="w3-center"> Usuarios </h1>
			<div class="w3-responsive w3-card-4 w3-round-xlarge">
				<table class="w3-table-all w3-round-xlarge">
					<thead>
						<tr class="w3-indigo">
							<th>Usuario</th>
							<th>Nombre y Apellido</th>
							<th>Contraseña</th>
							<th colspan="3" class="w3-center">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<% for(Usuario u : listaUsers){%>
							<tr class="w3-hover-light-green">
								<td><%=u.getUsuario() %></td>
								<td><%=u.getNombreyapellido() %></td>
								<td><%=u.getPassword() %></td>
								<td><button class="w3-xlarge fas fa-unlock w3-button w3-hover-indigo w3-text-yellow w3-round-xxlarge" onclick="cambiaPass('<%=u.getUsuario()%>')"></button></td>
								<td><button class="w3-xlarge fas fa-user-edit w3-button w3-hover-indigo w3-text-blue w3-round-xxlarge" onclick="document.getElementById('id03').style.display='block'" <% request.setAttribute("usuario", u.getUsuario()); %>></button></td>
								<td><button class="w3-xlarge fas fa-user-times w3-button w3-hover-indigo w3-text-red w3-round-xxlarge" onclick="document.getElementById('id04').style.display='block'" <% request.setAttribute("usuario", u.getUsuario()); %>></button></td>
							</tr>
						<%}%>
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
			      	<form action="/amema/Usuario" name="Usuario"  method="post">
				      	<div class="w3-container">
				      		<div class="w3-row w3-section">
		  						<div class="w3-col" style="width:120px"><label>Usuario: </label></div>
		    					<div class="w3-rest">
		    						<input id="user" class="w3-input w3-border" name="usuario" type="text" required>
		    					</div>
							</div>
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
									<a href="../views/usuarios.jsp" class="w3-button w3-red w3-round-large">Volver</a>
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
									<a href="../views/usuarios.jsp" class="w3-button w3-red w3-round-large">Volver</a>
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
		  						<p>Desea eliminar al usuario: </p><label name="usuario" ><%=request.getAttribute("usuario") %></label>
							</div>
							<div class="w3-row w3-section">
								<div class="w3-half w3-center">
									<button class="w3-button w3-green w3-round-large" name="evento_eliminar">Eliminar</button>
								</div>
								<div class="w3-rest w3-center">
									<a href="../views/usuarios.jsp" class="w3-button w3-red w3-round-large">Volver</a>
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
		<script>
			function cambiaPass(usuario){ 
				document.getElementById('id02').style.display='block'; 
				document.getElementById("user").value = usuario;}
		
		
		</script>
		
		
		<%@ include file="footer.jsp" %>
	</body>
</html>