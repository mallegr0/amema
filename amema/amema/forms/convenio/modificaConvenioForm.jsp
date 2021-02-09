<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Convenio" %>
<%@page import="controladores.CtrlConvenio"%>
<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Modificar Datos del Convenio</title>
		<%@ include file="../../meta/metadata.jsp"%>
	</head>
	<body>
		<%
			CtrlConvenio cConvenio = new CtrlConvenio();
			Convenio conv = cConvenio.consultaConvenio(request.getParameter("id"));
			String codigo, descripcion, c1, c2, c3, gint, codart, cobro;
			double t1, t2, t3, tasa; 
			if(conv != null){
					codigo = conv.getCCOND();
					descripcion = conv.getDESCOND();
					c1 = conv.getConc1();
					c2 = conv.getConc2();
					c3 = conv.getConc3();
					t1 = conv.getTope1();
					t2 = conv.getTope2();
					t3 = conv.getTope3();
					gint = conv.getGenInt(); 
					codart = conv.getCODARTINT();
					cobro = conv.getIngCobro();
					t1 = conv.getTope1();
					t2 = conv.getTope2();
					t3 = conv.getTope3();
					tasa = conv.getTasaInt();
				}
			else {
				codigo = "S/Nro";
				descripcion = "S/Desc";
				c1 = "S/Nro";
				c2 = "S/Nro";
				c3 = "S/Nro";
				t1 = 0;
				t2 = 0;
				t3 = 0;
				gint = "S/Desc"; 
				codart = "S/Desc";
				cobro = "S/Desc";
				tasa = 0;
			}
			
		%>
		<!-- AREA DEL MENU -->
		<%@ include file="../../views/menu.jsp"%>
		<div class="w3-container w3-padding-64">
			<div class="w3-container w3-padding w3-card-4">
				<form action="/amema/Convenio" method="post">
					<div class="w3-container w3-padding">
						<div class="w3-container w3-padding w3-half">
							<label class="w3-text-indigo"><strong>Cód. Convenio: </strong></label>
							<input class="w3-input" type="text" name="codConvenio" value='<%=codigo%>' readonly>
						</div>
						<div class="w3-container w3-padding w3-half">
							<label class="w3-text-indigo"><strong>Desc. Convenio: </strong></label>
							<input class="w3-input" type="text" name="descConvenio" value='<%=descripcion%>' required>
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-container w3-padding w3-half">
							<label class="w3-text-indigo"><strong>Concepto 1 (Cuota): </strong></label>
							<input class="w3-input" type="text" name="cpto1" value='<%=c1%>' required>
						</div>
						<div class="w3-container w3-padding w3-half">
							<label class="w3-text-indigo"><strong>Tope 1: $</strong></label>
							<input type="text" name="tope1" class="w3-input" value='<%=t1%>' required>
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-container w3-padding w3-half">
							<label class="w3-text-indigo"><strong>Concepto 2 (Varios): </strong></label>
							<input class="w3-input" type="text" name="cpto2" value='<%=c2%>' required>
						</div>
						<div class="w3-container w3-padding w3-half">
							<label class="w3-text-indigo"><strong>Tope 2: $</strong></label>
							<input class="w3-input" type="text" name="tope2" value='<%=t2%>' required>
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-container w3-padding w3-half">
							<label class="w3-text-indigo"><strong>Concepto 3: </strong></label>
							<input class="w3-input" type="text" name="cpto3" value='<%=c3%>' required>
						</div>
						<div class="w3-container w3-padding w3-half">
							<label class="w3-text-indigo"><strong>Tope 3: $</strong></label>
							<input class="w3-input" type="text" name="tope3" value='<%=t3%>' required>
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-container w3-padding w3-half">
							<label class="w3-text-indigo"><strong>Genera Interes?: </strong></label>
							<input class="w3-input" type="text" name="ginteres" value='<%=gint%>' required>
						</div>
						<div class="w3-container w3-padding w3-half">
							<label class="w3-text-indigo"><strong>Tasa Interes Mensual: </strong></label>
							<input class="w3-input" type="text" name="tasa" value='<%=tasa%>' required>
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-container w3-padding w3-half">
							<label class="w3-text-indigo"><strong>Cód. Producto para generación de Intereses: </strong></label>
							<input class="w3-input" type="text" name="producto" value='<%=codart%>' required>
						</div>
						<div class="w3-container w3-padding w3-half">
							<label class="w3-text-indigo"><strong>Modo para ingreso de Información: </strong></label>
							<select class="w3-select" name="modo">
								<option value="1">Municipalidad</option>
								<option value="0">Jubilados</option>
							</select>
						</div>
					</div>
					<br>
					<div class="w3-container w3-padding">
						<div class="w3-container w3-padding w3-center w3-half">
							<button class="w3-button w3-green w3-hover-indigo" name="evento_modificaConvenio">Modificar</button>
						</div>
						<div class="w3-container w3-padding w3-center w3-half">
							<a href="/amema/views/convenio.jsp" class="w3-button w3-red w3-hover-indigo">Volver</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- AREA DEL FOOTER -->
		<%@ include file="../../views/footer.jsp"%>
	</body>
</html>

