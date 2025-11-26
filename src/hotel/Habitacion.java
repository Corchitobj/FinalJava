package hotel;

import persona.Huesped;

public class Habitacion {
    private int numero;
    private TipoHabitacion tipo;
    private double precioPorNoche;
    private int totalPuntuaciones = 0;
    private int cantidadPuntuaciones = 0;
    private boolean disponible = true;
    private Huesped huespedActual; // huésped alojado

    // Constructor privado: solo accesible desde el Builder
    private Habitacion(Builder builder) {
        this.numero = builder.numero;
        this.tipo = builder.tipo;
        this.precioPorNoche = builder.precioPorNoche;
    }

    // Getters
    public int getNumero() { return numero; }
    public TipoHabitacion getTipo() { return tipo; }
    public double getPrecioPorNoche() { return precioPorNoche; }
    public boolean estaDisponible() { return disponible; }
    public Huesped getHuespedActual() { return huespedActual; }

    // Setters opcionales
    public void setNumero(int numero) { this.numero = numero; }
    public void setTipo(TipoHabitacion tipo) { this.tipo = tipo; }
    public void setPrecioPorNoche(double precioPorNoche) { this.precioPorNoche = precioPorNoche; }

    // Alta cohesión: mostrar datos
    public void mostrarDatos() {
        System.out.println("Habitación Nº " + numero +
                " | Tipo: " + tipo +
                " | Precio: $" + precioPorNoche +
                " | Disponible: " + disponible +
                (huespedActual != null ? " | Ocupada por: " + huespedActual.getNombre() : ""));
    }

    // ===============================
    // Clase Builder interna
    // ===============================
    public static class Builder {
        private int numero;
        private TipoHabitacion tipo;
        private double precioPorNoche;

        public Builder conNumero(int numero) { this.numero = numero; return this; }
        public Builder conTipo(TipoHabitacion tipo) { this.tipo = tipo; return this; }
        public Builder conPrecioPorNoche(double precioPorNoche) { this.precioPorNoche = precioPorNoche; return this; }

        public Habitacion build() { return new Habitacion(this); }
    }

    // Métodos para puntuar habitación
    public void puntuarHabitacion(int puntaje) {
        if (puntaje < 1 || puntaje > 5) {
            throw new IllegalArgumentException("El puntaje debe estar entre 1 y 5 estrellas.");
        }
        totalPuntuaciones += puntaje;
        cantidadPuntuaciones++;

        System.out.println("[OK] Puntuación registrada:");
        System.out.println(" - Habitación Nº " + numero);
        System.out.println(" - Tipo: " + tipo);
        System.out.println(" - Puntaje asignado: " + puntaje + " estrellas");
        System.out.println(" - Promedio actual: " + getPromedioPuntuacion() + " estrellas");
    }

    public double getPromedioPuntuacion() {
        if (cantidadPuntuaciones == 0) return 0;
        return (double) totalPuntuaciones / cantidadPuntuaciones;
    }

    // Método para asignar huésped
    public void asignarHuesped(Huesped huesped) {
        if (!disponible) {
            throw new IllegalStateException("La habitación ya está ocupada.");
        }
        this.huespedActual = huesped;
        this.disponible = false;
    }

    // Método para liberar habitación
    public void liberarHabitacion() {
        this.huespedActual = null;
        this.disponible = true;
    }

    @Override
    public String toString() {
        return "Habitación Nº " + numero +
               " | Tipo: " + tipo +
               " | Precio: $" + precioPorNoche +
               " | Disponible: " + disponible +
               (huespedActual != null ? " | Ocupada por: " + huespedActual.getNombre() : "");
    }
}
