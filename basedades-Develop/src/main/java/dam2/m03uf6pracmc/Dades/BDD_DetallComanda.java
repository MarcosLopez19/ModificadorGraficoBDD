/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Dades;

import dam2.m03uf6pracmc.Entitas.Comanda;
import dam2.m03uf6pracmc.Entitas.DetallComanda;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pablogomez
 */
public class BDD_DetallComanda {
    
    public static List<DetallComanda> CargarDetallComandas(Connection c, int i) throws SQLException {

        List<DetallComanda> listaDetallComandas = new ArrayList<>();
        ResultSet resultSet = consultaDetallComanda(c, i);

        while (resultSet.next()) {
            int numeroComanda = resultSet.getInt("orderNumber");
            int numeroProducte = resultSet.getInt("productCode");
            int quantitat = resultSet.getInt("quantityOrdered");
            double precio = resultSet.getDouble("priceEach");
            int numeroLlista = resultSet.getInt("orderLineNumber");
            
            DetallComanda detallcomanda = new DetallComanda(numeroComanda, numeroProducte, quantitat, precio, numeroLlista);
            listaDetallComandas.add(detallcomanda);
        }
        return listaDetallComandas;
    }
    
    public static ResultSet consultaDetallComanda(Connection c, int i) throws SQLException {
        Statement stmt = c.createStatement();
        // ejecutar la sentencia y obtener el resultado
        ResultSet rs = stmt.executeQuery("SELECT * FROM orderdetails WHERE orderNumber = " + i);
        return rs;
    }
    
    public static void eliminaDetallComanda(Connection con, DetallComanda c) throws SQLException {
        Statement sentencia;
        
        sentencia = con.createStatement();
        String sqlStr = "DELETE FROM orderdetails WHERE productCode = '" + c.getNumeroProduccto() + "'";
        sentencia.executeUpdate(sqlStr);
    }
    
     public static void eliminaDetallComandaTots(Connection con,Comanda c) throws SQLException {
        Statement sentencia;
        
        sentencia = con.createStatement();
        String sqlStr = "DELETE FROM orderdetails WHERE orderNumber = '" + c.getNumeroComanda() + "'";
        sentencia.executeUpdate(sqlStr);
    }
    
    
    
    public static int insertarNuevaDetallComanda(Connection con, DetallComanda c) throws SQLException {
        Statement sentencia;
        
        int NumeroComanda = 0;

        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        sentencia.executeQuery("SELECT * FROM orderdetails order by orderNumber asc");
        ResultSet rs = sentencia.getResultSet();
        
        if (rs.next()) {
            rs.last();
            NumeroComanda = rs.getInt("orderNumber");
        }
        
        rs.moveToInsertRow();
        rs.updateInt("orderNumber", c.getNumeroComanda());
        rs.updateInt("productCode", c.getNumeroProduccto());
        rs.updateInt("quantityOrdered", c.getCantidad());
        rs.updateDouble("priceEach", c.getPrecio());
        rs.updateInt("orderLineNumber", c.getNumeroLinia());
        rs.insertRow();
        
        return c.getNumeroComanda();
    }
    
    public static void modificarDetallComanda(Connection con, DetallComanda c) throws SQLException {
        Statement sentencia;

        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        sentencia.executeQuery("SELECT * FROM orderdetails WHERE orderNumber = " + c.getNumeroComanda());
        ResultSet rs = sentencia.getResultSet();

        if (rs.next()) {
            rs.updateInt("productCode", c.getNumeroProduccto());
            rs.updateInt("quantity", c.getCantidad());
            rs.updateDouble("priceEach", c.getPrecio());
            rs.updateInt("orderLineNumber", c.getNumeroLinia());

            rs.updateRow();
        }
    }
}
