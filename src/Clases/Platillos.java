/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Brayan
 */
public class Platillos {
    private int idproducto;
    private String Nombre;
    private double precio;

    public Platillos(int idproducto, String Nombre) {
        this.idproducto = idproducto;
        this.Nombre = Nombre;
    }

    
    public Platillos(int idproducto, String nombre, double precio)
    {
        this.idproducto = idproducto;
        this.Nombre = nombre;
        this.precio = precio;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


}
