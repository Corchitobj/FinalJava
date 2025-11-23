package Observer;

import java.util.ArrayList;
import java.util.List;

public class GestorReservas {
    private final List<ReservaObserver> observadores = new ArrayList<>();
    private final List<UltimaDisponibilidadObserver> observadoresUltima = new ArrayList<>();
    private int habitacionesDisponibles = 10; //  valor inicial configurable

    // Suscripción general
    public void agregarObserver(ReservaObserver o) {
        observadores.add(o);
    }

    // Suscripción especial
    public void agregarUltimaDisponibilidadObserver(UltimaDisponibilidadObserver o) {
        observadoresUltima.add(o);
    }

    // Crear reserva y notificar
    public void crearReserva() {
        if (habitacionesDisponibles > 0) {
            habitacionesDisponibles--;

            // Notificar a todos los observadores generales
            for (ReservaObserver o : observadores) {
                o.actualizarDisponibilidad(habitacionesDisponibles);
            }

            // Si queda una sola habitación, notificar a los especializados
            if (habitacionesDisponibles == 1) {
                for (UltimaDisponibilidadObserver o : observadoresUltima) {
                    o.ultimaDisponibilidad();
                }
            }
        } else {
            System.out.println("[ERROR] No hay habitaciones disponibles.");
        }
    }
}

