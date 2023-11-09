package br.ufla.gac106.compraingresso;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.ufla.gac106.adm.AdmEvento;
import br.ufla.gac106.adm.Evento;
import br.ufla.gac106.adm.Sistema;


import java.util.ArrayList;

public class EscolhaEvento extends JanelaBase {

    private JTextArea areaTextoEventos;
    private JScrollPane areaTextoEventosComRolagem;

    private Sistema sistema;

    private AdmEvento admEvento;
    private ArrayList<Evento> eventos;

    public EscolhaEvento(Sistema sistema) {
        super("Eventos", "Escolha um evento", 500, 600, false, null, true, false);

            this.sistema = sistema;

            admEvento = sistema.getAdmEvento();
            eventos = admEvento.getListaEventos();

            atualizaAreaTextoEventos();
            setVisible(true);
        
    }

    @Override
    public JPanel criarPainelCentral() {
        criarComponentes();

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.add(areaTextoEventosComRolagem);

        return painelCentral;
    }

    public void criarComponentes() {
        areaTextoEventos = new JTextArea();
        areaTextoEventosComRolagem = new JScrollPane(areaTextoEventos);

        // impede que o usuário edite a área de texto do feed
        areaTextoEventos.setEditable(false);
    }

    public void atualizaAreaTextoEventos() {
        areaTextoEventos.setText("");
        areaTextoEventos.setText(getTextoExibicao());
    }

    public String getTextoExibicao() {
        String textoEventos = "";

        int aux = 0;

        for (Evento ev : eventos) {
            if (ev != null) {

                if(aux != ev.getIdEvento()){

                    textoEventos += "\n[" + ev.getIdEvento() + "]";
                    textoEventos += "\n     Evento: " + ev.getTema();

                    if (ev.getLocal() != null)
                        textoEventos += "\n     Local: " + ev.getLocal().getNomeLocal();

                    else {
                        textoEventos += "\n     Local: Indefinido";
                    }

                    if (ev.getListaExpositores().length > 0) {
                        textoEventos += "\n     Expositores: \n";
                        int nExp = 1;
                        for (int i = 0; i < ev.getListaExpositores().length; i++) {
                            textoEventos += "\n        " + nExp + ". " + ev.getListaExpositores()[i];
                            nExp++;
                        }
                    }
                    textoEventos += "\n_____________________________________________________________________\n";

                    aux = ev.getIdEvento();
                }

            }
        }
        return textoEventos;
    }

    @Override
    protected boolean aoAvancar() {
        String idEventoStr = JOptionPane.showInputDialog(null, "Digite o ID do evento:");
        if (idEventoStr != null && !idEventoStr.isEmpty()) {
            try {
                int idEventoInserido = Integer.parseInt(idEventoStr);
                
                Evento eventoEscolhido = verificarEventoExistente(idEventoInserido);

                
                if (eventoEscolhido == null) {
                    JOptionPane.showMessageDialog(null, "Evento não encontrado.");
                }

                else{     

                    EscolhaAtividade escolhaAtividade = new EscolhaAtividade(sistema, eventoEscolhido, eventoEscolhido.getTema(), this);
                    setVisible(false);
                }
                
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID de evento inválido!");
            }
        }

        return false;
    }

    private Evento verificarEventoExistente(int idEvento) {

        for (Evento ev : eventos) {
            if (ev != null && ev.getIdEvento() == idEvento) {
                return ev;
            }
        }
        return null;
    }

}
