/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

/**
 *
 * @author USUARIO
 */
public class Estacion {
    private int idEstacion;
    private String nombre;
    private String distrito;
    private int capacidad;

    public Estacion() {}

    public Estacion(int idEstacion, String nombre, String distrito, int capacidad) {
        this.idEstacion = idEstacion;
        this.nombre = nombre;
        this.distrito = distrito;
        this.capacidad = capacidad;
    }

    // Getters y Setters
    public int getIdEstacion() { return idEstacion; }
    public void setIdEstacion(int idEstacion) { this.idEstacion = idEstacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    @Override
    public String toString() {
        return nombre + " (" + distrito + ")";
    }
}
