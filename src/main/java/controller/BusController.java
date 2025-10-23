package controller;

import java.util.List;
import model.entities.Bus;
import model.service.BusService;

public class BusController {
    private BusService busService = new BusService();

    // Listar todos los buses
    public List<Bus> listarBuses() {
        return busService.obtenerTodos();
    }

    // Registrar nuevo bus
    public boolean registrarBus(String placa, String modelo, int kilometraje, String estado) {
        Bus bus = new Bus(0, placa, modelo, kilometraje, estado);
        return busService.registrarBus(bus);
    }

    // Actualizar bus existente
    public boolean actualizarBus(int idBus, String placa, String modelo, int kilometraje, String estado) {
        Bus bus = new Bus(idBus, placa, modelo, kilometraje, estado);
        return busService.actualizarBus(bus);
    }

    // Eliminar bus
    public boolean eliminarBus(int idBus) {
        return busService.eliminarBus(idBus);
    }

    // Buscar bus por placa
    public Bus buscarBusPorPlaca(String placa) {
        return busService.buscarPorPlaca(placa);
    }
}