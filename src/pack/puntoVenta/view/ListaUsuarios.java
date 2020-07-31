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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pack.puntoVenta.Conexion;
import pack.puntoVenta.model.Conection;
import pack.puntoVenta.model.Usuario;

public class ListaUsuarios extends Conection{
	private Connection con = null;
	private PreparedStatement ps;
	private ResultSet res;
	private Usuario usuario;
	private static double corte;
	
	@FXML
	private Button but_atrasUs;
	
	@FXML
	private Button but_agregarUs;
	@FXML
    private TextField nombreNuevo;

    @FXML
    private TextField apellidoNuevo;
    
    @FXML
    private TextField passwordNueva;

    @FXML
    private TextField correoNuevo;
    @FXML
    private TextField tipoUsuarioNuevo;
    
    @FXML
    private Button but_actualizarBase;
	
    @FXML
    private TableView<Usuario> Table_usuarios;
    private ObservableList listaUsuarios = FXCollections.observableArrayList();
    
    
    public static  void setCorte(double cortex) {
		corte = cortex;
	}
    
    public void initialize() {
    	mostrarLista();
	}
	
    
    @FXML
     void agregarUsuario(ActionEvent event) throws IOException {
    	
    	Parent view = FXMLLoader.load(getClass().getResource("FormularioUsuarios.fxml"));
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FromularioUsuarios.fxml"));
   		FormularioUsuariosController controller = fxmlLoader.<FormularioUsuariosController>getController();
   		controller.setCorte(corte);
    	
    	Scene scene = new Scene(view,800,700);
    	
    	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	st.setScene(scene);
    	st.show();
    }
    @FXML
    void backMenu(ActionEvent event) throws IOException {
    	
    	Parent view = FXMLLoader.load(getClass().getResource("Menu.fxml"));
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
   		MenuController controller = fxmlLoader.<MenuController>getController();
   		controller.setCorte(corte);
    	
    	Scene scene = new Scene(view,800,700);
    	
    	Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	st.setScene(scene);
    	st.show();
    }
    @FXML
	public void mostrarLista() {
		try{
			listaUsuarios.clear();
			con = Conexion.getConection();
			ps = con.prepareStatement("SELECT * FROM dbo.Users" );
			res = ps.executeQuery();
			while (res.next()) {
				String nombre = res.getString("Nombre");
				String password = res.getString("Password");
				String apellido = res.getString("Apellido");
				String correo = res.getString("E-mail");
				String tipoUser= res.getString("Type_User");
				int id = res.getInt("id_Users");
				Usuario usuario = new Usuario(id,nombre,password,apellido,correo,tipoUser);
				listaUsuarios.addAll(usuario);
			}
			Table_usuarios.getItems().clear();
			Table_usuarios.getItems().addAll(listaUsuarios);
		}catch (Exception e) {
			
		}
	}
    
    @FXML 
    public void remove() {
    	Usuario usuario2 =(Usuario) this.Table_usuarios.getSelectionModel().getSelectedItem();
		String nombre = usuario2.getNombreUsuario();
		PreparedStatement ps = null;
		Connection conexion = getCon();
		String sql = "DELETE FROM Users WHERE Nombre="+"'"+nombre+"'";
		System.out.println(conexion);
		try {
			ps = conexion.prepareStatement(sql);
			
			ps.execute();
			
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Advertencia");
			alerta.setContentText("Usuario eliminado");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
			initialize();

		}catch(Exception e) {
			System.out.println("Error: "+e);
			
		}
    }
    
    @FXML
    public void editar() {
    	Usuario usuario2 = (Usuario) this.Table_usuarios.getSelectionModel().getSelectedItem();
    	nombreNuevo.setText(usuario2.getNombreUsuario());
    	apellidoNuevo.setText(usuario2.getApellidoUsuario());
    	correoNuevo.setText(usuario2.getCorreo());	
    	tipoUsuarioNuevo.setText(usuario2.getTipoUsuario());
    	passwordNueva.setText(usuario2.getPassword());
    	usuario = usuario2;
    	nombreNuevo.setVisible(true);
    	apellidoNuevo.setVisible(true);
    	passwordNueva.setVisible(true);
    	correoNuevo.setVisible(true);
    	tipoUsuarioNuevo.setVisible(true);
    	but_actualizarBase.setVisible(true);
    }
    @FXML
    public void agregarBase() {
    	PreparedStatement ps = null;
    	Connection conexion = getCon();
    	String nombre = nombreNuevo.getText();
    	String apellido = apellidoNuevo.getText();
    	String correo = correoNuevo.getText();
    	String password = passwordNueva.getText();
    	String tipoUsuario = tipoUsuarioNuevo.getText();
    	int idaux = usuario.getId_usuario();
		String sql = "UPDATE dbo.Users SET Nombre='"+nombre+"', "+"Apellido="+"'"+apellido+"',"+" [E-mail]='"+correo+"',"+"Password='"+password+"',"+" Type_User= '"+tipoUsuario+"' WHERE id_Users="+idaux; 
		try {
			ps = conexion.prepareStatement(sql);
			ps.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    

}
