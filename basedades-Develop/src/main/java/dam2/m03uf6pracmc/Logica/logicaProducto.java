/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Logica;

import dam2.m03uf6pracmc.Entitas.Producto;
import java.sql.SQLException;
import dam2.m03uf6pracmc.Dades.BDD;
import dam2.m03uf6pracmc.Dades.BDD_Producto;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author marku
 */
public class logicaProducto {
    Connection conn;
    
    //definim una llista observable d'objectes de la classe Producto
    ObservableList<Producto> llistaObservableProducto;
    
    public logicaProducto() throws SQLException {
        // inicialitzem connexió amb BD cridant le metode de la capa d'aplicació
        conn = BDD.connectarBD("m03uf6_22_23", "root", "123qweASD");

        // inicialitzem col.lecció
        llistaObservableProducto = FXCollections.<Producto>observableArrayList();
    }
    /**
     * Omple la llistaObservable amb els registres de la taula
     *
     * @throws SQLException
     */
    
    //Cargamos los productos que nos devuelve la base de datos y lo guardamos en una observableList
    public void carregarDadesProductos() throws SQLException {

        this.llistaObservableProducto.setAll(BDD_Producto.CargarProductos(conn));

    }
    
    //Devolvemos la Observablelist
     public ObservableList<Producto> getLlistaObservableProducto() {
        return llistaObservableProducto;
    }
     
     
     //Añadimos un nuevo producto a la lista observable y a la base de datos
     public void afegirProducto(Producto p) throws SQLException {

        // afegim una nova assignatura i ens retorna el nou id assignat
        p.setCodigo(BDD_Producto.insertarNuevoProducto(conn, p));

        // Si tot ha anat bé, afegim l'objecte a la llista observable.
        // NOTA: Quan afegim o eliminem elements de la collecció, la taula es
        // refresca de forma automàtica, amb el mateix efecte que
        // si fessim taulaAssignatura.refresh()
        llistaObservableProducto.add(p);
    }
     
     
     //eliminamos un producto de la base de datos y la lista observable 
     public void eliminarProducto(Producto p) throws SQLException {

        //l'eliminem de la BBDD
        BDD_Producto.eliminaProducto(conn, p);

        // Si tot ha anat bé, eliminem l'objecte de la llista observable.
        // NOTA: Quan afegim o eliminem elements de la collecció, la taula es
        // refresca de forma automàtica, amb el mateix efecte que
        // si fessim taulaAssignatura.refresh()
        llistaObservableProducto.remove(p);
    }
     
     //modificamos un producto de la base de datos
     public void modificarProducte(Producto p, int codigo) throws SQLException, Exception {

        BDD_Producto.modificarProducto(conn, p, codigo);
    }
     
     //Cerramos la conexion de la bd
     public void tancarConnexio() throws SQLException {
        this.conn.close();
    }
}
