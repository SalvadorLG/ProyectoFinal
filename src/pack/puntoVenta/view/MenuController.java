package pack.puntoVenta.view;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pack.puntoVenta.Main;

public class MenuController {
	private static double corte;
	
	@FXML 
	private Label nombre;
	
	@FXML
	private Button but_menuAgregar;
	
	@FXML
	private Button but_menuUsuarios;
	
	@FXML
	private Button but_menuVenta;
	
	 @FXML
	    private Button reportesVent;
	 
	
	 
	 public static  void setCorte(double cortex) {
			corte = cortex;
		}
	 
	 
	@FXML
	void addUsuario(ActionEvent event) throws IOException {
		 
		Parent view = FXMLLoader.load(getClass().getResource("FormularioUsuarios.fxml"));
    	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormulariosUsuarios.fxml"));
   		FormularioUsuariosController controller = fxmlLoader.<FormularioUsuariosController>getController();
   		controller.setCorte(corte);
    	Scene scene = new Scene(view,800,700);
    	
    	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	st.setScene(scene);
    	st.show();
	}
	@FXML
	void verLista(ActionEvent event) throws IOException {
		 
		Parent view = FXMLLoader.load(getClass().getResource("ListaUsuarios.fxml"));
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListaUsuarios.fxml"));
   		ListaUsuarios controller = fxmlLoader.<ListaUsuarios>getController();
   		controller.setCorte(corte);
   		Scene scene = new Scene(view,800,700);
    	
    	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	st.setScene(scene);
    	st.show();
	}
	@FXML
	void addVenta(ActionEvent event) throws IOException {
		 
		Parent view = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
    	
    	Scene scene = new Scene(view,800,700);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ticket.fxml"));
   		TicketController controller = fxmlLoader.<TicketController>getController();
   		controller.setCorte(corte);
    	
    	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	st.setScene(scene);
    	st.show();
	}
	
	@FXML
	void cerrarSesion(ActionEvent event) throws IOException {
		 
		Parent view = FXMLLoader.load(getClass().getResource("Farmacia.fxml"));
    	
    	Scene scene = new Scene(view,800,700);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Farmacia.fxml"));
   		FarmaciaController controller = fxmlLoader.<FarmaciaController>getController();
   		controller.setCorte(corte);
    	
    	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	st.setScene(scene);
    	st.show();
	}
	@FXML
	void verListaProd(ActionEvent event) throws IOException {
	
		Parent view = FXMLLoader.load(getClass().getResource("ListaProductos.fxml"));
    	
    	Scene scene = new Scene(view,800,700);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListaProductos.fxml"));
   		ListaProductosController controller = fxmlLoader.<ListaProductosController>getController();
   		controller.setCorte(corte);
    	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	st.setScene(scene);
    	st.show();
	}
	
	@FXML
	void addProducto(ActionEvent event) throws IOException {
	
		Parent view = FXMLLoader.load(getClass().getResource("FormularioProducto.fxml"));
    	
    	Scene scene = new Scene(view,800,700);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularioProducto.fxml"));
   		FormularioProductoController controller = fxmlLoader.<FormularioProductoController>getController();
   		controller.setCorte(corte);
    	
    	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	st.setScene(scene);
    	st.show();
	}
	@FXML
	void verListaProv(ActionEvent event) throws IOException {
		
		Parent view = FXMLLoader.load(getClass().getResource("Proveedores.fxml"));
	   	
	   	Scene scene = new Scene(view,800,700);
	   	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Proveedores.fxml"));
   		ProveedoresController controller = fxmlLoader.<ProveedoresController>getController();
   		controller.setCorte(corte);
	   	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
	   	
	   	st.setScene(scene);
	   	st.show();
		
	}
	
	@FXML
	void verReportes(ActionEvent event) throws IOException {
		
		Parent view = FXMLLoader.load(getClass().getResource("Reportes.fxml"));
		Scene scene = new Scene(view,800,700);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reportes.fxml"));
   		ReporteController controller = fxmlLoader.<ReporteController>getController();
   		controller.setCorte(corte);
	   	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
	   	
	   	st.setScene(scene);
	   	st.show();
	}
	@FXML
	void verReporteCaja(ActionEvent event) throws IOException {
	
		Parent view = FXMLLoader.load(getClass().getResource("ReporteCaja.fxml"));
		Scene scene = new Scene(view,800,700);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReporteCaja.fxml"));
   		ReporteController controller = fxmlLoader.<ReporteController>getController();
   		controller.setCorte(corte);
	   	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
	   	
	   	st.setScene(scene);
	   	st.show();
	}
		

}
