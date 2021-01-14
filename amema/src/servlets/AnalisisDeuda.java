package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

import controladores.CtrlCliente;
import controladores.CtrlVentasM;
import controladores.GeneroPDFAnalisisDeuda;
import entidades.Cliente;
import entidades.Usuario;
import entidades.VentasM;
import util.ApplicationException;

/**
 * Servlet implementation class AnalisisDeuda
 */
@WebServlet(urlPatterns = {"/AnalisisDeuda"})
public class AnalisisDeuda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String urlAnalisisDeuda ="/amema/views/analisisDeuda.jsp";
	private CtrlCliente cCliente = null; 
	private CtrlVentasM cVentasM = null; 
	private GeneroPDFAnalisisDeuda pdf = null;  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnalisisDeuda() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("migrarExcel") != null) {
			try { migrarExcel(request); } 
			catch (ApplicationException e) { e.printStackTrace(); } 
		}
		if(request.getParameter("imprimirPDF") != null) {
			try { imprimirPDF(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		response.sendRedirect(urlAnalisisDeuda);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("evento_buscarSocio") != null) {
			try {
				buscarDatosSocio(request);
				response.sendRedirect(urlAnalisisDeuda);
			}
			catch(IOException | ApplicationException e) { e.printStackTrace(); }
		}
	}
	
	
	/*
	 * 
	 * METODOS PRIMARIOS
	 * 
	 */
	
	private void buscarDatosSocio(HttpServletRequest req) throws ApplicationException {
		
		//Declaros las variables que voy a usar
		
		//variables del form.
		String codcli = req.getParameter("select");
		
		//Variables 
		double subtotal = 0; 
		double acuenta = 0; 
		
		//Controladores
		cCliente = new CtrlCliente();
		
		//Entidades
		Cliente cli = null; 
		
		//Arrays
		ArrayList<VentasM> deudasCliente = new ArrayList<VentasM>();
		
		//Recupero el legajo y el nombre y apellido para poner al principio de la tabla
		cli = cCliente.consultaCliente(codcli);
		
		//Recupero las deudas del socio en particular
		deudasCliente = analisisDeudaCliente(codcli); 
		
		//Calculo los importes que voy a usar en el form
		for(VentasM v: deudasCliente) {
			subtotal += v.getSUBTOTAL();
			acuenta += v.getA_CUENTA();
		}
		
		//Asigno los datos que voy a mostrar
		req.getSession().setAttribute("cliente", cli.getDNRP()+" - "+cli.getNOMCLI());
		req.getSession().setAttribute("datos", deudasCliente);
		req.getSession().setAttribute("subtotal", Double.toString(subtotal));
		req.getSession().setAttribute("acuenta", Double.toString(acuenta));
		
		//Finalizo todas las variables que ya no uso
		cCliente = null;
		cVentasM = null; 
		cli = null; 
	}
	
	private void migrarExcel(HttpServletRequest req) throws ApplicationException, IOException {
		// Declaro las variables
		
		//Recupero los datos del form
		String socio = req.getParameter("cliente");
		Usuario user = (Usuario) req.getSession().getAttribute("usuarioActivo");
		
		//Declaro las variables que voy a usar
		String codcli; 
		String msj; 
		
		//Controladores
		cCliente = new CtrlCliente();
		
		//Arrays
		ArrayList<VentasM> deudaCliente = new ArrayList<>();
		
		//FIN VARIABLES
		
		//Recupero codcli del cliente seleccionado
		String[] partes = socio.split(" - ");
		codcli = cCliente.buscoCodigo(partes[0]);
		
		//Recupero las deudas que tiene para hacer el analisis
		deudaCliente = analisisDeudaCliente(codcli);
		
		if(generoExcel(deudaCliente, codcli, user.getLogIn()) == true) { msj = "siExcel"; } 
		else { msj = "noExcel"; }
		
		req.getSession().setAttribute("msj", msj);
	}
	
	private void imprimirPDF(HttpServletRequest req, HttpServletResponse res) throws ApplicationException {
		// Declaro las variables
		
				//Recupero los datos del form
				String socio = req.getParameter("cliente");
				Usuario user = (Usuario) req.getSession().getAttribute("usuarioActivo");
				
				//Declaro las variables que voy a usar
				String codcli; 
				String msj; 
				
				//Controladores
				cCliente = new CtrlCliente();
				pdf = new GeneroPDFAnalisisDeuda();
				
				//Arrays
				ArrayList<VentasM> deudaCliente = new ArrayList<>();
				
				//FIN VARIABLES
				
				//Recupero codcli del cliente seleccionado
				String[] partes = socio.split(" - ");
				codcli = cCliente.buscoCodigo(partes[0]);
				
				//Recupero las deudas que tiene para hacer el analisis
				deudaCliente = analisisDeudaCliente(codcli);
				
				if(pdf.generoPDF(deudaCliente, codcli, user.getLogIn(), res)) { msj = "siPDF"; } 
				else { msj = "noPDF"; }
				
				req.getSession().setAttribute("msj", msj);
		
		
	}
	
	//   ---   METODOS SECUNDARIOS  --- 
	
	private ArrayList<VentasM> analisisDeudaCliente(String codcli) throws ApplicationException {
		// Constructores
		cVentasM = new CtrlVentasM(); 
		
		return cVentasM.listarDeudasPorSocio(codcli);
	}
	
	private boolean generoExcel(ArrayList<VentasM> deudas, String codcli, String usuario) throws ApplicationException, IOException {
		//Declaro las variables que voy a usar
		// Variables
		boolean r = true;
		Date fecLibro = new Date(); 
		double st = 0; 
		
		
		//Variables de formateo
		SimpleDateFormat fl = new SimpleDateFormat("ddMMyyyy"); //formato de fecha para el nombre del archivo
		SimpleDateFormat fi = new SimpleDateFormat("dd/MM/yyyy"); //formato de fecha para dentro del excel
		DecimalFormat df = new DecimalFormat("#0.00");
		
		//Controladores
		cCliente = new CtrlCliente();
		
		//Recupero la data del cliente
		Cliente c = cCliente.consultaCliente(codcli);
		
		//arays
		ArrayList<VentasM> deudasFecha = new ArrayList<>();
		ArrayList<VentasM> resto = new ArrayList<>();
		
		//Divido las deudas para hacer los calculos pertinentes
		for(VentasM v: deudas) {
			if(v.getFMOV().before(fecLibro) || v.getFMOV().equals(fecLibro)) { 
				deudasFecha.add(v);
				st += v.getSUBTOTAL();
			}
			else { resto.add(v); }
		}
		
		//Genero el excel
		
		//Creo los titulos del excel
		String titulo = "Mutual AMEMA";
		String stitulo = "Comprobantes de Deuda de "+c.getCODCLI()+" - "+c.getNOMCLI()+" a la fecha de: "+fi.format(fecLibro);
		String user = "Usuario: "+usuario;
		String fec = "Fecha emisión: "+fi.format(fecLibro);
		String ttabla[] = {"Comprobantes", "Fecha", "Importe Total", "Importe Adeudado"};
				
		int cont = 12;
				
		Workbook libro = new HSSFWorkbook(); //CREO EL LIBRO DE EXCEL 
		Sheet hoja = libro.createSheet("Analisis de Deuda"); //CREO LA HOJA CON EL NOMBRE QUE QUIERO
				
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
		
		CellStyle estilo4 = libro.createCellStyle();
		estilo4.setFont(fstitulo);
		estilo4.setAlignment(HorizontalAlignment.RIGHT);
		
		CellStyle estilo5 = libro.createCellStyle();
		estilo5.setFont(fstitulo);
		estilo5.setBorderTop(BorderStyle.MEDIUM);
		estilo5.setTopBorderColor(IndexedColors.BLUE.getIndex());
				
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
		Cell cusuario = rusuario.createCell(8);
		cusuario.setCellValue(user);
		cusuario.setCellStyle(estilo2);
				
		Row rfecha = hoja.createRow(1);
		Cell cfecha = rfecha.createCell(8);
		cfecha.setCellValue(fec);
		cfecha.setCellStyle(estilo2);
				
		hoja.addMergedRegion(new CellRangeAddress(8, 8, 0, 8)); // PRUEBA DE MERGEAR CELDAS
		Row rstitulo = hoja.createRow(8);
		Cell cstitulo = rstitulo.createCell(0);
		cstitulo.setCellValue(stitulo);
		cstitulo.setCellStyle(estilo1);
			
				
		//Cargo los titulos
		hoja.addMergedRegion(new CellRangeAddress(11,11,0,2));
		Row rttabla = hoja.createRow(11);
		Cell cttabla0 = rttabla.createCell(0);
		cttabla0.setCellValue(ttabla[0]);
		cttabla0.setCellStyle(estilo3);
		Cell cttabla1 = rttabla.createCell(1);
		cttabla1.setCellStyle(estilo3);
		Cell cttabla2 = rttabla.createCell(2);
		cttabla2.setCellStyle(estilo3);
		Cell cttabla3 = rttabla.createCell(3);
		cttabla3.setCellValue(ttabla[1]);
		cttabla3.setCellStyle(estilo3);
		Cell cttabla4 = rttabla.createCell(4);
		cttabla4.setCellValue(ttabla[2]);
		cttabla4.setCellStyle(estilo3);
		Cell cttabla5 = rttabla.createCell(5);
		cttabla5.setCellValue(ttabla[3]);
		cttabla5.setCellStyle(estilo3);
		
		
		//cargo la data del listado de deudas que esta antes de la fecha de hoy
				
		for(VentasM v : deudasFecha) {
			Row rdata = hoja.createRow(cont);
			hoja.addMergedRegion(new CellRangeAddress(cont,cont,0,2));
			rdata.createCell(0).setCellValue(v.getNCOMP()+" "+v.getOBSERV());
			rdata.createCell(3).setCellValue(fi.format(v.getFMOV()));
			rdata.createCell(4).setCellValue("$"+df.format(v.getSUBTOTAL()));
			rdata.createCell(5).setCellValue("$"+df.format(v.getSUBTOTAL()-v.getA_CUENTA()));
			cont++;
		}
		
		//Muestro el los totales
		Row rtotales = hoja.createRow(cont);
		hoja.addMergedRegion(new CellRangeAddress(cont,cont,0,4));
		Cell textTotales = rtotales.createCell(0);
		textTotales.setCellValue("Deudas a la fecha de: "+fi.format(fecLibro));
		textTotales.setCellStyle(estilo4);
		Cell subTotal = rtotales.createCell(5);
		subTotal.setCellValue("$"+df.format(st));
		subTotal.setCellStyle(estilo5);
		
		//aumento el contador para que no me pise el resto
		cont += cont + 3; 
		
		//cargo el resto de los comprobantes
		if(resto != null) {
			for(VentasM v : resto) {
				Row rdata = hoja.createRow(cont);
				rdata.createCell(0).setCellValue(v.getNCOMP()+" "+v.getOBSERV());
				rdata.createCell(1).setCellValue(" ");
				rdata.createCell(2).setCellValue(" ");
				rdata.createCell(3).setCellValue(fi.format(v.getFMOV()));
				rdata.createCell(4).setCellValue("$"+df.format(v.getSUBTOTAL()));
				rdata.createCell(5).setCellValue("$"+df.format(v.getSUBTOTAL()-v.getA_CUENTA()));
				cont++;
			}
		}
		
		
		Font vgvsr = libro.createFont(); //creo la fuente 
		vgvsr.setFontHeightInPoints((short) 12); //Tamaño de la letra
		vgvsr.setFontName("Arial"); //Letra
		vgvsr.setItalic(true); //Cursiva
		vgvsr.setBold(true);
		vgvsr.setColor(IndexedColors.ROYAL_BLUE.getIndex());
				
		CellStyle estilo = libro.createCellStyle();
		estilo.setFont(vgvsr);
		Row emp = hoja.createRow(cont+2);
		Cell cemp = emp.createCell(0);
		cemp.setCellValue("Desarrollado por VEGVISIR Soluciones informáticas");
		cemp.setCellStyle(estilo);
		try  {
			OutputStream fileOut = new FileOutputStream(new File("C://AMEMA//Analisis de Deuda de "+c.getNOMCLI()+" "+fl.format(fecLibro)+".xls"));
			libro.write(fileOut);

			fileOut.close();
			fileOut.flush();
			libro.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			r = false;}
		
		return r; 
	}
}
