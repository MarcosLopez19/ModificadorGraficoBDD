/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Logica;

import dam2.m03uf6pracmc.Dades.BDD;
import dam2.m03uf6pracmc.Dades.BDD_DetallComanda;
import dam2.m03uf6pracmc.Entitas.Comanda;
import dam2.m03uf6pracmc.Entitas.DetallComanda;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author pablogomez
 */
public class LogicaDetallComanda {
    
    Connection conn;
    
    //definim una llista observable d'objectes de la classe Comanda
    ObservableList<DetallComanda>llistaObservableDetallComanda;
    
    public LogicaDetallComanda() throws SQLException {
        // inicialitzem connexió amb BD pero passant per la capa d'aplicació
        conn = BDD.connectarBD("m03uf6_22_23", "root", "123qweASD");

        // inicialitzem col.lecció
        llistaObservableDetallComanda = FXCollections.<DetallComanda>observableArrayList();
    }
    
    public void carregarDades(int i) throws SQLException {

        this.llistaObservableDetallComanda.setAll(BDD_DetallComanda.CargarDetallComandas(conn, i));
    }
    
    public ObservableList<DetallComanda> getLlistaObservableDetallComanda() {
        return llistaObservableDetallComanda;
    }
    
    public void afegirDetallComanda(DetallComanda dc) throws SQLException {

        

        // afegim una nova assignatura i ens retorna el nou id assignat
        dc.setNumeroComanda(BDD_DetallComanda.insertarNuevaDetallComanda(conn, dc));

        // Si tot ha anat bé, afegim l'objecte a la llista observable.
        // NOTA: Quan afegim o eliminem elements de la collecció, la taula es
        // refresca de forma automàtica, amb el mateix efecte que
        // si fessim taulaAssignatura.refresh()
        llistaObservableDetallComanda.add(dc);
        
    }
    
    public void eliminarDetallComanda(DetallComanda c) throws SQLException {

        //l'eliminem de la BBDD
        BDD_DetallComanda.eliminaDetallComanda(conn, c);

        // Si tot ha anat bé, eliminem l'objecte de la llista observable.
        // NOTA: Quan afegim o eliminem elements de la collecció, la taula es
        // refresca de forma automàtica, amb el mateix efecte que
        // si fessim taulaAssignatura.refresh()
        llistaObservableDetallComanda.remove(c);
    }
    
    public void eliminarDetallComandaTots(Comanda c) throws SQLException {

        //l'eliminem de la BBDD
        BDD_DetallComanda.eliminaDetallComandaTots(conn, c);

        // Si tot ha anat bé, eliminem l'objecte de la llista observable.
        // NOTA: Quan afegim o eliminem elements de la collecció, la taula es
        // refresca de forma automàtica, amb el mateix efecte que
        // si fessim taulaAssignatura.refresh()
        llistaObservableDetallComanda.remove(c);
    }
    
}
