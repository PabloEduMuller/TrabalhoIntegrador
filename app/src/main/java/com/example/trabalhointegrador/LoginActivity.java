package com.example.trabalhointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LoginActivity extends AppCompatActivity {

    private EditText edEmail, edSenha;
    private Button btLogar, btNrTelefone;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        IniciarComponentes();

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String senha = edSenha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Preencha todos os campos", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(getColor(R.color.white));
                    snackbar.setTextColor(getColor(R.color.black));
                    snackbar.show();
                } else {
                    LoginUsuario(email, senha);
                }
            }
        });
    }

    private void LoginUsuario(String email, String senha){
        // usando o firebase pra autenticar o usuário
        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Se o login der certo, ir para a ChamadoActivity
                            Intent intent = new Intent(LoginActivity.this, ChamadoActivity.class);
                            startActivity(intent);
                            finish();  // fecha a activity  pra nao pesar
                        } else {
                            String erro;
                            try {
                                throw task.getException();
                            }catch (FirebaseAuthWeakPasswordException e){
                                erro = "Digite senha com no minimo 6 caracteres";
                            }catch (FirebaseAuthUserCollisionException e){
                                erro = "Esta conta ja existe";

                            }catch (FirebaseAuthInvalidCredentialsException e){
                                erro = "Email ou senha invalidos";
                            }catch(Exception e){
                                erro = "Erro ao cadastrar usuario";
                            }

                        }
                    }
                });
    }

    private void IniciarComponentes() {
        edEmail = findViewById(R.id.edEmail);
        edSenha = findViewById(R.id.edSenha);
        btLogar = findViewById(R.id.btLogar);

    }
}
