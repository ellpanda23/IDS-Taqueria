/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Clases.CONEXION;
import Clases.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author isack
 */
public class DAOCLIENTES {
    
    public Cliente obtenerClientePorId(int idCliente) throws Exception {
        String sql = "SELECT * FROM Clientes WHERE idCliente = ?";
        try {
            if (CONEXION.conectar()) {
                PreparedStatement consulta = CONEXION.conexion.prepareStatement(sql);
                consulta.setInt(1, idCliente);
                ResultSet rs = consulta.executeQuery();
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("idCliente"),
                            rs.getString("nombre"));
                    return cliente;
                } else {
                    throw new Exception("No se ha encontrado el cliente con el ID " + idCliente);
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
