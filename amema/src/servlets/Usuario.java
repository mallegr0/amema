package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import util.ApplicationException;
import controladores.ctrlUsuario;


/**
 * Servlet implementation class Usuario
 */
@WebServlet(urlPatterns = {"/Usuario"})
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String urlUser = "/amema/views/usuarios.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuario() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("evento_alta") != null){
			String msj = altaUsuario(request.getParameter("usuario"), request.getParameter("password"), request.getParameter("nombreyapellido"));
			request.getSession().setAttribute("msj", msj);
			response.sendRedirect(urlUser);
		}
		
		if(request.getParameter("evento_CambiaPassword") != null) { doPut(request, response); }
		
		if(request.getParameter("evento_modificar") != null) { doPut(request, response); }
		
		if(request.getParameter("evento_eliminar") != null){ doDelete(request, response); }
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msj = null;
		if(request.getParameter("evento_CambiaPassword") != null) {
			msj = cambiaPass(request.getParameter("usuario"), request.getParameter("password"));
			request.getSession().setAttribute("msj", msj);
			response.sendRedirect(urlUser);
		}
		else {
			entidades.Usuario u = new entidades.Usuario();
			ctrlUsuario cu = new ctrlUsuario();
			try {
				u = cu.consultaUsuario(request.getParameter("usuario"));
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			msj = modificaUsuario(u.getUsuario(), u.getPassword(), request.getParameter("nombre"));
			request.getSession().setAttribute("msj", msj);
			response.sendRedirect(urlUser);
		}

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ctrlUsuario cu = new ctrlUsuario();
		String msj=null;
		
		try {
			if(cu.bajaUsuario(request.getParameter("usuario")) == true ) {
				msj = "siBaja";
			}
			else {
				msj = "noBaja";
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("msj", msj);
		response.sendRedirect(urlUser);
		
	}
	
	private String altaUsuario(String user, String pass, String nombre) {
		entidades.Usuario u = new entidades.Usuario(user,pass,nombre);
		ctrlUsuario cu = new ctrlUsuario();
		String msj = null;
		try {
			if(cu.altaUsuario(u) == true) { msj = "siAlta"; }
			else { msj = "noAlta"; }
		} catch (ApplicationException e) { e.printStackTrace(); }
		return msj;
	}
	
	private String cambiaPass(String user, String pass) {
		ctrlUsuario cu = new ctrlUsuario();
		String msj = null;
		try {
			boolean r = cu.cambiaPassword(user, pass);
			if(r == true) { msj ="siPass";}
			else {msj = "noPass";}
		}catch(ApplicationException e) { e.printStackTrace();}
		return msj;
	}
	
	private String modificaUsuario(String user, String pass, String nombre) {
		entidades.Usuario u = new entidades.Usuario(user, pass, nombre);
		ctrlUsuario cu = new ctrlUsuario();
		String msj = null;
		try {
			if(cu.modificaUsuario(u) == true) { msj ="siModifica";}
			else {msj = "noModifica";}
		}catch(ApplicationException e) { e.printStackTrace();}
		return msj;
	}


}
