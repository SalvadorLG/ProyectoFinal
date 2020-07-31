package pack.puntoVenta.view;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pack.puntoVenta.Conexion;
import pack.puntoVenta.Main;
import pack.puntoVenta.model.Conection;
import pack.puntoVenta.model.Producto;


public class TicketController extends Conection{
	Main main;

	private Connection con = null;
	private PreparedStatement ps;
	private ResultSet res;
	private ObservableList listaProductos = FXCollections.observableArrayList();
	private static double corte;
	
	@FXML 
	private Label label_ticketFar;
	
	@FXML
	private TextField buscarProducto;
	
	@FXML
	private TextField cantidadProducto;
	
	@FXML
	private TextField sub;
	
	@FXML
	private TextField iva;
	
	@FXML
	private TextField total;
	
	@FXML
	private TextField pago;
	
	@FXML
	private TableView<?> table_ticket;
	
	@FXML
	private Button but_menuTicket;
	
	@FXML
	private Button but_pagar;

    @FXML
    private TextField text_dineroCaja;
	
	public static  void setCorte(double cortex) {
		corte = cortex;
	}
	
	@FXML
	 void backMenu(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource("Menu.fxml"));
    	
    	Scene scene = new Scene(view,800,700);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
   		MenuController controller = fxmlLoader.<MenuController>getController();
   		controller.setCorte(corte);
    	
    	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	st.setScene(scene);
    	st.show();
	}
	
	@FXML
	public void buscarProdTicket(ActionEvent event) {
		
		try{
			boolean success = false;
			String nombreProd = buscarProducto.getText();
			String query = "SELECT * FROM dbo.Productos WHERE nombre_producto = '" + nombreProd + "'";
			table_ticket.getItems().clear();	
			con = Conexion.getConection();
			ps = con.prepareStatement(query);
			res = ps.executeQuery();
			while (res.next()) {
				String nombre = res.getString("nombre_producto");
				String marca = res.getString("marca_producto");
				int precio = res.getInt("precio_unitario");
				int cantidad= res.getInt("cantidad_producto");
				int id = res.getInt("id_product");
				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
				alerta.setTitle("Advertencia");
				alerta.setContentText("Nombre Producto: "+nombre+"\nMarca:"+marca+"\nPrecio Unitario: "+precio+"\nDisponible: "+cantidad);
				alerta.initStyle(StageStyle.UTILITY);
				alerta.setHeaderText(null);
				alerta.showAndWait();
			}	
			
		}catch (Exception e) {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Advertencia");
			alerta.setContentText("No encontrado");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
		}
	}
	
	@FXML
	public void agregarTicket(ActionEvent event) {
		try{
			boolean success = false;
			boolean exists = false;
			String nombreProd = buscarProducto.getText();
			int cantidadProd = Integer.parseInt(cantidadProducto.getText());
			String query = "SELECT * FROM dbo.Productos WHERE nombre_producto = '" + nombreProd + "'";
			for(int i=0;i<listaProductos.size();i++) {
				Producto producto = (Producto) listaProductos.get(i);
				if(nombreProd.equals(producto.getNombreProducto()))	{
					exists = true;
					int cantidadStock = producto.getCantidadProducto();
					int cantidadCompra = producto.getCantidadComprar();
					int cantidadTotal = cantidadCompra+cantidadProd;
					if(cantidadTotal<=cantidadStock) {
						producto.setCantidadComprar(cantidadTotal);
						table_ticket.getItems().clear();
						table_ticket.getItems().addAll(listaProductos);
						
					}
					
				}
			}
			if(!exists) {
				con = Conexion.getConection();
				ps = con.prepareStatement(query);
				res = ps.executeQuery();
				while (res.next()) {
					String nombre = res.getString("nombre_producto");
					String marca = res.getString("marca_producto");
					int precio = res.getInt("precio_unitario");
					int cantidad= res.getInt("cantidad_producto");
					int id = res.getInt("id_product");
					Producto producto = new Producto(id,nombre,marca,precio,cantidad);
					if(producto.getCantidadProducto()>=cantidadProd) {
						producto.setCantidadComprar(cantidadProd);
						listaProductos.add(producto);
						success = true;
					}
					
				}
				if(success) {
					table_ticket.getItems().clear();
					table_ticket.getItems().addAll(listaProductos);	
				}
						
				
			}
			calculateTotal();
			
			
		}catch (Exception e) {
			
		}
	}
	
	private void calculateTotal() {
		int totalPagar=0;
		int cantidad = 0;
		for(int i=0;i<listaProductos.size();i++) {
			Producto producto = (Producto)listaProductos.get(i);
			totalPagar+= producto.getTotal();
		}
		double ivaN = 0.16 * totalPagar;
		double totalConIva = totalPagar + ivaN;
		sub.setText(""+totalPagar);
		iva.setText(""+ivaN);
		total.setText(""+totalConIva);
		
		
		
	}
	@FXML
	public void pagar(ActionEvent event) throws SQLException {
		Connection conexion = getCon();
		double pago2 = -500;
		double cambio = 0;
		double total2 = Double.parseDouble(total.getText());
		if(!pago.getText().isEmpty()) {
			pago2 = Double.parseDouble(pago.getText());
		}
		
		
		if(pago2<=total2) {
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setTitle("Advertencia");
			alerta.setContentText("Pago no solventado");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
			
		}else {
			double entradaCaja=0;
			double salidaCaja2 = 0;
			entradaCaja = corte+pago2;
			cambio = pago2-total2;
			salidaCaja2 = entradaCaja-cambio;
			corte = salidaCaja2;
			String query3 = "INSERT INTO dbo.Corte (Entrada,Salida,Restante) VALUES ('"+pago2+"','"+cambio+"','"+corte+"')";
			con = Conexion.getConection();
			ps = con.prepareStatement(query3);
			ps.execute();
			boolean aux = true;
			int indice = 0;
			try{
				do {
					
					indice = (int)(Math.random()*1000+1);
					String query = "SELECT * FROM dbo.Historial WHERE id_ticket ="+indice;
					con = Conexion.getConection();
					ps = con.prepareStatement(query);
					res = ps.executeQuery();
					if(!res.next()) {
						aux = false;
					}
				}while(aux);
				for(int i=0;i<listaProductos.size();i++) {
					Producto producto = (Producto) listaProductos.get(i);
					String query = "INSERT INTO dbo.Historial (id_producto,cantidad,total,id_ticket,Nombre_Prod) VALUES ("+producto.getId()+","+producto.getCantidadComprar()+","+producto.getTotal()+","+indice+",'"+producto.getNombreProducto()+"')";
					con = Conexion.getConection();
					ps = con.prepareStatement(query);
					ps.execute();
					int cantidad = producto.getCantidadComprar();
					int cantidadStock = producto.getCantidadProducto();
					int cantidadNueva = cantidadStock-cantidad;
					int idaux = producto.getId();
					String sql = "UPDATE Productos SET cantidad_producto="+cantidadNueva+" WHERE id_product="+idaux;
					try {
						ps = conexion.prepareStatement(sql);
						ps.execute();
					}catch(Exception e) {
						System.out.println("Error: "+e);
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Advertencia");
			alerta.setContentText("Cambio: $"+cambio);
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
			
		}
		
	}
	@FXML
	public void verDinero(ActionEvent event) {
		text_dineroCaja.setText(""+corte);
	}
	


}
