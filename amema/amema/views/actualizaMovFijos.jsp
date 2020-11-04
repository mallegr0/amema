<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>AMEMA web - Actualización de Movimientos Fijos</title>
	<%@ include file="../meta/metadata.jsp"%>
</head>
<body>
	<!-- AREA DEL MENU -->
	<%@ include file="menu.jsp" %>

	<div class="w3-container w3-padding-64">
		<%@ include file="../forms/movfijos/actualizaMovFijosForm.jsp"%>
	</div>


	<%@ include file="footer.jsp"%>
</body>
</html>