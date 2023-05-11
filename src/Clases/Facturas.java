/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Brayan
 */
public class Facturas {

    int Facid, Cliente, Empleado;
    String Fecha;
    double Total;

    public Facturas(int Facid, int Cliente, int Empleado, String Fecha, double Total) {
        this.Facid = Facid;
        this.Cliente = Cliente;
        this.Empleado = Empleado;
        this.Fecha = Fecha;
        this.Total = Total;
    }

    public int getFacid() {
        return Facid;
    }

    public void setFacid(int Facid) {
        this.Facid = Facid;
    }

    public int getCliente() {
        return Cliente;
    }

    public void setCliente(int Cliente) {
        this.Cliente = Cliente;
    }

    public int getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(int Empleado) {
        this.Empleado = Empleado;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }


}
