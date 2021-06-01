package controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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


import data.DataCtactecliente;
import entidades.Cliente;
import entidades.CtacteGral;
import entidades.Ctactecliente;
import entidades.TpoComprobante;
import util.ApplicationException;

public class CtrlCtactecliente {
	/* CONSTRUCTOR */
	public CtrlCtactecliente() {}

	/* VARIABLES */
	DataCtactecliente dc = null;
	
	/* METODOS */
	public boolean altaCtaCte(Ctactecliente c) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.altaCtaCte(c);
	}
	
	public boolean bajaCtaCte(String cod, Date fec) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.bajaCtaCte(cod, fec);
	}
	
	public boolean bajaCtaCtePorSocio(String cod) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.bajaCtaCtePorSocio(cod);
	}
	
	public boolean bajaCtaCtePorCompOrig(String comprobante) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.bajaCtaCtePorCompOrig(comprobante);
	}
	
	public Boolean bajaCtaCtePorComprobante(String comprobante) throws ApplicationException {
		dc = new DataCtactecliente(); 
		return dc.bajaCtaCtePorComprobante(comprobante);
	}
	
	public boolean modificaCtaCte(Ctactecliente c) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.modificaCtaCte(c);
	}
	
	public Ctactecliente consultarCtaCte(String cod, Date fec) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.consultarCtaCte(cod, fec);
	}
	
	public Ctactecliente consultarComprobanteCta(String cod) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.consultarComprobanteCta(cod);
	}
	
	public ArrayList<Ctactecliente> listarCtaCtePorSocioYFecha(String cod, Date fec) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.listarCtaCtePorSocioYFec(cod, fec);
	}
	
	public ArrayList<Ctactecliente> listarAnteriorCtaCtePorSocioYFec(String cod) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.listarAnteriorCtaCtePorSocioYFec(cod);
	}
	
	public ArrayList<Ctactecliente> listarCtaCtePorSocio(String cod) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.listarCtaCtePorSocio(cod);
	}
	
	public ArrayList<Ctactecliente> listarCtaCte() throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.listarCtaCte();
	}
	
	public boolean contarTabla() throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.contarTabla();
	}
	
	public int contarCtaSocio(String cod) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.contarCtaSocio(cod);
	}
	
	public void migrarExcel(String codigo, Date fecha, String usuario) throws ApplicationException, ParseException, IOException {
		// ESTABLEZCO LAS VARIABLES QUE VOY A USAR
		Date fecLibro = new Date();
		//Formateo las fechas que voy a usar
		SimpleDateFormat fl = new SimpleDateFormat("ddMMyyyy");
		SimpleDateFormat fi = new SimpleDateFormat("dd/MM/yyyy");
		
		
		// Recupero la información del socio
		CtrlCliente cCliente = new CtrlCliente();
		Cliente c = cCliente.consultaCliente(codigo);
		
		String titulo = "Mutual AMEMA";
		String stitulo = "Reporte de movimientos del socio "+c.getNOMCLI()+" a la fecha de: "+fi.format(fecLibro);
		String user = "Usuario: "+usuario;
		String fec = "Fecha emisión: "+fi.format(fecLibro);
		
		String ttabla[] = {"Fecha Movimiento", "Concepto", "Origen del Movimiento", "Debe", "Haber", "Saldo"};
		
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
		
		for(CtacteGral cta : cargaTabla(codigo, fecha)) {
			Row rdata = hoja.createRow(cont);
			rdata.createCell(0).setCellValue(cta.getFMOV());
			rdata.createCell(1).setCellValue(cta.getTMOV());
			rdata.createCell(2).setCellValue(cta.getNCOMP());
			rdata.createCell(3).setCellValue(cta.getDEBE());
			rdata.createCell(4).setCellValue(cta.getHABER());
			rdata.createCell(5).setCellValue(cta.getSALDO());
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

			OutputStream fileOut = new FileOutputStream(new File("C://AMEMA//Listado de Movimientos "+fl.format(fecLibro)+".xls"));
			libro.write(fileOut);

		    fileOut.close();
		    fileOut.flush();
			libro.close();
			System.gc();
		}
		catch(IOException e) {e.printStackTrace();}
		
	}
	
	private ArrayList<CtacteGral> cargaTabla(String codigo, Date fecha) throws ApplicationException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
		double saldo = 0.0;
		ArrayList<Ctactecliente> lmov = new ArrayList<>();
		ArrayList<CtacteGral> mov = new ArrayList<>();
		
		CtrlCtactecliente cCuentas = new CtrlCtactecliente();
		CtrlTpoComprobante cTComprobante = new CtrlTpoComprobante();

		lmov = cCuentas.listarCtaCtePorSocioYFecha(codigo, fecha);
		
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
			r.setSALDO(saldo);
			mov.add(r);
			r = null;
		}
		cCuentas = null; 
		cTComprobante = null; 
		lmov.removeAll(lmov);
		return mov;
	}
}

