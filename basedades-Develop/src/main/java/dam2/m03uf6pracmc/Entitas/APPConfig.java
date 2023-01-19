/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Entitas;

/**
 *
 * @author marku
 */
public class APPConfig {

    Double limiteDeCredito;
    int cantSolicitada;
    int beneficioDelProducto;
    int horasMinDeEnvio;
    int edad;
    int maxdeLineasPorPedido;
    int numeroMaximoDePedido;
    int valoresExistentes;

    public APPConfig() {
        
    }

    public APPConfig(double limiteDeCredito, int cantSolicitada, int beneficioDelProducto, int horasMinDeEnvio, int edad, int maxdeLineasPorPedido, int montoMaximoDePedido, int valoresExistentes) {
        this.limiteDeCredito = limiteDeCredito;
        this.cantSolicitada = cantSolicitada;
        this.beneficioDelProducto = beneficioDelProducto;
        this.horasMinDeEnvio = horasMinDeEnvio;
        this.edad = edad;
        this.maxdeLineasPorPedido = maxdeLineasPorPedido;
        this.numeroMaximoDePedido = montoMaximoDePedido;
        this.valoresExistentes = valoresExistentes;
    }

    public double getLimiteDeCredito() {
        return limiteDeCredito;
    }

    public int getCantSolicitada() {
        return cantSolicitada;
    }

    public int getBeneficioDelProducto() {
        return beneficioDelProducto;
    }

    public int getHorasMinDeEnvio() {
        return horasMinDeEnvio;
    }

    public int getEdad() {
        return edad;
    }

    public int getMaxdeLineasPorPedido() {
        return maxdeLineasPorPedido;
    }

    public int getNumeroMaximoDePedido() {
        return numeroMaximoDePedido;
    }

    public int getValoresExistentes() {
        return valoresExistentes;
    }



    public void setCantSolicitada(int cantSolicitada) {
        this.cantSolicitada = cantSolicitada;
    }

    public void setBeneficioDelProducto(int beneficioDelProducto) {
        this.beneficioDelProducto = beneficioDelProducto;
    }

    public void setHorasMinDeEnvio(int horasMinDeEnvio) {
        this.horasMinDeEnvio = horasMinDeEnvio;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setMaxdeLineasPorPedido(int maxdeLineasPorPedido) {
        this.maxdeLineasPorPedido = maxdeLineasPorPedido;
    }

    public void setMontoMaximoDePedido(int montoMaximoDePedido) {
        this.numeroMaximoDePedido = montoMaximoDePedido;
    }

    public void setValoresExistentes(int valoresExistentes) {
        this.valoresExistentes = valoresExistentes;
    }

    public void getLimiteDeCredito(double aDouble) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
