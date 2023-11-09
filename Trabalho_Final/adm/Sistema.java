package br.ufla.gac106.adm;

import java.io.Serializable;
import java.util.ArrayList;

import br.ufla.gac106.compraingresso.Ingresso;

public class Sistema implements Serializable{

    private AdmAtividade admAtividade;
    private AdmEvento admEvento;
    private AdmLocal admLocal;
    private ArrayList<Ingresso> ingressos; 

    public Sistema(){

        admAtividade = new AdmAtividade();
        admEvento = new AdmEvento();
        admLocal = new AdmLocal();
        ingressos = new ArrayList<>();
    }

    public AdmEvento getAdmEvento() {
        return admEvento;
    }
    
    public AdmAtividade getAdmAtividade() {
        return admAtividade;
    }

    public AdmLocal getAdmLocal() {
        return admLocal;
    }
    
    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }
}
