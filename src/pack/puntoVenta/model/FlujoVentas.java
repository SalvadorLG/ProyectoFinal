package pack.puntoVenta.model;

public class FlujoVentas {
	private String fecha;
	private int subTotal;
	private int iva;
	private int total;
	private int entrada;
	
	public FlujoVentas(int subTotal,int iva, int total) {
		this.subTotal = subTotal;
		this.iva =iva;
		this.total = total;
	}

}
