<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Usuario</title>
	</head>
	<body>
	
		<h1 style="color:red; vertical-align: center"> Menu Usuarios </h1>	
	
		<div>
			<table border="1">
				<thead>
					<th> Usuario </th>
					<th> Password </th>
					<th> Nombre y Apellido </th>
				</thead>
				<tbody>
					aca va los datos de la bbdd
				</tbody>
			</table>	
		</div>

		<br>
		<br>
		<br>

		<div>
			<table>
				<tr>
					<form id="UsuarioServlet" name = "UsuarioServlet" action="UsuarioServlet" method="post">
						<button> Alta </button>	
					</form>
				</tr>
				<tr>
					<form id="UsuarioServlet" name="UsuarioServlet" action="UsuarioServlet" method="delete">
						<button> Baja </button>
					</form>
				</tr>
				<tr>
					<form id="UsuarioServlet" name="UsuarioServlet" action="UsuarioServlet" method="put">
						<button> modificaciÃ³n </button>
					</form>

				</tr>
				<tr>
					<form id="UsuarioServlet" name="UsuarioServlet" action="UsuarioServlet" method="get">
						<button> Consulta </button>
					</form>
				</tr>
			</table>
		</div>	
	</body>
</html>