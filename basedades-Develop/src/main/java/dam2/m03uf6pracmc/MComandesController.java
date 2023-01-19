/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dam2.m03uf6pracmc;

import dam2.m03uf6pracmc.Entitas.Comanda;
import dam2.m03uf6pracmc.Entitas.DetallComanda;
import dam2.m03uf6pracmc.Entitas.Producto;
import dam2.m03uf6pracmc.Logica.LogicaDetallComanda;
import dam2.m03uf6pracmc.Logica.logicaComanda;
import dam2.m03uf6pracmc.Logica.logicaProducto;
import dam2.m03uf6pracmc.Utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author pablogomez
 */
public class MComandesController implements Initializable {

    /**
     * Initializes the controller class.
     *
     *
     */
    @FXML
    public static boolean esModificar;
    
    @FXML
    public static Comanda c;
    
    @FXML
    ObservableList<Producto> productoList = FXCollections.observableArrayList();
    
    @FXML
    TableView twProducto;
    
    @FXML
    ObservableList<DetallComanda> comandaList = FXCollections.observableArrayList();
    
    @FXML
    TableView twDetallComanda;
    
    @FXML
    private Button btnAceptar;
    
    @FXML
    private Text lbNombre;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnAnadir;
    
    @FXML
    private Button btnEliminar;
    
    @FXML
    private TableColumn<?, ?> Nombre;
    
    @FXML
    private TableColumn<?, ?> Codigo;
    
    @FXML
    private TableColumn<?, ?> Descripcion;
    
    @FXML
    private TableColumn<?, ?> Stock;
    
    @FXML
    private TableColumn<?, ?> Precio;
    
    @FXML
    private TableColumn<?, ?> NumeroComanda;
    
    @FXML
    private TableColumn<?, ?> NumeroProducte;
    
    @FXML
    private TableColumn<?, ?> precio;
    
    @FXML
    private TableColumn<?, ?> cantidad;
    
    @FXML
    private TextField fEntrega;
    
    @FXML
    private TextField required;
    
    @FXML
    private TextField fCompra;
    
    @FXML
    private TextField email;
    
    @FXML
    private TextField NumeroComandaa;
    
    logicaProducto capaLogica;
    
    LogicaDetallComanda capaLogicaDetall;
    
    logicaComanda capaLogicaComanda;
    
    @FXML
    void SwitchToComanda(ActionEvent event) throws IOException {
        App.setRoot("Comandes");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnAnadir.setDisable(true);
        btnEliminar.setDisable(true);
        //inicialitza la capa lógica, que inclou la connexió a BBDD
        try {
            capaLogica = new logicaProducto();
            capaLogica.carregarDadesProductos();
            twProducto.setItems(capaLogica.getLlistaObservableProducto());
        } catch (SQLException ex) {
            Utils.mostraMissatge(1, "Error carregant dades: " + ex.toString());
        } catch (Exception ex) {
            Utils.mostraMissatge(1, "Error inicialitzant capa lógica: " + ex.toString());
        }

        //establim un vincle entre els atributs de la classe Clients i les columnes del tableView
        Codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        Precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        
        try {
            capaLogicaComanda = new logicaComanda();
            capaLogicaComanda.carregarDades();
        } catch (SQLException ex) {
            Utils.mostraMissatge(1, "Error carregant dades: " + ex.toString());
        } catch (Exception ex) {
            Utils.mostraMissatge(1, "Error inicialitzant capa lógica: " + ex.toString());
        }
        
        if (esModificar) {
            setComanda(c);
            email.setEditable(false);
            NumeroComandaa.setEditable(false);
            try {
            capaLogicaDetall = new LogicaDetallComanda();
            capaLogicaDetall.carregarDades(c.getNumeroComanda());
            twDetallComanda.setItems(capaLogicaDetall.getLlistaObservableDetallComanda());
        } catch (SQLException ex) {
            Utils.mostraMissatge(1, "Error carregant dades: " + ex.toString());
        } catch (Exception ex) {
            Utils.mostraMissatge(1, "Error inicialitzant capa lógica: " + ex.toString());
        }

        //establim un vincle entre els atributs de la classe Clients i les columnes del tableView
        NumeroComanda.setCellValueFactory(new PropertyValueFactory<>("numeroComanda"));
        NumeroProducte.setCellValueFactory(new PropertyValueFactory<>("numeroProduccto"));
        cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        precio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        
        } else {
            email.setEditable(true);
            NumeroComanda.setEditable(true);
        }
        
    }
    
