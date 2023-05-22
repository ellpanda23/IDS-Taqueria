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

    public ArrayList<Facturas> consultarTodos() throws Exception {
        String sql = "SELECT * FROM Facturas";
        try {
            if (CONEXION.conectar()) {
                Statement consulta = CONEXION.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                ArrayList<Facturas> listaOrdenes = new ArrayList<>();
                while (rsLista.next()) {
                    Cliente a = new Cliente((int) rsLista.getObject("Cliente"), "JOSE");
                    Empleado a1 = new Empleado((int) rsLista.getObject("Empleado"), "MANUEL");
                    Facturas objP = new Facturas(
                            rsLista.getInt("Facturaid"),
                            (a),
                            (a1),
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

    // REPORTE DETALLADO DE VENTAS
    public ArrayList<Facturas> consultarRangoFechas(Date fechaDesde, Date fechaHasta) throws Exception {
        String sql = "SELECT * FROM Facturas WHERE Fecha >= ? AND Fecha <= ? ORDER BY facturaid ASC";
        try {
            if (CONEXION.conectar()) {
                PreparedStatement consulta = CONEXION.conexion.prepareStatement(sql);
                consulta.setDate(1, fechaDesde);
                consulta.setDate(2, fechaHasta);
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
                if (criterio.matches("[0-9]+")) {
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

    public ArrayList<Facturas> consultarRangoCriterio(String criterio, Date fechaDesde, Date fechaHasta) throws Exception {
        try {
            if (CONEXION.conectar()) {
                if (criterio.matches("[0-9]+")) {
                    ArrayList<Facturas> listaFacturas = new ArrayList<>();
                    listaFacturas.add(buscarFacturaPorId(Integer.parseInt(criterio)));
                    return listaFacturas;
                }

                String sql = "SELECT * FROM Facturas WHERE (Fecha >= ? AND Fecha <= ?) AND (Empleado IN (SELECT idEmpleado FROM Empleados WHERE Nombre LIKE ?) OR Fecha LIKE ? OR Cliente IN (SELECT idCliente FROM Clientes WHERE Nombre LIKE ?)) ORDER BY facturaid ASC";
                PreparedStatement consulta = CONEXION.conexion.prepareStatement(sql);
                consulta.setDate(1, fechaDesde);
                consulta.setDate(2, fechaHasta);
                consulta.setString(3, "%" + criterio + "%");
                consulta.setString(4, "%" + criterio + "%");
                consulta.setString(5, "%" + criterio + "%");
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

    public ArrayList<Facturas> consultarDesdeCriterio(String criterio, Date fechaDesde) throws Exception {
        try {
            if (CONEXION.conectar()) {
                if (criterio.matches("[0-9]+")) {
                    ArrayList<Facturas> listaFacturas = new ArrayList<>();
                    listaFacturas.add(buscarFacturaPorId(Integer.parseInt(criterio)));
                    return listaFacturas;
                }

                String sql = "SELECT * FROM Facturas WHERE Fecha >= ? AND (Empleado IN (SELECT idEmpleado FROM Empleados WHERE Nombre LIKE ?) OR Fecha LIKE ? OR Cliente IN (SELECT idCliente FROM Clientes WHERE Nombre LIKE ?)) ORDER BY facturaid ASC";
                PreparedStatement consulta = CONEXION.conexion.prepareStatement(sql);
                consulta.setDate(1, fechaDesde);
                consulta.setString(2, "%" + criterio + "%");
                consulta.setString(3, "%" + criterio + "%");
                consulta.setString(4, "%" + criterio + "%");
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

    public ArrayList<Facturas> consultarHastaCriterio(String criterio, Date fechaHasta) throws Exception {
        try {
            if (CONEXION.conectar()) {
                if (criterio.matches("[0-9]+")) {
                    ArrayList<Facturas> listaFacturas = new ArrayList<>();
                    listaFacturas.add(buscarFacturaPorId(Integer.parseInt(criterio)));
                    return listaFacturas;
                }

                String sql = "SELECT * FROM Facturas WHERE Fecha <= ? AND (Empleado IN (SELECT idEmpleado FROM Empleados WHERE Nombre LIKE ?) OR Fecha LIKE ? OR Cliente IN (SELECT idCliente FROM Clientes WHERE Nombre LIKE ?)) ORDER BY facturaid ASC";
                PreparedStatement consulta = CONEXION.conexion.prepareStatement(sql);
                consulta.setDate(1, fechaHasta);
                consulta.setString(2, "%" + criterio + "%");
                consulta.setString(3, "%" + criterio + "%");
                consulta.setString(4, "%" + criterio + "%");
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
