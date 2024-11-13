/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexiones;

import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author eugeniolorentecristobal
 */
public class ConexionSelector {
    public static Connection obtenerConexion(String tipoBaseDatos) throws SQLException {
        Connection conexion = null;
        try {
            if (tipoBaseDatos.equalsIgnoreCase("MySQL")) {
                conexion = Mysqlconexion.getConnection();
                System.out.println("Conectado a MySQL exitosamente");
            } else if (tipoBaseDatos.equalsIgnoreCase("PostgreSQL")) {
                conexion = PostgreConexion.getConnection();
                System.out.println("Conectado a PostgreSQL exitosamente");
            } else {
                throw new IllegalArgumentException("Tipo de base de datos no soportado: " + tipoBaseDatos);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            throw e;
        }
        return conexion;
    }
}

