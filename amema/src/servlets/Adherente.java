package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlCliente;
import controladores.CtrlConvenio;
import entidades.Cliente;
import util.ApplicationException;

/**
 * Servlet implementation class Adherente
 */
@WebServlet(urlPatterns = {"/Adherente"})
public class Adherente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String urlAdherente = "/amema/views/adherentes.jsp";
	private CtrlCliente cc = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adherente() { super(); }

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
		if(request.getParameter("evento_buscar1") != null) {
			if(request.getParameter("socio") != null) {
				try { request.getSession().setAttribute("lista", buscarPorNombre(request.getParameter("dato"))); }
				catch(ApplicationException e) { e.printStackTrace(); }
				}
			if(request.getParameter("doc") != null) { 
				try { request.getSession().setAttribute("socio", buscarPorDocumento(request.getParameter("dato"))); }
				catch(ApplicationException e) { e.printStackTrace(); }
			}
		}
		if(request.getParameter("evento_buscar2") != null) {
			try { request.getSession().setAttribute("socio", buscarSocio(request.getParameter("socio"))); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		
		
		
		response.sendRedirect(urlAdherente);
	}
	
	
	
	
	
	private ArrayList<Cliente> buscarPorNombre (String dato) throws ApplicationException {
		cc = new CtrlCliente();
		return cc.listarClientePorNombre(dato);	
	}
	
	private Cliente buscarPorDocumento (String dato) throws ApplicationException {
		cc = new CtrlCliente();
		return limpiarDatos(cc.consultaClientePorDNI(dato));
	}

	private Cliente buscarSocio(String dato) throws ApplicationException {
		cc = new CtrlCliente();
		return limpiarDatos(cc.consultaCliente(dato));
	}
	
	private Cliente limpiarDatos(Cliente c) throws ApplicationException {
		if(c.getTELCLI_1() == null) { c.setTELCLI_1("S/Nro"); }
		if(c.getTELCLI_2() == null) { c.setTELCLI_2("S/Nro"); }
		if(c.getOBSCLI() == null) { c.setOBSCLI("S/Obs"); }
		CtrlConvenio cc = new CtrlConvenio();
		String nro = c.getCCOND();
		c.setCCOND(nro+" - "+cc.buscaDescripcion(nro));
		return c;
	}
}
