package br.ufla.gac106.compraingresso;

import br.ufla.gac106.adm.Atividade;
import br.ufla.gac106.adm.Sistema;

import javax.swing.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class EscolhaIngresso{

    private Atividade atividadeEscolhida;
    private ArrayList<Atividade> atividades;

    private JComboBox<String> SelecionaTipo;
    private JSpinner SelecionaQTD;

    private Sistema sistema;
    private Ingresso ingresso;

    private ArrayList<Ingresso> ingressos; 

    public EscolhaIngresso(Sistema sistema, Atividade atividadeEscolhida){

        this.atividadeEscolhida = atividadeEscolhida;
        this.atividades = atividades;

        ingressos = sistema.getIngressos();

      criarComponentesDeIngresso();
    }
  
    private void criarComponentesDeIngresso(){

        criaCaixasSelecao();

        JLabel titulo = new JLabel("Atividade: " + atividadeEscolhida.getNome());
        JLabel qtdDisponivel = new JLabel("Ingressos Disponíveis: " + atividadeEscolhida.getIngressosDisponiveis());
        JLabel preco = new JLabel("Preço: $ " + atividadeEscolhida.getPrecoIngresso());

        JLabel tipo = new JLabel("Tipo Ingresso: ");
        JLabel qtd = new JLabel("Quantidade: ");

        //////////////////////////////////////////////////////////////////////////////

        // JPanel de Textos

        // Painel que armazena o titulo que será exibido 
        JPanel boxTitulo = new JPanel();
        boxTitulo.setLayout(new BoxLayout(boxTitulo, BoxLayout.X_AXIS));      
        boxTitulo.add(titulo);

        // Painel que armazena o texto "Tipo de ingresso:" que será exibido
        JPanel boxLabelTipo = new JPanel();
        boxLabelTipo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        boxLabelTipo.add(tipo);

        // Painel que armazena o texto "Quantidade: " que será exibido
        JPanel boxLabelQtd = new JPanel();
        boxLabelQtd.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        boxLabelQtd.add(qtd);

        //////////////////////////////////////////////////////////////////////////////

        // JPanel das caixas de seleção

        // Caixa de selecao do tipo do ingresso
        JPanel BoxSelecionaTipo = new JPanel();
        BoxSelecionaTipo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        BoxSelecionaTipo.add(SelecionaTipo);

        // Painel que armazena a caixa que define a quantidade de ingressos que o usuario deseja
        JPanel BoxSelecionaQTD = new JPanel();
        BoxSelecionaQTD.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        BoxSelecionaQTD.add(SelecionaQTD);

        //////////////////////////////////////////////////////////////////////////////

        // JPanel para agrupar os outros paineis por colunas

        // Painel que representara a primeira "coluna" da tela
        JPanel coluna1 = new JPanel();
        coluna1.setLayout(new BoxLayout(coluna1, BoxLayout.Y_AXIS));

        coluna1.add(boxLabelTipo);
        coluna1.add(Box.createVerticalStrut(10));   // Espaçamento entre as colunas
        coluna1.add(BoxSelecionaTipo);

        // Painel que representara a segunda "coluna" da tela
        JPanel coluna2 = new JPanel();
        coluna2.setLayout(new BoxLayout(coluna2,BoxLayout.Y_AXIS));

        coluna2.add(boxLabelQtd);
        coluna2.add(Box.createVerticalStrut(10)); // Espaçamento entre as colunas
        coluna2.add(BoxSelecionaQTD);

        //////////////////////////////////////////////////////////////////////////////

        // JPanel de textos que exibe no começo da tela

        //
        JPanel textoIngressos = new JPanel();
        textoIngressos.setLayout(new FlowLayout(FlowLayout.LEFT));
        textoIngressos.add(qtdDisponivel);

        JPanel textoPreco = new JPanel();
        textoPreco.setLayout(new FlowLayout(FlowLayout.LEFT));
        textoPreco.add(preco);

        // JPanel que agrupa os textos em um único painel

        JPanel telaDescricao = new JPanel();
        telaDescricao.setLayout(new BoxLayout(telaDescricao, BoxLayout.Y_AXIS));

        telaDescricao.add(textoIngressos);
        telaDescricao.add(textoPreco);

        //////////////////////////////////////////////////////////////////////////////

        // JPanel que agrupa os paineis coluna1 e coluna2 em um único painel


        JPanel telaComponentes = new JPanel();
        telaComponentes.setLayout(new BoxLayout(telaComponentes,BoxLayout.X_AXIS));

        telaComponentes.add(coluna1);
        telaComponentes.add(Box.createHorizontalStrut(9));
        telaComponentes.add(coluna2);

        //////////////////////////////////////////////////////////////////////////////

        // JPanel que agrupa os paineis de textos e componentes em um único painel

        JPanel telaIngressos = new JPanel();
        telaIngressos.setLayout(new BoxLayout(telaIngressos,BoxLayout.Y_AXIS));

        telaIngressos.add(telaDescricao);
        telaIngressos.add(Box.createVerticalStrut(30));
        telaIngressos.add(telaComponentes);

        // Envia o ultimo painel criado para a montagem da janela

        montaTelaIngressos(telaIngressos);

    }

    private void montaTelaIngressos(JPanel telaIngressos){
        
        int option = JOptionPane.showOptionDialog(
                null, // parentComponent
                telaIngressos, // message
                "Atividade: " + atividadeEscolhida.getNome(), // title
                JOptionPane.OK_CANCEL_OPTION, // optionType
                JOptionPane.INFORMATION_MESSAGE, // messageType
                null, // icon
                null, // options
                null // initialValue
        );

        if (option == JOptionPane.OK_OPTION) {
            String tipoIngresso = SelecionaTipo.getSelectedItem().toString();
            int quantidade = (int) SelecionaQTD.getValue();
            
            String nomeCliente = JOptionPane.showInputDialog(null, "Digite o seu nome:");

            
            
            tratarPrecoIngresso(nomeCliente, tipoIngresso, quantidade);
        }
    }

    private void criaCaixasSelecao(){

        String[] tiposIngresso = {"Comum", "Meia-entrada", "Com desconto"};

        // Crie um JComboBox para permitir que o usuário escolha o tipo de ingresso
        SelecionaTipo = new JComboBox<>(tiposIngresso);

        int ingressos = atividadeEscolhida.getIngressosDisponiveis();

        // Crie um JSpinner para permitir que o usuário selecione a quantidade de ingressos
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, ingressos, 1); // Valor inicial, mínimo, máximo, incremento
        SelecionaQTD = new JSpinner(spinnerModel);

        SelecionaTipo.setPreferredSize(new Dimension(160, 30)); // Set preferred size
        SelecionaQTD.setPreferredSize(new Dimension(80, 30)); // Set preferred size

    }
    
    private void tratarPrecoIngresso(String nomeCliente, String tipoIngresso, int quantidade){
        
        Double precototal = atividadeEscolhida.getPrecoIngresso() * quantidade;

        if(tipoIngresso.equals("Comum")){

            ingresso = new IngressoComum();
        }

        if(tipoIngresso.equals("Meia-entrada")){

            ingresso = new IngressoMeia();
        }

        if(tipoIngresso.equals("Com desconto")){

            ingresso = new IngressoComDesconto();
        }

        confirmarCompra(nomeCliente, precototal, tipoIngresso, quantidade);
    }

    private void confirmarCompra(String nomeCliente, Double precoTotal, String tipoIngresso, int quantidade){

        Double precoSimulado = ingresso.simulaPrecoTotal(precoTotal);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        JLabel preco = new JLabel("Preço Total: $ " + precoSimulado.toString());
        JLabel qtd = new JLabel("Quantidade: " + quantidade);
        JLabel tipo = new JLabel("Tipo: " + tipoIngresso);

        panel.add(preco);
        panel.add(Box.createVerticalStrut(10));
        panel.add(qtd);
        panel.add(Box.createVerticalStrut(10));
        panel.add(tipo);


        int option = JOptionPane.showOptionDialog(null, // parentComponent
                panel, // message
                "Confirmar compra", // title
                JOptionPane.OK_CANCEL_OPTION, // optionType
                JOptionPane.INFORMATION_MESSAGE, // messageType
                null, // icon
                null, // options
                null) ;// initialValue

        if (option == JOptionPane.OK_OPTION) {

            if(atividadeEscolhida.compraIngresso(quantidade) == true){
                JOptionPane.showMessageDialog(null, "Compra Realizada");

                cadastrarIngresso(nomeCliente, precoTotal, tipoIngresso, quantidade);
                gerarIngressosPDF(nomeCliente, atividadeEscolhida.getNome(), atividadeEscolhida.getCentro(), atividadeEscolhida.getData());
             }
            else{
                JOptionPane.showMessageDialog(null, "Quantidade Indisponivel");
            } 

        }
    }

    void cadastrarIngresso(String nomeCliente, Double precototal, String tipoIngresso, int quantidade){

        ingresso.setComprador(nomeCliente);
        ingresso.setTipoIngresso(tipoIngresso);
        ingresso.setQuantidade(quantidade);
        ingresso.setAtividade(atividadeEscolhida);
        ingresso.setPrecoTotal(precototal);

        ingressos.add(ingresso);


        // Codigo para testar o salvamento dos ingressos

        // String textoIng = "==";
        // int contador = 0;
        // for(Ingresso i : ingressos){

        //     textoIng +="\nIngresso: " + contador;
        //     textoIng +="\nComprador: " + i.getComprador();
        //     textoIng +="\nTipo: " + i.getTipoIngresso();
        //     textoIng +="\nId atividade: " + i.getAtividade();
        //     textoIng +="\nPreço total: " + i.getPrecoTotal();            
        //     textoIng +="\n+++++++++++++++++++++++++++++";
        //     contador++;
        // }

        // System.out.println(textoIng);
    }

    private void gerarIngressosPDF(String nomeCliente, String nomeEvento, String local, String dataHora) {
        Document document = new Document();

        try {
            JFileChooser fileChooser = new JFileChooser();
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath() + ".pdf";

                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.setPageSize(PageSize.A4);
                document.open();

                for (Ingresso ingresso : ingressos) {
                    if(ingresso.getComprador().equals(nomeCliente)){
                        
                        document.add(new Paragraph("Nome do evento: " + nomeEvento));
                        document.add(new Paragraph("Tipo da atividade: " + atividadeEscolhida.getTipo()));
                        document.add(new Paragraph("Identificação da atividade: " + atividadeEscolhida.getIdAtividade()));
                        document.add(new Paragraph("Local: " + local));
                        document.add(new Paragraph("Data/Hora da atividade: " + dataHora));
                        document.add(new Paragraph("Identificação do comprador: " + ingresso.getComprador()));
                        document.add(new Paragraph("Tipo do ingresso: " + ingresso.getTipoIngresso()));
                        document.add(new Paragraph("Valor do ingresso: " + ingresso.getPrecoTotal()));
                        document.newPage(); // Adiciona uma nova página para cada ingresso
                    }
                }

                document.close();
                System.out.println("Os ingressos foram gerados em um arquivo PDF com sucesso.");
            }
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}