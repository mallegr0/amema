package entidades;

import java.io.Serializable;
import java.util.Date;

public class VentaReporte implements Serializable {

	//Variables 
	private static final long serialVersionUID = 1L;
	private String cpccp, dnrp, nomcli, domcli; 
	private Date fecha_nac;
	private String tipo_doc, cuitcli, telcli_1, codart, desart; 
	private double unidad, precio; 
	private String va_dto;
	private double impch; 
	private String ubicac1, ubicac2; 
	private Date fec_desde; 
	private String cancdeuant;
	private double impcancdeuant; 
	
	//Constructores
	public VentaReporte() {}

	public VentaReporte(String cpccp, String dnrp, String nomcli, String domcli, Date fecha_nac, String tipo_doc,
			String cuitcli, String telcli_1, String codart, String desart, double unidad, double precio, String va_dto,
			double impch, String ubicac1, String ubicac2, Date fec_desde, String cancdeuant, double impcancdeuant) {
		this.cpccp = cpccp;
		this.dnrp = dnrp;
		this.nomcli = nomcli;
		this.domcli = domcli;
		this.fecha_nac = fecha_nac;
		this.tipo_doc = tipo_doc;
		this.cuitcli = cuitcli;
		this.telcli_1 = telcli_1;
		this.codart = codart;
		this.desart = desart;
		this.unidad = unidad;
		this.precio = precio;
		this.va_dto = va_dto;
		this.impch = impch;
		this.ubicac1 = ubicac1;
		this.ubicac2 = ubicac2;
		this.fec_desde = fec_desde;
		this.cancdeuant = cancdeuant;
		this.impcancdeuant = impcancdeuant;
	}
	
	
	//Metodos 
	
	public String getCpccp() {
		return cpccp;
	}

	public void setCpccp(String cpccp) {
		this.cpccp = cpccp;
	}

	public String getDnrp() {
		return dnrp;
	}

	public void setDnrp(String dnrp) {
		this.dnrp = dnrp;
	}

	public String getNomcli() {
		return nomcli;
	}

	public void setNomcli(String nomcli) {
		this.nomcli = nomcli;
	}

	public String getDomcli() {
		return domcli;
	}

	public void setDomcli(String domcli) {
		this.domcli = domcli;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public String getTipo_doc() {
		return tipo_doc;
	}

	public void setTipo_doc(String tipo_doc) {
		this.tipo_doc = tipo_doc;
	}

	public String getCuitcli() {
		return cuitcli;
	}

	public void setCuitcli(String cuitcli) {
		this.cuitcli = cuitcli;
	}

	public String getTelcli_1() {
		return telcli_1;
	}

	public void setTelcli_1(String telcli_1) {
		this.telcli_1 = telcli_1;
	}

	public String getCodart() {
		return codart;
	}

	public void setCodart(String codart) {
		this.codart = codart;
	}

	public String getDesart() {
		return desart;
	}

	public void setDesart(String desart) {
		this.desart = desart;
	}

	public double getUnidad() {
		return unidad;
	}

	public void setUnidad(double unidad) {
		this.unidad = unidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getVa_dto() {
		return va_dto;
	}

	public void setVa_dto(String va_dto) {
		this.va_dto = va_dto;
	}

	public double getImpch() {
		return impch;
	}

	public void setImpch(double impch) {
		this.impch = impch;
	}

	public String getUbicac1() {
		return ubicac1;
	}

	public void setUbicac1(String ubicac1) {
		this.ubicac1 = ubicac1;
	}

	public String getUbicac2() {
		return ubicac2;
	}

	public void setUbicac2(String ubicac2) {
		this.ubicac2 = ubicac2;
	}

	public Date getFec_desde() {
		return fec_desde;
	}

	public void setFec_desde(Date fec_desde) {
		this.fec_desde = fec_desde;
	}

	public String getCancdeuant() {
		return cancdeuant;
	}

	public void setCancdeuant(String cancdeuant) {
		this.cancdeuant = cancdeuant;
	}

	public double getImpcancdeuant() {
		return impcancdeuant;
	}

	public void setImpcancdeuant(double impcancdeuant) {
		this.impcancdeuant = impcancdeuant;
	}
}
