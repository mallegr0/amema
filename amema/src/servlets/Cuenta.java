package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlCliente;
import controladores.CtrlConvenio;
import controladores.CtrlCtactecliente;
import entidades.Cliente;
import entidades.Convenio;
import util.ApplicationException;

/**
 * Servlet implementation class Cuentas
 */
@WebServlet(urlPatterns = {"/Cuenta"})
public class Cuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String urlCtacte = "/amema/views/ctactes.jsp";
	private CtrlCliente cCliente = null;
	private CtrlConvenio cConvenio = null;
	private CtrlCtactecliente cCuentas = null;

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cuenta() { super(); }

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
				try { request.getSession().setAttribute("socios", listarSocios(request.getParameter("dato"))); }
				catch(ApplicationException e) { e.printStackTrace(); }
			}
			if(request.getParameter("doc") != null) {
				try { request.getSession().setAttribute("doc", buscarSocio(request.getParameter("dato"))); }
				catch(ApplicationException e) { e.printStackTrace(); }
			}
		}
		if(request.getParameter("evento_buscar2") != null) {
			try { request.getSession().setAttribute("doc", consultaSocio(request.getParameter("socio"))); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		if(request.getParameter("evento_buscar3") != null) {
			try {
				cCuentas = new CtrlCtactecliente();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date fec = (java.sql.Date) format.parse(request.getParameter("fecha"));
				System.out.println(fec);
				String socio = request.getParameter("socio");
				request.getSession().setAttribute("movimientos", cCuentas.listarCtaCtePorSocioYFecha(socio, fec));
			}
			catch (ApplicationException |ParseException e) { e.printStackTrace();}
		}
		
		response.sendRedirect(urlCtacte);
	}
	
	
	private ArrayList<Cliente> listarSocios(String dato) throws ApplicationException {
		cCliente = new CtrlCliente();
		return cCliente.listarClientePorNombre(dato);
	}
	
	private Cliente buscarSocio(String dato) throws ApplicationException {
		cCliente = new CtrlCliente();
		Cliente c = new Cliente();
		c = cCliente.consultaClientePorDNI(dato);
		if(c != null) {
			return limpiarDatos(c);
		}
		else { return null; }
	}
	
	private Cliente consultaSocio(String dato) throws ApplicationException {
		cCliente = new CtrlCliente();
		return limpiarDatos(cCliente.consultaCliente(dato));
	}
	
	private Cliente limpiarDatos(Cliente c) throws ApplicationException {
		if(c.getNOMCLI() == null) { c.setNOMCLI("S/Nombre"); }
		if(c.getDOMCLI() == null) { c.setDOMCLI("S/Domicilio"); }
		if(c.getTELCLI_1() == null) { c.setTELCLI_1("S/Nro Tel"); }
		if(c.getTELCLI_2() == null) { c.setTELCLI_2("S/Nro Tel"); }
		if(c.getDNRP() == null) { c.setDNRP("S/Legajo"); }
		if(c.getCONTACTO() == null) { c.setCONTACTO("S/CC"); }
		if(c.getCONTACTO2() == null) { c.setCONTACTO2("S/Desc CC"); }
		if(c.getTIPO_DOC() == null) { c.setTIPO_DOC("S/Tpo Doc"); }
		if(c.getCUITCLI() == null) { c.setCUITCLI("S/Nro Doc"); }
		if(c.getOBSCLI() == null) { c.setOBSCLI("S/Obs"); }
		
		cConvenio = new CtrlConvenio();
		String conv = c.getCCOND();
		c.setCCOND(conv+" - "+cConvenio.buscaDescripcion(conv));
		
		return c;
	}
}
