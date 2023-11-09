package br.ufla.gac106.adm;

import java.util.Scanner;

public abstract class Interface {

    protected Scanner entrada;

    public Interface(Scanner entrada){
        this.entrada = entrada;
    }

    public void executar(){

        int acao;

        
        exibirMenuAcoes();
                
        acao = retornaOpcao();

        tratarMenuAcoes(acao);
    }

    protected int retornaOpcao() {
        try {
            System.out.print("\nDigite sua opção: ");
            String entrada = this.entrada.nextLine();
            int opcao = Integer.parseInt(entrada);
            return opcao;
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Digite um número.");
            return retornaOpcao();
        }
    }

    public void exibirMenuAcoes() { // MENU DE ACOES
        System.out.println("\n_____________________________________");
        
        System.out.println("\n1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Detalhar");
        System.out.println("4 - Remover");  

        System.out.println("_____________________________________");
    }

    public void tratarMenuAcoes(Integer acao){

        switch (acao) {
            case 1: // CADASTRAR
                
                cadastrar();
                
                break;
        
            case 2: // LISTAR
                
                listar();

                break;

            case 3: // DETALHAR
                
                detalhar();

                break;

            case 4: // REMOVER
                
                remover();

                break;
            default:
                System.out.println("\nOpcao incorreta, tente novamente");
                break;
        }
    }

    protected abstract void cadastrar();
    protected abstract void listar();
    protected abstract void detalhar();
    protected abstract void remover();
}
