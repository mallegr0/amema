package reportes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class HeaderFooterPageEvent extends PdfPageEventHelper {
	private PdfTemplate tmp;
	private Image img;
	private String user;
	private String url = "C:\\Users\\tute\\git\\amema\\amema\\amema\\static\\imgenes\\escudo_AMEMA.png";
	
	
	public HeaderFooterPageEvent(String nombre) {
		this.user = nombre;
	}
	
	public void onOpenDocument(PdfWriter writer, Document documento) {
		tmp = writer.getDirectContent().createTemplate(30, 16);
		try {
			img = Image.getInstance(tmp);
			img.setRole(PdfName.ARTIFACT);
		}catch(DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}
	
	@Override
	public void onEndPage(PdfWriter writer, Document documento) {
		addHeader(writer);
		addfooter(writer);
	}
	
	private void addHeader(PdfWriter writer) {
		PdfPTable header = new PdfPTable(2);
		String nombre = "Asociación Mutual de Empleados Municipales y Adherentes  de Rosario";
		String matricula = "Mat. Inaes. SF 1218";
		try {
			// Set Default
			header.setWidths(new int[] {2,24});
			header.setTotalWidth(527);
			header.setLockedWidth(true);
			header.getDefaultCell().setFixedHeight(40);
			header.getDefaultCell().setBorder(Rectangle.BOTTOM);
			header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
			
			// Add image
			Image logo = Image.getInstance(url);
			header.addCell(logo);
			
			// Add text
			PdfPCell text = new PdfPCell();
			text.setPaddingBottom(15);
			text.setPaddingLeft(10);
			text.setBorder(Rectangle.BOTTOM);
			text.setBorderColor(BaseColor.LIGHT_GRAY);
			text.addElement(new Phrase(nombre, new Font(Font.FontFamily.HELVETICA,12, Font.BOLD)));
			text.addElement(new Phrase(matricula, new Font(Font.FontFamily.HELVETICA,10, Font.NORMAL, BaseColor.GRAY)));
			header.addCell(text);
			
			// Write content
			header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
		}
		catch(DocumentException de) { throw new ExceptionConverter(de);}
		catch(MalformedURLException de) { throw new ExceptionConverter(de);}
		catch(IOException de) { throw new ExceptionConverter(de);}
	}
	
	private void addfooter(PdfWriter writer) {
		PdfPTable footer = new PdfPTable(3);
		try {
			// Set default
			footer.setWidths(new int[] {24,10,10});
			footer.setTotalWidth(527);
			footer.setLockedWidth(true);
			footer.getDefaultCell().setFixedHeight(40);
			footer.getDefaultCell().setBorder(Rectangle.TOP);
			footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
			
			// Add copyright
			footer.addCell(new Phrase("\u00A9 Vegvisir", new Font(Font.FontFamily.HELVETICA,12,Font.BOLD)));
			
			//Add user
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			footer.addCell(new Phrase("Usuario: "+ this.user, new Font(Font.FontFamily.HELVETICA,8)));
			
			// Add Date
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			Date fecha = new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			footer.addCell(new Phrase("Fecha: "+format.format(fecha), new Font(Font.FontFamily.HELVETICA,8)));
			
			// Add footer con date and name
			PdfPCell pie = new PdfPCell();
			pie.setBorder(Rectangle.TOP);
			pie.setBorderColor(BaseColor.LIGHT_GRAY);
			footer.addCell(pie);
			
			// Write page
			PdfContentByte canvas = writer.getDirectContent();
			canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
			footer.writeSelectedRows(0, -1, 34, 50, canvas);
			canvas.endMarkedContentSequence();
		}
		catch(DocumentException de) { throw new ExceptionConverter(de);}
	}
	
	public void OnClosedDocument(PdfWriter writer, Document documento) {
		int totalLength = String.valueOf(writer.getPageNumber()).length();
		int totalWidth = totalLength * 5;
		ColumnText.showTextAligned(tmp, Element.ALIGN_RIGHT, new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)), totalWidth, 6, 0);
	}

}

