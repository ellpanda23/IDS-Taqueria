/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Brayan
 */
public class Ordenes {

    private int Orderid;
    private int Clienteid;
    private int Empleadoid;
    private String Fecha;
    private String Lugar;

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String Lugar) {
        this.Lugar = Lugar;
    }

    public Ordenes(int Orderid, int Clienteid, int Empleadoid, String Fecha, String Lugar) {
        this.Orderid = Orderid;
        this.Clienteid = Clienteid;
        this.Empleadoid = Empleadoid;
        this.Fecha = Fecha;
        this.Lugar = Lugar;
    }

    public Ordenes(int Clienteid, int Empleadoid, String Fecha, String Lugar) {
        this.Clienteid = Clienteid;
        this.Empleadoid = Empleadoid;
        this.Fecha = Fecha;
        this.Lugar = Lugar;
    }

    public int getOrderid() {
        return Orderid;
    }

    public void setOrderid(int Orderid) {
        this.Orderid = Orderid;
    }

    public int getClienteid() {
        return Clienteid;
    }

    public void setClienteid(int Clienteid) {
        this.Clienteid = Clienteid;
    }

    public int getEmpleadoid() {
        return Empleadoid;
    }

    public void setEmpleadoid(int Empleadoid) {
        this.Empleadoid = Empleadoid;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

}
