package Observer;

import java.util.ArrayList;
import java.util.List;

public class GestorReservas {
    private final List<ReservaObserver> observadores = new ArrayList<>();
    private final List<UltimaDisponibilidadObserver> observadoresUltima = new ArrayList<>();
    private int habitacionesDisponibles = 10;

    public void agregarObserver(ReservaObserver o) {
        observadores.add(o);
    }

    public void agregarUltimaDisponibilidadObserver(UltimaDisponibilidadObserver o) {
        observadoresUltima.add(o);
    }

    public void crearReserva() {
        if (habitacionesDisponibles > 0) {
            habitacionesDisponibles--;

            // Notificar a todos los observadores
            for (ReservaObserver o : observadores) {
                o.actualizarDisponibilidad(habitacionesDisponibles);
            }

            // Si queda una sola habitación, notificar a los especializados
            if (habitacionesDisponibles == 1) {
                for (UltimaDisponibilidadObserver o : observadoresUltima) {
                    o.ultimaDisponibilidad();
                }
            }
        }
    }


}
