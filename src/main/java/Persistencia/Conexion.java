package Persistencia;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Raikiri
 */
public class Conexion {

    public static Firestore db;

    public static Firestore conectarDB() {

        try {
            //Busca el archivo de cuenta de servicio y accede al mismo
            FileInputStream cuentaServicio = new FileInputStream("agendapersonal.json");

            //Busca el token con las credenciales requeridas para acceder a la BD
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(cuentaServicio))
                    .build();

            FirebaseApp.initializeApp(options);
            
            //Inicializacion de la base de datos
            db = FirestoreClient.getFirestore();
            System.out.println("Conexion a Firestore");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return db;       
    }
}
