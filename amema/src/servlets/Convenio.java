package servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlConvenio;
import util.ApplicationException;

/**
 * Servlet implementation class Convenio
 */
@WebServlet(urlPatterns = {"/Convenio"})
public class Convenio extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private String urlConvenio = "/amema/views/convenio.jsp"; 
	private CtrlConvenio cConvenio = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Convenio() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("evento_altaConvenio") != null) {
			try { altaConvenio(request); }
			catch (ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_bajaConvenio") != null) {
			try { bajaConvenio(request); }
			catch (ApplicationException e) { e.printStackTrace(); }
		}
		
		if(request.getParameter("evento_modificaConvenio") != null) {
			try { modificaConvenio(request); }
			catch (ApplicationException e) { e.printStackTrace(); }
		}
		
		//Vuelvo a la pagina principal con el response
		response.sendRedirect(urlConvenio);
	}
	
	// METODOS PRIMARIOS
	
	private void altaConvenio(HttpServletRequest req) throws ApplicationException {
		//variables
		String msj; 
		
		//Variables del FORM
		String codigo = req.getParameter("codConvenio");
		String descripcion = req.getParameter("descConvenio");
		String concepto1 = req.getParameter("cpto1");
		double tope1 = Double.parseDouble(req.getParameter("tope1"));
		String concepto2 = req.getParameter("cpto2");
		double tope2 = Double.parseDouble(req.getParameter("tope2"));
		String concepto3 = req.getParameter("cpto3");
		double tope3 = Double.parseDouble(req.getParameter("tope3"));
		String ginteres = req.getParameter("ginteres").substring(0,1);
		double tasa = Double.parseDouble(req.getParameter("tasa"));
		String producto = req.getParameter("producto");
		String modo = req.getParameter("modo"); 
		
		//Controlador
		cConvenio = new CtrlConvenio();
		
		//Entidad
		entidades.Convenio conv = new entidades.Convenio(); 
		
		//cargo los datos a la entidad
		conv.setCCOND(codigo);
		conv.setDESCOND(descripcion);
		conv.setConc1(concepto1);
		conv.setConc2(concepto2);
		conv.setConc3(concepto3);
		conv.setTope1(tope1);
		conv.setTope2(tope2);
		conv.setTope3(tope3);
		conv.setGenInt(ginteres.toUpperCase());
		conv.setTasaInt(tasa);
		conv.setCODARTINT(producto);
		conv.setIngCobro(modo);
		
		//cargo el convenio, si esta todo bien asigno OK a msj, sino asigno NO
		if(cConvenio.altaConvenio(conv) == true) { msj = "siAlta"; }
		else { msj = "noAlta"; }
		
		//Finalizo las variables que use
		cConvenio = null; 
		conv = null; 
		
		req.getSession().setAttribute("msj", msj);
	}
	
	private void bajaConvenio(HttpServletRequest req) throws ApplicationException {
		//Variables 
		String msj; 
		
		//Controlador
		cConvenio = new CtrlConvenio();
		
		//Variable form
		String codigo = req.getParameter("codConvenio"); 
		
		if(cConvenio.bajaConvenioPorCodigo(codigo) == true) { msj = "siBaja"; }
		else { msj = "noBaja"; }
		
		//Finalizo las variables que ya no uso
		cConvenio = null; 
		
		req.getSession().setAttribute("msj", msj);
	}
	
	private void modificaConvenio(HttpServletRequest req) throws ApplicationException {
		// Variables
		String msj; 
		
		//Variables del formulario 
		String codigo = req.getParameter("codConvenio");
		String descripcion = req.getParameter("descConvenio");
		String concepto1 = req.getParameter("cpto1");
		double tope1 = Double.parseDouble(req.getParameter("tope1"));
		String concepto2 = req.getParameter("cpto2");
		double tope2 = Double.parseDouble(req.getParameter("tope2"));
		String concepto3 = req.getParameter("cpto3");
		double tope3 = Double.parseDouble(req.getParameter("tope3"));
		String ginteres = req.getParameter("ginteres").substring(0,1);
		double tasa = Double.parseDouble(req.getParameter("tasa"));
		String producto = req.getParameter("producto");
		String modo = req.getParameter("modo"); 
		
		//Controlador
		cConvenio = new CtrlConvenio();
		
		//Entidad
		entidades.Convenio conv = new entidades.Convenio(); 
		
		//cargo los datos a la entidad
		conv.setCCOND(codigo);
		conv.setDESCOND(descripcion);
		conv.setConc1(concepto1);
		conv.setConc2(concepto2);
		conv.setConc3(concepto3);
		conv.setTope1(tope1);
		conv.setTope2(tope2);
		conv.setTope3(tope3);
		conv.setGenInt(ginteres.toUpperCase());
		conv.setTasaInt(tasa);
		conv.setCODARTINT(producto);
		conv.setIngCobro(modo);
		
		if(cConvenio.modificaConvenio(conv) == true) { msj = "siModifica"; }
		else { msj = "noModifica"; }
		
		//Finalizo las variables que no uso
		cConvenio = null; 
		
		//Asigno la variable para el mensaje de error 
		req.getSession().setAttribute("msj", msj);
	}
}
