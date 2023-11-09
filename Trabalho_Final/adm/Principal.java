package br.ufla.gac106.adm;

import java.util.Scanner;

import br.ufla.gac106.compraingresso.EscolhaEvento;
import br.ufla.gac106.compraingresso.JanelaBase;
import br.ufla.gac106.s2023_1.base.relatorios.ContabilizaAtividades;
import br.ufla.gac106.s2023_1.base.relatorios.ContabilizadorIngressos;

public class Principal {

    protected Scanner entrada;

    private InterfaceEvento iuEvento;
    private InterfaceLocal iuLocal;
    private InterfaceAtividade iuAtividade;

    private Sistema sistema;

    private Integer opcao;

    public Principal(Sistema sistema) {
        entrada = new Scanner(System.in);

        this.sistema = sistema;
        
        iuLocal = new InterfaceLocal(entrada, sistema.getAdmLocal());
        iuEvento = new InterfaceEvento(entrada, iuLocal, sistema.getAdmEvento());
        iuAtividade = new InterfaceAtividade(entrada, iuEvento, sistema.getAdmAtividade());

    }

    public void executar() {

        do {
            exibirMenuInicial();

            opcao = retornaOpcao();

            tratarMenuInicial(opcao);

        } while (opcao != 4);

        // fecha o objeto Scanner para liberar os seus recursos
        // entrada.close();
    }

    private int retornaOpcao() {
        try {
            System.out.print("\nDigite sua opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());
            return opcao;
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Digite um número.");
            return retornaOpcao();
        }
    }
    public void exibirMenuInicial() { // MENU INICIAL
        System.out.println("_____________________________________");

        System.out.println("\n1 - Modulo ADM");
        System.out.println("2 - Modulo Relatorios");
        System.out.println("3 - Modulo Ingressos");
        System.out.println("4 - Sair");

        System.out.println("_____________________________________");
    }

    public void exibirMenuAdm() { // MENU ADM
        System.out.println("_____________________________________");

        System.out.println("\n1 - Eventos");
        System.out.println("2 - Locais");
        System.out.println("3 - Atividades");

        System.out.println("_____________________________________");
    }

    private void tratarMenuInicial(int opcao) { // Trata menu inicial

        switch (opcao) {

            case 1: // ENTROU NO MODULO ADM
                System.out.println("\nModulo ADM");
                exibirMenuAdm(); // exibe o menu ADM
                opcao = retornaOpcao(); // pega opcao do usuario
                tratarMenuADM(opcao); // trata a opcao do usuario

                break;

            case 2: // ENTROU NO MODULO RELATORIOS

                System.out.println("\nModulo Relatorios");

                ContabilizadorIngressos  contabilizadorIngressos = new ContabilizaAtividades(sistema);

                break;

            case 3: // ENTROU NO MODULO INGRESSOS

                System.out.println("\nModulo Ingressos");

                JanelaBase janela = new EscolhaEvento(sistema);

                break;

            case 4: // SAIR

                Dados.salvaArquivoBin(sistema);

                System.out.println("\nSair");
                break;

            default: // ERRO

                System.out.println("\nOpcao invalida, tente novamente");

                break;
        }
    }

    private void tratarMenuADM(int opcao) { // Trata Menu ADM

        switch (opcao) {

            case 1: // ENTROU EM EVENTOS
                System.out.print("\nEventos");

                iuEvento.executar();

                break;

            case 2: // ENTROU EM LOCAIS
                System.out.print("\nLocais");

                iuLocal.executar();

                break;

            case 3: // ENTROU EM ATIVIDADES
                System.out.print("\nAtividades");

                iuAtividade.executar();

                break;

            default: // ERRO

                System.out.println("\nOpcao invalida, tente novamente");

                break;
        }
    }
    
}
