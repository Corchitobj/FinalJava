package persona;

import hotel.Reserva;
import java.util.List;
import java.util.ArrayList;

public class Huesped extends Persona {
    private List<Reserva> reservas = new ArrayList<>();

    public Huesped(String nombre, String apellido, double dni) {
        super(nombre, apellido, dni);
    }

    private boolean suscripto = false;

    public boolean isSuscripto() { return suscripto; }
    public void setSuscripto(boolean suscripto) { this.suscripto = suscripto; }
    
    // Getters y Setters
    public List<Reserva> getReservas() { return reservas; }
    public void setReservas(List<Reserva> reservas) { this.reservas = reservas; }

    // Métodos para gestionar reservas
    public void agregarReserva(Reserva reserva) {
        if (reserva != null && !reservas.contains(reserva)) {
            reservas.add(reserva);
        }
    }

    public boolean eliminarReserva(Reserva reserva) {
        return reservas.remove(reserva);
    }

    public List<Reserva> obtenerReservasPorNumeroHabitacion(int numeroHabitacion) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getHabitacion().getNumero() == numeroHabitacion) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    // Descripción legible
    @Override
    public String getDescripcion() {
        return "Huésped: " + getNombre() + " " + getApellido() +
               " - DNI: " + getDni() +
               " - Reservas activas: " + reservas.size();
    }

    @Override
    public String toString() {
        return getDescripcion();
    }
}