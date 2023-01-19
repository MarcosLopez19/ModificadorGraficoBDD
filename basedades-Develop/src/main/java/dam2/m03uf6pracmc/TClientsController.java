/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dam2.m03uf6pracmc;

import dam2.m03uf6pracmc.Entitas.Cliente;
import dam2.m03uf6pracmc.Logica.LogicaCliente;
import static dam2.m03uf6pracmc.Utils.Utils.mostraMissatge;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

public class TClientsController implements Initializable {
    
    @FXML
    ObservableList<Cliente> clienteList = FXCollections.observableArrayList();
    
    @FXML
    TableView taulaCliente;
    
    @FXML
    private TableColumn<?, ?> columnaClienteEmail;
    
    @FXML
    private Button btnAtrasClientes;
    
    @FXML
    private TableColumn<?, ?> columnaClienteDNI;
    
    @FXML
    private TableColumn<?, ?> columnaLimiteDinero;
    
    @FXML
    private Button btnEliminarClientes;
    
    @FXML
    private TableColumn<?, ?> columnaClienteNombre;
    
    @FXML
    private Button btnAnadirClientes;
    
    @FXML
    private Button btnModificarClientes;
    
    @FXML
    private TableColumn<?, ?> columnaClienteTelefono;
    
    @FXML
    private TableColumn<?, ?> columnaClienteFnacimiento;
    
    @FXML
    private TableColumn<?, ?> cliente;
    
    // capa lógica
    LogicaCliente capaLogica;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        btnModificarClientes.setDisable(true);
        btnEliminarClientes.setDisable(true);
        //inicialitza la capa lógica, que inclou la connexió a BBDD
        try {
            capaLogica = new LogicaCliente();
            capaLogica.carregarDades();
            taulaCliente.setItems(capaLogica.getLlistaObservableCliente());
        } catch (SQLException ex) {
            mostraMissatge(1, "Error carregant dades: " + ex.toString());
        } catch (Exception ex) {
            mostraMissatge(1, "Error inicialitzant capa lógica: " + ex.toString());
        }

        //establim un vincle entre els atributs de la classe Clients i les columnes del tableView
        columnaClienteEmail.setCellValueFactory(new PropertyValueFactory<>("emailCliente"));
        columnaClienteDNI.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        columnaClienteNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnaClienteTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        columnaLimiteDinero.setCellValueFactory(new PropertyValueFactory<>("LimiteDinero"));
        columnaClienteFnacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        
    }
    
    @FXML
    void btnAtrasClientesOnAction(ActionEvent event) throws IOException {
        App.setRoot("MenuPrincipal");
        
    }
    
    @FXML
    void btnAnadirClientesOnAction(ActionEvent event) throws IOException {
        MClientController.esModificiar=false;
        App.setRoot("mClient");
//        try
//        {
//            capaLogica.afegirCliente();
//        } catch (NumberFormatException e){
//            mostraMissatge(1,"Dades incorrectes: " + e);
//        } catch (SQLException e) {
//            mostraMissatge(1,"Error a l'inserir les dades: " + e);
//        }
//        
//        desactivaSeleccioTbv();
    }
    
    @FXML
    void btnModificarClientesOnAction(ActionEvent event) throws IOException {
        MClientController.esModificiar=true;
        enviarDatos(getClienteFromTable());
        App.setRoot("mClient");
        
    }
    
    @FXML
    void btnEliminarClientesOnAction(ActionEvent event) {

        // capturem l'objecte seleccionat a la taula
        Cliente c = getClienteFromTable();
        
        try {
            capaLogica.eliminarCliente(c);
            
        } catch (SQLException e) {
            mostraMissatge(1, "Error al eliminar les dades: " + e);
        }
        
        desactivaSeleccioTbv();
        
    }
    
    @FXML
    private void OnMouseClicked(MouseEvent ev) {
        // si hem seleccionat un registre de la taula
        if (taulaCliente.getSelectionModel().getSelectedItem() != null) {
            // agafem les dades de l'objecte seleccionat i els traspassem
            // als camps del formulari

            //habilitem botó de modificar i eliminar
            btnModificarClientes.setDisable(false);
            btnEliminarClientes.setDisable(false);
        } else {
            desactivaSeleccioTbv();
        }
    }
    
   

    //metodo para guardar los datos selecionados de la tabla en un objeto de tipo cliente
    private Cliente getClienteFromTable() {
        Cliente ret = null;
        
        ret = (Cliente) taulaCliente.getSelectionModel().getSelectedItem();
        
        return ret;
    }
    
    private void desactivaSeleccioTbv() {
        //deshabilitem botóns i fila seleccionada
        btnModificarClientes.setDisable(true);
        btnEliminarClientes.setDisable(true);
        taulaCliente.getSelectionModel().clearSelection();
    }
    
    private void enviarDatos(Cliente c) {
        MClientController.c = c;
    }
//    private void modificacioClient(){
//         if(MClientController.esModificiar){
//            clienteActualizado
//        }else{
//            
//        }
//    }
}
