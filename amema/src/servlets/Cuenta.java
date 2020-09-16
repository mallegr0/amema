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

import controladores.CtrlCliente;
import controladores.CtrlConvenio;
import controladores.CtrlCtactecliente;
import controladores.CtrlFactRec;
import controladores.CtrlGaranteMovFijo;
import controladores.CtrlTpoComprobante;
import controladores.CtrlVentasM;
import entidades.Cliente;
import entidades.CtacteGral;
import entidades.Ctactecliente;
import entidades.GaranteMovFijo;
import entidades.TpoComprobante;
import entidades.Usuario;
import entidades.VentasM;
import reportes.HeaderFooterPageEvent;
import util.ApplicationException;

/**
 * Servlet implementation class Cuentas
 */
@WebServlet(urlPatterns = {"/Cuenta"})
public class Cuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String urlCtacte = "/amema/views/ctactes.jsp";
	private static String urlBCtacte = "/amema/views/buscactactes.jsp";
	private static String urlDCtacte = "/amema/views/detalleCuenta.jsp";
	private static String urlGCtacte = "/amema/views/detalleCuentaGarante.jsp";
	private CtrlCliente cCliente;
	private CtrlConvenio cConvenio;
	private CtrlCtactecliente cCuentas;
	private CtrlTpoComprobante cTComprobante;
	private CtrlVentasM cVentasM;
	private CtrlFactRec	CFactura;
	private CtrlGaranteMovFijo gmf;

	/*
	 * ***************************************************************************
	 * METODOS DE HTTP QUE CONECTA CON EL JSP
	 * ***************************************************************************
	 * */
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cuenta() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("evento_imprimirPDF") != null) {
			try { imprimirPDF(request, response); } 
			catch (ApplicationException e) { e.printStackTrace(); }
		}
		if(request.getParameter("evento_todoPDF") != null) {
			try {
				imprimirExcel(request, response);
			} catch (ApplicationException | ParseException e) { e.printStackTrace(); } 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("evento_buscar1") != null) {
			if(request.getParameter("socio") != null) {
				try { listarSocio(request, response); }
				catch (ApplicationException e) { e.printStackTrace(); }
			}
			if(request.getParameter("doc") != null) {
				try { buscaDocumento(request, response); }
				catch (ApplicationException e) { e.printStackTrace(); }
			}
		}
		if(request.getParameter("evento_buscar2") != null) {
			try { consultaSocio(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		if(request.getParameter("evento_buscar3") != null) {
			try { buscarSocio(request, response);}
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		if(request.getParameter("evento_detalle") != null) {
			try { completaTabla(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		if(request.getParameter("evento_garante") != null) {
			try { completaGaranteCuenta(request, response); }
			catch(ApplicationException e) { e.printStackTrace();}
		}
	}
	
	
	
	/*
	 * ***************************************************************************
	 * METODOS PRIMARIOS QUE USAN LOS METODOS HTTP
	 * Estos metodos son invocados por el POST, GET, DELETE y PUT para hacer mas legible esa área de código
	 * ***************************************************************************
	 * */
	
	private void listarSocio(HttpServletRequest req, HttpServletResponse res) throws ApplicationException {
		try { 
			req.getSession().setAttribute("lista", listarClientePorNombre(req.getParameter("dato")));
			res.sendRedirect(urlBCtacte); 
			} 
		catch (IOException e) { e.printStackTrace(); }
	}
	
	private void buscaDocumento(HttpServletRequest req, HttpServletResponse res) throws ApplicationException {
		try { 
			req.getSession().setAttribute("socio", consultaClientePorDNI(req.getParameter("dato")));
			res.sendRedirect(urlBCtacte); } 
		catch (IOException e) { e.printStackTrace(); }
	}
	
	private void consultaSocio(HttpServletRequest req, HttpServletResponse res) throws ApplicationException {
		try { 
			req.getSession().setAttribute("socio", consultaCliente(req.getParameter("socio")));
			res.sendRedirect(urlBCtacte); } 
		catch (IOException e) { e.printStackTrace(); }
	}
	
	private void buscarSocio(HttpServletRequest req, HttpServletResponse res) throws ApplicationException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");	
		String socio = req.getParameter("socio");
		double saldo = 0.0;
		Cliente c = new Cliente();
		c = consultaCliente(socio.substring(0,4));
		
		req.getSession().setAttribute("persona", c);
		
		try {
			Date fecha = format.parse(req.getParameter("fecha"));
			ArrayList<Ctactecliente> lmov = new ArrayList<>();
			ArrayList<CtacteGral> mov = new ArrayList<>();
			
			cCuentas = new CtrlCtactecliente();
			cTComprobante = new CtrlTpoComprobante();

			lmov = cCuentas.listarCtaCtePorSocioYFecha(c.getCODCLI(), fecha);
			
			for(Ctactecliente cta : lmov) {
				CtacteGral r = new CtacteGral();
				TpoComprobante tc = cTComprobante.consultaTComprobante(cta.getTCOMP());
				r.setCODCLI(cta.getCODCLI());
				r.setFMOV(format.format(cta.getFMOV()));
				r.setTMOV(tc.getDESCTIPO());
				r.setNCOMP(cta.getNCOMP());
				r.setHABER(cta.getHABER());
				r.setDEBE(cta.getDEBE());
				saldo = saldo +(cta.getHABER() - cta.getDEBE());
				r.setSALDO(-1*saldo);
				mov.add(r);
				r = null;
			}
			req.getSession().setAttribute("movimientos", mov);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/*PONGO EN NULL TODOS LOS VALORES*/
		c = null; 
		format = null;
		cCuentas = null;
		cTComprobante = null;

		try { res.sendRedirect(urlCtacte); } 
		catch (IOException e) { e.printStackTrace(); }
	}
		
	private void completaTabla(HttpServletRequest req, HttpServletResponse res) throws ApplicationException {
		
		try { 
			String dato = req.getParameter("dato");
			String nro;
			if(dato.length() == 8) {
				CFactura = new  CtrlFactRec();
				nro = CFactura.consultaNroComprobante(dato);
			}
			else {
				nro = dato.substring(4);
			}
			req.getSession().setAttribute("movimiento", consultaVentasM(nro));
			res.sendRedirect(urlDCtacte); } 
		catch (IOException e) { e.printStackTrace(); }
	}
	
	private void imprimirPDF(HttpServletRequest req, HttpServletResponse res) throws ApplicationException {
		String dato = req.getParameter("printadherente");
		String comprobante;
		if(dato.length() == 8) { 
			CFactura = new CtrlFactRec();
			comprobante = CFactura.consultaNroComprobante(dato); 
		}
		else {
			comprobante = req.getParameter("printadherente").substring(4);
		}
		cVentasM = new CtrlVentasM();
		cCliente = new CtrlCliente();
		VentasM detalle = cVentasM.consultaVentasM(comprobante);
		Cliente socio = cCliente.consultaCliente(detalle.getCODCLI());
		generaPDF(limpiarDatos(socio), detalle,req, res);
		cVentasM = null;
		cCliente = null;
		detalle = null;
		socio = null;
		System.gc();
	}
	
	private void imprimirExcel(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, ParseException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = format.parse("2050-01-01");
		Usuario user = (Usuario) req.getSession().getAttribute("usuarioActivo");
		cCuentas = new CtrlCtactecliente();
		cCuentas.migrarExcel(req.getParameter("migrarExcel"), fecha, user.getNomUs());
		System.gc();
		res.sendRedirect(urlCtacte);
	}
	
	private void completaGaranteCuenta(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cVentasM = new CtrlVentasM();
		gmf = new CtrlGaranteMovFijo();
		cCliente = new CtrlCliente();
		VentasM vm = new VentasM();
		ArrayList<Cliente> socios = new ArrayList<>();
		String dato = req.getParameter("datog");
		
		if(dato.length() == 8) { vm = cVentasM.consultaVentasM(dato); }
		else { vm = cVentasM.consultaVentasM(dato.substring(4)); }
		req.getSession().setAttribute("comprobante", vm.getNROMOVPLANIF());
		ArrayList<GaranteMovFijo> movimientos = gmf.listarGarantesPorMovimientos(vm.getNROMOVPLANIF());
		for(GaranteMovFijo g: movimientos) {
			socios.add(limpiarDatos(cCliente.consultaCliente(g.getNroGarante())));
		}
		req.getSession().setAttribute("garantes", socios);
		// Limpio los contoladores
		cVentasM = null; 
		gmf = null; 
		cCliente = null;
		vm = null;
		
		res.sendRedirect(urlGCtacte);
	}

	
	/*
	 * ***************************************************************************
	 * METODOS SECUNDARIOS
	 * Estos metodos son los que hacen más legible a los metodos primarios. 
	 * ***************************************************************************
	 * */
	
	private ArrayList<Cliente> listarClientePorNombre(String nombre) throws ApplicationException {
		cCliente = new CtrlCliente();
		return cCliente.listarClientePorNombre(nombre);
	}
	
	private Cliente consultaClientePorDNI(String documento) throws ApplicationException {
		cCliente = new CtrlCliente();
		return limpiarDatos(cCliente.consultaClientePorDNI(documento));
	}
	
	private Cliente consultaCliente(String socio) throws ApplicationException {
		cCliente = new CtrlCliente();
		return limpiarDatos(cCliente.consultaCliente(socio));
	}
	
	private VentasM consultaVentasM(String cod) throws ApplicationException {
		cVentasM = new CtrlVentasM();
		return cVentasM.consultaVentasM(cod);
	}
	
	
	private Cliente limpiarDatos(Cliente c) throws ApplicationException {
		if(c.getNOMCLI() == null) { c.setNOMCLI("S/Nombre"); }
		if(c.getDOMCLI() == null) { c.setDOMCLI("S/Domicilio"); }
		if(c.getTELCLI_1() == null) { c.setTELCLI_1("S/Nro Tel"); }
		if(c.getTELCLI_2() == null) { c.setTELCLI_2("S/Nro Tel"); }
		if(c.getDNRP() == null) { c.setDNRP("S/Legajo"); }
		if(c.getCONTACTO() == null) { c.setCONTACTO("S/CC"); }
		if(c.getCONTACTO2() == null) { c.setCONTACTO2("S/Desc CC"); }
		if(c.getTIPO_DOC() == null) { c.setTIPO_DOC("S/Tpo Doc"); }
		if(c.getCUITCLI() == null) { c.setCUITCLI("S/Nro Doc"); }
		if(c.getOBSCLI() == null) {c.setOBSCLI("S/Observaciones");}
		
		cConvenio = new CtrlConvenio();
		String conv = c.getCCOND();
		c.setCCOND(conv+" - "+cConvenio.buscaDescripcion(conv));
		
		return c;
	}
	
	private void generaPDF(Cliente socio, VentasM detalle, HttpServletRequest req, HttpServletResponse res) throws ApplicationException {
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioActivo");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("#0.00");
		
		Document documento = new Document(PageSize.A4, 36, 36, 90, 36);		
		
		try {
			res.setContentType("application/pdf");
			OutputStream out = res.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(documento, out);
			
			// Add header and footer
			HeaderFooterPageEvent event = new HeaderFooterPageEvent(usuario.getNomUs());
			writer.setPageEvent(event);
			
			//Seteo las fuentes
			Font f1 = new Font(FontFamily.HELVETICA,13,Font.BOLDITALIC, BaseColor.DARK_GRAY);
			Font f2 = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLUE); // Fuentes de los titulos del reporte.
			Font f3 = new Font(FontFamily.HELVETICA, 10, Font.NORMAL); //Fuente de los textos.
			
			/*
			 * DEFINO LOS PARAMETROS DE LOS DATOS PERSONALES
			 */
			
			Paragraph t1 = new Paragraph(new Phrase("-- Datos Del Socio -- ", f1));
			t1.setAlignment(Element.ALIGN_CENTER);
			t1.add(Chunk.NEWLINE);
			t1.add(Chunk.NEWLINE);
			
			Paragraph p1 = new Paragraph();
			p1.add(new Phrase("Nro Socio: ",f2));
			p1.add(new Phrase(socio.getCODCLI(),f3));
			Paragraph p2 = new Paragraph();
			p2.add(new Phrase("Nombre y Apellido: ",f2));
			p2.add(new Phrase(socio.getNOMCLI(),f3));
			Paragraph p3 = new Paragraph();
			p3.add(new Phrase("Domicilio: ",f2));
			p3.add(new Phrase(socio.getDOMCLI(),f3));
			Paragraph p4 = new Paragraph();
			p4.add(new Phrase("Telefono/s: ",f2));
			p4.add(new Phrase(socio.getTELCLI_1()+" - "+socio.getTELCLI_2(),f3));
			Paragraph p5 = new Paragraph();
			p5.add(new Phrase("Nro Doc: ",f2));
			p5.add(new Phrase(socio.getTIPO_DOC()+" "+socio.getCUITCLI(),f3));
			Paragraph p6 = new Paragraph();
			p6.add(new Phrase("Convenio: ",f2)); // Convenio
			p6.add(new Phrase(socio.getCCOND(),f3));
			Paragraph p7 = new Paragraph();
			p7.add(new Phrase("Empresa: ",f2));
			p7.add(new Phrase(socio.getCPCCP(),f3));
			Paragraph p8 = new Paragraph();
			p8.add(new Phrase("Legajo: ",f2));
			p8.add(new Phrase(socio.getDNRP(),f3));
			Paragraph p9 = new Paragraph();
			p9.add(new Phrase("Lugar de Trabajo: ", f2));
			p9.add(new Phrase(socio.getCONTACTO()+" - "+socio.getCONTACTO2(),f3));
			Paragraph p10 = new Paragraph();
			p10.add(new Phrase("Observaciones: ",f2)); // Observaciones
			p10.add(new Phrase("S/Observaciones" ,f3));
			
			// Defino la tabla
			PdfPTable dp = new PdfPTable(3);
			dp.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
			
			// Defino las celdas
			PdfPCell blanco = new PdfPCell(new Paragraph(" ")); // Es un blanco para crear espacio completo
			blanco.setBorder(Rectangle.NO_BORDER);
			blanco.setColspan(3);
			
			PdfPCell dpc1 = new PdfPCell(p1);  // Nro socio
			dpc1.setBorder(Rectangle.NO_BORDER);
			PdfPCell dpc2 = new PdfPCell(p6);	// Convenio
			dpc2.setBorder(Rectangle.NO_BORDER); 
			dpc2.setColspan(2);
			PdfPCell dpc3 = new PdfPCell(p2); //Apellido y nombre
			dpc3.setBorder(Rectangle.NO_BORDER);
			dpc3.setColspan(2);
			PdfPCell dpc4 = new PdfPCell(p7); // Empresa
			dpc4.setBorder(Rectangle.NO_BORDER);
			PdfPCell dpc5 = new PdfPCell(p3); // Domicilio
			dpc5.setBorder(Rectangle.NO_BORDER);
			dpc5.setColspan(2);
			PdfPCell dpc6 = new PdfPCell(p8);  // Legajo
			dpc6.setBorder(Rectangle.NO_BORDER);
			PdfPCell dpc7 = new PdfPCell(p4);	// Telefonos
			dpc7.setBorder(Rectangle.NO_BORDER); 
			PdfPCell dpc8 = new PdfPCell(p9); // Lugar de Trabajo
			dpc8.setBorder(Rectangle.NO_BORDER);
			dpc8.setColspan(2);
			PdfPCell dpc9 = new PdfPCell(p5); // Documento
			dpc9.setBorder(Rectangle.NO_BORDER);
			PdfPCell dpc10 = new PdfPCell(p10); // Observaciones
			dpc10.setBorder(Rectangle.NO_BORDER);
			
			
			// Asigno las celdas a la tabla		
			dp.addCell(dpc1);
			dp.addCell(dpc2);
			dp.addCell(blanco);
			dp.addCell(dpc3);
			dp.addCell(dpc4);
			dp.addCell(blanco);
			dp.addCell(dpc5);
			dp.addCell(dpc6);
			dp.addCell(blanco);
			dp.addCell(dpc7);
			dp.addCell(dpc8);
			dp.addCell(blanco);
			dp.addCell(dpc9);
			dp.addCell(dpc10);
			dp.addCell(blanco);
			
			
			/* FIN DATOS PERSONALES */
			
			
			/*
			 * DEFINO LA TABLA DE LOS MOVIMIENTOS SEGUN EL PARAMETRO QUE PASO
			 */
			
			//Defino el titulo
				
			Paragraph t4 = new Paragraph();
			t4.add(new Phrase("-- Detalle del comprobante "+detalle.getNROMOVPLANIF()+" --",f1));
			t4.setAlignment(Element.ALIGN_CENTER);
			t4.add(Chunk.NEWLINE);
			t4.add(Chunk.NEWLINE);
				
			Paragraph m1 = new Paragraph();
			m1.add(new Phrase("Origen del Movimiento: ",f2));
			m1.add(new Phrase(detalle.getNCOMP(),f3));
			Paragraph m2 = new Paragraph();
			m2.add(new Phrase("Cant. de Cuotas: ",f2));
			m2.add(new Phrase(detalle.getOBSERV(),f3));
			Paragraph m3 = new Paragraph();
			m3.add(new Phrase("Referencia: ",f2));
			m3.add(new Phrase(detalle.getREFERENCIA(),f3));
			Paragraph m4 = new Paragraph();
			m4.add(new Phrase("Nro de Planificación: ",f2));
			m4.add(new Phrase(df.format(detalle.getNROMOVPLANIF()),f3));
			m4.add(new Phrase(" (Nro. Actualiz: ",f3));
			m4.add(new Phrase(detalle.getNROACTUALIZ()+")",f3));
			Paragraph m5 = new Paragraph();
			m5.add(new Phrase("Fec. Movimiento: ",f2));
			m5.add(new Phrase(format.format(detalle.getFMOV()),f3));
			Paragraph m6 = new Paragraph();
			m6.add(new Phrase("Pagado?: ",f2));
			m6.add(new Phrase(detalle.getPAGADO(),f3));
			Paragraph m7 = new Paragraph();
			m7.add(new Phrase("Anulado?: ",f2));
			m7.add(new Phrase(detalle.getANULADO(),f3));
			Paragraph m8 = new Paragraph();
			m8.add(new Phrase("Observaciones: ",f2));
			m8.add(new Phrase(detalle.getTEXTLIB(),f3));

				
				
			//Defino la tabla
			PdfPTable tmov = new PdfPTable(2);
			tmov.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
				
			// Defino las celdas
			PdfPCell cm1 = new PdfPCell(m1);
			cm1.setBorder(Rectangle.NO_BORDER);
			PdfPCell cm2 = new PdfPCell(m5);
			cm2.setBorder(Rectangle.NO_BORDER);
			PdfPCell cm3 = new PdfPCell(m2);
			cm3.setBorder(Rectangle.NO_BORDER);
			PdfPCell cm4 = new PdfPCell(m6);
			cm4.setBorder(Rectangle.NO_BORDER);
			PdfPCell cm5 = new PdfPCell(m3);
			cm5.setBorder(Rectangle.NO_BORDER);
			PdfPCell cm6 = new PdfPCell(m7);
			cm6.setBorder(Rectangle.NO_BORDER);
			PdfPCell cm7 = new PdfPCell(m4);
			cm7.setBorder(Rectangle.NO_BORDER);
			PdfPCell cm8 = new PdfPCell(m8);
			cm8.setBorder(Rectangle.NO_BORDER);
			
			
			// Asigno la celda a la tabla
			tmov.addCell(m1);
			tmov.addCell(m2);
			tmov.addCell(blanco);
			tmov.addCell(m3);
			tmov.addCell(m4);
			tmov.addCell(blanco);
			tmov.addCell(m5);
			tmov.addCell(m6);
			tmov.addCell(blanco);
			tmov.addCell(m7);
			tmov.addCell(m8);
			tmov.addCell(blanco);
			
			
			/* FIN TABLAS MOVIMIENTOS */
			
			
			// Write to document
			documento.open();
			documento.add(t1);
			documento.add(dp);
			documento.add(blanco);
			documento.add(t4);
			documento.add(tmov);
			documento.close();
			System.gc();
		} 
		catch (DocumentException | IOException e) { e.printStackTrace();}
	}	
}
