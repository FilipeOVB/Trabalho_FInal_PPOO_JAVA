package br.ufla.gac106.compraingresso;

import java.util.ArrayList;

import br.ufla.gac106.adm.Atividade;

public abstract interface Ingresso {
    
    public static Double taxaSistema = 1.05;

    void setComprador(String nomeComprador);
    String getComprador();

    void setTipoIngresso(String tipo);
    String getTipoIngresso();

    void setQuantidade(Integer quantidade);
    Integer getQuantidade();

    void setAtividade(Atividade atividade);
    Atividade getAtividade();

    void setPrecoTotal(double precoTotalVenda);
    double getPrecoTotal();

    double simulaPrecoTotal(double precoTotalVenda);



}


