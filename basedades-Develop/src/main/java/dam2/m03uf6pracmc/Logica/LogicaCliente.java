/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Logica;

import dam2.m03uf6pracmc.Dades.BDD;
import dam2.m03uf6pracmc.Entitas.Cliente;
import dam2.m03uf6pracmc.Dades.BDD_Cliente;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dam2.m03uf6pracmc.Utils.Utils;
/**
 *
 * @author marku
 */
public class LogicaCliente {

    Connection conn;

    //definim una llista observable d'objectes de la classe Clients
    ObservableList<Cliente> llistaObservableCliente;

    public LogicaCliente() throws SQLException {
        // inicialitzem connexió amb BD pero passant per la capa d'aplicació
        conn = BDD.connectarBD("m03uf6_22_23", "root", "123qweASD");

        // inicialitzem col.lecció
        llistaObservableCliente = FXCollections.<Cliente>observableArrayList();
    }

    public void carregarDades() throws SQLException {
        this.llistaObservableCliente.setAll(BDD_Cliente.CargarClientes(conn));

    }

    public ObservableList<Cliente> getLlistaObservableCliente() {
        return llistaObservableCliente;
    }

    public void afegirCliente(Cliente c) throws SQLException {

        // afegim un nou client i ens retorna el nou id assignat
        c.setEmailCliente(BDD_Cliente.insertarNuevoCliente(conn, c));

        // Si tot ha anat bé, afegim l'objecte a la llista observable.
        // NOTA: Quan afegim o eliminem elements de la collecció, la taula es
        // refresca de forma automàtica, amb el mateix efecte que
        // si fessim taulaAssignatura.refresh()
        llistaObservableCliente.add(c);
    }

    public void eliminarCliente(Cliente c) throws SQLException {

        //l'eliminem de la BBDD
        BDD_Cliente.eliminaCliente(conn, c);

        // Si tot ha anat bé, eliminem l'objecte de la llista observable.
        // NOTA: Quan afegim o eliminem elements de la collecció, la taula es
        // refresca de forma automàtica, amb el mateix efecte que
        // si fessim taulaAssignatura.refresh()
        llistaObservableCliente.remove(c);
    }

    public void modificarCliente(Cliente c, String email) throws SQLException, Exception {
        // si no valida el format del nom, genera una excepció
        if (!Utils.validaEmailCLiente(c.getEmailCliente())) {
            throw new Exception("El format del correo del cliente no es correcto.");
        }

        BDD_Cliente.modificarCliente(conn, c, email);
    }
}
