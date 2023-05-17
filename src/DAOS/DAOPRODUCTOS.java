/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Clases.Productos;
import Clases.CONEXION;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Brayan
 */
public class DAOPRODUCTOS {
    public ArrayList<String> consultarTodos() throws Exception {
        try {
            if (CONEXION.conectar()) {
                String sql = "SELECT Nombre"
                        + " FROM Productos";

                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                ArrayList<String> listaProductos = new ArrayList<>();
                while (rsLista.next()) {
                   String objP = rsLista.getString("Nombre");
                    listaProductos.add(objP);
                }
                return listaProductos;
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
