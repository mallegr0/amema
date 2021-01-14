package entidades;

import java.io.Serializable;
import java.util.Date;

public class Cheque implements Serializable {

	//variables
	private static final long serialVersionUID = 1L;
	private double ncheque; 
	private String cia, tcheque, nctacte, cconc, banco,plaza; 
	private Date fecche; 
	private double impche, dolar, hs_acredit; 
	private String prop_ter, car_pas, emitido, codcli; 
	private Date frecep; 
	private String pasado_a;
	private Date fpasado_a; 
	private String ncom_ent, tcom_ent, ncom_sal, tcom_sal, nmov, nrecibo, del_client, nroop;
	private double usado; 
	
	//constructores
	public Cheque() {}

	public Cheque(double ncheque, String cia, String tcheque, String nctacte, String cconc, String banco, String plaza,
			Date fecche, double impche, double dolar, double hs_acredit, String prop_ter, String car_pas,
			String emitido, String codcli, Date frecep, String pasado_a, Date fpasado_a, String ncom_ent,
			String tcom_ent, String ncom_sal, String tcom_sal, String nmov, String nrecibo, String del_client,
			String nroop, double usado) {
		this.ncheque = ncheque;
		this.cia = cia;
		this.tcheque = tcheque;
		this.nctacte = nctacte;
		this.cconc = cconc;
		this.banco = banco;
		this.plaza = plaza;
		this.fecche = fecche;
		this.impche = impche;
		this.dolar = dolar;
		this.hs_acredit = hs_acredit;
		this.prop_ter = prop_ter;
		this.car_pas = car_pas;
		this.emitido = emitido;
		this.codcli = codcli;
		this.frecep = frecep;
		this.pasado_a = pasado_a;
		this.fpasado_a = fpasado_a;
		this.ncom_ent = ncom_ent;
		this.tcom_ent = tcom_ent;
		this.ncom_sal = ncom_sal;
		this.tcom_sal = tcom_sal;
		this.nmov = nmov;
		this.nrecibo = nrecibo;
		this.del_client = del_client;
		this.nroop = nroop;
		this.usado = usado;
	}
	
	
	//Metodos
	public double getNcheque() {
		return ncheque;
	}

	public void setNcheque(double ncheque) {
		this.ncheque = ncheque;
	}

	public String getCia() {
		return cia;
	}

	public void setCia(String cia) {
		this.cia = cia;
	}

	public String getTcheque() {
		return tcheque;
	}

	public void setTcheque(String tcheque) {
		this.tcheque = tcheque;
	}

	public String getNctacte() {
		return nctacte;
	}

	public void setNctacte(String nctacte) {
		this.nctacte = nctacte;
	}

	public String getCconc() {
		return cconc;
	}

	public void setCconc(String cconc) {
		this.cconc = cconc;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getPlaza() {
		return plaza;
	}

	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}

	public Date getFecche() {
		return fecche;
	}

	public void setFecche(Date fecche) {
		this.fecche = fecche;
	}

	public double getImpche() {
		return impche;
	}

	public void setImpche(double impche) {
		this.impche = impche;
	}

	public double getDolar() {
		return dolar;
	}

	public void setDolar(double dolar) {
		this.dolar = dolar;
	}

	public double getHs_acredit() {
		return hs_acredit;
	}

	public void setHs_acredit(double hs_acredit) {
		this.hs_acredit = hs_acredit;
	}

	public String getProp_ter() {
		return prop_ter;
	}

	public void setProp_ter(String prop_ter) {
		this.prop_ter = prop_ter;
	}

	public String getCar_pas() {
		return car_pas;
	}

	public void setCar_pas(String car_pas) {
		this.car_pas = car_pas;
	}

	public String getEmitido() {
		return emitido;
	}

	public void setEmitido(String emitido) {
		this.emitido = emitido;
	}

	public String getCodcli() {
		return codcli;
	}

	public void setCodcli(String codcli) {
		this.codcli = codcli;
	}

	public Date getFrecep() {
		return frecep;
	}

	public void setFrecep(Date frecep) {
		this.frecep = frecep;
	}

	public String getPasado_a() {
		return pasado_a;
	}

	public void setPasado_a(String pasado_a) {
		this.pasado_a = pasado_a;
	}

	public Date getFpasado_a() {
		return fpasado_a;
	}

	public void setFpasado_a(Date fpasado_a) {
		this.fpasado_a = fpasado_a;
	}

	public String getNcom_ent() {
		return ncom_ent;
	}

	public void setNcom_ent(String ncom_ent) {
		this.ncom_ent = ncom_ent;
	}

	public String getTcom_ent() {
		return tcom_ent;
	}

	public void setTcom_ent(String tcom_ent) {
		this.tcom_ent = tcom_ent;
	}

	public String getNcom_sal() {
		return ncom_sal;
	}

	public void setNcom_sal(String ncom_sal) {
		this.ncom_sal = ncom_sal;
	}

	public String getTcom_sal() {
		return tcom_sal;
	}

	public void setTcom_sal(String tcom_sal) {
		this.tcom_sal = tcom_sal;
	}

	public String getNmov() {
		return nmov;
	}

	public void setNmov(String nmov) {
		this.nmov = nmov;
	}

	public String getNrecibo() {
		return nrecibo;
	}

	public void setNrecibo(String nrecibo) {
		this.nrecibo = nrecibo;
	}

	public String getDel_client() {
		return del_client;
	}

	public void setDel_client(String del_client) {
		this.del_client = del_client;
	}

	public String getNroop() {
		return nroop;
	}

	public void setNroop(String nroop) {
		this.nroop = nroop;
	}

	public double getUsado() {
		return usado;
	}

	public void setUsado(double usado) {
		this.usado = usado;
	}
	
	

}
