package com.agenda.pumg.config;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.FileInputStream;
import java.io.IOException;

public class conexionFirebase {

    private static conexionFirebase instance;

    private conexionFirebase() {
        try {
            // Ruta al archivo JSON de tu proyecto Firebase (credenciales)
            FileInputStream serviceAccount = new FileInputStream("C:/users/Wendy Lopez/Downloads/agendaPumg-firebase-adminsdk-fbsvc-3e6be8cd65.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://agendaPumg.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("✅ Conexión a Firebase inicializada correctamente.");

        } catch (IOException e) {
            System.err.println("❌ Error al conectar con Firebase: " + e.getMessage());
        }
    }

    public static conexionFirebase getInstance() {
        if (instance == null) {
            instance = new conexionFirebase();
        }
        return instance;
    }
    }