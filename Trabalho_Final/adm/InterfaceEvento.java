package br.ufla.gac106.adm;

import java.util.Scanner;

public class InterfaceEvento extends Interface {

    private AdmEvento admEvento;
    private InterfaceLocal iuLocal;
    
    public InterfaceEvento(Scanner entrada, InterfaceLocal iuLocal, AdmEvento admEvento){
        super(entrada);
        this.iuLocal = iuLocal;

        this.admEvento = admEvento;
        
    }
    
    protected void cadastrar(){ // CADASTRAR EVENTO

        System.out.print("\nDigite o tema do evento: ");
        String tema = entrada.nextLine();

        String[] expositores = new String[0];
        int opcao;

        do {
        
            System.out.print("\nDeseja adcionar expositor?\n");

            System.out.print("\n1. Sim\n"
                            + "2. Nao\n");

            opcao = retornaOpcao();
            
            if(opcao == 1){     
                expositores = adicionarExpositor();
            }

            if(opcao != 1 && opcao != 2){
                System.out.print("\nOpcao invalida, tente novamente\n");
            }

        } while (opcao != 1 && opcao != 2);

        do{
            System.out.println("_____________________________________");
            System.out.print("\nLocal de evento:\n");

            System.out.print("\n1. Adcionar local ja cadastrado\n"
                            + "2. Nao adcionar local\n");
            
            opcao = retornaOpcao();

            if(opcao == 1){

                System.out.print("\nDigite o nome do local que deseja adcionar: ");
                
                String nome = entrada.nextLine();
        
                Local local = iuLocal.retornaBuscarLocal(nome);
                    

                if(local != null){
                    admEvento.cadastrarEvento(tema, expositores, local);
                    System.out.print("\nEvento cadastrado com sucesso!\n");
                }
                else{
                    System.out.print("\nErro: Local não encontrado. \n");
                    
                    opcao = -1;
                }
            }

            if(opcao == 2){
                admEvento.cadastrarEvento(tema, expositores, null);
                System.out.print("\nEvento cadastrado com sucesso!\n");
            }                
        } while (opcao != 1 && opcao != 2);

        
        
    }

    private String[] adicionarExpositor() {

        System.out.print("\nQuantos expositores deseja adicionar? ");
        int qnt = 0;
        boolean entradaValida = false;
    
        while (!entradaValida) {
            try {
                qnt = Integer.parseInt(entrada.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. O valor deve ser um número inteiro.");
                System.out.print("Quantos expositores deseja adicionar? ");
            }
        }
    
        String[] expositores = new String[qnt];
        String nome = "";
    
        System.out.print("\n");
    
        for (int i = 0; i < qnt; i++) {
    
            System.out.print("Expositor " + (i + 1) + ": ");
    
            nome = entrada.nextLine();
            expositores[i] = nome;
        }
        return expositores;
    }
    
    protected void detalhar(){

        System.out.print("\nInsira o tema do evento que deseja detalhar: ");

        String tema = entrada.nextLine();

        System.out.println(admEvento.detalharEvento(tema));
    }
    
    protected void listar(){ 

        System.out.print("\nListar Eventos\n");
        System.out.println(admEvento.listarEventos());
    }

    protected void remover(){

        System.out.print("\nInsira o tema do evento que deseja remover: ");

        String tema = entrada.nextLine();

        System.out.println(admEvento.removerEvento(tema));
    }

    public Evento retornaBuscarEvento(String tema){
        
        return admEvento.buscarEvento(tema);
        
    }

}