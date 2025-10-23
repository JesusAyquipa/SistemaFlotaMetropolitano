/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.service;

import java.util.List;
import model.dao.RutaDao;
import model.entities.Ruta;

/**
 *
 * @author USUARIO
 */
public class RutaService {
    private RutaDao rutaDao = new RutaDao();

    public List<Ruta> obtenerTodas() {
        return rutaDao.listarRutas();
    }

    public boolean registrarRuta(Ruta ruta) {
        if (ruta.getNombre() == null || ruta.getNombre().isEmpty()) {
            System.out.println("⚠️ Error: el nombre de la ruta no puede estar vacío.");
            return false;
        }
        return rutaDao.insertarRuta(ruta);
    }

    public boolean actualizarRuta(Ruta ruta) {
        return rutaDao.actualizarRuta(ruta);
    }

    public boolean eliminarRuta(int idRuta) {
        return rutaDao.eliminarRuta(idRuta);
    }
}