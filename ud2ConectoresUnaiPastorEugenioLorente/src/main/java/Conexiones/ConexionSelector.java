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
    /**
     * Obtiene una conexión a la base de datos especificada.
     *
     * @param tipoBaseDatos Tipo de base de datos (por ejemplo, "MySQL" o "PostgreSQL").
     * @return Un objeto {@link Connection} si la conexión es exitosa, o {@code null} si ocurre un error
     *         o el tipo de base de datos no es soportado.
     */
    public static Connection obtenerConexion(String tipoBaseDatos) {
        Connection conexion = null;
        try {
            if (tipoBaseDatos.equalsIgnoreCase("MySQL")) {
                conexion = Mysqlconexion.getConnection();
                System.out.println("Conectado a MySQL exitosamente");
            } else if (tipoBaseDatos.equalsIgnoreCase("PostgreSQL")) {
                conexion = PostgreConexion.getConnectionUnaiCasa();
                System.out.println("Conectado a PostgreSQL exitosamente");
            } else {
                System.err.println("Tipo de base de datos no soportado: " + tipoBaseDatos);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error en los parámetros: " + e.getMessage());
        }
        return conexion;
    }
}