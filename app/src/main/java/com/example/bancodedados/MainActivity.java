package com.example.bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button AddUsuario;
    EditText ETnome;
    EditText ETemail;


    ArrayAdapter usuarioArrayAdapter;
    AcessoBD acessoBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddUsuario= findViewById(R.id.AddUsuario);
        ETemail= findViewById(R.id.ETemail);
        ETnome= findViewById(R.id.ETnome);


       AddUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Usuario usuario = null;

                try {
                    usuario = new Usuario(-1
                            ETnome.getText().toString(),
                            ETemail.getText().toString()));
                    boolean sucesso = acessoBD.adicionarUsuario(usuario);
                    Toast.makeText(MainActivity.this, "Sucesso:" + sucesso, Toast.LENGTH_SHORT.show();
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Erro na criação do usuário", Toast.LENGTH_LONG).show();
                }
            }

       });

        }
    }
