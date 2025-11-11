package hotel;

import persona.Empleado;
import persona.Huesped;

public class HotelFacade {
    private Hotel hotel;

    public HotelFacade(String nombre, String direccion, int estrellas) {
        this.hotel = Hotel.getInstancia(nombre, direccion, estrellas); // Singleton
    }

    // ✅ Agregar habitación usando Builder
    public void agregarHabitacion(int numero, String tipo, double precio) {
        Habitacion h = new Habitacion.Builder()
            .conNumero(numero)
            .conTipo(tipo)
            .conPrecioPorNoche(precio)
            .build();
        hotel.agregarHabitacion(h);
    }

    // ✅ Agregar empleado
    public void agregarEmpleado(String nombre, String apellido, String dni, String cargo, double salario) {
        Empleado e = new Empleado(nombre, apellido, dni, cargo, salario);
        hotel.agregarEmpleado(e);
    }

    // ✅ Registrar reserva
    public void registrarReserva(String nombreHuesped, String apellido, String dniHuesped, int numeroHabitacion, String dniEmpleado) {
        Huesped huesped = new Huesped(nombreHuesped, apellido, dniHuesped);
        Habitacion habitacion = hotel.obtenerHabitacion(numeroHabitacion);
        Empleado empleado = hotel.obtenerEmpleadoPorDNI(dniEmpleado);

        if (habitacion != null && empleado != null) {
            Reserva reserva = new Reserva(huesped, habitacion, empleado);
            hotel.agregarReserva(reserva);
        } else {
            System.out.println("Error: habitación o empleado no encontrados.");
        }
    }

    // ✅ Mostrar estado general del hotel
    public void mostrarEstadoHotel() {
        hotel.mostrarDatos();

        System.out.println("\nHabitaciones:");
        for (Habitacion h : hotel.getHabitaciones()) {
            h.mostrarDatos();
        }

        System.out.println("\nEmpleados:");
        for (Empleado e : hotel.getEmpleados()) {
            System.out.println(" - " + e.getDescripcion());
        }

        System.out.println("\nReservas:");
        for (Reserva r : hotel.getReservas()) {
            System.out.println(" - Huésped: " + r.getHuesped().getNombre() +
                               " | Habitación Nº " + r.getHabitacion().getNumero() +
                               " | Gestionada por: " + r.getEmpleadoRecepcion().getNombre());
        }
    }

}