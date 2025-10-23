/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entities.Falla;
import util.DBConnection;

/**
 *
 * @author USUARIO
 */
public class FallaDao {

    public List<Falla> listarFallas() {
        List<Falla> lista = new ArrayList<>();
        String sql = "SELECT * FROM falla";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Falla f = new Falla();
                f.setIdFalla(rs.getInt("id_falla"));
                f.setIdBus(rs.getInt("id_bus"));
                f.setDescripcion(rs.getString("descripcion"));
                f.setFecha(rs.getDate("fecha"));
                lista.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar fallas: " + e.getMessage());
        }

        return lista;
    }

    public boolean insertarFalla(Falla f) {
        String sql = "INSERT INTO falla (id_bus, descripcion, fecha) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, f.getIdBus());
            stmt.setString(2, f.getDescripcion());
            stmt.setDate(3, f.getFecha());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar falla: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarFalla(int idFalla) {
        String sql = "DELETE FROM falla WHERE id_falla=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFalla);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar falla: " + e.getMessage());
            return false;
        }
    }
}
