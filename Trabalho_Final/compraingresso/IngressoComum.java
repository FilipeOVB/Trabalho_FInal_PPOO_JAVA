package br.ufla.gac106.compraingresso;

import java.io.Serializable;

import br.ufla.gac106.adm.Atividade;

public class IngressoComum implements Ingresso, Serializable {
 
    private String nomeComprador;
    private String tipo;
    private Integer quantidade;
    private Atividade atividade;
    private Double precoTotalVenda;

    @Override
    public void setComprador(String nome) {
        this.nomeComprador = nome;
    }

    @Override
    public void setTipoIngresso(String tipo){
        this.tipo = tipo;
    }

    @Override
    public void setQuantidade(Integer quantidade){
        this.quantidade = quantidade;
    }


    @Override
    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    @Override
    public void setPrecoTotal(double precoTotalVenda) {

        precoTotalVenda *= taxaSistema;

        this.precoTotalVenda = precoTotalVenda;
    }

    @Override
    public String getTipoIngresso(){
        return tipo;
    }

    @Override
    public Integer getQuantidade() {
        return quantidade;
    }

    @Override
    public double getPrecoTotal() {
        return precoTotalVenda;
    }

    @Override
    public Atividade getAtividade() {
        return atividade;
    }

    @Override
    public String getComprador() {
        return nomeComprador;
    }

    @Override
    public double simulaPrecoTotal(double precoTotalVenda){

        precoTotalVenda *= taxaSistema;

        return precoTotalVenda;
    }
 
}


