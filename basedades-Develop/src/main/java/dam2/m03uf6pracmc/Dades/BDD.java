/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Dades;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

/**
 *
 * @author marku
 */
public class BDD {
    public static Connection connectarBD(String bd, String usuari, String password) throws SQLException {
        Connection ret = null;

        // si tot ha anat correcte, getConnection retorna una instància de la classe Connection
        // Aquesta és la classe base per a poder realitzar qualsevol accès a BBDD
        ret = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + bd + "?useUnicode=true&"
                + "useJDBCCompliantTimezoneShift=true&"
                + "useLegacyDatetimeCode=false&serverTimezone=UTC", usuari, password);

        return ret;
    }
}
