package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlCliente;
import controladores.CtrlCtactecliente;
import controladores.CtrlFactRec;
import controladores.CtrlReciboM;
import controladores.CtrlVentasM;
import entidades.Ctactecliente;
import entidades.FactRec;
import entidades.ReciboM;
import entidades.VentasM;
import util.ApplicationException;

/**
 * Servlet implementation class PagoSocio
 */
@WebServlet(urlPatterns = {"/PagoSocio"}, name="PagoSocio")
public class PagoSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Urls
	private String urlPagoSocio = "/amema/views/cobrosSocios.jsp";
	
	//Controladores
	CtrlVentasM cVentasM = null; 
	CtrlCliente cCliente = null; 
	CtrlReciboM cRecibos = null; 
	CtrlFactRec cFactura = null;
	CtrlCtactecliente cCtacte = null; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagoSocio() { super(); }

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
				buscarDatoSocio(request);
				response.sendRedirect(urlPagoSocio);
			}
			catch (IOException | ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_pago") != null) {
			try {
				grabarPago(request);
				response.sendRedirect(urlPagoSocio);
			} catch (ApplicationException | IOException e) { e.printStackTrace();  }
		}
	}

	// METODOS PRIMARIOS
	private void buscarDatoSocio(HttpServletRequest req) throws ApplicationException {
		// Recuperamos los datos que voy a necesitar.
		String codcli = req.getParameter("select");
		cVentasM = new  CtrlVentasM();
		cCliente = new CtrlCliente();

		
		//Seteo la respuesta
		req.getSession().setAttribute("deudas", cVentasM.listarVentasMPendientes(codcli));
		req.getSession().setAttribute("socio", cCliente.consultaCliente(codcli));
		
		//Anulo los controladores
		cVentasM = null; 
		cCliente = null; 
	}
	
	private void grabarPago(HttpServletRequest req) throws ApplicationException {
		//Recupero los datos del form
		String[] comprobantes = req.getParameterValues("opc");
		double importe = Double.parseDouble(req.getParameter("total").substring(1));
		
		//Calculo la fecha de hoy
		Date fecha = new Date();
		
		//Establezco el mensaje de exito / fracaso
		String msj = "";
		
		//Inicializo las variables que voy a usar
		cRecibos = new CtrlReciboM();
		String ultRecibo = calculoRecibo(cRecibos.ultimoID()); 
		
		
		if(generoRecibo(comprobantes, fecha, ultRecibo, importe) == true && generoFactura(comprobantes, fecha, ultRecibo) == true && 
		generaCtacte(comprobantes, fecha) == true && actualizoVentas(comprobantes) == true) { 
			msj ="siPago";
		}
		else { 
			msj = "noPago"; 
			}

		
		req.getSession().setAttribute("msj", msj);
	}
	
	//METODOS SECUNDARIOS
	private boolean generoRecibo(String[] comprobante, Date fecha, String nrecibo, double total) throws ApplicationException {
		//Inicializo las variables que voy a usar
		cRecibos = new CtrlReciboM();
		cVentasM = new CtrlVentasM(); 
		ReciboM r = new ReciboM();
		boolean rta; 
		
		int cont = comprobante.length;
		
		if(cont <= 20) {
			r = cargoDatos(comprobante, nrecibo);
		}
		if(cont > 20 && cont <= 40) {
			r = cargoDatos(comprobante, calculoRecibo(nrecibo)); 
		}
		
		r.setCIA("1");
		r.setPREFIJO("0006");
		r.setFRECIBO(fecha);
		r.setTRECIBO("1");
		r.setCODCLI(cVentasM.obtengoCliente(comprobante[0]));
		r.setNVIAJ("99");
		r.setLIQUIDA("N");
		r.setEFECTIVO(total);
		r.setCHEQUE(0);
		r.setA_CTA(0);
		r.setCERRADA("N");
		r.setCOMI_DIFE("N");
		r.setREFERENCIA("-");
		r.setDESCUENTOS(0);
		r.setRETENCION(0);
		r.setNROGENDEUDA(0);
		r.setDIFDSPAGO(0);
		
		rta = cRecibos.altaReciboM(r); 
		
		//Finalizo las variables que ocupan espacio
		cRecibos = null;
		cVentasM = null;
		r = null; 
		
		//retorno la rta
		return rta; 
	}
	
	private boolean generoFactura(String[] comprobante, Date fecha, String nrecibo) throws ApplicationException {
		//Inicializo las variables que voy a usar
		cVentasM = new CtrlVentasM();
		cFactura = new CtrlFactRec();
		VentasM v = new VentasM();
		FactRec f = null;
		boolean r = true; 
		
		//recorro comprobante para generar las facturas
		for(String c : comprobante) {
			v = cVentasM.consultaVentasM(c);
			f = new FactRec();
			f.setPREFIJO(v.getPREFIJO());
			f.setNCOMP(c);
			f.setTCOMP(v.getTCOMP());
			f.setLETRA(v.getLETRA());
			f.setCIA(v.getCIA());
			f.setNRECIBO(nrecibo);
			f.setFECREC(fecha);
			f.setMONTO_A(v.getSUBTOTAL());
			f.setMONTO_D(v.getSUBTOTAL());
			f.setDESCUENT_A(0);
			
			if(cFactura.altaFactura(f) == false) { 
				r = false; 
				break;
			}
		}
		
		//Finalizo las variables que ocupan memoria
		cVentasM = null;
		cFactura = null;
		v = null;
		f = null;
		
		// Devuelvo rta
		return r;
		
	}
	
	private boolean generaCtacte(String[] comprobante, Date fecha) throws ApplicationException {
		//Inicializo las variables
		boolean r = true; 
		cFactura = new CtrlFactRec(); 
		cCtacte = new CtrlCtactecliente();
		cVentasM = new CtrlVentasM();
		String codcli = cVentasM.obtengoCliente(comprobante[0]); 
		Ctactecliente c = null; 
		ArrayList<VentasM> ventas = new ArrayList<VentasM>();
		
		//Busco todas las ventas que se van a actualizar en Cta cte
		for(String aux: comprobante) {
			ventas.add(cVentasM.consultaVentasM(aux));
		}
		
		//recorro el array y cargo todas las cta cte. 
		for(VentasM v : ventas) {
			c = new Ctactecliente();
			c.setCODCLI(codcli);
			c.setFMOV(fecha);
			c.setTMOV("7");
			c.setTCOMP("7");
			c.setNCOMP(cFactura.devolverNroFactura(v.getNCOMP()));
			c.setFCOMPORIG(v.getFMOV());
			c.setLCOMPORIG(v.getLETRA());
			c.setPCOMPORIG(v.getPREFIJO());
			c.setTCOMPORIG(v.getTCOMP());
			c.setNCOMPORIG(v.getNCOMP());
			c.setDEBE(0);
			c.setHABER(v.getSUBTOTAL());
			
			if(cCtacte.altaCtaCte(c) == false) {
				r = false;
				break;
			}
		}
		
		//Finnalizo las variables
		cFactura = null; 
		cCtacte = null;
		cVentasM = null; 
		c = null; 
		
		//Decuelvo el valor
		return r; 
	}
	
	private boolean actualizoVentas(String[] comprobante) throws ApplicationException {
		//Inicializo las variables
		cVentasM = new CtrlVentasM();
		VentasM v = null;
		boolean r = true; 
		
		for(String c : comprobante) {
			v = new VentasM();
			v.setNCOMP(c);
			v.setPAGADO("S");
			
			if(cVentasM.modificoLetraPagado(c) == false) { 
				r = false;
				break;
			}
		}
		
		//Finalizo las variables 
		cVentasM = null; 
		v = null; 
		
		//Devuelvo el valor
		return r; 
	}
	
	private String calculoRecibo(String recibo) throws ApplicationException {
		int nro = Integer.parseInt(recibo);
		nro += 1; 
		return Integer.toString(nro);
	}
	
	private ReciboM cargoDatos(String[] comp, String nro) throws ApplicationException {
		// Declaro las variables que voy a usar y las inicializo
		ReciboM r = new ReciboM();
		cVentasM = new CtrlVentasM();
		r.setNRECIBO(nro);
		String tc = "1";
		
		switch (comp.length) {
		case 1:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			break;
		case 2:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			break;
		case 3:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			break;
		case 4:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			break;
		case 5:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			break;
		case 6:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			break;
		case 7:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			break;
		case 8:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			break;
		case 9:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setNFACT09(comp[8]);
			r.setTCOMP09(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			r.setIFACT09(cVentasM.obtengoImporte(comp[8]));
			break;
		case 10:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setNFACT09(comp[8]);
			r.setTCOMP09(tc);
			r.setNFACT10(comp[9]);
			r.setTCOMP10(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			r.setIFACT09(cVentasM.obtengoImporte(comp[8]));
			r.setIFACT10(cVentasM.obtengoImporte(comp[9]));
			break;
		case 11:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setNFACT09(comp[8]);
			r.setTCOMP09(tc);
			r.setNFACT10(comp[9]);
			r.setTCOMP10(tc);
			r.setNFACT11(comp[10]);
			r.setTCOMP11(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			r.setIFACT09(cVentasM.obtengoImporte(comp[8]));
			r.setIFACT10(cVentasM.obtengoImporte(comp[9]));
			r.setIFACT11(cVentasM.obtengoImporte(comp[10]));
			r.setIFACT12(cVentasM.obtengoImporte(comp[11]));
			break;
		case 12:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setNFACT09(comp[8]);
			r.setTCOMP09(tc);
			r.setNFACT10(comp[9]);
			r.setTCOMP10(tc);
			r.setNFACT11(comp[10]);
			r.setTCOMP11(tc);
			r.setNFACT12(comp[11]);
			r.setTCOMP12(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			r.setIFACT09(cVentasM.obtengoImporte(comp[8]));
			r.setIFACT10(cVentasM.obtengoImporte(comp[9]));
			r.setIFACT11(cVentasM.obtengoImporte(comp[10]));
			r.setIFACT12(cVentasM.obtengoImporte(comp[11]));
			break;
		case 13:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setNFACT09(comp[8]);
			r.setTCOMP09(tc);
			r.setNFACT10(comp[9]);
			r.setTCOMP10(tc);
			r.setNFACT11(comp[10]);
			r.setTCOMP11(tc);
			r.setNFACT12(comp[11]);
			r.setTCOMP12(tc);
			r.setNFACT13(comp[12]);
			r.setTCOMP13(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			r.setIFACT09(cVentasM.obtengoImporte(comp[8]));
			r.setIFACT10(cVentasM.obtengoImporte(comp[9]));
			r.setIFACT11(cVentasM.obtengoImporte(comp[10]));
			r.setIFACT12(cVentasM.obtengoImporte(comp[11]));
			r.setIFACT13(cVentasM.obtengoImporte(comp[12]));
			break;
		case 14:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setNFACT09(comp[8]);
			r.setTCOMP09(tc);
			r.setNFACT10(comp[9]);
			r.setTCOMP10(tc);
			r.setNFACT11(comp[10]);
			r.setTCOMP11(tc);
			r.setNFACT12(comp[11]);
			r.setTCOMP12(tc);
			r.setNFACT13(comp[12]);
			r.setTCOMP13(tc);
			r.setNFACT14(comp[13]);
			r.setTCOMP14(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			r.setIFACT09(cVentasM.obtengoImporte(comp[8]));
			r.setIFACT10(cVentasM.obtengoImporte(comp[9]));
			r.setIFACT11(cVentasM.obtengoImporte(comp[10]));
			r.setIFACT12(cVentasM.obtengoImporte(comp[11]));
			r.setIFACT13(cVentasM.obtengoImporte(comp[12]));
			r.setIFACT14(cVentasM.obtengoImporte(comp[13]));
			break;
		case 15:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setNFACT09(comp[8]);
			r.setTCOMP09(tc);
			r.setNFACT10(comp[9]);
			r.setTCOMP10(tc);
			r.setNFACT11(comp[10]);
			r.setTCOMP11(tc);
			r.setNFACT12(comp[11]);
			r.setTCOMP12(tc);
			r.setNFACT13(comp[12]);
			r.setTCOMP13(tc);
			r.setNFACT14(comp[13]);
			r.setTCOMP14(tc);
			r.setNFACT15(comp[14]);
			r.setTCOMP15(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			r.setIFACT09(cVentasM.obtengoImporte(comp[8]));
			r.setIFACT10(cVentasM.obtengoImporte(comp[9]));
			r.setIFACT11(cVentasM.obtengoImporte(comp[10]));
			r.setIFACT12(cVentasM.obtengoImporte(comp[11]));
			r.setIFACT13(cVentasM.obtengoImporte(comp[12]));
			r.setIFACT14(cVentasM.obtengoImporte(comp[13]));
			r.setIFACT15(cVentasM.obtengoImporte(comp[14]));
			break;
		case 16:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setNFACT09(comp[8]);
			r.setTCOMP09(tc);
			r.setNFACT10(comp[9]);
			r.setTCOMP10(tc);
			r.setNFACT11(comp[10]);
			r.setTCOMP11(tc);
			r.setNFACT12(comp[11]);
			r.setTCOMP12(tc);
			r.setNFACT13(comp[12]);
			r.setTCOMP13(tc);
			r.setNFACT14(comp[13]);
			r.setTCOMP14(tc);
			r.setNFACT15(comp[14]);
			r.setTCOMP15(tc);
			r.setNFACT16(comp[15]);
			r.setTCOMP16(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			r.setIFACT09(cVentasM.obtengoImporte(comp[8]));
			r.setIFACT10(cVentasM.obtengoImporte(comp[9]));
			r.setIFACT11(cVentasM.obtengoImporte(comp[10]));
			r.setIFACT12(cVentasM.obtengoImporte(comp[11]));
			r.setIFACT13(cVentasM.obtengoImporte(comp[12]));
			r.setIFACT14(cVentasM.obtengoImporte(comp[13]));
			r.setIFACT15(cVentasM.obtengoImporte(comp[14]));
			r.setIFACT16(cVentasM.obtengoImporte(comp[15]));
			break;
		case 17:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setNFACT09(comp[8]);
			r.setTCOMP09(tc);
			r.setNFACT10(comp[9]);
			r.setTCOMP10(tc);
			r.setNFACT11(comp[10]);
			r.setTCOMP11(tc);
			r.setNFACT12(comp[11]);
			r.setTCOMP12(tc);
			r.setNFACT13(comp[12]);
			r.setTCOMP13(tc);
			r.setNFACT14(comp[13]);
			r.setTCOMP14(tc);
			r.setNFACT15(comp[14]);
			r.setTCOMP15(tc);
			r.setNFACT16(comp[15]);
			r.setTCOMP16(tc);
			r.setNFACT17(comp[16]);
			r.setTCOMP17(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			r.setIFACT09(cVentasM.obtengoImporte(comp[8]));
			r.setIFACT10(cVentasM.obtengoImporte(comp[9]));
			r.setIFACT11(cVentasM.obtengoImporte(comp[10]));
			r.setIFACT12(cVentasM.obtengoImporte(comp[11]));
			r.setIFACT13(cVentasM.obtengoImporte(comp[12]));
			r.setIFACT14(cVentasM.obtengoImporte(comp[13]));
			r.setIFACT15(cVentasM.obtengoImporte(comp[14]));
			r.setIFACT16(cVentasM.obtengoImporte(comp[15]));
			r.setIFACT17(cVentasM.obtengoImporte(comp[16]));
			break;
		case 18:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setNFACT09(comp[8]);
			r.setTCOMP09(tc);
			r.setNFACT10(comp[9]);
			r.setTCOMP10(tc);
			r.setNFACT11(comp[10]);
			r.setTCOMP11(tc);
			r.setNFACT12(comp[11]);
			r.setTCOMP12(tc);
			r.setNFACT13(comp[12]);
			r.setTCOMP13(tc);
			r.setNFACT14(comp[13]);
			r.setTCOMP14(tc);
			r.setNFACT15(comp[14]);
			r.setTCOMP15(tc);
			r.setNFACT16(comp[15]);
			r.setTCOMP16(tc);
			r.setNFACT17(comp[16]);
			r.setTCOMP17(tc);
			r.setNFACT18(comp[17]);
			r.setTCOMP18(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			r.setIFACT09(cVentasM.obtengoImporte(comp[8]));
			r.setIFACT10(cVentasM.obtengoImporte(comp[9]));
			r.setIFACT11(cVentasM.obtengoImporte(comp[10]));
			r.setIFACT12(cVentasM.obtengoImporte(comp[11]));
			r.setIFACT13(cVentasM.obtengoImporte(comp[12]));
			r.setIFACT14(cVentasM.obtengoImporte(comp[13]));
			r.setIFACT15(cVentasM.obtengoImporte(comp[14]));
			r.setIFACT16(cVentasM.obtengoImporte(comp[15]));
			r.setIFACT17(cVentasM.obtengoImporte(comp[16]));
			r.setIFACT18(cVentasM.obtengoImporte(comp[17]));
			break;
		case 19:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setNFACT09(comp[8]);
			r.setTCOMP09(tc);
			r.setNFACT10(comp[9]);
			r.setTCOMP10(tc);
			r.setNFACT11(comp[10]);
			r.setTCOMP11(tc);
			r.setNFACT12(comp[11]);
			r.setTCOMP12(tc);
			r.setNFACT13(comp[12]);
			r.setTCOMP13(tc);
			r.setNFACT14(comp[13]);
			r.setTCOMP14(tc);
			r.setNFACT15(comp[14]);
			r.setTCOMP15(tc);
			r.setNFACT16(comp[15]);
			r.setTCOMP16(tc);
			r.setNFACT17(comp[16]);
			r.setTCOMP17(tc);
			r.setNFACT18(comp[17]);
			r.setTCOMP18(tc);
			r.setNFACT19(comp[18]);
			r.setTCOMP19(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			r.setIFACT09(cVentasM.obtengoImporte(comp[8]));
			r.setIFACT10(cVentasM.obtengoImporte(comp[9]));
			r.setIFACT11(cVentasM.obtengoImporte(comp[10]));
			r.setIFACT12(cVentasM.obtengoImporte(comp[11]));
			r.setIFACT13(cVentasM.obtengoImporte(comp[12]));
			r.setIFACT14(cVentasM.obtengoImporte(comp[13]));
			r.setIFACT15(cVentasM.obtengoImporte(comp[14]));
			r.setIFACT16(cVentasM.obtengoImporte(comp[15]));
			r.setIFACT17(cVentasM.obtengoImporte(comp[16]));
			r.setIFACT18(cVentasM.obtengoImporte(comp[17]));
			r.setIFACT19(cVentasM.obtengoImporte(comp[18]));
			break;
		default:
			r.setNFACT01(comp[0]);
			r.setTCOMP01(tc);
			r.setNFACT02(comp[1]);
			r.setTCOMP02(tc);
			r.setNFACT03(comp[2]);
			r.setTCOMP03(tc);
			r.setNFACT04(comp[3]);
			r.setTCOMP04(tc);
			r.setNFACT05(comp[4]);
			r.setTCOMP05(tc);
			r.setNFACT06(comp[5]);
			r.setTCOMP06(tc);
			r.setNFACT07(comp[6]);
			r.setTCOMP07(tc);
			r.setNFACT08(comp[7]);
			r.setTCOMP08(tc);
			r.setNFACT09(comp[8]);
			r.setTCOMP09(tc);
			r.setNFACT10(comp[9]);
			r.setTCOMP10(tc);
			r.setNFACT11(comp[10]);
			r.setTCOMP11(tc);
			r.setNFACT12(comp[11]);
			r.setTCOMP12(tc);
			r.setNFACT13(comp[12]);
			r.setTCOMP13(tc);
			r.setNFACT14(comp[13]);
			r.setTCOMP14(tc);
			r.setNFACT15(comp[14]);
			r.setTCOMP15(tc);
			r.setNFACT16(comp[15]);
			r.setTCOMP16(tc);
			r.setNFACT17(comp[16]);
			r.setTCOMP17(tc);
			r.setNFACT18(comp[17]);
			r.setTCOMP18(tc);
			r.setNFACT19(comp[18]);
			r.setTCOMP19(tc);
			r.setNFACT20(comp[19]);
			r.setTCOMP20(tc);
			r.setIFACT01(cVentasM.obtengoImporte(comp[0]));
			r.setIFACT02(cVentasM.obtengoImporte(comp[1]));
			r.setIFACT03(cVentasM.obtengoImporte(comp[2]));
			r.setIFACT04(cVentasM.obtengoImporte(comp[3]));
			r.setIFACT05(cVentasM.obtengoImporte(comp[4]));
			r.setIFACT06(cVentasM.obtengoImporte(comp[5]));
			r.setIFACT07(cVentasM.obtengoImporte(comp[6]));
			r.setIFACT08(cVentasM.obtengoImporte(comp[7]));
			r.setIFACT09(cVentasM.obtengoImporte(comp[8]));
			r.setIFACT10(cVentasM.obtengoImporte(comp[9]));
			r.setIFACT11(cVentasM.obtengoImporte(comp[10]));
			r.setIFACT12(cVentasM.obtengoImporte(comp[11]));
			r.setIFACT13(cVentasM.obtengoImporte(comp[12]));
			r.setIFACT14(cVentasM.obtengoImporte(comp[13]));
			r.setIFACT15(cVentasM.obtengoImporte(comp[14]));
			r.setIFACT16(cVentasM.obtengoImporte(comp[15]));
			r.setIFACT17(cVentasM.obtengoImporte(comp[16]));
			r.setIFACT18(cVentasM.obtengoImporte(comp[17]));
			r.setIFACT19(cVentasM.obtengoImporte(comp[18]));
			r.setIFACT20(cVentasM.obtengoImporte(comp[19]));
			break;
		}
		
		return r; 
	}
}
 