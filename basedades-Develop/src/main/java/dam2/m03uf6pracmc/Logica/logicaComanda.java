/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Logica;

import dam2.m03uf6pracmc.Dades.BDD;
import dam2.m03uf6pracmc.Dades.BDD_Comanda;
import dam2.m03uf6pracmc.Dades.BDD_DetallComanda;
import dam2.m03uf6pracmc.Entitas.Comanda;
import dam2.m03uf6pracmc.Entitas.DetallComanda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author pablogomez
 */
public class logicaComanda {

    Connection conn;

    //definim una llista observable d'objectes de la classe Comanda
    ObservableList<Comanda> llistaObservableComanda;
    ObservableList<Comanda> llistaObservableComandaFiltrada;

    public logicaComanda() throws SQLException {
        // inicialitzem connexió amb BD pero passant per la capa d'aplicació
        conn = BDD.connectarBD("m03uf6_22_23", "root", "123qweASD");

        // inicialitzem col.lecció
        llistaObservableComanda = FXCollections.<Comanda>observableArrayList();
        llistaObservableComandaFiltrada = FXCollections.<Comanda>observableArrayList();
    }

    public void carregarDades() throws SQLException {

        this.llistaObservableComanda.setAll(BDD_Comanda.CargarComandas(conn));
    }

    public ObservableList<Comanda> getLlistaObservableComanda() {
        return llistaObservableComanda;
    }

    public void afegirComanda( Comanda c) throws SQLException {

        // afegim una nova assignatura i ens retorna el nou id assignat
        c.setNumeroComanda(BDD_Comanda.insertarNuevaComanda(conn, c));

        // Si tot ha anat bé, afegim l'objecte a la llista observable.
        // NOTA: Quan afegim o eliminem elements de la collecció, la taula es
        // refresca de forma automàtica, amb el mateix efecte que
        // si fessim taulaAssignatura.refresh()
        llistaObservableComanda.add(c);

    }

    public void eliminarComanda(Comanda c) throws SQLException {

        //l'eliminem de la BBDD
        BDD_Comanda.eliminaComanda(conn, c);

        // Si tot ha anat bé, eliminem l'objecte de la llista observable.
        // NOTA: Quan afegim o eliminem elements de la collecció, la taula es
        // refresca de forma automàtica, amb el mateix efecte que
        // si fessim taulaAssignatura.refresh()
        
        llistaObservableComanda.remove(c);
    }

    public void modificarComanda(Comanda c) throws SQLException, Exception {

        BDD_Comanda.modificarComanda(conn, c);
    }

    public void tancarConnexio() throws SQLException {
        this.conn.close();
    }

    //Carrgamos los datos que nos devuelve la base de datos en una observable list
    public void carregarDadesFiltro(Date inicio, Date a) throws SQLException {

        this.llistaObservableComandaFiltrada.setAll(BDD_Comanda.filtrarfechas(conn, inicio, a));
    }
    
    //devolvemos la observable list
    public ObservableList<Comanda> filtrarfecha() throws SQLException{
       
         return llistaObservableComandaFiltrada;
    }    
}
