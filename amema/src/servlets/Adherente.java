package servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import controladores.CtrlArticulo;
import controladores.CtrlCliente;
import controladores.CtrlConvenio;
import controladores.CtrlGaranteMovFijo;
import controladores.CtrlVenta;
import controladores.CtrlVentasM;
import entidades.AdherentesGral;
import entidades.Articulo;
import entidades.Cliente;
import entidades.GaranteMovFijo;
import entidades.Usuario;
import entidades.Venta;
import entidades.VentasM;
import reportes.HeaderFooterPageEvent;
import util.ApplicationException;

/**
 * Servlet implementation class Adherente
 */
@WebServlet(urlPatterns = { "/Adherente" })
public class Adherente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String urlAdherente = "/amema/views/adherentes.jsp";
	private static String urlBAdherente = "/amema/views/buscaadherentes.jsp";
	private static String urlDAdherente = "/amema/views/detalleAdherente.jsp";
	private CtrlCliente cc = null;
	private CtrlVentasM cvm = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Adherente() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		generarPdf(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("evento_buscar1") != null) {
			if (request.getParameter("socio") != null) {
				try {
					request.getSession().setAttribute("lista", buscarPorNombre(request.getParameter("dato")));
					response.sendRedirect(urlBAdherente);
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
			}
			if (request.getParameter("doc") != null) {
				try {
					request.getSession().setAttribute("socio", buscarPorDocumento(request.getParameter("dato")));
					request.getSession().setAttribute("movimientos",
							listarMovimientos(buscarPorDocumento(request.getParameter("dato"))));
					response.sendRedirect(urlAdherente);
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
			}
		}
		if (request.getParameter("evento_buscar2") != null) {
			try {
				request.getSession().setAttribute("socio", buscarSocio(request.getParameter("socio")));
				request.getSession().setAttribute("movimientos",
						listarMovimientos(buscarSocio(request.getParameter("socio"))));
				response.sendRedirect(urlAdherente);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		}
		if (request.getParameter("evento_detalle") != null) {
			try {
				cvm = new CtrlVentasM();
				int nro = Integer.parseInt(request.getParameter("dato"));
				request.getSession().setAttribute("detalle", cvm.listarVentasMPorNroMov(nro));
				response.sendRedirect(urlDAdherente);
			} catch (NumberFormatException | ApplicationException e) {
				e.printStackTrace();
			}

		}
	}

	private ArrayList<Cliente> buscarPorNombre(String dato) throws ApplicationException {
		cc = new CtrlCliente();
		return cc.listarClientePorNombre(dato);
	}

	private Cliente buscarPorDocumento(String dato) throws ApplicationException {
		cc = new CtrlCliente();
		return limpiarDatos(cc.consultaClientePorDNI(dato));
	}

	private Cliente buscarSocio(String dato) throws ApplicationException {
		cc = new CtrlCliente();
		return limpiarDatos(cc.consultaCliente(dato));
	}

	private ArrayList<AdherentesGral> listarMovimientos(Cliente c) throws ApplicationException {
		// Declaro las variables que voy a usar para devolver los movimientos del socio.

		// Declaro los controladores
		CtrlGaranteMovFijo cgmf = new CtrlGaranteMovFijo();
		CtrlArticulo ca = new CtrlArticulo();
		CtrlVenta cv = new CtrlVenta();
		CtrlCliente cc = new CtrlCliente();

		// Declaros los arrays que voy a ir usando
		ArrayList<Venta> lventas = new ArrayList<>();
		ArrayList<AdherentesGral> lrta = new ArrayList<>();

		// Declaros las entidades que necesito.
		AdherentesGral rta = null;
		Articulo a = null;
		Cliente cli = null;

		// Declaro variables varias para usar
		String cgrupo, csubf, nroart, codart;

		// Recupero los movimientos fijos en el que sale garante un socio.
		ArrayList<GaranteMovFijo> lmovimientos = cgmf.listarMovimientos(c.getCODCLI());

		// Busco las ventas por cada movimiento en el que participo el socio garante
		for (GaranteMovFijo g : lmovimientos) {
			lventas.add(cv.ConsultaVentaPorNroMov(g.getNroMovimFijo()));
		}

		// Por cada venta que encontre devuelvo los valores
		for (Venta lv : lventas) {
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			codart = lv.getCODART();
			cgrupo = codart.substring(0, 2);
			csubf = codart.substring(2, 3);
			nroart = codart.substring(3, 6);
			a = new Articulo();
			a = ca.consultarArticulo(cgrupo, csubf, nroart);
			cli = new Cliente();
			cli = cc.consultaCliente(lv.getCODCLI());
			String fecDesde = f.format(lv.getFEC_DESDE());
			String fecHasta = f.format(lv.getFVTO());
			rta = new AdherentesGral(lv.getNROMOV(), fecDesde, fecHasta, lv.getANALISIS(), lv.getCODART(),
					lv.getREFERENCIA(), a.getDESART(), lv.getPRECIO(), (int) a.getUNIDAD(), a.getENVASE(),
					lv.getINNCTACTE(), lv.getVA_DTO(), cli.getNOMCLI());
			lrta.add(rta);
			a = null;
			cli = null;
			rta = null;
		}
		cv = null;
		ca = null;
		cc = null;
		cgmf = null;
		return lrta;
	}

	private Cliente limpiarDatos(Cliente c) throws ApplicationException {
		if (c.getTELCLI_1() == null) {
			c.setTELCLI_1("S/Nro");
		}
		if (c.getTELCLI_2() == null) {
			c.setTELCLI_2("S/Nro");
		}
		if (c.getOBSCLI() == null) {
			c.setOBSCLI("S/Obs");
		}
		CtrlConvenio cc = new CtrlConvenio();
		String nro = c.getCCOND();
		c.setCCOND(nro + " - " + cc.buscaDescripcion(nro));
		return c;
	}

	private String validaNull(String dato) {
		if (dato == null) {
			dato = "S/Dato";
		}
		return dato;
	}

	private void generarPdf(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		String socio = request.getParameter("printadherente").substring(0, 4);
		int movimiento = Integer.parseInt(request.getParameter("printadherente").substring(18));
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioActivo");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("#0.00");
		double total = 0.0;
		double tacuenta = 0.0;
		double tdebe = 0.0;

		Document documento = new Document(PageSize.A4, 36, 36, 90, 36);

		try {
			response.setContentType("application/pdf");
			OutputStream out = response.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(documento, out);

			// Add header and footer
			HeaderFooterPageEvent event = new HeaderFooterPageEvent(usuario.getNomUs());
			writer.setPageEvent(event);

			// Recupero la data del Socio que es garante y de los movimientos
			Cliente c = buscarSocio(socio);
			ArrayList<VentasM> movimientos = new ArrayList<>();
			cvm = new CtrlVentasM();

			movimientos = cvm.listarVentasMPorNroMov(movimiento);

			// Recupero los datos del socio al que le sale garante

			Cliente g = buscarSocio(movimientos.get(0).getCODCLI());

			// Seteo las fuentes
			Font f1 = new Font(FontFamily.HELVETICA, 13, Font.BOLDITALIC, BaseColor.DARK_GRAY);
			Font f2 = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLUE); // Fuentes de los titulos del
																						// reporte.
			Font f3 = new Font(FontFamily.HELVETICA, 10, Font.NORMAL); // Fuente de los textos.

			/*
			 * DEFINO LOS PARAMETROS DE LOS DATOS PERSONALES
			 */

			Paragraph t1 = new Paragraph(new Phrase("-- Datos Personales -- ", f1));
			t1.setAlignment(Element.ALIGN_CENTER);
			t1.add(Chunk.NEWLINE);
			t1.add(Chunk.NEWLINE);

			Paragraph p1 = new Paragraph();
			p1.add(new Phrase("Nro Socio: ", f2));
			p1.add(new Phrase(c.getCODCLI(), f3));
			Paragraph p2 = new Paragraph();
			p2.add(new Phrase("Nombre y Apellido: ", f2));
			p2.add(new Phrase(c.getNOMCLI(), f3));
			Paragraph p3 = new Paragraph();
			p3.add(new Phrase("Tpo Doc: ", f2));
			p3.add(new Phrase(c.getTIPO_DOC(), f3));
			Paragraph p4 = new Paragraph();
			p4.add(new Phrase("Nro Doc: ", f2));
			p4.add(new Phrase(c.getCUITCLI(), f3));
			Paragraph p5 = new Paragraph();
			p5.add(new Phrase("Fec. Nac: ", f2));
			p5.add(new Phrase(format.format(c.getFECHA_NAC()), f3));
			Paragraph p6 = new Paragraph();
			p6.add(new Phrase("Domicilio: ", f2));
			p6.add(new Phrase(c.getDOMCLI(), f3));
			Paragraph p7 = new Paragraph();
			p7.add(new Phrase("Ciudad : ", f2));
			p7.add(new Phrase(c.getCODPOS() + " - " + c.getLOCCLI(), f3));
			Paragraph p8 = new Paragraph();
			p8.add(new Phrase("Telefono/s: ", f2));
			p8.add(new Phrase(validaNull(c.getTELCLI_1()) + " - " + validaNull(c.getTELCLI_2()), f3));
			Paragraph p9 = new Paragraph();
			p9.add(new Phrase("E-Mail: ", f2));
			p9.add(new Phrase(validaNull(c.getE_MAIL()), f3));

			// Defino la tabla
			PdfPTable dp = new PdfPTable(4);
			dp.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			// Defino las celdas
			PdfPCell blanco = new PdfPCell(new Paragraph(" ")); // Es un blanco para crear espacio completo
			blanco.setBorder(Rectangle.NO_BORDER);
			blanco.setColspan(4);

			PdfPCell dpc1 = new PdfPCell(p1); // Nro socio
			dpc1.setBorder(Rectangle.NO_BORDER);
			PdfPCell dpc2 = new PdfPCell(p2); // Nombre y apellido
			dpc2.setBorder(Rectangle.NO_BORDER);
			dpc2.setColspan(3);
			PdfPCell dpc3 = new PdfPCell(p3); // Tpo doc
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
			PdfPCell dpc8 = new PdfPCell(p8); // Telefonos
			dpc8.setBorder(Rectangle.NO_BORDER);
			dpc8.setColspan(2);
			PdfPCell dpc9 = new PdfPCell(p9); // Email
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

			/* FIN DATOS PERSONALES */

			/*
			 * DEFINO LOS PARAMETROS DE LOS DATOS LABORALES
			 */

			Paragraph t2 = new Paragraph();
			t2.add(new Phrase("-- Datos Laborales -- ", f1));
			t2.setAlignment(Element.ALIGN_CENTER);
			t2.add(Chunk.NEWLINE);
			t2.add(Chunk.NEWLINE);

			Paragraph l1 = new Paragraph();
			l1.add(new Phrase("Empresa: ", f2));
			l1.add(new Phrase(c.getCPCCP(), f3));
			Paragraph l2 = new Paragraph();
			l2.add(new Phrase("Centro de Costo: ", f2));
			l2.add(new Phrase(c.getCONTACTO(), f3));
			Paragraph l3 = new Paragraph();
			l3.add(new Phrase("Desc. CC: ", f2));
			l3.add(new Phrase(c.getCONTACTO2(), f3));
			Paragraph l4 = new Paragraph();
			l4.add(new Phrase("Legajo: ", f2));
			l4.add(new Phrase(c.getDNRP(), f3));
			Paragraph l5 = new Paragraph();
			l5.add(new Phrase("Estado: ", f2));
			l5.add(new Phrase(c.getCOM_IND(), f3));
			Paragraph l6 = new Paragraph();
			l6.add(new Phrase("Fec. Ingreso: ", f2));
			l6.add(new Phrase(format.format(c.getFECHA_ING()), f3));

			// Defino la tabla
			PdfPTable dl = new PdfPTable(3);
			dl.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			// Defino las celdas

			PdfPCell dlc1 = new PdfPCell(l1); // Empresa
			dlc1.setBorder(Rectangle.NO_BORDER);
			PdfPCell dlc2 = new PdfPCell(l2); // CC
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
			/* FIN DATOS LABORALES */

			/*
			 * DEFINOS LOS DATOS DEL CONVENIO
			 */

			// Defino el titulo
			Paragraph t3 = new Paragraph();
			t3.add(new Phrase("-- Datos Convenios -- ", f1));
			t3.setAlignment(Element.ALIGN_CENTER);
			t3.add(Chunk.NEWLINE);
			t3.add(Chunk.NEWLINE);

			// defino los datos
			Paragraph c1 = new Paragraph();
			c1.add(new Phrase("Convenio: ", f2)); // Convenio
			c1.add(new Phrase(c.getCCOND(), f3));
			Paragraph c2 = new Paragraph();
			c2.add(new Phrase("Observaciones: ", f2)); // Observaciones
			c2.add(new Phrase(validaNull(c.getOBSCLI()), f3));

			// Defino la tabla
			PdfPTable dc = new PdfPTable(3);
			dc.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			// Defino las celdas

			PdfPCell dcc1 = new PdfPCell(c1); // Convenio
			dcc1.setBorder(Rectangle.NO_BORDER);
			PdfPCell dcc2 = new PdfPCell(c2); // Observaciones
			dcc2.setBorder(Rectangle.NO_BORDER);
			dcc2.setColspan(2);

			// Asigno las celdas a la tabla
			dc.addCell(dcc1);
			dc.addCell(dcc2);

			/* FIN DATOS DEL CONVENIO */

			/*
			 * DEFINO LA TABLA DE LOS MOVIMIENTOS PARA UN SOCIO Y UN MOVIMIENTO FIJO
			 */

			// Defino el titulo

			Paragraph t4 = new Paragraph();
			t4.add(Chunk.NEWLINE);
			t4.add(new Phrase("-- Es Garante de " + g.getNOMCLI() + " en el Movimiento " + movimiento + " --", f1));
			t4.setAlignment(Element.ALIGN_CENTER);
			t4.add(Chunk.NEWLINE);
			t4.add(Chunk.NEWLINE);

			// Defino los titulos de la tabla
			Paragraph m1 = new Paragraph(new Phrase("Fecha Movimiento", f2));
			Paragraph m2 = new Paragraph(new Phrase("Nro Comprobante", f2));
			Paragraph m3 = new Paragraph(new Phrase("Total", f2));
			Paragraph m4 = new Paragraph(new Phrase("A Cuenta", f2));
			Paragraph m5 = new Paragraph(new Phrase("Adeudado", f2));

			// Defino los datos de la tabla
			cvm = new CtrlVentasM();
			ArrayList<VentasM> lmov = cvm.listarVentasMPorNroMov(movimiento);
			cvm = null;

			// Defino la tabla
			PdfPTable tmov = new PdfPTable(5);

			// Defino las celdas
			PdfPCell cm1 = new PdfPCell(m1);
			cm1.setBorder(3);
			cm1.setBorderColor(BaseColor.BLUE);
			PdfPCell cm2 = new PdfPCell(m2);
			cm2.setBorder(3);
			cm2.setBorderColor(BaseColor.BLUE);
			PdfPCell cm3 = new PdfPCell(m3);
			cm3.setBorder(3);
			cm3.setBorderColor(BaseColor.BLUE);
			PdfPCell cm4 = new PdfPCell(m4);
			cm4.setBorder(3);
			cm4.setBorderColor(BaseColor.BLUE);
			PdfPCell cm5 = new PdfPCell(m5);
			cm5.setBorder(3);
			cm5.setBorderColor(BaseColor.BLUE);

			// Asigno la celda a la tabla
			tmov.addCell(cm1);
			tmov.addCell(cm2);
			tmov.addCell(cm3);
			tmov.addCell(cm4);
			tmov.addCell(cm5);

			for (VentasM v : lmov) {
				PdfPCell dm1 = new PdfPCell(new Paragraph(new Phrase(format.format(v.getFMOV()), f3)));
				dm1.setBorder(3);
				dm1.setBorderColor(BaseColor.BLUE);
				PdfPCell dm2 = new PdfPCell(new Paragraph(new Phrase(v.getNCOMP(), f3)));
				dm2.setBorder(3);
				dm2.setBorderColor(BaseColor.BLUE);
				PdfPCell dm3 = new PdfPCell(new Paragraph(new Phrase(df.format(v.getSUBTOTAL()), f3)));
				dm3.setBorder(3);
				dm3.setBorderColor(BaseColor.BLUE);
				PdfPCell dm4 = new PdfPCell(new Paragraph(new Phrase(df.format(v.getA_CUENTA()), f3)));
				dm4.setBorder(3);
				dm4.setBorderColor(BaseColor.BLUE);
				PdfPCell dm5 = new PdfPCell(new Paragraph(new Phrase(df.format(v.getA_CUENTAD()), f3)));
				dm5.setBorder(3);
				dm5.setBorderColor(BaseColor.BLUE);
				tmov.addCell(dm1);
				tmov.addCell(dm2);
				tmov.addCell(dm3);
				tmov.addCell(dm4);
				tmov.addCell(dm5);
				total += v.getSUBTOTAL();
				tacuenta += v.getA_CUENTA();
				tdebe += v.getA_CUENTAD();
			}
			PdfPCell tm1 = new PdfPCell();
			tm1.setBorder(3);
			tm1.setBorderColor(BaseColor.BLUE);
			PdfPCell tm2 = new PdfPCell(new Paragraph(new Phrase("Total: ", f2)));
			tm2.setBorder(3);
			tm2.setBorderColor(BaseColor.BLUE);
			PdfPCell tm3 = new PdfPCell(new Paragraph(new Phrase(df.format(total), f3)));
			tm3.setBorder(3);
			tm3.setBorderColor(BaseColor.BLUE);
			PdfPCell tm4 = new PdfPCell(new Paragraph(new Phrase(df.format(tacuenta), f3)));
			tm4.setBorder(3);
			tm4.setBorderColor(BaseColor.BLUE);
			PdfPCell tm5 = new PdfPCell(new Paragraph(new Phrase(df.format(tdebe), f3)));
			tm5.setBorder(3);
			tm5.setBorderColor(BaseColor.BLUE);
			tmov.addCell(tm1);
			tmov.addCell(tm2);
			tmov.addCell(tm3);
			tmov.addCell(tm4);
			tmov.addCell(tm5);

			/* FIN TABLAS MOVIMIENTOS */

			// Write to document
			documento.open();
			documento.add(t1);
			documento.add(dp);
			documento.add(t2);
			documento.add(dl);
			documento.add(t3);
			documento.add(dc);
			documento.add(t4);
			documento.add(tmov);
			documento.close();

		} catch (DocumentException | ApplicationException | IOException e) {
			e.printStackTrace();
		}
	}
}
