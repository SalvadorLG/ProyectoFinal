package pack.puntoVenta;
	


import javafx.application.Application;


import javafx.stage.Stage;
//import pack.puntoVenta.view.FarmaciaController;
//import pack.puntoVenta.view.FormularioUsuarios;
//import pack.puntoVenta.view.ListaUsuarios;
import pack.puntoVenta.view.*;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
    Stage farmacia = new Stage();
    Stage formularioProd = new Stage();
    Stage formulario = new Stage();
    Stage listaProductos = new Stage();
    Stage lista = new Stage();
    Stage menu = new Stage();
    Stage ticket = new Stage();
  
	@Override
	public void start(Stage primaryStage) {
		abrir();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void abrir() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/Farmacia.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Farmacia.fxml"));
			Scene scene = new Scene(root,620,720);
			//FarmaciaController controlador = loader.getController();
			//scene.getStylesheets().add(getClass().getResource("pack.puntoVenta").toExternalForm());
			farmacia.setTitle("Punto de Venta");
			farmacia.setScene(scene);
			farmacia.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
