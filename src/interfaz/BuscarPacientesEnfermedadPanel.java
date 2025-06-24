package interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import logica.EnfermoNacional;
import logica.Minsap;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuscarPacientesEnfermedadPanel extends JPanel {
    private JComboBox<String> comboEnfermedades;
    private JTable tablaPacientes;
    private DefaultTableModel modeloTabla;
    private Minsap minsap;

    public BuscarPacientesEnfermedadPanel(Minsap minsap) {
        this.minsap = minsap;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(240, 240, 240));

        // Panel superior con combo box
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setBackground(new Color(240, 240, 240));
        
        JLabel lblEnfermedad = new JLabel("Seleccione enfermedad:");
        lblEnfermedad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        comboEnfermedades = new JComboBox<>();
        comboEnfermedades.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboEnfermedades.setPreferredSize(new Dimension(250, 30));
        
        cargarEnfermedades();
        
        comboEnfermedades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPacientes();
            }
        });
        
        panelSuperior.add(lblEnfermedad);
        panelSuperior.add(comboEnfermedades);
        
        // Configurar tabla
        String[] columnas = {"C�digo", "Nombre y Apellidos", "Edad", "Direcci�n"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaPacientes = new JTable(modeloTabla);
        tablaPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaPacientes.setRowHeight(30);
        
        // Personalizar encabezados
        tablaPacientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaPacientes.getTableHeader().setBackground(new Color(70, 130, 180));
        tablaPacientes.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(tablaPacientes);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createLineBorder(new Color(200, 200, 200))));
        
        // Panel de t�tulo
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(new Color(240, 240, 240));
        JLabel titleLabel = new JLabel("PACIENTES NACIONALES POR ENFERMEDAD");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(70, 130, 180));
        titlePanel.add(titleLabel);
        
        add(titlePanel, BorderLayout.NORTH);
        add(panelSuperior, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void cargarEnfermedades() {
        comboEnfermedades.removeAllItems();
        comboEnfermedades.addItem("Seleccione...");
        
        for (logica.Enfermedad enfermedad : minsap.getAllEnfermedades()) {
            comboEnfermedades.addItem(enfermedad.getNombreComun());
        }
    }

    private void cargarPacientes() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        
        String enfermedadSeleccionada = (String) comboEnfermedades.getSelectedItem();
        if (enfermedadSeleccionada != null && !enfermedadSeleccionada.equals("Seleccione...")) {
            List<EnfermoNacional> pacientes = minsap.getPacientesNacionalesConEnfermedad(enfermedadSeleccionada);
            
            for (EnfermoNacional paciente : pacientes) {
                Object[] fila = {
                    paciente.getCodigo(),
                    paciente.getNombre(),
                    paciente.getEdad(),
                    paciente.getDireccion()
                };
                modeloTabla.addRow(fila);
            }
        }
    }
}