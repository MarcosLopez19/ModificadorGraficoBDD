/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dam2.m03uf6pracmc;

import dam2.m03uf6pracmc.Entitas.Producto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import dam2.m03uf6pracmc.Logica.logicaProducto;
import dam2.m03uf6pracmc.Utils.Utils;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author guiup
 */
public class TProductosController implements Initializable {
       
    @FXML
    ObservableList<Producto> ProductoList = FXCollections.observableArrayList();
    @FXML
    TableView tablaProductos;
    
    @FXML
    private TableColumn<?, ?> columnaProductosPrecio;

    @FXML
    private TableColumn<?, ?> columnaProductosCodigo;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnAtrasProductos;

    @FXML
    private Button btnAnadir;

    @FXML
    private TableColumn<?, ?> columnaProductosStock;

    @FXML
    private Button btnModificar;

    @FXML
    private Text tituloProductos;

    @FXML
    private TableColumn<?, ?> columnaProductosDescripcion;

    @FXML
    private TableColumn<?, ?> columnaProductosNombre;
    
    
    // capa lógica
    logicaProducto capaLogica;
    
   //Al inicializar ejecutamos que rellene la tabla 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            btnModificar.setDisable(true);
            btnEliminar.setDisable(true);
        //inicialitza la capa lógica, que inclou la connexió a BBDD
        try {
            capaLogica = new logicaProducto();
            capaLogica.carregarDadesProductos();
            tablaProductos.setItems(capaLogica.getLlistaObservableProducto());
        } catch (SQLException ex) {
            Utils.mostraMissatge(1, "Error carregant dades: " + ex.toString());
        } catch (Exception ex) {
            Utils.mostraMissatge(1, "Error inicialitzant capa lógica: " + ex.toString());
        }

        //establim un vincle entre els atributs de la classe Productos i les columnes del tableView
        columnaProductosCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnaProductosNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaProductosDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnaProductosStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        columnaProductosPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

    }
    
    //volvemos al menu principal
    @FXML
    public void btnAtrasProductosOnAction(ActionEvent event) throws IOException {
        App.setRoot("MenuPrincipal");
    }
    //Vamos a la pantalla de añadir/modificar y le pasamos como false el boolean de si es para modificar
    public void btnAnadirProductoOnAction(ActionEvent event) throws IOException {
        MProducteController.esModificiar=false;
        App.setRoot("mProducte");
    }
    //Vamos a la pantalla de añadir/modificar y le pasamos como true el boolean de si es para modificar y los parametros del objeto seleccionado
    public void btnModificarProductoOnAction(ActionEvent event) throws IOException {
        MProducteController.esModificiar=true;
        enviarDatos(getProductoFromTable());
        App.setRoot("mProducte");
    }
    

    //Eliminamos el producto de la base de datos
    public void btnEliminarProductoOnAction(ActionEvent event) throws IOException {
        // capturem l'objecte seleccionat a la taula
        Producto p = getProductoFromTable();
        
        try {
            capaLogica.eliminarProducto(p);
            
        } catch (SQLException e) {
            Utils.mostraMissatge(1, "Error al eliminar les dades: " + e);
        }
        
        desactivaSeleccioTbv();
    }
    
    //Detectamos a que objeto de la tabla hacemos click
    @FXML
    private void OnMouseClicked(MouseEvent ev) {
        // si hem seleccionat un registre de la taula
        if (tablaProductos.getSelectionModel().getSelectedItem() != null) {
            // agafem les dades de l'objecte seleccionat i els traspassem
            // als camps del formulari
            //habilitem botó de modificar i eliminar
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);
        } else {
            desactivaSeleccioTbv();
        }
    }
    
    //metodo para guardar los datos selecionados de la tabla en un objeto de tipo producto
    private Producto getProductoFromTable() {
        Producto ret = null;
        
        ret = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
        
        return ret;
    }
    
    private void desactivaSeleccioTbv() {
        //deshabilitem botóns i fila seleccionada
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        tablaProductos.getSelectionModel().clearSelection();
    }
    
    private void enviarDatos(Producto p) {
        MProducteController.p = p;
    }
}
