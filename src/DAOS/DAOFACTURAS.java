/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Clases.CONEXION;
import Clases.Facturas;
import Clases.Ordenes;
import Clases.Ventas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Brayan
 */
public class DAOFACTURAS {

    public ArrayList<Facturas> consultarTodos() throws Exception {
        String sql = "SELECT * FROM Facturas order by facturaid asc";
        try {
            if (CONEXION.conectar()) {
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                ArrayList<Facturas> listaOrdenes = new ArrayList<>();
                while (rsLista.next()) {
                    Facturas objP = new Facturas(
                            rsLista.getInt("Facturaid"),
                            rsLista.getInt("Cliente"),
                            rsLista.getInt("Empleado"),
                            rsLista.getString("Fecha"),
                            rsLista.getDouble("Total"));
                    listaOrdenes.add(objP);
                }
                return listaOrdenes;
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar la operación");
        } finally {
            CONEXION.desconectar();
        }
    }

    public static double TOTAL(double a) throws Exception {
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
    }

    public boolean fac(Facturas obj1) {
        try {
            if (CONEXION.conectarTrans()) {
                String sqlVenta = "INSERT INTO FACTURAS(Facturaid,Cliente,Empleado,Fecha,Total)"
                        + " VALUES(?,?,?,?,?)";
                PreparedStatement sentenciaFac = CONEXION.conexion.prepareStatement(sqlVenta, PreparedStatement.RETURN_GENERATED_KEYS);
                sentenciaFac.setInt(1, obj1.getFacid());
                sentenciaFac.setInt(2, obj1.getCliente());
                sentenciaFac.setInt(3, obj1.getEmpleado());
                sentenciaFac.setString(4, obj1.getFecha());
                sentenciaFac.setDouble(5, obj1.getTotal());
                sentenciaFac.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        return false;
    }

    public void eliminarOrden(int a) throws Exception {
        String sql = "DELETE FROM Facturas where Facturaid = ?";
        try {
            if (CONEXION.conectar()) {
                PreparedStatement sentencia = CONEXION.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                sentencia.setInt(1, a);
                sentencia.executeUpdate();
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar la operación");
        } finally {
            CONEXION.desconectar();
        }
    }
//
//    public int agregar(Facturas fac) throws Exception {
//        try {
//            if (CONEXION.conectar()) {
//                String sql = "INSERT INTO FACTURAS(Facturaid,Cliente,Empleado,Fecha,Total)"
//                        + " VALUES(?,?,?,?,?)";
//                PreparedStatement sentencia = CONEXION.conexion.prepareStatement(sql,
//                        PreparedStatement.RETURN_GENERATED_KEYS);
//                sentencia.setInt(1, (int) DAOORDENES.UltimaO());
//                sentencia.setInt(2, 1);
//                sentencia.setInt(3, 1);
//                sentencia.setString(4, "");
//                sentencia.setInt(5, 12);
//                sentencia.executeUpdate();
//
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "no se ha podido agregar");
//        }
//        return -1;
//    }

}
