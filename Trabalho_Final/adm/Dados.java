package br.ufla.gac106.adm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Dados {

    private Sistema sistema;

    public static Sistema leeArquivoBin(){

        try {

            File arquivo = new File("arquivo");
		    if (arquivo.exists() && !arquivo.isDirectory()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("arquivo"));
		    
                Sistema sistema = (Sistema)ois.readObject();
                ois.close();
                return sistema;
		    }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        
    }

    public static void salvaArquivoBin(Sistema sistema){

        try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("arquivo"));
			
  			    oos.writeObject(sistema);
		
			    oos.close();					
	    }
	    catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
	    }
	}
}
