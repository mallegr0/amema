package controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import data.DataParamUltNro;
import entidades.ParamUltNro;
import util.ApplicationException;

public class CtrlParamUltNro {
	
	//Constructor
	public CtrlParamUltNro() {}
	
	//Variables
	DataParamUltNro dpun = null; 
	
	//Metodos
	
	public boolean modificaParamUltNro(ParamUltNro p) throws ApplicationException {
		dpun = new DataParamUltNro();
		return dpun.modificaParamUltNro(p); 
	}
	
	public boolean modificaParamUltNroMF1(ParamUltNro p) throws ApplicationException {
		dpun = new DataParamUltNro();
		return dpun.modificaParamUltNroMF1(p); 
	}
	
	public boolean modificaParamUltNroMF2(ParamUltNro p) throws ApplicationException {
		dpun = new DataParamUltNro();
		return dpun.modificaParamUltNroMF2(p);
	}
	
	public ParamUltNro consultaUltimoNro(String desc, String letra) throws ApplicationException {
		dpun = new DataParamUltNro();
		return dpun.consultaUltimoNro(desc, letra);
	}

}
