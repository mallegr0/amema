package controladores;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import entidades.Cliente;
import entidades.VentasM;
import reportes.HeaderFooterPageEvent;
import util.ApplicationException;

public class GeneroPDFAnalisisDeuda {
	
	public GeneroPDFAnalisisDeuda() {}
	
	public boolean generoPDF(ArrayList<VentasM> deudas, String codcli, String usuario, HttpServletResponse response) throws ApplicationException {
		//Declaro las variables que voy a usar
		
		//Variables de formato
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("#0.00");
		
		//Variables
		double total = 0;
		double tacuenta = 0;
		Date fecha = new Date(); 
		boolean r = true; 
		
		//Arrays
		ArrayList<VentasM> deudores = new ArrayList<>();
		ArrayList<VentasM> resto = new ArrayList<>();
		
		//Controladores
		CtrlCliente cCliente = new CtrlCliente(); 
		
		
		//Divido segun la fecha 
		for(VentasM v : deudas) {
			if(v.getFMOV().before(fecha) || v.getFMOV().equals(fecha)) {
				deudores.add(v);
				total += v.getSUBTOTAL();
				tacuenta += v.getA_CUENTA();
			}
			else { resto.add(v); }
		}
		
		//CREO EL PDF
		Document documento = new Document(PageSize.A4, 36, 36, 90, 36);

		try {
			response.setContentType("application/pdf");
			OutputStream out = response.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(documento, out);

			// Add header and footer
			HeaderFooterPageEvent event = new HeaderFooterPageEvent(usuario);
			writer.setPageEvent(event);

			// Recupero la data del Socio
			Cliente c = cCliente.consultaCliente(codcli);

			// Seteo las fuentes
			Font f1 = new Font(FontFamily.HELVETICA, 13, Font.BOLDITALIC, BaseColor.DARK_GRAY);
			Font f2 = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLUE); // Fuentes de los titulos del
																						// reporte.
			Font f3 = new Font(FontFamily.HELVETICA, 10, Font.NORMAL); // Fuente de los textos.

			Paragraph t1 = new Paragraph(new Phrase("Analisis de Deuda del socio "+c.getCODCLI()+" - "+c.getNOMCLI()+" a la fecha "+format.format(fecha), f1));
			t1.setAlignment(Element.ALIGN_CENTER);
			t1.add(Chunk.NEWLINE);
			t1.add(Chunk.NEWLINE);
			
			//Defino los titulos de la tablas
			Paragraph p1 = new Paragraph();
			p1.add(new Phrase("Comprobantes", f2));
			Paragraph p2 = new Paragraph();
			p2.add(new Phrase("Fecha", f2));
			Paragraph p3 = new Paragraph();
			p3.add(new Phrase("Importe Total", f2));
			Paragraph p4 = new Paragraph();
			p4.add(new Phrase("Importe Adeudado", f2));
			

			// Defino la tabla
			PdfPTable dp = new PdfPTable(5);
			dp.setKeepTogether(true);
			dp.setHeaderRows(1);
			dp.getDefaultCell().setBorder(Rectangle.BOTTOM);
			dp.getDefaultCell().setBorderColorBottom(BaseColor.BLUE);

			// Defino las celdas
			PdfPCell blanco = new PdfPCell(new Paragraph(" ")); // Es un blanco para crear espacio completo
			blanco.setBorder(Rectangle.NO_BORDER);
			blanco.setColspan(5);

			PdfPCell ttabla1 = new PdfPCell(p1); 
			ttabla1.setColspan(2);
			PdfPCell ttabla2 = new PdfPCell(p2);
			PdfPCell ttabla3 = new PdfPCell(p3); 
			PdfPCell ttabla4 = new PdfPCell(p4);

			// Asigno las celdas a la tabla
			dp.addCell(ttabla1);
			dp.addCell(ttabla2);
			dp.addCell(ttabla3);
			dp.addCell(ttabla4);

			//Recorro el primer deudores para genera la tabla
			for(VentasM v: deudores) {
				PdfPCell dtabla1 = new PdfPCell(new Phrase(v.getNCOMP()+" "+v.getOBSERV(),f3));
				dtabla1.setColspan(2);
				PdfPCell dtabla2 = new PdfPCell(new Phrase(format.format(v.getFMOV()),f3));
				PdfPCell dtabla3 = new PdfPCell(new Phrase("$"+df.format(v.getSUBTOTAL()),f3));
				PdfPCell dtabla4 = new PdfPCell(new Phrase("$"+df.format(v.getSUBTOTAL()-v.getA_CUENTA()),f3));
				dp.addCell(dtabla1);
				dp.addCell(dtabla2);
				dp.addCell(dtabla3);
				dp.addCell(dtabla4);
			}
			
			// muestro el total con el mensaje 
			PdfPCell ttotal = new PdfPCell(new Phrase("Total adeudado por el socio: ",f2));
			ttotal.setColspan(4);
			PdfPCell dtotal = new PdfPCell(new Phrase("$"+df.format(total-tacuenta),f2));
			dp.addCell(ttotal);
			dp.addCell(dtotal);
			dp.addCell(blanco);
			dp.addCell(blanco);
			
			//Muestro los datos del resto si los hay 
			if(!resto.isEmpty()) {
				for(VentasM v: resto) {
					PdfPCell rtabla1 = new PdfPCell(new Phrase(v.getNCOMP()+" "+v.getOBSERV(),f3));
					rtabla1.setColspan(2);
					PdfPCell rtabla2 = new PdfPCell(new Phrase(format.format(v.getFMOV()),f3));
					PdfPCell rtabla3 = new PdfPCell(new Phrase("$"+df.format(v.getSUBTOTAL()),f3));
					PdfPCell rtabla4 = new PdfPCell(new Phrase("$"+df.format(v.getSUBTOTAL()-v.getA_CUENTA()),f3));
					dp.addCell(rtabla1);
					dp.addCell(rtabla2);
					dp.addCell(rtabla3);
					dp.addCell(rtabla4);
				}
			}
			
			// Write to document
			documento.open();
			documento.add(t1);
			documento.add(dp);
		
			documento.close();

		} catch (DocumentException | ApplicationException | IOException e) {
			e.printStackTrace();
			r = false;
		}
		return r; 
	}
	
}
