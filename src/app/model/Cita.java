package app.model;

public class Cita {
    private int id;
    private String nombrePaciente;
    private String fechaCita;
    private String doctor;

    public Cita(int id, String nombrePaciente, String fechaCita, String doctor) {
        this.id = id;
        this.nombrePaciente = nombrePaciente;
        this.fechaCita = fechaCita;
        this.doctor = doctor;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
