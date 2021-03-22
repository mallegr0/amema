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
		

		<div class="w3-dropdown-click w3-hide-small">
			<button class="w3-button" title="Productos">Productos <i class="fas fa-angle-down"></i></button>     
			<div id="menu" class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide">
				<a href="/amema/views/productos.jsp" class="w3-bar-item w3-button">Gestión de Familias</a>
			</div>
		</div>


		<div class="w3-dropdown-click w3-hide-small">
			<button class="w3-button" title="Administracion">Administración <i class="fas fa-angle-down"></i></button>
			<div class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide" style="left: 100px;">
				<div class="w3-dropdown-click w3-hide-small">
					<button class="w3-button" title="socios">socios <i class="fas fa-angle-down"></i></button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide" style="left: 100px;">
						<a href="/amema/views/socios.jsp" class="w3-bar-item w3-button">Gestión de Socios</a>
						<a href="/amema/views/listarsocios.jsp" class="w3-bar-item w3-button">Listado de Socios</a>
						<a href="/amema/views/buscaadherentes.jsp" class="w3-bar-item w3-button">Gestión de Adherentes</a>
						<a href="/amema/views/buscaconveniosocio.jsp" class="w3-bar-item w3-button">Parámetro Socio/Convenio</a>
					</div>
				</div>
				<div class="w3-dropdown-click w3-hide-small">
					<button class="w3-button" title="Movimientos Fijos">Movimientos Fijos <i class="fas fa-angle-down"></i></button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide" style="left: 100px;">
						<a href="/amema/views/buscamovfijos.jsp" class="w3-bar-item w3-button">Grabar Movimientos Fijos</a>
						<a href="/amema/views/actualizaMovFijos.jsp" class="w3-bar-item w3-button">Actualización de Movimientos Fijos</a>
						<a href="/amema/views/eliminaMovFijos.jsp" class="w3-bar-item w3-button">Cancelar Movim. Por Número de Movim. Fijo</a>
					</div>
				</div>
				<div class="w3-dropdown-click w3-hide-small">
					<button class="w3-button" title="Generar Recibos desde archivo">Generar Recibos desde Archivo <i class="fas fa-angle-down"></i></button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide" style="left: 100px;">
						<a href="/amema/views/actualizaPagosMasivo.jsp" class="w3-bar-item w3-button">Nueva importación de datos desde archivo</a>
						<a href="/amema/views/listaPagosMasivos.jsp" class="w3-bar-item w3-button">Listar importación de datos desde archivo</a>
						<a href="/amema/views/generaRecibos.jsp" class="w3-bar-item w3-button">Generación de Recibos</a>
					</div>
				</div>
				<div class="w3-dropdown-click w3-hide-small">
					<button class="w3-button" title="Grabar Pago de Socio"> Grabar Pago de Socio <i class="fas fa-angle-down"></i></button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide" style="left: 100px;">
						<a href="/amema/views/cobrosSocios.jsp" class="w3-bar-item w3-button">Grabar Pagos de Socios</a>
					</div>
				</div>
				<div class="w3-dropdown-click w3-hide-small">
					<button class="w3-button" title="Generar Inetereses por Convenio"> Generar Intereses por Convenio <i class="fas fa-angle-down"></i></button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide" style="left: 100px;">
						<a href="/amema/views/generaInteres.jsp" class="w3-bar-item w3-button">Genera Intereses</a>
					</div>
				</div>
				<div class="w3-dropdown-click w3-hide-small">
					<button class="w3-button" title="Generar Archivos"> Generar Archivo <i class="fas fa-angle-down"></i></button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide" style="left: 100px;">
						<a href="/amema/views/generaTXT.jsp" class="w3-bar-item w3-button">Generar  Archivos de descuentos por Convenio</a>
						<a href="/amema/views/periodosDeuda.jsp" class="w3-bar-item w3-button">Consultar Periodos de Deuda Generados</a>
					</div>
				</div>
				<div class="w3-dropdown-click w3-hide-small">
					<button class="w3-button" title="AnalisisDeuda"> Analisis de Deuda <i class="fas fa-angle-down"></i></button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide" style="left: 100px;">
						<a href="/amema/views/analisisDeuda.jsp" class="w3-bar-item w3-button">Analisis de Deuda</a>
					</div>
				</div>
				<div class="w3-dropdown-click w3-hide-small">
					<button class="w3-button" title="Consultar cobro Socio"> Consultar Cobro a Socio <i class="fas fa-angle-down"></i></button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide" style="left: 100px;">
						<a href="/amema/views/consultaCobroSocio.jsp" class="w3-bar-item w3-button">Consultar cobro a Socio</a>
					</div>
				</div>
				<div class="w3-dropdown-click w3-hide-small">
					<button class="w3-button" title="Listados"> Listados <i class="fas fa-angle-down"></i></button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide" style="left: 100px;">
						<a href="/amema/views/analisisSaldo.jsp" class="w3-bar-item w3-button">Analisis de Saldos de Socio</a>
						<a href="/amema/views/reporteMovFijos.jsp" class="w3-bar-item w3-button">Reportes Movimientos Fijos por Período</a>
					</div>
				</div>
			</div>
		</div>
		<div class="w3-dropdown-click">
			<button class="w3-button" title="Cuentas Corrientes">Cuenta Corriente <i class="fas fa-angle-down"></i></button>     
			<div id="menu" class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide">
				<a href="/amema/views/buscactactes.jsp" class="w3-bar-item w3-button">Cuenta Corriente</a>
			</div>
		</div>
		<div class="w3-dropdown-click">
			<button class="w3-button" title="Convenios">Convenio <i class="fas fa-angle-down"></i></button>     
			<div id="menu" class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide">
				<a href="/amema/views/convenio.jsp" class="w3-bar-item w3-button">Convenio</a>
			</div>
		</div>
		<div class="w3-dropdown-click w3-hide-small">
			<button class="w3-button" title="usuarios">Usuarios <i class="fas fa-angle-down"></i></button>     
			<div class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide">
				<a href="/amema/views/usuarios.jsp" class="w3-bar-item w3-button">Gestión de Usuarios</a>
			</div>
		</div>

		<div class="w3-dropdown-click w3-hide-small w3-right">
			<button class="w3-button" title="Perfil"><i class="fas fa-user fa-lg"></i></button>     
			<div id="menu" class="w3-dropdown-content w3-card-4 w3-bar-block w3-hide" style="right: 0">
				<p class="w3-bar-item"><%= user.getNomUs()%></p>
				<button class="w3-bar-item w3-button" onclick="abroModalUsuario('id02','<%=user.getLogIn()%>')">Cambiar Contraseña</button>
				<a href="/amema/Logout" class="w3-bar-item w3-button">Salir <i class="fas fa-sign-out-alt"></i></a>
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


<%@ include file="../modal/socio/modalCambioPass.jsp"%>

<script>
	function abroModalUsuario(id, usuario){
		document.getElementById(id).style.display='block';
		document.getElementById("user").value = usuario;
	}

</script>