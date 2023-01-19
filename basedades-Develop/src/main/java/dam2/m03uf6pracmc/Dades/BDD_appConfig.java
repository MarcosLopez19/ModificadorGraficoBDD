/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Dades;

import dam2.m03uf6pracmc.Entitas.APPConfig;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marku
 */
public class BDD_appConfig {

    public APPConfig CargarAPPConfig(Connection c) {
        Statement sentence;
        APPConfig a = new APPConfig();
        try {
            ResultSet rs = consultaAPPConfig(c);
            rs.next();
            a.getLimiteDeCredito(rs.getDouble("defaultCreditLimit"));
            a.getLimiteDeCredito(rs.getDouble("defaultCreditLimit"));
            a.getLimiteDeCredito(rs.getDouble("defaultCreditLimit"));
            a.getLimiteDeCredito(rs.getDouble("defaultCreditLimit"));
            a.getLimiteDeCredito(rs.getDouble("defaultCreditLimit"));
            a.getLimiteDeCredito(rs.getDouble("defaultCreditLimit"));
            a.getLimiteDeCredito(rs.getDouble("defaultCreditLimit"));
            a.getLimiteDeCredito(rs.getDouble("defaultCreditLimit"));
        } catch (SQLException e) {

        }
        return a;
    }

    public static ResultSet consultaAPPConfig(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        // ejecutar la sentencia y obtener el resultado
        ResultSet rs = stmt.executeQuery("SELECT defaultCreditLimit FROM appConfig");
        return rs;
    }

    public static void insertAppConfig(Connection c) throws SQLException {
        Statement sentencia;
        sentencia = c.createStatement();
        ResultSet resultSet = consultaAPPConfig(c);

        // Verifica si el resultado de la consulta es vacío o no
        boolean exists = resultSet.next();

        if (!exists) {
            sentencia.executeUpdate("insert into `appConfig`(defaultCreditLimit, defaultQuantityInStock, defaultQuantityOrdered, defaultProductBenefit, minShippingHours, minCustomerAge, maxLinesPerOrder, maxOrderAmount) values (1.1, 1, 1, 1, 00, 18, 1, 1000);");
        }
    }
}
