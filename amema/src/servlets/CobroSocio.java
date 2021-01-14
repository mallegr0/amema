package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import controladores.CtrlAnticC;
import controladores.CtrlCheque;
import controladores.CtrlCtactecliente;
import controladores.CtrlFactRec;
import controladores.CtrlReciboM;
import controladores.CtrlVentasM;
import entidades.FactRec;
import entidades.ReciboM;
import entidades.VentasM;
import util.ApplicationException;

/**
 * Servlet implementation class CobroSocio
 */
@WebServlet(urlPatterns = {"/CobroSocio"})
public class CobroSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String urlCobroSocio = "/amema/views/consultaCobroSocio.jsp";
	private String urlDetalleCobro = "/amema/views/";
	private CtrlReciboM cRecibosM = null;
	private CtrlVentasM cVentasM = null; 
	private CtrlAnticC cAnticC = null; 
	private CtrlCheque cCheque = null; 
	private CtrlCtactecliente cCtacte = null; 
	private CtrlFactRec cFactRec = null; 
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CobroSocio() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("evento_buscarSocio") != null) {
			try {
				buscarComprobantes(request); 
				response.sendRedirect(urlCobroSocio);
			}
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_buscarCobro") != null) {
			try {
				buscarCobro(request); 
				response.sendRedirect(urlCobroSocio);
			}
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_VerDetalle") != null) {
			try {
				verDetalle(request);
				response.sendRedirect(urlDetalleCobro);
			}
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_eliminarPago") != null) {
			try {
				eliminarPago(request);
				response.sendRedirect(urlCobroSocio);
			}
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_Imprimir") != null) {
			try {
				imprimirPago(request);
				response.sendRedirect(urlCobroSocio);
			}
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
	}
	
	/*
	 * 
	 * 	METODOS PRIMARIOS
	 * 
	 */
	
	private void buscarComprobantes(HttpServletRequest req) throws ApplicationException {
		//Declaro las variables que voy a usar
		//Recupero el codcli del form.
		String codcli = req.getParameter("select");
		
		//Variables
		String msj; 
		
		//Controladores
		cRecibosM = new CtrlReciboM();
		
		//Arrays
		ArrayList<ReciboM> recibos = new ArrayList<>();
		
		//Recupero los nros de los recibos
		recibos = cRecibosM.listarRecibosMSocio(codcli);
		
		//Valido que haya recibos para el socio ingresado
		if(recibos.isEmpty()) { msj = null; }
		else { msj = "si"; }
		
		//Finalizo las variables que me consume memoria
		cRecibosM = null; 
		
		//Devuelvo la data
		req.getSession().setAttribute("hayData", msj);
		req.getSession().setAttribute("recibos", recibos);
	}
	
	
	private void buscarCobro(HttpServletRequest req) throws ApplicationException {
		//Declaro las variables
		//Controladores
		cRecibosM = new CtrlReciboM();
		
		//Recupero el nro de recibo desde el form
		String nroRecibo = (String) req.getParameter("recibo");
		
		//Recupero los datos del recibo seleccionado	
		req.getSession().setAttribute("dataRecibo", cRecibosM.consultaRecibo(nroRecibo));
		
		//Finalizo las variables que me consumen espacio
		cRecibosM = null;
	}

	private void verDetalle(HttpServletRequest req) throws ApplicationException {
		System.out.println("veo detalles jajaj");
	}
	
	private void eliminarPago(HttpServletRequest req) throws ApplicationException {
		//Este metodo lo que hace es eliminar el recibo en la tabla recibosm y volver atras todos 
		//los cambios que tengan involucrado este nro de recibo.
		
		//Declaro las variables que voy a usar
		//variables del form
		String aux = (String) req.getParameter("nrecibo"); 
		
		//Variables
		String[] pRecibos; 
		String msj;
		boolean deleteRecibos, actualizaVentas, deleteFactura, deleteCtaCte, deleteAnticC, deleteCheque;
		
		//Entidades
		ReciboM recM = null; 
		
		//Controladores
		cRecibosM = new CtrlReciboM(); 
		
		
		//recupero el nro de recibo y recupero el recibo
		pRecibos = aux.split(" - "); 
		recM = cRecibosM.consultaReciboPorNroyPrefijo(pRecibos[0], pRecibos[1]);
		
		//Asigno las respuestas de las eliminaciones y actualizaciones
		//Estan por orden de jerarquias. 
		deleteAnticC = eliminarAnticC(recM.getNRECIBO()); 
		deleteCheque = eliminarCheque(recM.getNRECIBO()); 
		deleteCtaCte = eliminarCtacte(recM.getNRECIBO());
		actualizaVentas = actualizarVentas(recM); 
		deleteFactura = eliminarFacturas(recM.getNRECIBO());
		deleteRecibos = eliminarRecibo(recM.getNRECIBO());
		
		if(deleteAnticC == true && deleteCheque == true && deleteRecibos == true && actualizaVentas == true && deleteFactura == true && deleteCtaCte == true) { msj ="siBaja"; }
		else { msj = "noBaja"; }
		
		req.getSession().setAttribute("msj", msj);
	}
	
	private void imprimirPago(HttpServletRequest req) throws ApplicationException {
		
	}
	
	/*
	 * 			 METODOS SECUNDARIOS
	 */
	
	private boolean eliminarAnticC(String nRecibo) throws ApplicationException {
		//variables
		cAnticC = new CtrlAnticC();
		boolean r = true; 
		
		//Lo que hago es ver si hay anticipos, si los hay, los elimino y ahi veo si falla o no la baja.
		// Si no los hay devuelvo verdadero para que siga el curso
		if(cAnticC.consultaAnticipoPorNroRecibo(nRecibo) != null) {
			if(cAnticC.bajaAnticipoPorNroRecibo(nRecibo) == false) { r = false; }
		}
		
		//Finalizo las variables y devuelvo r
		cAnticC = null; 
		
		return r; 	
	}
	
	private boolean eliminarCheque(String nRecibo) throws ApplicationException {
		//variables
		cCheque = new CtrlCheque();
		boolean r = true; 
		
		//Lo que hago es ver si hay cheques, si los hay, los elimino y ahi veo si falla o no la baja.
		// Si no los hay devuelvo verdadero para que siga el curso
		if(cCheque.consultaChequePorNroRecibo(nRecibo) != null) {
			if(cCheque.bajaChequePorNroRecibo(nRecibo) == false) { r = false; }
		}
		
		//Finalizo las variables y devuelvo r
		cCheque = null; 
		
		return r; 	
	}
	
	private boolean eliminarCtacte(String nroRecibo) throws ApplicationException {
		//Variables
		cCtacte = new CtrlCtactecliente(); 
		
		return cCtacte.bajaCtaCtePorComprobante(nroRecibo);
	}
	
	private boolean actualizarVentas(ReciboM rec) throws ApplicationException {
		//declaro las variables que voy a usar
		//Variables
		boolean rta = true; 
		
		//Antes recupere el recibo segun el prefijo y el nro que ingreso el usuario, ahora consulto si tiene anticipos
		//Si los tiene actualizo a_cta (a_cta - ianti) de cada recibosM que me indique
		if(!rec.getNANTI01().equals(null)) { rta = actualizarAnticipos(rec); } 
		
		//Antes recupere el recibo segun el prefijo y el nro que ingreso el usuario, ahora consulto si tiene notas de credito
		//Si los tiene actualizo a_cta (a_cta - icred) de cada recibosM que me indique
		if(!rec.getNCRED01().equals(null)) {rta = actualizarCreditos(rec); }
		
		//devuelvo rta
		return rta;
	}
	
	private boolean eliminarFacturas(String nroRecibo) throws ApplicationException {
		//Declaro las variables que voy a usar
		//Variables
		boolean rta = true;
		double suma; 
		
		//arrays
		ArrayList<FactRec> facturas = new ArrayList<>();
		
		//Controladores
		cFactRec = new CtrlFactRec();
		cVentasM = new CtrlVentasM();
		
		//Entidades
		VentasM v = null; 
		
		//Recupero todas las facturas relacionadas al nro de recibo
		facturas = cFactRec.listarFacturasPorNroRecibo(nroRecibo);
		
		//Recorro el array de las facturas y devuelvo las ventas correspondientes, para asi actualizar las ventas
		for(FactRec f : facturas) {
			v = cVentasM.consultaVentasM(f.getNCOMP()); 
			suma = v.getA_CUENTA() - f.getMONTO_A() - f.getDESCUENT_A(); 
			v.setA_CUENTA(suma);
			v.setPAGADO("N");
			if(cVentasM.modificaVentasMImporte(v) == false) {
				rta = false; 
				break;
			}
		}
		
		//Finalizo las variables que ocupan memoria y devuelvo rta
		cFactRec = null;
		cVentasM = null;
		v = null;
		
		return rta;
	}
	
	private boolean eliminarRecibo(String nroRec) throws ApplicationException {
		cRecibosM = new CtrlReciboM();
		return cRecibosM.bajaReciboPorNroRecibo(nroRec);
	}
	
	
	
	/*
	 * OTROS METODOS DE BACK UP
	 */
	
	private boolean actualizarAnticipos(ReciboM rec) throws ApplicationException {
		//declaro las variables que voy a usar
		//Controladores
		cRecibosM = new CtrlReciboM();
				
		//Entidades
		ReciboM r = null; 
		
		//Variables 
		boolean rta = true; 
		double suma; 
		
		//Arrays
		ArrayList<String> comprobantes = new ArrayList<String>();
		ArrayList<Double> importes = new ArrayList<Double>();
		
		//Cargo los nantis e iantis que esten en el recibo
		comprobantes = cargoNantis(rec); 
		importes = cargoIantis(rec);
		
		//recorro el array de comprobantes para hacer la busqueda y actualizacion
		for(int j = 0; j <= comprobantes.size(); j++) {
			r = cRecibosM.consultaReciboPorTipo(comprobantes.get(j), "2"); 
			suma = r.getA_CTA() - importes.get(j);
			if(cRecibosM.modificaActa(suma, comprobantes.get(j), "2", rec.getCODCLI()) == false) {
				rta = false; 
				break;
			}
		}
		
		//Finalizo todas las variables y devuelvo la rta
		cRecibosM = null;
		r = null;
		
		return rta;
	}
	
	private ArrayList<String> cargoNantis(ReciboM r) throws ApplicationException {
		ArrayList<String> c = new ArrayList<String>();
		
		if(!r.getNANTI01().equals(null)) { c.add(r.getNANTI01()); }
		if(!r.getNANTI02().equals(null)) { c.add(r.getNANTI02()); }
		if(!r.getNANTI03().equals(null)) { c.add(r.getNANTI03()); }
		if(!r.getNANTI04().equals(null)) { c.add(r.getNANTI04()); }
		if(!r.getNANTI05().equals(null)) { c.add(r.getNANTI05()); }
		if(!r.getNANTI06().equals(null)) { c.add(r.getNANTI06()); }
		if(!r.getNANTI07().equals(null)) { c.add(r.getNANTI07()); }
		if(!r.getNANTI08().equals(null)) { c.add(r.getNANTI08()); }
		if(!r.getNANTI09().equals(null)) { c.add(r.getNANTI09()); }
		if(!r.getNANTI10().equals(null)) { c.add(r.getNANTI10()); }
		
		return c;
	}
	
	private ArrayList<Double> cargoIantis(ReciboM r) throws ApplicationException {
		ArrayList<Double> c = new ArrayList<Double>();
		
		if(r.getIANTI01() > 0) { c.add(r.getIANTI01()); }
		if(r.getIANTI02() > 0) { c.add(r.getIANTI02()); }
		if(r.getIANTI03() > 0) { c.add(r.getIANTI03()); }
		if(r.getIANTI04() > 0) { c.add(r.getIANTI04()); }
		if(r.getIANTI05() > 0) { c.add(r.getIANTI05()); }
		if(r.getIANTI06() > 0) { c.add(r.getIANTI06()); }
		if(r.getIANTI07() > 0) { c.add(r.getIANTI07()); }
		if(r.getIANTI08() > 0) { c.add(r.getIANTI08()); }
		if(r.getIANTI09() > 0) { c.add(r.getIANTI09()); }
		if(r.getIANTI10() > 0) { c.add(r.getIANTI10()); }
		
		return c;
	}
	
	private boolean actualizarCreditos(ReciboM recibo) throws ApplicationException {
		//declaro las variables que voy a usar
		//Controladores
		cVentasM = new CtrlVentasM();
						
		//Entidades
		VentasM v = null; 
				
		//Variables 
		boolean rta = true; 
		double suma; 
				
		//Arrays
		ArrayList<String> comprobantes = new ArrayList<String>();
		ArrayList<Double> importes = new ArrayList<Double>();
				
		//Cargo los ncreds e icreds que esten en el recibo
		comprobantes = cargoNcreds(recibo); 
		importes = cargoIcreds(recibo);
				
		//recorro el array de comprobantes para hacer la busqueda y actualizacion
		for(int j = 0; j <= comprobantes.size(); j++) {
			v = cVentasM.consultaVentaPorTipoComp(comprobantes.get(j), "2"); 
			suma = v.getA_CUENTA() - importes.get(j);
			v.setA_CUENTA(suma);
			v.setPAGADO("N");
			if(cVentasM.modificaVentasMImporte(v) == false) {
				rta = false; 
				break;
			}
		}
				
		//Finalizo todas las variables y devuelvo la rta
		cVentasM = null;
		v = null;
				
		return rta;
	}
	
	private ArrayList<String> cargoNcreds(ReciboM r) throws ApplicationException {
		ArrayList<String> c = new ArrayList<String>();
		
		if(!r.getNCRED01().equals(null)) { c.add(r.getNCRED01()); }
		if(!r.getNCRED02().equals(null)) { c.add(r.getNCRED02()); }
		if(!r.getNCRED03().equals(null)) { c.add(r.getNCRED03()); }
		if(!r.getNCRED04().equals(null)) { c.add(r.getNCRED04()); }
		if(!r.getNCRED05().equals(null)) { c.add(r.getNCRED05()); }
		if(!r.getNCRED06().equals(null)) { c.add(r.getNCRED06()); }
		if(!r.getNCRED07().equals(null)) { c.add(r.getNCRED07()); }
		if(!r.getNCRED08().equals(null)) { c.add(r.getNCRED08()); }
		if(!r.getNCRED09().equals(null)) { c.add(r.getNCRED09()); }
		if(!r.getNCRED10().equals(null)) { c.add(r.getNCRED10()); }
		
		return c;
	}
	
	private ArrayList<Double> cargoIcreds(ReciboM r) throws ApplicationException {
		ArrayList<Double> c = new ArrayList<Double>();
		
		if(r.getICRED01() > 0) { c.add(r.getICRED01()); }
		if(r.getICRED02() > 0) { c.add(r.getICRED02()); }
		if(r.getICRED03() > 0) { c.add(r.getICRED03()); }
		if(r.getICRED04() > 0) { c.add(r.getICRED04()); }
		if(r.getICRED05() > 0) { c.add(r.getICRED05()); }
		if(r.getICRED06() > 0) { c.add(r.getICRED06()); }
		if(r.getICRED07() > 0) { c.add(r.getICRED07()); }
		if(r.getICRED08() > 0) { c.add(r.getICRED08()); }
		if(r.getICRED09() > 0) { c.add(r.getICRED09()); }
		if(r.getICRED10() > 0) { c.add(r.getICRED10()); }
		
		return c;
	}
}
