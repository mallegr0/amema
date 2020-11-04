<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA Web - Actualiza Pagos por convenio</title>
		<%@include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<!-- Menu -->
		<%@include file="../views/menu.jsp"%>

		
		<!-- Cuerpo -->
		<div class="w3-container w3-padding-64">
			<div class="w3-container w3-padding w3-card-4">
				<%@include file="../forms/actualizacionesPagos/actualizaMasivoForm.jsp"%>
			</div>
			<br>
			<div class="w3-container w3-padding w3-card-4">
				<table class="w3-table w3-bordered">
					<thead>
						<tr class="w3-indigo">
							<td>Detalle del error</td>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
		<!-- Footer -->
		<%@include file="footer.jsp"%>
		<script type="text/javascript">
			function completaRuta(){
				var nombre = document.getElementById("texto").value;
				var ruta = "C:/Municipalidad/"+nombre;
				document.getElementById("ruta").value = ruta;
			}
		</script>
	</body>
</html>