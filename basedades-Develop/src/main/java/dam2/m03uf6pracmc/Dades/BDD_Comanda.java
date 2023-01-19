/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Dades;

import dam2.m03uf6pracmc.Entitas.Comanda;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author marku
 */
public class BDD_Comanda {

    public static List<Comanda> CargarComandas(Connection c) throws SQLException {

        List<Comanda> listaComandas = new ArrayList<>();
        ResultSet resultSet = consultaComanda(c, "select * from orders");

        while (resultSet.next()) {
            int numeroComanda = resultSet.getInt("orderNumber");
            Date dataComanda = resultSet.getDate("requiredDate");
            Date dataRequerida = resultSet.getDate("shippedDate");
            Date shippedDate = resultSet.getDate("orderDate");
            String emailClient = resultSet.getString("customers_customerEmail");
            
            Comanda comanda = new Comanda(numeroComanda, dataComanda, dataRequerida, shippedDate, emailClient);
            listaComandas.add(comanda);
        }
        return listaComandas;
    }

    public static ResultSet consultaComanda(Connection c, String ssql) throws SQLException {
        Statement stmt = c.createStatement();
        // ejecutar la sentencia y obtener el resultado
        ResultSet rs = stmt.executeQuery(ssql);
        return rs;
    }

    public static void eliminaComanda(Connection con, Comanda c) throws SQLException {
        Statement sentencia;

        sentencia = con.createStatement();
        String sqlStr = "DELETE FROM orders WHERE orderNumber = '" + c.getNumeroComanda() + "'";
        sentencia.executeUpdate(sqlStr);
    }

    public static int insertarNuevaComanda(Connection con, Comanda c) throws SQLException {
        Statement sentencia;
        int ordernumber=0;
        
        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        sentencia.executeQuery("SELECT * FROM orders order by orderNumber asc");
        ResultSet rs = sentencia.getResultSet();
        
         // si hi ha algun registre a la taula
         
        if (rs.next()){
            rs.last();
            ordernumber = rs.getInt("orderNumber");
        }
        
        rs.moveToInsertRow();
        rs.updateInt("orderNumber", c.getNumeroComanda());
        rs.updateDate("orderDate", c.getDataComanda());
        rs.updateDate("requiredDate", c.getDataRequerida());
        rs.updateDate("shippedDate", c.getShippedDate());
        rs.updateString("customers_customerEmail", c.getEmailCliente());
        rs.insertRow();
        
        return ordernumber;
    }
    

    public static void modificarComanda(Connection con, Comanda c) throws SQLException {
        Statement sentencia;

        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        sentencia.executeQuery("SELECT * FROM orders WHERE orderNumber = '" + c.getNumeroComanda() + "'");
        ResultSet rs = sentencia.getResultSet();

        if (rs.next()) {
            rs.updateDate("orderDate", c.getDataComanda());
            rs.updateDate("requiredDate", c.getDataRequerida());
            rs.updateDate("shippedDate", c.getShippedDate());
            rs.updateString("customers_customerEmail", c.getEmailCliente());

            rs.updateRow();
        }
    }
    
    
    //Creamos una lista con los objetos Comanda que cumplan la consulta
    public static List<Comanda> filtrarfechas(Connection con, Date inicio, Date a) throws SQLException{
        Statement sentencia;
        int ordernumber=0;
        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        List<Comanda> listaComandasFiltrada = new ArrayList<>();
        ResultSet resultSet =sentencia.executeQuery("SELECT * FROM orders WHERE orderDate BETWEEN '" + inicio + "' AND '" + a +"'");

        while (resultSet.next()) {
            int numeroComanda = resultSet.getInt("orderNumber");
            Date dataComanda = resultSet.getDate("requiredDate");
            Date dataRequerida = resultSet.getDate("shippedDate");
            Date shippedDate = resultSet.getDate("orderDate");
            String emailClient = resultSet.getString("customers_customerEmail");
            
            Comanda comanda = new Comanda(numeroComanda, dataComanda, dataRequerida, shippedDate, emailClient);
            listaComandasFiltrada.add(comanda);
        }
        return listaComandasFiltrada;
    }
    
}
