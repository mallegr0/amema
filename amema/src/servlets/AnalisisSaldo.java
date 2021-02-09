package servlets;

import java.io.IOException;
import java.io.OutputStream;
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

import controladores.CtrlAnticC;
import controladores.CtrlAuxAnDeudaSoc;
import controladores.CtrlCheque;
import controladores.CtrlCliente;
import controladores.CtrlFacRet;
import controladores.CtrlVentasM;
import entidades.AnalisisSaldoListado;
import entidades.AnticipoAnalisisSaldo;
import entidades.AuxAnDeudaSoc;
import entidades.ChequeAnalisisSaldo;
import entidades.Cliente;
import entidades.FacturaAnalisisSaldo;
import entidades.VentasM;
import reportes.HeaderFooterPageEvent;
import entidades.Usuario; 
import util.ApplicationException;

/**
 * Servlet implementation class AnalisisSaldo
 */
@WebServlet(urlPatterns = {"/AnalisisSaldo"}, name="AnalisisSaldo")
public class AnalisisSaldo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CtrlAuxAnDeudaSoc cDeudaSocio = null; 
	private CtrlVentasM cVentasM = null; 
	private CtrlCliente cCliente = null; 
	private CtrlAnticC cAnticipos = null; 
	private CtrlFacRet cFacRet = null; 
	private CtrlCheque cCheques = null; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnalisisSaldo() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("evento_buscarSaldos") != null) {
			try {
				imprimirSaldos(request, response);
			} catch (ApplicationException | ParseException |IOException e) { e.printStackTrace(); }
		}
	}
	
	
	//METODOS PRIMARIOS
	
	private void imprimirSaldos(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, ParseException, IOException {
		/*
		 * VARIABLES
		 */
		
		//variables de formateo
		SimpleDateFormat sdFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		//Variables del form
		String[]partes = req.getParameter("fecha").split("-");
		Date fecha = (Date) sdFormat.parse(partes[2]+"-"+partes[1]+"-"+partes[0]);
		String socios = req.getParameter("socios"); 
		String convenio = req.getParameter("convenio");
		Usuario user = (Usuario) req.getSession().getAttribute("usuarioActivo");
		
		//Variables
		String msj; 
		boolean r = true; 
		
		//FIN VARIABLES

		//Armo la tabla auxiliar
		if(socios != null) {
			r = armoTablaAuxiliarTodos();
			if(r == false) {
				msj = "noTabla";
				req.getSession().setAttribute("msj", msj);
			}
		}
		
		if(convenio != null) {
			r = armoTablaAuxiliarConvenio(convenio);
			if(r == false) {
				msj = "noTabla";
				req.getSession().setAttribute("msj", msj);
			}
		}
		
		//imprimo el pdf
		if( r == true) {
			if(generoPdf(res, fecha, user) == true) { msj = "siPDF"; }
			else { msj = "noPDf"; }
			req.getSession().setAttribute("msj", msj);
		}
	}
	
	
	/*
	 * METODOS SECUNDARIOS
	 */
	
	private boolean armoTablaAuxiliarTodos() throws ApplicationException, ParseException {
		//formateos
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		//Variables
		boolean r = true; 
		Date fechaComp;
		String tComp, nombre, saldo, codcli, viajante, nComp, obs; 
		double importeSaldo = 0;
		
		//Controladores
		cDeudaSocio = new CtrlAuxAnDeudaSoc(); 
		cCliente = new CtrlCliente();
		cVentasM = new CtrlVentasM();
		cAnticipos = new CtrlAnticC();
		cFacRet = new CtrlFacRet();
		cCheques = new CtrlCheque();
		
		//Entidades
		Cliente cliente = null; 
		AuxAnDeudaSoc auxDeuda = null; 
		
		//arrays
		ArrayList<VentasM> deudas = new ArrayList<>();
		ArrayList<AnticipoAnalisisSaldo> anticiposSaldos = new ArrayList<>();
		ArrayList<FacturaAnalisisSaldo> facturasSaldos = new ArrayList<>();
		ArrayList<ChequeAnalisisSaldo> chequesSaldos = new ArrayList<>();
		
		
		
		//elimino todo lo que haya en la tabla AUXANDEUDASOC si es true que siga, sino devuelvo false
		if(cDeudaSocio.cantidadRegistros() > 0) {
			if(cDeudaSocio.bajaTodo() == false) { r = false; }
		}
		
		//MENSAJES
		System.out.println("Baja de tabla ........... OK");
		//Si r sigue siendo TRUE es por se elimino todo bien. ahora cargo los datos en la tabla 
		//y sigo si salio todo bien, sino pongo r en FALSE.
		if(r == true) {
			deudas = cVentasM.listarAnalisisDeuda();
			
			//recorro el array y voy calculando cada campo y valor necesario. 
			for(VentasM d: deudas) {
				cliente = cCliente.consultaCliente(d.getCODCLI());
				if(d.getA_CUENTA() != 0) { saldo = "S"; }
				else { saldo = "N"; }
				if(d.getTCOMP().equals("1")) { tComp = "FAC"; }
				else { tComp = "ND"; }
				importeSaldo = d.getSUBTOTAL() - d.getA_CUENTA();
				nombre = cliente.getNOMCLI(); 
				if(nombre.equals(null)) { nombre = "-"; }
				fechaComp = (Date) d.getFMOV(); 
				if(fechaComp == null) { fechaComp = null; }
				nComp = d.getLETRA()+d.getPREFIJO()+d.getNCOMP();
				viajante = cliente.getNVIAJ(); 
				if(viajante.equals(null)) { viajante = "-"; }
				obs = d.getTEXTLIB(); 
				if(obs == null) { obs = "-"; }
				else { obs = obs.substring(0,33); }
				
				
				//Hago la alta en AUXANDEUDASOC
				auxDeuda = new AuxAnDeudaSoc();
				auxDeuda.setCodcli(d.getCODCLI());
				auxDeuda.setNomcli(nombre);
				auxDeuda.setTipoMov("1");
				auxDeuda.setFcomp(fechaComp);
				auxDeuda.setNcomp(nComp);
				auxDeuda.setTcomp(tComp);
				auxDeuda.setImporte(importeSaldo);
				auxDeuda.setNviaj(viajante);
				auxDeuda.setObsMov(obs);
				auxDeuda.setSaldo(saldo);
				
				if(cDeudaSocio.altaSocio(auxDeuda) == false) { 
					r = false;
					break;
				}
			}
		}
		//MENSAJES
				System.out.println("Altas de ventasM ........... OK");
		
		//Recupero los anticipos que tenga la persona si la variable r sigue siendo true
		if(r == true) {
			anticiposSaldos = cAnticipos.listarAnticiposParaAnalisisSaldo(); 
			
			//recorro los anticipos y calculo lo que tengo que calcular
			for(AnticipoAnalisisSaldo a: anticiposSaldos) {
				if(a.getSumaApliacado_a() != 0) { saldo = "S"; }
				else { saldo = "N"; }
				tComp = "ANT";  
				nombre = a.getNomcli();
				if(nombre.equals(null)) { nombre = "-"; }
				importeSaldo = (a.getMonto_a() - a.getSumaApliacado_a())*-1; 
				fechaComp = a.getFrecibo();
				if(fechaComp == null) { fechaComp = null; }
				nComp = a.getNrecibo();
				viajante = a.getNviaj();
				if(viajante.equals(null)) { viajante = "-"; }
				
				//asigno los anticipos en negativo
				if(importeSaldo != 0) {
					auxDeuda = new AuxAnDeudaSoc(); 
					auxDeuda.setCodcli(a.getCodcli());
					auxDeuda.setNomcli(nombre);
					auxDeuda.setTipoMov("2");
					auxDeuda.setFcomp(fechaComp);
					auxDeuda.setNcomp(nComp);
					auxDeuda.setImporte(importeSaldo);
					auxDeuda.setNviaj(viajante);
					auxDeuda.setSaldo(saldo);
					 
					if(cDeudaSocio.altaSocio(auxDeuda) == false) {
						r = false; 
						break;
					}
				}
			}
		}
		//MENSAJES
				System.out.println("altas de Anticipos ........... OK");
				
		//recupero las NC si r = TRUE
		if(r == true) {
			deudas = cVentasM.listarAnalisisDeuda();
			
			//recorro el array y voy calculando cada campo y valor necesario. 
			for(VentasM d: deudas) {
				cliente = cCliente.consultaCliente(d.getCODCLI());
				if(d.getA_CUENTA() != 0) { saldo = "S"; }
				else { saldo = "N"; }
				tComp = "NC"; 
				importeSaldo = (d.getSUBTOTAL() - d.getA_CUENTA())*-1;
				nombre = cliente.getNOMCLI();
				if(nombre.equals(null)) { nombre = "-"; }
				
				if(importeSaldo != 0) {
					//Hago la alta en AUXANDEUDASOC con los NC en negativo
					auxDeuda = new AuxAnDeudaSoc();
					auxDeuda.setCodcli(d.getCODCLI());
					auxDeuda.setNomcli(nombre);
					auxDeuda.setTipoMov("2");
					auxDeuda.setFcomp(d.getFMOV());
					auxDeuda.setNcomp(d.getLETRA()+d.getPREFIJO()+d.getNCOMP());
					auxDeuda.setTcomp(tComp);
					auxDeuda.setImporte(importeSaldo);
					auxDeuda.setNviaj(cliente.getNVIAJ());
					auxDeuda.setRef(d.getREFERENCIA());
					auxDeuda.setSaldo(saldo);
						
					if(cDeudaSocio.altaSocio(auxDeuda) == false) { 
						r = false;
						break;
					}
				}
			}
		}
		//MENSAJES
				System.out.println("Altas NC ........... OK");
				
		//Veo si hay retenciones y si las hay las cargo en negativo, siempre y cuando r = TRUE
		if(r == true) {
			facturasSaldos = cFacRet.listarRetencionesAnalisisSaldo();
			
			for(FacturaAnalisisSaldo f: facturasSaldos) {
				//grabo los datos en las variables auxiliares para grabarlas en la tabla aux
				fechaComp = f.getFec_ret(); 
				if(fechaComp == null) { fechaComp = (Date) sdFormat.parse("1900-01-01"); }
				tComp = "RET"; 
				importeSaldo = -1*f.getMonto_a();
				nombre = f.getNomcli();
				if(nombre.equals(null)) { nombre = "-"; }
				
				if(importeSaldo != 0) {
					auxDeuda = new AuxAnDeudaSoc();
					auxDeuda.setCodcli(f.getCodcli());
					auxDeuda.setNomcli(nombre);
					auxDeuda.setTipoMov("2");
					auxDeuda.setFcomp(fechaComp);
					auxDeuda.setNcomp(f.getConstancia());
					auxDeuda.setTcomp(tComp);
					auxDeuda.setImporte(importeSaldo);
					auxDeuda.setNviaj(f.getNviaj());
					
					if(cDeudaSocio.altaSocio(auxDeuda) == false) {
						r = false;
						break;
					}
				}
			}
		}
		
		//MENSAJES
				System.out.println("Altas de Retenciones ........... OK");
				
		//Recupero los cheques que haya 
		if(r == true) {
			chequesSaldos = cCheques.listarChequesAnalisisSaldo();
			
			//Recorro los cheques y calculo los valores que tenga y actualizo la tabla
			for(ChequeAnalisisSaldo c: chequesSaldos) {
				fechaComp = (Date) sdFormat.parse("1000-01-01"); 
				tComp = "CHD"; 
				nombre = c.getNomcli(); 
				if(nombre.equals(null)) { nombre = "-"; }
				importeSaldo = c.getMonto(); 
				if(Double.isNaN(importeSaldo)) { importeSaldo = 0; }
				codcli = c.getCodcli();
				if(codcli.equals(null)) { codcli = "0000"; }
				viajante = c.getNviaj();
				if(viajante.equals(null)) { viajante = "-"; }
				
				
				if(importeSaldo != 0) {
					auxDeuda = new AuxAnDeudaSoc();
					auxDeuda.setCodcli(codcli);
					auxDeuda.setNomcli(nombre);
					auxDeuda.setTipoMov("3");
					auxDeuda.setFcomp(fechaComp);
					auxDeuda.setNcomp("0");
					auxDeuda.setTcomp(tComp);
					auxDeuda.setImporte(importeSaldo);
					auxDeuda.setNviaj(viajante);
					
					if(cDeudaSocio.altaSocio(auxDeuda) == false) {
						r = false;
						break;
					}
				}
			}
		}
		//MENSAJES
				System.out.println("altas de cheques ........... OK");
		
		//Finalizo las variables y devuelvo la respuesta 
		cDeudaSocio = null; 
		cCliente = null;
		cVentasM = null;
		cAnticipos = null;
		cFacRet = null;
		cCheques = null;
		cliente = null; 
		auxDeuda = null; 
		
		return r; 
	}
	
	private boolean armoTablaAuxiliarConvenio(String convenio) throws ApplicationException, ParseException {
		//formateos
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				
		//Variables
		boolean r = true; 
		Date fechaComp;
		String tComp, nombre, saldo, codcli, viajante, nComp, obs; 
		double importeSaldo = 0;
				
		//Controladores
		cDeudaSocio = new CtrlAuxAnDeudaSoc(); 
		cCliente = new CtrlCliente();
		cVentasM = new CtrlVentasM();
		cAnticipos = new CtrlAnticC();
		cFacRet = new CtrlFacRet();
		cCheques = new CtrlCheque();
			
		//Entidades
		Cliente cliente = null; 
		AuxAnDeudaSoc auxDeuda = null; 
			
		//arrays
		ArrayList<VentasM> deudas = new ArrayList<>();
		ArrayList<AnticipoAnalisisSaldo> anticiposSaldos = new ArrayList<>();
		ArrayList<FacturaAnalisisSaldo> facturasSaldos = new ArrayList<>();
		ArrayList<ChequeAnalisisSaldo> chequesSaldos = new ArrayList<>();
			
				
				
		//elimino todo lo que haya en la tabla AUXANDEUDASOC si es true que siga, sino devuelvo false
		if(cDeudaSocio.cantidadRegistros() > 0) {
			if(cDeudaSocio.bajaTodo() == false) { r = false; }
		}
			
		//MENSAJES
		System.out.println("convenio nro "+convenio);
		System.out.println("Baja de tabla ........... OK");
		//Si r sigue siendo TRUE es por se elimino todo bien. ahora cargo los datos en la tabla 
		//y sigo si salio todo bien, sino pongo r en FALSE.
		if(r == true) {
			deudas = cVentasM.listarAnalisisDeudaConvenio(convenio);
					
			//recorro el array y voy calculando cada campo y valor necesario. 
			for(VentasM d: deudas) {
				cliente = cCliente.consultaCliente(d.getCODCLI());
				if(d.getA_CUENTA() != 0) { saldo = "S"; }
				else { saldo = "N"; }
				if(d.getTCOMP().equals("1")) { tComp = "FAC"; }
				else { tComp = "ND"; }
				importeSaldo = d.getSUBTOTAL() - d.getA_CUENTA();
				nombre = cliente.getNOMCLI(); 
				if(nombre.equals(null)) { nombre = "-"; }
				fechaComp = (Date) d.getFMOV(); 
				if(fechaComp == null) { fechaComp = null; }
				nComp = d.getLETRA()+d.getPREFIJO()+d.getNCOMP();
				viajante = cliente.getNVIAJ(); 
				if(viajante.equals(null)) { viajante = "-"; }
				obs = d.getTEXTLIB(); 
				if(obs == null) { obs = "-"; }
				else { obs = obs.substring(0,33); }
					
						
				//Hago la alta en AUXANDEUDASOC
				auxDeuda = new AuxAnDeudaSoc();
				auxDeuda.setCodcli(d.getCODCLI());
				auxDeuda.setNomcli(nombre);
				auxDeuda.setTipoMov("1");
				auxDeuda.setFcomp(fechaComp);
				auxDeuda.setNcomp(nComp);
				auxDeuda.setTcomp(tComp);
				auxDeuda.setImporte(importeSaldo);
				auxDeuda.setNviaj(viajante);
				auxDeuda.setObsMov(obs);
				auxDeuda.setSaldo(saldo);
				
				if(cDeudaSocio.altaSocio(auxDeuda) == false) { 
					r = false;
					break;
				}
			}
		}
		//MENSAJES
		System.out.println("Altas de ventasM ........... OK");
				
		//Recupero los anticipos que tenga la persona si la variable r sigue siendo true
		if(r == true) {
			anticiposSaldos = cAnticipos.listarAnticiposParaAnalisisSaldoConvenio(convenio); 
						
			//recorro los anticipos y calculo lo que tengo que calcular
			for(AnticipoAnalisisSaldo a: anticiposSaldos) {
				if(a.getSumaApliacado_a() != 0) { saldo = "S"; }
				else { saldo = "N"; }
				tComp = "ANT";  
				cliente = cCliente.consultaCliente(a.getCodcli());
				nombre = cliente.getNOMCLI();
				importeSaldo = (a.getMonto_a() - a.getSumaApliacado_a())*-1; 
				fechaComp = a.getFrecibo();
				if(fechaComp == null) { fechaComp = null; }
				nComp = a.getNrecibo();
				viajante = cliente.getNVIAJ();
					
				//asigno los anticipos en negativo
				if(importeSaldo != 0) {
					auxDeuda = new AuxAnDeudaSoc(); 
					auxDeuda.setCodcli(a.getCodcli());
					auxDeuda.setNomcli(nombre);
					auxDeuda.setTipoMov("2");
					auxDeuda.setFcomp(fechaComp);
					auxDeuda.setNcomp(nComp);
					auxDeuda.setImporte(importeSaldo);
					auxDeuda.setNviaj(viajante);
					auxDeuda.setSaldo(saldo);
						 
					if(cDeudaSocio.altaSocio(auxDeuda) == false) {
						r = false; 
						break;
					}
				}
			}
		}
				//MENSAJES
						System.out.println("altas de Anticipos ........... OK");
						
				//recupero las NC si r = TRUE
				if(r == true) {
					deudas = cVentasM.listarAnalisisDeudaConvenio(convenio);
					
					//recorro el array y voy calculando cada campo y valor necesario. 
					for(VentasM d: deudas) {
						cliente = cCliente.consultaCliente(d.getCODCLI());
						if(d.getA_CUENTA() != 0) { saldo = "S"; }
						else { saldo = "N"; }
						tComp = "NC"; 
						importeSaldo = (d.getSUBTOTAL() - d.getA_CUENTA())*-1;
						nombre = cliente.getNOMCLI();
						if(nombre.equals(null)) { nombre = "-"; }
						
						if(importeSaldo != 0) {
							//Hago la alta en AUXANDEUDASOC con los NC en negativo
							auxDeuda = new AuxAnDeudaSoc();
							auxDeuda.setCodcli(d.getCODCLI());
							auxDeuda.setNomcli(nombre);
							auxDeuda.setTipoMov("2");
							auxDeuda.setFcomp(d.getFMOV());
							auxDeuda.setNcomp(d.getLETRA()+d.getPREFIJO()+d.getNCOMP());
							auxDeuda.setTcomp(tComp);
							auxDeuda.setImporte(importeSaldo);
							auxDeuda.setNviaj(cliente.getNVIAJ());
							auxDeuda.setRef(d.getREFERENCIA());
							auxDeuda.setSaldo(saldo);
								
							if(cDeudaSocio.altaSocio(auxDeuda) == false) { 
								r = false;
								break;
							}
						}
					}
				}
				//MENSAJES
						System.out.println("Altas NC ........... OK");
						
				//Veo si hay retenciones y si las hay las cargo en negativo, siempre y cuando r = TRUE
				if(r == true) {
					facturasSaldos = cFacRet.listarRetencionesAnalisisSaldoConvenio(convenio);
					
					for(FacturaAnalisisSaldo f: facturasSaldos) {
						//grabo los datos en las variables auxiliares para grabarlas en la tabla aux
						fechaComp = f.getFec_ret(); 
						if(fechaComp == null) { fechaComp = (Date) sdFormat.parse("1900-01-01"); }
						tComp = "RET"; 
						importeSaldo = -1*f.getMonto_a();
						nombre = f.getNomcli();
						if(nombre.equals(null)) { nombre = "-"; }
						
						if(importeSaldo != 0) {
							auxDeuda = new AuxAnDeudaSoc();
							auxDeuda.setCodcli(f.getCodcli());
							auxDeuda.setNomcli(nombre);
							auxDeuda.setTipoMov("2");
							auxDeuda.setFcomp(fechaComp);
							auxDeuda.setNcomp(f.getConstancia());
							auxDeuda.setTcomp(tComp);
							auxDeuda.setImporte(importeSaldo);
							auxDeuda.setNviaj(f.getNviaj());
							
							if(cDeudaSocio.altaSocio(auxDeuda) == false) {
								r = false;
								break;
							}
						}
					}
				}
				
				//MENSAJES
						System.out.println("Altas de Retenciones ........... OK");
						
				//Recupero los cheques que haya 
				if(r == true) {
					chequesSaldos = cCheques.listarChequesAnalisisSaldoConvenio(convenio);
					
					//Recorro los cheques y calculo los valores que tenga y actualizo la tabla
					for(ChequeAnalisisSaldo c: chequesSaldos) {
						fechaComp = (Date) sdFormat.parse("1000-01-01"); 
						tComp = "CHD"; 
						nombre = c.getNomcli(); 
						if(nombre.equals(null)) { nombre = "-"; }
						importeSaldo = c.getMonto(); 
						if(Double.isNaN(importeSaldo)) { importeSaldo = 0; }
						codcli = c.getCodcli();
						if(codcli.equals(null)) { codcli = "0000"; }
						viajante = c.getNviaj();
						if(viajante.equals(null)) { viajante = "-"; }
						
						
						if(importeSaldo != 0) {
							auxDeuda = new AuxAnDeudaSoc();
							auxDeuda.setCodcli(codcli);
							auxDeuda.setNomcli(nombre);
							auxDeuda.setTipoMov("3");
							auxDeuda.setFcomp(fechaComp);
							auxDeuda.setNcomp("0");
							auxDeuda.setTcomp(tComp);
							auxDeuda.setImporte(importeSaldo);
							auxDeuda.setNviaj(viajante);
							
							if(cDeudaSocio.altaSocio(auxDeuda) == false) {
								r = false;
								break;
							}
						}
					}
				}
				//MENSAJES
						System.out.println("altas de cheques ........... OK");
				
				//Finalizo las variables y devuelvo la respuesta 
				cDeudaSocio = null; 
				cCliente = null;
				cVentasM = null;
				cAnticipos = null;
				cFacRet = null;
				cCheques = null;
				cliente = null; 
				auxDeuda = null; 
				
				return r; 
	}
	
	private boolean generoPdf(HttpServletResponse res, Date fecha, Usuario user) throws ApplicationException {
		//variables
		boolean r = true; 
		boolean aux = false; 
		double tEmpleado = 0;
		double tConvenio = 0;
		double tAnalisis = 0;
		int posicion = 0; 
		int contador = 0; 
		String bEmpleado, bConvenio, titEmpleado, titConvenio; 
		
		//formatos
		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat dFormat = new DecimalFormat("#0.00");
		
		//Constructores
		cDeudaSocio = new CtrlAuxAnDeudaSoc();
		cCliente = new CtrlCliente();
			
		//Arrays
		ArrayList<AnalisisSaldoListado> lista = new ArrayList<>();
		
		
		//FIN VARIABLES
		
		//Recupero los datos que estan en la tabla AUXANDEUDASOC
		lista = cDeudaSocio.emitirListadoSaldos();
		
				
		/*
		 * GENERO EL PDF
		 */
				
		Document documento = new Document(PageSize.A4, 36, 36, 90, 36);

		try {
			res.setContentType("application/pdf");
			OutputStream out = res.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(documento, out);

			// Add header and footer
			HeaderFooterPageEvent event = new HeaderFooterPageEvent(user.getNomUs());
			writer.setPageEvent(event);

			// Seteo las fuentes
			Font font1 = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLUE); // Fuentes de los titulos del reporte.
			Font font2 = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLUE); // Fuente de los Subtitulos.
			Font font3 = new Font(FontFamily.HELVETICA, 8, Font.NORMAL); //Fuente del texto

			//DETALLO TITULO GENERAL DEL REPORTE
			Paragraph tituloGral = new Paragraph(new Phrase("Listado de Analisis de Saldos de Socios al "+sdFormat.format(fecha),font1));
			tituloGral.setAlignment(Element.ALIGN_CENTER);
			tituloGral.add(Chunk.NEWLINE);
			tituloGral.add(Chunk.NEWLINE);

			
			//DEFINO LA TABLA QUE VOY A USAR PARA MOSTRAR LOS DATOS
			PdfPTable tabla = new PdfPTable(7);
			tabla.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			// Defino las celdas en las que voy a mostrar los datos en la tabla
			PdfPCell blanco = new PdfPCell(new Paragraph(" ")); // Es un blanco para crear espacio completo y asi dividir las opciones
			blanco.setBorder(Rectangle.NO_BORDER);
			blanco.setColspan(7);
			
			//Voy a ir recorriendo lista para imprimir los valores en el pdf
			//Seteo las banderas y el titulo para mostrar
			bEmpleado = lista.get(posicion).getCodcli();
			bConvenio = lista.get(posicion).getCcond();
			titEmpleado = lista.get(posicion).getCodcli()+" / "+lista.get(posicion).getCpccp()+" / "+lista.get(posicion).getDnrp()+" / "+lista.get(posicion).getNomcli();
			titConvenio = lista.get(posicion).getCcond()+"-"+lista.get(posicion).getDesccond();
			
			//Incializo el contador al tamaño del array LISTA
			contador = lista.size();
			
			//Uso un while para ejecutar el recorrido
			while(contador > 0) {
				
				//Uso una variable AUXILIAR para ver que no se me repita el titulo siempre
				if(aux == false) {
					Paragraph tituloEmpleado = new Paragraph();
					tituloEmpleado.add(new Phrase("Empleado: ",font2));
					tituloEmpleado.add(new Phrase(titEmpleado,font3));
					//Creo la celda
					PdfPCell cTEmpleado = new PdfPCell(tituloEmpleado);
					cTEmpleado.setColspan(7);
					cTEmpleado.setBorder(Rectangle.NO_BORDER);
					//lo asigno a la tabla
					tabla.addCell(cTEmpleado);
					
					//Muestro el titulo del convenio
					Paragraph tituloConvenio = new Paragraph();
					tituloConvenio.add(new Phrase("Convenio: ",font2));
					tituloConvenio.add(new Phrase(titConvenio,font3));
					//Creo la celda 
					PdfPCell cTConvenio = new PdfPCell(tituloConvenio);
					cTConvenio.setColspan(7);
					cTConvenio.setBorder(Rectangle.NO_BORDER);
					//lo asigno a la tabla
					tabla.addCell(cTConvenio);
					tabla.addCell(blanco);
				}
				
				//Valido que el empleado actual sea el mismo que estoy leyendo
				if(bEmpleado.contentEquals(lista.get(posicion).getCodcli())) {
					
					//Valido que el convenio actual sea el mismo que estoy leyendo 
					if(bConvenio.equals(lista.get(posicion).getCcond())) {
						//Es TRUE hago las sumatorias en convenio, en empleado y en total y despues muestro los datos de la fila
						tConvenio += lista.get(posicion).getImporte();
						tEmpleado += lista.get(posicion).getImporte();
						tAnalisis += lista.get(posicion).getImporte();
						if(aux == false) {
							//muestro los titulos generales y los datos del renglon 
							PdfPCell t1 = new PdfPCell(new Phrase("Fecha Comp:",font2));
							t1.setBorderColorBottom(BaseColor.BLUE);
							t1.setBorder(Rectangle.BOTTOM);
							PdfPCell t2 = new PdfPCell(new Phrase("Tpo Comp:",font2));
							t2.setBorderColorBottom(BaseColor.BLUE);
							t2.setBorder(Rectangle.BOTTOM);
							PdfPCell t3 = new PdfPCell(new Phrase("Nro Comp:",font2));
							t3.setBorderColorBottom(BaseColor.BLUE);
							t3.setBorder(Rectangle.BOTTOM);
							PdfPCell t4 = new PdfPCell(new Phrase("Ref:",font2));
							t4.setBorderColorBottom(BaseColor.BLUE);
							t4.setBorder(Rectangle.BOTTOM);
							PdfPCell t5 = new PdfPCell(new Phrase("Saldo:",font2));
							t5.setBorderColorBottom(BaseColor.BLUE);
							t5.setBorder(Rectangle.BOTTOM);
							PdfPCell t6 = new PdfPCell(new Phrase("Obs:",font2));
							t6.setBorderColorBottom(BaseColor.BLUE);
							t6.setBorder(Rectangle.BOTTOM);
							PdfPCell t7 = new PdfPCell(new Phrase("Importe:",font2));
							t7.setBorderColorBottom(BaseColor.BLUE);
							t7.setBorder(Rectangle.BOTTOM);
							tabla.addCell(t1);
							tabla.addCell(t2);
							tabla.addCell(t3);
							tabla.addCell(t4);
							tabla.addCell(t5);
							tabla.addCell(t6);
							tabla.addCell(t7);
						}
						
						//Muestro el renglon
						PdfPCell c1 = new PdfPCell(new Phrase(sdFormat.format(lista.get(posicion).getFcomp()),font3));
						c1.setBorder(Rectangle.NO_BORDER);
						PdfPCell c2 = new PdfPCell(new Phrase(lista.get(posicion).getTcomp(),font3));
						c2.setBorder(Rectangle.NO_BORDER);
						PdfPCell c3 = new PdfPCell(new Phrase(lista.get(posicion).getNcomp(),font3));
						c3.setBorder(Rectangle.NO_BORDER);
						PdfPCell c4 = new PdfPCell(new Phrase(noNull(lista.get(posicion).getRef()),font3));
						c4.setBorder(Rectangle.NO_BORDER);
						PdfPCell c5 = new PdfPCell(new Phrase(noNull(lista.get(posicion).getSaldo()),font3));
						c5.setBorder(Rectangle.NO_BORDER);
						PdfPCell c6 = new PdfPCell(new Phrase(noNull(lista.get(posicion).getObsmov()),font3));
						c6.setBorder(Rectangle.NO_BORDER);
						PdfPCell c7 = new PdfPCell(new Phrase("$"+dFormat.format(lista.get(posicion).getImporte()),font3));
						c7.setBorder(Rectangle.NO_BORDER);
						tabla.addCell(c1);
						tabla.addCell(c2);
						tabla.addCell(c3);
						tabla.addCell(c4);
						tabla.addCell(c5);
						tabla.addCell(c6);
						tabla.addCell(c7);
						
						aux = true; 
					}
					else {
						//Si convenio da FALSE, es porque el empleado tiene mas de un convenio, con lo cual imprimo los totales
						//Y re inicio los valores pertinentes
						PdfPCell tTConvenio = new PdfPCell(new Phrase("Total Convenio "+bConvenio,font2));
						tTConvenio.setColspan(6);
						tTConvenio.setBorder(Rectangle.BOX);
						tTConvenio.setBorderColor(BaseColor.BLUE);
						PdfPCell sTConvenio = new PdfPCell(new Phrase("$"+dFormat.format(tConvenio),font3));
						sTConvenio.setBorder(Rectangle.BOX);
						sTConvenio.setBorderColor(BaseColor.BLUE);
						tabla.addCell(tTConvenio);
						tabla.addCell(sTConvenio);
						tabla.addCell(blanco);
						
						aux = false; 
						bEmpleado = lista.get(posicion).getCodcli();
						bConvenio = lista.get(posicion).getCcond();
						titEmpleado = lista.get(posicion).getCodcli()+" / "+lista.get(posicion).getCpccp()+" / "+lista.get(posicion).getDnrp()+" / "+lista.get(posicion).getNomcli();
						titConvenio = lista.get(posicion).getCcond()+"-"+lista.get(posicion).getDesccond();
						tConvenio = 0; 
						posicion--;
						contador++;
					}
				}
				else {
					//Si Empleado da FALSE, es porque el empleado cambio, con lo cual imprimo los totales
					//Y re inicio los valores pertinentes
					PdfPCell tTConvenio = new PdfPCell(new Phrase("Total Convenio: "+bConvenio,font2));
					tTConvenio.setColspan(6);
					tTConvenio.setBorder(Rectangle.BOX);
					tTConvenio.setBorderColor(BaseColor.BLUE);
					PdfPCell sTConvenio = new PdfPCell(new Phrase("$"+dFormat.format(tConvenio),font3));
					sTConvenio.setBorder(Rectangle.BOX);
					sTConvenio.setBorderColor(BaseColor.BLUE);
					tabla.addCell(tTConvenio);
					tabla.addCell(sTConvenio);
					tabla.addCell(blanco);
					
					//Imprimo el total del empleado
					PdfPCell tTEmpleado = new PdfPCell(new Phrase("Total Empleado: "+bConvenio,font2));
					tTEmpleado.setColspan(6);
					tTEmpleado.setBorder(Rectangle.BOX);
					tTEmpleado.setBorderColor(BaseColor.BLUE);
					PdfPCell sTEmpleado = new PdfPCell(new Phrase("$"+dFormat.format(tEmpleado),font3));
					sTEmpleado.setBorder(Rectangle.BOX);
					sTEmpleado.setBorderColor(BaseColor.BLUE);
					tabla.addCell(tTEmpleado);
					tabla.addCell(sTEmpleado);
					tabla.addCell(blanco);
					
					aux = false; 
					bEmpleado = lista.get(posicion).getCodcli();
					bConvenio = lista.get(posicion).getCcond();
					titEmpleado = lista.get(posicion).getCodcli()+" / "+lista.get(posicion).getCpccp()+" / "+lista.get(posicion).getDnrp()+" / "+lista.get(posicion).getNomcli();
					titConvenio = lista.get(posicion).getCcond()+"-"+lista.get(posicion).getDesccond();
					tEmpleado = 0;
					tConvenio = 0; 
					posicion--;
					contador++; 
				}
				
				
				
				posicion++; 
				contador--; 
			}
			
			//Imprimo los ultimos totales
			PdfPCell tTConvenio = new PdfPCell(new Phrase("Total Convenio: "+bConvenio,font2));
			tTConvenio.setColspan(6);
			tTConvenio.setBorder(Rectangle.BOX);
			tTConvenio.setBorderColor(BaseColor.BLUE);
			PdfPCell sTConvenio = new PdfPCell(new Phrase("$"+dFormat.format(tConvenio),font3));
			sTConvenio.setBorder(Rectangle.BOX);
			sTConvenio.setBorderColor(BaseColor.BLUE);
			tabla.addCell(tTConvenio);
			tabla.addCell(sTConvenio);
			
			//Imprimo el total del empleado
			PdfPCell tTEmpleado = new PdfPCell(new Phrase("Total Empleado: "+bConvenio,font2));
			tTEmpleado.setColspan(6);
			tTEmpleado.setBorder(Rectangle.BOX);
			tTEmpleado.setBorderColor(BaseColor.BLUE);
			PdfPCell sTEmpleado = new PdfPCell(new Phrase("$"+dFormat.format(tEmpleado),font3));
			sTEmpleado.setBorder(Rectangle.BOX);
			sTEmpleado.setBorderColor(BaseColor.BLUE);
			tabla.addCell(tTEmpleado);
			tabla.addCell(sTEmpleado);
			tabla.addCell(blanco);
			tabla.addCell(blanco);
			
			//Imprimo el total del Analisis
			PdfPCell tTAnalisis = new PdfPCell(new Phrase("Total Analisis: ",font2));
			tTAnalisis.setColspan(6);
			tTAnalisis.setBorder(Rectangle.BOX);
			tTAnalisis.setBorderColor(BaseColor.BLUE);
			PdfPCell sTAnalisis = new PdfPCell(new Phrase("$"+dFormat.format(tAnalisis),font3));
			sTAnalisis.setBorder(Rectangle.BOX);
			sTAnalisis.setBorderColor(BaseColor.BLUE);
			tabla.addCell(tTAnalisis);
			tabla.addCell(sTAnalisis);

			// Write to document
			documento.open();
			documento.add(tituloGral);
			documento.add(tabla);
			documento.close();

		} catch (DocumentException | IOException e) { e.printStackTrace(); }
				
			/*
			* FIN GENERO PDF
			 */
		
		
		
		//Finalizo las variables y devuelvo
		
		return r; 
	}
	
	
	
	/*
	 * 	METODOS SECUNDARIOS
	 */

	
	
	private String noNull(String texto) throws ApplicationException {
		if(texto == null) { texto = "-"; }
		return texto;
	}
}
