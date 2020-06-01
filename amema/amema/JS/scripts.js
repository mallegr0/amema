function w3_open() {
	var x = document.getElementById("mySidebar");
	x.style.width = "300px";
	x.style.paddingTop = "10%";
	x.style.display = "block";
}

// Close side navigation
function w3_close() { document.getElementById("mySidebar").style.display = "none"; }

// Used to toggle the menu on smaller screens when clicking on the menu button
function openNav() {
  var x = document.getElementById("navDemo");
  if (x.className.indexOf("w3-show") == -1) { x.className += " w3-show"; } 
  else { x.className = x.className.replace(" w3-show", ""); }
}


function abroModal(id, usuario){
	if(id === 'id02'){
		document.getElementById('id02').style.display='block'; 
		document.getElementById("user").value = usuario;
	}
	if(id === 'id03'){
		document.getElementById('id03').style.display='block'; 
		document.getElementById("updateuser").value = usuario;
	}
	if(id === 'id04'){
		document.getElementById('id04').style.display='block'; 
		document.getElementById("deleteuser").value = usuario;
	}
}

function comparaPass(){
	var c1 = document.Usuario.password.value;
	var c2 = document.Usuario.password2.value;
	
	if(c1 === c2 ){
		return true;
	}
	else{
		alert("Las contraseñas no son iguales!!!");
		return false;
	}
}
