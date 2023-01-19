/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Dades;

import dam2.m03uf6pracmc.Entitas.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marku
 */
public class BDD_Cliente {

    public static List<Cliente> CargarClientes(Connection c) throws SQLException {

        List listaClients = new ArrayList();
        ResultSet resultSet = consultaCliente(c);
        while (resultSet.next()) {
            String emailCliente = resultSet.getString("customerEmail");
            String DNI = resultSet.getString("idCard");
            String nombre = resultSet.getString("customerName");
            String telefono = resultSet.getString("phone");
            int limiteDinero = resultSet.getInt("creditLimit");
            //puede petar la fecha por pasar de sql a util
            Date fechaNacimiento = resultSet.getDate("birthDate");
            Cliente cliente = new Cliente(emailCliente, DNI, nombre, telefono, limiteDinero, fechaNacimiento);
            listaClients.add(cliente);

        }
        return listaClients;
    }

    public static ResultSet consultaCliente(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        // ejecutar la sentencia y obtener el resultado
        ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
        return rs;
    }

    public static void eliminaCliente(Connection con, Cliente c) throws SQLException {
        Statement sentencia;

        sentencia = con.createStatement();
        String sqlStr = "DELETE FROM customers WHERE customerEmail ='" + c.getEmailCliente() + "'";
        sentencia.executeUpdate(sqlStr);

    }

    public static String insertarNuevoCliente(Connection con, Cliente c) throws SQLException {
        Statement sentencia;
        String correo = "";

        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        sentencia.executeQuery("SELECT * FROM customers order by customerEmail asc");
        ResultSet rs = sentencia.getResultSet();

        // si hi ha algun registre a la taula
        if (rs.next()) {
            rs.last();
            correo = rs.getString("customerEmail");
        }
        rs.moveToInsertRow();
        rs.updateString("customerEmail", c.getEmailCliente());
        rs.updateString("idCard", c.getDNI());
        rs.updateString("customerName", c.getNombre());
        rs.updateString("phone", c.getTelefono());
        rs.updateDouble("creditLimit", c.getLimiteDinero());
        rs.updateDate("birthDate", c.getFechaNacimiento());

        rs.insertRow();

        return correo;
    }

    public static void modificarCliente(Connection con, Cliente c, String email) throws SQLException {
        Statement sentencia;

        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        sentencia.executeQuery("SELECT * FROM customers where customerEmail = '" + email + "'");
        ResultSet rs = sentencia.getResultSet();
        
        if (rs.next()) {
            rs.updateString("customerEmail", c.getEmailCliente());
            rs.updateString("idCard", c.getDNI());
            rs.updateString("customerName", c.getNombre());
            rs.updateString("phone", c.getTelefono());
            rs.updateDouble("creditLimit", c.getLimiteDinero());

            rs.updateDate("birthDate", c.getFechaNacimiento());

            rs.updateRow();

        }
    }

}
