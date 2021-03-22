<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Date" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Cliente" %>
<%@page import="entidades.VentasM" %>
<%@page import="entidades.CtacteGral"%>
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>AMEMA web - Gestion de Cuentas Corrientes</title>
		<%@ include file="../meta/metadata.jsp"%>
	</head>
	<body>
		<%@ include file="../views/menu.jsp"%>
		
		<!-- AREA DE TRABAJO -->
		<div class="w3-container w3-padding-64">
			<br>
			<div class="w3-container w3-card-4">
				<h3 class="w3-center w3-text-indigo">Datos del Socio</h3>
				<% 
					Cliente c = (Cliente) request.getSession().getAttribute("persona"); // Recupero los datos de la persona
					VentasM v = (VentasM) request.getSession().getAttribute("movimiento"); // Recupero los datos del detalle del mov seleccionado
					ArrayList<CtacteGral> movimientos = (ArrayList<CtacteGral>) request.getSession().getAttribute("movimientos"); 
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
					DecimalFormat df = new DecimalFormat("#0.00"); 
					
					// Si el movimiento seleccionado es distinto de NULL seteo los valores correspondientes. En caso contrario pongo todo en NULL.
					String ncomp = "-"; 
					String obs1 = "-"; 
					String ref  = "-";
					int planif = 0;
					int nAct = 0; 
					Date fecMov = new Date();
					String pagado = "-";
					String anulado = "-";
					String obs2 = "-";
					
					if(v != null) {
						ncomp = v.getNCOMP(); 
						obs1 = v.getOBSERV(); 
						ref  = v.getREFERENCIA();
						planif = v.getNROMOVPLANIF();
						nAct = v.getNROACTUALIZ();
						fecMov = v.getFMOV();
						pagado = v.getPAGADO();
						anulado = v.getANULADO();
						obs2 = v.getTEXTLIB();
					}
					
					//pregunto si los datos del socio son distintos a NULL
					if(c != null){	
				%>
				<br>
				<div class="w3-container w3-padding w3-half">
					<div class="w3-container w3-padding">
						<div class="w3-quarter">
							<label class="w3-text-indigo"><strong>Nro Socio: </strong></label>
						</div>
						<div class="w3-rest">
							<input type="text" class="w3-input" value="<%=c.getCODCLI()%>">
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-third">
							<label class="w3-text-indigo"><strong>Apellido y Nombre: </strong></label>
						</div>
						<div class="w3-rest">
							<input type="text" class="w3-input" value="<%=c.getNOMCLI()%>">
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-quarter">
							<label class="w3-text-indigo"><strong>Dirección: </strong></label>
						</div>
						<div class="w3-rest">
							<input type="text" class="w3-input" value="<%=c.getDOMCLI()%>">
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-quarter">
							<label class="w3-text-indigo"><strong>Telefono/s: </strong></label>
						</div>
						<div class="w3-rest">
							<input type="text" class="w3-input" value="<%=c.getTELCLI_1()%> - <%=c.getTELCLI_2()%>">
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-quarter">
							<label class="w3-text-indigo"><strong>DNI: </strong></label>
						</div>
						<div class="w3-rest">
							<input type="text" class="w3-input" value="<%=c.getTIPO_DOC()%>-<%=c.getCUITCLI()%>">
						</div>
					</div>
				</div>
				<div class="w3-container w3-half">
					<div class="w3-container w3-padding">
						<div class="w3-quarter">
							<label class="w3-text-indigo"><strong>Convenio: </strong></label>
						</div>
						<div class="w3-rest">
							<input type="text" class="w3-input" value="<%=c.getCCOND()%>">
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-quarter">
							<label class="w3-text-indigo"><strong>Empresa: </strong></label>
						</div>
						<div class="w3-rest">
							<input type="text" class="w3-input" value="<%=c.getCPCCP()%>">
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-quarter">
							<label class="w3-text-indigo"><strong>Legajo: </strong></label>
						</div>
						<div class="w3-rest">
							<input type="text" class="w3-input" value="<%=c.getDNRP()%>">
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-third">
							<label class="w3-text-indigo"><strong>Lugar de Trabajo: </strong></label>
						</div>
						<div class="w3-rest">
							<input type="text" class="w3-input" value="<%=c.getCONTACTO()%> - <%=c.getCONTACTO2()%>">
						</div>
					</div>
					<div class="w3-container w3-padding">
						<div class="w3-third">
							<label class="w3-text-indigo"><strong>Observaciones: </strong></label>
						</div>
						<div class="w3-rest">
							<input type="text" class="w3-input" value="<%=c.getOBSCLI()%>">
						</div>
					</div>
				</div>
				<br>
			</div>
			<br><br>
			<!-- Muestro los datos que recupero del socio -->

			<div class = "w3-container w3-padding w3-card-4" id="contenedor">
				<div class = "w3-container w3-padding w3-threequarter">
					<div id = "data" class = "w3-card-4">
						<table class="w3-table w3-bordered" id="myTable">
							<thead>
								<tr class="w3-indigo">
									<th onclick="sortTable(0)">Fecha</th>
									<th onclick="sortTable(1)">Concepto</th>
									<th onclick="sortTable(2)">Nro Mov.</th>
									<th onclick="sortTable(3)">Debe</th>
									<th onclick="sortTable(4)">haber</th>
									<th onclick="sortTable(5)">Saldo</th>
									<th>Acción</th>
								</tr>
							</thead>
							<tbody>
							<% if(movimientos != null) {
								for(CtacteGral m : movimientos){ %>
								<tr class="w3-hover-light-blue">
									<td onclick="buscar('<%=m.getNCOMP()%>')"><%=m.getFMOV() %></td>
									<td onclick="buscar('<%=m.getNCOMP()%>')"><%=m.getTMOV() %></td>
									<td onclick="buscar('<%=m.getNCOMP()%>')"><%=m.getNCOMP() %></td>
									<td onclick="buscar('<%=m.getNCOMP()%>')">$ <%=df.format(m.getDEBE()) %></td>
									<td onclick="buscar('<%=m.getNCOMP()%>')">$ <%=df.format(m.getHABER()) %></td>
									<td onclick="buscar('<%=m.getNCOMP()%>')">$ <%=df.format(m.getSALDO()) %></td>
									<td><button class="w3-button w3-purple w3-hover-indigo" onclick="abrirModalCta('mGaraCta','<%=m.getNCOMP()%>')"><i class = "fas fa-users"></i></button></td>
								</tr>
								<%}} %>
							</tbody>
						</table>
					</div>
				</div>
				
				<div class = "w3-container w3-padding w3-rest">
					<div id = "detalle" class = "w3-card-4">
						<div>
							<button class="w3-button w3-green w3-hover-indigo fas fa-expand-alt" onclick="mostrar()"></button>
						</div>
						<div class="w3-container w3-padding">
							<h3 class="w3-center  w3-text-indigo"> Detalle del movimiento <%=ncomp%></h3>
					<br> 
					<div class="w3-container w3-card-4">
						<div class="w3-twothird">
							<p style="width: 90%"><label class="w3-text-indigo"><strong>Origen del Movimiento: </strong></label><input class="w3-input" type="text" value="<%=ncomp%>" readonly></p>
							<p style="width: 90%"><label class="w3-text-indigo"><strong>Cantidad de Cuotas: </strong></label><input class="w3-input" type="text" value="<%=obs1%>" readonly></p>
							<p style="width: 90%"><label class="w3-text-indigo"><strong>Referencia: </strong></label><input class="w3-input" type="text" value="<%=ref%>" readonly></p>
							<p style="width: 90%"><label class="w3-text-indigo"><strong>Nro Planificación: </strong></label><input class="w3-input" type="text" value="<%=planif%> (Nro Actualiz: <%=nAct%>)" readonly></p>
						</div>
						<div class="w3-rest">
							<p><label class="w3-text-indigo"><strong>Fec. Movimiento: </strong></label><input class="w3-input" type="date" value="<%=fecMov%>" readonly></p>
							<p><label class="w3-text-indigo"><strong>Pagado?: </strong></label><input class="w3-input" type="text" value="<%=pagado%>" readonly></p>
							<p><label class="w3-text-indigo"><strong>Anulado?: </strong></label><input class="w3-input" type="text" value="<%=anulado%>" readonly></p>
							<p><label class="w3-text-indigo"><strong>Observaciones: </strong></label><textarea class="w3-input" rows="6" cols="60" readonly><%=obs2%></textarea></p>
						</div>
					</div>
						</div>
						<div class="w3-container w3-padding w3-center">
							<button class="w3-button w3-red w3-hover-indigo" onclick="cerrar()">Cerrar</button>
						</div>
					</div>
				</div>
			</div>
			<br><br>
			<div class="w3-container w3-card-4 w3-padding">
				<div class="w3-container w3-third w3-center">
					<a href="buscactactes.jsp" class="w3-button w3-green w3-hover-indigo"><strong>VOLVER</strong></a>
				</div>
				<div class="w3-container w3-third w3-center">
					<button class="w3-button w3-orange w3-hover-indigo w3-text-white" onclick="abrirModalCta('id03', '<%=c.getCODCLI()%>')"><strong>TRANSF. A EXCEL</strong></button>
				</div>
				<div class="w3-container w3-third w3-center">
					<button class="w3-button w3-blue w3-hover-indigo w3-text-white" onclick="abrirModalCta('id05', '<%=c.getCODCLI()%>')"><strong>RECONSTRUIR</strong></button>
				</div>
			</div>
			<br><br>
	
	
			<!-- AREAS DE MODALES -->
			<%@include file="../modal/ctacte/modalGarantesCtacte.jsp"%>
			<%@include file="../modal/ctacte/modalImprimirTodo.jsp"%>
			<%@include file="../modal/ctacte/modalReconstruir.jsp"%>

			<%} %>
		</div>

		<!-- FIN AREA DE TRABAJO -->

		<%@ include file="footer.jsp" %>
		<script type="text/javascript">
			function abrirModalCta(modal, codigo){
				document.getElementById(modal).style.display='block';
				if(modal == 'mImpCta'){
					document.getElementById("printadherente").value = codigo;
				}
				if(modal == 'id03'){
					document.getElementById("migrarExcel").value = codigo;
				}
				if(modal == 'mGaraCta'){
					document.getElementById("nrog").innerHTML = codigo;
					document.getElementById("datog").value = codigo;
				}
				if(modal ==  'id05'){
					document.getElementById("socioCta").value = codigo;
				}
			}	
			
			function buscar(valor){
				location.href="/amema/Cuenta?evento_detalle="+valor; 
			}

			function mostrar(){
				var detalle = document.getElementById("detalle");

				detalle.style.width = "70%";
				detalle.style.right = "2%";
				detalle.style.zIndex = "2";
				detalle.style.backgroundColor = "white";
			}

			function cerrar() {
				var detalle = document.getElementById("detalle");
				
				detalle.style.zIndex = "0"; 
				detalle.style.width = "25%"; 
				detalle.style.height = "initial"; 
				detalle.style.right = "2%";
			}

			function sortTable(n) {
				  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
				  table = document.getElementById("myTable");
				  switching = true;
				  dir = "asc"; // Seteo la forma en que se va a acomodar
				  while (switching) {
				    switching = false;
				    rows = table.rows;
				    for (i = 1; i < (rows.length - 1); i++) {
				      shouldSwitch = false;
				      x = rows[i].getElementsByTagName("TD")[n];
				      y = rows[i + 1].getElementsByTagName("TD")[n];
				      if (dir == "asc") {
				        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
				          shouldSwitch = true;
				          break;
				        }
				      } else if (dir == "desc") {
				        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
				          shouldSwitch = true;
				          break;
				        }
				      }
				    }
				    if (shouldSwitch) {
				      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
				      switching = true;
				      switchcount ++;
				    } else {
				      if (switchcount == 0 && dir == "asc") {
				        dir = "desc";
				        switching = true;
				      }
				    }
				  }
				}

		</script>
	</body>
</html>