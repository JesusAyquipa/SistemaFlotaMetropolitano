package app;

import util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            if (connection != null) {
                System.out.println("✅ Conexión exitosa a PostgreSQL!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}