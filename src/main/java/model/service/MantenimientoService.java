/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.service;

import java.util.List;
import model.dao.MantenimientoDao;
import model.entities.Mantenimiento;

/**
 *
 * @author USUARIO
 */
public class MantenimientoService {
    private MantenimientoDao mantenimientoDao = new MantenimientoDao();

    public List<Mantenimiento> obtenerTodos() {
        return mantenimientoDao.listarMantenimientos();
    }

    public boolean registrarMantenimiento(Mantenimiento mantenimiento) {
        if (mantenimiento.getIdBus() <= 0) {
            System.out.println("⚠️ Error: se debe indicar un bus válido.");
            return false;
        }
        return mantenimientoDao.insertarMantenimiento(mantenimiento);
    }

    public boolean eliminarMantenimiento(int idMantenimiento) {
        return mantenimientoDao.eliminarMantenimiento(idMantenimiento);
    }
}