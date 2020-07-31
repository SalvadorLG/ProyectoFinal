package pack.puntoVenta.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pack.puntoVenta.Conexion;


public class FarmaciaController {
	private Connection con = null;
	private PreparedStatement ps;
	private ResultSet res;
	private static double corte;
	
    @FXML
    private Button But_ingresar;

    @FXML
    private TextField textUsuario;

    @FXML
    private PasswordField textContra;
    
	 @FXML
	    private TextField dinero;

   @FXML
   void cambiarVista(ActionEvent event) throws IOException, SQLException {
	   if(VerificarUser()) {
		   corte = Double.parseDouble(dinero.getText());
		   Parent view = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
	   		MenuController controller = fxmlLoader.<MenuController>getController();
	   		controller.setCorte(corte);
	   		Scene scene = new Scene(view,750,750);
	   		
	   		Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
	   		
	   		st.setScene(scene);
	   		st.show();
	   }else {
		   Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Advertencia");
			alerta.setContentText("Contraseña, usuario o cantidad igresada no valida");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
	   }
	   
	   
    }

	private boolean VerificarUser() throws SQLException {
		boolean verificar = false;
		try{
			String passwordIngresado = textContra.getText();
			con = Conexion.getConection();
			ps = con.prepareStatement("SELECT * FROM dbo.Users WHERE Nombre ='" + textUsuario.getText()+"'");
			res = ps.executeQuery();
			while (res.next()) {
				String password = res.getString("Password");
				if(passwordIngresado.equals(password)) {
					verificar = true;
				}
				
			}
		}catch (Exception e) {
			System.out.println("EL usuario no existe");
		}
		
		
		return verificar;
	}
	public static  void setCorte(double cortex) {
		corte = cortex;
	}
}
