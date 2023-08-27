package com.example.jugos_mm;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Ticket
{
    private CollectionReference TICKETSCollection;
    private TextView usuarioId;
    private TextView  saborElegido;
    private String Cuantos;
    private int Total_apagar;

    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.ticket);

        Ticket nuevoTicket = new Ticket();
        nuevoTicket.setUSabores(usuarioId); // Suponiendo que tienes una variable s_user que contiene el ID del usuario
        nuevoTicket.setId1(saborElegido);
        nuevoTicket.setUsuarioId(Cuantos); // Ajusta esto según cómo obtienes la cantidad
        nuevoTicket.setUsuarioId(Total_apagar); // Ajusta esto según cómo calculas el total

        // Apuntadores o variables a los objetos FIREBASE
        FirebaseAuth authen = FirebaseAuth.getInstance();//declaracion de la instancia
        FirebaseFirestore APK_JUGOS_NATURALES = FirebaseFirestore.getInstance();
        CollectionReference TICKETSCollection = APK_JUGOS_NATURALES.collection( "TICKETS" );

        APK_JUGOS_NATURALES.collection("TICKETS")
                .add(nuevoTicket)
                .addOnSuccessListener(documentReference ->
                {
                    // Éxito al agregar el ticket
                    String ticketId = documentReference.getId();
                    Log.d("Firestore", "Ticket agregado con ID: " + ticketId);
                })
                .addOnFailureListener(e -> {
                    // Error al agregar el ticket
                    Log.e("Firestore", "Error al agregar el ticket", e);
                });
    }
}
    }


}
