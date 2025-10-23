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
public class Falla {
    private int idFalla;
    private int idBus;
    private String descripcion;
    private Date fecha;

    public Falla() {}

    public Falla(int idFalla, int idBus, String descripcion, Date fecha) {
        this.idFalla = idFalla;
        this.idBus = idBus;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getIdFalla() { return idFalla; }
    public void setIdFalla(int idFalla) { this.idFalla = idFalla; }

    public int getIdBus() { return idBus; }
    public void setIdBus(int idBus) { this.idBus = idBus; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    @Override
    public String toString() {
        return descripcion + " (" + fecha + ")";
    }
}
