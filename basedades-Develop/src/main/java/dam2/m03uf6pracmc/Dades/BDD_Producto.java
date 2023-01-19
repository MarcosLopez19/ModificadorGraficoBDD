/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Dades;

import dam2.m03uf6pracmc.Entitas.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marku
 */
public class BDD_Producto {

    //Funcion para cargar los productos de la base de datos a un ArrayList
    public static List<Producto> CargarProductos(Connection c) throws SQLException {

        List<Producto> listaProductos = new ArrayList<>();
        ResultSet resultSet = consultaProducto(c);

        while (resultSet.next()) {
            int codigo = resultSet.getInt("productCode");
            String nombre = resultSet.getString("productName");
            String descripcion = resultSet.getString("productDescription");
            int stock = resultSet.getInt("quantityInStock");
            double precio = resultSet.getDouble("buyPrice");
            Producto producto = new Producto(codigo, nombre, descripcion, stock, precio);
            listaProductos.add(producto);
        }
        return listaProductos;
    }
    
    //COnsulta que devuelve los productos de la base de datos

    public static ResultSet consultaProducto(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        // ejecutar la sentencia y obtener el resultado
        ResultSet rs = stmt.executeQuery("SELECT * FROM products");
        return rs;
    }
    
    
    //Insertamos un nuevo producto  
    public static int insertarNuevoProducto(Connection con, Producto p) throws SQLException {
       Statement sentencia;
        int codigo = 0;

        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        sentencia.executeQuery("SELECT * FROM products order by productCode asc");
        ResultSet rs = sentencia.getResultSet();

        // si hi ha algun registre a la taula
        if (rs.next()) {
            rs.last();
            codigo = rs.getInt("productCode");
        }
        rs.moveToInsertRow();
        rs.updateInt("productCode", p.getCodigo());
        rs.updateString("productName", p.getNombre());
        rs.updateString("productDescription", p.getDescripcion());
        rs.updateInt("quantityInStock", p.getStock());
        rs.updateDouble("buyPrice", p.getPrecio());

        rs.insertRow();

        return codigo;
        
    }

    //MOdificamos un nuevo producto
    public static void modificarProducto(Connection con, Producto p, int codigo) throws SQLException {
        Statement sentencia;

        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        sentencia.executeQuery("SELECT * FROM products WHERE productCode = '" + codigo + "'");
        ResultSet rs = sentencia.getResultSet();

        if (rs.next()) {
            rs.updateInt("productCode", p.getCodigo());
            rs.updateString("productName", p.getNombre());
            rs.updateString("productDescription", p.getDescripcion());
            rs.updateInt("quantityInStock", p.getStock());
            rs.updateDouble("buyPrice", p.getPrecio());

            rs.updateRow();
        }
    }
    
    
    //Eliminar producto de la base de datos filtrando por su codigo
    public static void eliminaProducto(Connection con, Producto p) throws SQLException {
        Statement sentencia;

        sentencia = con.createStatement();
        String sqlStr = "DELETE FROM products WHERE productCode = '" + p.getCodigo() + "'";
        sentencia.executeUpdate(sqlStr);
    }
}
