package servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import controladores.CtrlActualizMasiva;
import controladores.CtrlArticulo;
import controladores.CtrlCliente;
import controladores.CtrlConvenio;
import controladores.CtrlCtactecliente;
import controladores.CtrlFactRec;
import controladores.CtrlGaranteMovFijo;
import controladores.CtrlMov_Stoc;
import controladores.CtrlParamUltNro;
import controladores.CtrlParametros;
import controladores.CtrlReferencia;
import controladores.CtrlVenta;
import controladores.CtrlVentasD;
import controladores.CtrlVentasM;
import entidades.ActualizMasivas;
import entidades.AdherentesGral;
import entidades.Articulo;
import entidades.Cliente;
import entidades.Ctactecliente;
import entidades.FactRec;
import entidades.GaranteMovFijo;
import entidades.Mov_stoc;
import entidades.MovimientoDetalleGral;
import entidades.ParamUltNro;
import entidades.Referencia;
import entidades.Usuario;
import entidades.Venta;
import entidades.VentasD;
import entidades.VentasGral;
import entidades.VentasM;
import reportes.HeaderFooterPageEvent;
import util.ApplicationException;

/**
 * Servlet implementation class MovimientoFijo
 */
@WebServlet(urlPatterns = {"/MovimientoFijo"})
public class MovimientoFijo extends HttpServlet {
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
    
	private String urlBMovFijo = "/amema/views/buscamovfijos.jsp";
	private String urlMovFijo = "/amema/views/movimientosfijos.jsp";
	private String urlMMovFijo = "/amema/forms/movfijos/muestraMovFijoForm.jsp";
	private String urlGMovFijo = "/amema/forms/movfijos/ABCGarantesForm.jsp";
	private String urlAMovFijo = "/amema/views/actualizaMovFijos.jsp";
	private String urlEMovFijo = "/amema/views/eliminaMovFijos.jsp";
	
