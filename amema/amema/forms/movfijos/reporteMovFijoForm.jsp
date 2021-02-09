<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="controladores.CtrlFamilia" %>
<%@page import="entidades.Familia" %>
<%@page import="java.util.ArrayList" %>

<%  CtrlFamilia cFamilia = new CtrlFamilia();
	ArrayList<Familia> familias = cFamilia.listarFamilia();
%>
<form action="/amema/ReporteMovFijo" method="post">
	<fieldset><legend class="w3-text-indigo"><strong>  Periodo a Analizar  </strong></legend>
		<div class="w3-container w3-padding">
			<div class="w3-container w3-padding w3-half">
				<label class="w3-text-indigo"><strong>Fechas desde: </strong></label>
				<input type="Date" class="w3-input" name="fDesde" max="2050-12-31" required>
			</div>
			<div class="w3-container w3-padding w3-half">
				<label class="w3-text-indigo"><strong>Fechas Hasta: </strong></label>
				<input type="Date" class="w3-input" name="fHasta" max="2050-12-31"  required>
			</div>
			<br><br>
			<div class="w3-container w3-padding">
				<label class="w3-text-indigo"><strong>Estado del Movimiento: </strong></label>
				<select class="w3-select" name="estado">
					<option value="A">Alta</option>
					<option value="B">Baja</option>
					<option value="ambos">Ambos</option>
				</select>
			</div>
		</div>
	</fieldset>
	<br><br>
	<fieldset><legend class="w3-text-indigo"><strong>  Familias a Analizar  </strong></legend>
		<div class="w3-container w3-padding">
			<label class="w3-text-indigo"><strong>Seleccionar Producto</strong> * Para seleccionar a mas de una opción, se tiene que apretar Ctrl Izquierdo</label>
			<select class="w3-select" name="familia" multiple>
			<%for(Familia f: familias){ %>
				<option value="<%=f.getCFAMI()%>"><%=f.getCFAMI() %> - <%=f.getNFAMI()%></option>
				<%} %>
			</select>
		</div>
	</fieldset>
	<br>
	<div class="w3-container w3-padding w3-center">
		<button class="w3-button w3-green w3-hover-indigo" name="evento_reporteMovimiento"><i class="fas fa-search fa-2x"></i></button>
	</div>
</form>