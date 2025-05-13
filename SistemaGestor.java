import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaGestor {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Vuelo> vuelos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear usuarios y vuelos de prueba
        Empleado emp = new Empleado("Carlos", "carlos@aerolinea.com", "admin123");
        Pasajero p1 = new Pasajero("Luis", "luis@gmail.com", "1234");

        usuarios.add(emp);
        usuarios.add(p1);

        Vuelo v1 = new Vuelo("V001", "Morelia", "CDMX", LocalDateTime.now().plusDays(1), 100);
        Vuelo v2 = new Vuelo("V002", "CDMX", "Cancún", LocalDateTime.now().plusDays(2), 50);
        vuelos.add(v1);
        vuelos.add(v2);
        emp.altaVuelo(v1);
        emp.altaVuelo(v2);

        boolean salir = false;

        while (!salir) {
            System.out.println("\n== Bienvenido a Aerolínea MX ==");
            System.out.println("1. Iniciar sesión");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int op = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            if (op == 0) {
                salir = true;
                continue;
            }

            System.out.print("Correo: ");
            String correo = sc.nextLine();
            System.out.print("Contraseña: ");
            String pass = sc.nextLine();

            Usuario actual = null;
            for (Usuario u : usuarios) {
                if (u.login(correo, pass)) {
                    actual = u;
                    break;
                }
            }

            if (actual == null) {
                System.out.println("Credenciales incorrectas.");
                continue;
            }

            System.out.println("Inicio de sesión exitoso como: " + actual.getNombre());

            if (actual instanceof Empleado) {
                menuEmpleado((Empleado) actual, sc);
            } else if (actual instanceof Pasajero) {
                menuPasajero((Pasajero) actual, sc);
            }
        }

        System.out.println("Gracias por usar el sistema.");
        sc.close();
    }

    private static void menuEmpleado(Empleado emp, Scanner sc) {
        boolean activo = true;
        while (activo) {
            System.out.println("\n--- Menú Empleado ---");
            System.out.println("1. Cancelar un vuelo");
            System.out.println("2. Ver todos los vuelos");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    for (Vuelo v : vuelos) System.out.println(v);
                    System.out.print("ID del vuelo a cancelar: ");
                    String id = sc.nextLine();
                    Vuelo vueloAEliminar = null;
                
                    for (Vuelo v : vuelos) {
                        if (v.getId().equals(id)) {
                            if (v.getReservas().isEmpty()) {
                                emp.anularServicioVuelo(v);
                                vueloAEliminar = v;
                                System.out.println("Vuelo cancelado exitosamente.");
                            } else {
                                System.out.println("No se puede cancelar el vuelo: tiene reservas activas.");
                            }
                            break;
                        }
                    }
                
                    if (vueloAEliminar != null) {
                        vuelos.remove(vueloAEliminar);
                    }
                }
                case 2 -> vuelos.forEach(System.out::println);
                case 3 -> {
                    System.out.println("Cerrando sesión de empleado...");
                    activo = false;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuPasajero(Pasajero pasajero, Scanner sc) {
        boolean activo = true;
        while (activo) {
            System.out.println("\n--- Menú Pasajero ---");
            System.out.println("1. Ver vuelos disponibles");
            System.out.println("2. Buscar por origen y destino");
            System.out.println("3. Reservar vuelo");
            System.out.println("4. Ver mis tickets");
            System.out.println("5. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> vuelos.forEach(System.out::println);
                case 2 -> {
                    System.out.print("Origen: ");
                    String o = sc.nextLine();
                    System.out.print("Destino: ");
                    String d = sc.nextLine();
                    vuelos.stream()
                          .filter(v -> v.getOrigen().equalsIgnoreCase(o) && v.getDestino().equalsIgnoreCase(d))
                          .forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID de vuelo: ");
                    String id = sc.nextLine();
                    System.out.print("Lugares a reservar: ");
                    int c = sc.nextInt();
                    sc.nextLine();
                    for (Vuelo v : vuelos) {
                        if (v.getId().equals(id)) {
                            boolean reservado = v.agregarReserva(pasajero, c);
                            System.out.println(reservado ? "¡Reserva exitosa!" : "No hay suficientes lugares.");
                        }
                    }
                }
                case 4 -> pasajero.getReservas().forEach(r -> System.out.println(r.generarTicket()));
                case 5 -> {
                    System.out.println("Cerrando sesión de pasajero...");
                    activo = false;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}
