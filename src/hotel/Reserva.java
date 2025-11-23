package hotel;

import persona.Huesped;
import persona.Empleado;

import java.util.List;
import java.util.Optional;

public class Reserva {
    private Huesped huesped;
    private Habitacion habitacion;
    private Empleado empleadoRecepcion;

    public Reserva(Huesped huesped, Habitacion habitacion, Empleado empleadoRecepcion) {
        if (!habitacion.estaDisponible()) {
            throw new IllegalStateException("La habitación ya está ocupada.");
        }
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.empleadoRecepcion = empleadoRecepcion;

        // Asociar huésped a la habitación
        habitacion.asignarHuesped(huesped);
        // Agregar reserva al huésped
        huesped.agregarReserva(this);
    }

    public Huesped getHuesped() { return huesped; }
    public Habitacion getHabitacion() { return habitacion; }
    public Empleado getEmpleadoRecepcion() { return empleadoRecepcion; }

    // Finalizar reserva: libera habitación y registra puntuación
    public void finalizarReserva(int puntuacion) {
        habitacion.liberarHabitacion();
        habitacion.puntuarHabitacion(puntuacion);

        System.out.println("[OK] Reserva finalizada:");
        System.out.println(" - Huésped: " + huesped);
        System.out.println(" - Habitación liberada: " + habitacion.getNumero() + " (" + habitacion.getTipo() + ")");
        System.out.println(" - Puntaje asignado: " + puntuacion + " estrellas");
        System.out.println(" - Empleado que gestionó: " + empleadoRecepcion);
    }

    // Mostrar resumen completo
    public void mostrarResumen() {
        System.out.println("=== Resumen de Reserva ===");
        System.out.println("Huésped: " + huesped);
        System.out.println("Habitación: " + habitacion);
        System.out.println("Empleado: " + empleadoRecepcion);
        System.out.println("Disponible: " + habitacion.estaDisponible());
        System.out.printf("Promedio de puntuación: %.2f estrellas%n", habitacion.getPromedioPuntuacion());
    }

    // Buscar reserva por huésped
    public Optional<Reserva> buscarReservaPorHuesped(Huesped huesped, List<Reserva> reservas) {
        return reservas.stream()
                .filter(reserva -> reserva.getHuesped().equals(huesped))
                .findFirst();
    }

    @Override
    public String toString() {
        return "Reserva -> Huésped: " + huesped +
               " | Habitación: " + habitacion.getNumero() + " (" + habitacion.getTipo() + ")" +
               " | Empleado: " + empleadoRecepcion;
    }
}