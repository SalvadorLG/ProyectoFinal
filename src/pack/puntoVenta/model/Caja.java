package pack.puntoVenta.model;

public class Caja {
	private int id;
	private String entradas;
	private String salidas;
	private String restantes;
	

	
	public Caja(int id, String entradas, String salidas, String restantes) {
		super();
		this.id = id;
		this.entradas = entradas;
		this.salidas = salidas;
		this.restantes = restantes;
	}
	public String getEntradas() {
		return entradas;
	}
	public void setEntradas(String entradas) {
		this.entradas = entradas;
	}
	public String getSalidas() {
		return salidas;
	}
	public void setSalidas(String salidas) {
		this.salidas = salidas;
	}
	public String getRestantes() {
		return restantes;
	}
	public void setRestantes(String restantes) {
		this.restantes = restantes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
