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
import model.entities.Asignacion;
import util.DBConnection;

/**
 *
 * @author USUARIO
 */
public class AsignacionDao {

    public List<Asignacion> listarAsignaciones() {
        List<Asignacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM asignacion";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Asignacion a = new Asignacion();
                a.setIdAsignacion(rs.getInt("id_asignacion"));
                a.setIdBus(rs.getInt("id_bus"));
                a.setIdRuta(rs.getInt("id_ruta"));
                a.setFecha(rs.getDate("fecha"));
                lista.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar asignaciones: " + e.getMessage());
        }

        return lista;
    }

    public boolean insertarAsignacion(Asignacion a) {
        String sql = "INSERT INTO asignacion (id_bus, id_ruta, fecha) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, a.getIdBus());
            stmt.setInt(2, a.getIdRuta());
            stmt.setDate(3, a.getFecha());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar asignación: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarAsignacion(int idAsignacion) {
        String sql = "DELETE FROM asignacion WHERE id_asignacion=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAsignacion);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar asignación: " + e.getMessage());
            return false;
        }
    }
}
