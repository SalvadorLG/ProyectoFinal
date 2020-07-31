package pack.puntoVenta.model;

public class Producto {
	private int idProducto;
	private String nombreProducto;
	private String marcaProducto;
	private int precioProducto;
	private int cantidadProducto;
	private int cantidadComprar;
	//private String descripcion;
	
	
	public Producto(int idProducto,String nombreProducto,String marcaProducto,int precioProducto,int cantidadProducto) {
		this.nombreProducto = nombreProducto;
		this.marcaProducto = marcaProducto;
		this.cantidadProducto = cantidadProducto;
		this.precioProducto = precioProducto;
		this.idProducto = idProducto;
	}
	
	//Retorna el total del precio por cantidad (Por producto)
	public int getTotal() {
		int total = precioProducto*cantidadComprar;
		return total;
	}
	
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getMarcaProducto() {
		return marcaProducto;
	}
	public void setMarcaProducto(String marcaProducto) {
		this.marcaProducto = marcaProducto;
	}
	public int getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(int precioProducto) {
		this.precioProducto = precioProducto;
	}
	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	public int getCantidadProducto() {
		return cantidadProducto;
	}

	/*public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}*/

	public int getId() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidadComprar() {
		return cantidadComprar;
	}

	public void setCantidadComprar(int cantidadComprar) {
		this.cantidadComprar = cantidadComprar;
	}
	
	
}
