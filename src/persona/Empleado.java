package persona;

public class Empleado extends Persona {
    private String cargo;
    private double salario;

    public Empleado(String nombre, String apellido, String dni, String cargo, double salario) {
        super(nombre, apellido, dni);
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    @Override
    public String getDescripcion() {
        return "Empleado: " + getNombre() + " " + getApellido() +
               " - Cargo: " + cargo +
               " - Salario: $" + salario;
    }

    @Override
    public String toString() {
        return getNombre() + " " + getApellido() +
               " (Cargo: " + cargo + ", Salario: $" + salario + ")";
    }
}