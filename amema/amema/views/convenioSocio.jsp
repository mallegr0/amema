<%@page import="java.util.ArrayList"%>
<%@page import="entidades.SocioConvenio" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Parámetros Socio / Convenio</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>	
		/* AREA DEL MENU */
		<%@ include file="menu.jsp"%>

		<div class="w3-container w3-padding-64">

			<% SocioConvenio s = (SocioConvenio) request.getSession().getAttribute("datos");
			if(s != null){
				if(s.getConc1() != null){%>
			<div class="w3-container w3-card-4 w3-padding">
				<h3 class="w3-center w3-text-indigo">Datos seleccionados</h3>
				<br>
				<form action="/amema/ConvenioSocio" method="post">
					<div class="w3-container w3-row w3-padding">
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Convenio: </strong></label><input class="w3-input" type="text" name="convenio" value="<%=s.getConVta() %>" readonly>
						</div>
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Socio: </strong></label><input class="w3-input" type="text" name="socio" value="<%=s.getCODCLI() %>" readonly>
						</div>
					</div>
					<div class="w3-container w3-row w3-padding">
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Concepto 1(Cuota): </strong></label><input class="w3-input" type="text" name="concepto1" value="<%=s.getConc1() %>">
						</div>
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Importe Cpto 1: </strong></label><input class="w3-input" type="text" name="importe1" value="<%=s.getTope1() %>">
						</div>
					</div>
					<div class="w3-container w3-row w3-padding">
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Concepto 2: </strong></label><input class="w3-input" type="text" name="concepto2" value="<%=s.getConc2() %>">
						</div>
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Importe Tope 2: </strong></label><input class="w3-input" type="text" name="importe2" value="<%=s.getTope2() %>">
						</div>
					</div>
					<div class="w3-container w3-row w3-padding">
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Concepto 3: </strong></label><input class="w3-input" type="text" name="concepto3" value="<%=s.getConc3() %>">
						</div>
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Importe tope 3: </strong></label><input class="w3-input" type="text" name="importe3" value="<%=s.getTope3() %>">
						</div>
					</div>
					<div class="w3-container w3-row w3-padding">
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Genera Intereses? : </strong></label><input class="w3-input" type="text" name="interes" value="<%=s.getGenInt() %>">
						</div>
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Tasa Int Mensual: </strong></label><input class="w3-input" type="text" name="tasa" value="<%=s.getTasaInt() %>">
						</div>
					</div>
					<div class="w3-container w3-row w3-padding">
						<label class="w3-text-indigo"><strong>Código de producto para generación de Intereses: </strong></label>
						<input class="w3-input" type="text" name="codigo" value ="<%=s.getCODARTINT() %>">
					</div>
					<br>
					<div class="w3-container w3-row w3-padding">
						<div class="w3-container w3-third w3-center">
							<button class="w3-button w3-green w3-hover-indigo" name="evento_alta"><i class="fas fa-plus"></i></button>
						</div>
						<div class="w3-container w3-third w3-center">
							<button class="w3-button w3-blue w3-hover-indigo" name="evento_modifica"><i class="fas fa-edit"></i></button>
						</div>
						<div class="w3-container w3-third w3-center">
							<button class="w3-button w3-red w3-hover-indigo" name="evento_baja"><i class="fas fa-minus"></i></button>
						</div>
					</div>
				</form>
				<br>
				<br>
				<div class="w3-container w3-center w3-padding">
					<a href="/amema/views/buscaconveniosocio.jsp" class="w3-button w3-green w3-hover-indigo"><strong>VOLVER</strong></a>
				</div>
			</div>
			<%}
			else {%>
			<div class="w3-container w3-card-4 w3-padding">
				<h3 class="w3-center w3-text-indigo">Datos seleccionados</h3>
				<br>
				<form action="/amema/ConvenioSocio" method="post">
					<div class="w3-container w3-row w3-padding">
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Convenio: </strong></label><input class="w3-input" type="text" name="convenio" value="<%=s.getConVta() %>" readonly>
						</div>
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Socio: </strong></label><input class="w3-input" type="text" name="socio" value="<%=s.getCODCLI() %>" readonly>
						</div>
					</div>
					<div class="w3-container w3-row w3-padding">
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Concepto 1(Cuota): </strong></label><input class="w3-input" type="text" name="concepto1">
						</div>
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Importe Cpto 1: </strong></label><input class="w3-input" type="text" name="importe1">
						</div>
					</div>
					<div class="w3-container w3-row w3-padding">
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Concepto 2: </strong></label><input class="w3-input" type="text" name="concepto2">
						</div>
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Importe Tope 2: </strong></label><input class="w3-input" type="text" name="importe2">
						</div>
					</div>
					<div class="w3-container w3-row w3-padding">
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Concepto 3: </strong></label><input class="w3-input" type="text" name="concepto3">
						</div>
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Importe tope 3: </strong></label><input class="w3-input" type="text" name="importe3">
						</div>
					</div>
					<div class="w3-container w3-row w3-padding">
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Genera Intereses? : </strong></label><input class="w3-input" type="text" name="interes">
						</div>
						<div class="w3-container w3-half">
							<label class="w3-text-indigo"><strong>Tasa Int Mensual: </strong></label><input class="w3-input" type="text" name="tasa">
						</div>
					</div>
					<div class="w3-container w3-row w3-padding">
						<label class="w3-text-indigo"><strong>Código de producto para generación de Intereses: </strong></label>
						<input class="w3-input" type="text" name="codigo">
					</div>
					<br>
					<div class="w3-container w3-row w3-padding">
						<div class="w3-container w3-third w3-center">
							<button class="w3-button w3-green w3-hover-indigo" name="evento_alta"><i class="fas fa-plus"></i></button>
						</div>
						<div class="w3-container w3-third w3-center">
							<button class="w3-button w3-blue w3-hover-indigo" name="evento_modifica"><i class="fas fa-edit"></i></button>
						</div>
						<div class="w3-container w3-third w3-center">
							<button class="w3-button w3-red w3-hover-indigo" name="evento_baja"><i class="fas fa-minus"></i></button>
						</div>
					</div>
				</form>
				<br>
				<br>
				<div class="w3-container w3-center w3-padding">
					<a href="/amema/views/buscaconveniosocio.jsp" class="w3-button w3-green w3-hover-indigo"><strong>VOLVER</strong></a>
				</div>
			</div>
			<%}
			}
		%>
		</div>

		<!-- AREA DEL FOOTER -->
		<%@ include file="footer.jsp"%>
	</body>
</html>