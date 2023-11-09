package br.ufla.gac106.s2023_1.base.relatorios;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.ufla.gac106.adm.Sistema;
import br.ufla.gac106.adm.Evento;
import br.ufla.gac106.compraingresso.Ingresso;

public class ContabilizaAtividades implements ContabilizadorIngressos {

    private Evento evento;
    private Double valorTotal;

    private ArrayList<Ingresso> ingressos;
    private ArrayList<ContabilizadorIngressos> contabilizador;

    
    public ContabilizaAtividades(Sistema sistema){

        ingressos = sistema.getIngressos();

        buscaEvento();

        if(evento != null){

            contabilizador = new ArrayList<>();
            //contabilizador.add();

            GraficoIngressos grafico = new GraficoIngressos();
            
            grafico.exibir(evento.getTema(), contabilizador, false);
        }
    }

    public void buscaEvento(){

        String nome = JOptionPane.showInputDialog(null, "Digite o nome do evento:");

        for(Ingresso in : ingressos){
            if(in.getAtividade().getEvento().getTema().equals(nome)){
                evento = in.getAtividade().getEvento();
            }
        }
        
    }

    public void criaGrafico(){

    }

    @Override
    public int quantidadeIngressos(){

        int quantidade = 0;

        for(Ingresso in : ingressos){
            if(in.getAtividade().getEvento().equals(evento)){
                quantidade += in.getQuantidade();
            }
        }
        return quantidade;
    }


    @Override
    public String identificador(){
        return evento.getTema();
    }

    @Override
    public double valorTotal(){

         for(Ingresso in : ingressos){
            if(in.getAtividade().getEvento().equals(evento)){
                valorTotal += in.getPrecoTotal();
            }
        }
        return valorTotal;
    }
}
