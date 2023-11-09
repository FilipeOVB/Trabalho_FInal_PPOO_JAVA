package br.ufla.gac106.adm;

import java.io.Serializable;

public class Evento implements Serializable{
    
    private String tema;
    private String[] listaExpositores = new String[0];
    private Local local;
    
    private int idEvento;
    static private int ultimoEventoCriado = 000;
    
    public Evento(String tema, String[] listaExpositores, Local local){ // Criar tratativa para eventos sem local
        this(tema, listaExpositores);
        this.local = local;

        ultimoEventoCriado++;
        idEvento = ultimoEventoCriado;
    }

    public Evento(String tema, String[] listaExpositores){ // construtor para iniciar evento sem local
        this.tema = tema;                                  
        this.listaExpositores = listaExpositores;
    }

    public String getTema(){
        return tema;
    }

    public String[] getListaExpositores(){
        return listaExpositores;
    }

    public Local getLocal(){
        return local;
    }

    public Integer getIdEvento(){
        return idEvento;
    }
}
