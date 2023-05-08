/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Clases.CONEXION;
import Clases.Item;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author isack
 */
public class DAOITEMS {
    
    public ArrayList<Item> listar() throws Exception {
        String sql = "select * from ITEMS";
        try {
            if(CONEXION.conectar())
            {
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                ArrayList<Item> listaItems = new ArrayList<>();
                while (rsLista.next()) {
                    Item items = new Item(
                        rsLista.getInt(1),
                        rsLista.getString(2),
                        rsLista.getDouble(3),
                        rsLista.getInt(4)
                    );
                    // los numeros q salen despues de los getters son la posicion de la columna pa hacerlo mas facil
                    listaItems.add(items);

                }
                return listaItems;
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException e) {
            //System.out.println("Error al listar: " + e);
            throw new Exception("No se ha podido realizar la operación");
        } finally{
            CONEXION.desconectar();
        }   
    }

    // RETORNA VERDADERO SI SE PUDO AÑADIR EL OBJETO PARA QUE PUEDAS COMPROBAR SI SE AÑADIO REALMENTE
    // Y MANDES EL MEENSAJE CORRESPONDIENTE
    public boolean agregar(Item item) {
        try {
            if (CONEXION.conectarTrans()) {
                String sql = "insert into items (NOMBRE, PRECIO, INVENTARIO) values (?, ?, ?)";
                PreparedStatement sentencia = CONEXION.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                sentencia.setString(1, item.getNombre());
                sentencia.setDouble(2, item.getPrecio());
                sentencia.setInt(3, item.getInventario());
                sentencia.executeUpdate();
                return true;
                
                
            }
                   
        } catch (SQLException e) {
            // ARROJA EXCEPTIONS PARA QUE NO TENGAS QUE REGRESAR
            //System.out.println("Error en agregar" + e);
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        return false;
    }

    public boolean actualizar(Item item) {
        try {
            if (CONEXION.conectarTrans()) {
                String sql = "update items set NOMBRE=?, PRECIO=?, INVENTARIO=? where CODIGO=?";
                PreparedStatement sentencia = CONEXION.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                sentencia.setString(1, item.getNombre());
                sentencia.setDouble(2, item.getPrecio());
                sentencia.setInt(3, item.getInventario());
                sentencia.setInt(4, item.getCodigo());
                sentencia.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            //System.out.println("Error ActualizarOBJ: " + e);
            return false;
        }
        return false;
    }

    public boolean borrar(int id) {
        try {
            if (CONEXION.conectarTrans()) {
                String sql = "delete from items where codigo=?";
                PreparedStatement sentencia = CONEXION.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                sentencia.setInt(1, id);
                sentencia.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error en borrar " + e);
            return false;
        }
        return false;
    }
}
