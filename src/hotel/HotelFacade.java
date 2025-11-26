package hotel;

import persona.Empleado;
import persona.Huesped;

import java.time.LocalDate;

public class HotelFacade {
    private final Hotel hotel;

    public HotelFacade(String nombre, String direccion, int estrellas) {
        this.hotel = Hotel.getInstancia(nombre, direccion, estrellas); // Singleton
    }

    public Hotel getHotel() {
        return hotel;
    }

    // Agregar habitación usando Builder
    public void agregarHabitacion(int numero, TipoHabitacion tipo, double precio) {
        Habitacion h = new Habitacion.Builder()
            .conNumero(numero)
            .conTipo(tipo)
            .conPrecioPorNoche(precio)
            .build();

        hotel.agregarHabitacion(h);

        System.out.println("[OK] Habitación Nº " + numero + " agregada (Tipo: " + tipo + ", Precio: $" + precio + ")");
    }

    // Agregar empleado
    public void agregarEmpleado(String nombre, String apellido, double dni, String cargo, double salario) {
        Empleado e = new Empleado(nombre, apellido, dni, cargo, salario);
        hotel.agregarEmpleado(e);

        System.out.println("[OK] Empleado dado de alta: " + e);
    }

    // Alta de huésped
    public void agregarHuesped(String nombre, String apellido, double dni) {
        Huesped h = new Huesped(nombre, apellido, dni);
        hotel.agregarHuesped(h);

        System.out.println("[OK] Huésped dado de alta: " + h);
    }

    // Registrar reserva
    public void registrarReserva(double dniHuesped,
                                 int numeroHabitacion,
                                 double dniEmpleado,
                                 int noches) {

        Huesped huesped = hotel.buscarHuesped(dniHuesped);
        Habitacion habitacion = hotel.obtenerHabitacion(numeroHabitacion);
        Empleado empleado = hotel.obtenerEmpleadoPorDNI(dniEmpleado);

        if (huesped == null || habitacion == null || empleado == null) {
            System.out.println("[ERROR] Datos inválidos para la reserva.");
            return;
        }

        if (!habitacion.estaDisponible()) {
            System.out.println("[ERROR] La habitación está ocupada.");
            return;
        }

        try {
            // montoTotal = noches * precioPorNoche
            double montoTotal = noches * habitacion.getPrecioPorNoche();

            Reserva reserva = new Reserva(huesped,habitacion,empleado,noches,LocalDate.now(),montoTotal);

            hotel.agregarReserva(reserva);

            System.out.println("[OK] Reserva registrada:");
            System.out.println(" - Huésped: " + huesped);
            System.out.println(" - Habitación: " + habitacion);
            System.out.println(" - Noches: " + noches);
            System.out.println(" - Monto total: $" + montoTotal);
            System.out.println(" - Empleado que registró: " + empleado);

        } catch (IllegalStateException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // Mostrar estado general del hotel
    public void mostrarEstadoHotel() {                  
        hotel.mostrarEstadoHotel();
    }
}
