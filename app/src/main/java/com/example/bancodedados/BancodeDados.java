package com.example.bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BancodeDados extends SQLiteOpenHelper {
    protected static final String TABELA_USUARIO = "TABELA_USUARIO";
    protected static final String USUARIO_NOME = "USUARIO_NOME";
    protected static final String USUARIO_EMAIL = "USUARIO_EMAIL";

    public AcessoBD(@Nullable Context context) {

        super(context, "ClienteBD", null, 1);
    }
   public void onCreate(SQLiteDatabase db){
        String statement = "CREATE TABLE" + TABELA_USUARIO +
                " (" + USUARIO_NOME + " TEXT, " + USUARIO_EMAIL + " TEXT)";

        db.execSQL(statement);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean AddUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_NOME, usuario.getNomeUsuario());
        contentValues.put(USUARIO_EMAIL, usuario.getEmailUsuario());

        long inserirSucedido = db.insert(TABELA_USUARIO, null, contentValues);
        db.close();
        return inserirSucedido != -1;

    }



}
