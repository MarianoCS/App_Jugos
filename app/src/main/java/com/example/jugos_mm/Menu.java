package com.example.jugos_mm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.rpc.context.AttributeContext;

import java.util.HashMap;
import java.util.Map;

public class Menu extends AppCompatActivity {

    private FirebaseAuth Authen;

    private CollectionReference JUGOSCollection;
    private FirebaseFirestore APK_JUGOS_NATURALES ;

    private TextView Tv;
    private ImageView Imv;
    private ImageButton I_btn, I_btn2, I_btn3, I_btn4;

    private Button Btn_Comprar;

    String s_sabor_elegido = "";

    //Declaracion de la referencia para acceder a la realtime database
    FirebaseAuth auth = FirebaseAuth.getInstance();//declaracion de la instancia

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.menu);

        String s_Email = getIntent().getExtras().getString("Email");
        String s_user_id = getIntent().getExtras().getString("Id");

        Tv = findViewById(R.id.Tv);
        Imv = findViewById(R.id.ImV);
        I_btn = findViewById(R.id.I_btn);
        I_btn2 = findViewById(R.id.I_btn2);
        I_btn3 = findViewById(R.id.I_btn3);
        I_btn4 = findViewById(R.id.I_btn4);
        Btn_Comprar = findViewById(R.id.Btn_Comprar);

        I_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                I_btn = (ImageButton) findViewById(R.id.I_btn);
                I_btn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Toast.makeText(Menu.this, "Naranja", Toast.LENGTH_LONG).show();

                    }
                });

                I_btn2 = (ImageButton) findViewById(R.id.I_btn2);
                I_btn2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Menu.this, "Zanahoria", Toast.LENGTH_LONG).show();

                    }
                });

                I_btn3 = (ImageButton) findViewById(R.id.I_btn3);
                I_btn3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Toast.makeText(Menu.this, "Betabel", Toast.LENGTH_LONG).show();

                    }
                });

                I_btn4 = (ImageButton) findViewById(R.id.I_btn4);
                I_btn4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Toast.makeText(Menu.this, "Verde", Toast.LENGTH_LONG).show();

                    }
                });

                Btn_Comprar = (Button) findViewById(R.id.Btn_Comprar);
                Btn_Comprar.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent I_main = new Intent( Menu.this, Ticket.class);
                        startActivity(I_main);
                        //Finalizamos el button
                        //finish();

                    }
                });

                // Apuntadores o variables a los objetos FIREBASE
                FirebaseFirestore APK_JUGOS_NATURALES = FirebaseFirestore.getInstance();//Declaracion de la referencia para acceder a la realtime database
                FirebaseAuth athhen = FirebaseAuth.getInstance();//declaracion de la instancia

                //Configuramos el Listener para el botón de inicio de sesión en mi clase REGISTRO
                Btn_Comprar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Obtener los valores de las variables
                        String Id = s_sabor_elegido;
                        String Id2 = s_sabor_elegido;
                        String Id3 = s_sabor_elegido;
                        String Id4 = s_sabor_elegido;

                        // Pasar a la siguiente actividad (TicketActivity)
                        Intent intent = new Intent(Menu.this, Ticket.class);
                        intent.putExtra("Id", Id);
                        intent.putExtra("Id2", Id2);
                        intent.putExtra("Id3", Id3);
                        intent.putExtra("Id4", Id4);
                        startActivity(intent);
                    }
                });

                agregarDatos();
                obtenerDatos();
            }

            // Llamamos a los métodos para crear y obtener datos
            private void agregarDatos()
            {
                Map<String, Object> data = new HashMap<>();
                data.put("Id ", s_sabor_elegido);
                data.put("Id2 ", s_sabor_elegido);
                data.put("Id3 ", s_sabor_elegido);
                data.put("Id4 ", s_sabor_elegido);
                data.put("Total de jugos", 0);


                APK_JUGOS_NATURALES.collection("JUGOS")
                        .add(data)
                        .addOnSuccessListener(documentReference ->
                        {
                            // Éxito al agregar los datos
                        })
                        .addOnFailureListener(e ->
                        {
                            // Error al agregar los datos
                        });
            }

            private void obtenerDatos() {
                APK_JUGOS_NATURALES.collection("JUGOS")
                        .get()
                        .addOnSuccessListener(queryDocumentSnapshots ->
                        {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                            {
                                String Id = documentSnapshot.getString("Id");
                                int Cuantos = documentSnapshot.getLong("Total a pagar").intValue();
                                String Id2 = documentSnapshot.getString("Id2");
                                String Id3 = documentSnapshot.getString("Id3");
                                String Id4 = documentSnapshot.getString("Id4");

                                // Hacer algo con los datos obtenidos
                                // Por ejemplo, imprimirlos en el logcat
                                Log.d("FirestoreDatos", "Id1: " + Id + ", Cuantos: " + Cuantos);
                            }
                        })
                        .addOnFailureListener(e ->
                        {
                            // Error al obtener los datos
                        });
            }

        });

    }

    public void setAPK_JUGOS_NATURALES(FirebaseFirestore APK_JUGOS_NATURALES)
    {
        this.APK_JUGOS_NATURALES = APK_JUGOS_NATURALES;
    }
}


