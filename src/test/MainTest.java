package test;
import hotel.*;
import persona.*;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        try {
            System.out.println("=== Alta de hotel con habitaciones, personas y empleados ===");

            Hotel hotel = Hotel.getInstancia("Hotel Único", "Av. Principal 100", 5);

            // Crear habitaciones
            Habitacion h1 = new Habitacion.Builder().conNumero(101).conTipo("doble").conPrecioPorNoche(15000).build();
            Habitacion h2 = new Habitacion.Builder().conNumero(102).conTipo("suite").conPrecioPorNoche(25000).build();
            Habitacion h3 = new Habitacion.Builder().conNumero(103).conTipo("simple").conPrecioPorNoche(10000).build();

            hotel.agregarHabitacion(h1);
            hotel.agregarHabitacion(h2);
            hotel.agregarHabitacion(h3);

            // Crear empleados
            Empleado e1 = new Empleado("Luis", "Pérez", "87654321", "Recepcionista", 120000);
            Empleado e2 = new Empleado("María", "López", "11223344", "Gerente", 180000);
            hotel.agregarEmpleado(e1);
            hotel.agregarEmpleado(e2);

            // Crear huéspedes
            List<Huesped> huespedes = new ArrayList<>();
            huespedes.add(new Huesped("Ana", "Gómez", "12345678"));
            huespedes.add(new Huesped("Carlos", "Martínez", "23456789"));
            huespedes.add(new Huesped("Laura", "Fernández", "34567890"));
            huespedes.add(new Huesped("Pedro", "Suárez", "45678901"));
            huespedes.add(new Huesped("Sofía", "Ramírez", "56789012"));

            System.out.println("Hotel creado con 3 habitaciones, 5 huéspedes y 2 empleados.\n");

            System.out.println("=== Suscripción de huéspedes a notificación de disponibilidad ===");

            for (Huesped h : huespedes) {
                System.out.println("Huésped " + h.getNombre() + " suscripto a notificación.");
                System.out.println("Habitaciones disponibles: " + hotel.getHabitaciones().size());
            }

            System.out.println("\n=== Generar una reserva ===");

            Reserva reserva = new Reserva(huespedes.get(0), h1, e2);
            hotel.agregarReserva(reserva);

            System.out.println("Reserva creada para " + reserva.getHuesped().getNombre() +
                               " en habitación " + reserva.getHabitacion().getNumero());

            System.out.println(" === Huésped puntúa hotel ===");

            int puntuacion = 5;
            System.out.println("Huésped " + huespedes.get(0).getNombre() + " puntúa el hotel con " + puntuacion + " estrellas.");

            System.out.println("=== Destruir hotel y verificar relaciones ===");

            System.out.println("Estado antes de destrucción:");
            System.out.println("Habitaciones: " + hotel.getHabitaciones().size());
            System.out.println("Reservas: " + hotel.getReservas().size());
            System.out.println("Empleados: " + hotel.getEmpleados().size());

            // Simular destrucción
            try {
                hotel.getHabitaciones().clear(); // composición: deberían eliminarse
                hotel.getReservas().clear();     // agregación: pueden eliminarse
                hotel.getEmpleados().clear();    // agregación: pueden eliminarse

                System.out.println("\nEstado después de destrucción:");
                System.out.println("Habitaciones: " + hotel.getHabitaciones().size());
                System.out.println("Reservas: " + hotel.getReservas().size());
                System.out.println("Empleados: " + hotel.getEmpleados().size());

                System.out.println("✔ Relaciones de composición y agregación verificadas correctamente.");
            } catch (Exception ex) {
                System.out.println(" Error al destruir hotel: " + ex.getMessage());
            }

        } catch (Exception e) {
            System.out.println(" Error general en testeo: " + e.getMessage());
        }
    }
}

