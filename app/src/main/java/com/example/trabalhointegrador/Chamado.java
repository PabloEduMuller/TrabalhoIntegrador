package com.example.trabalhointegrador;

public class Chamado {
    private String departamento;
    private String nomeSolicitante;
    private String ocorrencia;
    private String urgencia;

    public Chamado() {


    }

    public Chamado(String departamento, String nomeSolicitante, String ocorrencia, String urgencia) {
        this.departamento = departamento;
        this.nomeSolicitante = nomeSolicitante;
        this.ocorrencia = ocorrencia;
        this.urgencia = urgencia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public String getUrgencia() {
        return urgencia;
    }
}
