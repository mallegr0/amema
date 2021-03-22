<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-indigo.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
<link rel="stylesheet" type="text/css" href="/amema/static/w3.css">
<link rel="stylesheet" type="text/css" href="/amema/static/w3-indigo.css">
<link rel="stylesheet" type="text/css" href="/amema/static/fontawesome.css">

<style>
	.loader {
		border: 16px solid #f3f3f3;
	    border-radius: 50%;
		border-top: 16px solid #3498db;
		width: 120px;
		height: 120px;
		-webkit-animation: spin 2s linear infinite; /* Safari */
		animation: spin 2s linear infinite;
		position: absolute;
 		left: 40%;
  		top: 130%;
	}
			
	/* Safari */
	@-webkit-keyframes spin {
	  0% { -webkit-transform: rotate(0deg); }
	  100% { -webkit-transform: rotate(360deg); }
	}
			
	@keyframes spin {
	  0% { transform: rotate(0deg); }
	  100% { transform: rotate(360deg); }
	}

	#data {
		width: 100%;
		max-height: 650px;
		float: left;
		overflow: auto;
	}

	#detalle {
		width: 25%;
		height: 650px;
		position: absolute;
		right: 2%; 
	}

	#contenedor {
		height: 700px;
	}

	tr, td {
		border: 1px solid #ddd; 
	}

	th {
		cursor: pointer;
	}
</style>
