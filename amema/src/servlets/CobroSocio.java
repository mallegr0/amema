package servlets;

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

import controladores.CtrlAnticC;
import controladores.CtrlCheque;
import controladores.CtrlCliente;
import controladores.CtrlCtactecliente;
import controladores.CtrlFactRec;
import controladores.CtrlReciboM;
import controladores.CtrlVentasM;
import entidades.AnticC;
import entidades.Cliente;
import entidades.DatosCobroFact;
import entidades.FactRec;
import entidades.ReciboM;
import entidades.Usuario;
import entidades.VentasM;
import reportes.HeaderFooterPageEvent;
import entidades.DatosCobroGral;
import util.ApplicationException;

/**
 * Servlet implementation class CobroSocio
 */
@WebServlet(urlPatterns = {"/CobroSocio"})
public class CobroSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String urlCobroSocio = "/amema/views/consultaCobroSocio.jsp";
	private String urlDetalleCobro = "/amema/views/detalleCobroSocio.jsp";
	private CtrlReciboM cRecibosM = null;
	private CtrlVentasM cVentasM = null; 
	private CtrlAnticC cAnticC = null; 
	private CtrlCheque cCheque = null; 
	private CtrlCtactecliente cCtacte = null; 
	private CtrlFactRec cFactRec = null; 
	private CtrlCliente cCliente = null; 
	
	
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
				imprimirPago(request, response);
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
		//Declaro las variables que voy a usar}
		//variables
		String prefijo, comprobante; 
		String[] partes; 
		
		//formatos
		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat dFormat = new DecimalFormat("#.00"); 
		
		//Controlador
		cRecibosM = new CtrlReciboM(); 
		cCliente = new CtrlCliente();
		cAnticC = new CtrlAnticC();
		cVentasM = new CtrlVentasM();
		cFactRec = new CtrlFactRec();
		
		//Entidad
		ReciboM recibo = null; 
		Cliente cliente = null;
		AnticC anticipo = null; 
		DatosCobroGral dataListados = null; 
		VentasM venta = null; 
		
		//ARRAYS
		ArrayList<DatosCobroGral> anticipos = new ArrayList<>();
		ArrayList<DatosCobroGral> creditos = new ArrayList<>();
		ArrayList<String> comprobantes = new ArrayList<>();
		ArrayList<Double> importes = new ArrayList<>();
		ArrayList<DatosCobroFact> facturas = new ArrayList<>();
		
		
		//Recupero el prefijo y el nro de comprobante del form y los asigno a sus variables correspondientes.
		partes = req.getParameter("nrecibo").split(" - "); 
		prefijo = partes[0];
		comprobante = partes[1];
		
		//Recupero el recibo
		recibo = cRecibosM.consultaReciboPorNroyPrefijo(prefijo, comprobante);
		
		//Recupero los datos del cliente
		cliente = cCliente.consultaCliente(recibo.getCODCLI());
		
		//Listo los anticipos si hay 
		if(recibo.getNANTI01() != null) {
			comprobantes = cargoNantis(recibo);
			importes = cargoIantis(recibo);
		}
		
		if(!comprobantes.isEmpty()) {
			for(int j= 0; j <= comprobantes.size(); j++) {
				anticipo = cAnticC.consultaAnticipoPorNroRecibo(comprobantes.get(j));
				dataListados = new DatosCobroGral(comprobante, sdFormat.format(anticipo.getFrecibo()), dFormat.format(importes.get(j)));
				anticipos.add(dataListados);
			}
		}
		
		//Listo los creditos si hay
		if(recibo.getNCRED01() != null) {
			comprobantes = cargoNcreds(recibo);
			importes = cargoIcreds(recibo);
		}
		
		if(!comprobantes.isEmpty()) {
			for(int j= 0; j <= comprobantes.size(); j++) {
				venta = cVentasM.consultaVentaPorTipoComp(comprobante, "2"); 
				dataListados = new DatosCobroGral(comprobante, sdFormat.format(venta.getFMOV()), dFormat.format(importes.get(j)));
				creditos.add(dataListados);
			}
		}
		
		//Recupero todas las facturas que son del recibo ingresados
		facturas = cFactRec.listarFacturasPorNroReciboDCobro(comprobante, recibo.getFRECIBO());
		
		//Asigno a la session los datos que voy recuperando
		req.getSession().setAttribute("dataRecibo", recibo);
		req.getSession().setAttribute("dataCliente", cliente);
		req.getSession().setAttribute("dataAnticipos", anticipos);
		req.getSession().setAttribute("dataCreditos", creditos);
		req.getSession().setAttribute("dataFacturas", facturas);
		
		//Finalizo las variables que me consumen memoria
		cRecibosM = null; 
		cCliente = null;
		cAnticC = null;
		cVentasM = null;
		cFactRec = null; 
		recibo = null; 
		cliente = null;
		anticipo = null; 
		dataListados = null; 
		venta = null;
	}
	
	private void eliminarPago(HttpServletRequest req) throws ApplicationException {
		//Este metodo lo que hace es eliminar el recibo en la tabla recibosm y volver atras todos 
		//los cambios que tengan involucrado este nro de recibo.
		
		//Declaro las variables que voy a usar
		//variables del form
		String aux = req.getParameter("nrecibo"); 
		
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
	
	private void imprimirPago(HttpServletRequest req, HttpServletResponse response) throws ApplicationException {
		//Declaro las variables que voy a usar}
		//variables
		String prefijo, comprobante; 
		String[] partes; 
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioActivo");
		double totalF = 0; 
		double totalC = 0;
		double totalA = 0; 
		double totalR = 0; 
		double totalD = 0; 
		
		//formatos
		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yy");
		DecimalFormat dFormat = new DecimalFormat("#0.00"); 
				
		//Controlador
		cRecibosM = new CtrlReciboM(); 
		cCliente = new CtrlCliente();
		cAnticC = new CtrlAnticC();
		cVentasM = new CtrlVentasM();
		cFactRec = new CtrlFactRec();
				
		//Entidad
		ReciboM recibo = null; 
		Cliente cliente = null;
		AnticC anticipo = null; 
		DatosCobroGral dataListados = null; 
		VentasM venta = null; 
			
		//ARRAYS
		ArrayList<DatosCobroGral> anticipos = new ArrayList<>();
		ArrayList<DatosCobroGral> creditos = new ArrayList<>();
		ArrayList<String> comprobantes = new ArrayList<>();
		ArrayList<Double> importes = new ArrayList<>();
		ArrayList<DatosCobroFact> facturas = new ArrayList<>();
				
				
		//Recupero el prefijo y el nro de comprobante del form y los asigno a sus variables correspondientes.
		partes = req.getParameter("nrecibo").split(" - "); 
		prefijo = partes[0];
		comprobante = partes[1];
			
		//Recupero el recibo
		recibo = cRecibosM.consultaReciboPorNroyPrefijo(prefijo, comprobante);
				
		//Recupero los datos del cliente
		cliente = cCliente.consultaCliente(recibo.getCODCLI());
				
		//Listo los anticipos si hay 
		if(recibo.getNANTI01() != null) {
			comprobantes = cargoNantis(recibo);
			importes = cargoIantis(recibo);
		}
				
		if(!comprobantes.isEmpty()) {
			for(int j= 0; j <= comprobantes.size(); j++) {
				anticipo = cAnticC.consultaAnticipoPorNroRecibo(comprobantes.get(j));
				dataListados = new DatosCobroGral(comprobante, sdFormat.format(anticipo.getFrecibo()), dFormat.format(importes.get(j)));
				anticipos.add(dataListados);
			}
		}
				
		//Listo los creditos si hay
		if(recibo.getNCRED01() != null) {
			comprobantes = cargoNcreds(recibo);
			importes = cargoIcreds(recibo);
		}
				
		if(!comprobantes.isEmpty()) {
			for(int j= 0; j <= comprobantes.size(); j++) {
				venta = cVentasM.consultaVentaPorTipoComp(comprobante, "2"); 
				dataListados = new DatosCobroGral(comprobante, sdFormat.format(venta.getFMOV()), dFormat.format(importes.get(j)));
				creditos.add(dataListados);
			}
		}
				
		//Recupero todas las facturas que son del recibo ingresados
		facturas = cFactRec.listarFacturasPorNroReciboDCobro(comprobante, recibo.getFRECIBO());
				
		/*
		 * GENERO EL PDF
		 */
				
		Document documento = new Document(PageSize.A4, 36, 36, 90, 36);

		try {
			response.setContentType("application/pdf");
			OutputStream out = response.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(documento, out);

			// Add header and footer
			HeaderFooterPageEvent event = new HeaderFooterPageEvent(usuario.getNomUs());
			writer.setPageEvent(event);

			// Seteo las fuentes
			Font f1 = new Font(FontFamily.HELVETICA, 13, Font.BOLDITALIC, BaseColor.DARK_GRAY);
			Font f2 = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLUE); // Fuentes de los titulos del
																								// reporte.
			Font f3 = new Font(FontFamily.HELVETICA, 10, Font.NORMAL); // Fuente de los textos.
			Font f4 = new Font(FontFamily.HELVETICA, 8, Font.NORMAL);

			

			Paragraph t1 = new Paragraph(new Phrase("-- Detalle del Cobro -- ", f1));
			t1.setAlignment(Element.ALIGN_CENTER);
			t1.add(Chunk.NEWLINE);
			t1.add(Chunk.NEWLINE);

			Paragraph dc1 = new Paragraph();
			dc1.add(new Phrase("Nro de Recibo: ",f2));
			dc1.add(new Phrase(recibo.getPREFIJO()+"-"+recibo.getNRECIBO(),f3));
			Paragraph dc2 = new Paragraph();
			dc2.add(new Phrase("Tpo de Pago: ",f2));
			dc2.add(new Phrase(recibo.getTRECIBO(),f3));
			Paragraph dc3 = new Paragraph();
			dc3.add(new Phrase("Fecha de Imputación: ",f2));
			dc3.add(new Phrase(sdFormat.format(recibo.getFRECIBO()),f3));
			Paragraph dc4 = new Paragraph();
			dc4.add(new Phrase("Fecha de Pago: ",f2));
			dc4.add(new Phrase(sdFormat.format(recibo.getFRECIBO()),f3));
			Paragraph dc5 = new Paragraph();
			dc5.add(new Phrase("Cliente: ",f2));
			dc5.add(new Phrase(cliente.getCODCLI()+"-"+cliente.getNOMCLI(),f3));
			Paragraph dc6 = new Paragraph();
			dc6.add(new Phrase("Condición IVA: ",f2));
			dc6.add(new Phrase(cliente.getREGCLI(),f3));

			// Defino la tabla en la que va el primer grupo de información
			PdfPTable dc = new PdfPTable(3);
			dc.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			// Defino las celdas en las que voy a mostrar los datos de la primer tabla
			PdfPCell blanco = new PdfPCell(new Paragraph(" ")); // Es un blanco para crear espacio completo
			blanco.setBorder(Rectangle.NO_BORDER);
			blanco.setColspan(3);
			
			PdfPCell cdc1 = new PdfPCell(dc1);
			cdc1.setBorder(Rectangle.NO_BORDER);
			PdfPCell cdc2 = new PdfPCell(dc2);
			cdc2.setBorder(Rectangle.NO_BORDER);
			PdfPCell cdc3 = new PdfPCell(dc3);
			cdc3.setBorder(Rectangle.NO_BORDER);
			PdfPCell cdc4 = new PdfPCell(dc4);
			cdc4.setBorder(Rectangle.NO_BORDER);
			PdfPCell cdc5 = new PdfPCell(dc5);
			cdc5.setBorder(Rectangle.NO_BORDER);
			PdfPCell cdc6 = new PdfPCell(dc6);
			cdc6.setBorder(Rectangle.NO_BORDER);
			

			// Asigno las celdas a la tabla
			dc.addCell(cdc1);
			dc.addCell(cdc2);
			dc.addCell(cdc3);
			dc.addCell(blanco);
			dc.addCell(cdc4);
			dc.addCell(cdc5);
			dc.addCell(cdc6);
			dc.addCell(blanco);		


			/*
			 * DEFINO LOS PARAMETROS DE LOS DATOS DEL PAGO
			 */

			Paragraph t2 = new Paragraph();
			t2.add(new Phrase("-- Datos del Pago -- ", f1));
			t2.setAlignment(Element.ALIGN_CENTER);
			t2.add(Chunk.NEWLINE);
			t2.add(Chunk.NEWLINE);

			Paragraph dp1 = new Paragraph();
			dp1.add(new Phrase("Imp. En Efectivo: ",f2));
			dp1.add(new Phrase("$"+dFormat.format(recibo.getEFECTIVO()),f3));
			Paragraph dp2 = new Paragraph();
			dp2.add(new Phrase("Imp. En Cheque: ",f2));
			dp2.add(new Phrase("$"+dFormat.format(recibo.getCHEQUE()),f3));
			Paragraph dp3 = new Paragraph();
			dp3.add(new Phrase("A cuenta: ",f2));
			dp3.add(new Phrase("$"+dFormat.format(recibo.getA_CTA()),f3));
			Paragraph dp4 = new Paragraph();
			dp4.add(new Phrase("Aplicado: ",f2));
			dp4.add(new Phrase("$"+dFormat.format(0),f3));
			Paragraph dp5 = new Paragraph();
			dp5.add(new Phrase("Total de Pago: ",f2));
			dp5.add(new Phrase("$"+dFormat.format(recibo.getEFECTIVO()+recibo.getCHEQUE()+recibo.getA_CTA()),f3));

			// Defino la tabla
			PdfPTable dp = new PdfPTable(3);
			dp.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
			
			// Defino las celdas
			PdfPCell dpBlanco = new PdfPCell(new Paragraph(" ")); // Es un blanco para crear espacio completo
			dpBlanco.setBorder(Rectangle.NO_BORDER);
			dpBlanco.setColspan(3);
			
			PdfPCell cdp1 = new PdfPCell(dp1);
			cdp1.setBorder(Rectangle.NO_BORDER);
			PdfPCell cdp2 = new PdfPCell(dp2);
			cdp2.setBorder(Rectangle.NO_BORDER);
			PdfPCell cdp3 = new PdfPCell(dp3);
			cdp3.setBorder(Rectangle.NO_BORDER);
			PdfPCell cdp4 = new PdfPCell(dp4);
			cdp4.setBorder(Rectangle.NO_BORDER);
			PdfPCell cdp5 = new PdfPCell(dp5);
			cdp5.setBorder(Rectangle.NO_BORDER);
			cdp5.setColspan(2);

			// Asigno las celdas a la tabla
			dp.addCell(cdp1);
			dp.addCell(cdp2);
			dp.addCell(cdp3);
			dp.addCell(dpBlanco);
			dp.addCell(cdp4);
			dp.addCell(cdp5);
			
			
			/*
			 * Defino los datos de las facturas que se efectuaron en el pago
			 */

			// Defino el titulo
			Paragraph t3 = new Paragraph(new Phrase("-- En concepto de -- ", f1));
			t3.setAlignment(Element.ALIGN_CENTER);
			t3.add(Chunk.NEWLINE);
			t3.add(Chunk.NEWLINE);

			//Defino los titulos de la tabla de las facturas
			Paragraph tf1 = new Paragraph(new Phrase("Tpo Comp.",f2));
			Paragraph tf2 = new Paragraph(new Phrase("Letra",f2));
			Paragraph tf3 = new Paragraph(new Phrase("Prefijo",f2));
			Paragraph tf4 = new Paragraph(new Phrase("Nro Comp.",f2));
			Paragraph tf5 = new Paragraph(new Phrase("Fecha",f2));
			Paragraph tf6 = new Paragraph(new Phrase("Imp Pagado",f2));
			Paragraph tf7 = new Paragraph(new Phrase("Descuento",f2));
			Paragraph tf8 = new Paragraph(new Phrase("Tasa",f2));
			Paragraph tf9 = new Paragraph(new Phrase("Observaciones",f2));

			// Defino la tabla
			PdfPTable df = new PdfPTable(9);
			df.getDefaultCell().setBorder(Rectangle.BOTTOM);
			df.getDefaultCell().setBackgroundColor(BaseColor.BLUE);
			
			// Defino las celdas
			PdfPCell ctf1 = new PdfPCell(tf1);
			ctf1.setBorder(Rectangle.BOTTOM);
			PdfPCell ctf2 = new PdfPCell(tf2);
			ctf2.setBorder(Rectangle.BOTTOM);
			PdfPCell ctf3 = new PdfPCell(tf3);
			ctf3.setBorder(Rectangle.BOTTOM);
			PdfPCell ctf4 = new PdfPCell(tf4);
			ctf4.setBorder(Rectangle.BOTTOM);
			PdfPCell ctf5 = new PdfPCell(tf5);
			ctf5.setBorder(Rectangle.BOTTOM);
			PdfPCell ctf6 = new PdfPCell(tf6);
			ctf6.setBorder(Rectangle.BOTTOM);
			PdfPCell ctf7 = new PdfPCell(tf7);
			ctf7.setBorder(Rectangle.BOTTOM);
			PdfPCell ctf8 = new PdfPCell(tf8);
			ctf8.setBorder(Rectangle.BOTTOM);
			PdfPCell ctf9 = new PdfPCell(tf9);
			ctf9.setBorder(Rectangle.BOTTOM);
			
			// Asigno las celdas que serian los titulos de la tabla
			df.addCell(ctf1);
			df.addCell(ctf2);
			df.addCell(ctf3);
			df.addCell(ctf4);
			df.addCell(ctf5);
			df.addCell(ctf6);
			df.addCell(ctf7);
			df.addCell(ctf8);
			df.addCell(ctf9);
			
			//Valido que facturas no este vacio para recorrerlo, sino muestro un mensaje de que no hay datos para mostrar
			if(facturas != null) {
				for(DatosCobroFact f : facturas) {
					//recorro el array y muestro los datos en la tabla
					PdfPCell c1 = new PdfPCell(new Phrase(f.getTcomp(),f4));
					PdfPCell c2 = new PdfPCell(new Phrase(f.getLetra(),f4));
					PdfPCell c3 = new PdfPCell(new Phrase(f.getPrefijo(),f4));
					PdfPCell c4 = new PdfPCell(new Phrase(f.getNcomp(),f4));
					PdfPCell c5 = new PdfPCell(new Phrase(sdFormat.format(f.getFecha()),f4));
					PdfPCell c6 = new PdfPCell(new Phrase(dFormat.format(f.getImppagado()),f4));
					PdfPCell c7 = new PdfPCell(new Phrase(dFormat.format(f.getDescuento()),f4));
					PdfPCell c8 = new PdfPCell(new Phrase(dFormat.format(f.getTasa()),f4));
					PdfPCell c9 = new PdfPCell(new Phrase(f.getObs(),f4));
					df.addCell(c1);
					df.addCell(c2);
					df.addCell(c3);
					df.addCell(c4);
					df.addCell(c5);
					df.addCell(c6);
					df.addCell(c7);
					df.addCell(c8);
					df.addCell(c9);
					totalF += f.getImppagado();
					totalD += f.getDescuento();
				}
			}
			else {
				Paragraph p1 = new Paragraph(new Phrase("No hay facturas para mostrar",f3));
				p1.setAlignment(Element.ALIGN_CENTER);
				PdfPCell c1 = new PdfPCell();
				c1.setColspan(9);
			}
			//defino los parrafos de los titulos y los valores para agregarlo a la tabla
			Paragraph tTotalF = new Paragraph(new Phrase("Total de Comprobante: ",f2));
			tTotalF.setAlignment(Element.ALIGN_RIGHT);
			PdfPCell cTTotalF = new PdfPCell(tTotalF); 
			cTTotalF.setColspan(8);
			PdfPCell cTotalF = new PdfPCell(new Phrase("$"+dFormat.format(totalF),f2));
			Paragraph tTotalD = new Paragraph(new Phrase("Descuentos Efectuados: ",f2));
			tTotalD.setAlignment(Element.ALIGN_RIGHT);
			PdfPCell cTTotalD = new PdfPCell(tTotalD); 
			cTTotalD.setColspan(8);
			PdfPCell cTotalD = new PdfPCell(new Phrase("$"+dFormat.format(totalD),f2));
			Paragraph tTotalR = new Paragraph(new Phrase("Retenciones Aplicadas: ",f2));
			tTotalR.setAlignment(Element.ALIGN_RIGHT);
			PdfPCell cTTotalR = new PdfPCell(tTotalR); 
			cTTotalR.setColspan(8);
			PdfPCell cTotalR = new PdfPCell(new Phrase("$"+dFormat.format(totalR),f2));
			df.addCell(cTTotalF);
			df.addCell(cTotalF);
			df.addCell(cTTotalD);
			df.addCell(cTotalD);
			df.addCell(cTTotalR);
			df.addCell(cTotalR);
			
			
			/*
			 * Defino los datos de los créditos que se efectuaron en el pago
			 */

			// Defino el titulo
			Paragraph t4 = new Paragraph(new Phrase("-- Notas del Crédito Descontadas -- ", f1));
			t4.setAlignment(Element.ALIGN_CENTER);
			t4.add(Chunk.NEWLINE);
			t4.add(Chunk.NEWLINE);

			//Defino los titulos de la tabla de las facturas
			Paragraph tc1 = new Paragraph(new Phrase("Nro Comprobante",f2));
			Paragraph tc2 = new Paragraph(new Phrase("Fecha",f2));
			Paragraph tc3 = new Paragraph(new Phrase("Importe Aplicado",f2));

			// Defino la tabla
			PdfPTable dcd = new PdfPTable(3);
			dcd.getDefaultCell().setBorder(Rectangle.BOTTOM);
			dcd.getDefaultCell().setBackgroundColor(BaseColor.BLUE);
			
			// Defino las celdas
			PdfPCell ctc1 = new PdfPCell(tc1);
			ctc1.setBorder(Rectangle.BOTTOM);
			PdfPCell ctc2 = new PdfPCell(tc2);
			ctc2.setBorder(Rectangle.BOTTOM);
			PdfPCell ctc3 = new PdfPCell(tc3);
			ctc3.setBorder(Rectangle.BOTTOM);
			
			// Asigno las celdas que serian los titulos de la tabla
			dcd.addCell(ctc1);
			dcd.addCell(ctc2);
			dcd.addCell(ctc3);
			
			//Valido que creditos no este vacio para recorrerlo, sino muestro un mensaje de que no hay datos para mostrar
			if(creditos != null) {
				for(DatosCobroGral cred : creditos) {
					//recorro el array y muestro los datos en la tabla
					PdfPCell c1 = new PdfPCell(new Phrase(cred.getnRecibo(),f3));
					PdfPCell c2 = new PdfPCell(new Phrase(cred.getFecha(),f3));
					PdfPCell c3 = new PdfPCell(new Phrase(cred.getImporte(),f3));
					df.addCell(c1);
					df.addCell(c2);
					df.addCell(c3);
					totalC += Double.parseDouble(cred.getImporte());
				}
				PdfPCell b = new PdfPCell(new Phrase(" ",f2));
				b.setColspan(3);
			}
			Paragraph tTotalC = new Paragraph(new Phrase("Total N. Cred. Descontadas: ",f2));
			tTotalC.setAlignment(Element.ALIGN_RIGHT);
			PdfPCell cTTotalC = new PdfPCell(tTotalC);
			cTTotalC.setColspan(8);
			PdfPCell cTotalC = new PdfPCell(new Phrase("$"+dFormat.format(totalC),f2));
			dcd.addCell(cTTotalC);
			dcd.addCell(cTotalC);
			
			/*
			 * Defino los datos de los créditos que se efectuaron en el pago
			 */

			// Defino el titulo
			Paragraph t5 = new Paragraph(new Phrase("-- Total de anticipos -- ", f1));
			t5.setAlignment(Element.ALIGN_CENTER);
			t5.add(Chunk.NEWLINE);
			t5.add(Chunk.NEWLINE);

			//Defino los titulos de la tabla de las facturas
			Paragraph ta1 = new Paragraph(new Phrase("Nro Recibo",f2));
			Paragraph ta2 = new Paragraph(new Phrase("Fecha",f2));
			Paragraph ta3 = new Paragraph(new Phrase("Importe Aplicado",f2));

			// Defino la tabla
			PdfPTable da = new PdfPTable(3);
			da.getDefaultCell().setBorder(Rectangle.BOTTOM);
			da.getDefaultCell().setBackgroundColor(BaseColor.BLUE);
			
			// Defino las celdas
			PdfPCell cta1 = new PdfPCell(ta1);
			cta1.setBorder(Rectangle.BOTTOM);
			PdfPCell cta2 = new PdfPCell(ta2);
			cta2.setBorder(Rectangle.BOTTOM);
			PdfPCell cta3 = new PdfPCell(ta3);
			cta3.setBorder(Rectangle.BOTTOM);
			
			// Asigno las celdas que serian los titulos de la tabla
			da.addCell(cta1);
			da.addCell(cta2);
			da.addCell(cta3);
			
			//Valido que creditos no este vacio para recorrerlo, sino muestro un mensaje de que no hay datos para mostrar
			if(anticipos != null) {
				for(DatosCobroGral anti : anticipos) {
					//recorro el array y muestro los datos en la tabla
					PdfPCell c1 = new PdfPCell(new Phrase(anti.getnRecibo(),f3));
					PdfPCell c2 = new PdfPCell(new Phrase(anti.getFecha(),f3));
					PdfPCell c3 = new PdfPCell(new Phrase(anti.getImporte(),f3));
					df.addCell(c1);
					df.addCell(c2);
					df.addCell(c3);
					totalA += Double.parseDouble(anti.getImporte());
				}
				PdfPCell b = new PdfPCell(new Phrase(" ",f2));
				b.setColspan(3);
			}
			
			Paragraph tTotalA = new Paragraph(new Phrase("Total Anticipos Aplicados: ",f2));
			tTotalA.setAlignment(Element.ALIGN_RIGHT);
			PdfPCell cTTotalA = new PdfPCell(tTotalA);
			cTTotalA.setColspan(8);
			PdfPCell cTotalA = new PdfPCell(new Phrase("$"+dFormat.format(totalA),f2));
			da.addCell(cTTotalA);
			da.addCell(cTotalA);

			//FALTA HACER EL BUCLE DE LAS FACTURAS Y LAS OTRAS DOS TABLAS
					/* FIN TABLAS MOVIMIENTOS */

					// Write to document
					documento.open();
					documento.add(t1);
					documento.add(dc);
					documento.add(t2);
					documento.add(dp);
					documento.add(t3);
					documento.add(df);
					documento.add(t4);
					documento.add(dcd);
					documento.add(t5);
					documento.add(da);
					documento.close();

				} catch (DocumentException | IOException e) {
					e.printStackTrace();
				}
				
				/*
				 * FIN GENERO PDF
				 */
				
				//Finalizo las variables que me consumen memoria
				cRecibosM = null; 
				cCliente = null;
				cAnticC = null;
				cVentasM = null;
				cFactRec = null; 
				recibo = null; 
				cliente = null;
				anticipo = null; 
				dataListados = null; 
				venta = null;
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
		boolean r = true; 
		cCtacte = new CtrlCtactecliente(); 
		
		//Valido que haya un recibo en cta cte cli si lo hay le doy de baja sino devuelvo true, para que siga el curso
		if(cCtacte.consultarComprobanteCta(nroRecibo) != null) {
			r = cCtacte.bajaCtaCtePorComprobante(nroRecibo);
		}
		
		//Finalizo las variables que estan en memoria y devuelvo r
		cCtacte = null; 
		
		return r; 
	}
	
	private boolean actualizarVentas(ReciboM rec) throws ApplicationException {
		//declaro las variables que voy a usar
		//Variables
		boolean rta = true; 
		
		//Antes recupere el recibo segun el prefijo y el nro que ingreso el usuario, ahora consulto si tiene anticipos
		//Si los tiene actualizo a_cta (a_cta - ianti) de cada recibosM que me indique
		if(rec.getNANTI01() != null) { rta = actualizarAnticipos(rec); } 
		
		//Antes recupere el recibo segun el prefijo y el nro que ingreso el usuario, ahora consulto si tiene notas de credito
		//Si los tiene actualizo a_cta (a_cta - icred) de cada recibosM que me indique
		if(rec.getNCRED01() != null) {rta = actualizarCreditos(rec); }
		
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
