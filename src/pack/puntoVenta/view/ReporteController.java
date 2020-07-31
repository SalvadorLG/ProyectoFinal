package pack.puntoVenta.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pack.puntoVenta.Conexion;
import pack.puntoVenta.model.Conection;
import pack.puntoVenta.model.Recibo;

public class ReporteController extends Conection{
	private Connection con = null;
	private PreparedStatement ps;
	private ResultSet res;
	private ObservableList listaReportes = FXCollections.observableArrayList();
	private ObservableList listaId = FXCollections.observableArrayList();
	private static double corte;
	
	@FXML
    private TableView<?> table_reportes;
	
	@FXML
    private ComboBox comboReportes;
	  
	@FXML
	private TextField totalTicket;
	
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
	
	public static  void setCorte(double cortex) {
		corte = cortex;
	}
	
	public void initialize() {
		try {
			String query =  "select DISTINCT id_ticket from dbo.Historial";
			con = Conexion.getConection();
			ps = con.prepareStatement(query);
			res = ps.executeQuery();
			while (res.next()) {
				int id = res.getInt("id_ticket");
				listaId.add(""+id);
			}
			for(int i=0;i<listaId.size();i++) {
				String query2 =  "select * from dbo.Historial where id_ticket= "+listaId.get(i);
				con = Conexion.getConection();
				ps = con.prepareStatement(query2);
				res = ps.executeQuery();
				while (res.next()) {
					int id = res.getInt("id_ticket");
					int idProd = res.getInt("id_Producto");
					int cantidad = res.getInt("cantidad");
					int total = res.getInt("total");
					String nombre = res.getString("Nombre_Prod");
					Recibo ticket = new Recibo(idProd,cantidad,total,id,nombre);
					listaReportes.add(ticket);
					
				}
				
			}
			table_reportes.getItems().clear();
			table_reportes.getItems().addAll(listaReportes);
			comboReportes.getItems().clear();
			comboReportes.getItems().addAll(listaId);
			comboReportes.valueProperty().addListener(new ChangeListener<String>(){
				@Override
				public void changed(ObservableValue ov,String valorAnterior,String valorNuevo) {
					String total = calcularTotal(valorNuevo);
					totalTicket.setText(total);
				}
			});
			
			comboReportes.setValue(listaId.get(0));
		}catch (Exception e) {
			
		}
	}
	
	
	
	private String calcularTotal(String x) {
		double total = 0;
		int id = Integer.parseInt(x);
		for(int i=0;i<listaReportes.size();i++) {
			Recibo ticket = (Recibo) listaReportes.get(i);
			if(id==ticket.getId_ticket()) {
				total+= ticket.getTotal();
			}
		}
		return ""+ total;
	}

}
