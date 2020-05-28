<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Usuario"%>

<% if(session.getAttribute("usuarioActivo") == null){ response.sendRedirect("../"); }	%>
<% Usuario user = (Usuario) session.getAttribute("usuarioActivo"); %>

<!-- Sidebar on click -->
<!-- Es el menu lateral cuando la pantalla se achica -->

<nav class="w3-sidebar w3-bar-block w3-white w3-card w3-animate-left w3-xxlarge" style="display:none;z-index:2" id="mySidebar">
	<a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-display-topright w3-text-teal">Close
		<i class="fas fa-remove"></i>
	</a>
	<a href="#" class="w3-bar-item w3-button">Link 1</a>
	<a href="#" class="w3-bar-item w3-button">Link 2</a>
	<a href="#" class="w3-bar-item w3-button">Link 3</a>
	<a href="#" class="w3-bar-item w3-button">Link 4</a>
	<a href="#" class="w3-bar-item w3-button">Link 5</a>
</nav>

<!-- Navbar -->
<div class="w3-top">
	<div class="w3-bar w3-theme-d2 w3-left-align">
		<a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-hover-white w3-theme-d2" href="javascript:void(0);" onclick="openNav()"><i class="fas fa-bars"></i></a>
		<div class="w3-dropdown-hover w3-hide-small">
			<button class="w3-button" title="Familia">Familias <i class="fas fa-angle-down"></i></button>     
			<div class="w3-dropdown-content w3-card-4 w3-bar-block">
				<a href="#" class="w3-bar-item w3-button">Link</a>
				<a href="#" class="w3-bar-item w3-button">Link</a>
				<a href="#" class="w3-bar-item w3-button">Link</a>
			</div>
		</div>
		<div class="w3-dropdown-hover w3-hide-small">
			<button class="w3-button" title="Menu 2">Menu 2 <i class="fas fa-angle-down"></i></button>     
			<div class="w3-dropdown-content w3-card-4 w3-bar-block">
				<a href="#" class="w3-bar-item w3-button">Link</a>
				<a href="#" class="w3-bar-item w3-button">Link</a>
				<a href="#" class="w3-bar-item w3-button">Link</a>
			</div>
		</div>
		<div class="w3-dropdown-hover w3-hide-small">
			<button class="w3-button" title="Menu 3">Menu 3 <i class="fas fa-angle-down"></i></button>     
			<div class="w3-dropdown-content w3-card-4 w3-bar-block">
				<a href="#" class="w3-bar-item w3-button">Link</a>
				<a href="#" class="w3-bar-item w3-button">Link</a>
				<a href="#" class="w3-bar-item w3-button">Link</a>
			</div>
		</div>
		<div class="w3-dropdown-hover w3-hide-small">
			<button class="w3-button" title="Menu 4">Menu 4 <i class="fas fa-angle-down"></i></button>     
			<div class="w3-dropdown-content w3-card-4 w3-bar-block">
				<a href="#" class="w3-bar-item w3-button">Link</a>
				<a href="#" class="w3-bar-item w3-button">Link</a>
				<a href="#" class="w3-bar-item w3-button">Link</a>
			</div>
		</div>
		<div class="w3-dropdown-hover w3-hide-small">
			<button class="w3-button" title="usuarios">Usuarios <i class="fas fa-angle-down"></i></button>     
			<div class="w3-dropdown-content w3-card-4 w3-bar-block">
				<a href="/amema/views/usuarios.jsp" class="w3-bar-item w3-button">Gestión de Usuarios</a>
			</div>
		</div>
		<div class="w3-dropdown-hover w3-hide-small w3-right">
			<button class="w3-button" title="Perfil"><i class="fas fa-user fa-lg"></i></button>     
			<div class="w3-dropdown-content w3-card-4 w3-bar-block" style="right: 0">
				<p class="w3-bar-item"><%= user.getNombreyapellido()%></p>
				<a href="#" class="w3-bar-item w3-button">Cambiar Contraseña</a>
				<a href="/amema/Logout" name="Logout" class="w3-bar-item w3-button">Salir <i class="fas fa-sign-out-alt"></i></a>
			</div>
		</div>
		
		
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
