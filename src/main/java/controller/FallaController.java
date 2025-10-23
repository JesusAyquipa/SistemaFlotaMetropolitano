/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Date;
import java.util.List;
import model.entities.Falla;
import model.service.FallaService;

/**
 *
 * @author USUARIO
 */
public class FallaController {
    private FallaService fallaService = new FallaService();

    public List<Falla> listarFallas() {
        return fallaService.obtenerTodas();
    }

    public boolean registrarFalla(int idBus, String descripcion, Date fecha) {
        Falla f = new Falla(0, idBus, descripcion, fecha);
        return fallaService.registrarFalla(f);
    }

    public boolean eliminarFalla(int idFalla) {
        return fallaService.eliminarFalla(idFalla);
    }
}