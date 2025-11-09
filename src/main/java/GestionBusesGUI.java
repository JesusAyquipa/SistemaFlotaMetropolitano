import controller.BusController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.entities.Bus;
import java.io.File;
import java.awt.Desktop;
import javax.swing.JFileChooser;

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
        JButton btnGenerarReportes = new JButton("Generar Reportes PDF");

        panelBotonesForm.add(btnRegistrar);
        panelBotonesForm.add(btnActualizar);
        panelBotonesForm.add(btnEliminar);
        panelBotonesForm.add(btnLimpiar);
        panelBotonesForm.add(btnBuscar);
        panelBotonesForm.add(btnGenerarReportes);

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
        btnGenerarReportes.addActionListener(e -> mostrarOpcionesReportes());

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

    private void mostrarOpcionesReportes() {
        String[] opciones = {
            "Reporte de Estado de Flota",
            "Reporte de Mantenimientos", 
            "Reporte de Asignaciones Diarias",
            "Cancelar"
        };
        
        int seleccion = JOptionPane.showOptionDialog(
            this,
            "Seleccione el tipo de reporte a generar:",
            "Generar Reportes PDF",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        switch (seleccion) {
            case 0:
                generarReporteFlota();
                break;
            case 1:
                generarReporteMantenimientos();
                break;
            case 2:
                generarReporteAsignaciones();
                break;
            case 3:
                // Cancelar - no hacer nada
                break;
            default:
                // No selection
                break;
        }
    }

    private void generarReporteFlota() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte de Flota");
            fileChooser.setSelectedFile(new File("Reporte_Flota_Metropolitano.pdf"));
            
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".pdf")) {
                    filePath += ".pdf";
                }
                
                List<Bus> buses = busController.listarBuses();
                boolean exito = PDFGenerator.generarReporteFlota(buses, filePath);
                
                if (exito) {
                    JOptionPane.showMessageDialog(this, 
                        "Reporte de flota generado exitosamente:\n" + filePath,
                        "Reporte Generado", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Preguntar si desea abrir el PDF
                    int abrir = JOptionPane.showConfirmDialog(this,
                        "¿Desea abrir el reporte generado?",
                        "Abrir Reporte", JOptionPane.YES_NO_OPTION);
                    
                    if (abrir == JOptionPane.YES_OPTION) {
                        Desktop.getDesktop().open(new File(filePath));
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Error al generar el reporte",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generarReporteMantenimientos() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte de Mantenimientos");
            fileChooser.setSelectedFile(new File("Reporte_Mantenimientos_Metropolitano.pdf"));
            
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".pdf")) {
                    filePath += ".pdf";
                }
                
                List<Bus> buses = busController.listarBuses();
                
                // Generar reporte básico de mantenimientos
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                com.itextpdf.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(filePath));
                document.open();
                
                com.itextpdf.text.Font titleFont = com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA_BOLD, 18, com.itextpdf.text.BaseColor.RED);
                com.itextpdf.text.Paragraph title = new com.itextpdf.text.Paragraph("REPORTE DE MANTENIMIENTOS - METROPOLITANO", titleFont);
                title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                title.setSpacingAfter(20);
                document.add(title);
                
                // Fecha de generación
                com.itextpdf.text.Font dateFont = com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA, 10, com.itextpdf.text.BaseColor.GRAY);
                com.itextpdf.text.Paragraph fecha = new com.itextpdf.text.Paragraph("Generado el: " + new java.util.Date(), dateFont);
                fecha.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                fecha.setSpacingAfter(20);
                document.add(fecha);
                
                // Alertas de mantenimiento
                com.itextpdf.text.Font alertFont = com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA_BOLD, 12, com.itextpdf.text.BaseColor.RED);
                com.itextpdf.text.Paragraph alertas = new com.itextpdf.text.Paragraph("ALERTAS DE MANTENIMIENTO PENDIENTE:", alertFont);
                alertas.setSpacingAfter(10);
                document.add(alertas);
                
                // Buses que requieren mantenimiento (más de 10,000 km)
                com.itextpdf.text.Font normalFont = com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA, 10);
                int alertasCount = 0;
                for (Bus bus : buses) {
                    if (bus.getKilometraje() > 10000) {
                        com.itextpdf.text.Paragraph alerta = new com.itextpdf.text.Paragraph(
                            "• Bus " + bus.getPlaca() + " (" + bus.getModelo() + 
                            ") tiene " + bus.getKilometraje() + " km - Requiere mantenimiento preventivo", 
                            normalFont
                        );
                        document.add(alerta);
                        alertasCount++;
                    }
                }
                
                if (alertasCount == 0) {
                    document.add(new com.itextpdf.text.Paragraph("No hay alertas de mantenimiento pendientes.", normalFont));
                }
                
                document.close();
                
                JOptionPane.showMessageDialog(this, 
                    "Reporte de mantenimientos generado exitosamente:\n" + filePath,
                    "Reporte Generado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generarReporteAsignaciones() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte de Asignaciones");
            fileChooser.setSelectedFile(new File("Reporte_Asignaciones_Metropolitano.pdf"));
            
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".pdf")) {
                    filePath += ".pdf";
                }
                
                List<Bus> buses = busController.listarBuses();
                
                // Generar reporte básico de asignaciones
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                com.itextpdf.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(filePath));
                document.open();
                
                com.itextpdf.text.Font titleFont = com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA_BOLD, 18, com.itextpdf.text.BaseColor.GREEN);
                com.itextpdf.text.Paragraph title = new com.itextpdf.text.Paragraph("REPORTE DE ASIGNACIONES - METROPOLITANO", titleFont);
                title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                title.setSpacingAfter(20);
                document.add(title);
                
                // Fecha de generación
                com.itextpdf.text.Font dateFont = com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA, 10, com.itextpdf.text.BaseColor.GRAY);
                com.itextpdf.text.Paragraph fecha = new com.itextpdf.text.Paragraph("Generado el: " + new java.util.Date(), dateFont);
                fecha.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                fecha.setSpacingAfter(20);
                document.add(fecha);
                
                // Resumen de buses disponibles
                com.itextpdf.text.Font normalFont = com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA, 12);
                int busesDisponibles = 0;
                for (Bus bus : buses) {
                    if ("DISPONIBLE".equalsIgnoreCase(bus.getEstado())) {
                        busesDisponibles++;
                    }
                }
                
                com.itextpdf.text.Paragraph resumen = new com.itextpdf.text.Paragraph();
                resumen.add(new com.itextpdf.text.Chunk("RESUMEN DE FLOTA:\n\n", com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA_BOLD, 14)));
                resumen.add(new com.itextpdf.text.Chunk("• Total de buses: " + buses.size() + "\n", normalFont));
                resumen.add(new com.itextpdf.text.Chunk("• Buses disponibles: " + busesDisponibles + "\n", normalFont));
                resumen.add(new com.itextpdf.text.Chunk("• Buses no disponibles: " + (buses.size() - busesDisponibles) + "\n\n", normalFont));
                resumen.add(new com.itextpdf.text.Chunk("Nota: Este módulo mostrará las asignaciones de rutas cuando esté implementado completamente.", normalFont));
                document.add(resumen);
                
                document.close();
                
                JOptionPane.showMessageDialog(this, 
                    "Reporte de asignaciones generado exitosamente:\n" + filePath,
                    "Reporte Generado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Versión simplificada sin LookAndFeel
        SwingUtilities.invokeLater(() -> {
            new GestionBusesGUI().setVisible(true);
        });
    }
}