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
import model.entities.Estacion;
import util.DBConnection;

/**
 *
 * @author USUARIO
 */
public class EstacionDao {

    public List<Estacion> listarEstaciones() {
        List<Estacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM estacion";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Estacion e = new Estacion();
                e.setIdEstacion(rs.getInt("id_estacion"));
                e.setNombre(rs.getString("nombre"));
                e.setDistrito(rs.getString("distrito"));
                e.setCapacidad(rs.getInt("capacidad"));
                lista.add(e);
            }

        } catch (SQLException ex) {
            System.out.println("Error al listar estaciones: " + ex.getMessage());
        }

        return lista;
    }

    public boolean insertarEstacion(Estacion estacion) {
        String sql = "INSERT INTO estacion (nombre, distrito, capacidad) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estacion.getNombre());
            stmt.setString(2, estacion.getDistrito());
            stmt.setInt(3, estacion.getCapacidad());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Error al insertar estación: " + ex.getMessage());
            return false;
        }
    }

    public boolean actualizarEstacion(Estacion estacion) {
        String sql = "UPDATE estacion SET nombre=?, distrito=?, capacidad=? WHERE id_estacion=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estacion.getNombre());
            stmt.setString(2, estacion.getDistrito());
            stmt.setInt(3, estacion.getCapacidad());
            stmt.setInt(4, estacion.getIdEstacion());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Error al actualizar estación: " + ex.getMessage());
            return false;
        }
    }

    public boolean eliminarEstacion(int idEstacion) {
        String sql = "DELETE FROM estacion WHERE id_estacion=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEstacion);
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Error al eliminar estación: " + ex.getMessage());
            return false;
        }
    }
}