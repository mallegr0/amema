package servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import controladores.CtrlCliente;
import entidades.Cliente;
import entidades.Usuario;
import util.ApplicationException;

/**
 * Servlet implementation class ListarSocio
 */
@WebServlet(urlPatterns = {"/ListarSocio"})
public class ListarSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String urlListarSocio = "/amema/views/listarsocios.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarSocio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String socioActivo = request.getParameter("activos");
		String convenio = request.getParameter("convenio");
		
		Usuario user = (entidades.Usuario) request.getSession().getAttribute("usuarioActivo");
		
		CtrlCliente cc = new CtrlCliente();
		ArrayList<Cliente> lista = new ArrayList<>();
		
		try {
			if(socioActivo != null) {
				if(convenio != null) { 
					lista = cc.listarClienteActivoConvenio("A", convenio);
					migrarExcel(lista, user.getNomUs());
				}
				else { 
					lista = cc.listarClienteEstado("A");
					migrarExcel(lista, user.getNomUs());
				}
			}
			else {
				if(convenio != null) { 
					lista = cc.listarClienteConvenio(convenio);
					migrarExcel(lista, user.getNomUs());
				}
				else { 
					lista = cc.listarCliente();
					migrarExcel(lista, user.getNomUs());
				}
			}
		}
		catch(ApplicationException e){ e.printStackTrace(); }
	}
	
	
	private void migrarExcel(ArrayList<Cliente> lista, String usuario) {
		
		/* creo el libro del excel */
	
		Workbook libro = new HSSFWorkbook();
		
		
		try  {

			OutputStream fileOut = new FileOutputStream("prueba:xls");
		    libro.write(fileOut);


		}
		catch(IOException e) {e.printStackTrace();}
		
	}
}
