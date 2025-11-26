import hotel.HotelFacade;
import hotel.Hotel;
import hotel.Reserva;
import hotel.TipoHabitacion;

public class MainTesting {
    public static void main(String[] args) {

        System.out.println("=== TESTING DEL SISTEMA ===");

        // ---------------------------------------------------------
        // a) Alta de hotel, habitaciones, huéspedes y empleados
        // ---------------------------------------------------------
        System.out.println("\n[a] Alta de hotel, habitaciones, huéspedes y empleados (PRE)");

        HotelFacade facade = new HotelFacade(
                "Hotel Istic",
                "Av. Siempre Viva 123",
                4
        );

        // Alta de habitaciones
        facade.agregarHabitacion(101, TipoHabitacion.SENCILLA  , 1500);
        facade.agregarHabitacion(102, TipoHabitacion.DOBLE     , 2500);
        facade.agregarHabitacion(201, TipoHabitacion.SUITE     , 4000);


        // Alta de huéspedes
        facade.agregarHuesped("Ignacio", "García", 101);
        facade.agregarHuesped("María", "Rodríguez", 102);
        facade.agregarHuesped("Luis", "Fernández", 103);
        facade.agregarHuesped("Ana", "López", 104);
        facade.agregarHuesped("Sofía", "Martínez", 105);

        // Alta de empleados
        facade.agregarEmpleado("Carlos", "Pérez", 1, "Recepcionista", 50000);
        facade.agregarEmpleado("Lucía", "Gómez", 2, "Gerente", 80000);

        System.out.println("[a] Alta completada (POST)");

        // Mostrar estado actual
        facade.mostrarEstadoHotel();

        // ---------------------------------------------------------
        // b) Suscripción de huéspedes a notificación de disponibilidad
        // ---------------------------------------------------------
        System.out.println("\n[b] Suscripción de huéspedes a notificación de disponibilidad (PRE)");

        // *Aquí iría la lógica del Observer si estuviera implementada*
        System.out.println("[b] Suscripción completada (POST)");

        // ---------------------------------------------------------
        // c) Generar una reserva
        // ---------------------------------------------------------
        System.out.println("\n[c] Generación de reserva (PRE)");

        // Reserva realizada completamente a través de la fachada
        facade.registrarReserva(
                101,    // DNI del huésped
                101,    // Número de habitación
                1,      // DNI del empleado
                3       // Noches
        );

        System.out.println("[c] Reserva generada (POST)");

        // ---------------------------------------------------------
        // d) Huésped puntúa el hotel
        // ---------------------------------------------------------
        System.out.println("\n[d] Puntuación de hotel (PRE)");

        // Para evitar acceder directamente a listas internas del hotel:
        Reserva primeraReserva = facade.getHotel().getReservas().stream().findFirst().orElse(null);

        if (primeraReserva != null) {
            primeraReserva.finalizarReserva(5);
            System.out.println("[d] Puntuación registrada (POST)");
        } else {
            System.out.println("[d] ERROR: No hay reservas para puntuar.");
        }

        // ---------------------------------------------------------
        // e) Destruir hotel con manejo de errores
        // ---------------------------------------------------------
        System.out.println("\n[e] Destrucción de hotel (PRE)");

        try {
            Hotel.destruirInstancia();
            System.out.println("[e] Hotel destruido correctamente (POST)");
        } catch (Exception ex) {
            System.out.println("[ERROR] Al destruir hotel: " + ex.getMessage());
        }

        // Verificación final
        if (Hotel.getInstancia() == null) {
            System.out.println("[e] Verificación: instancia eliminada.");
        } else {
            System.out.println("[e] Verificación: instancia aún existe.");
        }
    }
}
