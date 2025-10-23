/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Date;
import java.util.List;
import model.entities.Mantenimiento;
import model.service.MantenimientoService;

/**
 *
 * @author USUARIO
 */
public class MantenimientoController {
    private MantenimientoService mantenimientoService = new MantenimientoService();

    public List<Mantenimiento> listarMantenimientos() {
        return mantenimientoService.obtenerTodos();
    }

    public boolean registrarMantenimiento(int idBus, String tipo, Date fecha, int kilometraje, String descripcion) {
        Mantenimiento m = new Mantenimiento(0, idBus, tipo, fecha, kilometraje, descripcion);
        return mantenimientoService.registrarMantenimiento(m);
    }

    public boolean eliminarMantenimiento(int idMantenimiento) {
        return mantenimientoService.eliminarMantenimiento(idMantenimiento);
    }
}
