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
@WebServlet("/Usuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		// TODO Auto-generated method stub
		
		if(request.getParameter("evento_alta") != null){
			altaUsuario(request.getParameter("usuario"), request.getParameter("password"), request.getParameter("nombreyapellido"));
			
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
	
	private void altaUsuario(String user, String pass, String nombre) {
		entidades.Usuario u = new entidades.Usuario(user,pass,nombre);
		ctrlUsuario cu = new ctrlUsuario();
		
		try {
			if(cu.altaUsuario(u) == true) {
				System.out.println("se cargo el usuario");
			}
			else {
				System.out.println("no se cargo el usuario");
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
