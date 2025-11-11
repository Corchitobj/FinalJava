package hotel;
public class Habitacion {
    private int numero;
    private String tipo;
    private double precioPorNoche;

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

    // Setters opcionales (si querés permitir cambios post-construcción)
    public void setNumero(int numero) { this.numero = numero; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setPrecioPorNoche(double precioPorNoche) { this.precioPorNoche = precioPorNoche; }


    //Alta cohesión: método para mostrar datos de la habitación
    public void mostrarDatos() {
        System.out.println("Habitación Nº " + numero + " | Tipo: " + tipo + " | Precio: $" + precioPorNoche);
    }

    // ===============================
    // 🧱 Clase Builder interna
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


}
