package br.ufla.gac106;

import br.ufla.gac106.adm.Dados;
import br.ufla.gac106.adm.Principal;
import br.ufla.gac106.adm.Sistema;

public class App {

    private static Sistema sistema;
    public static void main(String[] args) throws Exception {

        sistema = Dados.leeArquivoBin();

        if(sistema == null){
            sistema = new Sistema();
        }
        
        Principal p = new Principal(sistema);
        p.executar();
            
    }

}
