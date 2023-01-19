/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Entitas;

import java.sql.Date;


public class Cliente {
  private String emailCliente;
  private String DNI;
  private String nombre;
  private String telefono;
  private double limiteDinero;
  private Date fechaNacimiento;

  // constructor
  public Cliente() {
    this.emailCliente = "";
    this.DNI = "";
    this.nombre = "";
    this.telefono = "";
    this.limiteDinero = 0;
    this.fechaNacimiento = null;
  }
  public Cliente(String emailCliente, String DNI, String nombre, String telefono, double limiteDinero, Date fechaNacimiento) {
    this.emailCliente = emailCliente;
    this.DNI = DNI;
    this.nombre = nombre;
    this.telefono = telefono;
    this.limiteDinero = limiteDinero;
    this.fechaNacimiento = fechaNacimiento;
  }

  // métodos get y set para cada atributo
  public String getEmailCliente() {
    return emailCliente;
  }

  public void setEmailCliente(String emailCliente) {
    this.emailCliente = emailCliente;
  }

  public String getDNI() {
    return DNI;
  }

  public void setDNI(String DNI) {
    this.DNI = DNI;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public double getLimiteDinero() {
    return limiteDinero;
  }

  public void setLimiteDinero(double limiteDinero) {
    this.limiteDinero = limiteDinero;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }
}
