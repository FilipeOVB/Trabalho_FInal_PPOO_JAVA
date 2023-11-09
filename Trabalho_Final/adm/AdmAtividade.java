package br.ufla.gac106.adm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class AdmAtividade implements Serializable{
    
    private ArrayList<Atividade> atividades = new ArrayList<>(); 
    
    protected void cadastrarAtividade(double preco, String nome, Evento evento, String tipo, String centro, String data, Integer ingressosDisponiveis){

        Atividade a = new Atividade(preco, nome, evento, tipo, centro, data, ingressosDisponiveis); // Cria objeto de Atividade para adicionar ao ArrayList de atividades
        atividades.add(a);
    }
    
    
    protected String listarAtividades(){

        if(atividades.size() > 0){ // Verifica se o ArrayList esta vazio

            String atividadeNome = ""; // Armazenara os nomes das atividades
            Integer i=0;

            for(Atividade at : atividades){
                i++;

                if(at.getNome() != null){ 

                    atividadeNome +="\n" + i + ". " + at.getNome();
                }
                
            }
            return atividadeNome;
        } 
        return "\nLista Vazia."; 
    }

    protected String detalharAtividade(String nome){

        String detalhesAtiv = "\n"; // String q apresentara toda a descricao do evento
        Atividade ativ = buscarAtividade(nome);

        if (ativ != null) { // Se atividade existir adciona os atributos dela na String
            detalhesAtiv += "Nome: " + ativ.getNome() + "\n";
            detalhesAtiv += "Evento: " + ativ.getEvento().getTema() + "\n";
            detalhesAtiv += "Tipo: " + ativ.getTipo() + "\n";               
            detalhesAtiv += "Local: " + ativ.getCentro()+ "\n";
            detalhesAtiv += "Data: " + ativ.getData()+ "\n";
            detalhesAtiv += "Ingressos Disponíveis: " + ativ.getIngressosDisponiveis()+ "\n";
            detalhesAtiv += "Preço: $ " + ativ.getPrecoIngresso()+ "\n";
        } else {
            detalhesAtiv = "\nErro: Atividade não encontrada.\n";
        }

        return detalhesAtiv;
    }
    public Atividade buscarAtividade(String nome) { // Buscar Atividade por nome

        for (Atividade at : atividades) {

            if (at != null && at.getNome().equals(nome)) {
                return at; 
            }
        }
        return null;
    }
    protected String removerAtividade(String nome){ // Remover Atividade por nome

        Atividade ativ = buscarAtividade(nome);

        if(ativ != null){
            
            atividades.remove(ativ);
            return "\nAtividade removida com sucesso.";
        }

        else{
            return "\nErro: Atividade não encontrada.";
        }
    }

    public ArrayList<Atividade> getAtividades(){
        return atividades;
    } 

    
}
