package com.agenda.pumg.config;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import java.util.HashMap;
import java.util.Map;

public class  Servicio {

    public void agregarContacto(String nombre, String telefono, String correo) {
        try {
            Firestore db = FirestoreClient.getFirestore();

            Map<String, Object> datos = new HashMap<>();
            datos.put("nombre", nombre);
            datos.put("telefono", telefono);
            datos.put("correo", correo);

            ApiFuture<WriteResult> resultado = db.collection("contactos").document().set(datos);
            System.out.println("Contacto agregado con éxito en: " + resultado.get().getUpdateTime());
        } catch (Exception e) {
            System.err.println("Error al agregar contacto: " + e.getMessage());
        }
    }
}