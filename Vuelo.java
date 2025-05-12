
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    private String id;
    private String origen;
    private String destino;
    private LocalDateTime fechaHora;
    private int lugaresTotales;
    private int lugaresDisponibles;
    private List<Reserva> reservas;

    public Vuelo(String id, String origen, String destino, LocalDateTime fechaHora, int lugaresTotales) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaHora = fechaHora;
        this.lugaresTotales = lugaresTotales;
        this.lugaresDisponibles = lugaresTotales;
        this.reservas = new ArrayList<>();
    }

    public boolean agregarReserva(Pasajero pasajero, int cantidad) {
        if (cantidad <= lugaresDisponibles) {
            for (int i = 0; i < cantidad; i++) {
                Reserva r = new Reserva(pasajero, this);
                reservas.add(r);
                pasajero.agregarReserva(r);
            }
            lugaresDisponibles -= cantidad;
            return true;
        }
        return false;
    }

    public boolean anularServicioVuelo() {
        for (Reserva r : reservas) {
            System.out.println("Notificación a: " + r.getPasajero().getCorreo());
        }
        reservas.clear();
        lugaresDisponibles = lugaresTotales;
        System.out.println("Servicio de vuelo " + id + " anulado.");
        return true;
    }

    public String getId() { return id; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public int getLugaresDisponibles() { return lugaresDisponibles; }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public String toString() {
        return "Vuelo " + id + ": " + origen + " -> " + destino + " | " + fechaHora + " | Disponibles: " + lugaresDisponibles;
    }
}
