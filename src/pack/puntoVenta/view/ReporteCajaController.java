package pack.puntoVenta.view;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pack.puntoVenta.Conexion;
import pack.puntoVenta.model.Caja;
import pack.puntoVenta.model.Conection;

public class ReporteCajaController extends Conection{
	private Connection con = null;
	private PreparedStatement ps;
	private ResultSet res;
	private static double corte;

	
	private ObservableList listaCorte = FXCollections.observableArrayList();

	 @FXML
	    private TableView<?> table_corte;
	 
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
	 public void verReporte(ActionEvent event) {
		 try{
			 String query =  "select * from dbo.Corte";
			 con = Conexion.getConection();
				ps = con.prepareStatement(query);
				res = ps.executeQuery();
				while (res.next()) {
					int id = res.getInt("id");
					String entrada = res.getString("Entrada");
					String salida = res.getString("Salida");
					String restante = res.getString("Restante");
					Caja caja = new Caja(id,entrada,salida,restante);
					listaCorte.add(caja);
				}
				table_corte.getItems().clear();
				table_corte.getItems().addAll(listaCorte);
			
		 }catch(Exception e) {
			 
		 }
		 	
	 }

		public static  void setCorte(double cortex) {
			corte = cortex;
		}

}
