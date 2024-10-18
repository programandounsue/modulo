package app.dao;

import app.model.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/clinica";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "123456789";

    // Método para obtener la conexión
    private Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
    }

    // Método para insertar una nueva cita
    public void insertarCita(Cita cita) {
        String sql = "INSERT INTO citas (nombrePaciente, fechaCita, doctor) VALUES (?, ?, ?)";

        try (Connection conexion = obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, cita.getNombrePaciente());
            pstmt.setString(2, cita.getFechaCita());
            pstmt.setString(3, cita.getDoctor());

            pstmt.executeUpdate();
            System.out.println("¡Cita insertada exitosamente!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar todas las citas
    public List<Cita> obtenerCitas() {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas";

        try (Connection conexion = obtenerConexion();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombrePaciente = rs.getString("nombrePaciente");
                String fechaCita = rs.getString("fechaCita");
                String doctor = rs.getString("doctor");

                citas.add(new Cita(id, nombrePaciente, fechaCita, doctor));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return citas;
    }

    // Método para actualizar una cita
    public void actualizarCita(Cita cita) {
        String sql = "UPDATE citas SET nombrePaciente = ?, fechaCita = ?, doctor = ? WHERE id = ?";

        try (Connection conexion = obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, cita.getNombrePaciente());
            pstmt.setString(2, cita.getFechaCita());
            pstmt.setString(3, cita.getDoctor());
            pstmt.setInt(4, cita.getId());

            pstmt.executeUpdate();
            System.out.println("¡Cita actualizada exitosamente!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar una cita
    public void eliminarCita(int id) {
        String sql = "DELETE FROM citas WHERE id = ?";

        try (Connection conexion = obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("¡Cita eliminada exitosamente!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
