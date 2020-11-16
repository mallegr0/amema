package servlets;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlAuxAnDeudaCli;
import controladores.CtrlAuxListaMasivo;
import controladores.CtrlCliente;
import controladores.CtrlPeriodoDeudaArch;
import entidades.AuxAnDeudaCli;
import entidades.AuxListaMasivo;
import entidades.Entrada;
import entidades.PeriodoDeudaArch;
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
	private CtrlAuxListaMasivo cListarAux = null; 
	private DecimalFormat df = null; //formato para el importe
	private String urlAMasivo = "/amema/views/actualizaPagosMasivo.jsp";
	private String urlLMasivo = "/amema/views/listaPagosMasivos.jsp";
	
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
				listarMasivo(request, response);
				response.sendRedirect(urlLMasivo);
			}
			catch(ApplicationException | ParseException e) { e.printStackTrace(); }
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
		
		//Ahora actualizo los importes de AuxAnDeudaCli para luego generar los recibos. y lo seteo en el request
		req.getSession().setAttribute("errores", actualizoAuxiliar(ingresos, listadoAuxCli));
		req.getSession().setAttribute("mensaje", mensaje);

	}
	
	private void listarMasivo(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, ParseException {
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


}

/*
 * 
 * AYUDAS PARA ENTENDER MEJOR EL CODIGO
 * 
 * 
*/
// (1) El tipo de archivo identifica que tipo de archivo ingresa masivamente, es decir lo que se informa de la muni tiene un formato,
//     y usa el metodo leoMasivoMuni, en tando los jubilados los informa el IMPSR y usa otro formato distinto. Con lo cual hay dos metodos para la lectura.