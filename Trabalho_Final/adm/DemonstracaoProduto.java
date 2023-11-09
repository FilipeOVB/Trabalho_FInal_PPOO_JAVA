package br.ufla.gac106.adm;

public class DemonstracaoProduto extends Atividade{
      
    public DemonstracaoProduto(double preco, String nome, Evento evento, String centro, String data, Integer ingressosDisponiveis){
        super(preco, nome, evento, "Demonstracao", centro, data, ingressosDisponiveis);
    }
    
}