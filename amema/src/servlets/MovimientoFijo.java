package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlArticulo;
import controladores.CtrlCliente;
import controladores.CtrlConvenio;
import controladores.CtrlVenta;
import entidades.AdherentesGral;
import entidades.Articulo;
import entidades.Cliente;
import entidades.Venta;
import util.ApplicationException;

/**
 * Servlet implementation class MovimientoFijo
 */
@WebServlet(urlPatterns = {"/MovimientoFijo"})
public class MovimientoFijo extends HttpServlet {
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
    
	private String urlBMovFijo = "/amema/views/buscamovfijos.jsp";
	private String urlMovFijo = "/amema/views/movimientosfijos.jsp";
	
	private CtrlVenta cVentas = null;
	private CtrlCliente cCliente = null; 
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovimientoFijo() { super(); }

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
				try { buscarSocioPorNombre(request, response); }
				catch(ApplicationException e) { e.printStackTrace(); }
			}
			if(request.getParameter("doc") != null) {
				try { buscarSocioPorDNI(request, response); }
				catch(ApplicationException e) { e.printStackTrace(); }
			}
		}
		
		if(request.getParameter("evento_buscar2") != null) {
			try { buscarSocio(request, response); }
			catch(ApplicationException e) { e.printStackTrace(); }
		}
	}
	
	
	/* 
	 * METODOS PRIMARIOS 
	 */
	
	private void buscarSocioPorNombre(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cCliente = new CtrlCliente();
		req.getSession().setAttribute("lista", cCliente.listarClientePorNombre(req.getParameter("dato")));
		cCliente = null; 
		res.sendRedirect(urlBMovFijo);
	}
	
	private void buscarSocioPorDNI(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cCliente = new CtrlCliente();
		muestraDatos(req, res, cCliente.consultaClientePorDNI(req.getParameter("dato")));
	}
	
	private void buscarSocio(HttpServletRequest req, HttpServletResponse res) throws ApplicationException, IOException {
		cCliente = new CtrlCliente(); 
		muestraDatos(req, res, cCliente.consultaCliente(req.getParameter("socio")));
	}
	
	/*
	 * METODOS SECUNDARIOS
	 */
	
	private void muestraDatos(HttpServletRequest req, HttpServletResponse res, Cliente c) throws ApplicationException, IOException {
		req.getSession().setAttribute("socio", limpiarDatos(c));
		//Declaro los controladores 
		CtrlArticulo ca = new CtrlArticulo();
		cVentas = new CtrlVenta();
				
		//Declaros los arrays que voy a ir usando
		ArrayList<Venta> lventas = new ArrayList<>();
		ArrayList<AdherentesGral> lrta = new ArrayList<>();
		
		//Declaros las entidades que necesito.
		AdherentesGral rta = null;
		Articulo a = null;
				
		//Declaro variables varias para usar
		String cgrupo, csubf, nroart, codart;
				
				
		//Busco las ventas del socio 
		lventas = cVentas.listarVentaPorSocio(c.getCODCLI());
				
				
		//Por cada venta que encontre devuelvo los valores 
		for(Venta lv : lventas) {
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			codart = lv.getCODART();
			cgrupo = codart.substring(0,2);
			csubf = codart.substring(2,3);
			nroart = codart.substring(3,6);
			a = new Articulo();
			a = ca.consultarArticulo(cgrupo, csubf, nroart);
			String fecDesde = f.format(lv.getFEC_DESDE());
			String fecHasta = f.format(lv.getFVTO());
			rta = new AdherentesGral(lv.getNROMOV(), fecDesde, fecHasta, lv.getANALISIS(), lv.getCODART(), lv.getREFERENCIA(),
						a.getDESART(), lv.getPRECIO(), (int) a.getUNIDAD(), a.getENVASE(), lv.getINNCTACTE(), lv.getVA_DTO(), null);
			lrta.add(rta);
			a = null;
			rta = null;
		}
		cVentas = null;
		ca = null;
		
		req.getSession().setAttribute("movimientos", lrta);
		res.sendRedirect(urlMovFijo);
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