    @FXML
    void OnMouseClickedProducte(MouseEvent ev) {
        
        if (twProducto.getSelectionModel().getSelectedItem() != null) {
            // agafem les dades de l'objecte seleccionat i els traspassem
            // als camps del formulari

            //habilitem botó de modificar i eliminar
            btnAnadir.setDisable(false);
            
        } else {
            desactivaSeleccioTbv();
        }
    }
    
    @FXML
    void OnMouseClickedDetallComanda(MouseEvent ev) {
        
        if (twDetallComanda.getSelectionModel().getSelectedItem() != null) {
            // agafem les dades de l'objecte seleccionat i els traspassem
            // als camps del formulari

            //habilitem botó de modificar i eliminar
            btnEliminar.setDisable(false);
            
        } else {
            desactivaSeleccioTbvComanda();
        }
    }
    
    @FXML
    void OnActionAnadir(ActionEvent event) throws SQLException {
        
        capaLogicaDetall.afegirDetallComanda(getDetallComanda());
        
    }
    
    @FXML
    void OnActionEliminar(ActionEvent event) throws SQLException {
        
        DetallComanda dc = getDetallComandaFromTable();
        
        try {
            capaLogicaDetall.eliminarDetallComanda(dc);
        } catch (SQLException e) {
            Utils.mostraMissatge(1, "error al eliminar les dades: " + e);
        }
        
        desactivaSeleccioTbvComanda();
        
    }
    
    @FXML
    void OnActionAceptar(ActionEvent event) throws Exception {
        
        if (esModificar) {
            try{
                capaLogicaComanda.modificarComanda(getComandaTextFild());
                App.setRoot("Comandes");
            }catch(RuntimeException a){
            MensajeInfo.mostraError("Fechas incorrectas");
        }
        } else {
            try{
                capaLogicaComanda.afegirComanda(getComandaTextFild());
                 App.setRoot("Comandes");
            }catch(SQLIntegrityConstraintViolationException e){
            MensajeInfo.mostraError("Id i/o email incorrectos");
        }catch(RuntimeException a){
            MensajeInfo.mostraError("Fechas incorrectas");
        }
        }
    }
    
    private void setComanda(Comanda c) {
        
        if (c != null) {
            
            fEntrega.setText(String.valueOf(c.getDataComanda()));
            NumeroComandaa.setText(String.valueOf(c.getNumeroComanda()));
            required.setText(String.valueOf(c.getDataRequerida()));
            fCompra.setText(String.valueOf(c.getShippedDate()));
            email.setText(c.getEmailCliente());
            
        }
    }
    
    private Producto getProducteFromTable() {
        
        Producto ret = null;
        
        ret = (Producto) twProducto.getSelectionModel().getSelectedItem();
        
        return ret;
    }
    
    private DetallComanda getDetallComandaFromTable() {
        
        DetallComanda ret = null;
        
        ret = (DetallComanda) twDetallComanda.getSelectionModel().getSelectedItem();
        
        return ret;
    }
    
    private DetallComanda getDetallComanda() {
        
        DetallComanda dc = new DetallComanda();
        
        dc.setNumeroComanda(c.getNumeroComanda());
        dc.setNumeroProduccto(getProducteFromTable().getCodigo());
        dc.setCantidad(5);
        dc.setPrecio(getProducteFromTable().getPrecio());
        
        return dc;
        
    }
    
    private Comanda getComandaTextFild() throws ParseException {
        
        Comanda c = new Comanda();
        
        c.setNumeroComanda(Integer.parseInt(NumeroComandaa.getText()));
        c.setDataComanda(java.sql.Date.valueOf(fCompra.getText()));
        c.setDataRequerida(java.sql.Date.valueOf(required.getText()));
        c.setShippedDate(java.sql.Date.valueOf(fEntrega.getText()));
        c.setEmailCliente(email.getText());
        
        return c;
        
    }
    
    private void desactivaSeleccioTbv() {
        //deshabilitem botóns i fila seleccionada
        btnAnadir.setDisable(true);
        twProducto.getSelectionModel().clearSelection();
    }
    
    private void desactivaSeleccioTbvComanda() {
        //deshabilitem botóns i fila seleccionada
        btnEliminar.setDisable(true);
        twDetallComanda.getSelectionModel().clearSelection();
    }
    
}
