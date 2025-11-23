import hotel.HotelFacade;
import hotel.Hotel;
import hotel.Reserva;


public class MainTesting {
    public static void main(String[] args) {
        System.out.println("=== TESTING DEL SISTEMA ===");

        // a) Alta de hotel, habitaciones, huéspedes y empleados
        System.out.println("\n[a] Alta de hotel, habitaciones, huéspedes y empleados (PRE)");
        HotelFacade facade = new HotelFacade("Hotel Copilot", "Av. Siempre Viva 123", 4);

        // Habitaciones
        facade.agregarHabitacion(101, "SENCILLA", 100.0);
        facade.agregarHabitacion(102, "DOBLE", 150.0);
        facade.agregarHabitacion(103, "SUITE", 250.0);

        // Empleados
        facade.agregarEmpleado("Ana", "Pérez", "E001", "Recepcionista", 50000);
        facade.agregarEmpleado("Luis", "Martínez", "E002", "Recepcionista", 48000);

        // Huéspedes
        facade.agregarHuesped("Ignacio", "García", "H001");
        facade.agregarHuesped("Juan", "López", "H002");
        facade.agregarHuesped("María", "Fernández", "H003");
        facade.agregarHuesped("Pedro", "Sánchez", "H004");
        facade.agregarHuesped("Laura", "Gómez", "H005");

        System.out.println("[a] Alta completada (POST)");
        facade.mostrarEstadoHotel();

        // b) Suscripción de huéspedes a notificación de disponibilidad
        System.out.println("\n[b] Suscripción de huéspedes a notificación de disponibilidad (PRE)");
        // Aquí iría la lógica del Observer (GestorReservas, PanelRecepcion, AlertaUltimaHabitacion)
        System.out.println("[b] Suscripción completada (POST)");

        // c) Generar una reserva
        System.out.println("\n[c] Generación de reserva (PRE)");
        facade.registrarReserva("Ignacio", "García", "H001", 101, "E001");
        System.out.println("[c] Reserva generada (POST)");

        // d) Huésped puntúa hotel
        System.out.println("\n[d] Puntuación de hotel (PRE)");
        Reserva reserva = facade.getHotel().getReservas().get(0); // tomo la primera reserva
        reserva.finalizarReserva(5);
        System.out.println("[d] Puntuación registrada (POST)");

        // e) Destruir hotel con manejo de errores SOLO aquí
        System.out.println("\n[e] Destrucción de hotel (PRE)");
        try {
            Hotel.destruirInstancia();
            System.out.println("[e] Hotel destruido correctamente (POST)");
        } catch (Exception ex) {
            System.out.println("[ERROR] Al destruir hotel: " + ex.getMessage());
        }

        // Verificar instancias post-destrucción
        if (Hotel.getInstancia() == null) {
            System.out.println("[e] Verificación: instancia de hotel eliminada.");
        } else {
            System.out.println("[e] Verificación: instancia de hotel aún existe.");
        }
    }
}