package com.example.redai;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class UsuarioRepositorio {
    private DbHelper helper;

    public UsuarioRepositorio(Context context) {
        helper = new DbHelper(context);
    }

    public boolean cadastrarUsuario (Usuario usuario){
        //instancia do banco e metodo pra sobrescrever
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DbHelper.COLUNA_USERNAME, usuario.getUsername());
        valores.put(DbHelper.COLUNA_NOME, usuario.getNome());
        valores.put(DbHelper.COLUNA_EMAIL, usuario.getEmail());
        valores.put(DbHelper.COLUNA_SENHA, usuario.getSenha());

        ByteArrayOutputStream stream = new ByteArrayOutputStream(); //array de bytes
        //comprime a foto, transforma em um array de bytes e ajusta a qualidade
        usuario.getFotoPerfil().compress(Bitmap.CompressFormat.PNG, 100, stream);
        valores.put(DbHelper.COLUNA_FOTO_PERFIL, stream.toByteArray());

        //realiza o cadastro no banco
        long id = db.insert(DbHelper.TABELA_USUARIO, null, valores);
        db.close(); // fecha o banco

        if (id != -1){
            return true;
        }else {
            return false;
        }
    }

    public boolean atualizarUsuario(Usuario usuario){
       SQLiteDatabase db = helper.getWritableDatabase();
       ContentValues valores = new ContentValues();

        valores.put(DbHelper.COLUNA_USERNAME, usuario.getUsername());
        valores.put(DbHelper.COLUNA_NOME, usuario.getNome());
        valores.put(DbHelper.COLUNA_EMAIL, usuario.getEmail());
        valores.put(DbHelper.COLUNA_SENHA, usuario.getSenha());
        ByteArrayOutputStream stream = new ByteArrayOutputStream(); //array de bytes
        //comprime a foto, transforma em um array de bytes e ajusta a qualidade
        usuario.getFotoPerfil().compress(Bitmap.CompressFormat.PNG, 100, stream);
        valores.put(DbHelper.COLUNA_FOTO_PERFIL, stream.toByteArray());

        String valuesWhere[] = new String[1];
        valuesWhere[0] = String.valueOf(usuario.getUsername());

        long qtd = db.update(DbHelper.TABELA_USUARIO, valores, "username= ?", valuesWhere);
        db.close();

        if (qtd>1){
            return true;
        }else{
            return false;
        }
    }


    public boolean logarUsuario(Usuario usuario){
        SQLiteDatabase db = helper.getReadableDatabase();
        int exito = 0;
        String sql_busca_usuario = "SELECT * from"
                + DbHelper.TABELA_USUARIO
                + " WHERE "
                + DbHelper.COLUNA_EMAIL + "="
                + "'" + usuario.getEmail() + "'";
        Cursor cursor = db.rawQuery(sql_busca_usuario, null);
        while (cursor.moveToNext()){
            if(usuario.getEmail().equals(cursor.getString(cursor.getColumnIndex(helper.COLUNA_EMAIL)))){
                if (usuario.getEmail().equals(cursor.getString(cursor.getColumnIndex(helper.COLUNA_SENHA)))){
                    exito = 1;
                }else{
                    Log.d("ERRO", "senha incorreta ou não encontrada");
                    exito = 0;
                }

            }else{
                Log.d("ERRO", "Login não encontrado");
                exito = 0;
            }
        }
        if (exito == 1){
            return true;
        }else {
            return false;
        }
    }
}
