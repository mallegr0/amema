<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Cliente"%>
<%@page import="controladores.CtrlCliente"%>


<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestion de Socios</title>
		<%@ include file="../../meta/metadata.jsp"%>
	</head>
	<body>
		<%@ include file="../../views/menu.jsp"%>

		<%
			CtrlCliente cc = new CtrlCliente();
			Cliente tmp = cc.consultaCliente(request.getParameter("id"));
		%>

		<div class="w3-container w3-padding-64">
			<div class="w3-card-4">	
				<header class="w3-container w3-indigo">
					<h2 class="w3-center">Modificación de Socio</h2>
				</header>
				<form action="/amema/Socio" name ="Socio" method="post">
					<div class="w3-container">
						<div class="w3-row-padding w3-section">
							<div class="w3-col" style="width: 20%">
								<label>Nro Socio: </label><input class="w3-input" type="text" name="nrosocio" value="<%=tmp.getCODCLI()%>" required>
							</div>
							<div class="w3-rest">
								<label>  Apellido y Nombre:</label><input class="w3-input" type="text" name="nombre" value="<%=tmp.getNOMCLI()%>" required>
							</div>
						</div>

						<div class="w3-row-padding w3-section">
							<div class="w3-col" style="width: 20%">
								<label>Tpo Documento: </label>
								<select class="w3-select" name="tpodoc">
									  <option value="<%=tmp.getTIPO_DOC()%>" disabled selected><%=tmp.getTIPO_DOC()%></option>
									  <option value="DNI">DNI</option>
									  <option value="LE">LE</option>
									  <option value="LC">LC</option>
								</select>
							</div>
							<div class="w3-col" style="width: 40%">
								<label>Nro Documento: </label><input class="w3-input" type="text" name="nrodoc" value="<%=tmp.getCUITCLI()%>" required>
							</div>
							<div class="w3-rest">
								<label>Fecha Nacimiento: </label><input class="w3-input" type="date" name="fecnac" value="<%=tmp.getFECHA_NAC()%>" required>
							</div>
						</div>

						<div class="w3-row-padding w3-section">
							<label>Domicilio: </label><input class="w3-input" type="text" name="domicilio" value="<%=tmp.getDOMCLI()%>" required>
						</div>

						<div class="w3-row-padding w3-section">
							<div class="w3-col" style="width: 20%">
								<label>Telefono/s: </label>
							</div>
							<div class="w3-col" style="width: 40%">
								<input class="w3-input" type="text" name="telefono1" value="<%=tmp.getTELCLI_1()%>" required>
							</div>
							<div class="w3-col" style="width: 40%">
								<input class="w3-input" type="text" name="telefono2" value="<%=tmp.getTELCLI_2()%>" required>
							</div>			
						</div>

						<div class="w3-row-padding w3-section">
							<div class="w3-threequarter">
								<label>E-mail: </label><input class="w3-input" type="email" name="mail" value="<%=tmp.getE_MAIL()%>" required>
							</div>
							<div class="w3-rest">
								<label>Cód. Postal: </label><input class="w3-input" type="text" name="codpos" value="<%=tmp.getCODPOS()%>" required>
							</div>
						</div>

						<div class="w3-row-padding w3-section">
							<label>Convenio: </label><input class="w3-input" type="text" name="convenio" value="<%=tmp.getCCOND()%>" required maxlength="2">
						</div>
						
						<div class="w3-row-padding w3-section">
							<div class="w3-quarter">
								<label>Empresa: </label>
								<select class="w3-select" name="empresa">
									  <option value="<%=tmp.getCPCCP()%>" disabled selected><%=tmp.getCPCCP()%></option>
									  <option value="MR">MR</option>
									  <option value="CM">CM</option>
									  <option value="TC">TC</option>
									  <option value="PP">PP</option>
									  <option value="PI">PIL</option>
								</select>
							</div>
							<div class="w3-rest w3-container">
								<label>Lugar de trabajo: </label>
								<div class="w3-row-padding">
									<div class="w3-half">
										<input class="w3-input" type="text" name="cc" value="<%=tmp.getCONTACTO()%>" required>
									</div>
									<div class="w3-half">
										<input class="w3-input" type="text" name="descc" value="<%=tmp.getCONTACTO2()%>" required>
									</div>
								</div>
							</div>
						</div>

						<div class="w3-row-padding w3-section"> 
							<div class="w3-third">
								<label>Nro. Benef/Legajo: </label><input class="w3-input" type="text" name="legajo" value="<%=tmp.getDNRP()%>" required>
							</div>
							<div class="w3-third">
								<label>Estado: </label><input class="w3-input" type="text" name="estado" value="<%=tmp.getCOM_IND()%>" required>
							</div>
							<div class="w3-third">
								<label>Fecha Ingreso: </label><input class="w3-input" type="date" name="fecing" value="<%=tmp.getFECHA_ING()%>" required>
							</div>
						</div>

						<div class="w3-row-padding w3-section">
							<label>Observaciones: </label><textarea class="w3-input" name="obs" rows="5" ><%=tmp.getOBSCLI()%></textarea>
						</div>

					
						<div class="w3-row w3-section">
							<div class="w3-half w3-center"><button class="w3-button w3-green w3-hover-indigo" name="evento_modificar">Guardar</button></div>
							<div class="w3-rest w3-center"><a href="../../views/socios.jsp" class="w3-button w3-red">Volver</a></div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<script type="text/javascript" src="../../JS/scripts.js"></script>
		<%@ include file="../../views/footer.jsp"%>
	</body>
</html>