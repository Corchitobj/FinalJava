package hotel;

import persona.Empleado;
import persona.Huesped;

public class HotelFacade {
    private final Hotel hotel;

    public HotelFacade(String nombre, String direccion, int estrellas) {
        this.hotel = Hotel.getInstancia(nombre, direccion, estrellas); // Singleton
    }

    public Hotel getHotel() {
        return hotel;
    }

    // Agregar habitación usando Builder
    public void agregarHabitacion(int numero, String tipo, double precio) {
        Habitacion h = new Habitacion.Builder()
            .conNumero(numero)
            .conTipo(tipo)
            .conPrecioPorNoche(precio)
            .build();
        hotel.agregarHabitacion(h);
        System.out.println("[OK] Habitación Nº " + numero + " agregada (Tipo: " + tipo + ", Precio: $" + precio + ")");
    }

    // Agregar empleado
    public void agregarEmpleado(String nombre, String apellido, String dni, String cargo, double salario) {
        Empleado e = new Empleado(nombre, apellido, dni, cargo, salario);
        hotel.agregarEmpleado(e);
        System.out.println("[OK] Empleado dado de alta: " + e);
    }

    // Alta de huésped
    public void agregarHuesped(String nombre, String apellido, String dni) {
        Huesped h = new Huesped(nombre, apellido, dni);
        hotel.agregarHuesped(h);
        System.out.println("[OK] Huésped dado de alta: " + h);
    }

    // Registrar reserva con validación de disponibilidad
    public void registrarReserva(String nombre, String apellido, String dni, int numeroHabitacion, String dniEmpleado) {
        Huesped huesped = hotel.buscarHuesped(dni);
        Habitacion habitacion = hotel.obtenerHabitacion(numeroHabitacion);
        Empleado empleado = hotel.obtenerEmpleadoPorDNI(dniEmpleado); // 🔹 corregido

        if (huesped == null || habitacion == null || empleado == null) {
            System.out.println("[ERROR] Datos inválidos para la reserva.");
            return;
        }

        try {
            Reserva reserva = new Reserva(huesped, habitacion, empleado);
            hotel.agregarReserva(reserva);

            System.out.println("[OK] Reserva registrada:");
            System.out.println(" - Huésped: " + huesped);
            System.out.println(" - Habitación: " + habitacion);
            System.out.println(" - Empleado que registró: " + empleado);
        } catch (IllegalStateException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // Mostrar estado general del hotel
    public void mostrarEstadoHotel() {
        hotel.mostrarEstadoHotel(); // 🔹 ya imprime habitaciones, empleados, huéspedes y reservas
    }
}