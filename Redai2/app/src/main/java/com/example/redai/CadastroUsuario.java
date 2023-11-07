package com.example.redai;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CadastroUsuario extends AppCompatActivity {

    Button  btnLogin;
    ImageButton btnCadastrar;
    EditText editTextNome, editTextUsername, editTextEmail, editTextSenha;
    DAOUsuario daoUsuario = new DAOUsuario(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        vinculacao();
        LoginIntent();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String nome = editTextNome.getText().toString();
                String email = editTextEmail.getText().toString();
                String senha = editTextSenha.getText().toString();

                if (username.isEmpty() || nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                    Toast.makeText(CadastroUsuario.this,
                            "Por favor! Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }else{
                    Usuario usuario = new Usuario(username, nome, email, senha, null);
                    if (daoUsuario.cadastrarUsuario(usuario)){
                        Toast.makeText(CadastroUsuario.this,
                                "Cadastro realizado com êxito! Seja bem-vindo ao Redaí!",
                                Toast.LENGTH_SHORT).show();
                        Intent cadastroHomepage = new Intent(CadastroUsuario.this, Homepage.class);
                    }else{
                        Toast.makeText(CadastroUsuario.this,
                                "Houve uma falha na realização do seu cadastro. Tente novamente!",
                                Toast.LENGTH_SHORT).show();
                        editTextUsername.getText().clear();
                        editTextNome.getText().clear();
                        editTextEmail.getText().clear();
                        editTextSenha.getText().clear();
                    }
                }
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
        btnCadastrar = findViewById(R.id.btnCadastrar_CadastroActivity);
        editTextNome = findViewById(R.id.editTextNome_CadastroActivity);
        editTextUsername = findViewById(R.id.editTextUsername_CadastroActivity);
        editTextEmail = findViewById(R.id.editTextEmail_CadastroActivity);
        editTextSenha = findViewById(R.id.editTextSenha_CadastroActivity);
    }
}