	private CtrlVenta cVentas = null;
	private CtrlCliente cCliente = null; 
	private CtrlVentasM cVentasM = null;
	private CtrlArticulo cArticulo = null;
	private CtrlReferencia cReferencia = null; 
	private CtrlConvenio cConvenio = null;
	private CtrlGaranteMovFijo cGarantes = null;
	private CtrlCtactecliente cCtacte = null; 
	private CtrlVentasD cVentasD = null; 
	private CtrlFactRec cFactRec = null; 
	private CtrlActualizMasiva cActualizM = null; 
	private CtrlParamUltNro cParamUlt = null; 
	private CtrlParametros cParametros = null; 
	private CtrlMov_Stoc cMovStoc = null; 
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovimientoFijo() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("evento_buscar1") != null) {
			if(request.getParameter("socio") != null) {
				try { buscarSocioPorNombre(request, response); }
				catch(ApplicationException e) { e.printStackTrace(); }
			}
			if(request.getParameter("doc") != null) {
				try { buscarSocioPorDNI(request, response); }
				catch(ApplicationException e) { e.printStackTrace(); }
			}
		}
		
		if(request.getParameter("evento_buscar2") != null) {
			try { buscarSocio(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_alta") != null) {
			try { altaMovimiento(request, response); }
			catch (ApplicationException | ParseException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_eliminar") != null) {
			try { bajaMovimiento(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_modifica") != null) {
			try { modificaMovimiento(request, response); }
			catch(ApplicationException | ParseException e) { e.printStackTrace(); } 
		}
		
		if(request.getParameter("evento_consultar") != null) {
			try { consultaMovimiento(request, response); }
			catch(ApplicationException | ParseException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_imprimir") != null) {
			try { imprimirMovimiento(request, response); }
			catch(ApplicationException | ParseException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_consultaGarante") != null) {
			try { muestraGarantes(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_eliminaGarante") != null) {
			try { eliminaGarante(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
		
		if(request.getParameter("agregar_garante") != null) {
			try { agregaGarante(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_actualizar") != null) {
			try { actualizarMovimientos(request, response); }
			catch(ApplicationException | ParseException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_eliminaBuscar1") != null) {
			try { listoMovimientos(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_detalle") != null) {
			try { detalleMovimiento(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_eliminaMov") != null) {
			try { eliminaMovimiento(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
	}
	
	
	/* 
	 * METODOS PRIMARIOS 
	 */
	
	private void buscarSocioPorNombre(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cCliente = new CtrlCliente();
		req.getSession().setAttribute("lista", cCliente.listarClientePorNombre(req.getParameter("dato")));
		cCliente = null; 
		res.sendRedirect(urlBMovFijo);
	}
	
	private void buscarSocioPorDNI(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cCliente = new CtrlCliente();
		muestraDatos(req, res, cCliente.consultaClientePorDNI(req.getParameter("dato")));
	}
	
	private void buscarSocio(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cCliente = new CtrlCliente(); 
		muestraDatos(req, res, cCliente.consultaCliente(req.getParameter("socio")));
	}
	
	private void muestraDatos(HttpServletRequest req, HttpServletResponse res, Cliente c) throws ApplicationException, IOException {
		req.getSession().setAttribute("socio", limpiarDatos(c));
		//Declaro los controladores 
		CtrlArticulo ca = new CtrlArticulo();
		cVentas = new CtrlVenta();
				
		//Declaros los arrays que voy a ir usando
		ArrayList<Venta> lventas = new ArrayList<>();
		ArrayList<AdherentesGral> lrta = new ArrayList<>();
		
		//Declaros las entidades que necesito.
		AdherentesGral rta = null;
		Articulo a = null;
				
		//Declaro variables varias para usar
		String cgrupo, csubf, nroart, codart;
				
				
		//Busco las ventas del socio 
		lventas = cVentas.listarVentaPorSocio(c.getCODCLI());
				
				
		//Por cada venta que encontre devuelvo los valores 
		for(Venta lv : lventas) {
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			codart = lv.getCODART();
			cgrupo = codart.substring(0,2);
			csubf = codart.substring(2,3);
			nroart = codart.substring(3,6);
			a = new Articulo();
			a = ca.consultarArticulo(cgrupo, csubf, nroart);
			String fecDesde = f.format(lv.getFEC_DESDE());
			String fecHasta = f.format(lv.getFVTO());
			rta = new AdherentesGral(lv.getNROMOV(), fecDesde, fecHasta, lv.getANALISIS(), lv.getCODART(), lv.getREFERENCIA(),
						a.getDESART(), lv.getPRECIO(), (int) a.getUNIDAD(), a.getENVASE(), lv.getINNCTACTE(), lv.getVA_DTO(), null);
			lrta.add(rta);
			a = null;
			rta = null;
		}
		cVentas = null;
		ca = null;
		
		req.getSession().setAttribute("movimientos", lrta);
		res.sendRedirect(urlMovFijo);
	}
	
	private void altaMovimiento(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, ParseException, IOException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String msj = "";
		cCliente = new CtrlCliente();
		
		// Recupero el ultimo nro de comprobante desde VentasM
		cVentasM = new CtrlVentasM();
		String ncomp = cVentasM.ultimoID();
		
		// Recupero los datos del formulario
		
		String codcli = req.getParameter("socio").substring(0,4);
		int nromov = Integer.parseInt(req.getParameter("movimiento"));
		String codart = req.getParameter("articulo");
		double unidades = Double.parseDouble(req.getParameter("cantidad"));
		double precio = Double.parseDouble(req.getParameter("importe"));
		Date fecDesde = f.parse(req.getParameter("fecIni"));
		Date fvto = f.parse(req.getParameter("fecFin"));
		String incctacte = req.getParameter("modoGeneracion");
		String referencia = req.getParameter("referencia");
		String analisis = req.getParameter("prioridad");
		String va_dto = req.getParameter("estado");
		String ubic1 = req.getParameter("nroCheque");
		String ubic2 = req.getParameter("banco");
		double impch = Double.parseDouble(req.getParameter("impCheque"));
		String cancDeuda = req.getParameter("cancelaDeuda");
		double impCancDeuda = Double.parseDouble(req.getParameter("impCancela"));
		String textlib = req.getParameter("observaciones");
		String convenio = req.getParameter("convenio").substring(0,2);
		
		// Calculo los datos que necesito. Como ser el nro de comprobante
		int cant = Integer.parseInt(codart.substring(4));
		ncomp = calculoComprobante(ncomp, cant);
		
		// Creo la variable Venta
		
		Venta v = new Venta();
		
		v.setNCOMP(ncomp);
		v.setTCOMP("1");
		v.setLETRA("B");
		v.setCIA("1");
		v.setFCOMP(fecDesde);
		v.setNFACC("00000000");
		v.setFVTO(fvto);
		v.setCODCLI(codcli);
		v.setREGCLI("3");
		v.setOBSERV(null);
		v.setCPERS1("00");
		v.setCPERS2("00");
		v.setCPERS3("00");
		v.setCVTO("01");
		v.setNROREMITO("0");
		v.setNROPEDIDO("0");
		v.setNROPRESUP("0");
		v.setNVIAJ("99");
		v.setDIRECTA("S");
		v.setREFERENCIA(referencia);
		v.setLIQUIDA("N");
		v.setCOMI_DIFE("N");
		v.setINNCTACTE(incctacte);
		v.setDESPACHA("-");
		v.setTEXTLIB(textlib);
		v.setTEXTO(0.0);
		v.setFLETE(0.0);
		v.setCCOND_1(convenio);
		v.setCCOND_2(null);
		v.setCCOND_3(null);
		v.setCCOND_4(null);
		v.setPORDESCTO(0.0);
		v.setPORBONIF(1.0);
		v.setVA_DTO(va_dto);
		v.setCODART(codart);
		v.setTASA(0.0);
		v.setDESPACHO(null);
		v.setTIVA(null);
		v.setBONART(0.0);
		v.setBONART2(0.0);
		v.setPRECIO(precio);
		v.setUNIDADES(unidades);
		v.setUBICAC1(ubic1);
		v.setUBICAC2(ubic2);
		v.setUBICAC3("-");
		v.setANALISIS(analisis);
		v.setFEC_DESDE(fecDesde);
		v.setNROMOV(nromov);
		v.setIMPCH(impch);
		v.setCANCDEUANT(cancDeuda);
		v.setIMPCANCDEUANT(impCancDeuda);
		
		// Hago el alta
		
		cVentas = new CtrlVenta();
		if(cVentas.altaVenta(v) == true) { msj = "siAlta"; }
		else { msj = "noAlta"; }
		
		//Limpio las variables que no uso
		cVentas = null;
		cVentasM = null; 
		v = null;
		
		//Devuelvo los datos
		req.getSession().setAttribute("msj", msj);
		muestraDatos(req, res, cCliente.consultaCliente(codcli));
	}

	private void bajaMovimiento(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		// Creo las variables que necesito
		int nromov = Integer.parseInt(req.getParameter("codigo"));
		String msj, codcli; 
		
		
		cVentas = new CtrlVenta();
		cCliente = new CtrlCliente();
		Cliente cli = null; 
		
		
		System.out.println("NRO MOV "+nromov);
		//Recupero el codigo de cliente asociado al movimiento
		codcli = cVentas.consultarClientePorNroMov(nromov);
		
		
		//Realizo la baja y pregunto si salio todo bien, y de eso depende el mensaje que voy a mostrar en pantalla
		if(cVentas.bajaVenta(nromov) == true) { msj = "siBaja"; }
		else { msj = "noBaja"; }
		
		cli = cCliente.consultaCliente(codcli);
	
		//Finalizo las variables
		cVentas = null;
		cCliente = null; 
		
		//devuelvo los valores.
		req.getSession().setAttribute("msj", msj);
		muestraDatos(req, res, cli);
	}
	
	private void modificaMovimiento(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, ParseException, IOException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String msj = "";
		cCliente = new CtrlCliente();
		cVentas = new CtrlVenta();
		
		// Recupero los datos del formulario
		
		String codcli = req.getParameter("socio").substring(0,4);
		int nromov = Integer.parseInt(req.getParameter("movimiento"));
		String codart = req.getParameter("articulo");
		double unidades = Double.parseDouble(req.getParameter("cantidad"));
		double precio = Double.parseDouble(req.getParameter("importe"));
		Date fecDesde = f.parse(req.getParameter("fecIni"));
		Date fvto = f.parse(req.getParameter("fecFin"));
		String incctacte = req.getParameter("modoGeneracion");
		String referencia = req.getParameter("referencia");
		String analisis = req.getParameter("prioridad");
		String va_dto = req.getParameter("estado");
		String ubic1 = req.getParameter("nroCheque");
		String ubic2 = req.getParameter("banco");
		double impch = Double.parseDouble(req.getParameter("impCheque"));
		String cancDeuda = req.getParameter("cancelaDeuda");
		double impCancDeuda = Double.parseDouble(req.getParameter("impCancela"));
		String textlib = req.getParameter("observaciones");
		String convenio = req.getParameter("convenio").substring(0,2);
		
		// Recupero los datos de la venta asociada al nr de mov. fijo.
		
		Venta v = cVentas.ConsultaVentaPorNroMov(nromov);
		
		// Seteo todos los datos que recupere del formulario, el resto no lo toco 
		
		v.setFCOMP(fecDesde);
		v.setFVTO(fvto);
		v.setCODCLI(codcli);
		v.setREFERENCIA(referencia);
		v.setINNCTACTE(incctacte);
		v.setTEXTLIB(textlib);
		v.setCCOND_1(convenio);
		v.setVA_DTO(va_dto);
		v.setCODART(codart);
		v.setPRECIO(precio);
		v.setUNIDADES(unidades);
		v.setUBICAC1(ubic1);
		v.setUBICAC2(ubic2);
		v.setANALISIS(analisis);
		v.setFEC_DESDE(fecDesde);
		v.setNROMOV(nromov);
		v.setIMPCH(impch);
		v.setCANCDEUANT(cancDeuda);
		v.setIMPCANCDEUANT(impCancDeuda);
		
		// Hago la modificacion
		
		if(cVentas.modificaVenta(v) == true) { msj = "siModifica"; }
		else { msj = "noModifica"; }
		
		//Limpio las variables que no uso
		cVentas = null;
		v = null;
		
		//Devuelvo los datos
		req.getSession().setAttribute("msj", msj);
		muestraDatos(req, res, cCliente.consultaCliente(codcli));
	}
	
	private void consultaMovimiento(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, ParseException, IOException {
		int nromov = Integer.parseInt(req.getParameter("codigo"));
		VentasGral vg = cargoDatos(nromov);
		req.getSession().setAttribute("venta", vg);
		res.sendRedirect(urlMMovFijo);
	}
	
	private void imprimirMovimiento(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, ParseException, IOException {
		
		int nro = Integer.parseInt(req.getParameter("movimiento"));
		VentasGral vg = cargoDatos(nro);
		generaPDF(req, res, vg);
	}
	
	private void muestraGarantes(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		// Recupero los datos e inicializo las variables que voy a utilizar para completar la tabla del form
		int nro = Integer.parseInt(req.getParameter("codigo"));
		cGarantes = new CtrlGaranteMovFijo();
		cCliente = new CtrlCliente(); 
		cVentas = new CtrlVenta();
		cArticulo = new CtrlArticulo();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		
		// Recupero los datos de la BBDD para devolver al form
		
		Venta v = cVentas.ConsultaVentaPorNroMov(nro);
		Articulo art = cArticulo.consultarArticulo(v.getCODART().substring(0,2), v.getCODART().substring(2,3), v.getCODART().substring(3));
		String articulo = art.getDESART();
		ArrayList<GaranteMovFijo> lista = cGarantes.listarGarantesPorMovimientos(nro); 
		
		if(lista != null) {
			for(GaranteMovFijo g: lista) {
				Cliente c = cCliente.consultaCliente(g.getNroGarante());
				clientes.add(c);
			}
		}
		
		// asigno los datos al req.
		req.getSession().setAttribute("nro", nro);
		req.getSession().setAttribute("articulo", articulo);
		req.getSession().setAttribute("garantes", clientes);
		
		
		//Limpio las variables que use
		cGarantes = null;
		cCliente = null; 
		cVentas = null;
		cArticulo = null;
		v = null;
		art = null;
		
		// direcciono a la pantalla
		res.sendRedirect(urlGMovFijo);
	}
	
	private void agregaGarante(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cGarantes = new CtrlGaranteMovFijo();
		int nromov = (int) req.getSession().getAttribute("nro");
		String codcli = req.getParameter("select");
		String msj = "";
		
		GaranteMovFijo gm = new GaranteMovFijo(codcli, nromov);
		
		if(cGarantes.altaGaranteMovF(gm) == true) { msj ="siAlta"; }
		else { msj = "noAlta"; }
		
		cGarantes = null; 
		
		//req.getSession().setAttribute("msj", msj);
		garante(req, res, nromov, msj);;	
	}
	
	private void eliminaGarante(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cGarantes = new CtrlGaranteMovFijo();
		int nromov = (int) req.getSession().getAttribute("nro");
		String codcli = req.getParameter("socio");
		String msj = "";
		if(cGarantes.bajaGaranteMovFPorGarante(codcli) == true) { msj = "siBaja"; }
		else { msj = "noBaja"; }
		
		cGarantes = null; 
		
		//req.getSession().setAttribute("msj", msj);
		garante(req, res, nromov, msj);
	}
	
	private void actualizarMovimientos(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException, ParseException {
		//recupero los datos del formulario
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date fecIni = f.parse(req.getParameter("fecIni"));
		Date fecFin = f.parse(req.getParameter("fecFin"));
		String modo = req.getParameter("modo");
		
		//inicializo las variables que voy a usar
		cVentas = new CtrlVenta();
		cVentasM = new CtrlVentasM();
		cActualizM = new CtrlActualizMasiva(); 
		ArrayList<Venta> lVentas = new ArrayList<>();
		String msj = "";
		int ultNro; 
		Date fecha = new Date(); 
		boolean r = false; 
		
		
		//Recupero el ultimo nroactualiz para hacer la actualizacion masiva
		ultNro = cVentasM.ultimoNroActualiz();
		ultNro += 1;

		
		//Actualizo los datos en la tabla actualizMasivas
		ActualizMasivas aMasivas = new ActualizMasivas(ultNro, fecha, fecIni, fecFin, "N");
		if(cActualizM.altaActualizMasiva(aMasivas) == true) { r = true; }
		
		//recupero las ventas que se ajusten a los criterios que recupere en el formulario
		if( r == true) {
			System.out.println("entra a las opciones");
			//Primero recupero las opciones M o A, segun corresponda
			if(modo.equals("A") || modo.equals("M")) { 
				lVentas = cVentas.listarVentasPorFechasyModo(fecIni, fecFin, "M"); 
				System.out.println("entra a la primera opcion");
				//Recorro el array y guardo los datos correspondientes
				for(Venta v: lVentas) {
					if(asignoVentas(v, fecIni, fecFin) == true) { r = true; System.out.println("OK");}
					else { 
						r = false;
						System.out.println("Falla");
						break;
					}
					if(procactfactvta(v, 1) == true && r == true) { r = true; System.out.println("ok");}
					else {
						r = false;
						System.out.println("falla2");
						break;
					}
				}
			}
			
			//Segundo recupero las opciones T o A, segun corresponda
			if(modo.equals("A") || modo.equals("T")) { 
				lVentas = cVentas.listarVentasPorFechasyModo(fecIni, fecFin, "T"); 
				
				//Recorro el array y guardo los datos correspondientes
				for(Venta v: lVentas) {
					if(asignoVentas(v, fecIni, fecFin) == true) { r = true; }
					else { 
						r = false;
						break;
					}
					if(procactfactvta(v, 1) == true && r == true) { r = true; }
					else {
						r = false;
						break;
					}
				}
			}
		}
		
		if(r == true) { msj = "siActualiza"; }
		else { msj = "noActualiza"; }
		
		//Finalizo las variables que use y redirijo.
		cVentas = null;
		cVentasM = null;
		cActualizM = null;
		
		req.getSession().setAttribute("msj", msj);
		res.sendRedirect(urlAMovFijo);
	}
	
	private void listoMovimientos(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		// recupero los datos del form
		String codcli = req.getParameter("select").substring(0,4);
		
		//Inicializo las variables que voy a usar
		cVentas = new CtrlVenta();
		
		//recupero la data y la seteo en el req para devolver al form.
		req.getSession().setAttribute("socio", req.getParameter("select"));
		req.getSession().setAttribute("ventas", cVentas.listarVentaPorSocio(codcli));
		
		//Finalizo las variables que use para liberar memoria
		cVentas = null; 
		
		//Reenvio la informacion al formulario para seguir con las busquedas
		res.sendRedirect(urlEMovFijo);
		
	}
	
	private void detalleMovimiento(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		// recupero los datos del form
		int nromov = Integer.parseInt(req.getParameter("nroMov"));
		
		// incializo las variables que voy a usar
		cVentas = new CtrlVenta();
		cArticulo = new CtrlArticulo();
		Venta v = null; 
		Articulo a = null; 
		MovimientoDetalleGral m = null; 
		
		// recupero los datos de la bbdd
		v = cVentas.ConsultaVentaPorNroMov(nromov);
		double debe = calculoDebe(nromov);
		double pago = calculoPago(nromov);
		a = cArticulo.consultarArticulo(v.getCODART().substring(0,2), v.getCODART().substring(2,3), v.getCODART().substring(3));
		
		m = new MovimientoDetalleGral(nromov, v.getFCOMP(), a.getDESART(), debe, pago);
		
		// seteo las variables que voy a devolver
		req.getSession().setAttribute("detalle", m);
		
		// finalizo las variables que no uso mas para liberar memoria
		cVentas = null; 
		cArticulo = null; 
		v = null; 
		a = null;
		m = null;
		
		// vuelvo al jsp
		res.sendRedirect(urlEMovFijo);
	}
	
	private void eliminaMovimiento(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		
		//recupero los datos del form
		int nro = Integer.parseInt(req.getParameter("movEliminar"));
		
		//inicializo las variables que voy a usar
		cVentas = new CtrlVenta();
		cVentasD = new CtrlVentasD();
		cVentasM = new CtrlVentasM();
		cFactRec = new CtrlFactRec();
		cCtacte = new CtrlCtactecliente();
		boolean rVentas = false;
		boolean rVentasD = false;
		boolean rVentasM = false;
		boolean rFactRec = false;
		boolean rCtaCte = false;
		String msj =""; 
		ArrayList<VentasM> listadoComprobantes = cVentasM.listarVentasMPorNroMov(nro);
		
		//// realizo la baja de los elementos y asigno la respuesta para el mensaje posteriormente
		if(cVentas.bajaVenta(nro) == true) { rVentas = true;}
		
		for(VentasM v: listadoComprobantes) {
			if(cCtacte.bajaCtaCtePorCompOrig("0000"+v.getNCOMP()) == true || cCtacte.consultarComprobanteCta("0000"+v.getNCOMP()) == null ) { rCtaCte = true; }
			else {
				rCtaCte = false; 
				break;
			}
		}
		
		for(VentasM v: listadoComprobantes) {
			if(cVentasD.bajaVentasD(v.getNCOMP()) == true || cVentasD.consultaVentasD(v.getNCOMP()) == null) { rVentasD = true; }
			else {
				rVentasD = false; 
				break;
			}
		}
		
		for(VentasM v: listadoComprobantes) {
			if(cFactRec.bajaFacturaPorComprobante(v.getNCOMP()) == true || cFactRec.consultarFacturaPorNroComprobante(v.getNCOMP()) == null) { rFactRec = true; }
			else {
				rFactRec = false; 
				break;
			}
		}
		
		if(cVentasM.bajaVentasMPorMovimiento(nro) == true || cVentasM.listarVentasMPorNroMov(nro) == null) { rVentasM = true; }
		
		if(rVentas == true && rVentasD == true && rVentasM == true && rFactRec == true && rCtaCte == true) { msj = "siElimina"; }
		else { msj = "noElimina"; }
		
		// finalizo todas las variables que use para liberar memoria
		cVentas = null;
		cVentasD = null;
		cVentasM = null;
		cFactRec = null;
		cCtacte = null;
		
		req.getSession().setAttribute("msj", msj);
		res.sendRedirect(urlEMovFijo);
	}
	/*
	 * 
	 * METODOS SECUNDARIOS
	 * 
	 */
	
	private Cliente limpiarDatos(Cliente c) throws ApplicationException {
		if(c.getTELCLI_1() == null) { c.setTELCLI_1("S/Nro"); }
		if(c.getTELCLI_2() == null) { c.setTELCLI_2("S/Nro"); }
		if(c.getOBSCLI() == null) { c.setOBSCLI("S/Obs"); }
		CtrlConvenio cc = new CtrlConvenio();
		String nro = c.getCCOND();
		c.setCCOND(nro+" - "+cc.buscaDescripcion(nro));
		return c;
	}
	
	private String calculoComprobante(String ncomp, int cant) throws ApplicationException {
		
		int aux = Integer.parseInt(ncomp) + cant;
		ncomp = Integer.toString(aux);
		
		
		switch (ncomp.length()) {
		case 1:
			ncomp = "0000000"+ncomp;
			break;
		case 2:
			ncomp = "000000"+ncomp;
			break;
		case 3:
			ncomp = "00000"+ncomp;
			break;
		case 4:
			ncomp = "0000"+ncomp;
			break;
		case 5:
			ncomp = "000"+ncomp;
			break;
		case 6:
			ncomp = "00"+ncomp;
			break;
		case 7:
			ncomp = "0"+ncomp;
			break;
		default:
			break;
		}
		return ncomp;
	}
	
	private VentasGral cargoDatos(int nro) throws ApplicationException, ParseException {
	
		VentasGral vg = new VentasGral();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String modoGeneracion = "Generar mensualmente las cuotas";
		
		// declaro los controladores que voy a usar para recuperar la info
		cVentas = new CtrlVenta();
		Venta v = cVentas.ConsultaVentaPorNroMov(nro);
		
		cCliente = new CtrlCliente();
		Cliente cli = cCliente.consultaCliente(v.getCODCLI());

		cArticulo = new CtrlArticulo();
		Articulo a = cArticulo.consultarArticulo(v.getCODART().substring(0,2), v.getCODART().substring(2,3), v.getCODART().substring(3));
		
		
		if(v.getINNCTACTE() == "T") { modoGeneracion = "Generar todas las cuotas"; }
		
		cReferencia = new CtrlReferencia();
		Referencia r = cReferencia.consultaReferencia(v.getREFERENCIA());
		
		cConvenio = new CtrlConvenio();
		String descConvenio = cConvenio.buscaDescripcion(v.getCCOND_1());
		

		//Cargo los datos a la variable
		vg.setCliente(cli.getCODCLI()+" - "+cli.getNOMCLI());
		vg.setNromov(Integer.toString(nro));
		vg.setArticulo(a.getDESART());
		vg.setCuotas(v.getCODART().substring(4));
		vg.setCantidad(String.valueOf(v.getUNIDADES()));
		vg.setImporte(String.valueOf(v.getPRECIO()));
		vg.setFecDesde(f.format(v.getFCOMP()));
		vg.setFecHasta(f.format(v.getFVTO()));
		vg.setModoGeneracion(v.getINNCTACTE()+" - "+modoGeneracion);
		vg.setReferencia(r.getCREF()+" - "+r.getNREF());
		vg.setAnalisis(v.getANALISIS());
		vg.setEstado(v.getVA_DTO());
		vg.setConvenio(v.getCCOND_1()+" - "+descConvenio);
		vg.setNroCheque(v.getUBICAC1());
		vg.setBancoCheque(v.getUBICAC2());
		vg.setImpCheque(String.valueOf(v.getIMPCH()));
		vg.setCancelaDeuda(v.getCANCDEUANT());
		vg.setImpCancelaDeuda(String.valueOf(v.getIMPCANCDEUANT()));
		vg.setObservaciones(v.getTEXTLIB());
		
		
		// finalizo los controladores 
		cVentas = null;
		cCliente = null;
		cArticulo = null;
		cReferencia = null;
		cConvenio =null;
		
		return vg;
		
	}	

	private void generaPDF(HttpServletRequest request, HttpServletResponse response, VentasGral v) throws IOException {
		
		//Declaro las variables que voy a usar.
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioActivo");
		Document documento = new Document(PageSize.A4, 36, 36, 90, 36);		
				
		try {
				response.setContentType("application/pdf");
				OutputStream out = response.getOutputStream();
				PdfWriter writer = PdfWriter.getInstance(documento, out);
					
				// Add header and footer
				HeaderFooterPageEvent event = new HeaderFooterPageEvent(usuario.getNomUs());
				writer.setPageEvent(event);
					
				//Seteo las fuentes
				Font f1 = new Font(FontFamily.HELVETICA,13,Font.BOLDITALIC, BaseColor.DARK_GRAY);
				Font f2 = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLUE); // Fuentes de los titulos del reporte.
				Font f3 = new Font(FontFamily.HELVETICA, 10, Font.NORMAL); //Fuente de los textos.
					
				/*
				* DEFINO EL TITULO PRINCIPAL 
				*/

				Paragraph t1 = new Paragraph(new Phrase("-- Consulta de Movimiento Fijo -- ", f1));
					t1.setAlignment(Element.ALIGN_CENTER);
					t1.add(Chunk.NEWLINE);
					t1.add(Chunk.NEWLINE);
					
					Paragraph p1 = new Paragraph();
					p1.add(new Phrase("Socio: ",f2));
					p1.add(new Phrase(v.getCliente(),f3));
					Paragraph p2 = new Paragraph();
					p2.add(new Phrase("Nro Movimiento: ",f2));
					p2.add(new Phrase(v.getNromov(),f3));
					

					// Defino la tabla
					PdfPTable dp = new PdfPTable(4);
					dp.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					
					
					// Defino las celdas
					PdfPCell blanco = new PdfPCell(new Paragraph(" ")); // Es un blanco para crear espacio completo
					blanco.setBorder(Rectangle.NO_BORDER);
					blanco.setColspan(4);
					
					PdfPCell celdaBlanca = new PdfPCell(new Paragraph(" "));
					celdaBlanca.setBorder(Rectangle.NO_BORDER);
					
					
					
					PdfPCell dpc1 = new PdfPCell(p1);  // Datos del socio
					dpc1.setBorder(Rectangle.NO_BORDER);
					dpc1.setColspan(2);
					PdfPCell dpc2 = new PdfPCell(p2);	// nro de movimiento
					dpc2.setBorder(Rectangle.NO_BORDER); 
					dpc2.setColspan(2); 
					
					// Asigno las celdas a la tabla		
					dp.addCell(dpc1);
					dp.addCell(dpc2);
					dp.addCell(blanco);
					
					
					/*
					 * DEFINO LOS PARAMETROS DE LOS DATOS LABORALES
					 */

					Paragraph t2 = new Paragraph();
					t2.add(new Phrase("-- Datos del movimiento -- ", f1));
					t2.setAlignment(Element.ALIGN_CENTER);
					t2.add(Chunk.NEWLINE);
					t2.add(Chunk.NEWLINE);
					
					Paragraph l1 = new Paragraph();
					l1.add(new Phrase("Cód. Movimiento: ",f2));
					l1.add(new Phrase(v.getArticulo(),f3));
					Paragraph l2 = new Paragraph();
					l2.add(new Phrase("Cuotas: ",f2));
					l2.add(new Phrase(v.getCuotas(),f3));
					Paragraph l3 = new Paragraph();
					l3.add(new Phrase("Cantidad: ",f2));
					l3.add(new Phrase(v.getCantidad(),f3));
					Paragraph l4 = new Paragraph();
					l4.add(new Phrase("Importe: $ ",f2));
					l4.add(new Phrase(v.getImporte(),f3));
					Paragraph l5 = new Paragraph();
					l5.add(new Phrase("Fec. Inicio: ",f2));
					l5.add(new Phrase(v.getFecDesde(),f3));
					Paragraph l6 = new Paragraph();
					l6.add(new Phrase("Fec. Fin: ",f2));
					l6.add(new Phrase(v.getFecHasta(),f3));
					Paragraph l7 = new Paragraph();
					l7.add(new Phrase("Modo de generación: ",f2));
					l7.add(new Phrase(v.getModoGeneracion(),f3));
					Paragraph l8 = new Paragraph();
					l8.add(new Phrase("Referencia: ",f2));
					l8.add(new Phrase(v.getReferencia(),f3));
					Paragraph l9 = new Paragraph();
					l9.add(new Phrase("Prioridad de Cobro: ",f2));
					l9.add(new Phrase(v.getAnalisis(),f3));
					Paragraph l10 = new Paragraph();
					l10.add(new Phrase("Estado: ",f2));
					l10.add(new Phrase(v.getEstado(),f3));
					Paragraph l11 = new Paragraph();
					l11.add(new Phrase("Convenio: ",f2));
					l11.add(new Phrase(v.getConvenio(),f3));
					Paragraph l12 = new Paragraph();
					l12.add(new Phrase("Nro Cheque: ",f2));
					l12.add(new Phrase(v.getNroCheque(),f3));
					Paragraph l13 = new Paragraph();
					l13.add(new Phrase("Cheque Banco: ",f2));
					l13.add(new Phrase(v.getBancoCheque(),f3));
					Paragraph l14 = new Paragraph();
					l14.add(new Phrase("Imp. Cheque: ",f2));
					l14.add(new Phrase(v.getImpCheque(),f3));
					Paragraph l15 = new Paragraph();
					l15.add(new Phrase("Canc. deuda Ant: ",f2));
					l15.add(new Phrase(v.getCancelaDeuda(),f3));
					Paragraph l16 = new Paragraph();
					l16.add(new Phrase("Imp. Canc. deuda Ant: ",f2));
					l16.add(new Phrase(v.getImpCancelaDeuda(),f3));
					Paragraph l17 = new Paragraph();
					l17.add(new Phrase("Observaciones: ",f2));
					l17.add(new Phrase(v.getObservaciones(),f3));

					// Defino la tabla
					PdfPTable dl = new PdfPTable(3);
					dl.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					
					
					// Defino las celdas

					PdfPCell dlc1 = new PdfPCell(l1);  
					dlc1.setBorder(Rectangle.NO_BORDER);
					dlc1.setColspan(3);
					PdfPCell dlc2 = new PdfPCell(l2);	
					dlc2.setBorder(Rectangle.NO_BORDER); 
					PdfPCell dlc3 = new PdfPCell(l3); 
					dlc3.setBorder(Rectangle.NO_BORDER);
					PdfPCell dlc4 = new PdfPCell(l4); 
					dlc4.setBorder(Rectangle.NO_BORDER);
					PdfPCell dlc5 = new PdfPCell(l5);
					dlc5.setBorder(Rectangle.NO_BORDER);
					PdfPCell dlc6 = new PdfPCell(l6); 
					dlc6.setBorder(Rectangle.NO_BORDER);
					PdfPCell dlc7 = new PdfPCell(l7); 
					dlc7.setBorder(Rectangle.NO_BORDER);
					dlc7.setColspan(3);
					PdfPCell dlc8 = new PdfPCell(l8); 
					dlc8.setBorder(Rectangle.NO_BORDER);
					dlc8.setColspan(2);
					PdfPCell dlc9 = new PdfPCell(l9); 
					dlc9.setBorder(Rectangle.NO_BORDER);
					PdfPCell dlc10 = new PdfPCell(l10); 
					dlc10.setBorder(Rectangle.NO_BORDER);
					dlc10.setColspan(2);
					PdfPCell dlc11 = new PdfPCell(l11); 
					dlc11.setBorder(Rectangle.NO_BORDER);
					PdfPCell dlc12 = new PdfPCell(l12); 
					dlc12.setBorder(Rectangle.NO_BORDER);
					PdfPCell dlc13 = new PdfPCell(l13); 
					dlc13.setBorder(Rectangle.NO_BORDER);
					PdfPCell dlc14 = new PdfPCell(l14); 
					dlc14.setBorder(Rectangle.NO_BORDER);
					PdfPCell dlc15 = new PdfPCell(l15); 
					dlc15.setBorder(Rectangle.NO_BORDER);
					PdfPCell dlc16 = new PdfPCell(l16); 
					dlc16.setBorder(Rectangle.NO_BORDER);
					PdfPCell dlc17 = new PdfPCell(l17); 
					dlc17.setBorder(Rectangle.NO_BORDER);
					dlc17.setColspan(3);
					
					// Asigno las celdas a la tabla		
					dl.addCell(dlc1);
					dl.addCell(blanco);
					dl.addCell(dlc2);
					dl.addCell(dlc3);
					dl.addCell(dlc4);
					dl.addCell(blanco);
					dl.addCell(dlc5);
					dl.addCell(celdaBlanca);
					dl.addCell(dlc6);
					dl.addCell(blanco);
					dl.addCell(dlc7);
					dl.addCell(blanco);
					dl.addCell(dlc8);
					dl.addCell(dlc9);
					dl.addCell(blanco);
					dl.addCell(dlc10);
					dl.addCell(dlc11);
					dl.addCell(blanco);
					dl.addCell(dlc12);
					dl.addCell(dlc13);
					dl.addCell(dlc14);
					dl.addCell(blanco);
					dl.addCell(dlc15);
					dl.addCell(celdaBlanca);
					dl.addCell(dlc16);
					dl.addCell(blanco);
					dl.addCell(dlc17);
					dl.addCell(blanco);
					
					
					// Write to document
					documento.open();
					documento.add(t1);
					documento.add(dp);
					documento.add(t2);
					documento.add(dl);
					documento.close();
					
					
				} 
				catch (FileNotFoundException | DocumentException e) {e.printStackTrace();  } 
	}
	
	private void garante(HttpServletRequest req, HttpServletResponse res, int codigo, String msj) throws ApplicationException, IOException {
		// Recupero los datos e inicializo las variables que voy a utilizar para completar la tabla del form
				cGarantes = new CtrlGaranteMovFijo();
				cCliente = new CtrlCliente(); 
				cVentas = new CtrlVenta();
				cArticulo = new CtrlArticulo();
				ArrayList<Cliente> clientes = new ArrayList<Cliente>();
				
				
				// Recupero los datos de la BBDD para devolver al form
				
				Venta v = cVentas.ConsultaVentaPorNroMov(codigo);
				Articulo art = cArticulo.consultarArticulo(v.getCODART().substring(0,2), v.getCODART().substring(2,3), v.getCODART().substring(3));
				String articulo = art.getDESART();
				ArrayList<GaranteMovFijo> lista = cGarantes.listarGarantesPorMovimientos(codigo); 
				
				if(lista != null) {
					for(GaranteMovFijo g: lista) {
						Cliente c = cCliente.consultaCliente(g.getNroGarante());
						clientes.add(c);
					}
				}
				
				// asigno los datos al req.
				req.getSession().setAttribute("nro", codigo);
				req.getSession().setAttribute("articulo", articulo);
				req.getSession().setAttribute("garantes", clientes);
				
				
				//Limpio las variables que use
				cGarantes = null;
				cCliente = null; 
				cVentas = null;
				cArticulo = null;
				v = null;
				art = null;
				
				// direcciono a la pantalla
				req.getSession().setAttribute("msj", msj);
				res.sendRedirect(urlGMovFijo);
			}
	
	private boolean asignoVentas(Venta v, Date fecIni, Date fecFin) throws ApplicationException, ParseException {
		//Declaro las variables que voy a usar.
		//Variables
		boolean r = false; 
		ParamUltNro p = null; 
		String nroFact = null, nroPref = null, auxFecha; 
		int cantDias = 0; 
		int mes, año; 
		Date fechaComp; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//Controladores
		cParamUlt = new CtrlParamUltNro();
		cVentas = new CtrlVenta();
		
		//Recupero el ultimo nro segun las faturas 
		p = cParamUlt.consultaUltimoNro("factvta", v.getLETRA());
		
		if(p != null) {
			nroFact = String.format("%08d", p.getUltimonro());
			nroPref = String.format("%01d", p.getPrefijo());
		}
		
		//Calculo la cantidad de dias que pasaron para asignar las fechas correspondientes
		/*
		 * opc --> 1 - Dias
		 * 		   2 - Meses
		 *         3 - Año
		 */
		
		cantDias = obtengoDiaMesAño(v.getFEC_DESDE(), 1); 
		
		//Valido que los dias sean mayores a la fecha de inicio
		if(cantDias >= obtengoDiaMesAño(fecIni, 1)) {
			mes = obtengoDiaMesAño(fecIni, 2); 
			año = obtengoDiaMesAño(fecIni, 3);
			auxFecha = año+"-"+mes+"-"+cantDias;
		}
		else {
			mes = obtengoDiaMesAño(fecFin, 2); 
			año = obtengoDiaMesAño(fecFin, 3);
			auxFecha = año+"-"+mes+"-"+cantDias;
		}
		
		fechaComp = sdf.parse(auxFecha);
		
		//Actualizo la venta
		v.setFCOMP(fechaComp);
		v.setNCOMP(nroFact);
		v.setPORBONIF(Integer.parseInt(nroPref));
		r = cVentas.modificaVenta(v);
		
		//Actualizo el ultimo nro
		if(r == true) { r = actualizoUltNro("FACTVTA", v.getLETRA(), nroFact, nroPref); }
		
		//Finalizo las variables que uso y devuelvo r
		return r; 
	}
	
	private Date calculoFecha(Date fecha, int cant) throws ApplicationException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.MONTH, cant);
		return calendar.getTime();
	}
	
	private double calculoDebe(int nro) throws ApplicationException {
		//Inicializo las variables que voy a usar
		double saldo = 0.0;
		cVentasM = new CtrlVentasM();
		ArrayList<VentasM> listaVM = new ArrayList<>();
		
		//Recupero las ventasM que tiene el movimiento
		listaVM = cVentasM.listarVentasMPorNroMov(nro);
		
		//calculo el saldo de las ventas
		if(listaVM != null) {
			for(VentasM vm: listaVM) {
				saldo += vm.getSUBTOTAL();
			}
		}
		
		// finalizo las variables que no uso para liberar memoria
		cVentasM = null;
		
		return saldo;
	}
	
	private double calculoPago(int nro) throws ApplicationException {
		//Inicializo las variables que voy a usar
		double saldo = 0.0;
		cVentasM = new CtrlVentasM();
		cFactRec = new CtrlFactRec();
		FactRec factura = null;
		ArrayList<VentasM> listaVM = new ArrayList<>();
				
		//Recupero las ventasM que tiene el movimiento
		listaVM = cVentasM.listarVentasMPorNroMov(nro);
				
		//calculo el saldo de las facturas
		if(listaVM != null) { 
			for(VentasM vm: listaVM) { 
				factura = cFactRec.consultarFactura(vm.getNCOMP());
				if(factura != null) {
					saldo += factura.getMONTO_A(); 
				}
			}
		}
				
		// finalizo las variables que no uso para liberar memoria
		cVentasM = null;
		cFactRec = null;
		factura = null;
		
		
		return saldo;
	}
	
	private int obtengoDiaMesAño(Date fecha, int opc) throws ApplicationException {
		//Declaro las variables que voy a usar
		int rta = 0; 
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
		String[] arrayFecha = sdf.format(fecha).split("-"); 
		String dia, mes, año; 
		
		//ASigno las partes de la fecha a cada variable
		dia = arrayFecha[0]; 
		mes = arrayFecha[1];
		año = arrayFecha[2];
		
		//consulto la opcion para asignarla a rta
		switch (opc) {
		case 1:
			rta = Integer.parseInt(dia);
			break;
		case 2: 
			rta = Integer.parseInt(mes);
			break; 
		case 3:
			rta = Integer.parseInt(año);
		}
		
		//Devuelvo la respuesta
		return rta; 
	}
	
	private boolean actualizoUltNro(String desc, String letra, String nro, String prefijo) throws ApplicationException {
		//Declaro las variables
		boolean rta = false; 
		int ultimoNro = 0, pref = 0; 
		cParamUlt = new CtrlParamUltNro();
		ParamUltNro p = new ParamUltNro();
		
		//Asigno las variables 
		pref = Integer.parseInt(prefijo);
		ultimoNro = Integer.parseInt(nro)+1;
		p.setDescUltNro(desc);
		p.setLetra(letra);
		p.setUltimonro(ultimoNro);
		p.setPrefijo(pref);
		
		if(letra != "9") { rta = cParamUlt.modificaParamUltNroMF1(p); }
		else { rta = cParamUlt.modificaParamUltNroMF2(p); }
		
		
		//Finalizo las variables y devuelvo rta
		cParamUlt = null; 
		p = null; 
		return rta; 
	}
	
	private boolean procactfactvta(Venta v, int varnrocuota) throws ApplicationException {
		/*
		 * PROCESO ACTUALIZACION FACTURA VENTAS
		 */
		//Declaro las variables que voy a usar
		boolean rta = false; 
		String codconcpingcompraalkard, varobserv;
		double vsubtot, vimpiva, vregfact, vimpdolar, vcotdolar, vtasa, vpreccivapfacb; 
		double vstockanter, vimptexto;
		String vumedida, vcodcliactfactvta, cgrupo, csubf, nroart;
		int ultNro; 
		
		//Controladores
		cParametros = new CtrlParametros();
		cVentasM = new CtrlVentasM();
		cCtacte = new CtrlCtactecliente(); 
		cVentasD = new CtrlVentasD();
		cArticulo = new CtrlArticulo();
		cMovStoc = new CtrlMov_Stoc();
		
		//variables
		VentasM vm = null; 
		Ctactecliente c = null; 
		VentasD vd = null; 
		Articulo a = null; 
		Mov_stoc ms = null; 
		
		
		// Inicializo las variables que voy a usar para actualizar las VentasM, VentasD y Ctacte
		varobserv = "";
		vsubtot = 0;
		vimpiva = 0;
		vimpdolar = 0;
		vcotdolar = 0;
		vtasa = v.getTASA();
		if(v.getTEXTLIB() == null) { vimptexto = 0; }
		else { vimptexto = v.getTEXTO(); }
		vcodcliactfactvta = v.getCODCLI();
		
		
		//Busco el parametros de las ventas Kardex
		codconcpingcompraalkard = cParametros.consultaCompctaneto("CMEVENTA");
		if(!codconcpingcompraalkard.equals(null)) { rta = true; }
		
		if(rta == true) {
			// Obtengo la cotizacion del Dolar
			vcotdolar = cParametros.consultaCotDolar("dolar"); 
			if(vcotdolar == 0) { vcotdolar = 1; }
			
			// Asigno los datos a una variable para actualizar ventasM
			vm = new VentasM();
			vm.setNCOMP(v.getNCOMP());
			vm.setTCOMP(v.getTCOMP());
			vm.setLETRA(v.getLETRA());
			vm.setCIA("1");
			vm.setFMOV(v.getFCOMP());
			vm.setNFACC("00000000");
			vm.setCODCLI(v.getCODCLI());
			vm.setCPERS1(v.getCPERS1());
			vm.setCPERS2(v.getCPERS2());
			vm.setCPERS3(v.getCPERS3());
			vm.setNROREMITO(v.getNROREMITO());
			vm.setNROPEDIDO(v.getNROPEDIDO());
			vm.setNROPRESUP(v.getNROPRESUP());
			vm.setNVIAJ(v.getNVIAJ());
			vm.setDIRECTA(v.getDIRECTA());
			vm.setREFERENCIA(v.getREFERENCIA());
			vm.setLIQUIDA(v.getLIQUIDA());
			vm.setCOMI_DIFE(v.getCOMI_DIFE());
			vm.setFLETE(v.getFLETE());
			vm.setCCOND_1(v.getCCOND_1());
			vm.setPORDESCTO(0);
			vm.setPREFIJO(Double.toString(v.getPORBONIF()));
			vm.setVA_DTO(v.getVA_DTO());
			vm.setTASA(v.getTASA());
			vm.setPAGADO("N");
			vm.setANULADO("N");
			vm.setDOLAR(vcotdolar);
			vm.setTEXTLIB(v.getTEXTLIB());
			vm.setTEXTO(v.getTEXTO());
			vm.setCVTO(v.getCVTO());
			vm.setFECVTO(v.getFVTO());
			vm.setANALISIS(v.getANALISIS());
			vm.setNROMOVPLANIF(v.getNROMOV());
			
			if(cVentasM.altaVentasM(vm) == true) { rta = true; }
		}
		
		if( rta == true) {
			//Grabo registro en ctacte del cliente
			c = new Ctactecliente();
			c.setCODCLI(v.getCODCLI());
			c.setFMOV(v.getFCOMP());
			c.setTMOV("1");
			c.setTCOMP(v.getTCOMP());
			c.setLCOMP(v.getLETRA());
			c.setPCOMP(String.format("%04d", (int) v.getPORBONIF()));
			c.setNCOMP(String.format("%08d", Integer.parseInt(v.getNCOMP())));
			c.setFCOMPORIG(v.getFCOMP());
			c.setLCOMPORIG(v.getLETRA());
			c.setPCOMPORIG(String.format("%04d", (int) v.getPORBONIF()));
			c.setTCOMPORIG(v.getTCOMP());
			c.setNCOMPORIG(String.format("%08d", Integer.parseInt(v.getNCOMP())));
			c.setDEBE(0);
			c.setHABER(0);
			
			if(cCtacte.altaCtaCte(c) == true) { rta = true; }
		}
		
		if(rta == true) {
			if(v.getCODART() != "" && v.getCODART() != null) {
				// Grabo en VentasD
				vd = new VentasD();
				vd.setPREFIJO(String.format("%04d", (int) v.getPORBONIF()));
				vd.setNCOMP(v.getNCOMP());
				vd.setTCOMP(v.getTCOMP());
				vd.setLETRA(v.getLETRA());
				vd.setCIA("1");
				vd.setCODART(v.getCODART());
				vd.setUNIDADES(v.getUNIDADES());
				vd.setPBONIF(v.getBONART());
				vd.setPBONIF2(v.getBONART2());
				vd.setPVENTA(v.getPRECIO());
				
				if(cVentasD.altaVentasD(vd) == true) { rta = true; }
				
				// Actualizo el Stock de los articulos
				vstockanter = 0; 
				cgrupo = v.getCODART().substring(0, 2);
				csubf = v.getCODART().substring(2, 3);
				nroart = v.getCODART().substring(3, 6);
				
				//Busco el articulo deseado
				a = cArticulo.consultarArticulo(cgrupo, csubf, nroart);
				
				if( a != null) {
					vstockanter = vstockanter + a.getSTOCK_1();
					if(a.getDESART() == null) { varobserv ="-"; }
					else { varobserv = a.getDESART(); }
				}
				
				// resto las unidades vendidas al Stock 
				vstockanter = vstockanter - v.getUNIDADES(); 
				
				// Actualizo el stock en articulos
				if(cArticulo.actualizoStock(cgrupo, csubf, nroart, vstockanter) == true) { rta = true; }
				
				// Actualizo la tabla MOV_STOC
				ms = new Mov_stoc();
				ms.setCodart(v.getCODART());
				ms.setCdep("01");
				ms.setFmov(v.getFCOMP());
				ms.setCconcepto(codconcpingcompraalkard);
				ms.setCantidad(v.getUNIDADES());
				ms.setNcomp(String.format("%04d",(int) v.getPORBONIF())+v.getNCOMP());
				
				if(cMovStoc.altaMovStoc(ms) == true) { rta = true; }
				
				// Actualizo IMPIVA1
				a = cArticulo.consultarArticulo(cgrupo, csubf, nroart);
				vumedida = "1"; 
				if(a != null) {
					vumedida = Double.toString(a.getUNIDAD()); 
					varobserv = varobserv+" (Cuota "+varnrocuota+" de "+vumedida+")"; 
				}
				
				switch (v.getLETRA()) {
				case "A":
					vregfact = (precioBonificado(v.getPRECIO(), v.getBONART(), v.getBONART2(),0)*v.getUNIDADES())/Double.parseDouble(vumedida);
					vimpiva =+ vregfact; 
					break;
				case "B":
					vpreccivapfacb = 0; 
					vpreccivapfacb = v.getPRECIO() * (1+vtasa/100); 
					vpreccivapfacb = vpreccivapfacb * Double.parseDouble(vumedida);
					
					vregfact = precioBonificado(vpreccivapfacb, v.getBONART(), v.getBONART2(), 0) * v.getUNIDADES();
					vimpiva =+ vregfact; 
					break;
				}
				
			}
			
			if(v.getLETRA().equals("A")) {
				vsubtot = vimpiva + vimpiva*v.getTASA()/100; 
			}
			else {
				vsubtot = vimpiva; 
				vimpiva = vimpiva / (1 + (v.getTASA() / 100));
			}
			
			vimpdolar = vsubtot / vcotdolar; 
			
			// Actualizo totales de ventasM 
			//Recupero el ultimo nroactualiz para hacer la actualizacion masiva
			ultNro = cVentasM.ultimoNroActualiz();
			ultNro += 1;
			vm.setSUBTOTAL(vsubtot);
			vm.setIMPIVA_1(vimpiva);
			vm.setDOLAR(vimpdolar);
			vm.setOBSERV(varobserv);
			vm.setNROACTUALIZ(ultNro);
			vm.setA_CUENTA(0);
			
			if(cVentasM.modificoVentaM(vm) == true) { rta = true; }
		}
		
		
		
		//Finalizo las variables que uso y devuelvo rta
		
		return rta; 
	}
	
	private double precioBonificado(double vprecf, double vbon1f, double vbon2f, double vbon3f) throws ApplicationException {
		double b1, b2, b3, r;
		b1 = 1-(vbon1f/100);
		b2 = 1-(vbon2f/100);
		b3 = 1-(vbon3f/100);
		r = vprecf*b1*b2*b3; 
		return r;
	}
}
