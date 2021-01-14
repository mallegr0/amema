package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.CtrlPeriodoDeudaArch;
import controladores.CtrlPeriodoDeudaGen;
import entidades.PeriodoDeudaArch;
import entidades.PeriodoDeudaGen;
import entidades.PeriodoGenGral;
import util.ApplicationException;

/**
 * Servlet implementation class PeriodoDeudaGen
 */
@WebServlet(urlPatterns = {"/PeriodoDeudaGenerado"})
public class PeriodoDeudaGenerado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String urlPeriodoDeuda = "/amema/views/periodosDeuda.jsp";
	private CtrlPeriodoDeudaGen cPeriodoGen = null; 
	private CtrlPeriodoDeudaArch cPeriodoArch = null; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PeriodoDeudaGenerado() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("evento_buscar") != null) {
			try {
				muestroDatos(request);
				response.sendRedirect(urlPeriodoDeuda);
			} catch (ApplicationException e) { e.printStackTrace(); }
		}
	}
	
	/*
	 * 
	 * METODOS PRIMARIOS
	 */
	
	private void muestroDatos(HttpServletRequest req) throws ApplicationException {
		
		//Declaros las variables que voy a usar
		
		//Recupero los datos del form
		int nroDeuda = Integer.parseInt(req.getParameter("nroDeuda")); 
		
		//Controladores
		cPeriodoGen = new CtrlPeriodoDeudaGen();
		cPeriodoArch = new CtrlPeriodoDeudaArch();
		
		//Entidades
		 PeriodoDeudaGen periodo = null; 
		 PeriodoGenGral dato = null; 
		
		//Arrays
		ArrayList<PeriodoDeudaArch> resultados = new ArrayList<>();
		ArrayList<PeriodoGenGral> datos = new ArrayList<>();
		
		//Recupero el registro segun el nro de deuda seleccionado
		periodo = cPeriodoGen.consultaDatosPeriodoPorNro(nroDeuda);
		
		//recupero todos los movimientos que tiene ese periodo seleccionado
		resultados = cPeriodoArch.ListarDatosPorPeriodoyConvenio(periodo.getNroconv(), periodo.getPeriodo());
		
		//Asigno los datos recuperados a las nuevas variables.
		for(PeriodoDeudaArch p: resultados) {
			dato = new PeriodoGenGral(Integer.toString(nroDeuda), p.getNroconv(), p.getPerido(), p.getNomb_archivo());
			datos.add(dato);
		}
		
		//Finalizo las variables que ya no uso
		cPeriodoGen = null;
		cPeriodoArch = null;
		periodo = null;
		dato = null;
		
		//Devuelvo los datos
		req.getSession().setAttribute("datos", datos);
	}

}
