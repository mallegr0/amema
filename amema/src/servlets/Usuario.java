package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import util.ApplicationException;
import controladores.CtrlUsuario;


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
			String msj = "";
			try {
				System.out.println("password "+request.getParameter("password"));
				
				msj = altaUsuario(request.getParameter("usuario"), request.getParameter("password"), request.getParameter("nombre"));
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			CtrlUsuario cu = new CtrlUsuario();
			try {
				u = cu.consultaUsuario(request.getParameter("usuario"));
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			msj = modificaUsuario(u.getNroUsuario(), request.getParameter("nombre"), u.getLogIn(), u.getPassWord(), u.getDescOficina(),
					u.getDescFunc(), u.getCperfil(), u.getHab());
			request.getSession().setAttribute("msj", msj);
			response.sendRedirect(urlUser);
		}

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CtrlUsuario cu = new CtrlUsuario();
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
	
	private String altaUsuario(String nombre, String user, String pass) throws ApplicationException {
		CtrlUsuario cu = new CtrlUsuario();	
		String id = cu.UltimoID();
		entidades.Usuario u = new entidades.Usuario(id, nombre, user, pass ,"-", "ADMINISTRADOR", "001", "1");
		String msj = null;
		try {
			if(cu.altaUsuario(u) == true) { msj = "siAlta"; }
			else { msj = "noAlta"; }
		} catch (ApplicationException e) { e.printStackTrace(); }
		return msj;
	}
	
	private String cambiaPass(String user, String pass) {
		CtrlUsuario cu = new CtrlUsuario();
		String msj = null;
		try {
			boolean r = cu.cambiaPassword(user, pass);
			if(r == true) { msj ="siPass";}
			else {msj = "noPass";}
		}catch(ApplicationException e) { e.printStackTrace();}
		return msj;
	}
	
	private String modificaUsuario(String id, String nombre, String user, String pass, String oficina, String funcion, String perfil, String hab) {
		entidades.Usuario u = new entidades.Usuario(id, nombre, user, pass, oficina, funcion, perfil, hab);
		CtrlUsuario cu = new CtrlUsuario();
		String msj = null;
		try {
			if(cu.modificaUsuario(u) == true) { msj ="siModifica";}
			else {msj = "noModifica";}
		}catch(ApplicationException e) { e.printStackTrace();}
		return msj;
	}


}
