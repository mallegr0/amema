package controladores;


/* imports */
import util.ApplicationException;
import entidades.Familia;
import data.DataFamilia;


import java.util.ArrayList;


public class CtrlFamilia {
	

	
	/* CONSTRUCTORES */
	
	public CtrlFamilia() {}
	
	/* VARIABLES */
	
	DataFamilia df = null;


	/* METODOS */
	
	public boolean altaFamilia(Familia f) throws ApplicationException{
		df = new  DataFamilia();
		return df.altaFamilia(f);
	}
	
	public boolean bajaFamilia(String id) throws ApplicationException{
		df = new  DataFamilia();
		return df.bajaFamilia(id);
	}
	
	public boolean modificaFamilia(Familia f) throws ApplicationException{
		df = new  DataFamilia();
		return df.modificaFamilia(f);
	}
	
	public Familia consultaFamilia(String id) throws ApplicationException{
		df = new  DataFamilia();
		return df.consultaFamilia(id);
	}
	
	public ArrayList<Familia> listarFamilia() throws ApplicationException{
		df = new  DataFamilia();
		return df.listarFamilia();
	}
}
