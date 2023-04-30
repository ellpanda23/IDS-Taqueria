/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Brayan
 */
public class CONEXION {

    public static Connection conexion, conTrans;

    private static  String a = "jdbc:mysql://localhost:3306/taqueria?user=root&password=";

    public static boolean conectar() {
        try {
            conexion = DriverManager.getConnection(a);

            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean conectarTrans() {
        try {
            conTrans = DriverManager.getConnection(a);
            conTrans.setAutoCommit(false);

            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static void desconectar() {
        try {
            conexion.close();
        } catch (Exception ex) {
        }
    }

}
