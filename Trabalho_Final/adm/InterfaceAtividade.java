package br.ufla.gac106.adm;

import java.util.Scanner;

public class InterfaceAtividade extends Interface {
    
    private AdmAtividade admAtividade;
    private InterfaceEvento iuEvento;

    public InterfaceAtividade(Scanner entrada, InterfaceEvento iuEvento, AdmAtividade admAtividade){
        super(entrada);
        this.iuEvento = iuEvento;
        this.admAtividade = admAtividade;
    }

    protected void cadastrar() {
        System.out.print("\nDigite o nome da Atividade: "); 
        String nome = entrada.nextLine();

        System.out.print("\nDigite o tema do evento que ela pertence: ");
        String tema = entrada.nextLine();

        Evento evento = iuEvento.retornaBuscarEvento(tema);
        if (evento != null) {
            Local local = evento.getLocal();
            
                int opcao;
                do {
                    System.out.print("\nQual é o tipo de atividade?\n");
                    System.out.print("\n1. Demonstracao de Produtos\n"
                                    + "2. Palestras Tecnicas\n");
                    opcao = retornaOpcao();
                    if (opcao != 1 && opcao != 2) {
                        System.out.print("\nOpcao invalida, tente novamente\n");
                    }
                } while (opcao != 1 && opcao != 2);

                System.out.print("\nDigite a data e hora: ");
                String data = entrada.nextLine();

                String tipoAtividade = "";
                if (opcao == 1) {
                    tipoAtividade = "Demonstracao de Produtos";
                } else if (opcao == 2) {
                    tipoAtividade = "Palestras Tecnicas";
                }

                System.out.print("\nDigite o numero de ingressos disponíveis: ");
                Integer ingressos = Integer.parseInt(entrada.nextLine());

                System.out.print("\nDigite o valor do ingresso: ");
                Double preco = Double.parseDouble(entrada.nextLine());

            if (local != null) {
                admAtividade.cadastrarAtividade(preco, nome, evento, tipoAtividade, local.getNomeLocal(), data, ingressos);
                System.out.print("\nAtividade cadastrada com sucesso!\n");
            } else {
                admAtividade.cadastrarAtividade(preco, nome, evento, tipoAtividade, "Indefinido", data, ingressos);
            }
        } else {
            System.out.print("\nErro: Evento não encontrado.\n");
        }
    }

    protected void listar() {
        System.out.print("\nListar Atividades\n");
        System.out.println(admAtividade.listarAtividades());
    }

    protected void detalhar() {
        System.out.print("\nInsira o nome da atividade que deseja detalhar: ");
        String nome = entrada.nextLine();
        System.out.println(admAtividade.detalharAtividade(nome));
    }

    protected void remover() {
        System.out.print("\nInsira o nome do local que deseja remover: ");
        String nome = entrada.nextLine();
        System.out.println(admAtividade.removerAtividade(nome));
    }

}
