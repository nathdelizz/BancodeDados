package com.example.bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Button AddUsuario;
    Button ListaUsuario;
    Button AtualizarUsuario;
    EditText ETnome;
    EditText ETemail;
    EditText ETident;
    ListView lvUsuarios;


    ArrayAdapter usuarioArrayAdapter;
    BancodeDados acessoBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddUsuario= findViewById(R.id.AddUsuario);
        ListaUsuario = findViewById(R.id.Listasuarios);
        AtualizarUsuario = findViewById(R.id.AtualizarUsuario);
        ETemail= findViewById(R.id.ETemail);
        ETnome= findViewById(R.id.ETnome);
        ETident= findViewById(R.id.Etident);
        lvUsuario = findViewById(R.id.lv_usuarios);


        acessoBD = new BancodeDados(MainActivity.this);
        mostrarUsuariosNaListView(acessoBD);

       AddUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Usuario usuario = null;

                try {
                    usuario = new Usuario(-1
                            ETnome.getText().toString(),
                            ETemail.getText().toString());
                    boolean sucesso = acessoBD.adicionarUsuario(usuario);
                    Toast.makeText(MainActivity.this, "Sucesso:" + sucesso, Toast.LENGTH_SHORT.show();
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Erro na criação do usuário", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Erro na criação do usuário!", Toast.LENGTH_LONG).show();
                }
            }

       });
 ListaUsuario.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
       mostrarUsuariosNaListView(acessoBD);
       Toast.makeText(MainActivity.this, "Lista de usuários preenchida com sucesso", Toast.LENGTH_SHORT).show();

     }
 });

    lvUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            System.out.println("Captou click na lista!");
            Usuario usuarioClicado = (Usuario) parent.getItemPosition(position);
            boolean excluiu = acessoBD.excluirUsuario(usuarioClicado)

        mostrarUsuariosNaListView(acessoBD);

        Toast.makeText(MainActivity.this, "Usuário excluído(" + excluiu +"):" + usuarioClicado.toString(), Toast.LENGTH_SHORT).show();

        }
    });

     AtualizarUsuario.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

        Usuario usuario = null;
        try {
            usuario = new Usuario(Integer.parseInt(ETident.getText().toString()), ETnome.getText().toString(), ETemail.getText().toString());
            boolean sucesso = acessoBD.atualizarUsuario(usuario);
            mostrarUsuariosNaListView(acessoBD);
            Toast.makeText(MainActivity.this, "Sucesso:" + sucesso, Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Erro na conversão de uma String para int: Idade não corresponde a número!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Erro na criação do usuário!", Toast.LENGTH_LONG).show();
            usuario = new Usuario(-1, "erro", 0);
        }
         }
     });


    }

     private  void mostrarUsuariosNaListView(BancodeDados acessoBD){
        usuarioArrayAdapter = new ArrayAdapter<Usuario>(MainActivity.this,
                android.R.layout.simple_list_item_1, acessoBD.getListaUsuarios());//Dentro de <> está o tipo de objeto que será adicionado na lista
         lvUsuarios.setAdapter(usuarioArrayAdapter);
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
