/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Clases.CONEXION;
import Clases.Ordenes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Brayan
 */
public class DAOORDENES {

    public static int orden;

    public ArrayList<Ordenes> consultarTodos() throws Exception {
        String sql = "SELECT * FROM Ordenes order by Orderid asc";
        try {
            if (CONEXION.conectar()) {
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                ArrayList<Ordenes> listaOrdenes = new ArrayList<>();
                while (rsLista.next()) {
                    Ordenes objP = new Ordenes(
                            rsLista.getInt("Orderid"),
                            rsLista.getInt("Clienteid"),
                            rsLista.getInt("Empleadoid"),
                            rsLista.getString("Fecha"),
                            rsLista.getString("Lugar"));
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

    public static int UltimaO() throws SQLException {
        try {
            if (CONEXION.conectar()) {
                String sql2 = "SELECT MAX(Orderid) from Ordenes";
                Statement consulta1 = CONEXION.conexion.createStatement();
                ResultSet rs = consulta1.executeQuery(sql2);
                if (rs.next()) {
                    orden = rs.getInt(1);
                } else {
                    orden = -1;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la conexion");
        }
        return orden;
    }

    public void eliminarOrden(int a) throws Exception {
        String sql = "DELETE FROM ORDENES where orderid = ?";
        try {
            if (CONEXION.conectar()) {
                PreparedStatement sentencia = CONEXION.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                sentencia.setInt(1, a);
                sentencia.execute();
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar la operación");
        } finally {
            CONEXION.desconectar();
        }
    }
}
