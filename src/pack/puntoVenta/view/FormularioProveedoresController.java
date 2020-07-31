package pack.puntoVenta.view;

import java.io.IOException;
import java.sql.Connection;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pack.puntoVenta.model.Conection;
import pack.puntoVenta.model.Proveedores;

public class FormularioProveedoresController extends Conection{
	
	
		private Connection con = null;
		private ResultSet res;
		private static double corte;
		
	@FXML
	private Button but_menuaddProv;
	
	@FXML
	private Button but_viewAddProv;
	@FXML
	private TextField text_nombreProveedores;
	@FXML 
	private TextField text_nombreContacto;
	@FXML
	private TextField text_telefonoContacto;
	
	
	public static  void setCorte(double cortex) {
		corte = cortex;
	}

	@FXML
	 void backMenu(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
   		MenuController controller = fxmlLoader.<MenuController>getController();
   		controller.setCorte(corte);
   	
	   	Scene scene = new Scene(view);
	   	
	   	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
	   	
	   	st.setScene(scene);
	   	st.show();
	}
	
	@FXML
	void verListaProv(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource("Proveedores.fxml"));
	   	
	   	Scene scene = new Scene(view);
	   	
	   	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
	   	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Proveedores.fxml"));
   		ProveedoresController controller = fxmlLoader.<ProveedoresController>getController();
   		controller.setCorte(corte);
	   	
	   	st.setScene(scene);
	   	st.show();
		
	}
	@FXML 
	public void addProveedor() {
		String nombre = text_nombreProveedores.getText();
		String nombreContacto = text_nombreContacto.getText();
		int telefono = Integer.parseInt(text_telefonoContacto.getText());
		int id = 0;
		Proveedores proveedor = new Proveedores(id,nombre,nombreContacto,telefono);
		try {
			try {
				String sql = "INSERT INTO Suppliers (nombre_contacto,Empresa,Numero) VALUES ('"+nombreContacto+"','"+nombre+"',"+telefono+")";
				Conection conexion = new Conection();
				Statement st = conexion.getCon().createStatement();
				st.executeUpdate(sql);
				ResultSet rs;
				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
				alerta.setTitle("Advertencia");
				alerta.setContentText("Proveedor Agregado");
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
