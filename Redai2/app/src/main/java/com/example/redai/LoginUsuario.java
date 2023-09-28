package com.example.redai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginUsuario extends AppCompatActivity {
    EditText editTextEmail_LoginActivity, editTextSenha_LoginActivity;
    Button btnCadastro_LoginActivity;
    ImageButton btnVoltar_LoginActivity, btnEntrar_LoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        initComponents();
        telaInicialIntent();
        intentCadastro();
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

    private void telaInicialIntent() {
        btnVoltar_LoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initComponents() {
        editTextEmail_LoginActivity = findViewById(R.id.editTextEmail_LoginActivity);
        editTextSenha_LoginActivity = findViewById(R.id.editTextSenha_LoginActivity);
        btnCadastro_LoginActivity = findViewById(R.id.btnCadastrar_LoginActivity);
        btnEntrar_LoginActivity = findViewById(R.id.btnEntrar_LoginActivity);
        btnVoltar_LoginActivity = findViewById(R.id.btnVoltar_LoginActivity);
    }
}