package app.model;


import app.dao.CitaDAO;
import app.model.Cita;

import java.util.List;

public class Principal {
    public static void main(String[] args) {
        CitaDAO citaDAO = new CitaDAO();

        // Crear una nueva cita
        Cita cita = new Cita(0, "Juan Pérez", "2024-10-20", "Dra. Ramírez");
        citaDAO.insertarCita(cita);

        // Consultar todas las citas
        List<Cita> citas = citaDAO.obtenerCitas();
        for (Cita c : citas) {
            System.out.println("Cita: " + c.getNombrePaciente() + " con " + c.getDoctor());
        }

        // Actualizar una cita
        cita.setDoctor("Dr. López");
        citaDAO.actualizarCita(cita);

        // Eliminar una cita
        citaDAO.eliminarCita(cita.getId());
    }
}