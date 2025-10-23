
package model.service;

import java.util.List;
import model.dao.BusDao;
import model.entities.Bus;


public class BusService {
    
    private BusDao busDao = new BusDao();

    public List<Bus> obtenerTodos() {
        return busDao.listarBuses();
    }

    public boolean registrarBus(Bus bus) {
        if (bus.getPlaca() == null || bus.getPlaca().isEmpty()) {
            System.out.println("⚠️ Error: la placa no puede estar vacía.");
            return false;
        }
        return busDao.insertarBus(bus);
    }

    public boolean actualizarBus(Bus bus) {
        return busDao.actualizarBus(bus);
    }

    public boolean eliminarBus(int idBus) {
        return busDao.eliminarBus(idBus);
    }

    public Bus buscarPorPlaca(String placa) {
        return busDao.buscarPorPlaca(placa);
    }
}
