package br.ufla.gac106.adm;

public class PalestraTecnica extends Atividade{
    public PalestraTecnica(double preco, String nome, Evento evento, String tipo, String centro, String data, Integer ingressosDisponiveis){
        super(preco, nome, evento, "Palestra", centro, data, ingressosDisponiveis);
    }
}
