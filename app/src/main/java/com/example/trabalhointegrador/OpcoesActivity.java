package com.example.trabalhointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class OpcoesActivity extends AppCompatActivity {

    private Button btSair;
    private ImageButton ibHome;
    private ImageView ibDadoschamado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_ou_ligar);

        // Inicializando os botões
        btSair = findViewById(R.id.btSair);
        ibHome = findViewById(R.id.ibHome);


        // Ação para o botão "Sair" - Vai para a tela de login
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpcoesActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Ação para o botão "Home" - Vai para a tela de ChamadoActivity
        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpcoesActivity.this, ChamadoActivity.class);
                startActivity(intent);
            }
        });


    }
}
