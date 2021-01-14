package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlAuxAnDeudaCli;
import controladores.CtrlAuxTxtxConvPeriodoConc;
import controladores.CtrlCliente;
import controladores.CtrlConvenio;
import controladores.CtrlPeriodoDeudaGen;
import controladores.CtrlVentasM;
import entidades.AuxAnDeudaCli;
import entidades.AuxTxtxConvPeriodoConc;
import entidades.Cliente;
import entidades.Convenio;
import entidades.PeriodoDeudaGen;
import entidades.VentasM;
import util.ApplicationException;

/**
 * Servlet implementation class GeneraTXT
 */
@WebServlet(urlPatterns = {"/GeneraTXT"})
public class GeneraTXT extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String urlGeneraTxt = "/amema/views/generaTXT.jsp";
	private CtrlConvenio cConvenio = null;
	private CtrlAuxAnDeudaCli cAuxCliente = null; 
	private CtrlVentasM cVentasM = null; 
	private CtrlCliente cCliente = null; 
	private CtrlAuxTxtxConvPeriodoConc cAuxTXT = null; 
	private CtrlPeriodoDeudaGen cPeriodoGen = null; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneraTXT() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("evento_generaTXT") != null) {
			try { 
				generaTXT(request);
				response.sendRedirect(urlGeneraTxt);}
			catch( ApplicationException | IOException | ParseException e) { e.printStackTrace(); }
		}
	}
	
	
	//METODOS PRIMARIOS
	private void generaTXT(HttpServletRequest req) throws ApplicationException, ParseException {

		//Formateo de datos 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		//Variables que recupero del form
		Date fecha = df.parse(req.getParameter("fecha"));
		String convenio = req.getParameter("convenio");
		
		//Variables que voy a usar
		Calendar fec = Calendar.getInstance();
		fec.setTime(fecha); 
		String periodo; 
		String msj = "siAlta";
		
		//Variables de entidades
		Convenio conv = new Convenio();
		
		//Controladores de entidades que nesecito
		cConvenio = new CtrlConvenio();
		CtrlAuxAnDeudaCli cAuxiliarCliente = new CtrlAuxAnDeudaCli();
		cAuxTXT = new CtrlAuxTxtxConvPeriodoConc();
		
		//Arrays que necesito
		ArrayList<AuxTxtxConvPeriodoConc> txt = new ArrayList<>();
		ArrayList<AuxAnDeudaCli> listaDeudores = new ArrayList<>();  
		
		//Fin Variables
		
		
		//Extraigo el mes y el año para formar el periodo que voy a usar actualmente
		periodo =  "0"+Integer.toString(fec.get(Calendar.MONTH)+1)+Integer.toString(fec.get(Calendar.YEAR));
		
		//Recupero los datos del convenio
		conv = cConvenio.consultaConvenio(convenio);
		
		//Valido que no haya datos para el periodo actual y el convenio ingresados en AUXANDEUDACLI, si los hay los elimino y los vuelvo a generar
		if(cAuxiliarCliente.hayDatosEnPeriodoyConvenio(periodo, convenio) == true) { cAuxiliarCliente.eliminaPeriodoyConvenio(convenio, periodo);}
		
		if(armoTablaAuxCliente(periodo, convenio, fecha) == false) {msj = "noAlta"; }
		
		
		//Busco si hay informacion en la tabla AUXTXTXCONVPERIODOCONC para eliminarlo segun el periodo que se ingreso
		txt = cAuxTXT.listarPeriodo(periodo);
		if(txt != null) { cAuxTXT.bajaPeriodo(periodo); }
		
		//Busco los importes de AUXANDEUDACLI para genera el txt 
		listaDeudores = cAuxiliarCliente.totalDeudaPeriodoyConvenio(convenio, periodo);

		//Cargo la tabla auxiliar para el periodo y convenio seleccionado
		if(conv.getCCOND().equals("20")) {
			if(armoTablaAuxTXT(listaDeudores, conv, periodo) == false) {msj = "noAlta"; }
		}
		else {
			if(armoTablaAuxTXTJubilado(listaDeudores, conv, periodo) == false) {msj = "noAlta"; }
		}
		
		//Genero el periodo generado de archivo
		if(cargoPeriodoArchivoGenerado(convenio, periodo) == false) {msj = "noAlta"; }
		
		//Si el convenio es 20 genero los primeros 3, si es 30 genero el ultimo
		if(conv.getCCOND().equals("20")) {
			//Genero el txt para el 2109
			if(armoTXT("2109", periodo) == false) { msj = "no2109"; }
			
			//Genero el txt para el 2206
			if(armoTXT("2206", periodo) == false) { msj = "no2206"; }
			
			//Genero el txt para el 3000
			if(armoTXT("3000", periodo) == false) { msj = "no3000"; }
		}
		else {
			//Genero el txt para los jubilados
			if(armoTXTJubilados("0", periodo) == false) { msj = "no0"; }
		}
		
		//Finalizo las variables que use
		conv = null;
		cConvenio = null;
		cAuxCliente = null;
		cAuxTXT = null;
		
		//Seteo el mensaje para que se muestre en la APP
		req.getSession().setAttribute("msj", msj);
	}
	
	
	/*
	 * 
	 * 
	 * 
	 */
	
	// Metodos Secundarios

	private boolean armoTablaAuxCliente(String periodo, String convenio, Date fecha) throws ApplicationException {
		
		//Inicializo las variables que voy a usar
		
		//Arrays 
		ArrayList<VentasM> deudas = new ArrayList<>();
		
		//Entidades
		AuxAnDeudaCli aux = null; 
		Cliente cli = null; 
		
		//Controladores
		cVentasM = new CtrlVentasM();
		cAuxCliente = new CtrlAuxAnDeudaCli();
		cCliente = new CtrlCliente();
		
		//Variables
		String comprobante, saldo; 
		boolean r = true; 
		
		//FIN VARIABLES
		
		//Recupero todas las ventasM que cumplen con las condiciones impuestas
		deudas = cVentasM.listarVentasParatablaAux(convenio, fecha);
		
		//Cargo las deudas del mes en AUXANDEUDACLI
		for(VentasM v : deudas) {
			
			if(convenio.equals("20")) {
				//Valido que el cliente sea de MR, CM, PP, TC o PI y recupero la data. Sino lo uso para genera el archivo de jubilados
				cli = new Cliente(); 
				cli = cCliente.consultaCliente(v.getCODCLI());
				if(validaEmpresa(cli.getCPCCP()) == true) {

					//Busco tipoMov y le asigno lo que corresponda
					if(v.getTCOMP().equals("1")) { comprobante = "FAC"; }
					else { comprobante = "ND "; }
					
					//Busco el saldo y le asigno lo que corresponda
					if(v.getSUBTOTAL() !=  0) { saldo = "S"; }
					{ saldo = "N"; }
					
					//Cargo los datos en aux y recupero los datos del cliente
					aux = new AuxAnDeudaCli(); 
					aux.setCODCLI(v.getCODCLI());
					aux.setNOMCLI(cli.getNOMCLI());
					aux.setTCOMP(comprobante);
					aux.setTIPOMOV("1");
					aux.setFCOMP(fecha);
					aux.setNCOMP(v.getNCOMP());
					aux.setIMPORTE(v.getSUBTOTAL());
					aux.setNVIAJ(v.getNVIAJ());
					aux.setREF(v.getREFERENCIA());
					aux.setCONVENIO(convenio);
					aux.setANALISIS(Integer.parseInt(v.getANALISIS()));
					aux.setPERIODO(periodo);
					aux.setSALDO(saldo);
					aux.setIMPORTEPAGADO(0);
					
					//Hago el alta, si esta bien que no haga nada. Si falla sale del bucle y devuelve false
					if(cAuxCliente.altaAuxAnDeudaCli(aux) == false) { 
						r = false;
						break; 
					}
				}
			}
			if(convenio.equals("30")) {
				//Genero los datos para jubilados
				//Valido que el cliente no sea de MR, CM, PP, TC o PI y recupero la data
				cli = new Cliente(); 
				cli = cCliente.consultaCliente(v.getCODCLI());
				if(validaJubilado(cli.getCPCCP()) == true ) {
				
					//Busco tipoMov y le asigno lo que corresponda
					if(v.getTCOMP().equals("1")) { comprobante = "FAC"; }
					else { comprobante = "ND "; }
					
					//Busco el saldo y le asigno lo que corresponda
					if(v.getSUBTOTAL() !=  0) { saldo = "S"; }
					{ saldo = "N"; }
					
					//Cargo los datos en aux y recupero los datos del cliente
					aux = new AuxAnDeudaCli(); 
					aux.setCODCLI(v.getCODCLI());
					aux.setNOMCLI(cli.getNOMCLI());
					aux.setTCOMP(comprobante);
					aux.setTIPOMOV("1");
					aux.setFCOMP(fecha);
					aux.setNCOMP(v.getNCOMP());
					aux.setIMPORTE(v.getSUBTOTAL());
					aux.setNVIAJ(v.getNVIAJ());
					aux.setREF(v.getREFERENCIA());
					aux.setCONVENIO(convenio);
					aux.setANALISIS(Integer.parseInt(v.getANALISIS()));
					aux.setPERIODO(periodo);
					aux.setSALDO(saldo);
					aux.setIMPORTEPAGADO(0);
					
					//Hago el alta, si esta bien que no haga nada. Si falla sale del bucle y devuelve false
					if(cAuxCliente.altaAuxAnDeudaCli(aux) == false) { 
						r = false;
						break; 
					}
				}
			}
		}
		
		//Finalizo las variables que use
		aux = null; 
		cli = null; 
		cVentasM = null;
		cCliente = null;
		cAuxCliente = null; 
		
		//Devuelvo la rta
		return r; 
	}

	private boolean armoTablaAuxTXT(ArrayList<AuxAnDeudaCli>listaDeudores, Convenio conv, String periodo) throws ApplicationException {
		//Declaro las variables que voy a usar
		boolean rta = true; 
		String cpto1, cpto2, cpto3;
		double tope1, tope2, tope3, cuota, varios, excedente; 
		cCliente = new CtrlCliente();
		cAuxTXT = new CtrlAuxTxtxConvPeriodoConc();
		Cliente cli = null; 
		AuxTxtxConvPeriodoConc auxTXT1 = null; 
		AuxTxtxConvPeriodoConc auxTXT2 = null; 
		AuxTxtxConvPeriodoConc auxTXT3 = null; 
		ArrayList<AuxTxtxConvPeriodoConc> listaTXT = new ArrayList<>();
		
		//Inicializo las variables porque me lo pide
		cuota = 0; 
		varios = 0; 
		excedente = 0; 
		
		//Asigno los conceptos y topes a las variables segun el convenio que paso para hacer las divisiones.
		cpto1 = conv.getConc1(); //2109 Cuota
		cpto2 = conv.getConc2(); //2206 Varios
		cpto3 = conv.getConc3(); //3000 Excedente
		tope1 = conv.getTope1(); //Valor cuota 9.50
		tope2 = conv.getTope2(); //Valor entre 9.50 y 30000
		tope3 = conv.getTope3(); //Valor entre 30000 y 40000

		//Recorro el array de las deudas y hago las divisiones segun lo que corresponda y agrego la info que necesito de Cliente
		for(AuxAnDeudaCli a: listaDeudores) {
			
			//Recupero los datos del cliente
			cli = cCliente.consultaCliente(a.getCODCLI());
			
			//Hago las divisiones de los montos para los conceptos 
			if(a.getIMPORTE() == tope1) { 
				cuota = a.getIMPORTE();
				varios = 0; 
				excedente = 0;
			}
			
			if(a.getIMPORTE() <= tope2) {
				cuota = tope1;
				varios = a.getIMPORTE(); 
				excedente = 0;
			}
			
			if(a.getIMPORTE() > tope2 && a.getIMPORTE() <= tope3) {
				cuota = tope1;
				varios = tope2;
				excedente = a.getIMPORTE() - tope2; 
			}
			
			//Instancio las variables auxiliares y las guardo en un array
			if(cuota != 0) { 
				auxTXT1 = new AuxTxtxConvPeriodoConc(periodo, conv.getCCOND(), cpto1, cli.getCPCCP(), cli.getDNRP(), a.getCODCLI(), cuota);
				listaTXT.add(auxTXT1);
			}
			if(varios != 0) {
				auxTXT2 = new AuxTxtxConvPeriodoConc(periodo, conv.getCCOND(), cpto2, cli.getCPCCP(), cli.getDNRP(), a.getCODCLI(), varios);
				listaTXT.add(auxTXT2);
			}
			if(excedente != 0) {
				auxTXT3 = new AuxTxtxConvPeriodoConc(periodo, conv.getCCOND(), cpto3, cli.getCPCCP(), cli.getDNRP(), a.getCODCLI(), excedente);
				listaTXT.add(auxTXT3);
			}
		}
		
		//Recorro el array para hacer las altas en el AUXTXTXCONVPERIODOCONC
		for(AuxTxtxConvPeriodoConc aux : listaTXT) {
			if(cAuxTXT.altaRegistro(aux) == false) {
				rta = false; 
				break;
			}
		}
		
		//Finalizo las variables que use
		cCliente = null;
		cAuxTXT = null;
		cli = null; 
		auxTXT1 = null; 
		auxTXT2 = null; 
		auxTXT3 = null; 
		
		//Devuelvo la respuesta
		return rta; 
	}
	
	private boolean armoTablaAuxTXTJubilado(ArrayList<AuxAnDeudaCli>listaDeudores, Convenio conv, String periodo) throws ApplicationException {
		//Declaro las variables que voy a usar
		boolean rta = true; 
		String cpto = "0";
		
		//Controladores
		cCliente = new CtrlCliente();
		cAuxTXT = new CtrlAuxTxtxConvPeriodoConc();
		
		//Entidades
		Cliente cli = null; 
		AuxTxtxConvPeriodoConc auxTXT1 = null; 
		
		//arrays
		ArrayList<AuxTxtxConvPeriodoConc> listaTXT = new ArrayList<>();
		
		
		//Fin Variables
		
		
		//Recorro el array de las deudas y hago las divisiones segun lo que corresponda y agrego la info que necesito de Cliente
		for(AuxAnDeudaCli a: listaDeudores) {
			
			//Recupero los datos del cliente
			cli = cCliente.consultaCliente(a.getCODCLI());
			auxTXT1 = new AuxTxtxConvPeriodoConc(periodo, conv.getCCOND(), cpto, cli.getCPCCP(), cli.getDNRP(), a.getCODCLI(), a.getIMPORTE());
			listaTXT.add(auxTXT1);
			
		}
		
		//Recorro el array para hacer las altas en el AUXTXTXCONVPERIODOCONC
		for(AuxTxtxConvPeriodoConc aux : listaTXT) {
			if(cAuxTXT.altaRegistro(aux) == false) {
				rta = false; 
				break;
			}
		}
		
		//Finalizo las variables que use
		cCliente = null;
		cAuxTXT = null;
		cli = null; 
		auxTXT1 = null; 
		
		//Devuelvo la respuesta
		return rta; 
	}
	
	private boolean cargoPeriodoArchivoGenerado(String convenio, String periodo) throws ApplicationException {
		//Creo las variables que voy a usar
		boolean rta = true; 
		cPeriodoGen = new CtrlPeriodoDeudaGen();
		int id = cPeriodoGen.ultimoID() + 1; 
		Date fecha = new Date();
		PeriodoDeudaGen perArchivo = new PeriodoDeudaGen(id, "N", convenio, periodo, "N", "N", fecha, fecha);
		
		//Grabo la data
		if (cPeriodoGen.altaPeriodoDeudaGen(perArchivo) == false) { rta = false; }
		
		//Finalizo las variables 
		cPeriodoGen = null; 
		perArchivo = null; 
		
		//Devuelvo la rta
		return rta; 
	}
	
	private boolean armoTXT(String concepto, String periodo) throws ApplicationException {
		//Declaro las variables que voy a usar
		
		//Variables
		String aux, leg, imp, form; 
		int nro, legajo; 
		String ruta = "c:/Municipalidad/"+periodo+"/";
		
		//Controladores
		cAuxTXT = new CtrlAuxTxtxConvPeriodoConc();
		
		//Arrays
		ArrayList<AuxTxtxConvPeriodoConc> deudores = new ArrayList<>();
		ArrayList<String> datosTXT = new ArrayList<>();
		
		//Recupero los deudores segun el concepto
		deudores = cAuxTXT.listarConceptoPeriodo(concepto, periodo);
		
		//Finalizo el controlador
		cAuxTXT = null; 
		
		//Metodo para generar txt
		try {
			
			//Creo el directorio si no existe
			
			File directorio = new File(ruta);
			if(!directorio.exists()) { directorio.mkdirs(); }
			
			
			//Creo el txt en la ubicacion que deseo con el nombre que quiero y con la configuracion de la letra, es decir,
			// me va a imprimir correctamente los caracteres raros.
			PrintWriter writer = new PrintWriter(ruta+"enti"+concepto+".txt","UTF-8");
			
			//Recorro el array de deudores
			for(AuxTxtxConvPeriodoConc d: deudores) {
				
				//genero el nro entero para poder hacer el formato
				form = String.format("%.02f", d.getImporte());
				
				//Divido el form segun la coma y lo asigo a form
				String[] partes = form.split(",");
				form = partes[0]+partes[1];
				
				//paso el String a entero para despues formatearlo segun lo que requiero
				nro = Integer.parseInt(form);
				legajo = Integer.parseInt(d.getLegajo());
				
				leg = String.format("%06d", legajo);
				imp = String.format("%09d", nro);
				aux = leg+concepto+imp+d.getEmpresa();
				datosTXT.add(aux);
			}
	
			//Recorro el array que hice con los registros para el txt
			for(String var: datosTXT) { writer.println(var); }
			
			//Cierro el txt 
			writer.close();
			
			return true; 
		}
		catch (Exception e) { 
			e.printStackTrace();
			return false; 
		}

	}
	
	private boolean armoTXTJubilados(String concepto, String periodo) throws ApplicationException {
		//Declaro las variables que voy a usar
		
		//Variables
		String aux, empresa, leg, nombre, imp; 
		int nro, legajo; 
		String ruta = "c:/Municipalidad/"+periodo+"/";
		
		//Controladores
		cAuxTXT = new CtrlAuxTxtxConvPeriodoConc();
		cCliente = new CtrlCliente();
		
		//Entidades
		Cliente cli = null; 
		
		//Arrays
		ArrayList<AuxTxtxConvPeriodoConc> deudores = new ArrayList<>();
		ArrayList<String> datosTXT = new ArrayList<>();
		
		//Recupero los deudores segun el concepto
		deudores = cAuxTXT.listarConceptoPeriodo(concepto, periodo);
		
		//Finalizo el controlador
		cAuxTXT = null; 
		
		//Metodo para generar txt
		try {
			
			//Creo el directorio si no existe
			
			File directorio = new File(ruta);
			if(!directorio.exists()) { directorio.mkdirs(); }
			
			
			//Creo el txt en la ubicacion que deseo con el nombre que quiero y con la configuracion de la letra, es decir,
			// me va a imprimir correctamente los caracteres raros.
			PrintWriter writer = new PrintWriter(ruta+"JUB.txt","UTF-8");
			
			//Recorro el array de deudores
			for(AuxTxtxConvPeriodoConc d: deudores) {
				cli = cCliente.consultaCliente(d.getCodcli());
				if(cli.getCPCCP().equals("CJ")) { empresa = "J"; }
				else { empresa = "P"; }
				nombre = String.format("%-30s", cli.getNOMCLI()); 
				String[] partes = cli.getDNRP().split("/"); //Divido el legajo por la barra, asi lo asigno como entero
				leg = partes[0]+partes[1]; //Le asigno las dos partes a leg
				legajo = Integer.parseInt(leg); //Lo paso a entero para agregarles los ceros delante
				leg = String.format("%08d", legajo); //Le agrego los ceros correspondientes y lo paso a String
				
				
				
				imp = String.format("%.02f", d.getImporte()); //Genero el String redondeado a dos decimales
				String[] nros = imp.split(","); //Divido el decimal por la coma
				nro = Integer.parseInt(nros[0]); //Le agrego los ceros delante y lo paso a String
				imp = String.format("%05d", nro)+"."+nros[1]; //Genero el String del importe
								
				aux = empresa+leg+nombre+imp;
				datosTXT.add(aux);
			}
	
			//Recorro el array que hice con los registros para el txt
			for(String var: datosTXT) { writer.println(var); }
			
			//Cierro el txt 
			writer.close();
			
			return true; 
		}
		catch (Exception e) { 
			e.printStackTrace();
			return false; 
		}

	}
	
	/*
	 * FUNCIONES TERCIARIAS
	 * 
	 */
	
	private boolean validaEmpresa(String empresa) throws ApplicationException {
		boolean r = false; 
		switch (empresa.toUpperCase()) {
		case "CM":
			r = true;
			break;
		case "MR":
			r = true;
			break;
		case "PP":
			r = true;
			break;
		case "PI":
			r = true;
			break;
		case "TC":
			r = true;
			break;
		}
		return r;
	}
	
	private boolean validaJubilado(String empresa) throws ApplicationException {
		boolean r = false; 
		switch (empresa.toUpperCase()) {
		case "CJ":
			r = true;
			break;
		case "PN":
			r = true;
			break;
		}
		return r;
	}
}
