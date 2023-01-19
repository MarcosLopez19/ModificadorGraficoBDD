/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Entitas;

/**
 *
 * @author pablogomez
 */
public class DetallComanda {

    private int numeroComanda;
    private int numeroProduccto;
    private int cantidad;
    private double Precio;
    private int numeroLinia;

    public DetallComanda() {
        this.numeroComanda = 0;
        this.numeroProduccto = 0;
        this.cantidad = 0;
        this.Precio = 0;
        this.numeroLinia = 0;
    }

    public DetallComanda(int numeroComanda, int numeroProduccto, int cantidad, double Precio, int numeroLinia) {
        this.numeroComanda = numeroComanda;
        this.numeroProduccto = numeroProduccto;
        this.cantidad = cantidad;
        this.Precio = Precio;
        this.numeroLinia = numeroLinia;
    }

    public int getNumeroComanda() {
        return numeroComanda;
    }

    public int getNumeroProduccto() {
        return numeroProduccto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return Precio;
    }

    public int getNumeroLinia() {
        return numeroLinia;
    }

    public void setNumeroComanda(int numeroComanda) {
        this.numeroComanda = numeroComanda;
    }

    public void setNumeroProduccto(int numeroProduccto) {
        this.numeroProduccto = numeroProduccto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public void setNumeroLinia(int numeroLinia) {
        this.numeroLinia = numeroLinia;
    }

    @Override
    public String toString() {
        return "DetallComanda{" + "numeroComanda=" + numeroComanda + ", numeroProduccto=" + numeroProduccto + ", cantidad=" + cantidad + ", Precio=" + Precio + ", numeroLinia=" + numeroLinia + '}';
    }

}
