package br.ufla.gac106.adm;

import java.io.Serializable;
import java.util.ArrayList;

public class AdmEvento implements Serializable {

    private ArrayList<Evento> eventos = new ArrayList<>();

    protected void cadastrarEvento(String tema, String[] listaExpositores, Local local) {

        Evento e = new Evento(tema, listaExpositores, local); // Cria objeto de Evento para adicionar ao ArrayList de
                                                              // eventos
        eventos.add(e);
    }

    public String listarEventos() {

        if (eventos.size() > 0) { // Verifica se o ArrayList esta vazio

            String eventoTema = ""; // Armazenara os nomes dos eventos
            Integer i = 0;

            for (Evento ev : eventos) {
                i++;

                if (ev.getTema() != null) { // para cada "ev" presente em eventos, se o tema do evento for diferente de
                                            // null acrescenta o nome na String eventoTema

                    eventoTema += "\n" + i + ". " + ev.getTema();
                }

            }
            return eventoTema;
        }
        return "\nLista Vazia.";
    }

    public String detalharEvento(String tema) {

        String detalhesEvento = "\nExpositor(es): "; // String q apresentara toda a descricao do evento
        String[] listaExpositores;

        Evento evento = buscarEvento(tema);

        if (evento != null) { // Verifica se evento existe

            listaExpositores = evento.getListaExpositores(); // pega a lista de expositores de evento

            if (listaExpositores.length != 0) { // Verifica se a lista possui expositores
                detalhesEvento += "\n";
                for (int i = 0; i < evento.getListaExpositores().length; i++) {

                    detalhesEvento += "\n   " + (i + 1) + ". " + listaExpositores[i]; // Adciona os expositores na
                                                                                      // String
                }
            } else { // Se não tiver expositores adciona Nenhum na String
                detalhesEvento += "Nenhum";
            }

            if (evento.getLocal() != null) { // Se o evento possui local adciona o local na String e a retorna
                return detalhesEvento += "\n\nLocal do evento: " + evento.getLocal().getNomeLocal();
            } else { // Se o evento não possui local adciona Indefinido na String e a retorna
                return detalhesEvento += "\n\nLocal do evento: Indefinido";
            }

        }

        else {
            return "Evento não encontrado.\n";
        }

    }

    public Evento buscarEvento(String tema) { // Busca Eventos por tema

        for (Evento ev : eventos) {

            if (ev != null && ev.getTema().equals(tema)) {
                return ev;
            }
        }
        return null;
    }

    public String removerEvento(String tema) { // Remove Eventos por tema

        Evento evento = buscarEvento(tema);

        if (evento != null) {

            eventos.remove(evento);
            return "\nEvento removido com sucesso";
        }

        else {
            return "\nEvento não encontrado";
        }
    }

    public ArrayList<Evento> getListaEventos(){
        return eventos;
    }
}
