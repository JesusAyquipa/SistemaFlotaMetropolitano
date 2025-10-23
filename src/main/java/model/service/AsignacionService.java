/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.service;

import java.util.List;
import model.dao.AsignacionDao;
import model.entities.Asignacion;

/**
 *
 * @author USUARIO
 */
public class AsignacionService {
    private AsignacionDao asignacionDao = new AsignacionDao();

    public List<Asignacion> obtenerTodas() {
        return asignacionDao.listarAsignaciones();
    }

    public boolean registrarAsignacion(Asignacion asignacion) {
        if (asignacion.getIdBus() <= 0 || asignacion.getIdRuta() <= 0) {
            System.out.println("⚠️ Error: debe asignarse un bus y una ruta válidos.");
            return false;
        }
        return asignacionDao.insertarAsignacion(asignacion);
    }

    public boolean eliminarAsignacion(int idAsignacion) {
        return asignacionDao.eliminarAsignacion(idAsignacion);
    }
}
