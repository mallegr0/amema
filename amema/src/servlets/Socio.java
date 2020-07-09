package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlCliente;
import entidades.Cliente;
import util.ApplicationException;


/**
 * Servlet implementation class Socio
 */
@WebServlet(urlPatterns = {"/Socio"})
public class Socio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String urlSocio = "/amema/views/socios.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Socio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("evento_alta") != null){
			String msj = "";
			try {
				msj = altaSocio(request.getParameter("nrosocio"), request.getParameter("nombre"), request.getParameter("domicilio"),
						request.getParameter("codpos"), request.getParameter("telefono1"), request.getParameter("telefono2"), 
						request.getParameter("convenio"), request.getParameter("nrodoc"), request.getParameter("legajo"),
						request.getParameter("estado"), request.getParameter("cc"), request.getParameter("descc"),
						request.getParameter("mail"), request.getParameter("tpodoc"), request.getParameter("fecnac"),
						request.getParameter("fecing"), request.getParameter("empresa"), request.getParameter("obs"));
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("msj", msj);
			response.sendRedirect(urlSocio);
		}
		
		if(request.getParameter("evento_modificar") != null) { doPut(request, response); }
		
		if(request.getParameter("evento_eliminar") != null){ doDelete(request, response); }
	}


	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msj = null;
		try {
			msj = modificaSocio(request.getParameter("nrosocio"), request.getParameter("nombre"), request.getParameter("domicilio"),
					request.getParameter("codpos"), request.getParameter("telefono1"), request.getParameter("telefono2"), 
					request.getParameter("convenio"), request.getParameter("nrodoc"), request.getParameter("legajo"),
					request.getParameter("estado"), request.getParameter("cc"), request.getParameter("descc"),
					request.getParameter("mail"), request.getParameter("tpodoc"), request.getParameter("fecnac"),
					request.getParameter("fecing"), request.getParameter("empresa"), request.getParameter("obs"));
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("msj", msj);
		response.sendRedirect(urlSocio);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlCliente cc = new CtrlCliente();
		String msj = null;
		
		try {
			if(cc.bajaCliente(request.getParameter("socio")) == true) {
				msj = "siBaja";
			}
			else { msj = "noBaja"; }
		} catch (ApplicationException e) { e.printStackTrace(); }
		
		request.getSession().setAttribute("msj", msj);
		response.sendRedirect(urlSocio);

	}
	
	
	private String altaSocio(String nrosocio, String nombre, String domicilio, String codpos, String telefono1, String telefono2,
			String convenio, String nrodoc, String legajo, String estado, String cc, String descc, String mail, String tpodoc, 
			String fecnac, String fecing, String empresa, String obs) throws ApplicationException {
		
		CtrlCliente ctc = new  CtrlCliente();
		Cliente c = new Cliente();
		String msj = "";	
		
		c.setMARCA("N");
		c.setCODCLI(nrosocio);
		c.setNOMCLI(nombre);
		c.setDOMCLI(domicilio);
		c.setCODPOS(Integer.parseInt(codpos));
		c.setLOCCLI(ctc.localidad(Integer.parseInt(codpos)));
		c.setTELCLI_1(telefono1);
		c.setTELCLI_2(telefono2);
		c.setFAX(null);
		c.setCVTO("01");
		c.setCCOND(convenio);
		c.setZONCLI("01");
		c.setNVIAJ("99");
		c.setPROVCLI("S");
		c.setCUITCLI(nrodoc);
		c.setIVACLI(null);
		c.setREGCLI("3");
		c.setPRETEN(0.0);
		c.setDNRP(legajo);
		c.setSALCLI_1(0.0);
		c.setSALCLID_1(0.0);
		c.setFSALCLI_1(null);
		c.setSALCLI_2(0.0);
		c.setSALCLID_2(0.0);
		c.setFSALCLI_2(null);
		c.setA_CTA_1(0.0);
		c.setA_CTA_2(0.0);
		c.setA_CTAD_1(0.0);
		c.setA_CTAD_2(0.0);
		c.setCTRANSP("000");
		c.setCOM_IND(estado);
		c.setCREDITO("N");
		c.setCRED_MAX(0.0);
		c.setCONTACTO(cc);
		c.setCONTACTO2(descc);
		c.setLISTAPRE(1);
		c.setE_MAIL(mail);
		c.setMAKITA("N");
		c.setCOMISION("N");
		c.setCOMI_DIFE("N");
		c.setTIPO_DOC(tpodoc);
		c.setFECHA_NAC(fecha(fecnac));
		c.setFECHA_ING(fecha(fecing));
		c.setCPCCP(empresa);
		c.setOBSCLI(obs);
		
		try {
			if(ctc.altaCliente(c) == true) { msj = "siAlta"; }
			else { msj = "noAlta"; }
		}
		catch(Exception e) { e.printStackTrace();}
		
		return msj;
		
	}
	
	private String modificaSocio(String nrosocio, String nombre, String domicilio, String codpos, String telefono1, String telefono2,
			String convenio, String nrodoc, String legajo, String estado, String cc, String descc, String mail, String tpodoc, 
			String fecnac, String fecing, String empresa, String obs) throws ApplicationException {
		
		CtrlCliente ctc = new  CtrlCliente();
		Cliente c = new Cliente();
		String msj = "";	
		
		c.setMARCA("N");
		c.setCODCLI(nrosocio);
		c.setNOMCLI(nombre);
		c.setDOMCLI(domicilio);
		c.setCODPOS(Integer.parseInt(codpos));
		c.setLOCCLI(ctc.localidad(Integer.parseInt(codpos)));
		c.setTELCLI_1(telefono1);
		c.setTELCLI_2(telefono2);
		c.setFAX(null);
		c.setCVTO("01");
		c.setCCOND(convenio);
		c.setZONCLI("01");
		c.setNVIAJ("99");
		c.setPROVCLI("S");
		c.setCUITCLI(nrodoc);
		c.setIVACLI(null);
		c.setREGCLI("3");
		c.setPRETEN(0.0);
		c.setDNRP(legajo);
		c.setSALCLI_1(0.0);
		c.setSALCLID_1(0.0);
		c.setFSALCLI_1(null);
		c.setSALCLI_2(0.0);
		c.setSALCLID_2(0.0);
		c.setFSALCLI_2(null);
		c.setA_CTA_1(0.0);
		c.setA_CTA_2(0.0);
		c.setA_CTAD_1(0.0);
		c.setA_CTAD_2(0.0);
		c.setCTRANSP("000");
		c.setCOM_IND(estado);
		c.setCREDITO("N");
		c.setCRED_MAX(0.0);
		c.setCONTACTO(cc);
		c.setCONTACTO2(descc);
		c.setLISTAPRE(1);
		c.setE_MAIL(mail);
		c.setMAKITA("N");
		c.setCOMISION("N");
		c.setCOMI_DIFE("N");
		c.setTIPO_DOC(tpodoc);
		c.setFECHA_NAC(fecha(fecnac));
		c.setFECHA_ING(fecha(fecing));
		c.setCPCCP(empresa);
		c.setOBSCLI(obs);
		
		try {
			if(ctc.modificaCliente(c) == true) { msj = "siModifica"; }
			else { msj = "noModifica"; }
		}
		catch(Exception e) { e.printStackTrace();}
		
		return msj;
		
	}
	
	private Date fecha (String fec) {
		Date data = Date.valueOf(fec);
		return data; 
	}

}
