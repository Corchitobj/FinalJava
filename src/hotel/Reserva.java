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
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.empleadoRecepcion = empleadoRecepcion;
    }

    public Huesped getHuesped() { return huesped; }
    public void setHuesped(Huesped huesped) { this.huesped = huesped; }

    public Habitacion getHabitacion() { return habitacion; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }

    public Empleado getEmpleadoRecepcion() { return empleadoRecepcion; }
    public void setEmpleadoRecepcion(Empleado empleadoRecepcion) { this.empleadoRecepcion = empleadoRecepcion; }

    // Finalizar reserva: libera habitación y registra puntuación
    public void finalizarReserva(int puntuacion) {
        habitacion.liberarHabitacion();
        habitacion.puntuarHabitacion(puntuacion);
        System.out.println("Reserva finalizada. Habitación liberada y puntuada con " + puntuacion + " estrellas.");
    }

    // Mostrar resumen completo de la reserva
    public void mostrarResumen() {
        System.out.println("=== Resumen de Reserva ===");
        System.out.println("Huésped: " + huesped.getNombre() + " " + huesped.getApellido());
        System.out.println("Habitación Nº " + habitacion.getNumero() + " | Tipo: " + habitacion.getTipo());
        System.out.println("Empleado: " + empleadoRecepcion.getNombre() + " " + empleadoRecepcion.getApellido());
        System.out.println("Disponible: " + habitacion.estaDisponible());
        System.out.printf("Promedio de puntuación: %.2f estrellas%n", habitacion.getPromedioPuntuacion());
    }

    // Agregar reserva (simulado)
    public void agregarReserva(Reserva reserva) {
        System.out.println("Reserva agregada para el huésped: " + reserva.getHuesped().getNombre() + " " + reserva.getHuesped().getApellido());
    }

    // Eliminar reserva (simulado)
    public void eliminarReserva(Reserva reserva) {
        System.out.println("Reserva eliminada para el huésped: " + reserva.getHuesped().getNombre() + " " + reserva.getHuesped().getApellido());
    }

    // Buscar reserva por huésped
    public Optional<Reserva> buscarReservaPorHuesped(Huesped huesped, List<Reserva> reservas) {
        return reservas.stream()
                .filter(reserva -> reserva.getHuesped().equals(huesped))
                .findFirst();
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "huesped=" + huesped.getNombre() + " " + huesped.getApellido() +
                ", habitacion=" + habitacion.getNumero() +
                ", empleadoRecepcion=" + empleadoRecepcion.getNombre() + " " + empleadoRecepcion.getApellido() +
                '}';
    }
}
