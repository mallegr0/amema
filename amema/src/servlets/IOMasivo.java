package servlets;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlAuxAnDeudaCli;
import controladores.CtrlAuxListaMasivo;
import controladores.CtrlCliente;
import controladores.CtrlCtactecliente;
import controladores.CtrlFactRec;
import controladores.CtrlPeriodoDeudaArch;
import controladores.CtrlPeriodoDeudaGen;
import controladores.CtrlReciboM;
import controladores.CtrlVentasM;
import entidades.AuxAnDeudaCli;
import entidades.AuxListaMasivo;
import entidades.Ctactecliente;
import entidades.Entrada;
import entidades.FactRec;
import entidades.PeriodoDeudaArch;
import entidades.PeriodoDeudaGen;
import entidades.ReciboM;
import entidades.VentasM;
import util.ApplicationException;

/**
 * Servlet implementation class IOMasivo
 */
@WebServlet(urlPatterns = {"/IOMasivo"})
public class IOMasivo extends HttpServlet {
	//Variables
	private static final long serialVersionUID = 1L;
	private CtrlAuxAnDeudaCli cAuxCliente = null; 
	private CtrlCliente cCliente = null; 
	private CtrlPeriodoDeudaArch cPeriodoArch = null; 
	private CtrlPeriodoDeudaGen cPeriodoGen = null;
	private CtrlAuxListaMasivo cListarAux = null; 
	private CtrlReciboM cReciboM = null; 
	private CtrlFactRec cFactura = null; 
	private CtrlVentasM cVentasM = null; 
	private CtrlCtactecliente cCtacte = null; 
	private DecimalFormat df = null; //formato para el importe
	private String urlAMasivo = "/amema/views/actualizaPagosMasivo.jsp";
	private String urlLMasivo = "/amema/views/listaPagosMasivos.jsp";
	private String urlGRecibos = "/amema/views/generaRecibos.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IOMasivo() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("evento_actualizaMasivo") != null) {
			try {
				leoMasivo(request, response);
				response.sendRedirect(urlAMasivo);
			}
			catch(ApplicationException | ParseException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_buscar") != null) {
			try {
				listarMasivo(request);
				response.sendRedirect(urlLMasivo);
			}
			catch(ApplicationException | ParseException e) { e.printStackTrace(); }
		}
		if(request.getParameter("evento_generaRecibos") != null) {
			try {
				generaRecibos(request); 
				response.sendRedirect(urlGRecibos);
			}
			catch (ApplicationException |ParseException e) { e.printStackTrace(); }
		}
	}
	
	
	// Las ayudas estan al final del servlet
	
	// METODOS PRIMARIOS
	
	private void leoMasivo(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, ParseException {
		/*
		 * Tengo que actualizar los datos de las siguientes tablas
		 * RECIBOSM
		 * FAC_REC
		 * CTACTECLI
		 * VENTASM
		 * TENGO QUE GRABAR LOS MOVIMIENTOS DE LOS TXT QUE SUBO
		 * TENGO QUE GRABAR EN AUXDEUDCLI LOS DATOS QUE SUBO DEL TXT
		 */
		
		// Declaro las variables recuperadas del form.
		String ruta = req.getParameter("ruta"); //Ruta del archivo
		String periodo = req.getParameter("periodo"); //Periodo que se carga
		String convenio = req.getParameter("convenio"); //Convenio de los socios
		String modo = req.getParameter("modo"); //Que tipo de archivo ingreso en forma masiva (1)
		String nombre = ""; // Nombre de la ruta para mostrar y asignar a la tabla peridosGeneradosArch
		df = new DecimalFormat("#0.00"); //formato para el importe

		
		if(ruta.substring(17).length() == 10) { nombre = ruta.substring(17,23); }
		else { nombre = ruta.substring(17,44); }
		
		// Declaro las variables que voy a usar
		String mensaje = "";
		double importe = 0.0;
		ArrayList<Entrada> ingresos = new ArrayList<>();
		
		if(modo.equals("municipalidad")) { ingresos = leoArchivoMuni(ruta); }
		else { ingresos = leoArchivoJub(ruta); }
		
		ArrayList<AuxAnDeudaCli> listadoAuxCli = new ArrayList<AuxAnDeudaCli>(); 
		cAuxCliente = new CtrlAuxAnDeudaCli();


		//Recupero todos los datos de AuxAnDeudaCli para hacer los UPDATEs
		if(ingresos.size() > 0) {
			if(ingresos.get(0).getConcepto().equals("2110")) {
				listadoAuxCli = cAuxCliente.listarAuxAnDeudaCliCuotas(periodo, convenio);
			}
			if(ingresos.get(0).getConcepto().equals("2207")) {
				listadoAuxCli = cAuxCliente.listarAuxAnDeudaCliVarios(periodo, convenio);
			}
			if(ingresos.get(0).getConcepto().equals("jub")) {
				listadoAuxCli = cAuxCliente.listarAuxAnDeudaCliJubilados(periodo, convenio);
			}
		}
		
		//Calculo los importes del txt y lo asigno a mensaje
		for(Entrada e: ingresos) { importe += Double.parseDouble(e.getImporte()); }
		
		if(importe < 0) { importe = importe *-1; }
		if(importe == 0) { importe = 0; }
		
		mensaje = "El archivo "+nombre+" del periodo "+periodo+" informa un importe de: $"+df.format(importe);
		
		// Guardo los valores en las tablas generadas.
		
		PeriodoDeudaArch p = new PeriodoDeudaArch(0, convenio, periodo, nombre+".txt");
		cPeriodoArch = new CtrlPeriodoDeudaArch();
		cPeriodoArch.altaPeriodoDeudaArch(p);
		
		cPeriodoGen = new CtrlPeriodoDeudaGen();
		PeriodoDeudaGen g = cPeriodoGen.consultaPeriodoDeudaGen(periodo, convenio);
		g.setRecibos_gen("S");
		cPeriodoGen.modificaPeriodoDeudaGen(g);
		
		// Pongo en null los controladores para liberar memoria
		cAuxCliente = null; 
		cPeriodoArch = null; 
		cPeriodoGen = null; 
		
		
		//Ahora actualizo los importes de AuxAnDeudaCli para luego generar los recibos. y lo seteo en el request
		req.getSession().setAttribute("errores", actualizoAuxiliar(ingresos, listadoAuxCli));
		req.getSession().setAttribute("mensaje", mensaje);

	}
	
	private void listarMasivo(HttpServletRequest req) throws ApplicationException, ParseException {
		//Declaro las variables que voy a usar
		String periodo, convenio; 
		ArrayList<AuxListaMasivo> lista = new ArrayList<>(); 
		cListarAux = new CtrlAuxListaMasivo();
		
		//Recupero los datos del form para hacer las busquedas
		periodo = req.getParameter("periodo");
		convenio = req.getParameter("convenio");

		
		//Realizo la busqueda segun los requerimientos
		if(convenio.equals("999")) {lista = cListarAux.listarPorPeriodo(periodo); }
		else { lista = cListarAux.listarPorPeriodoyConvenio(periodo, convenio); }
		
		//Finalizo el controlador que use
		cListarAux = null; 
		
		req.getSession().setAttribute("listado", lista);
	}
	
	private void generaRecibos(HttpServletRequest req) throws ApplicationException, ParseException {
		//Declaro las variables que voy a usar
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cAuxCliente = new CtrlAuxAnDeudaCli();
		cPeriodoGen = new CtrlPeriodoDeudaGen();
		cReciboM = new CtrlReciboM();
		String nrecibo = cReciboM.ultimoID();
		
		//Recupero los datos del form
		String periodo = req.getParameter("periodo");
		String convenio = req.getParameter("convenio");
		Date fecha = df.parse(req.getParameter("fecha"));
		
		//Recupero de aux todos los datos que se ingresaron
		ArrayList<AuxAnDeudaCli> listado = cAuxCliente.listarAuxAnDeudaCliPeriodoyConvenio(periodo, convenio);
		
		ArrayList<ReciboM> eRecibos = generoRecibosM(listado, fecha, nrecibo);
		ArrayList<FactRec> eFacturas = generoFact_rec(listado, fecha, nrecibo);
		ArrayList<VentasM> eVentasM = actualizoVentasM(listado, fecha);
		ArrayList<Ctactecliente> eCtacte = actualizoCtaCte(listado, fecha, nrecibo);
		
		// Modifico la letra de genera recibos en la tabla
		cambiaLetraPeriodo(periodo, convenio);
		
		// Pongo en null los controladores para liberar espacio
		cAuxCliente = null; 
		cPeriodoGen = null;
		
		//Valido para devolver el mensaje
		
		//Inicializo las variables de sesion para devolver los errores
		req.getSession().setAttribute("erecibos", eRecibos);
		req.getSession().setAttribute("efacturas", eFacturas);
		req.getSession().setAttribute("eventasm", eVentasM);
		req.getSession().setAttribute("ectacte", eCtacte);
		req.getSession().setAttribute("mensaje", "Se han generado correctamente los recibos");
	}
	
	
	// METODOS SECUNDARIOS
	
	private ArrayList<Entrada> leoArchivoMuni(String ruta) throws ApplicationException {
		ArrayList<Entrada> lista = new ArrayList<>();
		Entrada entrada = null; 
		File f = new File(ruta);
		Scanner s;
		try {
			s = new Scanner(f);
			while(s.hasNext() == true) {
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s* ");
				entrada = new Entrada(sl.next(), sl.next(), sl.next(), sl.next(), sl.next()+" "+sl.next(), sl.next());
				lista.add(entrada);
				sl.close();
			}
			s.close();
		}
		catch (Exception e) { e.printStackTrace(); }
		lista.remove(0);
		return lista;
	}
	
	private ArrayList<Entrada> leoArchivoJub(String ruta) throws ApplicationException {
		//Declaro las variables que voy a usar
		ArrayList<Entrada> lista = new ArrayList<>();
		Entrada entrada = null; 
		File f = new File(ruta);
		Scanner s;
		String empresa, legajo, fecha, concepto, descConcepto, importe; 
		
		try {
			s = new Scanner(f);
			while (s.hasNext() == true) {
				String linea = s.nextLine();
				empresa = linea.substring(0,2);
				if(empresa.equals("J0")) { legajo = linea.substring(2,7)+"/"+linea.substring(7,9); }
				else { legajo = linea.substring(3,7)+"/"+linea.substring(7,9); }
				importe = linea.substring(39);
				fecha = "";
				concepto = "jub";
				descConcepto = ""; 
				
				
				entrada = new Entrada(empresa, legajo, fecha, concepto, descConcepto, importe);
				
				lista.add(entrada);
			}
			s.close();
		}catch (Exception e) { e.printStackTrace(); }
		
		return lista;
	}
	
	private ArrayList<AuxAnDeudaCli> actualizoAuxiliar(ArrayList<Entrada> entradas, ArrayList<AuxAnDeudaCli> datos) throws ApplicationException, ParseException {
		df = new DecimalFormat("#0.00"); //formato para el importe
		ArrayList<AuxAnDeudaCli> errores = new ArrayList<>();
		ArrayList<AuxAnDeudaCli> depurados = filtraPersonas(entradas, datos); // Filtro las personas por Empresa, para hacerlo mas eficiente al momento de la carga
		String codcli;
		double importe, saldo;
		cAuxCliente = new CtrlAuxAnDeudaCli();
		cCliente = new CtrlCliente();

		if(depurados.size() > 0) {
			for(Entrada e: entradas) {
				codcli = cCliente.buscoCodigo(e.getLegajo());
				saldo = Double.parseDouble(e.getImporte());
				if(saldo <= 0) { saldo = saldo *(-1); } //Como el archivo de jubilado tieme importes positivos, tengo que validar para que no me sume los importes que tengo que restar
				for(AuxAnDeudaCli d: depurados) {
					if(codcli.equals(d.getCODCLI())) {
						
						importe = saldo - d.getIMPORTE();
						
						if(importe > 0) { d.setIMPORTEPAGADO(d.getIMPORTE()); }
						if(importe <= 0) { d.setIMPORTEPAGADO(saldo); }
						
						if(cAuxCliente.modificaAuxAnDeudaCli(d) == false) { errores.add(d); }
						if(importe > 0) { saldo = importe; }
						else { saldo = 0.0; }
					}
				}
			}
		}
		
		//Finalizo las variables que me ocupan memoria
		cAuxCliente = null;
		cCliente = null;
		
		
		//Devuelvo valores
		return errores;
	}
	
	private ArrayList<AuxAnDeudaCli> filtraPersonas(ArrayList<Entrada> entradas, ArrayList<AuxAnDeudaCli> datos) throws ApplicationException {
		
		//Defino las variables que voy a usar
		ArrayList<AuxAnDeudaCli> lista = new ArrayList<AuxAnDeudaCli>();
		String codcli = "";
		cCliente = new CtrlCliente();
		
		//Busco a las personas del txt que se ingreso
		for(Entrada e: entradas) {
			codcli = cCliente.buscoCodigo(e.getLegajo());
			for(AuxAnDeudaCli d: datos) {
				if(codcli.equals(d.getCODCLI())) { lista.add(d); }
			}

		}
		
		//Cierro el controlador de cliente para que no me ocupe memoria
		cCliente = null; 
		
		return lista; //devuelvo las coincidencias
		
	}

	private ArrayList<ReciboM> generoRecibosM (ArrayList<AuxAnDeudaCli> lista, Date fecha, String nrecibo) throws ApplicationException {
		//declaro las variables que voy a usar
		cReciboM = new CtrlReciboM();
		cPeriodoGen = new CtrlPeriodoDeudaGen();
		ReciboM rm = null;
		ArrayList<ReciboM> error = new ArrayList<>();
		
		for(AuxAnDeudaCli a: lista) {
			nrecibo = calculoRecibo(nrecibo);
			rm = new ReciboM();
			rm.setNRECIBO(nrecibo);
			rm.setCIA("1");
			rm.setPREFIJO("0006");
			rm.setFRECIBO(fecha);
			rm.setTRECIBO("1");
			rm.setCODCLI(a.getCODCLI());
			rm.setNVIAJ("00");
			rm.setLIQUIDA("N");
			rm.setEFECTIVO(a.getIMPORTEPAGADO());
			rm.setCHEQUE(0);
			rm.setA_CTA(0);
			rm.setCERRADA("N");
			rm.setCOMI_DIFE("N");
			rm.setREFERENCIA(a.getREF());
			rm.setTCOMP01("1");
			rm.setNFACT01(a.getNCOMP().substring(7));
			rm.setIFACT01(a.getIMPORTEPAGADO());
			rm.setDESCUENTOS(0);
			rm.setRETENCION(0);
			rm.setUSADO(0);
			rm.setNROGENDEUDA(cPeriodoGen.consultaNroDeudaPorPeriodoyConvenio(a.getPERIODO(), a.getCONVENIO(), fecha));
			rm.setDIFDSPAGO(0);
			
			if(cReciboM.altaReciboM(rm) != true) { error.add(rm); }
		}
		
		// Pongo en null las varaibles para liberar espacio
		cReciboM = null;
		cPeriodoGen = null; 
		rm = null; 
		
		return error;
	}
	
	private ArrayList<FactRec> generoFact_rec(ArrayList<AuxAnDeudaCli> lista, Date fecha, String nroRecibo) throws ApplicationException {
		//declaro las variables que voy a usar
		cFactura = new CtrlFactRec();
		cPeriodoGen = new CtrlPeriodoDeudaGen();
		FactRec f = null;
		ArrayList<FactRec> error = new ArrayList<>();
			
		for(AuxAnDeudaCli a: lista) {
			nroRecibo = calculoRecibo(nroRecibo);
			f = new FactRec();
			f.setPREFIJO(a.getNCOMP().substring(2,6));
			f.setNCOMP(a.getNCOMP().substring(7));
			f.setTCOMP("1");
			f.setLETRA(a.getNCOMP().substring(0,1));
			f.setCIA("1");
			f.setNRECIBO(nroRecibo);
			f.setFECREC(fecha);
			f.setMONTO_A(a.getIMPORTEPAGADO());
			f.setMONTO_D(a.getIMPORTEPAGADO());
			f.setDESCUENT_A(0);
					
			if(cFactura.altaFactura(f) != true) { error.add(f); }
		}
				
		// Pongo en null las varaibles para liberar espacio
		cFactura = null;
		cPeriodoGen = null; 
		f = null; 
				
		return error;
	}
	
	private ArrayList<VentasM> actualizoVentasM(ArrayList<AuxAnDeudaCli> lista, Date fecha) throws ApplicationException {
		//declaro las variables que voy a usar
		cVentasM = new CtrlVentasM(); 
		cPeriodoGen = new CtrlPeriodoDeudaGen();
		VentasM v = null; 
		ArrayList<VentasM> error = new ArrayList<>();
		double imp = 0;
			
		for(AuxAnDeudaCli a: lista) {
			v = new VentasM();
			imp = a.getIMPORTE() - a.getIMPORTEPAGADO();
			if(imp < 0.01 ) { v.setPAGADO("S"); }
			else { v.setPAGADO("N"); }
			
			imp = v.getA_CUENTA() + a.getIMPORTEPAGADO(); 
			v.setA_CUENTA(imp);
			
			v.setPREFIJO(a.getNCOMP().substring(2,6));
			v.setNCOMP(a.getNCOMP().substring(7));
			v.setTCOMP("1");
			v.setLETRA(a.getNCOMP().substring(0,1));
			v.setCODCLI(a.getCODCLI());

			if(cVentasM.modificaVentasMImporte(v) != true) { error.add(v); }
			
		}
				
		// Pongo en null las varaibles para liberar espacio
		cVentasM = null;
		cPeriodoGen = null; 
		v = null; 
				
		return error;
	}
	
	private ArrayList<Ctactecliente> actualizoCtaCte(ArrayList<AuxAnDeudaCli> lista, Date fecha, String nroRecibo) throws ApplicationException {
		//declaro las variables que voy a usar
		cCtacte = new CtrlCtactecliente();
		Ctactecliente c = null; 
		ArrayList<Ctactecliente> error = new ArrayList<>();
			
		for(AuxAnDeudaCli a: lista) {
			nroRecibo = calculoRecibo(nroRecibo);
			c = new Ctactecliente();
			c.setCODCLI(a.getCODCLI());
			c.setFMOV(fecha);
			c.setTMOV("7");
			c.setLCOMP(null);
			c.setPCOMP(null);
			c.setNCOMP(nroRecibo);
			c.setFCOMPORIG(fecha);
			c.setLCOMPORIG(a.getNCOMP().substring(0,1));
			c.setPCOMPORIG(a.getNCOMP().substring(2,6));
			c.setTCOMPORIG("1");
			c.setNCOMPORIG(a.getNCOMP().substring(7));
			c.setHABER(0);
			c.setDEBE(a.getIMPORTEPAGADO());
			
			if(cCtacte.altaCtaCte(c) != true) { error.add(c); }
		}
				
		// Pongo en null las varaibles para liberar espacio
		cCtacte = null;
		c = null; 
				
		return error;
	}
	
	private String calculoRecibo(String nroRecibo) throws ApplicationException {
		int nro = Integer.parseInt(nroRecibo);
		nro = nro +1;
		return Integer.toString(nro);
	}
	
	private void cambiaLetraPeriodo(String p, String c) throws ApplicationException {
		
		//entra al metodo
		cPeriodoGen = new CtrlPeriodoDeudaGen();
		PeriodoDeudaGen pdg = cPeriodoGen.consultaPeriodoDeudaGen(p, c);
		pdg.setRecibos_gen("S");
		cPeriodoGen.modificaPeriodoDeudaGen(pdg);
		
		//anulo las variables
		cPeriodoGen = null; 
		pdg = null; 
	}
	
	
}

/*
 * 
 * AYUDAS PARA ENTENDER MEJOR EL CODIGO
 * 
 * 
*/
// (1) El tipo de archivo identifica que tipo de archivo ingresa masivamente, es decir lo que se informa de la muni tiene un formato,
//     y usa el metodo leoMasivoMuni, en tando los jubilados los informa el IMPSR y usa otro formato distinto. Con lo cual hay dos metodos para la lectura.