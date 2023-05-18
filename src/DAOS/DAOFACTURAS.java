/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Clases.CONEXION;
import Clases.Cliente;
import Clases.Empleado;
import Clases.Facturas;
import Clases.Ordenes;
import Clases.Ventas;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Brayan
 */
public class DAOFACTURAS {

    /*public ArrayList<Facturas> consultarTodos() throws Exception {
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
    }*/
    
    public ArrayList<Facturas> consultarTodos() throws Exception {
        String sql = "SELECT f.*, c.*, e.* FROM Facturas f JOIN Clientes c ON f.Cliente = c.idCliente JOIN Empleados e ON f.Empleado = e.idEmpleado ORDER BY f.Facturaid ASC";
        try {
            if (CONEXION.conectar()) {
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                ArrayList<Facturas> listaFacturas = new ArrayList<>();
                while (rsLista.next()) {
                    Cliente cliente = new Cliente(rsLista.getInt("c.idCliente"), rsLista.getString("c.NOMBRE"));
                    Empleado empleado = new Empleado(rsLista.getInt("e.idEmpleado"), rsLista.getString("e.NOMBRE"));
                    Facturas factura = new Facturas(
                            rsLista.getInt("f.Facturaid"),
                            cliente,
                            empleado,
                            rsLista.getString("f.Fecha"),
                            rsLista.getDouble("f.Total"));
                    listaFacturas.add(factura);
                }
                return listaFacturas;
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            //throw new Exception("No se ha podido realizar la operación");
            throw ex;
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
                sentenciaFac.setInt(2, obj1.getCliente().getId());
                sentenciaFac.setInt(3, obj1.getEmpleado().getId());
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
    
    // REPORTE DETALLADO DE VENTAS
    
    public ArrayList<Facturas> consultarDesdeHasta(Date fechaDesde, Date fechaHasta) throws Exception {
        // Formatea las fechas en formato "dd/MM/yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaDesdeStr = dateFormat.format(fechaDesde);
        String fechaHastaStr = dateFormat.format(fechaHasta);

        // Crea la consulta SQL con los parámetros recibidos
        String sql = "SELECT Facturaid, c.*, e.*, Fecha, Total " +
                     "FROM Facturas f " +
                     "JOIN Clientes c ON f.Cliente = c.Clienteid " +
                     "JOIN Empleados e ON f.Empleado = e.EmpleadoId " +
                     "WHERE Fecha BETWEEN '" + fechaDesdeStr + "' AND '" + fechaHastaStr + "' ORDER BY Facturaid ASC";
        try {
            if (CONEXION.conectar()) {
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                ArrayList<Facturas> listaFacturas = new ArrayList<>();
                while (rsLista.next()) {
                    Facturas objFactura = new Facturas(
                            rsLista.getInt("Facturaid"),
                            new Cliente(rsLista.getInt("Clienteid"), rsLista.getString("NombreCliente")),
                            new Empleado(rsLista.getInt("EmpleadoId"), rsLista.getString("NombreEmpleado")),
                            rsLista.getString("Fecha"),
                            rsLista.getDouble("Total"));
                    listaFacturas.add(objFactura);
                }
                return listaFacturas;
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar la operación");
        } finally {
            CONEXION.desconectar();
        }
    }

    
    public ArrayList<Facturas> consultarDesde(Date fechaDesde) throws Exception {
        String sql = "SELECT * FROM Facturas WHERE Fecha >= ? ORDER BY facturaid ASC";
        try {
            if (CONEXION.conectar()) {
                PreparedStatement consulta = CONEXION.conexion.prepareStatement(sql);
                consulta.setDate(1, fechaDesde);
                ResultSet rsLista = consulta.executeQuery();
                ArrayList<Facturas> listaFacturas = new ArrayList<>();
                while (rsLista.next()) {
                    // Obtener los IDs del cliente y el empleado
                    int idCliente = rsLista.getInt("Cliente");
                    int idEmpleado = rsLista.getInt("Empleado");

                    DAOCLIENTES daoClientes = new DAOCLIENTES();
                    DAOEMPLEADOS daoEmpleado = new DAOEMPLEADOS();
                    
                    // Obtener los objetos Cliente y Empleado a partir de sus IDs
                    Cliente cliente = daoClientes.obtenerClientePorId(idCliente);
                    Empleado empleado = daoEmpleado.obtenerEmpleadoPorId(idEmpleado);

                    // Crear un nuevo objeto Facturas con los objetos Cliente y Empleado
                    Facturas objFactura = new Facturas(
                            rsLista.getInt("Facturaid"),
                            cliente,
                            empleado,
                            rsLista.getString("Fecha"),
                            rsLista.getDouble("Total"));
                    listaFacturas.add(objFactura);
                }
                return listaFacturas;
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar la operación");
        } finally {
            CONEXION.desconectar();
        }
    }
    
    public ArrayList<Facturas> consultarHasta(Date fechaHasta) throws Exception {
        String sql = "SELECT * FROM Facturas WHERE Fecha <= ? ORDER BY facturaid ASC";
        try {
            if (CONEXION.conectar()) {
                PreparedStatement consulta = CONEXION.conexion.prepareStatement(sql);
                consulta.setDate(1, fechaHasta);
                ResultSet rsLista = consulta.executeQuery();
                ArrayList<Facturas> listaFacturas = new ArrayList<>();
                while (rsLista.next()) {
                    // Obtener los IDs del cliente y el empleado
                    int idCliente = rsLista.getInt("Cliente");
                    int idEmpleado = rsLista.getInt("Empleado");

                    DAOCLIENTES daoClientes = new DAOCLIENTES();
                    DAOEMPLEADOS daoEmpleado = new DAOEMPLEADOS();
                    
                    // Obtener los objetos Cliente y Empleado a partir de sus IDs
                    Cliente cliente = daoClientes.obtenerClientePorId(idCliente);
                    Empleado empleado = daoEmpleado.obtenerEmpleadoPorId(idEmpleado);
                    
                    Facturas objFactura = new Facturas(
                            rsLista.getInt("Facturaid"),
                            cliente,
                            empleado,
                            rsLista.getString("Fecha"),
                            rsLista.getDouble("Total"));
                    listaFacturas.add(objFactura);
                }
                return listaFacturas;
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar la operación");
        } finally {
            CONEXION.desconectar();
        }
    }

    
    public Facturas buscarFacturaPorId(int id) throws Exception {
        String sql = "SELECT * FROM Facturas WHERE Facturaid = ?";
        try {
            if (CONEXION.conectar()) {
                PreparedStatement consulta = CONEXION.conexion.prepareStatement(sql);
                consulta.setInt(1, id);
                ResultSet rs = consulta.executeQuery();
                if (rs.next()) {
                    
                    // Obtener los IDs del cliente y el empleado
                    int idCliente = rs.getInt("Cliente");
                    int idEmpleado = rs.getInt("Empleado");

                    DAOCLIENTES daoClientes = new DAOCLIENTES();
                    DAOEMPLEADOS daoEmpleado = new DAOEMPLEADOS();
                    
                    // Obtener los objetos Cliente y Empleado a partir de sus IDs
                    Cliente cliente = daoClientes.obtenerClientePorId(idCliente);
                    Empleado empleado = daoEmpleado.obtenerEmpleadoPorId(idEmpleado);
                    
                    
                    
                    Facturas factura = new Facturas(
                            rs.getInt("Facturaid"),
                            cliente,
                            empleado,
                            rs.getString("Fecha"),
                            rs.getDouble("Total"));
                    return factura;
                } else {
                    throw new Exception("No se encontró una factura con el ID especificado");
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
    
    

    public ArrayList<Facturas> buscarFacturas(String criterio) throws Exception {
        try {
            if (CONEXION.conectar()) {
                if(criterio.matches("[0-9]+"))
                {
                    ArrayList<Facturas> listaFacturas = new ArrayList<>();
                    listaFacturas.add(buscarFacturaPorId(Integer.parseInt(criterio)));
                    return listaFacturas;
                }
                
                String sql = "SELECT * FROM Facturas WHERE Empleado IN (SELECT idEmpleado FROM Empleados WHERE Nombre LIKE ?) OR Fecha LIKE ? OR Cliente IN (SELECT idCliente FROM Clientes WHERE Nombre LIKE ?) ORDER BY facturaid ASC";
                PreparedStatement consulta = CONEXION.conexion.prepareStatement(sql);
                consulta.setString(1, "%" + criterio + "%");
                consulta.setString(2, "%" + criterio + "%");
                consulta.setString(3, "%" + criterio + "%");
                ResultSet rsLista = consulta.executeQuery();
                ArrayList<Facturas> listaFacturas = new ArrayList<>();
                while (rsLista.next()) {
                    
                    // Obtener los IDs del cliente y el empleado
                    int idCliente = rsLista.getInt("Cliente");
                    int idEmpleado = rsLista.getInt("Empleado");

                    DAOCLIENTES daoClientes = new DAOCLIENTES();
                    DAOEMPLEADOS daoEmpleado = new DAOEMPLEADOS();
                    
                    // Obtener los objetos Cliente y Empleado a partir de sus IDs
                    Cliente cliente = daoClientes.obtenerClientePorId(idCliente);
                    Empleado empleado = daoEmpleado.obtenerEmpleadoPorId(idEmpleado);
                    
                    Facturas objFactura = new Facturas(
                            rsLista.getInt("Facturaid"),
                            cliente,
                            empleado,
                            rsLista.getString("Fecha"),
                            rsLista.getDouble("Total"));
                    listaFacturas.add(objFactura);
                }
                return listaFacturas;
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            throw new Exception(ex);
        } finally {
            CONEXION.desconectar();
        }
    }




}
