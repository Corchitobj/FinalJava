package Observer;

public class Observadores {
    public class PanelRecepcion implements ReservaObserver {
    public void actualizarDisponibilidad(int disponibles) {
        System.out.println(" Panel: habitaciones disponibles → " + disponibles);
    }
}

public class AlertaUltimaHabitacion implements UltimaDisponibilidadObserver {
    public void ultimaDisponibilidad() {
        System.out.println("¡Solo queda una habitación!");
    }
}

}
