package com.example.redai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginUsuario extends AppCompatActivity {
    EditText editTextEmail_LoginActivity, editTextSenha_LoginActivity;
    Button btnCadastro_LoginActivity;
    ImageButton  btnEntrar_LoginActivity;
    DAOUsuario daoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        daoUsuario = new DAOUsuario(getApplicationContext());
        initComponents();

        intentCadastro();

        btnEntrar_LoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail_LoginActivity.getText().toString();
                String senha = editTextSenha_LoginActivity.getText().toString();

                if (email.isEmpty() || senha.isEmpty()){
                    Toast.makeText(LoginUsuario.this,
                            "Por favor! Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }else {
                    if (daoUsuario.validaLogin(email, senha)){
                        Toast.makeText(LoginUsuario.this,
                                "Login realizado com êxito. Seja bem-vindo ao Redaí!",
                                Toast.LENGTH_SHORT).show();
                        Intent loginHomepage = new Intent(LoginUsuario.this, Homepage.class);
                        startActivity(loginHomepage);
                    }else {
                        Toast.makeText(LoginUsuario.this,
                                "Email ou senha incorretos! Verifique e tente novamente.",
                                Toast.LENGTH_SHORT).show();
                        editTextEmail_LoginActivity.getText().clear();
                        editTextSenha_LoginActivity.getText().clear();
                    }
                }
            }
        });
    }

    private void intentCadastro() {
        btnCadastro_LoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoginCadastro = new Intent(LoginUsuario.this, CadastroUsuario.class);
                startActivity(intentLoginCadastro);
            }
        });
    }



    private void initComponents() {
        editTextEmail_LoginActivity = findViewById(R.id.editTextEmail_LoginActivity);
        editTextSenha_LoginActivity = findViewById(R.id.editTextSenha_LoginActivity);
        btnCadastro_LoginActivity = findViewById(R.id.btnCadastrar_LoginActivity);
        btnEntrar_LoginActivity = findViewById(R.id.btnEntrar_LoginActivity);

    }
}