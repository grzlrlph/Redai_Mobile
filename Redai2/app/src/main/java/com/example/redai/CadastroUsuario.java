package com.example.redai;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class CadastroUsuario extends AppCompatActivity {

    Button  btnLogin;
    ImageButton btnVoltar, btnCadastrar;
    EditText editTextNome, editTextUsername, editTextEmail, editTextSenha;
    UsuarioRepositorio repositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        vinculacao();
        LoginIntent();
        TelaInicialIntent();
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String nome = editTextNome.getText().toString();
                String email = editTextEmail.getText().toString();
                int senha = Integer.parseInt(editTextSenha.getText().toString());

                Usuario usuario = new Usuario(username, nome, email, senha, null);
                repositorio.cadastrarUsuario(usuario);
            }
        });

    }

    private void TelaInicialIntent() {
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void LoginIntent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(CadastroUsuario.this, LoginUsuario.class);
                startActivity(intentLogin);
            }
        });
    }
    private void vinculacao() {
        btnLogin = findViewById(R.id.btnEntrar_CadastroActivity);
        btnVoltar = findViewById(R.id.btnVoltar_CadastroActivity);
        btnCadastrar = findViewById(R.id.btnCadastrar_CadastroActivity);
        editTextNome = findViewById(R.id.editTextNome_CadastroActivity);
        editTextUsername = findViewById(R.id.editTextUsername_CadastroActivity);
        editTextEmail = findViewById(R.id.editTextEmail_CadastroActivity);
        editTextSenha = findViewById(R.id.editTextSenha_CadastroActivity);
    }
}