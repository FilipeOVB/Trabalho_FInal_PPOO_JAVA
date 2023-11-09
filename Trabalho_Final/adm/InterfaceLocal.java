package br.ufla.gac106.adm;

import java.util.Scanner;

public class InterfaceLocal extends Interface {

    private AdmLocal admLocal;

    public InterfaceLocal(Scanner entrada, AdmLocal admLocal){
        super(entrada);
        this.admLocal = admLocal;
        
    }
    
    protected void cadastrar(){ // CADASTRAR Local

        System.out.print("\nDigite o nome do Local: ");
        String nome = entrada.nextLine();

        System.out.print("\nDigite a cidade do Local: ");
        String cidade = entrada.nextLine();

        System.out.print("\nDigite a capacidade de pessoas no Local: ");
        String capacidade = entrada.nextLine();

        admLocal.cadastrarLocal(nome, cidade, capacidade);
        System.out.print("\nLocal cadastrado com sucesso!\n");
    
    }

    protected void detalhar(){

        System.out.print("\nInsira o nome do local que deseja detalhar: ");

        String nome = entrada.nextLine();

        System.out.println(admLocal.detalharLocal(nome));

    }

    protected void listar(){ 

        System.out.print("\nListar Locais\n");

        System.out.println(admLocal.listarLocais());
    }

    protected void remover(){

        System.out.print("\nInsira o nome do local que deseja remover: ");

        String nome = entrada.nextLine();

        System.out.println(admLocal.removerLocal(nome));
    }

    public Local retornaBuscarLocal(String nome){
        
        return admLocal.buscarLocal(nome);
        
    }
}

