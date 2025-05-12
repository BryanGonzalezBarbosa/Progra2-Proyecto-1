
import java.util.ArrayList;
import java.util.List;

public class Empleado extends Usuario {
    private List<Vuelo> vuelosGestionados;

    public Empleado(String nombre, String correo, String contraseña) {
        super(nombre, correo, contraseña);
        this.vuelosGestionados = new ArrayList<>();
    }

    public void altaVuelo(Vuelo vuelo) {
        vuelosGestionados.add(vuelo);
    }

    public boolean anularServicioVuelo(Vuelo vuelo) {
        return vuelo.anularServicioVuelo();
    }
}
