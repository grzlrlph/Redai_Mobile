package com.example.redai;

import android.graphics.Bitmap;

public class Usuario {
    private String nome, username, email, senha;
    private Bitmap fotoPerfil;

    public Usuario() {
    }

    public Usuario(String nome, String username, String email, String senha, Bitmap fotoPerfil) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.fotoPerfil = fotoPerfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Bitmap getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Bitmap fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
