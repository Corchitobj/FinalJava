package persona;

public abstract class Persona {
    //SUBO REPO 
    protected String nombre;
    protected String apellido;
    protected double dni;

    public Persona(String nombre, String apellido, double dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public double getDni() { return dni; }
    public void setDni(double dni) { this.dni = dni; }

    public abstract String getDescripcion();
}


