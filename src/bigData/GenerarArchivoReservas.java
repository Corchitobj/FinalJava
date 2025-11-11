package bigData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarArchivoReservas {
    public static void main(String[] args) {
        String rutaTxt = "reservas.txt";
        String rutaCsv = "reservas.csv";

        String[] tiposHabitacion = { "SENCILLA", "DOBLE", "SUITE" };
        String[] nombres = { "Juan", "Ana", "Carlos", "Lucia", "Pedro", "Sofia", "Diego", "Laura", "Marcos",
                "Valentina" };
        String[] apellidos = { "Perez", "Gomez", "Lopez", "Diaz", "Fernandez", "Torres", "Ruiz", "Sosa", "Mendez",
                "Herrera" };
        Random random = new Random();

        try (
                FileWriter writerTxt = new FileWriter(rutaTxt);
                FileWriter writerCsv = new FileWriter(rutaCsv)) {
            // Encabezado CSV
            writerCsv.write("Fecha,NumeroReserva,Huesped,TipoHabitacion,Empleado,Noches,Total,Calidad\n");

            for (int i = 1; i <= 5000; i++) {
                // Fecha aleatoria
                int mes = 1 + random.nextInt(12);
                int dia = 1 + random.nextInt(28);
                String fecha = String.format("2025-%02d-%02d", mes, dia);

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
                int precioPorNoche = 0;
                switch (tipo) {
                    case "SENCILLA":
                        precioPorNoche = 10000;
                        break;
                    case "DOBLE":
                        precioPorNoche = 18000;
                        break;
                    case "SUITE":
                        precioPorNoche = 30000;
                        break;
                    default:
                        precioPorNoche = 0;
                        break;
                }

                int total = precioPorNoche * noches;

                // Calidad según total
                String calidad;
                if (total < 100000)
                    calidad = "BAJA";
                else if (total < 200000)
                    calidad = "MEDIA";
                else
                    calidad = "ALTA";

                // Línea común
                String linea = fecha + ", " + numeroReserva + ", " + huesped + ", " + tipo + ", Miguel Torcuato, "
                        + noches + ", " + total + ", " + calidad + "\n";

                // Escribir en ambos archivos
                writerTxt.write(linea);
                writerCsv.write(linea);
            }

            System.out.println("Archivos generados correctamente: reservas.txt y reservas.csv");

        } catch (IOException e) {
            System.out.println("Error al generar los archivos: " + e.getMessage());
        }
    }

}
