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

    int Facid;
    Cliente cliente;
    Empleado empleado;
    String Fecha;
    double Total;

    public Facturas(int Facid, int clienteId, int empleadoId, String Fecha, double Total) {
        this.Facid = Facid;
        this.cliente = new Cliente(clienteId);
        this.empleado = new Empleado(empleadoId);
        this.Fecha = Fecha;
        this.Total = Total;
    }

    public Facturas(int Facid, Cliente cliente, Empleado empleado, String Fecha, double Total) {
        this.Facid = Facid;
        this.cliente = cliente;
        this.empleado = empleado;
        this.Fecha = Fecha;
        this.Total = Total;
    }


    public int getFacid() {
        return Facid;
    }

    public void setFacid(int Facid) {
        this.Facid = Facid;
    }

   public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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
