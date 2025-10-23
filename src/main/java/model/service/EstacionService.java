/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.service;

import java.util.List;
import model.dao.EstacionDao;
import model.entities.Estacion;

/**
 *
 * @author USUARIO
 */
public class EstacionService {
    private EstacionDao estacionDao = new EstacionDao();

    public List<Estacion> obtenerTodas() {
        return estacionDao.listarEstaciones();
    }

    public boolean registrarEstacion(Estacion estacion) {
        if (estacion.getCapacidad() <= 0) {
            System.out.println("⚠️ Error: la capacidad debe ser mayor a 0.");
            return false;
        }
        return estacionDao.insertarEstacion(estacion);
    }

    public boolean actualizarEstacion(Estacion estacion) {
        return estacionDao.actualizarEstacion(estacion);
    }

    public boolean eliminarEstacion(int idEstacion) {
        return estacionDao.eliminarEstacion(idEstacion);
    }
}