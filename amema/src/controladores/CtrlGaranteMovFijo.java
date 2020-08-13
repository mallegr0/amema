package controladores;

import java.util.ArrayList;

import data.DataGaranteMovFijo;
import entidades.GaranteMovFijo;
import util.ApplicationException;

public class CtrlGaranteMovFijo {
	/* VARIABLES */
	DataGaranteMovFijo dg = null;
	
	/* METODOS */
	
	public Boolean altaGaranteMovF(GaranteMovFijo g) throws ApplicationException {
		dg = new DataGaranteMovFijo();
		return dg.altaGaranteMovF(g);
	}
	
	public Boolean bajaGaranteMovFPorGarante(String g) throws ApplicationException {
		dg = new DataGaranteMovFijo();
		return dg.bajaGaranteMovFPorGarante(g);
	}
	
	public Boolean bajaGaranteMovFPorMov(Integer m) throws ApplicationException {
		dg = new DataGaranteMovFijo();
		return dg.bajaGaranteMovFPorMov(m);
	}
	
	public Boolean modifucaGaranteMovF(GaranteMovFijo g) throws ApplicationException {
		dg = new DataGaranteMovFijo();
		return dg.modificaGaranteMovF(g);
	}
	
	public GaranteMovFijo consultaGaranteMovF(GaranteMovFijo g) throws ApplicationException {
		dg = new DataGaranteMovFijo();
		return dg.consultaGaranteMovF(g);
	}
	
	public ArrayList<GaranteMovFijo> listarTodo() throws ApplicationException {
		dg = new DataGaranteMovFijo();
		return dg.listarTodo();
	}
	
	public ArrayList<GaranteMovFijo> listarMovimientos(String g) throws ApplicationException {
		dg = new DataGaranteMovFijo();
		return dg.listarMovimientos(g);
	}
}
