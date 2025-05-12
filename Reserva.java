
public class Reserva {
    private Pasajero pasajero;
    private Vuelo vuelo;

    public Reserva(Pasajero pasajero, Vuelo vuelo) {
        this.pasajero = pasajero;
        this.vuelo = vuelo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public String generarTicket() {
        return "===== TICKET DE RESERVA =====\nPasajero: " + pasajero.getNombre() +
                "\nVuelo: " + vuelo.getId() + " (" + vuelo.getOrigen() + " -> " + vuelo.getDestino() + ")" +
                "\nFecha/Hora: " + vuelo.getFechaHora();
    }
}
