package com.example.trabalhointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DadosActivity extends AppCompatActivity {

    private TextView tvDepartamento, tvNomeSolicitante, tvTelefone, tvOcorrencia, tvUrgencia;
    private Button btAvancarChamado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_dados);

        // Iniciar os componentes
        tvDepartamento = findViewById(R.id.tvDepartamento);
        tvNomeSolicitante = findViewById(R.id.tvNomeSolicitante);
        tvTelefone = findViewById(R.id.tvTelefone);
        tvOcorrencia = findViewById(R.id.tvOcorrencia);
        tvUrgencia = findViewById(R.id.tvUrgencia);
        btAvancarChamado = findViewById(R.id.btAvancarChamado);

        // recupera os dados enviados via Intent
        Intent intent = getIntent();
        String departamento = intent.getStringExtra("DEPARTAMENTO");
        String nomeSolicitante = intent.getStringExtra("NOME_SOLICITANTE");
        String telefone = intent.getStringExtra("TELEFONE");
        String ocorrencia = intent.getStringExtra("OCORRENCIA");
        String urgencia = intent.getStringExtra("URGENCIA");

        // atribuindo os dados às TextViews
        tvDepartamento.setText("Departamento: " + departamento);
        tvNomeSolicitante.setText("Nome: " + nomeSolicitante);
        tvTelefone.setText("Telefone suporte: " + telefone);
        tvOcorrencia.setText("Ocorrência: " + ocorrencia);

        // verifica se o valor da urgência não é null e exibir
        if (urgencia != null) {
            tvUrgencia.setText("Urgência: " + urgencia);
        } else {
            tvUrgencia.setText("Urgência: Não especificada");
        }

        // botão de avançar
        btAvancarChamado.setOnClickListener(v -> {

            Intent opcoesIntent = new Intent(DadosActivity.this, OpcoesActivity.class);
            startActivity(opcoesIntent);
        });
    }
}









