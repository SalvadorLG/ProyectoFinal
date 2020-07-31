package pack.puntoVenta.model;

import java.util.ArrayList;

public class Recibo {
	
	private int id_Producto;
	private int cantidad;
	private int total;
	private int id_ticket;
	private String nombreProd;
	
	
	
	public Recibo( int id_Producto, int cantidad, int total, int id_ticket,
			String nombreProd) {
		super();
		this.id_Producto = id_Producto;
		this.cantidad = cantidad;
		this.total = total;
		this.id_ticket = id_ticket;
		this.nombreProd = nombreProd;
	}

	public int getId_Producto() {
		return id_Producto;
	}

	public void setId_Producto(int id_Producto) {
		this.id_Producto = id_Producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getId_ticket() {
		return id_ticket;
	}

	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}

	public String getNombreProd() {
		return nombreProd;
	}

	public void setNombreProd(String nombreProd) {
		this.nombreProd = nombreProd;
	}
	
		
}
