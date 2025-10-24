import controller.BusController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.entities.Bus;

public class GestionBusesGUI extends JFrame {
    private JTable tablaBuses;
    private DefaultTableModel modeloTabla;
    private JTextField txtPlaca, txtModelo, txtKilometraje, txtEstado;
    private JButton btnRegistrar, btnActualizar, btnEliminar, btnLimpiar, btnBuscar;
    private BusController busController;
    private JLabel lblIdBus;
    private int idBusActual = -1;

    public GestionBusesGUI() {
        busController = new BusController();
        initComponents();
        cargarBuses();
        setTitle("Gestión del Metropolitano - Buses");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de título
        JPanel panelTitulo = new JPanel();
        JLabel titulo = new JLabel("GESTIÓN DEL METROPOLITANO");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panelTitulo.add(titulo);
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Bus"));

        // ID Bus (oculto para el usuario, pero necesario para actualizar)
        JPanel panelId = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelId.add(new JLabel("ID Bus:"));
        lblIdBus = new JLabel("-");
        panelId.add(lblIdBus);
        panelFormulario.add(panelId);
        panelFormulario.add(new JLabel()); // Espacio vacío

        panelFormulario.add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        panelFormulario.add(txtPlaca);

        panelFormulario.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        panelFormulario.add(txtModelo);

        panelFormulario.add(new JLabel("Kilometraje:"));
        txtKilometraje = new JTextField();
        panelFormulario.add(txtKilometraje);

        panelFormulario.add(new JLabel("Estado:"));
        txtEstado = new JTextField();
        panelFormulario.add(txtEstado);

        // Panel de botones del formulario
        JPanel panelBotonesForm = new JPanel(new FlowLayout());
        btnRegistrar = new JButton("Registrar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnBuscar = new JButton("Buscar por Placa");

        panelBotonesForm.add(btnRegistrar);
        panelBotonesForm.add(btnActualizar);
        panelBotonesForm.add(btnEliminar);
        panelBotonesForm.add(btnLimpiar);
        panelBotonesForm.add(btnBuscar);

        // Deshabilitar botones inicialmente
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);

        // Panel que contiene formulario y botones
        JPanel panelDatos = new JPanel(new BorderLayout());
        panelDatos.add(panelFormulario, BorderLayout.CENTER);
        panelDatos.add(panelBotonesForm, BorderLayout.SOUTH);

        // Tabla de buses
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable directamente
            }
        };
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Placa");
        modeloTabla.addColumn("Modelo");
        modeloTabla.addColumn("Kilometraje");
        modeloTabla.addColumn("Estado");

        tablaBuses = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaBuses);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Lista de Buses"));

        // Agregar componentes al panel principal
        panelPrincipal.add(panelDatos, BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);

        // Agregar listeners
        agregarListeners();

        add(panelPrincipal);
    }

    private void agregarListeners() {
        // Listener para seleccionar fila de la tabla
        tablaBuses.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaBuses.getSelectedRow() != -1) {
                int filaSeleccionada = tablaBuses.getSelectedRow();
                mostrarDatosBus(filaSeleccionada);
            }
        });

        // Listener para Registrar
        btnRegistrar.addActionListener(e -> registrarBus());

        // Listener para Actualizar
        btnActualizar.addActionListener(e -> actualizarBus());

        // Listener para Eliminar
        btnEliminar.addActionListener(e -> eliminarBus());

        // Listener para Limpiar
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        // Listener para Buscar
        btnBuscar.addActionListener(e -> buscarPorPlaca());
    }

    private void cargarBuses() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);
        
        // Obtener buses de la base de datos
        List<Bus> buses = busController.listarBuses();
        
        // Llenar tabla
        for (Bus bus : buses) {
            modeloTabla.addRow(new Object[]{
                bus.getIdBus(),
                bus.getPlaca(),
                bus.getModelo(),
                bus.getKilometraje(),
                bus.getEstado()
            });
        }
    }

    private void mostrarDatosBus(int fila) {
        try {
            int id = (int) modeloTabla.getValueAt(fila, 0);
            String placa = (String) modeloTabla.getValueAt(fila, 1);
            String modelo = (String) modeloTabla.getValueAt(fila, 2);
            int kilometraje = (int) modeloTabla.getValueAt(fila, 3);
            String estado = (String) modeloTabla.getValueAt(fila, 4);

            idBusActual = id;
            lblIdBus.setText(String.valueOf(id));
            txtPlaca.setText(placa);
            txtModelo.setText(modelo);
            txtKilometraje.setText(String.valueOf(kilometraje));
            txtEstado.setText(estado);

            // Habilitar botones de actualizar y eliminar
            btnActualizar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnRegistrar.setEnabled(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos del bus: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarBus() {
        if (validarCampos()) {
            try {
                String placa = txtPlaca.getText().trim();
                String modelo = txtModelo.getText().trim();
                int kilometraje = Integer.parseInt(txtKilometraje.getText().trim());
                String estado = txtEstado.getText().trim();

                boolean exito = busController.registrarBus(placa, modelo, kilometraje, estado);
                
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Bus registrado exitosamente");
                    cargarBuses();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar el bus", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El kilometraje debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarBus() {
        if (validarCampos() && idBusActual != -1) {
            try {
                String placa = txtPlaca.getText().trim();
                String modelo = txtModelo.getText().trim();
                int kilometraje = Integer.parseInt(txtKilometraje.getText().trim());
                String estado = txtEstado.getText().trim();

                boolean exito = busController.actualizarBus(idBusActual, placa, modelo, kilometraje, estado);
                
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Bus actualizado exitosamente");
                    cargarBuses();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el bus", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El kilometraje debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarBus() {
        if (idBusActual != -1) {
            int confirmacion = JOptionPane.showConfirmDialog(
                this, 
                "¿Está seguro de eliminar este bus?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = busController.eliminarBus(idBusActual);
                
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Bus eliminado exitosamente");
                    cargarBuses();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el bus", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void buscarPorPlaca() {
        String placa = JOptionPane.showInputDialog(this, "Ingrese la placa a buscar:");
        if (placa != null && !placa.trim().isEmpty()) {
            Bus bus = busController.buscarBusPorPlaca(placa.trim());
            if (bus != null) {
                // Limpiar tabla y mostrar solo el bus encontrado
                modeloTabla.setRowCount(0);
                modeloTabla.addRow(new Object[]{
                    bus.getIdBus(),
                    bus.getPlaca(),
                    bus.getModelo(),
                    bus.getKilometraje(),
                    bus.getEstado()
                });
                
                // Seleccionar y mostrar el bus encontrado
                tablaBuses.setRowSelectionInterval(0, 0);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún bus con la placa: " + placa);
                cargarBuses(); // Recargar todos los buses
            }
        }
    }

    private void limpiarFormulario() {
        idBusActual = -1;
        lblIdBus.setText("-");
        txtPlaca.setText("");
        txtModelo.setText("");
        txtKilometraje.setText("");
        txtEstado.setText("");
        
        // Restablecer botones
        btnRegistrar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        // Deseleccionar fila de la tabla
        tablaBuses.clearSelection();
    }

    private boolean validarCampos() {
        if (txtPlaca.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La placa es obligatoria", "Error", JOptionPane.ERROR_MESSAGE);
            txtPlaca.requestFocus();
            return false;
        }
        if (txtModelo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El modelo es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            txtModelo.requestFocus();
            return false;
        }
        if (txtKilometraje.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El kilometraje es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            txtKilometraje.requestFocus();
            return false;
        }
        try {
            Integer.parseInt(txtKilometraje.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El kilometraje debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            txtKilometraje.requestFocus();
            return false;
        }
        if (txtEstado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El estado es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            txtEstado.requestFocus();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Versión simplificada sin LookAndFeel
        SwingUtilities.invokeLater(() -> {
            new GestionBusesGUI().setVisible(true);
        });
    }
}