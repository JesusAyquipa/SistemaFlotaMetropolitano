package model.entities;

public class Bus {
    private int idBus;
    private String placa;
    private String modelo;
    private int kilometraje;
    private String estado;

    public Bus() {
    }
    
    public Bus(int idBus, String placa, String modelo, int kilometraje, String estado) {
        this.idBus = idBus;
        this.placa = placa;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.estado = estado;
    }

    public Bus(String placa, String modelo, int kilometraje, String estado) {
        this.placa = placa;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.estado = estado;
    }

    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Bus{" + "idBus=" + idBus + ", placa=" + placa + ", modelo=" + modelo + ", kilometraje=" + kilometraje + ", estado=" + estado + '}';
    }
    
}
