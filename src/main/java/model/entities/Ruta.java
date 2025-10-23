/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

/**
 *
 * @author USUARIO
 */
public class Ruta {
    private int idRuta;
    private String nombre;
    private String origen;
    private String destino;

    public Ruta() {}

    public Ruta(int idRuta, String nombre, String origen, String destino) {
        this.idRuta = idRuta;
        this.nombre = nombre;
        this.origen = origen;
        this.destino = destino;
    }

    // Getters y Setters
    public int getIdRuta() { return idRuta; }
    public void setIdRuta(int idRuta) { this.idRuta = idRuta; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    @Override
    public String toString() {
        return nombre + " (" + origen + " - " + destino + ")";
    }
}
