package pack.puntoVenta.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pack.puntoVenta.Conexion;
import pack.puntoVenta.model.Conection;
import pack.puntoVenta.model.Producto;
import pack.puntoVenta.model.Proveedores;

public class ProveedoresController extends Conection{
	private Connection con = null;
	private PreparedStatement ps;
	private ResultSet res;
	private static double corte;
	@FXML 
	private Button but_menuProv;
	
	@FXML
	private Button but_agregarProv;
	
	@FXML
	private Label label_Provedorres;
	
	@FXML 
	private Button but_editarProv;
	
	@FXML 
	private Button but_eliminarProv;
	
	@FXML
	private TableView<?> table_Proveedores;
	private ObservableList listaProveedores = FXCollections.observableArrayList();
	
	public static  void setCorte(double cortex) {
		corte = cortex;
	}
	
	public void initialize() {
		mostrarLista();
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
	void formularioProv(ActionEvent event) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource("FormularioProveedores.fxml"));
    	
    	Scene scene = new Scene(view);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularioProveedores.fxml"));
   		FormularioProveedoresController controller = fxmlLoader.<FormularioProveedoresController>getController();
   		controller.setCorte(corte);
    	
    	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	st.setScene(scene);
    	st.show();
	}
	@FXML
	public void mostrarLista() {
		try{
			con = Conexion.getConection();
			ps = con.prepareStatement("SELECT * FROM dbo.Suppliers " );
			res = ps.executeQuery();
			while (res.next()) {
				String nombre = res.getString("nombre_contacto");
				String empresa = res.getString("Empresa");
				int numero = res.getInt("Numero");
				int id = res.getInt("id_Suppliers");
				Proveedores proveedor = new Proveedores(id,empresa,nombre,numero);
				listaProveedores.addAll(proveedor);
			}
			table_Proveedores.getItems().addAll(listaProveedores);
		}catch (Exception e) {
		}
	}
	@FXML 
	public void remove() {
		Proveedores proveedor2 = (Proveedores) this.table_Proveedores.getSelectionModel().getSelectedItem();
		String nombre = proveedor2.getNombreEmpresa();
		PreparedStatement ps = null;
		Connection conexion = getCon();
		String sql = "DELETE FROM Suppliers WHERE nombre_producto="+"'"+nombre+"'";
		try {
			ps = conexion.prepareStatement(sql);
			
			ps.execute();
			
			JOptionPane.showMessageDialog(null, "Producto eliminado");
			initialize();

		}catch(Exception e) {
			System.out.println("Error: "+e);
			
		}
	}
	
}
