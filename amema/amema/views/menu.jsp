<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Navbar -->
<div class="w3-top">
	<div class="w3-bar w3-theme-d2 w3-left-align">
	<!-- ES EL ICONO DEL MENU CUANDO SE ACHICA LA PANTALLA -->
  		<a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-hover-white w3-theme-d2" href="javascript:void(0);" onclick="openNav()"><i class="fas fa-bars"></i></a>
	<!-- -->
		  		
		<div class="w3-dropdown-hover w3-hide-small">
	   		<button class="w3-button" title="menu1">menu1 <i class="fas fa-angle-down"></i></button>
	   		<div class="w3-dropdown-content w3-card-4 w3-bar-block">
	      		<a href="#" class="w3-bar-item w3-button">Link</a>
	      		<a href="#" class="w3-bar-item w3-button">Link</a>
	      		<a href="#" class="w3-bar-item w3-button">Link</a>
	   		</div>
		</div>
		<div class="w3-dropdown-hover w3-hide-small">
	   		<button class="w3-button" title="menu2">menu2 <i class="fas fa-angle-down"></i></button>
	   		<div class="w3-dropdown-content w3-card-4 w3-bar-block">
	      		<a href="#" class="w3-bar-item w3-button">Link</a>
	      		<a href="#" class="w3-bar-item w3-button">Link</a>
	      		<a href="#" class="w3-bar-item w3-button">Link</a>
	   		</div>
		</div>
		<div class="w3-dropdown-hover w3-hide-small">
	   		<button class="w3-button" title="menu3">menu3 <i class="fas fa-angle-down"></i></button>
	   		<div class="w3-dropdown-content w3-card-4 w3-bar-block">
	      		<a href="#" class="w3-bar-item w3-button">Link</a>
	      		<a href="#" class="w3-bar-item w3-button">Link</a>
	      		<a href="#" class="w3-bar-item w3-button">Link</a>
	   		</div>
		</div>
		<div class="w3-dropdown-hover w3-hide-small">
			<button class="w3-button" title="menu4">menu4 <i class="fas fa-angle-down"></i></button>
			<div class="w3-dropdown-content w3-card-4 w3-bar-block">
		   		<a href="#" class="w3-bar-item w3-button">Link</a>
		   		<a href="#" class="w3-bar-item w3-button">Link</a>
		   		<a href="#" class="w3-bar-item w3-button">Link</a>
			</div>
		</div>
		<div class="w3-dropdown-hover w3-hide-small w3-right">
			<button class="w3-button w3-large" title="usuario"><i class="fas fa-user"></i></button>
			<div class="w3-dropdown-content w3-card-4 w3-bar-block" style="right: 0">
		   		<a href="#" class="w3-bar-item w3-button">aca va el nombre y apellido</a>
		   		<a href="#" class="w3-bar-item w3-button">Cambio contraseña</a>
		   		<a href="#" class="w3-bar-item w3-button">salir  <i class="w3-large fa fa-sign-out"></i></a>
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