package br.ufla.gac106.adm;

import java.io.Serializable;
import java.util.ArrayList;

public class AdmLocal implements Serializable {
 
    private ArrayList<Local> locais = new ArrayList<>();
        
    protected void cadastrarLocal(String nome, String endereco, String capacidade) {

        Local l = new Local(nome, endereco, capacidade); // Cria objeto de Local para adicionar ao ArrayList de loais
        locais.add(l);
    }

    protected String listarLocais() {
        if(locais.size() > 0){ // Verifica se o ArrayList esta vazio

            String nomeLocal = ""; // Armazenara os nomes dos locais
            Integer i=0;

            for(Local lo : locais){
                i++;

                if(lo.getNomeLocal() != null){  // para cada "lo" presente em locais, se o nome do local for diferente de null acrescenta o nome na String nomeLocal

                    nomeLocal +="\n" + i + ". " + lo.getNomeLocal();
                }
        
            }
            return nomeLocal;
        } 
        return "\nLista Vazia."; 
    }

    protected String detalharLocal(String nome) {

        String detalhesLocal = "\n"; // String q apresentara toda a descricao do evento
        Local local = buscarLocal(nome);

        if (local != null) { // Se local existir pegue os atributos dele e adiciona na String
            detalhesLocal += "Nome: " + local.getNomeLocal() + "\n";
            detalhesLocal += "Cidade: " + local.getCidade() + "\n";
            detalhesLocal += "Capacidade: " + local.getCapacidade();
        } else {
            detalhesLocal = "\nLocal não encontrado.\n";
        }

        return detalhesLocal;
    }
    public Local buscarLocal(String nome) { // Busca Local por nome

        for (Local lo : locais) {

            if (lo != null && lo.getNomeLocal().equals(nome)) {
                return lo; 
            }
        }
        return null;
    }

    protected String removerLocal(String nome){ // Remove Local por nome

        Local local = buscarLocal(nome);

        if( local != null){
            
            locais.remove(local);
            return "\nLocal removido com sucesso";
        }

        else{
            return "\nLocal não encontrado";
        }
    }
    public ArrayList<Local> getListaLocais(){
        return locais;
    }
}