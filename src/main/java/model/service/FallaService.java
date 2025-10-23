/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.service;

import java.util.List;
import model.dao.FallaDao;
import model.entities.Falla;

/**
 *
 * @author USUARIO
 */
public class FallaService {
    private FallaDao fallaDao = new FallaDao();

    public List<Falla> obtenerTodas() {
        return fallaDao.listarFallas();
    }

    public boolean registrarFalla(Falla falla) {
        if (falla.getDescripcion() == null || falla.getDescripcion().isEmpty()) {
            System.out.println("⚠️ Error: la descripción no puede estar vacía.");
            return false;
        }
        return fallaDao.insertarFalla(falla);
    }

    public boolean eliminarFalla(int idFalla) {
        return fallaDao.eliminarFalla(idFalla);
    }
}
