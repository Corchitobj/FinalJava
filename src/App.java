import hotel.*;
import persona.*;
import Observer.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("=== Alta de hotel con habitaciones, personas y empleados ===");

            Hotel hotel = Hotel.getInstancia("Hotel Único", "Av. Principal 100", 5);
            System.out.println("Hotel creado: " + hotel.getNombre() + ", Dirección: " + hotel.getDireccion() + ", Estrellas: " + hotel.getEstrellas());

            // Crear habitaciones
            Habitacion h1 = new Habitacion.Builder().conNumero(101).conTipo("doble").conPrecioPorNoche(15000).build();
            Habitacion h2 = new Habitacion.Builder().conNumero(102).conTipo("suite").conPrecioPorNoche(25000).build();
            Habitacion h3 = new Habitacion.Builder().conNumero(103).conTipo("simple").conPrecioPorNoche(10000).build();

            hotel.agregarHabitacion(h1);
            hotel.agregarHabitacion(h2);
            hotel.agregarHabitacion(h3);

            // Crear empleados
            List<Empleado> empleados = new ArrayList<>();
            Empleado e1 = new Empleado("Luis", "Pérez", "87654321", "Recepcionista", 120000);
            Empleado e2 = new Empleado("María", "López", "11223344", "Gerente", 180000);
            hotel.agregarEmpleado(e1);
            hotel.agregarEmpleado(e2);
            empleados.add(e1);
            empleados.add(e2);

            // Crear huéspedes
            List<Huesped> huespedes = new ArrayList<>();
            huespedes.add(new Huesped("Ana", "Gómez", "12345678"));
            huespedes.add(new Huesped("Carlos", "Martínez", "23456789"));
            huespedes.add(new Huesped("Laura", "Fernández", "34567890"));
            huespedes.add(new Huesped("Pedro", "Suárez", "45678901"));
            huespedes.add(new Huesped("Sofía", "Ramírez", "56789012"));

            System.out.println("Hotel creado con 3 habitaciones, 5 huéspedes y 2 empleados.\n");

            System.out.println("Se crearon los siguientes huéspedes:");
            for (Huesped h : huespedes) {
                System.out.println("- " + h.getNombre() + " " + h.getApellido() + " DNI: " + h.getDni());
            }

            System.out.println("Se crearon los siguientes empleados:");
            for (Empleado e : empleados) {
                System.out.println("- " + e.getNombre() + " " + e.getApellido() + " Cargo: " + e.getCargo());
            }

            System.out.println("\n=== Suscripción de huéspedes a notificación de disponibilidad ===");
            for (Huesped h : huespedes) {
                System.out.println("Huésped " + h.getNombre() + " suscripto a notificación.");
                System.out.println("Habitaciones disponibles: " + hotel.getHabitaciones().size());
            }

            System.out.println("\n=== Generar una reserva ===");
            Reserva reserva = new Reserva(huespedes.get(0), h1, e2);
            huespedes.get(0).agregarReserva(reserva);
            hotel.agregarReserva(reserva);

            System.out.println("Reserva creada para " + reserva.getHuesped().getNombre() +
                    " en habitación " + reserva.getHabitacion().getNumero());

            System.out.println("\n=== Finalizar reserva: liberar y puntuar habitación ===");
            int puntuacion = 5;
            reserva.finalizarReserva(puntuacion);
            reserva.mostrarResumen();

            System.out.println("\n=== Demostración del patrón Observer ===");
            GestorReservas gestor = new GestorReservas();

            gestor.agregarObserver(disponibles ->
                    System.out.println("Observador general: quedan " + disponibles + " habitaciones"));

            gestor.agregarUltimaDisponibilidadObserver(() ->
                    System.out.println("Observador especial: ¡solo queda una habitación!"));

            for (int i = 0; i < 10; i++) {
                gestor.crearReserva();
            }

            System.out.println("\n=== Estado antes de destrucción ===");
            System.out.println("Habitaciones: " + hotel.getHabitaciones().size());
            System.out.println("Reservas: " + hotel.getReservas().size());
            System.out.println("Empleados: " + hotel.getEmpleados().size());

            System.out.println("\n=== Destruir hotel y verificar relaciones ===");
            Hotel.destruirInstancia();
            System.out.println("Destrucción simulada correctamente.");

            System.out.println("\n=== Estado post-destrucción ===");
            System.out.println("Habitaciones: " + hotel.getHabitaciones().size());
            System.out.println("Reservas: " + hotel.getReservas().size());
            System.out.println("Empleados: " + hotel.getEmpleados().size());

            Hotel nuevoHotel = Hotel.getInstancia("Nuevo Hotel", "Calle Falsa 123", 3);
            if (nuevoHotel != hotel) {
                System.out.println("\nSe creó una nueva instancia de hotel tras destrucción.");
                System.out.println("Nuevo hotel: " + nuevoHotel.getNombre() + ", Dirección: " + nuevoHotel.getDireccion() + ", Estrellas: " + nuevoHotel.getEstrellas());
            } else {
                System.out.println("\nLa instancia anterior no fue destruida correctamente.");
            }

            System.out.println("\nRelaciones de composición y agregación verificadas correctamente.");

        } catch (Exception e) {
            System.out.println("Error general en ejecución: " + e.getMessage());
        }
    }
}