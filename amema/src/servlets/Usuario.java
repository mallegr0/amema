package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
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
@WebServlet(urlPatterns = {"/Usuario"}, name ="Usuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuario() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		ArrayList<entidades.Usuario>lista = new ArrayList<>();
		ctrlUsuario cu = new ctrlUsuario();
		
		try {
			lista = cu.listarUsuarios();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
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
		// TODO Auto-generated method stub
		
		if(request.getParameter("evento_alta") != null){
			String msj = altaUsuario(request.getParameter("usuario"), request.getParameter("password"), request.getParameter("nombreyapellido"));
			request.getSession().setAttribute("msj", msj);
			response.sendRedirect("/amema/");
		}
		if(request.getParameter("evento_modificar") != null){
			doPut(request, response);
		}
		if(request.getParameter("evento_eliminar") != null){
			doDelete(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("UPDATE Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("DELETE Served at: ").append(request.getContextPath());
	}
	
	private String altaUsuario(String user, String pass, String nombre) {
		entidades.Usuario u = new entidades.Usuario(user,pass,nombre);
		ctrlUsuario cu = new ctrlUsuario();
		String msj = "Hubo un problema al momento de cargar el Usuario";
		try {
			if(cu.altaUsuario(u) == true) { msj = "OK"; }
			else { msj = "NO"; }
		} catch (ApplicationException e) { e.printStackTrace(); }
		return msj;
	}

}
