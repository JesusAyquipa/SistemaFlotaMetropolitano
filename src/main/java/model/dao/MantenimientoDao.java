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
import model.entities.Mantenimiento;
import util.DBConnection;

/**
 *
 * @author USUARIO
 */
public class MantenimientoDao {


    public List<Mantenimiento> listarMantenimientos() {
        List<Mantenimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM mantenimiento";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Mantenimiento m = new Mantenimiento();
                m.setIdMantenimiento(rs.getInt("id_mantenimiento"));
                m.setIdBus(rs.getInt("id_bus"));
                m.setTipo(rs.getString("tipo"));
                m.setFecha(rs.getDate("fecha"));
                m.setKilometraje(rs.getInt("kilometraje"));
                m.setDescripcion(rs.getString("descripcion"));
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar mantenimientos: " + e.getMessage());
        }

        return lista;
    }


    public boolean insertarMantenimiento(Mantenimiento m) {
        String sql = "INSERT INTO mantenimiento (id_bus, tipo, fecha, kilometraje, descripcion) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, m.getIdBus());
            stmt.setString(2, m.getTipo());
            stmt.setDate(3, m.getFecha());
            stmt.setInt(4, m.getKilometraje());
            stmt.setString(5, m.getDescripcion());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar mantenimiento: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarMantenimiento(int idMantenimiento) {
        String sql = "DELETE FROM mantenimiento WHERE id_mantenimiento=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMantenimiento);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar mantenimiento: " + e.getMessage());
            return false;
        }
    }
}
