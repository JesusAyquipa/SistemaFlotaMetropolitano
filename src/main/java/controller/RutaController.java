/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.entities.Ruta;
import model.service.RutaService;

/**
 *
 * @author USUARIO
 */
public class RutaController {
    private RutaService rutaService = new RutaService();

    public List<Ruta> listarRutas() {
        return rutaService.obtenerTodas();
    }

    public boolean registrarRuta(String nombre, String origen, String destino) {
        Ruta ruta = new Ruta(0, nombre, origen, destino);
        return rutaService.registrarRuta(ruta);
    }

    public boolean actualizarRuta(int idRuta, String nombre, String origen, String destino) {
        Ruta ruta = new Ruta(idRuta, nombre, origen, destino);
        return rutaService.actualizarRuta(ruta);
    }

    public boolean eliminarRuta(int idRuta) {
        return rutaService.eliminarRuta(idRuta);
    }
}