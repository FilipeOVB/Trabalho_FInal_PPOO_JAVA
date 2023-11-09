package br.ufla.gac106.compraingresso;

import br.ufla.gac106.adm.AdmAtividade;
import br.ufla.gac106.adm.Atividade;
import br.ufla.gac106.adm.Dados;
import br.ufla.gac106.adm.Evento;
import br.ufla.gac106.adm.Sistema;

import javax.swing.BoxLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.util.ArrayList;

public class EscolhaAtividade extends JanelaBase {

    private JTextArea areaTextoAtividades;
    private JScrollPane areaTextoAtividadesComRolagem;

    private JanelaBase telaEscolhaEventos;

    private Evento eventoEscolhido;

    private AdmAtividade admAtividade;
    private ArrayList<Atividade> atividades;

    private Sistema sistema;


    public EscolhaAtividade(Sistema sistema, Evento eventoEscolhido,String nomeEvento, JanelaBase telaEscolhaEvento) {
        super("Evento: " + nomeEvento, "Escolha uma atividade", 500, 600, true, telaEscolhaEvento, true, true);

        this.eventoEscolhido = eventoEscolhido;
        this.telaEscolhaEventos = telaEscolhaEvento;
        this.sistema = sistema;

        admAtividade = sistema.getAdmAtividade();
        atividades = admAtividade.getAtividades();

        atualizaAreaTextoAtividades();

        setVisible(true);
    }

    @Override
    public JPanel criarPainelCentral() {
        criarComponentes();

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.add(areaTextoAtividadesComRolagem);
        
        return painelCentral;
    }

    public void criarComponentes() {
        areaTextoAtividades = new JTextArea();
        areaTextoAtividadesComRolagem = new JScrollPane(areaTextoAtividades);

        // impede que o usuário edite a área de texto do feed
        areaTextoAtividades.setEditable(false);
    }

    public String getTextoExibicao() {
        String textoAtividades = "";

        for (Atividade at : atividades) {
            if (at != null && at.getEvento().equals(eventoEscolhido)) {

                textoAtividades += "\n[" + at.getIdAtividade() + "]";
                textoAtividades += "\n     Atividade: " + at.getNome();
                textoAtividades += "\n     Tipo: " + at.getTipo();
                
                if (at.getCentro() == null) {
                     textoAtividades += "\n   Local: Indefinido";
                } else {
                     textoAtividades += "\n     Local: " + at.getCentro();
                }
                if (at.getIngressosDisponiveis() == 0) {
                    textoAtividades += "\n     Não há ingressos disponíveis para esta atividade";
                }else{
                    textoAtividades += "\n     Ingressos Disponíveis: " + at.getIngressosDisponiveis();
                }
                textoAtividades += "\n     Data: " + at.getData();
                textoAtividades += "\n     Preço: " + at.getPrecoIngresso();

                textoAtividades += "\n_____________________________________________________________________\n";
            }
        }
        return textoAtividades;
    }

    public void atualizaAreaTextoAtividades() {
        areaTextoAtividades.setText("");
        areaTextoAtividades.setText(getTextoExibicao());
    }

    @Override
    protected boolean aoAvancar() {
        String idAtivStr = JOptionPane.showInputDialog(null, "Digite o ID da atividade:");
        if (idAtivStr != null && !idAtivStr.isEmpty()) {
            try {
                int idAtivInserida = Integer.parseInt(idAtivStr);
                Atividade atividadeEscolhida = verificarAtividadeExistente(idAtivInserida);

                
                if (atividadeEscolhida == null) {
                    JOptionPane.showMessageDialog(null, "Atividade não encontrada.");
                } 
                else if (atividadeEscolhida.getIngressosDisponiveis() == 0) {
                    JOptionPane.showMessageDialog(null, "Não há ingressos disponíveis para esta atividade.");
                }
                else {
                    EscolhaIngresso escolhaIngresso = new EscolhaIngresso(sistema, atividadeEscolhida);
                    atualizaAreaTextoAtividades();
            }
                
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "ID de atividade inválido!");
            }
        }

        return false;
    }

    private Atividade verificarAtividadeExistente(int idAtividade) {

        for (Atividade at : atividades) {
            if (at != null && at.getIdAtividade() == idAtividade) {
                return at;
            }
        }
        return null;
    }

    @Override
    protected boolean aoVoltar() {
        setVisible(false);
        telaEscolhaEventos.setVisible(true);
        
        return true;
    }

    @Override
    protected boolean aoFinalizar() {
        setVisible(false);
        
        Dados.salvaArquivoBin(sistema);
        
        return true;
    }

}
