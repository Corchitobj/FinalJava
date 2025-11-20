package hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import persona.Empleado;

public class Hotel {
    private String nombre;
    private String direccion;
    private int estrellas;

    private final List<Habitacion> habitaciones = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>();
    private final List<Empleado> empleados = new ArrayList<>();

    // Singleton: única instancia
    private static Hotel instanciaUnica = null;

    // Constructor privado
    private Hotel(String nombre, String direccion, int estrellas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.estrellas = estrellas;
    }

    // Método estático para obtener la instancia única
    public static Hotel getInstancia(String nombre, String direccion, int estrellas) {
        if (instanciaUnica == null) {
            instanciaUnica = new Hotel(nombre, direccion, estrellas);
        }
        return instanciaUnica;
    }

    // Método para obtener la instancia sin parámetros (una vez creada)
    public static Hotel getInstancia() {
        return instanciaUnica;
    }

    public static void destruirInstancia() {
        if (instanciaUnica != null) {
        instanciaUnica.habitaciones.clear();
        instanciaUnica.reservas.clear();
        instanciaUnica.empleados.clear();
        instanciaUnica = null;
        }
    }
    
    // ===============================
    // Getters y setters básicos
    // ===============================
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getEstrellas() { return estrellas; }
    public void setEstrellas(int estrellas) { this.estrellas = estrellas; }

    // ===============================
    // Métodos para Habitaciones
    // ===============================
    public void agregarHabitacion(Habitacion h) {
        if (h != null && !habitaciones.contains(h)) {
            habitaciones.add(h);
        }
    }

    public boolean eliminarHabitacion(Habitacion h) {
        return habitaciones.remove(h);
    }

    public List<Habitacion> getHabitaciones() {
        return Collections.unmodifiableList(habitaciones);
    }

    public Habitacion obtenerHabitacion(int numero) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero() == numero) {
                return h;
            }
        }
        return null;
    }

    public List<Habitacion> obtenerHabitacionesPorTipo(String tipo) {
        List<Habitacion> resultado = new ArrayList<>();
        for (Habitacion h : habitaciones) {
            if (h.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(h);
            }
        }
        return resultado;
    }

    public void actualizarPrecioHabitacion(int numero, double nuevoPrecio) {
        Habitacion h = obtenerHabitacion(numero);
        if (h != null) {
            h.setPrecioPorNoche(nuevoPrecio);
        }
    }

    // ===============================
    // Métodos para Reservas
    // ===============================
    public void agregarReserva(Reserva r) {
        if (r != null && !reservas.contains(r)) {
            reservas.add(r);
        }
    }

    public boolean eliminarReserva(Reserva r) {
        return reservas.remove(r);
    }

    public List<Reserva> getReservas() {
        return Collections.unmodifiableList(reservas);
    }

    public List<Reserva> obtenerReservasPorDNIHuesped(String dni) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getHuesped().getDni().equals(dni)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    // ===============================
    // Métodos para Empleados
    // ===============================
    public void agregarEmpleado(Empleado e) {
        if (e != null && !empleados.contains(e)) {
            empleados.add(e);
        }
    }

    public boolean eliminarEmpleado(Empleado e) {
        return empleados.remove(e);
    }

    public List<Empleado> getEmpleados() {
        return Collections.unmodifiableList(empleados);
    }

    public Empleado obtenerEmpleadoPorDNI(String dni) {
        for (Empleado e : empleados) {
            if (e.getDni().equals(dni)) {
                return e;
            }
        }
        return null;
    }

    public List<Empleado> obtenerEmpleadosPorCargo(String cargo) {
        List<Empleado> resultado = new ArrayList<>();
        for (Empleado e : empleados) {
            if (e.getCargo().equalsIgnoreCase(cargo)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // ===============================
    // Método de presentación --- Alta cohesión
    // ===============================
    public void mostrarDatos() {
        System.out.println("Hotel: " + nombre);
        System.out.println("Dirección: " + direccion);
        System.out.println("Estrellas: " + estrellas);
    }
}