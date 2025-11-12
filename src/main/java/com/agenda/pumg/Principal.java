package com.agenda.pumg;

import com.agenda.pumg.config.conexionFirebase; 
import com.agenda.pumg.config.Servicio;         

public class Principal {
    public static void main(String[] args) {
        System.out.println("Iniciando conexión a Firebase..");

    conexionFirebase.getInstance();

        
        Servicio servicio = new Servicio();
        servicio.agregarContacto("Jose", "5554-1234", "jose@hotmail.com");

        System.out.println("Proceso terminado.");
    }
}