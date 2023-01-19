/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Entitas;

import java.sql.Date;

/**
 *
 * @author pablogomez
 */
public class Comanda {
    
    private int numeroComanda;
    private Date dataComanda;
    private Date dataRequerida;
    private Date shippedDate;
    private String emailCliente;
    
    public Comanda() {
        this.numeroComanda = 0;
        this.dataComanda = null;
        this.dataRequerida = null;
        this.shippedDate = null;
        this.emailCliente = "";
    }

    public Comanda(int numeroComanda, Date dataComanda, Date dataRequerida, Date shippedDate, String emailCliente) {
        this.numeroComanda = numeroComanda;
        this.dataComanda = dataComanda;
        this.dataRequerida = dataRequerida;
        this.shippedDate = shippedDate;
        this.emailCliente = emailCliente;
    }

    public int getNumeroComanda() {
        return numeroComanda;
    }

    public Date getDataComanda() {
        return dataComanda;
    }

    public Date getDataRequerida() {
        return dataRequerida;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public String getEmailCliente() {
        return emailCliente;
    }
    

    public void setNumeroComanda(int numeroComanda) {
        this.numeroComanda = numeroComanda;
    }

    public void setDataComanda(Date dataComanda) {
        this.dataComanda = dataComanda;
    }

    public void setDataRequerida(Date dataRequerida) {
        this.dataRequerida = dataRequerida;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }


    @Override
    public String toString() {
        return "Comanda{" + "numeroComanda=" + numeroComanda + ", dataComanda=" + dataComanda + ", dataRequerida=" + dataRequerida + ", shippedDate=" + shippedDate + ", emailCliente=" + emailCliente + '}';
    }
    
    

    
    
    
    
    
    
    
}
