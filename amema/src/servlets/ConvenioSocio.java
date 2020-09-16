package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlCliente;
import controladores.CtrlSocioConvenio;
import entidades.SocioConvenio;
import util.ApplicationException;

/**
 * Servlet implementation class ConvenioSocio
 */
@WebServlet(urlPatterns = {"/ConvenioSocio"})
public class ConvenioSocio extends HttpServlet {
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String urlBConvSocio = "/amema/views/buscaconveniosocio.jsp";
	private String urlConvSocio = "/amema/views/convenioSocio.jsp";
	
	private CtrlCliente cCliente = null;
	private CtrlSocioConvenio cSocCon = null;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConvenioSocio() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("evento_buscar1") != null) {
			if(request.getParameter("socio") != null) { 
				try { buscaSocioPorNombre(request, response); } 
				catch (ApplicationException e) { e.printStackTrace(); } 
				}
			if(request.getParameter("doc") != null) { 
				try { buscaSocioPorDNI(request, response); }
				catch(ApplicationException e) { e.printStackTrace(); }
			}
		}
		if(request.getParameter("evento_buscar2") != null) {
			try { buscaSocioPorCodigo(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		if(request.getParameter("evento_buscar3") != null) {
			try { muestraDatos(request, response); }
			catch (ApplicationException e) { e.printStackTrace(); }
		}
		if(request.getParameter("evento_alta") != null) {
			try { altaDatos(request, response); }
			catch (ApplicationException e) { e.printStackTrace(); }
		}
		if(request.getParameter("evento_modifica") != null) {
			try { modificaDatos(request, response); }
			catch (ApplicationException e) { e.printStackTrace(); }
		}
		if(request.getParameter("evento_baja") != null) {
			try { bajaDatos(request, response); }
			catch (ApplicationException e) { e.printStackTrace(); }
		}
	}
	
	
	
	/*
	 * METODOS PRIMARIOS
	 */
	
	private void buscaSocioPorNombre(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cCliente = new CtrlCliente();
		req.getSession().setAttribute("lista", cCliente.listarClientePorNombre(req.getParameter("dato")));
		cCliente = null;
		res.sendRedirect(urlBConvSocio);
	}
	
	
	private void buscaSocioPorDNI(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cCliente = new CtrlCliente();
		req.getSession().setAttribute("socio", cCliente.consultaClientePorDNI(req.getParameter("dato")));
		cCliente = null;
		res.sendRedirect(urlBConvSocio);
	}
	
	private void buscaSocioPorCodigo(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cCliente = new CtrlCliente();
		req.getSession().setAttribute("socio", cCliente.consultaCliente(req.getParameter("socio")));
		cCliente = null; 
		res.sendRedirect(urlBConvSocio);
	}
	
	private void muestraDatos(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cSocCon = new CtrlSocioConvenio();
		SocioConvenio s = new SocioConvenio();
		String cod = req.getParameter("socio").substring(0,4);
		String cpto = "20";
		s = cSocCon.consultaSocioConvenio(cod, cpto);
		if(s != null) {
			req.getSession().setAttribute("datos", s);
		}
		else {
			SocioConvenio c = new SocioConvenio(cod, cpto, null, 0, null, 0, null, 0, "N", 0, "999999");
			req.getSession().setAttribute("datos", c);
		}
		cSocCon = null; 
		s = null;
		res.sendRedirect(urlConvSocio);
	}
	
	private void altaDatos(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		String msj; 
		SocioConvenio s = recuperaData(req);
		cSocCon = new CtrlSocioConvenio();
		if(cSocCon.altaSocioConvenio(s) == true) { msj = "siAlta"; }
		else {msj = "noAlta"; }
		req.getSession().setAttribute("msj", msj);
		s = null; 
		cSocCon = null; 
		res.sendRedirect(urlBConvSocio);
	}
	
	private void modificaDatos(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		String msj; 
		SocioConvenio s = recuperaData(req);
		cSocCon = new CtrlSocioConvenio();
		if(cSocCon.modificaSocioConvenio(s) == true) { msj = "siModifica"; }
		else {msj = "noModifica"; }
		req.getSession().setAttribute("msj", msj);
		s = null; 
		cSocCon = null; 
		res.sendRedirect(urlBConvSocio);
	}
	
	private void bajaDatos(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		String msj; 
		cSocCon = new CtrlSocioConvenio();
		if(cSocCon.bajaSocioConvenio(req.getParameter("socio"), req.getParameter("convenio")) == true) { msj = "siBaja"; }
		else {msj = "noBaja"; }
		req.getSession().setAttribute("msj", msj);
		cSocCon = null; 
		res.sendRedirect(urlBConvSocio);
	}
	
	
	/*
	 * METODOS SECUNDARIOS
	 */
	private SocioConvenio recuperaData(HttpServletRequest req) throws ApplicationException {
		SocioConvenio s = new SocioConvenio(req.getParameter("socio"), req.getParameter("convenio"), req.getParameter("concepto1"), Double.parseDouble(req.getParameter("importe1")),
				req.getParameter("concepto2"), Double.parseDouble(req.getParameter("importe2")), req.getParameter("concepto3"), Double.parseDouble(req.getParameter("importe3")), req.getParameter("interes"), 
				Double.parseDouble(req.getParameter("tasa")), req.getParameter("codigo"));
		return s;
	}
}
