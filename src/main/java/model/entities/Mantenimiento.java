/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.sql.Date;

/**
 *
 * @author USUARIO
 */
public class Mantenimiento {
    private int idMantenimiento;
    private int idBus;
    private String tipo;
    private Date fecha;
    private int kilometraje;
    private String descripcion;

    public Mantenimiento() {}

    public Mantenimiento(int idMantenimiento, int idBus, String tipo, Date fecha, int kilometraje, String descripcion) {
        this.idMantenimiento = idMantenimiento;
        this.idBus = idBus;
        this.tipo = tipo;
        this.fecha = fecha;
        this.kilometraje = kilometraje;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdMantenimiento() { return idMantenimiento; }
    public void setIdMantenimiento(int idMantenimiento) { this.idMantenimiento = idMantenimiento; }

    public int getIdBus() { return idBus; }
    public void setIdBus(int idBus) { this.idBus = idBus; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public int getKilometraje() { return kilometraje; }
    public void setKilometraje(int kilometraje) { this.kilometraje = kilometraje; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return tipo + " - " + fecha;
    }
}
