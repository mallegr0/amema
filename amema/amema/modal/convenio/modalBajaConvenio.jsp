<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div id="bajaConvenio" class="w3-modal w3-round-xlarge">
	<div class="w3-modal-content w3-animate-zoom w3-card-4">
		<header class="w3-container w3-indigo"> 
			<span onclick="document.getElementById('bajaConvenio').style.display='none'" class="w3-button w3-display-topright">&times;</span>
			<h2 class="w3-center"> Baja de Convenio </h2>
		</header>
		<div class="w3-container w3-padding w3-card-4">
			<%@ include file="../../forms/convenio/bajaConvenioForm.jsp" %>
		</div>
	</div>
</div>