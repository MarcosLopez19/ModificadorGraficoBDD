package dam2.m03uf6pracmc;

import dam2.m03uf6pracmc.Entitas.Comanda;
import dam2.m03uf6pracmc.Entitas.DetallComanda;
import dam2.m03uf6pracmc.Logica.LogicaDetallComanda;
import dam2.m03uf6pracmc.Logica.logicaComanda;
import dam2.m03uf6pracmc.Utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
public class ComandesController implements Initializable {

    @FXML
    ObservableList<Comanda> comandaList = FXCollections.observableArrayList();
    
    @FXML
    TableView taulaComanda;

    @FXML
    ObservableList<DetallComanda> comandaDetallList = FXCollections.observableArrayList();

    @FXML
    TableView taulaDetallComanda;

    @FXML
    private Button btnAtras;

    @FXML
    private Label lbFechaInicio;

    @FXML
    private Button btnEliminar;

    @FXML
    private TextField tfFechaInicio;

    @FXML
    private Label lbTitulo;

    @FXML
    private TextField tfFechaFinal;

    @FXML
    private Button btnAnadir;

    @FXML
    private Button btnModificar;

    @FXML
    private Label lbFechaFinal;

    @FXML
    private TableColumn<?, ?> NumeroComanda;
    @FXML
    private TableColumn<?, ?> NumeroComanda2;

    @FXML
    private TableColumn<?, ?> shippedFecha;

    @FXML
    private TableColumn<?, ?> requiredFecha;

    @FXML
    private TableColumn<?, ?> fecha;

    @FXML
    private TableColumn<?, ?> cliente;

    @FXML
    private TableColumn<?, ?> CodiProducte;

    @FXML
    private TableColumn<?, ?> Cantidad;

    @FXML
    private TableColumn<?, ?> Precio;

    //Esta función hará que cuando cliquemos al boton nos lleve a la pantalla Menu Principal
    @FXML
    void switchToMenu() throws IOException {
        App.setRoot("MenuPrincipal");
    }

    //Esta función nos lleva a la pantalla modificar Comandes
    @FXML
    void SwitchToModificarComanda(ActionEvent event) throws IOException {
        
        MComandesController.esModificar = true;
        
        enviarDatos(getComandaFromTable());
        
        App.setRoot("mComandes");
    }

    logicaComanda capaLogica;

    LogicaDetallComanda capaLogicaDetall;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        //inicialitza la capa lógica, que inclou la connexió a BBDD
        try {
            capaLogica = new logicaComanda();
            capaLogica.carregarDades();
            taulaComanda.setItems(capaLogica.getLlistaObservableComanda());
        } catch (SQLException ex) {
            Utils.mostraMissatge(1, "Error carregant dades: " + ex.toString());
        } catch (Exception ex) {
            Utils.mostraMissatge(1, "Error inicialitzant capa lógica: " + ex.toString());
        }

        //establim un vincle entre els atributs de la classe Clients i les columnes del tableView
        NumeroComanda.setCellValueFactory(new PropertyValueFactory<>("numeroComanda"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("dataComanda"));
        requiredFecha.setCellValueFactory(new PropertyValueFactory<>("dataRequerida"));
        shippedFecha.setCellValueFactory(new PropertyValueFactory<>("shippedDate"));
        cliente.setCellValueFactory(new PropertyValueFactory<>("emailCliente"));
    }

    @FXML
    void eliminarComandaOnAction(ActionEvent event) {

        // capturem l'objecte seleccionat a la taula
        Comanda c = getClienteFromTable();

        try {
            capaLogicaDetall.eliminarDetallComandaTots(c);
            capaLogica.eliminarComanda(c);
            
        } catch (SQLException e) {
            Utils.mostraMissatge(1, "Error al eliminar les dades: " + e);
        }

        desactivaSeleccioTbv();
    }
    
    @FXML
    void anadirComandaOnAction(ActionEvent event) throws IOException {
         MComandesController.esModificar = false;
        App.setRoot("mComandes");
    }

    @FXML
     void OnMouseClicked(MouseEvent ev) {
        // si hem seleccionat un registre de la taula
        if (taulaComanda.getSelectionModel().getSelectedItem() != null) {
            // agafem les dades de l'objecte seleccionat i els traspassem
            // als camps del formulari

            //habilitem botó de modificar i eliminar
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);

            try {
                capaLogicaDetall = new LogicaDetallComanda();
                capaLogicaDetall.carregarDades(getClienteFromTable().getNumeroComanda());
                taulaDetallComanda.setItems(capaLogicaDetall.getLlistaObservableDetallComanda());
            } catch (SQLException ex) {
                Utils.mostraMissatge(1, "Error carregant dades: " + ex.toString());
            } catch (Exception ex) {
                Utils.mostraMissatge(1, "Error inicialitzant capa lógica: " + ex.toString());
            }

            //establim un vincle entre els atributs de la classe Clients i les columnes del tableView
            NumeroComanda2.setCellValueFactory(new PropertyValueFactory<>("numeroComanda"));
            CodiProducte.setCellValueFactory(new PropertyValueFactory<>("numeroProduccto"));
            Cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            Precio.setCellValueFactory(new PropertyValueFactory<>("Precio"));

        } else {
            desactivaSeleccioTbv();
        }
    }

     Comanda getClienteFromTable() {
        Comanda ret = null;

        ret = (Comanda) taulaComanda.getSelectionModel().getSelectedItem();

        return ret;
    }

     void desactivaSeleccioTbv() {
        //deshabilitem botóns i fila seleccionada
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        taulaComanda.getSelectionModel().clearSelection();
    }
    
     void enviarDatos(Comanda c) {
        
        MComandesController.c = c;
    }
    
     Comanda getComandaFromTable() {
        
        Comanda ret = null;
        
        ret = (Comanda) taulaComanda.getSelectionModel().getSelectedItem();
        
        return ret;
    }
    
     //OnAction que aplicara los filtros
    @FXML
    public void aplicarFiltroOnAction(ActionEvent event) throws SQLException {
        
        Date inicio = java.sql.Date.valueOf(tfFechaInicio.getText());
        Date a = java.sql.Date.valueOf(tfFechaFinal.getText());

        capaLogica.carregarDadesFiltro(inicio, a);
        taulaComanda.setItems(capaLogica.filtrarfecha());
    }

}
