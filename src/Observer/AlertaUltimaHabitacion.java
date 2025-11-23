package Observer;

public class AlertaUltimaHabitacion implements UltimaDisponibilidadObserver {
    @Override
    public void ultimaDisponibilidad() {
        System.out.println("[Alerta] ¡Solo queda una habitación disponible!");
    }
}

