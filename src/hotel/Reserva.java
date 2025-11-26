package hotel;

import persona.Huesped;
import persona.Empleado;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Reserva {

    private static int contadorReservas = 1;

    private String numeroReserva;
    private Huesped huesped;
    private Habitacion habitacion;
    private Empleado empleadoRecepcion;
    private int noches;
    private LocalDate fechaInicio;
    private double montoTotal;

    // Constructor completo según UML
    public Reserva(Huesped huesped, Habitacion habitacion, Empleado empleadoRecepcion,
                   int noches, LocalDate fechaInicio, double montoTotal) {

        if (!habitacion.estaDisponible()) {
            throw new IllegalStateException("La habitación ya está ocupada.");
        }

        this.numeroReserva = "RES-" + contadorReservas++;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.empleadoRecepcion = empleadoRecepcion;
        this.noches = noches;
        this.fechaInicio = fechaInicio;
        this.montoTotal = montoTotal; 

        // Calcular monto total automáticamente
        this.montoTotal = habitacion.getPrecioPorNoche() * noches;

        // Enlazar objetos (como ya hacías)
        habitacion.asignarHuesped(huesped);
        huesped.agregarReserva(this);
    }

    // Getters y setters según UML

    public String getNumeroReserva() { return numeroReserva; }

    public Huesped getHuesped() { return huesped; }
    public void setHuesped(Huesped huesped) { this.huesped = huesped; }

    public Habitacion getHabitacion() { return habitacion; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }

    public Empleado getEmpleadoRecepcion() { return empleadoRecepcion; }
    public void setEmpleadoRecepcion(Empleado empleado) { this.empleadoRecepcion = empleado; }

    public int getNoches() { return noches; }
    public void setNoches(int noches) { 
        this.noches = noches; 
        this.montoTotal = habitacion.getPrecioPorNoche() * noches; // recalcular
    }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public double getMontoTotal() { return montoTotal; }

    // Métodos que ya existían en tu código

    public void finalizarReserva(int puntuacion) {
        habitacion.liberarHabitacion();
        habitacion.puntuarHabitacion(puntuacion);

        System.out.println("[OK] Reserva finalizada:");
        System.out.println(" - Número: " + numeroReserva);
        System.out.println(" - Huésped: " + huesped);
        System.out.println(" - Habitación liberada: " + habitacion.getNumero() + " (" + habitacion.getTipo() + ")");
        System.out.println(" - Puntaje asignado: " + puntuacion + " estrellas");
        System.out.println(" - Empleado que gestionó: " + empleadoRecepcion);
    }

    public void mostrarResumen() {
        System.out.println("=== Resumen de Reserva ===");
        System.out.println("Número: " + numeroReserva);
        System.out.println("Huésped: " + huesped);
        System.out.println("Habitación: " + habitacion);
        System.out.println("Empleado: " + empleadoRecepcion);
        System.out.println("Fecha inicio: " + fechaInicio);
        System.out.println("Noches: " + noches);
        System.out.println("Monto total: $" + montoTotal);
        System.out.println("Disponible: " + habitacion.estaDisponible());
        System.out.printf("Promedio de puntuación: %.2f estrellas%n", habitacion.getPromedioPuntuacion());
    }

    public Optional<Reserva> findReservaByHuesped(Huesped h, List<Reserva> reservas) {
        return reservas.stream()
                .filter(reserva -> reserva.getHuesped().equals(h))
                .findFirst();
    }

    @Override
    public String toString() {
        return "Reserva #" + numeroReserva +
                " | Huésped: " + huesped +
                " | Habitación: " + habitacion.getNumero() + " (" + habitacion.getTipo() + ")" +
                " | Noches: " + noches +
                " | Total: $" + montoTotal;
    }
}

