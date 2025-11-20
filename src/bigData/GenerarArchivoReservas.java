package bigData;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerarArchivoReservas {
    public static void main(String[] args) {
        String rutaTxt = "reservas.txt";
        String rutaCsv = "reservas.csv";

        String[] tiposHabitacion = { "SENCILLA", "DOBLE", "SUITE" };
        String[] nombres = { "Juan", "Ana", "Carlos", "Lucia", "Pedro", "Sofia", "Diego", "Laura", "Marcos", "Valentina" };
        String[] apellidos = { "Perez", "Gomez", "Lopez", "Diaz", "Fernandez", "Torres", "Ruiz", "Sosa", "Mendez", "Herrera" };
        Random random = new Random();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Rango de fechas: 1 de enero a 31 de diciembre de 2025
        LocalDate inicio = LocalDate.of(2025, 1, 1);
        int diasDelAño = inicio.lengthOfYear();

        try (
            FileWriter writerTxt = new FileWriter(rutaTxt);
            FileWriter writerCsv = new FileWriter(rutaCsv)
        ) {
            writerCsv.write("Fecha,NumeroReserva,Huesped,TipoHabitacion,Empleado,Noches,Total,Calidad\n");

            for (int i = 1; i <= 5000; i++) {
                // Fecha aleatoria dentro de 2025
                LocalDate fecha = inicio.plusDays(random.nextInt(diasDelAño));
                String fechaFormateada = fecha.format(formatter);

                // Huesped
                String nombre = nombres[random.nextInt(nombres.length)];
                String apellido = apellidos[random.nextInt(apellidos.length)];
                int dni = 10000000 + random.nextInt(40000000);
                String huesped = nombre + " " + apellido + " DNI " + dni;

                // Tipo de habitación
                String tipo = tiposHabitacion[random.nextInt(tiposHabitacion.length)];

                // Número de reserva
                int numeroReserva = 1000 + i;

                // Noches
                int noches = 1 + random.nextInt(15);

                // Precio por noche
                int precioPorNoche = switch (tipo) {
                    case "SENCILLA" -> 10000;
                    case "DOBLE" -> 18000;
                    case "SUITE" -> 30000;
                    default -> 0;
                };

                int total = precioPorNoche * noches;

                // Calidad
                String calidad = (total < 100000) ? "BAJA" : (total < 200000) ? "MEDIA" : "ALTA";

                // Línea
                String linea = fechaFormateada + ", " + numeroReserva + ", " + huesped + ", " + tipo + ", Miguel Torcuato, "
                        + noches + ", " + total + ", " + calidad + "\n";

                writerTxt.write(linea);
                writerCsv.write(linea);
            }

            System.out.println("Archivos generados correctamente: reservas.txt y reservas.csv");

        } catch (IOException e) {
            System.out.println("Error al generar los archivos: " + e.getMessage());
        }
    }
}