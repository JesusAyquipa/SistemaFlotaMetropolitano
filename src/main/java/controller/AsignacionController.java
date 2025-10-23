/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Date;
import java.util.List;
import model.entities.Asignacion;
import model.service.AsignacionService;

/**
 *
 * @author USUARIO
 */
public class AsignacionController {
    private AsignacionService asignacionService = new AsignacionService();

    public List<Asignacion> listarAsignaciones() {
        return asignacionService.obtenerTodas();
    }

    public boolean registrarAsignacion(int idBus, int idRuta, Date fecha) {
        Asignacion a = new Asignacion(0, idBus, idRuta, fecha);
        return asignacionService.registrarAsignacion(a);
    }

    public boolean eliminarAsignacion(int idAsignacion) {
        return asignacionService.eliminarAsignacion(idAsignacion);
    }
}
