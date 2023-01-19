package dam2.m03uf6pracmc;

import dam2.m03uf6pracmc.Logica.LogicaAppConfig;
import static dam2.m03uf6pracmc.Utils.Utils.mostraMissatge;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MenuPrincipalController implements Initializable{

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnCliente;

    @FXML
    private Button btnPedido;

    @FXML
    private Button btnProducto;

    @FXML
    LogicaAppConfig logicaAppConfig;

    
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            logicaAppConfig = new LogicaAppConfig();
            logicaAppConfig.insertAppConfig();

        } catch (SQLException ex) {
            mostraMissatge(1, "Error inicialitzant capa lógica: " + ex.toString());
        }
     
    }
    @FXML
    void OnActionbtnSalir(ActionEvent event) throws SQLException {
        logicaAppConfig.tancarConnexio();
    }

    @FXML
    void switchToCliente() throws IOException {
        App.setRoot("tClients");
    }

    @FXML
    void switchToProducto() throws IOException {
        App.setRoot("tProductos");
    }

    @FXML
    void switchToPedido() throws IOException {
        App.setRoot("Comandes");
    }

}
