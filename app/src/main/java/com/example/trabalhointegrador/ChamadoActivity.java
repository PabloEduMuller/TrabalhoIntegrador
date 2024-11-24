package com.example.trabalhointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChamadoActivity extends AppCompatActivity {

    private EditText edDepartamento, edNomeSolicitante, edOcorrencia;
    private RadioGroup rgUrgencia;
    private Button btEnviarChamado;
    String urgencia;

    // Instância do Firestore
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado);

        IniciarComponentes();

        btEnviarChamado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String departamento = edDepartamento.getText().toString();
                String nomeSolicitante = edNomeSolicitante.getText().toString();
                String ocorrencia = edOcorrencia.getText().toString();

                // Recuperar a urgência selecionada
                int selectedUrgenciaId = rgUrgencia.getCheckedRadioButtonId();

                if (selectedUrgenciaId == R.id.rbUrgenciaLeve) {
                    urgencia = "Leve";
                } else if (selectedUrgenciaId == R.id.rbUrgenciaMedio) {
                    urgencia = "Médio";
                } else if (selectedUrgenciaId == R.id.rbUrgenciaGrave) {
                    urgencia = "Grave";
                } else {
                    Snackbar.make(btEnviarChamado, "Selecione o nível de urgência", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(getColor(R.color.white))
                            .setTextColor(getColor(R.color.black))
                            .show();
                    return;
                }

                // Verificação dos campos
                if (departamento.isEmpty() || nomeSolicitante.isEmpty() || ocorrencia.isEmpty()) {
                    Snackbar.make(v, "Preencha todos os campos", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(getColor(R.color.white))
                            .setTextColor(getColor(R.color.black))
                            .show();
                } else {
                    // Enviar os dados para o Firestore
                    EnviarChamado(departamento, nomeSolicitante, ocorrencia);
                }
            }
        });
    }

    private void EnviarChamado(String departamento, String nomeSolicitante, String ocorrencia) {
        // Criar um objeto chamado (dados que serão enviados)
        Chamado chamado = new Chamado(departamento, nomeSolicitante, ocorrencia, urgencia);

        // Salvar no Firestore
        db.collection("chamados")
                .add(chamado)  // Adiciona os dados no Firestore
                .addOnSuccessListener(documentReference -> {
                    // sucesso, ir para a próxima activity
                    Intent intent = new Intent(ChamadoActivity.this, DadosActivity.class);
                    intent.putExtra("DEPARTAMENTO", departamento);
                    intent.putExtra("NOME_SOLICITANTE", nomeSolicitante);
                    intent.putExtra("TELEFONE", "(45) 99999-9999"); // Exemplo de telefone
                    intent.putExtra("OCORRENCIA", ocorrencia);
                    intent.putExtra("URGENCIA", urgencia);
                    startActivity(intent);
                })
                .addOnFailureListener(e -> {
                    // Erro ao salvar
                    Snackbar.make(findViewById(android.R.id.content), "Erro ao enviar chamado", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(getColor(R.color.black))
                            .show();
                });
    }

    private void IniciarComponentes() {
        edDepartamento = findViewById(R.id.edDepartamento);
        edNomeSolicitante = findViewById(R.id.edNomeSolicitante);
        edOcorrencia = findViewById(R.id.edOcorrencia);
        rgUrgencia = findViewById(R.id.rgUrgencia);
        btEnviarChamado = findViewById(R.id.btEnviarChamado);
    }
}
