/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Clases.Ordenes;
import Clases.Ventas;
import Clases.CONEXION;
import Clases.Facturas;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Brayan
 */
public class DAOVENTAS {

    private int OrdenS = 0;

    public DAOVENTAS() {

    }

    public int getOrdenS() {
        return OrdenS;
    }

    public void setOrdenS(int OrdenS) {
        this.OrdenS = OrdenS;
    }

    public static int EliminarLista(List<Ventas> ventas, Ventas a) {
        int cont = 0;
        for (Ventas b : ventas) {
            if (b.getProducto() == a.getProducto() && b.getCantidad() == a.getCantidad() && b.getPrecio() == a.getPrecio() && b.getSubtotal() == a.getSubtotal()) {
                return cont;
            }
            cont++;
        }
        return 0;
    }

    public double PyS(String N) {
        double precio = 0;
        try {
            if (CONEXION.conectar()) {
                String sql = "SELECT PRECIO FROM PLATILLOS WHERE Nombre =" + "'" + N + "'";
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                while (rsLista.next()) {
                    precio = rsLista.getDouble("PRECIO");
                }
                return precio;
            }
        } catch (Exception e) {
        }
        return precio;
    }

    public boolean agregarVarios(List<Ventas> ventas, Ordenes objOrden, int bandera) throws Exception {
        if (bandera == 0) {
            try {
                if (CONEXION.conectarTrans()) {
                    String sql = "INSERT INTO Ordenes(Clienteid,Empleadoid,Fecha,LUGAR) VALUES(?,?,?,?)";
                    PreparedStatement sentencia = CONEXION.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    //sentencia.setInt(1, objOrden.getOrderid());
                    sentencia.setInt(1, objOrden.getClienteid());
                    sentencia.setInt(2, objOrden.getEmpleadoid());
                    sentencia.setString(3, objOrden.getFecha());
                    sentencia.setString(4, objOrden.getLugar());
                    sentencia.executeUpdate();
                    ResultSet rsLista = sentencia.getGeneratedKeys();
                    int ordenId = 0;
                    if (rsLista.next()) {
                        ordenId = rsLista.getInt(1);
                    } else {
                        JOptionPane.showMessageDialog(null, "NO TIENE NADA");
                    }
                    for (Ventas venta : ventas) {
                        String sqlVenta = "INSERT INTO DETALLESORDEN(Orderid,Producto,Precio,Cantidad,Subtotal)"
                                + " VALUES(?,?,?,?,?)";
                        PreparedStatement sentenciaVenta = CONEXION.conTrans.prepareStatement(sqlVenta, PreparedStatement.RETURN_GENERATED_KEYS);
                        int a = (DAOORDENES.UltimaO());
                        sentenciaVenta.setInt(1, a);
                        sentenciaVenta.setString(2, venta.getProducto());
                        sentenciaVenta.setDouble(3, venta.getPrecio());
                        sentenciaVenta.setDouble(4, venta.getCantidad());
                        sentenciaVenta.setDouble(5, venta.getSubtotal());
                        sentenciaVenta.executeUpdate();
                    }
                    CONEXION.conTrans.commit();
                    return true;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                CONEXION.conTrans.rollback();
                return false;
            }
        } else {
            try {
                ActualizarL(objOrden.getLugar(), bandera);
                for (Ventas venta : ventas) {
                    String sqlVenta = "INSERT INTO DETALLESORDEN(Orderid,Producto,Precio,Cantidad,Subtotal)"
                            + " VALUES(?,?,?,?,?)";
                    PreparedStatement sentenciaVenta = CONEXION.conexion.prepareStatement(sqlVenta, PreparedStatement.RETURN_GENERATED_KEYS);
                    sentenciaVenta.setInt(1, bandera);
                    sentenciaVenta.setString(2, venta.getProducto());
                    sentenciaVenta.setDouble(3, venta.getPrecio());
                    sentenciaVenta.setDouble(4, venta.getCantidad());
                    sentenciaVenta.setDouble(5, venta.getSubtotal());
                    sentenciaVenta.executeUpdate();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return false;
    }

    public void ActualizarL(String a, int b) {
        try {
            String sql = "UPDATE Ordenes SET Lugar = '" + a + "' WHERE orderid = " + b;
            Statement statement = CONEXION.conexion.createStatement();
            int filasActualizadas = statement.executeUpdate(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public int agregar(Ventas Ventas) throws Exception {
        try {
            if (CONEXION.conectar()) {
                String sql = "INSERT INTO DETALLESORDEN(Orderid,Producto,Precio,Cantidad,Subtotal)"
                        + " VALUES(?,?,?,?,?)";
                PreparedStatement sentencia = CONEXION.conexion.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                sentencia.setInt(1, DAOORDENES.UltimaO());
                sentencia.setInt(2, (int) DAOVENTAS.IdProduct(Ventas.getProducto()));
                sentencia.setDouble(3, Ventas.getPrecio());
                sentencia.setDouble(4, Ventas.getCantidad());
                sentencia.setDouble(5, Ventas.getSubtotal());
                sentencia.executeUpdate();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se ha podido agregar");
        }
        return -1;
    }

    public static double IdProduct(String N) {
        double id = 0;
        try {
            if (CONEXION.conectar()) {
                String sql = "SELECT Productoid FROM Platillos WHERE Nombre =" + "'" + N + "'";
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                while (rsLista.next()) {
                    id = rsLista.getDouble("Productoid");
                }
                return id;
            }
        } catch (Exception e) {
        }
        return id;
    }
//CONSULTA TODOS LOS PRODUCTOS DE UNA ORDEN(REGRESA UNA LISTA)

    public ArrayList<String> ConsultarEditar(int N) {
        ArrayList<String> lista = new ArrayList();
        try {
            if (CONEXION.conectar()) {
                String sql = "SELECT PRODUCTO FROM DETALLESORDEN WHERE Orderid =" + N;
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                while (rsLista.next()) {
                    lista.add(rsLista.getString("Producto"));
                }
                return lista;
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public static boolean ConsultarEditarT(String N, int a) {
        ArrayList<Ventas> lista = new ArrayList();
        try {
            if (CONEXION.conectar()) {
                String sql = "SELECT * FROM DETALLESORDEN WHERE Producto =" + "'" + N + "'" + " and orderid =" + a;
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                while (rsLista.next()) {
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }
//BORRAR PLATILLO

    public static boolean Act(String N, double cantidad, int id) {
        try {
            if (CONEXION.conectar()) {
                String sql = "UPDATE DETALLESORDEN SET CANTIDAD = ? WHERE orderid= ? and producto =" + "'" + N + "'";
                PreparedStatement sentencia = CONEXION.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                sentencia.setDouble(1, cantidad);
                sentencia.setInt(2, id);
                sentencia.execute();
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        return false;
    }
    public boolean BorrarProducto(String a, int b) {
        try {
            if (CONEXION.conectar()) {
                String sql = "DELETE FROM DETALLESORDEN WHERE Orderid =" + b + " AND PRODUCTO = " + "'" + a + "'";
                Statement consulta = CONEXION.conexion.createStatement();
                consulta.executeUpdate(sql);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        return false;
    }
//CONSULTA TODOS LOS DETALLES DE LAS ORDENES

    public ArrayList<Ventas> consultarTodos(int a) throws Exception {
        String sql = "SELECT Producto,Cantidad,Precio,Subtotal"
                + "   FROM DETALLESORDEN where orderid =" + a;
        try {
            if (CONEXION.conectar()) {
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                ArrayList<Ventas> listaOrdenes = new ArrayList<>();
                while (rsLista.next()) {
                    Ventas objP = new Ventas(
                            rsLista.getString("Producto"),
                            rsLista.getInt("Cantidad"),
                            rsLista.getInt("Precio"),
                            rsLista.getInt("Subtotal"));
                    listaOrdenes.add(objP);
                }
                return listaOrdenes;
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar la operación");
        }
//finally {
//            CONEXION.desconectar();
//        }
    }
//REGRESA EL TOTAL DE UNA ORDEN

    public double TOTAL(double a) throws Exception {
        String sql = "SELECT Subtotal"
                + "   FROM DETALLESORDEN where orderid =" + a;
        try {
            if (CONEXION.conectar()) {
                double T2 = 0;
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                while (rsLista.next()) {
                    T2 = T2 + rsLista.getDouble("Subtotal");
                }
                return T2;
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar la operación");
        }
//finally {
//            CONEXION.desconectar();
//        }
    }
//CREAR EL TICKET

    public String consultarTodos1(int a) throws Exception {
        String sql = "SELECT Producto,Cantidad,Precio,Subtotal"
                + "   FROM DETALLESORDEN where orderid =" + a;
        try {
            if (CONEXION.conectar()) {
                String arreglo = "No.Orden =" + a + "\n";
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                arreglo += "--------------------------------------\n"
                        + "            TAQUERÍA\n"
                        + "--------------------------------------\n"
                        + "Fecha:" + String.valueOf(LocalDate.now()) + "\n"
                        //  + "Hora:"++"\n"
                        + "Orden:\nPRODUCTO    CANTIDAD   PRECIO\n";
                while (rsLista.next()) {
                    arreglo += rsLista.getString("Producto") + "              ";
                    arreglo += rsLista.getInt("Cantidad") + "                 $";
                    arreglo += rsLista.getInt("Precio");
                    arreglo += "\n-----------------------------------------------\n";
                }
                arreglo += "------------------------------\n"
                        + "TOTAL = " + TOTAL(a) + "\n"
                        + "-----------------------------";
                return arreglo;
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar la operación " + ex);
        }
    }
}
