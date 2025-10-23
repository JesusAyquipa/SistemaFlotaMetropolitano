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
import model.entities.Ruta;
import util.DBConnection;

/**
 *
 * @author USUARIO
 */
public class RutaDao {
    public List<Ruta> listarRutas() {
        List<Ruta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ruta";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ruta ruta = new Ruta();
                ruta.setIdRuta(rs.getInt("id_ruta"));
                ruta.setNombre(rs.getString("nombre"));
                ruta.setOrigen(rs.getString("origen"));
                ruta.setDestino(rs.getString("destino"));
                lista.add(ruta);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar rutas: " + e.getMessage());
        }

        return lista;
    }

    public boolean insertarRuta(Ruta ruta) {
        String sql = "INSERT INTO ruta (nombre, origen, destino) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ruta.getNombre());
            stmt.setString(2, ruta.getOrigen());
            stmt.setString(3, ruta.getDestino());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar ruta: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarRuta(Ruta ruta) {
        String sql = "UPDATE ruta SET nombre=?, origen=?, destino=? WHERE id_ruta=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ruta.getNombre());
            stmt.setString(2, ruta.getOrigen());
            stmt.setString(3, ruta.getDestino());
            stmt.setInt(4, ruta.getIdRuta());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar ruta: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarRuta(int idRuta) {
        String sql = "DELETE FROM ruta WHERE id_ruta=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRuta);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar ruta: " + e.getMessage());
            return false;
        }
    }
}
