package pack.puntoVenta.view;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pack.puntoVenta.Conexion;
import pack.puntoVenta.model.Conection;
import pack.puntoVenta.model.Producto;

public class FormularioProductoController extends Conection{
	private Connection con = null;
	private ResultSet res;
	private static double corte;
	
	@FXML 
	private Label label_newProd;
	
	@FXML
	private TextField text_nombreProd;
	
	@FXML
	private TextField text_marca;
	
	@FXML
	private TextField text_precioUn;
	
	@FXML
	private TextField text_cantidad;
	
	@FXML
	private Button but_agregarProducto;
	
	public static  void setCorte(double cortex) {
		corte = cortex;
	}
	
	@FXML
	void backMenu(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource("Menu.fxml"));
    	
    	Scene scene = new Scene(view);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
   		MenuController controller = fxmlLoader.<MenuController>getController();
   		controller.setCorte(corte);
    	
    	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	st.setScene(scene);
    	st.show();
	}
	@FXML
	void verLista(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource("ListaProductos.fxml"));

		Scene scene = new Scene(view);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListaProductos.fxml"));
		ListaProductosController controller = fxmlLoader.<ListaProductosController>getController();
   		controller.setCorte(corte);

		Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();

		st.setScene(scene);
		st.show();
	}
	@FXML
	public void addProducto() {
		
		String nombreProducto=text_nombreProd.getText();
		String marcaProducto=text_marca.getText();
		int precioProducto=Integer.parseInt(text_precioUn.getText());
		int cantidadProducto=Integer.parseInt(text_cantidad.getText());
		int id=45;
		Producto producto = new Producto(id, nombreProducto, marcaProducto,precioProducto,cantidadProducto);
		try {
			try {
				String sql = "INSERT INTO Productos (nombre_producto,marca_producto,precio_unitario,cantidad_producto) VALUES ('"+nombreProducto+"','"+marcaProducto+"',"+precioProducto+","+cantidadProducto+")";
				Conection conexion = new Conection();
				Statement st = conexion.getCon().createStatement();
				st.executeUpdate(sql);
				ResultSet rs;
				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    			alerta.setTitle("Advertencia");
    			alerta.setContentText("Producto Agregado");
    			alerta.initStyle(StageStyle.UTILITY);
    			alerta.setHeaderText(null);
    			alerta.showAndWait();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			System.out.println("Error: "+e);	
		}
	}
	
	
	

}
