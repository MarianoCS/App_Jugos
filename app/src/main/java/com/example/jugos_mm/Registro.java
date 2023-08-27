package com.example.jugos_mm;



import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Registro extends AppCompatActivity
{

    private EditText et_Email, et_Password;
   private Button Btn;

    private CheckBox Chk;
    private boolean Valido = false;


    private String et_Correo = "";
    private String et_password = "";

    // Apuntadores o variables a los objetos FIREBASE
    FirebaseAuth Authen = FirebaseAuth.getInstance();//declaracion de la instancia


    @Override
    protected void onCreate ( Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        et_Email = (EditText) findViewById(R.id.et_Email);
        et_Password = (EditText) findViewById(R.id.et_Password);
        Btn = (Button) findViewById( R.id.Btn );
        Chk = (CheckBox) findViewById( R.id.Chk );

        // instanciar el objeto de autenticacion
        FirebaseAuth Authen = FirebaseAuth.getInstance();

        Chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            //cerramos la conexion
            //Authen.getInstance().signOut();

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (Chk.isChecked())
                {
                    Intent I_reg = new Intent (MainActivity.this, Registro.class);
                }

            }
        });

        //Login
        Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                et_Correo = et_Email.getText().toString();
                et_password = et_Password.getText().toString();

                if (!et_Correo.isEmpty() && !et_password.isEmpty())
                {
                    if (et_password.length() >= 6)
                    {
                        Toast.makeText(MainActivity.this, "Validando", Toast.LENGTH_SHORT).show();

                        if (Validar_user())
                        {
                            FirebaseUser currentUser = Authen.getCurrentUser();
                            Intent I_menu = new Intent(MainActivity.this, Menu.class);
                            I_menu.putExtra("correo", currentUser.getUid().toString());
                            I_menu.putExtra("Id", currentUser.getUid().toString());

                            //cerramos la conexion
                            //Authen.getInstance().signOut();
                            startActivity(I_menu);
                        } else
                            Toast.makeText(MainActivity.this, "Usuario no valido", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(MainActivity.this, "Minimo debe tener 6 caracteres en el password", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Llenar los campos: correo y password", Toast.LENGTH_SHORT).show();
            }
        });
        //Authen.getInstance().signOut();//cerramos la conexion
    }

    public boolean Validar_user()
    {
        Authen.signInWithEmailAndPassword( et_Correo, et_password ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Entrada exitosa.", Toast.LENGTH_SHORT).show();
                    Valido = true;
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Fallo vuelva a intentar.", Toast.LENGTH_SHORT).show();
                    Valido = false;
                }
            }
        });
        if  ( Valido )
            return true;
        else
            return false;

    }
}

