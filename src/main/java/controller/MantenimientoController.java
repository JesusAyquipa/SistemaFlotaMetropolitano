import java.util.ArrayList;
import java.util.List;
import model.dao.MantenimientoDao;
import model.entities.Mantenimiento;

public class MantenimientoController {
    private MantenimientoDao mantenimientoDao;

    public MantenimientoController() {
        this.mantenimientoDao = new MantenimientoDao();
    }

    public List<Mantenimiento> listarMantenimientos() {
        return mantenimientoDao.listarMantenimientos();
    }

    public boolean registrarMantenimiento(Mantenimiento mantenimiento) {
        return mantenimientoDao.insertarMantenimiento(mantenimiento);
    }

    public boolean eliminarMantenimiento(int idMantenimiento) {
        return mantenimientoDao.eliminarMantenimiento(idMantenimiento);
    }

    public List<Mantenimiento> buscarMantenimientosPorBus(int idBus) {
        List<Mantenimiento> todos = mantenimientoDao.listarMantenimientos();
        List<Mantenimiento> filtrados = new ArrayList<>();
        
        for (Mantenimiento m : todos) {
            if (m.getIdBus() == idBus) {
                filtrados.add(m);
            }
        }
        return filtrados;
    }

    public List<String> generarAlertas() {
        List<String> alertas = new ArrayList<>();
        List<Mantenimiento> mantenimientos = mantenimientoDao.listarMantenimientos();
        
        // Aquí puedes personalizar las reglas de alertas según tus necesidades
        // Ejemplo: alerta para buses con más de 10,000 km sin mantenimiento preventivo
        
        // Esta es una implementación básica - puedes mejorarla según tus criterios
        for (Mantenimiento m : mantenimientos) {
            if (m.getKilometraje() > 10000 && "Preventivo".equals(m.getTipo())) {
                alertas.add("Bus ID " + m.getIdBus() + " requiere mantenimiento preventivo - " + 
                           m.getKilometraje() + " km");
            }
        }
        
        return alertas;
    }
}