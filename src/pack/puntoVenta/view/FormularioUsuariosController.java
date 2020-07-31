package pack.puntoVenta.view;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import javafx.scene.layout.AnchorPane;
import pack.puntoVenta.Main;
import pack.puntoVenta.model.Conection;
import pack.puntoVenta.model.Usuario;

public class FormularioUsuariosController {
	
	private static double corte;
	@FXML
	private Button but_menuUsuario;
	
	@FXML
	private Label label_tituloAgregar;
	
    @FXML
    private TextField text_nombre;

    @FXML
    private TextField text_apellido;

    @FXML
    private TextField text_correo;

    @FXML
    private Button but_agregar;

    @FXML
    private PasswordField text_password;

    @FXML
    private TextField text_tipoUsuario;

    
    @FXML
    private Button but_menuAgregarUs;
    
    public static  void setCorte(double cortex) {
		corte = cortex;
	}
    
    @FXML
     void agregarNuevo(ActionEvent event) {
    	String nombreUsuario = text_nombre.getText();
    	String apellidoUsuario = text_apellido.getText();
    	String password = text_password.getText();
    	String correo = text_correo.getText();
    	String tipoUsuario = text_tipoUsuario.getText();
    	int id = 45;
    	Usuario usuario = new Usuario(id,nombreUsuario,password,apellidoUsuario,correo,tipoUsuario);
    	try {
    		try {
    			String sql = "INSERT INTO dbo.Users (Nombre,Apellido,[E-mail],Password,Type_User) VALUES ('"+nombreUsuario+"','"+apellidoUsuario+"','"+correo+"','"+password+"','"+tipoUsuario+"')";
    			Conection conexion = new Conection();
    			Statement st = conexion.getCon().createStatement();
    			st.executeUpdate(sql);
    			ResultSet rs;
    			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    			alerta.setTitle("Advertencia");
    			alerta.setContentText("Usuario Agregado");
    			alerta.initStyle(StageStyle.UTILITY);
    			alerta.setHeaderText(null);
    			alerta.showAndWait();
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
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
    

}
