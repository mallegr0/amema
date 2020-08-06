package servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import controladores.CtrlCliente;
import entidades.Cliente;
import reportes.HeaderFooterPageEvent;
import util.ApplicationException;
import entidades.Usuario;


/**
 * Servlet implementation class Socio
 */
@WebServlet(urlPatterns = {"/Socio"})
public class Socio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String urlSocio = "/amema/views/socios.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Socio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioActivo");
		String idSocio = request.getParameter("idSocio");
		CtrlCliente cc = new CtrlCliente();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		Document documento = new Document(PageSize.A4, 36, 36, 90, 36);		
		
		try {
			response.setContentType("application/pdf");
			OutputStream out = response.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(documento, out);
			
			// Add header and footer
			HeaderFooterPageEvent event = new HeaderFooterPageEvent(usuario.getNomUs());
			writer.setPageEvent(event);
			
			
			//Recupero la data del Socio
			Cliente c = cc.consultaCliente(idSocio);
			
			
			//Seteo las fuentes
			Font f1 = new Font(FontFamily.HELVETICA,13,Font.BOLDITALIC, BaseColor.DARK_GRAY);
			Font f2 = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLUE); // Fuentes de los titulos del reporte.
			Font f3 = new Font(FontFamily.HELVETICA, 10, Font.NORMAL); //Fuente de los textos.
			
			/*
			 * DEFINO LOS PARAMETROS DE LOS DATOS PERSONALES
			 */
			
			Paragraph t1 = new Paragraph(new Phrase("-- Datos Personales -- ", f1));
			t1.setAlignment(Element.ALIGN_CENTER);
			t1.add(Chunk.NEWLINE);
			t1.add(Chunk.NEWLINE);
			
			Paragraph p1 = new Paragraph();
			p1.add(new Phrase("Nro Socio: ",f2));
			p1.add(new Phrase(c.getCODCLI(),f3));
			Paragraph p2 = new Paragraph();
			p2.add(new Phrase("Nombre y Apellido: ",f2));
			p2.add(new Phrase(c.getNOMCLI(),f3));
			Paragraph p3 = new Paragraph();
			p3.add(new Phrase("Tpo Doc: ",f2));
			p3.add(new Phrase(c.getTIPO_DOC(),f3));
			Paragraph p4 = new Paragraph();
			p4.add(new Phrase("Nro Doc: ",f2));
			p4.add(new Phrase(c.getCUITCLI(),f3));
			Paragraph p5 = new Paragraph();
			p5.add(new Phrase("Fec. Nac: ",f2));
			p5.add(new Phrase(format.format(c.getFECHA_NAC()),f3));
			Paragraph p6 = new Paragraph();
			p6.add(new Phrase("Domicilio: ",f2));
			p6.add(new Phrase(c.getDOMCLI(),f3));
			Paragraph p7 = new Paragraph();
			p7.add(new Phrase("Ciudad : ",f2));
			p7.add(new Phrase(c.getCODPOS()+" - "+c.getLOCCLI(),f3));
			Paragraph p8 = new Paragraph();
			p8.add(new Phrase("Telefono/s: ",f2));
			p8.add(new Phrase(validaNull(c.getTELCLI_1())+" - "+validaNull(c.getTELCLI_2()),f3));
			Paragraph p9 = new Paragraph();
			p9.add(new Phrase("E-Mail: ",f2));
			p9.add(new Phrase(validaNull(c.getE_MAIL()),f3));

			// Defino la tabla
			PdfPTable dp = new PdfPTable(4);
			dp.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
			
			// Defino las celdas
			PdfPCell blanco = new PdfPCell(new Paragraph(" ")); // Es un blanco para crear espacio completo
			blanco.setBorder(Rectangle.NO_BORDER);
			blanco.setColspan(4);
			
			PdfPCell dpc1 = new PdfPCell(p1);  // Nro socio
			dpc1.setBorder(Rectangle.NO_BORDER);
			PdfPCell dpc2 = new PdfPCell(p2);	// Nombre y apellido
			dpc2.setBorder(Rectangle.NO_BORDER); 
			dpc2.setColspan(3); 
			PdfPCell dpc3 = new PdfPCell(p3); //Tpo doc
			dpc3.setBorder(Rectangle.NO_BORDER);
			PdfPCell dpc4 = new PdfPCell(p4); // Nro Doc
			dpc4.setBorder(Rectangle.NO_BORDER);
			PdfPCell dpc5 = new PdfPCell(p5); // Fec Nac
			dpc5.setBorder(Rectangle.NO_BORDER);
			dpc5.setColspan(2);
			PdfPCell dpc6 = new PdfPCell(p6); // Domicilio
			dpc6.setBorder(Rectangle.NO_BORDER);
			dpc6.setColspan(2);
			PdfPCell dpc7 = new PdfPCell(p7); // Cod postal + ciudad
			dpc7.setBorder(Rectangle.NO_BORDER);
			dpc7.setColspan(2);
			PdfPCell dpc8 = new PdfPCell(p8); //Telefonos
			dpc8.setBorder(Rectangle.NO_BORDER);
			dpc8.setColspan(2); 
			PdfPCell dpc9 = new PdfPCell(p9); //Email
			dpc9.setBorder(Rectangle.NO_BORDER);
			dpc9.setColspan(2);
			
			// Asigno las celdas a la tabla		
			dp.addCell(dpc1);
			dp.addCell(dpc2);
			dp.addCell(blanco);
			dp.addCell(dpc3);
			dp.addCell(dpc4);
			dp.addCell(dpc5);
			dp.addCell(blanco);
			dp.addCell(dpc6);
			dp.addCell(dpc7);
			dp.addCell(blanco);
			dp.addCell(dpc8);
			dp.addCell(dpc9);
			dp.addCell(blanco);
			
			
			/*
			 * DEFINO LOS PARAMETROS DE LOS DATOS LABORALES
			 */
			
			Paragraph t2 = new Paragraph();
			t2.add(new Phrase("-- Datos Laborales -- ", f1));
			t2.setAlignment(Element.ALIGN_CENTER);
			t2.add(Chunk.NEWLINE);
			t2.add(Chunk.NEWLINE);
			
			Paragraph l1 = new Paragraph();
			l1.add(new Phrase("Empresa: ",f2));
			l1.add(new Phrase(c.getCPCCP(),f3));
			Paragraph l2 = new Paragraph();
			l2.add(new Phrase("Centro de Costo: ",f2));
			l2.add(new Phrase(c.getCONTACTO(),f3));
			Paragraph l3 = new Paragraph();
			l3.add(new Phrase("Desc. CC: ",f2));
			l3.add(new Phrase(c.getCONTACTO2(),f3));
			Paragraph l4 = new Paragraph();
			l4.add(new Phrase("Legajo: ",f2));
			l4.add(new Phrase(c.getDNRP(),f3));
			Paragraph l5 = new Paragraph();
			l5.add(new Phrase("Estado: ",f2));
			l5.add(new Phrase(c.getCOM_IND(),f3));
			Paragraph l6 = new Paragraph();
			l6.add(new Phrase("Fec. Ingreso: ",f2));
			l6.add(new Phrase(format.format(c.getFECHA_ING()),f3));
			

			// Defino la tabla
			PdfPTable dl = new PdfPTable(3);
			dl.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
			
			// Defino las celdas

			PdfPCell dlc1 = new PdfPCell(l1);  // Empresa
			dlc1.setBorder(Rectangle.NO_BORDER);
			PdfPCell dlc2 = new PdfPCell(l2);	// CC
			dlc2.setBorder(Rectangle.NO_BORDER); 
			PdfPCell dlc3 = new PdfPCell(l3); // Desc CC
			dlc3.setBorder(Rectangle.NO_BORDER);
			PdfPCell dlc4 = new PdfPCell(l4); // Legajo
			dlc4.setBorder(Rectangle.NO_BORDER);
			PdfPCell dlc5 = new PdfPCell(l5); // Estado
			dlc5.setBorder(Rectangle.NO_BORDER);
			PdfPCell dlc6 = new PdfPCell(l6); // Fec. Ing
			dlc6.setBorder(Rectangle.NO_BORDER);
			
			// Asigno las celdas a la tabla		
			dl.addCell(dlc1);
			dl.addCell(dlc2);
			dl.addCell(dlc3);
			dl.addCell(blanco);
			dl.addCell(dlc4);
			dl.addCell(dlc5);
			dl.addCell(dlc6);
			dl.addCell(blanco);
			
			
			/*
			 * DEFINOS LOS DATOS DEL CREDITO 
			 */
			
			Paragraph t3 = new Paragraph();
			t3.add(new Phrase("-- Datos Convenios -- ", f1));
			t3.setAlignment(Element.ALIGN_CENTER);
			t3.add(Chunk.NEWLINE);
			t3.add(Chunk.NEWLINE);
			
			Paragraph c1 = new Paragraph();
			c1.add(new Phrase("Convenio: ",f2)); // Convenio
			c1.add(new Phrase(c.getCCOND(),f3));
			Paragraph c2 = new Paragraph();
			c2.add(new Phrase("Observaciones: ",f2)); // Observaciones
			c2.add(new Phrase(validaNull(c.getOBSCLI()),f3));
			
			

			// Defino la tabla
			PdfPTable dc = new PdfPTable(3);
			dc.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
			
			// Defino las celdas
			
			PdfPCell dcc1 = new PdfPCell(c1);  // Convenio
			dcc1.setBorder(Rectangle.NO_BORDER);
			PdfPCell dcc2 = new PdfPCell(c2);	// Observaciones
			dcc2.setBorder(Rectangle.NO_BORDER); 
			dcc2.setColspan(2);
		
			// Asigno las celdas a la tabla		
			dc.addCell(dcc1);
			dc.addCell(dcc2);
			
			
			// Write to document
			documento.open();
			documento.add(t1);
			documento.add(dp);
			documento.add(t2);
			documento.add(dl);
			documento.add(t3);
			documento.add(dc);
			documento.close();
			
			
		} 
		catch (FileNotFoundException | DocumentException | ApplicationException e) {e.printStackTrace();  } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("evento_alta") != null){
			String msj = "";
			try {
				msj = altaSocio(request.getParameter("nrosocio"), request.getParameter("nombre"), request.getParameter("domicilio"),
						request.getParameter("codpos"), request.getParameter("telefono1"), request.getParameter("telefono2"), 
						request.getParameter("convenio"), request.getParameter("nrodoc"), request.getParameter("legajo"),
						request.getParameter("estado"), request.getParameter("cc"), request.getParameter("descc"),
						request.getParameter("mail"), request.getParameter("tpodoc"), request.getParameter("fecnac"),
						request.getParameter("fecing"), request.getParameter("empresa"), request.getParameter("obs"));
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("msj", msj);
			response.sendRedirect(urlSocio);
		}
		
		if(request.getParameter("evento_modificar") != null) { doPut(request, response); }
		
		if(request.getParameter("evento_eliminar") != null){ doDelete(request, response); }
		
		if(request.getParameter("evento_buscar") != null) {
			buscar(request, response); }
	}


	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msj = null;
		try {
			msj = modificaSocio(request.getParameter("nrosocio"), request.getParameter("nombre"), request.getParameter("domicilio"),
					request.getParameter("codpos"), request.getParameter("telefono1"), request.getParameter("telefono2"), 
					request.getParameter("convenio"), request.getParameter("nrodoc"), request.getParameter("legajo"),
					request.getParameter("estado"), request.getParameter("cc"), request.getParameter("descc"),
					request.getParameter("mail"), request.getParameter("tpodoc"), request.getParameter("fecnac"),
					request.getParameter("fecing"), request.getParameter("empresa"), request.getParameter("obs"));
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("msj", msj);
		response.sendRedirect(urlSocio);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlCliente cc = new CtrlCliente();
		String msj = null;
		
		try {
			if(cc.bajaCliente(request.getParameter("socio")) == true) {
				msj = "siBaja";
			}
			else { msj = "noBaja"; }
		} catch (ApplicationException e) { e.printStackTrace(); }
		
		request.getSession().setAttribute("msj", msj);
		response.sendRedirect(urlSocio);

	}
	
	
	private String altaSocio(String nrosocio, String nombre, String domicilio, String codpos, String telefono1, String telefono2,
			String convenio, String nrodoc, String legajo, String estado, String cc, String descc, String mail, String tpodoc, 
			String fecnac, String fecing, String empresa, String obs) throws ApplicationException {
		
		CtrlCliente ctc = new  CtrlCliente();
		Cliente c = new Cliente();
		String msj = "";	
		
		c.setMARCA("N");
		c.setCODCLI(nrosocio);
		c.setNOMCLI(nombre.toUpperCase());
		c.setDOMCLI(domicilio.toUpperCase());
		c.setCODPOS(Integer.parseInt(codpos));
		c.setLOCCLI(ctc.localidad(Integer.parseInt(codpos)));
		c.setTELCLI_1(telefono1);
		c.setTELCLI_2(telefono2);
		c.setFAX(null);
		c.setCVTO("01");
		c.setCCOND(convenio);
		c.setZONCLI("01");
		c.setNVIAJ("99");
		c.setPROVCLI("S");
		c.setCUITCLI(nrodoc);
		c.setIVACLI(null);
		c.setREGCLI("3");
		c.setPRETEN(0.0);
		c.setDNRP(legajo);
		c.setSALCLI_1(0.0);
		c.setSALCLID_1(0.0);
		c.setFSALCLI_1(null);
		c.setSALCLI_2(0.0);
		c.setSALCLID_2(0.0);
		c.setFSALCLI_2(null);
		c.setA_CTA_1(0.0);
		c.setA_CTA_2(0.0);
		c.setA_CTAD_1(0.0);
		c.setA_CTAD_2(0.0);
		c.setCTRANSP("000");
		c.setCOM_IND(estado);
		c.setCREDITO("N");
		c.setCRED_MAX(0.0);
		c.setCONTACTO(cc.toUpperCase());
		c.setCONTACTO2(descc.toUpperCase());
		c.setLISTAPRE(1);
		c.setE_MAIL(mail.toUpperCase());
		c.setMAKITA("N");
		c.setCOMISION("N");
		c.setCOMI_DIFE("N");
		c.setTIPO_DOC(tpodoc);
		c.setFECHA_NAC(fecha(fecnac));
		c.setFECHA_ING(fecha(fecing));
		c.setCPCCP(empresa);
		c.setOBSCLI(obs.toUpperCase());
		
		try {
			if(ctc.altaCliente(c) == true) { msj = "siAlta"; }
			else { msj = "noAlta"; }
		}
		catch(Exception e) { e.printStackTrace();}
		
		return msj;
		
	}
	
	private String modificaSocio(String nrosocio, String nombre, String domicilio, String codpos, String telefono1, String telefono2,
			String convenio, String nrodoc, String legajo, String estado, String cc, String descc, String mail, String tpodoc, 
			String fecnac, String fecing, String empresa, String obs) throws ApplicationException {
		
		CtrlCliente ctc = new  CtrlCliente();
		Cliente c = new Cliente();
		String msj = "";	
		
		c.setMARCA("N");
		c.setCODCLI(nrosocio);
		c.setNOMCLI(nombre.toUpperCase());
		c.setDOMCLI(domicilio.toUpperCase());
		c.setCODPOS(Integer.parseInt(codpos));
		c.setLOCCLI(ctc.localidad(Integer.parseInt(codpos)));
		c.setTELCLI_1(telefono1);
		c.setTELCLI_2(telefono2);
		c.setFAX(null);
		c.setCVTO("01");
		c.setCCOND(convenio);
		c.setZONCLI("01");
		c.setNVIAJ("99");
		c.setPROVCLI("S");
		c.setCUITCLI(nrodoc);
		c.setIVACLI(null);
		c.setREGCLI("3");
		c.setPRETEN(0.0);
		c.setDNRP(legajo);
		c.setSALCLI_1(0.0);
		c.setSALCLID_1(0.0);
		c.setFSALCLI_1(null);
		c.setSALCLI_2(0.0);
		c.setSALCLID_2(0.0);
		c.setFSALCLI_2(null);
		c.setA_CTA_1(0.0);
		c.setA_CTA_2(0.0);
		c.setA_CTAD_1(0.0);
		c.setA_CTAD_2(0.0);
		c.setCTRANSP("000");
		c.setCOM_IND(estado);
		c.setCREDITO("N");
		c.setCRED_MAX(0.0);
		c.setCONTACTO(cc.toUpperCase());
		c.setCONTACTO2(descc.toUpperCase());
		c.setLISTAPRE(1);
		c.setE_MAIL(mail.toUpperCase());
		c.setMAKITA("N");
		c.setCOMISION("N");
		c.setCOMI_DIFE("N");
		c.setTIPO_DOC(tpodoc);
		c.setFECHA_NAC(fecha(fecnac));
		c.setFECHA_ING(fecha(fecing));
		c.setCPCCP(empresa);
		c.setOBSCLI(obs.toUpperCase());
		
		try {
			if(ctc.modificaCliente(c) == true) { msj = "siModifica"; }
			else { msj = "noModifica"; }
		}
		catch(Exception e) { e.printStackTrace();}
		
		return msj;
		
	}
	
	private Date fecha (String fec) {
		Date data = Date.valueOf(fec);
		return data; 
	}
	
	private String validaNull(String dato) {
		String r = "";
		
		if(dato != null) {
			if(dato != "0") {
				if(!dato.isEmpty()) {
					r = dato;
				}
			}
		}
		else {
			r = "S/D";
		}
		return r;
	}
	
	private void buscar(HttpServletRequest req, HttpServletResponse res) {
		CtrlCliente cc = new CtrlCliente();
		Cliente c = null;
		ArrayList<Cliente> lista = new ArrayList<>();
		
		try {
			if(req.getParameter("todos") != null) {
				lista = cc.listarCliente();
				req.getSession().setAttribute("todos", lista);
			}
			if(req.getParameter("nombre") != null) {
				lista = cc.listarClientePorNombre(req.getParameter("dato").toUpperCase());
				req.getSession().setAttribute("nombre", lista);
			}
			if(req.getParameter("doc") != null) {
				c = cc.consultaClientePorDNI(req.getParameter("dato"));
				req.getSession().setAttribute("doc", c);
			}
			res.sendRedirect(urlSocio);
		}
		catch (ApplicationException | IOException e) { e.printStackTrace();}
	}
}
