import Observer.GestorReservas;
import Observer.Observadores.AlertaUltimaHabitacion;
import Observer.Observadores.PanelRecepcion;

import hotel.*;
import persona.*;

public class App {
    public static void main(String[] args) throws Exception {
        
       GestorReservas gestor = new GestorReservas();

        // Suscripción general
        gestor.agregarObserver(disponibles -> 
            System.out.println("Observador general: quedan " + disponibles + " habitaciones"));

        // Suscripción específica
        gestor.agregarUltimaDisponibilidadObserver(() -> 
            System.out.println("Observador especial: ¡solo queda una habitación!"));

        // Simular reservas
        for (int i = 0; i < 5; i++) {
            gestor.crearReserva();
        }
    }
}
