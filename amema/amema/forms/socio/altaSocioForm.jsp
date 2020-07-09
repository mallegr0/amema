<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="/amema/Socio" name ="Socio" method="post">
	<div class="w3-container">
		<div class="w3-row-padding w3-section">
			<div class="w3-col" style="width: 20%">
				<label>Nro Socio: </label><input class="w3-input" type="text" name="nrosocio" value="<%=(String)request.getSession().getAttribute("id")%>"required>
			</div>
			<div class="w3-rest">
				<label>  Apellido y Nombre:</label><input class="w3-input" type="text" name="nombre" required>
			</div>
		</div>

		<div class="w3-row-padding w3-section">
			<div class="w3-col" style="width: 20%">
				<label>Tpo Documento: </label>
				<select class="w3-select" name="tpodoc">
					  <option value="" disabled selected>Tpo Doc</option>
					  <option value="DNI">DNI</option>
					  <option value="LE">LE</option>
					  <option value="LC">LC</option>
				</select>
			</div>
			<div class="w3-col" style="width: 40%">
				<label>Nro Documento: </label><input class="w3-input" type="text" name="nrodoc">
			</div>
			<div class="w3-rest">
				<label>Fecha Nacimiento: </label><input class="w3-input" type="date" name="fecnac">
			</div>
		</div>

		<div class="w3-row-padding w3-section">
			<label>Domicilio: </label><input class="w3-input" type="text" name="domicilio" required>
		</div>

		<div class="w3-row-padding w3-section">
			<div class="w3-col" style="width: 20%">
				<label>Telefono/s: </label>
			</div>
			<div class="w3-col" style="width: 40%">
				<input class="w3-input" type="text" name="telefono1" required placeholder="***-*******">
			</div>
			<div class="w3-col" style="width: 40%">
				<input class="w3-input" type="text" name="telefono2" required placeholder="***-*******">
			</div>			
		</div>

		<div class="w3-row-padding w3-section">
			<div class="w3-threequarter">
				<label>E-mail: </label><input class="w3-input" type="email" name="mail" required placeholder="nombre@servidor.com">
			</div>
			<div class="w3-rest">
				<label>Cód. Postal: </label><input class="w3-input" type="text" name="codpos" required>
			</div>
		</div>

		<div class="w3-row-padding w3-section">
			<label>Convenio: </label><input class="w3-input" type="text" name="convenio" required maxlength="2">
		</div>
		
		<div class="w3-row-padding w3-section">
			<div class="w3-quarter">
				<label>Empresa: </label>
				<select class="w3-select" name="empresa">
					  <option value="" disabled selected>...</option>
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
						<input class="w3-input" type="text" name="cc" required>
					</div>
					<div class="w3-half">
						<input class="w3-input" type="text" name="descc" required>
					</div>
				</div>
			</div>
		</div>

		<div class="w3-row-padding w3-section"> 
			<div class="w3-third">
				<label>Nro. Benef/Legajo: </label><input class="w3-input" type="text" name="legajo" required>
			</div>
			<div class="w3-third">
				<label>Estado: </label><input class="w3-input" type="text" name="estado" required>
			</div>
			<div class="w3-third">
				<label>Fecha Ingreso: </label><input class="w3-input" type="date" name="fecing" required>
			</div>
		</div>

		<div class="w3-row-padding w3-section">
			<label>Observaciones: </label><textarea class="w3-input" name="obs" rows="5"></textarea>
		</div>

	
		<div class="w3-row w3-section">
			<div class="w3-half w3-center"><button class="w3-button w3-green w3-round-large" name="evento_alta">Guardar</button></div>
			<div class="w3-rest w3-center"><a href="../views/socios.jsp" class="w3-button w3-red w3-round-large">Volver</a></div>
		</div>
	</div>
</form>