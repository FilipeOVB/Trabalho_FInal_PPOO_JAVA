package br.ufla.gac106.compraingresso;

import java.io.Serializable;

import br.ufla.gac106.adm.Atividade;

public class IngressoComDesconto implements  Ingresso, Serializable {
    
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
    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    @Override
    public void setPrecoTotal(double precoTotalVenda) {

        precoTotalVenda *= 0.8;
        precoTotalVenda *= taxaSistema;

        this.precoTotalVenda = precoTotalVenda;
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
    public void setTipoIngresso(String tipo){
        this.tipo = tipo;
    }

    @Override
    public Integer getQuantidade() {
        return quantidade;
    }
        
    @Override
    public void setQuantidade(Integer quantidade){
        this.quantidade = quantidade;
    }

    @Override
    public String getTipoIngresso(){
        return tipo;
    }

    @Override
    public String getComprador() {
        return nomeComprador;
    }

    @Override
    public double simulaPrecoTotal(double precoTotalVenda){

        precoTotalVenda *= 0.8;
        precoTotalVenda *= taxaSistema;

        return precoTotalVenda;
    }

}
