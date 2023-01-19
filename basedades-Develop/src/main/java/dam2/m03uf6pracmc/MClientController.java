/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dam2.m03uf6pracmc;

import dam2.m03uf6pracmc.Entitas.Cliente;
import dam2.m03uf6pracmc.Logica.LogicaCliente;
import dam2.m03uf6pracmc.Utils.Utils;
import static dam2.m03uf6pracmc.Utils.Utils.mostraMissatge;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;

public class MClientController implements Initializable {

    @FXML
    private TextField txtFidelCampoClente6;

    @FXML
    private TextField txtFidelCampoClente5;

    @FXML
    private TextField txtFidelCampoClente4;

    @FXML
    private Button btnMclientAceptar;

    @FXML
    private TextField txtFidelCampoClente3;

    @FXML
    private Label lbCampoCliente6;

    @FXML
    private Label lbCampoCliente5;

    @FXML
    private Label lbCampoCliente4;

    @FXML
    private Label lbCampoCliente3;

    @FXML
    private Text textNombreProducto;

    @FXML
    private Label lbCampoCliente2;

    @FXML
    private Label lbCampoCliente1;

    @FXML
    private Button btnMclientCancelar;

    @FXML
    private TextField txtFidelCampoCliente1;

    @FXML
    private TextField txtFidelCampoCliente2;

    @FXML
    public static Cliente c;

    @FXML
    public static Boolean esModificiar;

    LogicaCliente capaLogica;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            capaLogica = new LogicaCliente();
            capaLogica.carregarDades();

        } catch (SQLException ex) {
            mostraMissatge(1, "Error carregant dades: " + ex.toString());
        } catch (Exception ex) {
            mostraMissatge(1, "Error inicialitzant capa lógica: " + ex.toString());
        }
        if (esModificiar) {
            setClientToView(c);

        } else {

        }
    }

    @FXML
    void onAction_btnMclientCancelar(ActionEvent event) throws IOException {
        App.setRoot("tClients");
    }

    @FXML
    void onAction_btnMclientAceptar(ActionEvent event) throws IOException, NumberFormatException, ParseException, Exception {
        if (esModificiar) {
            //el modifiquem a la BBDD

            capaLogica.modificarCliente(getClienteDelTextFidel(), c.getEmailCliente());
            App.setRoot("tCLients");
            //TClientsController.clienteActualizado = getClienteDelTextFidel();
        } else {
            boolean va=true;
            if(Utils.validaEmailCLiente(getClienteDelTextFidel().getEmailCliente())){
                
            }else {
                 mostraMissatge(0, "El correo introducido no es correcto");
            }
            if(Utils.validarDNICliente(getClienteDelTextFidel().getDNI())){
                   
            }else{
                 mostraMissatge(0, "El DNI introducio es incorrecto");               
            }
            if(va){
            capaLogica.afegirCliente(getClienteDelTextFidel());
                   App.setRoot("tCLients");
            }
        }
    }

   

    private void setClientToView(Cliente c) {
        if (c != null) {
            txtFidelCampoCliente1.setText(String.valueOf(c.getEmailCliente()));
            txtFidelCampoCliente2.setText(c.getDNI());
            txtFidelCampoClente3.setText(c.getNombre());
            txtFidelCampoClente4.setText(c.getTelefono());
            txtFidelCampoClente5.setText(Double.toString(c.getLimiteDinero()));
            txtFidelCampoClente6.setText(c.getFechaNacimiento().toString());

        }
    }

    private Cliente getClienteDelTextFidel() throws ParseException {
        Cliente c = new Cliente();

        c.setEmailCliente(txtFidelCampoCliente1.getText());
        c.setDNI(txtFidelCampoCliente2.getText());
        c.setNombre(txtFidelCampoClente3.getText());
        c.setTelefono(txtFidelCampoClente4.getText());
        c.setLimiteDinero(Double.parseDouble(txtFidelCampoClente5.getText()));
        //definimos un patron de fecha con el DateFormat
        java.sql.Date date = java.sql.Date.valueOf(txtFidelCampoClente6.getText());
        c.setFechaNacimiento(date);

        return c;
    }

}
