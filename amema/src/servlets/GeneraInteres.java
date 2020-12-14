package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlAuxAnDeudaCli;
import controladores.CtrlAuxAnDeudaCliGI;
import controladores.CtrlConvenio;
import controladores.CtrlCtactecliente;
import controladores.CtrlSocioConvenio;
import controladores.CtrlVentasD;
import controladores.CtrlVentasM;
import entidades.AuxAnDeudaCli;
import entidades.AuxAnDeudaCliGI;
import entidades.Convenio;
import entidades.Ctactecliente;
import entidades.SocioConvenio;
import entidades.VentasD;
import entidades.VentasM;
import util.ApplicationException;

/**
 * Servlet implementation class GeneraInteres
 */
@WebServlet(urlPatterns = {"/GeneraInteres"})
public class GeneraInteres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String urlGeneraInteres = "/amema/views/generaInteres.jsp";
	
	// Controladores
	CtrlConvenio cConvenio = null; 
	CtrlAuxAnDeudaCliGI cGenInteres = null; 
	CtrlSocioConvenio cConvenioSocio = null; 
	CtrlAuxAnDeudaCli cDeudaSocio = null; 
	CtrlVentasM cVentasM = null; 
	CtrlVentasD cVentasD = null; 
	CtrlCtactecliente cCtaCte = null; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneraInteres() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("evento_generaInteres") != null) {
			try {
				generaInteres(request);
				response.sendRedirect(urlGeneraInteres);
			}
			catch(ApplicationException | IOException | ParseException e) { e.printStackTrace(); }
		}
	}
	
	
	//METODOS PRIMARIOS
	
	private void generaInteres(HttpServletRequest req) throws ApplicationException, ParseException {
		//Defino las variables que voy a usar y recupero las demas del form.
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String periodo = req.getParameter("periodo");
		String convenio = req.getParameter("convenio");
		Date fecMov = df.parse(req.getParameter("fechaMovimiento"));
		String prioridad = req.getParameter("prioridad");
		String msj =""; 
		String mensaje = "";
		Convenio conv  = null; 
		AuxAnDeudaCliGI interes = null; 
		ArrayList<AuxAnDeudaCli> deudores = new ArrayList<>();
		ArrayList<AuxAnDeudaCliGI> deudoresInteres = new ArrayList<>();
		cConvenio = new CtrlConvenio();
		cGenInteres = new CtrlAuxAnDeudaCliGI();
		cDeudaSocio = new CtrlAuxAnDeudaCli();
		
		//Recupero el interes para el concepto ingresado
		conv = cConvenio.consultaConvenio(convenio);
		
		//Recupero segun el periodo y el convenio todos los importes pagados en 0 para generar la tabla AUXANDEUDACLIGI
		deudores = cDeudaSocio.listarDeudoresPeriodoyConvenio(convenio, periodo);

		//Valido que no haya deuda para el periodo, sino lo elimino y vuelvo a cargar
		if(cGenInteres.listarInteresPorPeriodoyConvenio(convenio, periodo) != null) { cGenInteres.bajaInteresMasivoPeriodo(convenio, periodo); }
		for(AuxAnDeudaCli a: deudores) {
			interes = new AuxAnDeudaCliGI(a.getCODCLI(), a.getNOMCLI(), a.getTIPOMOV(), a.getNCOMP(), a.getTCOMP(), a.getNVIAJ(), a.getREF(), a.getCONVENIO(), a.getPERIODO(), a.getSALDO(), a.getFCOMP(), a.getIMPORTE(), a.getIMPORTEPAGADO(), a.getANALISIS());
			cGenInteres.altaInteres(interes);
		}
		
		//Genero un listado con todos los deudores de la tabla AUXANDEUDACLIGI con los importes totales, es decir sumo las deudas para hacer el calculo de los intereses.
		deudoresInteres = cGenInteres.listarDeudaTotal(convenio, periodo);
		
		//Genero el mensaje que le voy a ingresar 
		mensaje = "Int. por deuda al "+periodo+"con una Tasa de Interés de ";
		//Hago las cargas pertinentes de los intereses
		if(cargoVentasM(deudoresInteres, conv, fecMov, prioridad, mensaje) == true && cargoCtaCteCli(fecMov) == true && cargoVentasD(fecMov) == true) {
			msj = "siAlta";
		}
		else {
			msj = "noAlta";
		}
		
		//asigno el mensaje a la variable request para mostrarlo en el jsp
		req.getSession().setAttribute("msj", msj);
		
		//Limpio todas las variables que ocupna memoria
		conv  = null; 
		interes = null; 
		cConvenio = null;
		cGenInteres = null;
		cDeudaSocio = null;
	}
	
	
	// METODOS SECUNDARIOS
	private boolean cargoVentasM(ArrayList<AuxAnDeudaCliGI> deudoresInteres, Convenio convenio, Date fecMov, String analisis, String mensaje) throws ApplicationException {
		//declaro las variables que voy a usar
		DecimalFormat df = new DecimalFormat("##.00");
		cVentasM = new CtrlVentasM();
		cConvenioSocio = new CtrlSocioConvenio(); 
		VentasM v = null; 
		boolean rta = true; 
		double interes, calculoInteres, dolar;
		dolar = 3.18; 
		
		//Recorremos el array para hacer el insert
		for(AuxAnDeudaCliGI a: deudoresInteres) {
			//Calculo el ultimo nro de comprobante para ingresar
			String ncomp = calculoUltComprobante(cVentasM.ultimoID());
			//Recupero el registro del cliente para saber la tasa de descuento
			SocioConvenio cs = cConvenioSocio.consultaSocioConvenio(a.getCodcli(), convenio.getCCOND());
			//Si el cliente tiene tasa de descuento se lo aplico, sino uso el general del convenio
			if(cs != null && cs.getGenInt() == "S") { interes = cs.getTasaInt(); }
			else { interes = convenio.getTasaInt(); }
			//Calculo el valor del interes para asignarlo a VentasM
			calculoInteres = (a.getImporte() * interes) / 100; 
			//Completo el mensaje con los datos que calcule
			mensaje = mensaje+interes+" sobre el importe $"+df.format(a.getImporte());
			
			v = new VentasM();
			v.setPREFIJO("0001");
			v.setNCOMP(ncomp);
			v.setTCOMP("1");
			v.setLETRA("B");
			v.setCIA("1");
			v.setFMOV(fecMov);
			v.setNFACC("00000000");
			v.setCODCLI(a.getCodcli());
			v.setCVTO("1");
			v.setTASA(0);
			v.setPAGADO("N");
			v.setANULADO("N");
			v.setREFERENCIA("J");
			v.setDIRECTA("S");
			v.setCOMI_DIFE("S");
			v.setFECVTO(fecMov);
			v.setPORDESCTO(0);
			v.setCCOND_1(convenio.getCCOND());
			v.setNVIAJ("99");
			v.setLIQUIDA("N");
			v.setNROPEDIDO("0");
			v.setNROREMITO("0");
			v.setNROPRESUP("0");
			v.setFLETE(0);
			v.setSUBTOTAL(calculoInteres);
			v.setA_CUENTA(calculoInteres);v.setIMPIVA_1(calculoInteres);
			v.setTDOLAR(calculoInteres/dolar);
			v.setDOLAR(dolar);
			v.setTEXTO(0);
			v.setTEXTLIB(mensaje);
			v.setVA_DTO("A");
			v.setCPERS1("00");
			v.setCPERS2("00");
			v.setCPERS3("00");
			v.setANALISIS(analisis);
			v.setNROMOVPLANIF(0);
			v.setOBSERV("AJUSTE DEUDA ANTERIOR (Cuota 1 de 1)");
			v.setNROACTUALIZ(0);
			
			//inserto el registro en ventasM, si es falso setea la variable rta en Falso y sale del bucle
			if(cVentasM.altaVentasM(v) == false) {
				rta = false;
				break;
			}
		}
		
		//Limpio las variables que ocupan memoria
		cVentasM = null;
		cConvenioSocio = null; 
		v = null; 
		
		//Devuelvo rta
		return rta;
	}
	
	private boolean cargoCtaCteCli(Date fecha) throws ApplicationException {
		//Declaro las variables que voy a usar
		cVentasM = new CtrlVentasM();
		cCtaCte = new CtrlCtactecliente();
		Ctactecliente c = null; 
		ArrayList<VentasM> deudas = new ArrayList<>();
		boolean rta = true;
		
		//Listo las ventasm que son intereses
		deudas = cVentasM.listarVentasMporFecha(fecha);
		
		//recorro esas ventas y genero las ctactecli
		for(VentasM v: deudas) {
			c = new Ctactecliente();
			c.setCODCLI(v.getCODCLI());
			c.setFMOV(v.getFMOV());
			c.setTMOV("1");
			c.setLCOMP(v.getLETRA());
			c.setPCOMP(v.getPREFIJO());
			c.setTCOMP(v.getTCOMP());
			c.setNCOMP(v.getNCOMP());
			c.setFCOMPORIG(v.getFMOV());
			c.setLCOMPORIG(v.getLETRA());
			c.setPCOMPORIG(v.getPREFIJO());
			c.setTCOMPORIG(v.getTCOMP());
			c.setNCOMPORIG(v.getNCOMP());
			c.setDEBE(v.getSUBTOTAL());
			c.setHABER(0);
			
			//inserto el registro en Cta Cte Clientes, si es falso setea la variable rta en Falso y sale del bucle
			if(cCtaCte.altaCtaCte(c) == false) {
				rta = false; 
				break;
			}
		}
		
		//Finalizo las variables
		cVentasM = null;
		cCtaCte = null;
		c = null; 
		
		//Devuelvo la respuesta
		return rta;
	}
	
	private boolean cargoVentasD(Date fecha) throws ApplicationException {
		//Declaro las variables que voy a usar
		boolean rta = true; 
		cVentasD = new CtrlVentasD();
		cVentasM = new CtrlVentasM();
		VentasD v = null; 
		ArrayList<VentasM> deudores = new ArrayList<>();
		
		//Recupero las ventasM que se cargaron para la fecha asi cargo las ventasD
		deudores = cVentasM.listarVentasMporFecha(fecha);
		
		//Recorro a los deudores para cargar las VentasD
		for(VentasM d : deudores) {
			v = new VentasD();
			v.setPREFIJO(d.getPREFIJO());
			v.setNCOMP(d.getNCOMP());
			v.setTCOMP(d.getTCOMP());
			v.setLETRA(d.getLETRA());
			v.setCIA(v.getCIA());
			v.setCODART("999999");
			v.setUNIDADES(1);
			v.setPBONIF(0);
			v.setPBONIF2(0);
			v.setPVENTA(d.getSUBTOTAL());

			//inserto el registro en VentasD, si es falso setea la variable rta en Falso y sale del bucle
			if(cVentasD.altaVentasD(v) == false) {
				rta = false; 
				break;
			}
		}
		
		//Finalizo las variables que use y consumen memoria
		cVentasD = null;
		cVentasM = null;
		v = null; 
		
		//Devuelvo la respuesta
		return rta; 
	}
	
	
	
	private String calculoUltComprobante(String comp) throws ApplicationException {
		int nro = Integer.parseInt(comp); 
		nro += 1;
		comp = Integer.toString(nro);
		switch (comp.length()) {
		case 1:
			comp = "0000000"+comp;
			break;
		case 2:
			comp = "000000"+comp;
			break;
		case 3:
			comp = "00000"+comp;
			break;
		case 4:
			comp = "0000"+comp;
			break;
		case 5:
			comp = "000"+comp;
			break;
		case 6:
			comp = "00"+comp;
			break;
		case 7:
			comp = "0"+comp;
			break;
		default:
			break;
		}
		
		return comp;
	}

}
