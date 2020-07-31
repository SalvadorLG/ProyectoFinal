package pack.puntoVenta.model;

public class Proveedores {
	private String nombreEmpresa;
	private int idEmpresa;
	private String nombreContacto;
	private int telefonoContacto;
	
	public Proveedores(int idEmpresa, String nombreEmpresa,String nombreContacto,int telefonoContacto) {
		this.nombreEmpresa = nombreEmpresa;
		this.idEmpresa = idEmpresa;
		this.nombreContacto = nombreContacto;
		this.telefonoContacto = telefonoContacto;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getNombreContacto() {
		return nombreContacto;
	}
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	public int getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(int telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

}
