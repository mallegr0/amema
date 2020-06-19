// Seccion para manejar el menu

var toggler = document.getElementsByClassName("w3-button");
var i;

for (i = 0; i < toggler.length; i++) {
  toggler[i].addEventListener("click", function() {
    this.parentElement.querySelector(".w3-hide").classList.toggle("w3-show");
  });
}

// Fin seccion menu
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


function abroModalUsuario(id, usuario){
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

function abroModalProducto(id, producto){
	console.log("codigo pasado como parametro: "+producto.codigo);
	if(id === 'id02'){
		document.getElementById('id02').style.display='block';
		document.getElementById("updateprod").value = producto;
		document.getElementById("updateNombre").value = producto;
		document.getElementById("updateBonificacion").value = producto;
	}
	if(id === 'id03'){
		document.getElementById('id03').style.display='block'; 
		document.getElementById("deleteprod").value = producto;
	}
}


