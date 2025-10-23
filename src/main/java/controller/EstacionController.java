/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.entities.Estacion;
import model.service.EstacionService;

/**
 *
 * @author USUARIO
 */
public class EstacionController {
    private EstacionService estacionService = new EstacionService();

    public List<Estacion> listarEstaciones() {
        return estacionService.obtenerTodas();
    }

    public boolean registrarEstacion(String nombre, String distrito, int capacidad) {
        Estacion e = new Estacion(0, nombre, distrito, capacidad);
        return estacionService.registrarEstacion(e);
    }

    public boolean actualizarEstacion(int idEstacion, String nombre, String distrito, int capacidad) {
        Estacion e = new Estacion(idEstacion, nombre, distrito, capacidad);
        return estacionService.actualizarEstacion(e);
    }

    public boolean eliminarEstacion(int idEstacion) {
        return estacionService.eliminarEstacion(idEstacion);
    }
}