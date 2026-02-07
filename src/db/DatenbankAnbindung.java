package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Verwaltet die Verbindung zur MySQL-Datenbank.
 */
public class DatenbankAnbindung {

    private static Connection connection;
    private static final String URL = "jdbc:mysql://10.25.2.145:3306";
    private static final String USER = "24gobu";
    private static final String PASSWORD = "geb24";

    /**
     * Liefert eine aktive Datenbankverbindung.
     */
    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Ошибка подключения к базе данных:\n" + e.getMessage(),
                    "Connection error",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }
        return connection;
    }

    /**
     * Schließt die Datenbankverbindung.
     */
    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Datenbankverbindung geschlossen.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
