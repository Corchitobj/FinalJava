package Observer;

public class PanelRecepcion implements ReservaObserver {
    @Override
    public void actualizarDisponibilidad(int disponibles) {
        System.out.println("[Recepción] Habitaciones disponibles  " + disponibles);
    }

}
