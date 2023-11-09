package br.ufla.gac106.adm;

import java.io.Serializable;

public class Local implements Serializable{

    private String nome;
    private String cidade;
    private String capacidade;
    
    public Local(String nome, String cidade, String capacidade){
        this.nome = nome;
        this.cidade = cidade;
        this.capacidade = capacidade;
    }

    public String getNomeLocal(){
        return nome;
    }

    public String getCidade(){
        return cidade;
    }

    public String getCapacidade(){
        return capacidade;
    }

}