package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlFamilia;
import entidades.Familia;
import util.ApplicationException;

/**
 * Servlet implementation class Producto
 */
@WebServlet("/Producto")
public class Producto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String urlFlia = "/amema/views/productos.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Producto() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /*
     * Métodos básicos de manejo de httpServletsRequest
     * Estos son los que van a manejar las llamadas desde los forms.
     * 
     */
    
    
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
		if(request.getParameter("evento_alta") != null){
			String msj = "";
			try {
				msj = altaFamilia(request.getParameter("codigo"), request.getParameter("nombre"), Float.parseFloat(request.getParameter("bonificacion")));
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("msj", msj);
			response.sendRedirect(urlFlia);
		}
		
		if(request.getParameter("evento_modificar") != null) { doPut(request, response); }
		
		if(request.getParameter("evento_eliminar") != null){ doDelete(request, response); }
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Familia f = new Familia(request.getParameter("codigo"), request.getParameter("nombre"), Float.parseFloat(request.getParameter("bonificacion")));
		CtrlFamilia cf = new CtrlFamilia();
		String msj = null;
		try {
			if(cf.modificaFamilia(f) == true) { msj="siModifica"; }
			else { msj="noModifica";}
		} catch (Exception e) { e.printStackTrace(); }
		
		request.getSession().setAttribute("msj", msj);
		response.sendRedirect(urlFlia);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlFamilia cf = new CtrlFamilia();
		String msj = null;
		try {
			if(cf.bajaFamilia(request.getParameter("codigo")) == true) { msj="siBaja"; }
			else { msj="noBaja";}
		} catch (Exception e) { e.printStackTrace(); }
		
		request.getSession().setAttribute("msj", msj);
		response.sendRedirect(urlFlia);
	}
	
	
	/*
	 * Métodos que invocan al controlador.
	 * 
	 * Son los metodos que llaman al controlador y asignan los mensajes de fracaso o exito.
	 * 
	 */
	private String altaFamilia(String codigo, String nombre, float bonif) throws ApplicationException {
		CtrlFamilia cf = new CtrlFamilia();
		Familia f = new Familia(codigo, nombre,(float) bonif);
		String msj = null;
		try {
			if(cf.altaFamilia(f) == true) { msj = "siAlta"; }
			else { msj = "noAlta"; }
		} catch (ApplicationException e) { e.printStackTrace(); }
		return msj;
	}

	
}
