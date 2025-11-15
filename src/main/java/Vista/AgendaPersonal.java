package Vista;

import Controlador.ControladorLogica;
import Persistencia.Conexion;
import com.google.cloud.firestore.Firestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Raikiri
 */
public class AgendaPersonal {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Conexion.conectarDB();

        //Aca se declaran las variables necesarias para llamar a la aplicacion
        Firestore db = Conexion.conectarDB();
        ControladorLogica aplicacion = new ControladorLogica(db);
        Scanner leer = new Scanner(System.in);
        int opcion;

        do {

            // Aca se muestran las opciones del menu
            System.out.println("Agenta Personal");
            System.out.println("1. Agregar nuevo contacto");
            System.out.println("2. Mostrar contactos existentes");
            System.out.println("3. Modificar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Salir");

            System.out.print("Seleccione una opcion: ");
            opcion = leer.nextInt();
            leer.nextLine();
            //Inicio del switch-case
            switch (opcion) {
                case 1:
                    System.out.print("Nombre y apellido: ");
                    String nombre = leer.nextLine();

                    System.out.print("Telefono: ");
                    String telefono = leer.nextLine();

                    System.out.print("Correo electronico");
                    String correo = leer.nextLine();

                    System.out.print("Pasatiempos: ");
                    String pasatiempos = leer.nextLine();

                    System.out.print("Donde se conocieron: ");
                    String areaComun = leer.nextLine();
                    aplicacion.agregarContacto(nombre, telefono, correo, pasatiempos, areaComun);

                    break;
                case 2:
                    aplicacion.listarContactos();
                    break;
                case 3:
                    System.out.print("Ingrese el nombre de la persona a modificar");
                    Map<String, Object> camposActualizados = new HashMap<>();

                    System.out.print("Ingrese el nombre del contacto a modificar: ");
                     nombre = leer.nextLine();

                    System.out.print("Nuevo teléfono?: ");
                     telefono = leer.nextLine();
                    if (!telefono.isBlank()) {
                        camposActualizados.put("telefono", telefono);
                    }

                    System.out.print("Nuevo correo?: ");
                     correo = leer.nextLine();
                    if (!correo.isBlank()) {
                        camposActualizados.put("correoElectronico", correo);
                    }

                    System.out.print("Nuevos pasatiempos?: ");
                     pasatiempos = leer.nextLine();
                    if (!pasatiempos.isBlank()) {
                        camposActualizados.put("pasatiempos", pasatiempos);
                    }

                    System.out.print("Donde se conocieron: ");
                     areaComun = leer.nextLine();
                    if (!areaComun.isBlank()) {
                        camposActualizados.put("areaComun", areaComun);
                    }

                    if (camposActualizados.isEmpty()) {
                        System.out.print("No se actualizó ningún campo.");
                        return;
                    }

                    try {
                        aplicacion.modificarContacto(nombre, camposActualizados);
                        System.out.print("Contacto actualizado correctamente.");
                    } catch (Exception e) {
                        System.out.print("Error al actualizar el contacto: " + e.getMessage());
                    }
                    break;
                case 4:
                    aplicacion.listarContactos();
                    
                    System.out.println("Ingrese nombre y apellido del contacto a eliminar");
                    nombre = leer.nextLine();
                    aplicacion.eliminarContacto(nombre);

                    break;
                    
                default:
                    throw new AssertionError();
            }

        } while (true);
    }
}
