package pack.puntoVenta.view;


import java.io.IOException;





import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import pack.puntoVenta.model.Conection;
import pack.puntoVenta.model.Producto;

public class ListaProductosController extends Conection{
	private Connection con = null;
	private PreparedStatement ps;
	private ResultSet res;
	private Producto producto;
	private static double corte;
	@FXML
	private Label label_productosList;
	
	@FXML
	private Button but_newProducto;
	
	@FXML
	private Button but_menuProducto;
	
	@FXML
	private Button but_eliminarProd;

    @FXML
    private TextField NombreNuevo;

    @FXML
    private TextField marcaNuevo;
    
    

    @FXML
    private TextField cantidadNuevo;

    @FXML
    private TextField precioNuevo;
    
    @FXML
    private Button but_agregar;
	
	@FXML
	private TableView<Producto> table_producto;
	private ObservableList listaProductos = FXCollections.observableArrayList();
	
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
    void agregarProductos(ActionEvent event) throws IOException {
    	
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
	public void mostrarLista() {
		try{
			table_producto.getItems().clear();
			con = Conexion.getConection();
			ps = con.prepareStatement("SELECT * FROM dbo.Productos " );
			res = ps.executeQuery();
			while (res.next()) {
				String nombre = res.getString("nombre_producto");
				String marca = res.getString("marca_producto");
				int precio = res.getInt("precio_unitario");
				int cantidad= res.getInt("cantidad_producto");
				int id = res.getInt("id_product");
				Producto producto = new Producto(id,nombre,marca,precio,cantidad);
				listaProductos.addAll(producto);
			}
			table_producto.getItems().clear();
			table_producto.getItems().addAll(listaProductos);			
			
		}catch (Exception e) {
			
		}
	}
	@FXML
	public void remove() {
		Producto producto2=this.table_producto.getSelectionModel().getSelectedItem();
		String nombre=producto2.getNombreProducto();
		PreparedStatement ps = null;
		Connection conexion = getCon();
		String sql = "DELETE FROM Productos WHERE nombre_producto="+"'"+nombre+"'";
		try {
			ps = conexion.prepareStatement(sql);
			
			ps.execute();
			
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Advertencia");
			alerta.setContentText("Prodcuto eliminado");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
			initialize();

		}catch(Exception e) {
			System.out.println("Error: "+e);
			
		}
		
	}
	@FXML
	public void modificarProd() {
		Producto producto2=this.table_producto.getSelectionModel().getSelectedItem();
		NombreNuevo.setText(producto2.getNombreProducto());
		marcaNuevo.setText(producto2.getMarcaProducto());
		cantidadNuevo.setText(toString().valueOf(producto2.getCantidadProducto()));
		precioNuevo.setText(toString().valueOf(producto2.getPrecioProducto()));
		producto = producto2;
		NombreNuevo.setVisible(true);
		marcaNuevo.setVisible(true);
		cantidadNuevo.setVisible(true);
		precioNuevo.setVisible(true);
		but_agregar.setVisible(true);
		
	}
	@FXML
	public void agregarBase() {
		PreparedStatement ps = null;
		Connection conexion = getCon();
		String marca = marcaNuevo.getText();
		String nombre= NombreNuevo.getText();
		int cantidad = Integer.parseInt(cantidadNuevo.getText());
		int precio = Integer.parseInt(precioNuevo.getText());
		int idaux = producto.getId();
		Producto aux = new Producto(idaux, nombre, marca, precio, cantidad);
		String sql = "UPDATE Productos SET nombre_producto='"+nombre+"', "+"marca_producto="+"'"+marca+"',"+" precio_unitario="+precio+","+" cantidad_producto="+cantidad+" WHERE id_product="+idaux;
		try {
			ps = conexion.prepareStatement(sql);
			ps.execute();
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
		
	}
	
	
	


}
