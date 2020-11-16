package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controladores.CtrlUsuario;
import entidades.Usuario;
import util.ApplicationException;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = {"/Login"}, name="Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlUsuario cu = new CtrlUsuario();
		String msg = "";
		HttpSession session = request.getSession();
		String user,pass;
		
		user = request.getParameter("usuario");
		pass = request.getParameter("password");	
		
		try {
			if(session.getAttribute("usuarioActivo") == null) {		
				if(cu.validaUsuario(user,pass) == true) {
					
					//ESTABLECER LA SESION
					Usuario u = cu.consultaUsuario(request.getParameter("usuario"));
					session.setAttribute("usuarioActivo", u);
					request.getSession().setMaxInactiveInterval(-1); // Establezco que nunca se inactive la sesion.
					request.getRequestDispatcher("/views/ppal.jsp").forward(request, response);
				}
				else {
					session.removeAttribute("usuarioActivo");
					msg = "El usuario o la Contraseña es incorrecta!!";
					request.setAttribute("msg", msg);
					RequestDispatcher rd = request.getRequestDispatcher("/");
					rd.forward(request, response);
				}
			}
			else {
				session.setAttribute("usuarioActivo", null);
				request.getRequestDispatcher("/").forward(request, response);
			}
		} catch (ApplicationException e) { e.printStackTrace(); }
		
	}

}
