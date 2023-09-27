package com.example.redai;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DBNome = "RedaiDB";
    private static final int VERSAO = 1;

    public static final String TABELA_USUARIO = "Usuario";
    public static final String COLUNA_USERNAME = "Nome de Usuário";
    public static final String COLUNA_NOME = "Nome";
    public static final String COLUNA_EMAIL = "Email";
    public static final String COLUNA_SENHA = "Senha";
    public static final String COLUNA_FOTO_PERFIL = "Foto de Perfil";


    public DbHelper(@Nullable Context context) {
        super(context, DBNome, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA_USUARIO + " ( "
                + COLUNA_USERNAME + " TEXT PRIMARY KEY, "
                + COLUNA_NOME + " TEXT NOT NULL, "
                + COLUNA_EMAIL + " TEXT NOT NULL, "
                + COLUNA_SENHA + " INTEGER NOT NULL, "
                + COLUNA_FOTO_PERFIL + " BLOB"
                + ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
