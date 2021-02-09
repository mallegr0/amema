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

import controladores.CtrlVenta;
import entidades.VentaReporte;
import entidades.Usuario; 
import reportes.HeaderFooterPageEvent;
import util.ApplicationException;

/**
 * Servlet implementation class ReporteMovFijo
 */
@WebServlet(urlPatterns = {"/ReporteMovFijo"})
public class ReporteMovFijo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CtrlVenta cVenta = null; 
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReporteMovFijo() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("evento_reporteMovimiento")  != null) {
			try { generarReporte(request, response); } 
			catch (ApplicationException | ParseException e) { e.printStackTrace(); }
		}
	}
	
	
	// METODOS PRIMARIOS
	
	private void generarReporte(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, ParseException {
		// AREA DE VARIABLES
		//Variables del form
		String fd = req.getParameter("fDesde");
		String fh = req.getParameter("fHasta"); 
		String estado = req.getParameter("estado");
		String[] familia = req.getParameterValues("familia");
		
		//Variables de Formato
		SimpleDateFormat sdFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		
		//Constructores
		cVenta = new CtrlVenta();
		
		//Arrays
		ArrayList<VentaReporte> lista = new ArrayList<>();
		
		
		//FIN AREA DE VARIABLES

		//Paso las fechas a Date para hacer las consulta Sql
		Date fDesde = (Date) sdFormat1.parse(fd);
		Date fHasta = (Date) sdFormat1.parse(fh);

		
		//Ahora busco segun los parametros que me pasaron. 
		if(estado.equals("ambos")) {
			//busco cualquier estado en VA_DTO
			switch (familia.length) {
			case 1:
				lista = cVenta.listarAmbosUnaFlia(fDesde, fHasta, familia[0]);
				break;
			case 2:
				lista = cVenta.listarAmbosDosFlia(fDesde, fHasta, familia[0], familia[1]);
				break;
			case 3:
				lista = cVenta.listarAmbosTresFlia(fDesde, fHasta, familia[0], familia[1], familia[2]);
				break;
			case 4:
				lista = cVenta.listarAmbosCuatroFlia(fDesde, fHasta, familia[0], familia[1], familia[2], familia[3]);
				break;
			case 5:
				lista = cVenta.listarAmbosTodasFlia(fDesde, fHasta);
				break;
			}
		}
		else {
			//busco con un estado en VA_DTO
			switch (familia.length) {
			case 1:
				lista = cVenta.listarEstadoUnaFlia(fDesde, fHasta, estado, familia[0]);
				break;
			case 2:
				lista = cVenta.listarEstadoDosFlia(fDesde, fHasta, estado, familia[0], familia[1]);
				break;
			case 3:
				lista = cVenta.listarEstadoTresFlia(fDesde, fHasta, estado, familia[0], familia[1], familia[2]);
				break;
			case 4:
				lista = cVenta.listarEstadoCuatroFlia(fDesde, fHasta, estado, familia[0], familia[1], familia[2], familia[3]);
				break;
			case 5:
				lista = cVenta.listarEstadoTodasFlia(fDesde, fHasta, estado);
				break;
			}
		}
		
		//Finalizo las varables que no uso 
		cVenta = null; 
		
		
		//LLamo a generar el pdf 
		generoPDF(req, res, lista); 
		
	}
	
	private void generoPDF(HttpServletRequest req, HttpServletResponse res, ArrayList<VentaReporte> lista) throws ApplicationException, ParseException {
		//AREA DE VARIABLES 
		//Variables del form
		String fd = req.getParameter("fDesde");
		String fh = req.getParameter("fHasta");
		Usuario user = (Usuario) req.getSession().getAttribute("usuarioActivo");
		
		//Variables de formateo 
		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		DecimalFormat dFormat = new DecimalFormat("#0.00");
		
		//Variables
		String[] partes = fd.split("-");
		String fDesde = partes[2]+"/"+partes[1]+"/"+partes[0];
		String[] partes2 = fh.split("-");
		String fHasta = partes2[2]+"/"+partes2[1]+"/"+partes2[0];
		String bEmpleado, dataSocio1, dataSocio2, empresa, legajo, doc, nombre, fecha, domicilio, telefono; 
		double impCuota = 0;
		double impSolicitado = 0; 
		int posicion = 0; 
		int contador = 0; 
		boolean aux = false; 
		
		
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
			Font font2 = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLUE); // Fuente de los Subtitulos.
			Font font3 = new Font(FontFamily.HELVETICA, 9, Font.NORMAL); //Fuente del texto
			Font font4 = new Font(FontFamily.HELVETICA, 7, Font.NORMAL);

			//DETALLO TITULO GENERAL DEL REPORTE
			Paragraph tituloGral = new Paragraph(new Phrase("REPORTES DE MOVIMIENTOS DESDE "+fDesde+" HASTA "+fHasta,font1));
			tituloGral.setAlignment(Element.ALIGN_CENTER);
			tituloGral.add(Chunk.NEWLINE);
			tituloGral.add(Chunk.NEWLINE);

			
			//DEFINO LA TABLA QUE VOY A USAR PARA MOSTRAR LOS DATOS
			PdfPTable tabla = new PdfPTable(10);
			tabla.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			// Defino las celdas en las que voy a mostrar los datos en la tabla
			PdfPCell blanco = new PdfPCell(new Paragraph(" ")); // Es un blanco para crear espacio completo y asi dividir las opciones
			blanco.setBorder(Rectangle.NO_BORDER);
			blanco.setColspan(10);
			
			if(lista.size() > 0) {
				//Voy a ir recorriendo lista para imprimir los valores en el pdf
				//Seteo las banderas y el titulo para mostrar
				bEmpleado = lista.get(posicion).getDnrp();
				empresa = noNull(lista.get(posicion).getCpccp());
				legajo = noNull(lista.get(posicion).getDnrp());
				doc = noNull(lista.get(posicion).getTipo_doc())+" "+noNull(lista.get(posicion).getCuitcli());
				nombre = noNull(lista.get(posicion).getNomcli());
				fecha = noNull(sdFormat.format(lista.get(posicion).getFecha_nac()));
				domicilio = noNull(lista.get(posicion).getDomcli());
				telefono = noNull(lista.get(posicion).getTelcli_1());
				dataSocio1 = empresa+" / "+legajo+" / "+doc+" / "+nombre;
				dataSocio2 = fecha+" / "+domicilio+" / "+telefono;
	
				//Incializo el contador al tamaño del array LISTA
				contador = lista.size();
				//Uso un while para ejecutar el recorrido
				while(contador > 0) {
					
					//Uso una variable AUXILIAR para ver que no se me repita el titulo siempre
					if(aux == false) {
						tabla.addCell(blanco);
						Paragraph tituloEmpleado1 = new Paragraph();
						tituloEmpleado1.add(new Phrase("Datos Empleado: ",font2));
						tituloEmpleado1.add(new Phrase(dataSocio1,font3));
						Paragraph tituloEmpleado2 = new Paragraph();
						tituloEmpleado2.add(new Phrase("Continua Datos Empleado: ",font2));
						tituloEmpleado2.add(new Phrase(dataSocio2,font3));
						//Creo la celda
						PdfPCell cTEmpleado1 = new PdfPCell(tituloEmpleado1);
						cTEmpleado1.setColspan(10);
						cTEmpleado1.setBorder(Rectangle.NO_BORDER);
						PdfPCell cTEmpleado2 = new PdfPCell(tituloEmpleado2);
						cTEmpleado2.setColspan(10);
						cTEmpleado2.setBorder(Rectangle.NO_BORDER);
						//lo asigno a la tabla
						tabla.addCell(cTEmpleado1);
						tabla.addCell(cTEmpleado2);
						tabla.addCell(blanco);
					}
	
					//Valido que el empleado actual sea el mismo que estoy leyendo
					if(bEmpleado.equals(lista.get(posicion).getDnrp())) {
						
						if(aux == false) {
							//muestro los titulos generales y los datos del renglon 
							PdfPCell t1 = new PdfPCell(new Phrase("Concepto",font2));
							t1.setBorderColorBottom(BaseColor.BLUE);
							t1.setBorder(Rectangle.BOTTOM);
							PdfPCell t2 = new PdfPCell(new Phrase("Cuotas",font2));
							t2.setBorderColorBottom(BaseColor.BLUE);
							t2.setBorder(Rectangle.BOTTOM);
							PdfPCell t3 = new PdfPCell(new Phrase("Fecha",font2));
							t3.setBorderColorBottom(BaseColor.BLUE);
							t3.setBorder(Rectangle.BOTTOM);
							PdfPCell t4 = new PdfPCell(new Phrase("Imp. Cuota",font2));
							t4.setBorderColorBottom(BaseColor.BLUE);
							t4.setBorder(Rectangle.BOTTOM);
							PdfPCell t5 = new PdfPCell(new Phrase("Imp. solic",font2));
							t5.setBorderColorBottom(BaseColor.BLUE);
							t5.setBorder(Rectangle.BOTTOM);
							PdfPCell t6 = new PdfPCell(new Phrase("Imp. Ch",font2));
							t6.setBorderColorBottom(BaseColor.BLUE);
							t6.setBorder(Rectangle.BOTTOM);
							PdfPCell t7 = new PdfPCell(new Phrase("Retención",font2));
							t7.setBorderColorBottom(BaseColor.BLUE);
							t7.setBorder(Rectangle.BOTTOM);
							PdfPCell t8 = new PdfPCell(new Phrase("Nro Cheque",font2));
							t8.setBorderColorBottom(BaseColor.BLUE);
							t8.setBorder(Rectangle.BOTTOM);
							PdfPCell t9 = new PdfPCell(new Phrase("Banco",font2));
							t9.setBorderColorBottom(BaseColor.BLUE);
							t9.setBorder(Rectangle.BOTTOM);
							PdfPCell t10 = new PdfPCell(new Phrase("Estado",font2));
							t10.setBorderColorBottom(BaseColor.BLUE);
							t10.setBorder(Rectangle.BOTTOM);
							tabla.addCell(t1);
							tabla.addCell(t2);
							tabla.addCell(t3);
							tabla.addCell(t4);
							tabla.addCell(t5);
							tabla.addCell(t6);
							tabla.addCell(t7);
							tabla.addCell(t8);
							tabla.addCell(t9);
							tabla.addCell(t10);
						}
						impCuota = lista.get(posicion).getPrecio()/lista.get(posicion).getUnidad();
						impSolicitado = impCuota+lista.get(posicion).getImpch();
						
						//Muestro el renglon
						PdfPCell c1 = new PdfPCell(new Phrase(lista.get(posicion).getCodart()+" "+lista.get(posicion).getDesart(),font4));
						c1.setBorder(Rectangle.NO_BORDER);
						PdfPCell c2 = new PdfPCell(new Phrase(Double.toString(lista.get(posicion).getUnidad()),font4));
						c2.setBorder(Rectangle.NO_BORDER);
						PdfPCell c3 = new PdfPCell(new Phrase(sdFormat.format(lista.get(posicion).getFec_desde()),font4));
						c3.setBorder(Rectangle.NO_BORDER);
						PdfPCell c4 = new PdfPCell(new Phrase("$"+dFormat.format(impCuota),font4));
						c4.setBorder(Rectangle.NO_BORDER);
						PdfPCell c5 = new PdfPCell(new Phrase("$"+dFormat.format(impSolicitado),font4));
						c5.setBorder(Rectangle.NO_BORDER);
						PdfPCell c6 = new PdfPCell(new Phrase("$"+dFormat.format(lista.get(posicion).getImpch()),font4));
						c6.setBorder(Rectangle.NO_BORDER);
						PdfPCell c7 = new PdfPCell(new Phrase(lista.get(posicion).getCancdeuant()+"  $"+dFormat.format(lista.get(posicion).getImpcancdeuant()),font4));
						c7.setBorder(Rectangle.NO_BORDER);
						PdfPCell c8 = new PdfPCell(new Phrase(lista.get(posicion).getUbicac1(),font4));
						c8.setBorder(Rectangle.NO_BORDER);
						PdfPCell c9 = new PdfPCell(new Phrase(lista.get(posicion).getUbicac2(),font4));
						c9.setBorder(Rectangle.NO_BORDER);
						PdfPCell c10 = new PdfPCell(new Phrase(lista.get(posicion).getVa_dto(),font4));
						c10.setBorder(Rectangle.NO_BORDER);
						tabla.addCell(c1);
						tabla.addCell(c2);
						tabla.addCell(c3);
						tabla.addCell(c4);
						tabla.addCell(c5);
						tabla.addCell(c6);
						tabla.addCell(c7);
						tabla.addCell(c8);
						tabla.addCell(c9);
						tabla.addCell(c10);
						aux = true; 
					}
					else {
						bEmpleado = lista.get(posicion).getDnrp();
						empresa = noNull(lista.get(posicion).getCpccp());
						legajo = noNull(lista.get(posicion).getDnrp());
						doc = noNull(lista.get(posicion).getTipo_doc())+" "+noNull(lista.get(posicion).getCuitcli());
						nombre = noNull(lista.get(posicion).getNomcli());
						fecha = noNull(sdFormat.format(lista.get(posicion).getFecha_nac()));
						domicilio = noNull(lista.get(posicion).getDomcli());
						telefono = noNull(lista.get(posicion).getTelcli_1());
						dataSocio1 = empresa+" / "+legajo+" / "+doc+" / "+nombre;
						dataSocio2 = fecha+" / "+domicilio+" / "+telefono;
						aux = false; 
						posicion--;
						contador++; 
					}
					
					posicion++; 
					contador--; 
				}
			}
			else {
				PdfPCell nada = new PdfPCell(new Phrase("No hay datos para la fecha y familia seleccionada",font1));
				nada.setBorder(Rectangle.NO_BORDER);
				nada.setColspan(10);
				tabla.addCell(nada);
				
			}
			
			

			// Write to document
			documento.open();
			documento.add(tituloGral);
			documento.add(tabla);
			documento.close();

		} catch (DocumentException | IOException e) { e.printStackTrace(); }
		
	}
	
	// METODOS SECUNDARIOS
	
	private String noNull(String data) throws ApplicationException {
		if(data == null) { data = "-"; }
		return data;
	}

}
