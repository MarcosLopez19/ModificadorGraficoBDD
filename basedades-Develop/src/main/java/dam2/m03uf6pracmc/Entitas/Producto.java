/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Entitas;

/**
 *
 * @author guiup
 */
public class Producto {
  // Atributos
  private int codigo;
  private String nombre;
  private String descripcion;
  private int stock;
  private double precio;
  
  // Constructor
  public Producto(int codigo, String nombre, String descripcion, int stock, double precio) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.stock = stock;
    this.precio = precio;
  }
  
  // Constructor vacío
   public Producto() {
    this.codigo = 0;
    this.nombre = "";
    this.descripcion = "";
    this.stock = 0;
    this.precio = 0;
   }
   
  // Métodos getters y setters para cada atributo
  public int getCodigo() {
    return codigo;
  }
  
  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public String getDescripcion() {
    return descripcion;
  }
  
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  public int getStock() {
    return stock;
  }
  
  public void setStock(int stock) {
    this.stock = stock;
  }
  
  public double getPrecio() {
    return precio;
  }
  
  public void setPrecio(double precio) {
    this.precio = precio;
  }
}
