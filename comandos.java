import java.io.*;
import java.net.*;
import java.util.*;


class comandos {
    public void comando1 (List<Vizinho> lista){
        for(Vizinho vizinho : lista){
            vizinho.imprime();
        }
    }
    public void comando3 (File[] arquivos){
        if (arquivos != null){
            for(File arquivo : arquivos){
                if(arquivo.isFile()){
                    System.out.println(arquivo.getName());
                }
            }
        }
    }
}