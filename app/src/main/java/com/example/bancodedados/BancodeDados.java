package com.example.bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancodeDados extends SQLiteOpenHelper {
    protected static final String TABELA_USUARIO = "TABELA_USUARIO";
    protected static final String USUARIO_ID = "ID";
    protected static final String USUARIO_NOME = "USUARIO_NOME";
    protected static final String USUARIO_EMAIL = "USUARIO_EMAIL";

    public BancodeDados(@Nullable Context context) {

        super(context, "ClienteBD", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE TABELA_USUARIO
        String statement = "CREATE TABLE " + TABELA_USUARIO +
                " (" + USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USUARIO_NOME + " TEXT, " + USUARIO_EMAIL + " TEXT)";

        db.execSQL(statement);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean adicionarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_NOME, usuario.getNomeUsuario());
        contentValues.put(USUARIO_EMAIL, usuario.getEmailUsuario());

        long inserirSucedido = db.insert(TABELA_USUARIO, null, contentValues);
        db.close();
        return inserirSucedido != -1;

    }

    public boolean atualizarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_NOME, usuario.getNomeUsuario());
        contentValues.put(USUARIO_EMAIL, usuario.getEmailUsuario());
        contentValues.put(USUARIO_ID, usuario.getidUsuario());

        long atualizarSucedido = db.update(TABELA_USUARIO,
                contentValues,
                USUARIO_ID + "=" + usuario.getidUsuario(),
                null);
        db.close();

        //-1 indica que nenhuma linha foi inserida na referida tabela
        return atualizarSucedido != -1;


    }

    public List<Usuario> getListaUsuarios() {

        List<Usuario> listaUsuarios = new ArrayList<>();


        String queryStatement = "SELECT * FROM " + TABELA_USUARIO;

        SQLiteDatabase db = this.getReadableDatabase();


        try (Cursor cursor = db.rawQuery(queryStatement, null)) {

            if (cursor.moveToFirst()) {
                do {
                    int usuarioIdent = cursor.getInt(0);
                    String usuarioNome = cursor.getString(1);
                    String usuarioEmail = cursor.getString(2);

                    Usuario usuario = new Usuario(usuarioIdent, usuarioNome, usuarioEmail);
                    listaUsuarios.add(usuario);
                } while (cursor.moveToNext());
            } else {

            }

        cursor.close();
        }
        db.close();

        return listaUsuarios;
    }
   public boolean excluirUsuario(Usuario usuario){
       SQLiteDatabase db = this.getWritableDatabase();
       String queryString =
               "DELETE FROM " + TABELA_USUARIO + " WHERE " + USUARIO_ID + " = " + usuario.getidUsuario();

       Cursor cursor = db.rawQuery(queryString, null);

       if (cursor.moveToFirst()) {
           return true;
       } else {
           return false;
       }


   }

}
