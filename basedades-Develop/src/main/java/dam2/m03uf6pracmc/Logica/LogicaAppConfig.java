/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Logica;

import dam2.m03uf6pracmc.Dades.BDD;
import dam2.m03uf6pracmc.Dades.BDD_appConfig;
import dam2.m03uf6pracmc.Entitas.APPConfig;
import static dam2.m03uf6pracmc.Utils.Utils.mostraMissatge;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Platform;

/**
 *
 * @author marku
 */
public class LogicaAppConfig {
    private Connection conn;
    public APPConfig appconfig;
    public LogicaAppConfig() throws SQLException {
        // inicialitzem connexió amb BD pero passant per la capa d'aplicació
        conn = BDD.connectarBD("m03uf6_22_23", "root", "123qweASD");

    }
//      public APPConfig carregarDades(){
//
//        return BDD_appConfig.CargarAPPConfig(conn);
//    }
    public void insertAppConfig() throws SQLException {

        // insertem els valors per defecte en la BDD
        BDD_appConfig.insertAppConfig(conn);

    }

    public void tancarConnexio() throws SQLException {
        try {
            //tanquem connexió
            conn.close();

        } catch (SQLException e) {
            mostraMissatge(1, "Error tancant la connexió: " + e);
        } finally {
            //sortim de totes maneres
            Platform.exit();
        }
    }
}
