package br.ufla.gac106.adm;

import java.io.Serializable;

public class Atividade implements Serializable {

    private double precoIngresso;    

    private Evento evento;
    private String nome;
    private String tipo;
    private String centro;
    private String data;
    private Integer ingressosDisponiveis;

    private int idAtividade;
    static private int ultimaAtividadeCriada = 000;

    public Atividade(double preco, String nome, Evento evento, String tipo, String centro, String data, Integer ingressosDisponiveis ){
        
        this.precoIngresso = preco;
        this.nome = nome;
        this.evento = evento;
        this.tipo = tipo;
        this.centro = centro;
        this.data = data;
        this.ingressosDisponiveis = ingressosDisponiveis;

        ultimaAtividadeCriada++;
        idAtividade = ultimaAtividadeCriada;
    }
    
    public double getPrecoIngresso() {
        return precoIngresso;
    }

    public Evento getEvento() {
        return evento;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCentro() {
        return centro;
    }

    public String getData() {
        return data;
    }

    public Integer getIngressosDisponiveis() {
        return ingressosDisponiveis;
    }

    public boolean compraIngresso(int quantidade) {
        if((ingressosDisponiveis >= quantidade) && (quantidade > 0)){
            ingressosDisponiveis-= quantidade;
            return true;
        }
        else{
            return false;
        }
    }

    public Integer getIdAtividade(){
        return idAtividade;
    }
}
