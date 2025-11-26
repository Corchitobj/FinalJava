package hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import persona.Empleado;
import persona.Huesped;

public class Hotel {
    private String nombre;
    private String direccion;
    private int estrellas;

    private final List<Habitacion> habitaciones = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>();
    private final List<Empleado> empleados = new ArrayList<>();
    private final List<Huesped> huespedes = new ArrayList<>();

    // Singleton
    private static Hotel instanciaUnica = null;

    private Hotel(String nombre, String direccion, int estrellas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.estrellas = estrellas;
    }

    public static Hotel getInstancia(String nombre, String direccion, int estrellas) {
        if (instanciaUnica == null) {
            instanciaUnica = new Hotel(nombre, direccion, estrellas);
        }
        return instanciaUnica;
    }

    public static Hotel getInstancia() { 
        return instanciaUnica; 
    }

    public static void destruirInstancia() {
        if (instanciaUnica != null) {
            instanciaUnica.habitaciones.clear();
            instanciaUnica.reservas.clear();
            instanciaUnica.empleados.clear();
            instanciaUnica.huespedes.clear();
            instanciaUnica = null;
        }
    }

    // Getters básicos
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public int getEstrellas() { return estrellas; }

    // Setters básicos
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setEstrellas(int estrellas) { this.estrellas = estrellas; }

    // Métodos para Habitaciones
    public void agregarHabitacion(Habitacion h) {
        if (h != null && !habitaciones.contains(h)) {
            habitaciones.add(h);
        }
    }

    public Habitacion obtenerHabitacion(int numero) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero() == numero) return h;
        }
        return null;
    }

    public Habitacion buscarHabitacionDisponible(TipoHabitacion tipo) {
        for (Habitacion h : habitaciones) {
            if (h.getTipo() == tipo && h.estaDisponible()) {
                return h;
            }
        }
        return null;
    }

    public List<Habitacion> getHabitaciones() {
        return Collections.unmodifiableList(habitaciones);
    }

    // Métodos para Reservas
    public void agregarReserva(Reserva r) {
        if (r != null && !reservas.contains(r)) {
            reservas.add(r);
        }
    }

    public List<Reserva> getReservas() {
        return Collections.unmodifiableList(reservas);
    }

    // Métodos para Empleados
    public void agregarEmpleado(Empleado e) {
        if (e != null && !empleados.contains(e)) {
            empleados.add(e);
        }
    }

    public Empleado obtenerEmpleadoPorDNI(double dni) {
        for (Empleado e : empleados) {
            if (e.getDni() == dni) return e;
        }
        return null;
    }

    public List<Empleado> getEmpleados() {
        return Collections.unmodifiableList(empleados);
    }

    // Métodos para Huéspedes
    public void agregarHuesped(Huesped h) {
        if (h != null && !huespedes.contains(h)) {
            huespedes.add(h);
        }
    }

    public Huesped buscarHuesped(double dni) {
        for (Huesped h : huespedes) {
            if (h.getDni() == dni) return h;
        }
        return null;
    }

    public List<Huesped> getHuespedes() {
        return Collections.unmodifiableList(huespedes);
    }

    // Mostrar estado completo del hotel
    public void mostrarEstadoHotel() {
        System.out.println("Hotel: " + nombre);
        System.out.println("Dirección: " + direccion);
        System.out.println("Estrellas: " + estrellas);

        System.out.println("Habitaciones:");
        for (Habitacion h : habitaciones) {
            System.out.println(" - " + h);
        }

        System.out.println("Empleados:");
        for (Empleado e : empleados) {
            System.out.println(" - " + e);
        }

        System.out.println("Huéspedes:");
        for (Huesped h : huespedes) {
            System.out.println(" - " + h);
        }

        System.out.println("Reservas:");
        for (Reserva r : reservas) {
            System.out.println(" - " + r);
        }
    }
}
