package com.example.redai;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class DAOUsuario {
    private static DBHelper helper;

    public static boolean cadastrarUsuario(Usuario usuario){
        //instancia do banco e metodo pra sobrescrever
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DBHelper.COLUNA_USERNAME, usuario.getUsername());
        valores.put(DBHelper.COLUNA_NOME, usuario.getNome());
        valores.put(DBHelper.COLUNA_EMAIL, usuario.getEmail());
        valores.put(DBHelper.COLUNA_SENHA, usuario.getSenha());

        ByteArrayOutputStream stream = new ByteArrayOutputStream(); //array de bytes
        //comprime a foto, transforma em um array de bytes e ajusta a qualidade
        usuario.getFotoPerfil().compress(Bitmap.CompressFormat.PNG, 100, stream);
        valores.put(DBHelper.COLUNA_FOTO_PERFIL, stream.toByteArray());

        //realiza o cadastro no banco
        long id = db.insert(DBHelper.TABELA_USUARIO, null, valores);
        db.close(); // fecha o banco

        if (id != -1){
            return true;
        }else {
            return false;
        }
    }

    public boolean atualizarUsuario(Usuario usuario) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(DBHelper.COLUNA_USERNAME, usuario.getUsername());
        valores.put(DBHelper.COLUNA_NOME, usuario.getNome());
        valores.put(DBHelper.COLUNA_EMAIL, usuario.getEmail());
        valores.put(DBHelper.COLUNA_SENHA, usuario.getSenha());
        ByteArrayOutputStream stream = new ByteArrayOutputStream(); //array de bytes
        //comprime a foto, transforma em um array de bytes e ajusta a qualidade
        usuario.getFotoPerfil().compress(Bitmap.CompressFormat.PNG, 100, stream);
        valores.put(DBHelper.COLUNA_FOTO_PERFIL, stream.toByteArray());

        String valuesWhere[] = new String[1];
        valuesWhere[0] = String.valueOf(usuario.getUsername());

        long qtd = db.update(DBHelper.TABELA_USUARIO, valores, "username= ?", valuesWhere);
        db.close();

        if (qtd > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validaLogin (String email, String senha){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                + DBHelper.TABELA_USUARIO
                + " WHERE "
                + DBHelper.COLUNA_EMAIL+ " = ?"
                + "AND " + DBHelper.COLUNA_SENHA + " = ?", new String[]{email, senha});
        if (cursor.getCount()>0){
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }
    }

}
