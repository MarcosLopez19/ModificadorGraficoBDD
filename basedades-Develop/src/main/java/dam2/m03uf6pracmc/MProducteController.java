/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dam2.m03uf6pracmc;

import dam2.m03uf6pracmc.Entitas.Cliente;
import dam2.m03uf6pracmc.Entitas.Producto;
import dam2.m03uf6pracmc.Logica.logicaProducto;
import dam2.m03uf6pracmc.Utils.Utils;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class MProducteController implements Initializable{
    
    @FXML
    public static Boolean esModificiar;
    
    @FXML
    public static Producto p;
    
    @FXML
    private TextField textStockProducto;

    @FXML
    private Label lbStockProducto;

    @FXML
    private Label lbNombreProducto;

    @FXML
    private Button btnAceptarProducto;

    @FXML
    private Text tituloNombreProducto;

    @FXML
    private TextField textNombreProducto;

    @FXML
    private TextField textCodigoProducto;

    @FXML
    private TextField textDescripcionProducto;

    @FXML
    private Label lbPrecioProducto;

    @FXML
    private Button btnCancelarProducto;

    @FXML
    private Label lbCodigoProducto;

    @FXML
    private Label lbDescripcionProducto;

    @FXML
    private TextField textPrecioProducto;

    // capa lógica
    logicaProducto capaLogica;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            capaLogica = new logicaProducto();
            capaLogica.carregarDadesProductos();

        } catch (SQLException ex) {
            Utils.mostraMissatge(1, "Error carregant dades: " + ex.toString());
        } catch (Exception ex) {
            Utils.mostraMissatge(1, "Error inicialitzant capa lógica: " + ex.toString());
        }
        if (esModificiar) {
            setProductToView(p);

        } else {

        }
    }
    
    
    //VOlvemos a la pantalla principal
    @FXML
    public void btnCancelarProducto(ActionEvent event) throws IOException {
        App.setRoot("tProductos");
    }

    //boton que añade o modifica el producto dependiendo del boolean de la pantalla anteror (TProductosController)
    @FXML
    public void btnAceptarProducto(ActionEvent event) throws IOException, ParseException, Exception {
        if (esModificiar) {
            //el modifiquem a la BBDD

            capaLogica.modificarProducte(getProductoDelTextFidel(), p.getCodigo());

        } else {
            
            capaLogica.afegirProducto(getProductoDelTextFidel());
        }
        App.setRoot("tProductos");
    }
    
    private void setProductToView(Producto p) {
        if (p != null) {
            textCodigoProducto.setText(String.valueOf(p.getCodigo()));
            textNombreProducto.setText(p.getNombre());
            textDescripcionProducto.setText(p.getDescripcion());
            textStockProducto.setText(String.valueOf(p.getStock()));
            textPrecioProducto.setText(Double.toString(p.getPrecio()));
        }
    }
    
    //devuelve un producto
    private Producto getProductoDelTextFidel() throws ParseException {
        Producto p = new Producto();

        p.setCodigo(Integer.parseInt(textCodigoProducto.getText()));
        p.setNombre(textNombreProducto.getText());
        p.setDescripcion(textDescripcionProducto.getText());
        p.setStock(Integer.parseInt(textStockProducto.getText()));
        p.setPrecio(Double.parseDouble(textPrecioProducto.getText()));

        return p;
    }
}
