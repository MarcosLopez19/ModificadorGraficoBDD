/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

/**
 *
 * @author marku
 */
public class MensajeInfo {

    public static void mostraError(String txt) {
        Alert a = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
        a.setTitle("ERROR");
        a.setContentText(txt);
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        //mostrem error
        a.showAndWait();
    }
    public static void mostrarInfo(String txt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info:");
        alert.setContentText(txt);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

}
