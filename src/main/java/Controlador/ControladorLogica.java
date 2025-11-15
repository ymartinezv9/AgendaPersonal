/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Raikiri
 */
import Modelo.Contacto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ControladorLogica {

    private final Firestore db;

    public ControladorLogica(Firestore db) {
        this.db = db;
    }

    // Agregar nuevo contacto
    public void agregarContacto(String nombre, String telefono,
            String correoElectronico, String pasatiempos,
            String areaComun)
            throws ExecutionException, InterruptedException {

        DocumentReference docRef = db.collection("contactos").document(nombre);

        // Validar duplicado
        if (docRef.get().get().exists()) {
            System.out.println("El contacto con ese nombre ya existe.");
            return;
        }

        Map<String, Object> datos = new HashMap<>();
        datos.put("nombre", nombre);
        datos.put("telefono", telefono);
        datos.put("correoElectronico", correoElectronico);
        datos.put("pasatiempos", pasatiempos);
        datos.put("areaComun", areaComun);

        ApiFuture<WriteResult> result = docRef.set(datos);
        System.out.println("Contacto agregado en: " + result.get().getUpdateTime());
    }

    // Mostrar contactos
    public void listarContactos() throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> future = db.collection("contactos").get();
        List<QueryDocumentSnapshot> documentos = future.get().getDocuments();

        if (documentos.isEmpty()) {
            System.out.println("No hay contactos guardados.");
            return;
        }

        System.out.println("\n=== LISTA DE CONTACTOS ===");

        for (QueryDocumentSnapshot doc : documentos) {
            System.out.println("--------------------------------");
            System.out.println("Nombre: " + doc.getString("nombre"));
            System.out.println("Telefono: " + doc.getString("telefono"));
            System.out.println("Correo: " + doc.getString("correoElectronico"));
            System.out.println("Pasatiempos: " + doc.getString("pasatiempos"));
            System.out.println("Donde se conocieron: " + doc.getString("areaComun"));
        }
        System.out.println("--------------------------------");
    }

    //Modificar contacto
    public Map<String, Object> prepararActualizacion(
            String telefono, String correoElectronico,
            String pasatiempos, String areaComun) {

        Map<String, Object> cambios = new HashMap<>();

        if (telefono != null && !telefono.isBlank()) {
            cambios.put("telefono", telefono);
        }
        if (correoElectronico != null && !correoElectronico.isBlank()) {
            cambios.put("correoElectronico", correoElectronico);
        }
        if (pasatiempos != null && !pasatiempos.isBlank()) {
            cambios.put("pasatiempos", pasatiempos);
        }
        if (areaComun != null && !areaComun.isBlank()) {
            cambios.put("areaComun", areaComun);
        }

        return cambios;
    }

    public void modificarContacto(String nombre, Map<String, Object> camposActualizados)
            throws ExecutionException, InterruptedException {

        if (camposActualizados == null || camposActualizados.isEmpty()) {
            System.out.println("No se recibieron datos para actualizar.");
            return;
        }

        DocumentReference docRef = db.collection("contactos").document(nombre);
        DocumentSnapshot snap = docRef.get().get();

        if (!snap.exists()) {
            System.out.println("No existe contacto con ese nombre.");
            return;
        }

        ApiFuture<WriteResult> result = docRef.update(camposActualizados);
        System.out.println("Contacto actualizado en: " + result.get().getUpdateTime());
    }

    // Eliminar contacto
    public void eliminarContacto(String nombre)
            throws ExecutionException, InterruptedException {

        DocumentReference docRef = db.collection("contactos").document(nombre);

        if (!docRef.get().get().exists()) {
            System.out.println("El contacto no existe.");
            return;
        }

        ApiFuture<WriteResult> result = docRef.delete();
        System.out.println("Contacto eliminado en: " + result.get().getUpdateTime());
    }

}
