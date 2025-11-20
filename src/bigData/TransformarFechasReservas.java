package bigData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TransformarFechasReservas {
    public static void main(String[] args) {
        String archivoOrigen = "reservas.csv";
        String archivoDestino = "reservas_limpias.csv";

        DateTimeFormatter formatoOriginal = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoTransformado = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (
            BufferedReader reader = new BufferedReader(new FileReader(archivoOrigen));
            FileWriter writer = new FileWriter(archivoDestino)
        ) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = reader.readLine()) != null) {
                if (primeraLinea) {
                    writer.write(linea + "\n"); // copiar encabezado
                    primeraLinea = false;
                    continue;
                }

                String[] campos = linea.split(",");

                if (campos.length >= 1) {
                    String fechaOriginal = campos[0].trim();
                    try {
                        LocalDate fecha = LocalDate.parse(fechaOriginal, formatoOriginal);
                        campos[0] = fecha.format(formatoTransformado);
                    } catch (Exception e) {
                        System.out.println("Fecha inválida: " + fechaOriginal);
                    }

                    // reconstruir línea transformada
                    String nuevaLinea = String.join(",", campos);
                    writer.write(nuevaLinea + "\n");
                }
            }

            System.out.println("Archivo transformado correctamente: reservas_limpias.csv");

        } catch (IOException e) {
            System.out.println("Error en el proceso ETL: " + e.getMessage());
        }
    }
}

