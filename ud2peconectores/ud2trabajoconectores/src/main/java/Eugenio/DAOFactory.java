
package Eugenio;

import java.sql.Connection;

public class DAOFactory {
    public static JugadorDAO createJugadorDAO(Connection connection) {
        return new JugadorDAO(connection);
    }

    public static VideojuegoDAO createVideojuegoDAO(Connection connection) {
        return new VideojuegoDAO(connection);
    }
}
