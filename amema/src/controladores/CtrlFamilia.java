package controladores;

import java.util.ArrayList;

import data.DataFamilia;
import entidades.Familia;
import util.ApplicationException;

public class CtrlFamilia {
	
/*CONSTRUCTOR*/
	
	public CtrlFamilia() {}
	
	/*VARIABLES*/
	
	DataFamilia df = new DataFamilia();
	
	/*METODOS*/
	
	public boolean altaFamilia(Familia f){
		try {
			return df.altaFamilia(f);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean bajaFamilia(int codigo){
		try {
			return df.bajaFamilia(codigo);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean modificaFamilia(Familia f){
		try {
			return df.modificaFamilia(f);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Familia consultaFamilia(int id){
		try {
			return df.consultaFamilia(id);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Familia> listaFamilias(String orden){
		try {
			return df.listarFamilias(orden);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return  null;
		}
	}
	
}
