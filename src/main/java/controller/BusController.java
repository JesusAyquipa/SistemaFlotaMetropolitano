package controller;

import java.util.List;
import model.dao.BusDao;
import model.entities.Bus;
import model.service.BusService;

public class BusController {
    private BusService busService = new BusService();
    private BusDao dao = new BusDao();

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

    public Bus buscarBusPorId(int idBus) {
        // Implementación para buscar bus por ID
        List<Bus> buses = listarBuses();
        for (Bus bus : buses) {
            if (bus.getIdBus() == idBus) {
                return bus;
            }
        }
        return null;
    }


}