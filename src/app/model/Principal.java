package app.model;

import app.dao.CitaDAO;
import app.model.Cita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Principal extends JFrame {
    private CitaDAO citaDAO;
    private JTextField nombreField;
    private JTextField fechaField;
    private JTextField doctorField;
    private JTextArea citasArea;
    private List<Cita> citasActuales; // Lista para almacenar citas de la sesión actual
    private JLabel relojLabel; // JLabel para mostrar el reloj

    public Principal() {
        citaDAO = new CitaDAO();
        citasActuales = new ArrayList<>(); // Inicializar la lista
        initializeUI();
        iniciarReloj(); // Iniciar el reloj
    }

    private void initializeUI() {
        setTitle("Gestión de Citas Médicas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(235, 235, 235)); // Fondo gris claro

        // Panel para los campos de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.setBackground(new Color(255, 255, 255)); // Fondo blanco
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaciado alrededor

        inputPanel.add(new JLabel("Nombre del Paciente:"));
        nombreField = new JTextField();
        nombreField.setBorder(BorderFactory.createLineBorder(new Color(59, 89, 152), 1));
        inputPanel.add(nombreField);

        inputPanel.add(new JLabel("Fecha (YYYY-MM-DD):"));
        fechaField = new JTextField();
        fechaField.setBorder(BorderFactory.createLineBorder(new Color(59, 89, 152), 1));
        inputPanel.add(fechaField);

        inputPanel.add(new JLabel("Doctor:"));
        doctorField = new JTextField();
        doctorField.setBorder(BorderFactory.createLineBorder(new Color(59, 89, 152), 1));
        inputPanel.add(doctorField);

        JButton insertarButton = new JButton("Insertar Cita");
        insertarButton.setBackground(new Color(59, 89, 152));
        insertarButton.setForeground(Color.WHITE);
        insertarButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarCita();
            }
        });

        inputPanel.add(insertarButton);
        
        // Área de texto para mostrar las citas
        citasArea = new JTextArea();
        citasArea.setEditable(false);
        citasArea.setBorder(BorderFactory.createLineBorder(new Color(59, 89, 152), 1));

        // Botón para consultar citas
        JButton consultarButton = new JButton("Consultar Citas");
        consultarButton.setBackground(new Color(59, 89, 152));
        consultarButton.setForeground(Color.WHITE);
        consultarButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarCitas();
            }
        });

        inputPanel.add(consultarButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(citasArea), BorderLayout.CENTER);
        
        // Panel para el reloj
        JPanel relojPanel = new JPanel();
        relojPanel.setBackground(new Color(235, 235, 235));
        relojLabel = new JLabel();
        relojLabel.setFont(new Font("Arial", Font.BOLD, 14));
        relojPanel.add(relojLabel);

        add(relojPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void iniciarReloj() {
        // Timer para actualizar el reloj cada segundo
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarReloj();
            }
        });
        timer.start();
    }

    private void actualizarReloj() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaHoraActual = formatoFecha.format(new Date());
        relojLabel.setText(fechaHoraActual);
    }

    private void insertarCita() {
        String nombre = nombreField.getText();
        String fecha = fechaField.getText();
        String doctor = doctorField.getText();
        
        Cita cita = new Cita(0, nombre, fecha, doctor);
        citaDAO.insertarCita(cita);
        citasActuales.add(cita); // Agregar a la lista de citas actuales
        
        JOptionPane.showMessageDialog(this, "Cita insertada con éxito.");
        nombreField.setText("");
        fechaField.setText("");
        doctorField.setText("");
    }

    private void consultarCitas() {
        StringBuilder sb = new StringBuilder();
        for (Cita c : citasActuales) { // Solo mostrar citas de la sesión actual
            sb.append("Cita: ").append(c.getNombrePaciente()).append(" con ").append(c.getDoctor()).append("\n");
        }
        citasArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Principal());
    }
}
