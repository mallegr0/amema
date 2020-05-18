package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ctrlUsuario;
import entidades.Usuario;
import util.ApplicationException;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = {"/Login"}, name ="login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		ctrlUsuario cu = new ctrlUsuario();
		String msg = "";
		try {
			if(cu.validaUsuario(request.getParameter("usuario"), request.getParameter("password")) == true) {
				//ESTABLECER LA SESION
				Usuario u = cu.consultaUsuario(request.getParameter("usuario"));
				request.getSession().setAttribute("usuarioActivo", u);
				response.sendRedirect("/amema/views/ppal.jsp");
			}
			else {
				msg = "El usuario o la Contraseña es incorrecta!!";
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("/");
				rd.forward(request, response);
				//response.sendRedirect("/amema/views/fail.jsp");
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
