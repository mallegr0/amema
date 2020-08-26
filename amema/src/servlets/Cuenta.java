package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlCliente;
import controladores.CtrlConvenio;
import controladores.CtrlCtactecliente;
import controladores.CtrlTpoComprobante;
import entidades.Cliente;
import entidades.CtacteGral;
import entidades.Ctactecliente;
import entidades.TpoComprobante;
import util.ApplicationException;

/**
 * Servlet implementation class Cuentas
 */
@WebServlet(urlPatterns = {"/Cuenta"})
public class Cuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String urlCtacte = "/amema/views/ctactes.jsp";
	private static String urlBCtacte = "/amema/views/buscactactes.jsp";
	private CtrlCliente cCliente = null;
	private CtrlConvenio cConvenio = null;
	private CtrlCtactecliente cCuentas = null;
	private CtrlTpoComprobante cTComprobante = null;

	
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
				try { listarSocio(request, response); }
				catch (ApplicationException e) { e.printStackTrace(); }
			}
			if(request.getParameter("doc") != null) {
				try { buscaDocumento(request, response); }
				catch (ApplicationException e) { e.printStackTrace(); }
			}
		}
		if(request.getParameter("evento_buscar2") != null) {
			try { consultaSocio(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
		if(request.getParameter("evento_buscar3") != null) {
			try { buscarSocio(request, response);}
			catch(ApplicationException e) { e.printStackTrace(); }
		}
	}
	
	
	private void listarSocio(HttpServletRequest req, HttpServletResponse res) throws ApplicationException {
		cCliente = new CtrlCliente();
		ArrayList<Cliente> lista = cCliente.listarClientePorNombre(req.getParameter("dato"));
		req.getSession().setAttribute("lista", lista);
		cCliente = null;
		lista = null;
		try { res.sendRedirect(urlBCtacte); } 
		catch (IOException e) { e.printStackTrace(); }
	}
	
	private void buscaDocumento(HttpServletRequest req, HttpServletResponse res) throws ApplicationException {
		cCliente = new CtrlCliente();
		Cliente c = cCliente.consultaClientePorDNI(req.getParameter("dato"));
		req.getSession().setAttribute("socio", limpiarDatos(c));
		cCliente = null; 
		c = null;
		try { res.sendRedirect(urlBCtacte); } 
		catch (IOException e) { e.printStackTrace(); }
	}
	
	private void consultaSocio(HttpServletRequest req, HttpServletResponse res) throws ApplicationException {
		cCliente = new CtrlCliente();
		Cliente c = cCliente.consultaCliente(req.getParameter("socio"));
		req.getSession().setAttribute("socio", limpiarDatos(c));
		cCliente = null; 
		c = null;
		try { res.sendRedirect(urlBCtacte); } 
		catch (IOException e) { e.printStackTrace(); }
	}
	
	private void buscarSocio(HttpServletRequest req, HttpServletResponse res) throws ApplicationException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");	
		String socio = req.getParameter("socio");
		double saldo = 0.0;
		cCliente = new CtrlCliente();
		Cliente c = new Cliente();
		c = cCliente.consultaCliente(socio.substring(0,4));
		req.getSession().setAttribute("persona", limpiarDatos(c));
		
		try {
			Date fecha = (Date) format.parse(req.getParameter("fecha"));
			ArrayList<Ctactecliente> lista = new ArrayList<>();
			ArrayList<CtacteGral> mov = new ArrayList<>();
			
			cCuentas = new CtrlCtactecliente();
			cTComprobante = new CtrlTpoComprobante();
			lista = cCuentas.listarCtaCtePorSocioYFecha(socio, fecha);
			for(Ctactecliente cta : lista) {
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
			}
			req.getSession().setAttribute("movimientos", mov);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/*PONGO EN NULL TODOS LOS VALORES*/
		
		
		try { res.sendRedirect(urlCtacte); } 
		catch (IOException e) { e.printStackTrace(); }
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
