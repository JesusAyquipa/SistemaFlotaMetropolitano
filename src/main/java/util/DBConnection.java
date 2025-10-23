package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/bus_metro";
    private static final String USER = "bus_metro_admin"; // o tu usuario admin
    private static final String PASS = "12345";    // tu contraseña

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
