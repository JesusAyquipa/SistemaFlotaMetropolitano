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
public class Asignacion {
    private int idAsignacion;
    private int idBus;
    private int idRuta;
    private Date fecha;

    public Asignacion() {}

    public Asignacion(int idAsignacion, int idBus, int idRuta, Date fecha) {
        this.idAsignacion = idAsignacion;
        this.idBus = idBus;
        this.idRuta = idRuta;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getIdAsignacion() { return idAsignacion; }
    public void setIdAsignacion(int idAsignacion) { this.idAsignacion = idAsignacion; }

    public int getIdBus() { return idBus; }
    public void setIdBus(int idBus) { this.idBus = idBus; }

    public int getIdRuta() { return idRuta; }
    public void setIdRuta(int idRuta) { this.idRuta = idRuta; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    @Override
    public String toString() {
        return "Bus " + idBus + " → Ruta " + idRuta + " (" + fecha + ")";
    }
}
