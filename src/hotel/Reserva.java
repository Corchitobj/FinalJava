package hotel;
import persona.Huesped;
import java.util.List;
import java.util.Optional;
import persona.Empleado;

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

    public void agregarReserva(Reserva reserva) {
        // Lógica para agregar la reserva (por ejemplo, guardarla en una lista)
        System.out.println("Reserva agregada para el huésped: " + reserva.getHuesped().getNombre() + " " + reserva.getHuesped().getApellido());
    }

    // Elimina una reserva
    public void eliminarReserva(Reserva reserva) {
        // Lógica para eliminar la reserva (por ejemplo, eliminarla de una lista)
        System.out.println("Reserva eliminada para el huésped: " + reserva.getHuesped().getNombre() + " " + reserva.getHuesped().getApellido());
    }

    //Buscar una reserva por huésped
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
