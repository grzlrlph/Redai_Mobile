package com.example.redai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    //criando componentes
    EditText editTextEmail_LoginActivity, editTextSenha_LoginActivity;
    ImageButton btnLogar_LoginActivity;
    Button btnCadastrar_LoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inicia os componentes
        initComponentsLoginActivity();

        //checa o email e a senha
        btnLogar_LoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateEmail() | !validateSenha()){

                }else{
                    //verifica no banco
                    validaUsuario();
                }
            }
        });

    }

    public boolean validateEmail(){
        String val = editTextEmail_LoginActivity.getText().toString();
        if (val.isEmpty()){
            editTextEmail_LoginActivity.setError("O Email precisa ser preenchido");
            return false;
        }else{
            return true;
        }
    }

    public boolean validateSenha(){
        String val = editTextSenha_LoginActivity.getText().toString();
        if (val.isEmpty()){
            editTextSenha_LoginActivity.setError("O Email precisa ser preenchido");
            return false;
        }else{
            return true;
        }
    }

    public void validaUsuario(){
        String loginEmail = editTextEmail_LoginActivity.getText().toString().trim();
        String loginSenha = editTextSenha_LoginActivity.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("usuários");
        Query validaUsuarioDB = reference.orderByChild("nome").equalTo(loginEmail);

        validaUsuarioDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    String senhaFromDB = snapshot.child(loginEmail).child("senha").getValue(String.class);

                    if(!Objects.equals(senhaFromDB, loginSenha)){
                        startActivity(new Intent(LoginActivity.this, Homepage.class));
                    }else{
                        editTextSenha_LoginActivity.setError("Senha incorreta!");
                        editTextSenha_LoginActivity.requestFocus();
                    }

                }else{
                    editTextEmail_LoginActivity.setError("Email inválido!");
                    editTextEmail_LoginActivity.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initComponentsLoginActivity() {
        editTextEmail_LoginActivity = findViewById(R.id.editTextEmail_LoginActivity);
        editTextSenha_LoginActivity = findViewById(R.id.editTextSenha_LoginActivity);
        btnLogar_LoginActivity = findViewById(R.id.btnEntrar_LoginActivity);
        btnCadastrar_LoginActivity = findViewById(R.id.btnCadastrar_LoginActivity);
    }
}