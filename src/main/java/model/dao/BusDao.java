package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.entities.Bus;
import util.DBConnection;

public class BusDao {
    
    // Listar todos los buses
    
    public List<Bus> listarBuses(){
    
        List<Bus> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM bus";
        
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            
            while(rs.next()){
            
                Bus bus = new Bus();
                
                bus.setIdBus(rs.getInt("id_bus"));
                bus.setPlaca(rs.getString("placa"));
                bus.setModelo(rs.getString("modelo"));
                bus.setKilometraje(rs.getInt("kilometraje"));
                bus.setEstado(rs.getString("estado"));
                lista.add(bus);
                
            }
        }catch(SQLException e){
            System.out.println("Error al listar buses " + e.getMessage());
        }
        
        return lista;
    }
    
    public boolean insertarBus(Bus bus){
        String sql = "INSERT INTO bus (placa, modelo, kilometraje, estado) VALUES (?,?,?,?)";
        
        try(Connection coon = DBConnection.getConnection(); PreparedStatement stmt = coon.prepareStatement(sql)){
        
            stmt.setString(1, bus.getPlaca());
            stmt.setString(2, bus.getModelo());
            stmt.setInt(3, bus.getKilometraje());
            stmt.setString(4, bus.getEstado());
            
            stmt.executeUpdate();
            return true;
            
            
        }catch(SQLException e){
            System.out.println("Error insertando Bus "+ e.getMessage());
            return false;
        }
        
    }
    
    public boolean actualizarBus(Bus bus){
        String sql= "UPDATE bus SET placa=?, modelo=?, kilometraje=?, estado=? WHERE id_bus=?";
        
        try(Connection coon = DBConnection.getConnection(); PreparedStatement stmt = coon.prepareStatement(sql)){
        
            stmt.setString(1, bus.getPlaca());
            stmt.setString(2, bus.getModelo());
            stmt.setInt(3, bus.getKilometraje());
            stmt.setString(4, bus.getEstado());
            stmt.setInt(5, bus.getIdBus());
            
            stmt.executeUpdate();
            return true;
            
        }catch(SQLException e){
            System.out.println("Eroor al actualizar bus "+ e.getMessage());
            return false;
        }
        
    }
    
    
    public boolean eliminarBus(int idBus){
    String sql = "DELETE FROM bus WHERE id_bus=?";
    
    try(Connection coon = DBConnection.getConnection(); PreparedStatement stmt = coon.prepareStatement(sql)){
    
        stmt.setInt(1, idBus);
        stmt.executeUpdate();
        return true;
        
    }catch(SQLException e){
        System.out.println("Error al eliminar bus " + e.getMessage() );
        return false;
    }
    
    }
    
    public Bus buscarPorPlaca(String placa) {
        String sql = "SELECT * FROM bus WHERE placa=?";
        Bus bus = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                bus = new Bus();
                bus.setIdBus(rs.getInt("id_bus"));
                bus.setPlaca(rs.getString("placa"));
                bus.setModelo(rs.getString("modelo"));
                bus.setKilometraje(rs.getInt("kilometraje"));
                bus.setEstado(rs.getString("estado"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar bus: " + e.getMessage());
        }

        return bus;
    }
}
