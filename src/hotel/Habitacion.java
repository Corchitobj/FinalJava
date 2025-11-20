package hotel;
public class Habitacion {
    private int numero;
    private String tipo;
    private double precioPorNoche;
    private int totalPuntuaciones = 0;
    private int cantidadPuntuaciones = 0;
    private boolean disponible = true;

    // Constructor privado: solo accesible desde el Builder
    private Habitacion(Builder builder) {
        this.numero = builder.numero;
        this.tipo = builder.tipo;
        this.precioPorNoche = builder.precioPorNoche;
    }

    // Getters
    public int getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public double getPrecioPorNoche() { return precioPorNoche; }
    public boolean estaDisponible() { return disponible; }

    // Setters opcionales (si querés permitir cambios post-construcción)
    public void setNumero(int numero) { this.numero = numero; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setPrecioPorNoche(double precioPorNoche) { this.precioPorNoche = precioPorNoche; }


    //Alta cohesión: método para mostrar datos de la habitación
    public void mostrarDatos() {
        System.out.println("Habitación Nº " + numero + " | Tipo: " + tipo + " | Precio: $" + precioPorNoche);
    }

    // ===============================
    // Clase Builder interna
    // ===============================
    public static class Builder {
        private int numero;
        private String tipo;
        private double precioPorNoche;

        public Builder conNumero(int numero) {
            this.numero = numero;
            return this;
        }

        public Builder conTipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder conPrecioPorNoche(double precioPorNoche) {
            this.precioPorNoche = precioPorNoche;
            return this;
        }

        public Habitacion build() {
            return new Habitacion(this);
        }
    }

    // Métodos para puntuar habitación

    public void puntuar(int estrellas) {
        if (estrellas < 1 || estrellas > 5) {
        throw new IllegalArgumentException("La puntuación debe estar entre 1 y 5 estrellas.");
        }
        totalPuntuaciones += estrellas;
        cantidadPuntuaciones++;
    }

    public double getPromedioPuntuacion() {
        if (cantidadPuntuaciones == 0) return 0;
        return (double) totalPuntuaciones / cantidadPuntuaciones;
    }

    // Método para liberar habitación

    public void liberar() {
        disponible = true;
    }

    // Método para ocupar habitación

    public void ocupar() {
        if (!disponible) {
        throw new IllegalStateException("La habitación ya está ocupada.");
        }
        disponible = false;
    }

}
