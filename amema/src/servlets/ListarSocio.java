package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

import controladores.CtrlCliente;
import entidades.Cliente;
import entidades.Usuario;
import util.ApplicationException;

/**
 * Servlet implementation class ListarSocio
 */
@WebServlet(urlPatterns = {"/ListarSocio"})
public class ListarSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String urlListarSocio = "/amema/views/listarsocios.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarSocio() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String socioActivo = request.getParameter("activos");
		String convenio = request.getParameter("convenio");
		
		Usuario user = (entidades.Usuario) request.getSession().getAttribute("usuarioActivo");
		
		CtrlCliente cc = new CtrlCliente();
		ArrayList<Cliente> lista = new ArrayList<>();

		try {
			if(socioActivo != null) {
				if(convenio != null) { 
					lista = cc.listarClienteActivoConvenio("A", convenio);
					migrarExcel(lista, user.getNomUs());
				}
				else { 
					lista = cc.listarClienteEstado("A");
					migrarExcel(lista, user.getNomUs());
				}
			}
			else {
				if(convenio != null) { 
					lista = cc.listarClienteConvenio(convenio);
					migrarExcel(lista, user.getNomUs());
				}
				else { 
					lista = cc.listarCliente();
					migrarExcel(lista, user.getNomUs());
				}
			}
			String rta = "La migración se realizo Correctamente";
			request.getSession().setAttribute("msj", rta);
			response.sendRedirect(urlListarSocio);
		}
		catch(ApplicationException e){ e.printStackTrace(); }
	}
	
	
	private void migrarExcel(ArrayList<Cliente> lista, String usuario) throws IOException {
		// ESTABLEZCO LAS VARIABLES QUE VOY A USAR
		Date fecLibro = new Date();
		//Formateo las fechas que voy a usar
		SimpleDateFormat fl = new SimpleDateFormat("ddMMyyyy");
		SimpleDateFormat fi = new SimpleDateFormat("dd/MM/yyyy");
		
		String titulo = "Mutual AMEMA";
		String stitulo = "Reporte de Socios de la Mutual AMEMA a la fecha de: "+fi.format(fecLibro);
		String user = "Usuario: "+usuario;
		String fec = "Fecha emisión: "+fi.format(fecLibro);
		
		String ttabla[] = {"Nro Socio", "Apellido y Nombre", "Domicilio", "Localidad", "Cod. Postal", "Telefono 1", 
				"Telefono 2", "Tpo DNI", "Nro Doc", "Fecha Nacimiento","E-Mail","Convenio", "Empresa", "Centro de Costo", "Desc. Centro de Costo",
				"Estado", "Fecha Ingreso", "Observaciones"};
		
		int cont = 12;
		
		/* CREO EL EXCEL */
		
		Workbook libro = new HSSFWorkbook(); //CREO EL LIBRO DE EXCEL 
		Sheet hoja = libro.createSheet("Listado de Socios"); //CREO LA HOJA CON EL NOMBRE QUE QUIERO
		
		/* DEFINO LA FUENTE DEL LIBRO*/
		Font ftitulo = libro.createFont(); //creo la fuente 
		ftitulo.setFontHeightInPoints((short) 16); //Tamaño de la letra
		ftitulo.setFontName("Arial"); //Letra
		ftitulo.setBold(true); //Negrita
		ftitulo.setItalic(true); //Cursiva
		ftitulo.setUnderline((byte) 1); //Subrayado
		
		CellStyle estilo1 = libro.createCellStyle();
		estilo1.setAlignment(HorizontalAlignment.CENTER);
		estilo1.setFont(ftitulo);
		
		Font fstitulo = libro.createFont(); //creo la fuente 
		fstitulo.setFontHeightInPoints((short) 10); //Tamaño de la letra
		fstitulo.setFontName("Arial"); //Letra
		fstitulo.setItalic(true); //Cursiva
		fstitulo.setBold(true);
		
		CellStyle estilo2 = libro.createCellStyle();
		estilo2.setFont(fstitulo);
		
		CellStyle estilo3 = libro.createCellStyle();
		estilo3.setFont(fstitulo);
		estilo3.setBorderBottom(BorderStyle.MEDIUM);
		estilo3.setBottomBorderColor(IndexedColors.BLUE.getIndex());
		
		
		/* INGRESO LA IMAGEN */
		InputStream is = new FileInputStream("C:\\AMEMA\\recursos//escudo_AMEMA.png");
		byte[] bytes = IOUtils.toByteArray(is);
		int pictureIdx = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
		is.close();
		
		CreationHelper helper = libro.getCreationHelper();
		
		Drawing<?> drawing = hoja.createDrawingPatriarch();
		
		ClientAnchor anchor = helper.createClientAnchor();
		
		anchor.setCol1(0);
		anchor.setRow1(0);
		
		Picture pic = drawing.createPicture(anchor, pictureIdx);
		pic.resize();
		
		/* DEFINO LOS RENGLONES Y LAS CELDAS */
		
		Row rtitulo = hoja.createRow(2); //CREO UN RENGLON
		Cell ctitulo = rtitulo.createCell(3); //CREO UNA CELDA
		ctitulo.setCellValue(titulo); //AGREGO UN TEXTO A LA CELDA
		ctitulo.setCellStyle(estilo1); // Le agrego estilo a las celdas
		
		Row rusuario = hoja.createRow(0);
		Cell cusuario = rusuario.createCell(16);
		cusuario.setCellValue(user);
		cusuario.setCellStyle(estilo2);
		
		Row rfecha = hoja.createRow(1);
		Cell cfecha = rfecha.createCell(16);
		cfecha.setCellValue(fec);
		cfecha.setCellStyle(estilo2);
		
		hoja.addMergedRegion(new CellRangeAddress(8, 8, 0, 18)); // PRUEBA DE MERGEAR CELDAS
		Row rstitulo = hoja.createRow(8);
		Cell cstitulo = rstitulo.createCell(0);
		cstitulo.setCellValue(stitulo);
		cstitulo.setCellStyle(estilo1);
		
		// LA TABLA TIENE 19 COLUMNAS.
		
		//Cargo los titulos
		Row rttabla = hoja.createRow(11);
		int k = ttabla.length;
		for(int i = 0; i < k; i++) {
			Cell cttabla = rttabla.createCell(i);
			cttabla.setCellValue(ttabla[i]);
			cttabla.setCellStyle(estilo3);
		}
		//cargo la data del listado
			
		for(Cliente c : lista) {
			Row rdata = hoja.createRow(cont);
			rdata.createCell(0).setCellValue(c.getCODCLI());
			rdata.createCell(1).setCellValue(c.getNOMCLI());
			rdata.createCell(2).setCellValue(c.getDOMCLI());
			rdata.createCell(3).setCellValue(c.getLOCCLI());
			rdata.createCell(4).setCellValue(c.getCODPOS());
			rdata.createCell(5).setCellValue(c.getTELCLI_1());
			rdata.createCell(6).setCellValue(c.getTELCLI_2());
			rdata.createCell(7).setCellValue(c.getTIPO_DOC());
			rdata.createCell(8).setCellValue(c.getCUITCLI());
			rdata.createCell(9).setCellValue(fl.format(c.getFECHA_NAC()));
			rdata.createCell(10).setCellValue(c.getE_MAIL());
			rdata.createCell(11).setCellValue(c.getCCOND());
			rdata.createCell(12).setCellValue(c.getCPCCP());
			rdata.createCell(13).setCellValue(c.getCONTACTO());
			rdata.createCell(14).setCellValue(c.getCONTACTO2());
			rdata.createCell(15).setCellValue(c.getCOM_IND());
			rdata.createCell(16).setCellValue(fl.format(c.getFECHA_ING()));
			rdata.createCell(17).setCellValue("S/Observaciones");
			cont++;
		}
		
		
		Font vgvsr = libro.createFont(); //creo la fuente 
		vgvsr.setFontHeightInPoints((short) 12); //Tamaño de la letra
		vgvsr.setFontName("Arial"); //Letra
		vgvsr.setItalic(true); //Cursiva
		vgvsr.setBold(true);
		vgvsr.setColor(IndexedColors.ROYAL_BLUE.getIndex());
		
		CellStyle estilo = libro.createCellStyle();
		estilo.setFont(vgvsr);
		Row emp = hoja.createRow(cont+3);
		Cell cemp = emp.createCell(0);
		cemp.setCellValue("Desarrollado por VEGVISIR Soluciones informáticas");
		cemp.setCellStyle(estilo);
		try  {

			OutputStream fileOut = new FileOutputStream(new File("C://AMEMA//ListadoSociosAMEMA"+fl.format(fecLibro)+".xls"));
			libro.write(fileOut);

		    fileOut.close();
		    fileOut.flush();
			libro.close();

		}
		catch(IOException e) {e.printStackTrace();}
		
	}
}
