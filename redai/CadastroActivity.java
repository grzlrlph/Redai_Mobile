package com.example.redai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroActivity extends AppCompatActivity {

    EditText editTextNome_CadastroActivity, editTextUsername_CadastroActivity, editTextEmail_CadastroActivity, editTextSenha_CadastroActivity;
    ImageButton btnCriarConta_CadastroActivity;
    Button btnLogin_CadastroActivity;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        initComponents();

        btnCriarConta_CadastroActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("usuários");

                String nome = editTextNome_CadastroActivity.getText().toString();
                String username = editTextUsername_CadastroActivity.getText().toString();
                String email = editTextEmail_CadastroActivity.getText().toString();
                String senha = editTextSenha_CadastroActivity.getText().toString();

                if (username.isEmpty() || nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                    Toast.makeText(CadastroActivity.this,
                            "Por favor! Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }else{
                    Helper helper = new Helper(nome, username, email, senha);
                    reference.child(nome).setValue(helper);

                    Toast.makeText(CadastroActivity.this, "Seu cadastro foi realizade com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                }
            }
        });

        btnLogin_CadastroActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
            }
        });
    }

    private void initComponents() {
        editTextNome_CadastroActivity = findViewById(R.id.editTextNome_CadastroActivity);
        editTextUsername_CadastroActivity = findViewById(R.id.editTextUsername_CadastroActivity);
        editTextEmail_CadastroActivity = findViewById(R.id.editTextEmail_CadastroActivity);
        editTextSenha_CadastroActivity = findViewById(R.id.editTextSenha_CadastroActivity);
        btnCriarConta_CadastroActivity = findViewById(R.id.btnCadastrar_CadastroActivity);
        btnLogin_CadastroActivity = findViewById(R.id.btnEntrar_CadastroActivity);
    }
}