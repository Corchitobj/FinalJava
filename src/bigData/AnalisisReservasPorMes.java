package bigData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hotel.TipoHabitacion;

public class AnalisisReservasPorMes {

    public static void main(String[] args) {

        String rutaArchivo = "reservas.txt";
         DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Map<TipoHabitacion, Integer> reservasPorTipo = new HashMap<>();
        Map<DayOfWeek, Integer> reservasPorDiaSemana = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");

                if (campos.length >= 4) {
                    // Día de la semana
                    String fechaTexto = campos[0].trim();
                    try {
                        LocalDate fecha = LocalDate.parse(fechaTexto, formatoFecha);
                        DayOfWeek diaSemana = fecha.getDayOfWeek();
                        reservasPorDiaSemana.put(diaSemana, reservasPorDiaSemana.getOrDefault(diaSemana, 0) + 1);
                    } catch (Exception e) {
                        System.out.println("Fecha inválida: " + fechaTexto);
                    }

                    // Tipo de habitación
                    String tipoTexto = campos[3].trim().toUpperCase();
                    try {
                        TipoHabitacion tipo = TipoHabitacion.valueOf(tipoTexto);
                        reservasPorTipo.put(tipo, reservasPorTipo.getOrDefault(tipo, 0) + 1);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo de habitación inválido: " + tipoTexto);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Ordenar tipos de habitación por cantidad descendente
        List<Map.Entry<TipoHabitacion, Integer>> tiposOrdenados = new ArrayList<>(reservasPorTipo.entrySet());
        tiposOrdenados.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Ordenar días de la semana por cantidad descendente
        List<Map.Entry<DayOfWeek, Integer>> diasOrdenados = new ArrayList<>(reservasPorDiaSemana.entrySet());
        diasOrdenados.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Mostrar resultados
        System.out.println("=== Tipos de habitaciones más contratadas ===");
        for (Map.Entry<TipoHabitacion, Integer> entry : tiposOrdenados) {
            System.out.println("Tipo: " + entry.getKey() + " " + entry.getValue() + " reservas");
        }

        System.out.println("\n=== Reservas por día de la semana ===");
        for (Map.Entry<DayOfWeek, Integer> entry : diasOrdenados) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " reservas");
        }

        // Día con más reservas
        if (!diasOrdenados.isEmpty()) {
            Map.Entry<DayOfWeek, Integer> top = diasOrdenados.get(0);
            System.out.println("Día con más reservas: " + top.getKey() + " (" + top.getValue() + " reservas)");
        }
    }
}
