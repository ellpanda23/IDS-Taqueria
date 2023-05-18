/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Clases.CONEXION;
import Clases.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author isack
 */
public class DAOEMPLEADOS {
    public Empleado obtenerEmpleadoPorId(int idEmpleado) throws Exception {
        String sql = "SELECT * FROM Empleados WHERE idEmpleado = ?";
        try {
            if (CONEXION.conectar()) {
                PreparedStatement consulta = CONEXION.conexion.prepareStatement(sql);
                consulta.setInt(1, idEmpleado);
                ResultSet rs = consulta.executeQuery();
                if (rs.next()) {
                    Empleado empleado = new Empleado(
                            rs.getInt("idEmpleado"),
                            rs.getString("nombre"));
                    return empleado;
                } else {
                    throw new Exception("No se ha encontrado el empleado con el ID " + idEmpleado);
                }
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

