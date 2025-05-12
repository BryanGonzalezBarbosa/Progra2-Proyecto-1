
import java.util.ArrayList;
import java.util.List;

public class Pasajero extends Usuario {
    private List<Reserva> reservas;

    public Pasajero(String nombre, String correo, String contraseña) {
        super(nombre, correo, contraseña);
        this.reservas = new ArrayList<>();
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}
