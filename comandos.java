import java.io.*;
import java.net.*;
import java.util.*;


class comandos {
    public void comando1 (List<Vizinho> lista){
        for(Vizinho vizinho : lista){
            vizinho.imprime();
        }
        
        //falta acrescentar a opção de mandar o HELLO
    }
    public void comando2 (){
        //falta implementar
        System.out.println("Comando ainda não implementado.");   
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
    public void comando4 (){
        //será implementado na outra parte do ep
        System.out.println("Comando ainda não implementado.");   
    }    
    public void comando5 (){
        //será implemantado na outra parte do ep
        System.out.println("Comando ainda não implementado.");   
    }    
    public void comando6 (){
        //será implementado na outra parte do ep
        System.out.println("Comando ainda não implementado.");   
    }    
    public void comando9 (ServerSocket serverSocket, List<Socket> clientes) throws IOException{
        //verificar os recursos abertos para fechar
        for(Socket cliente : clientes){
            //mandar a mensagem tipo bye
            cliente.close();
        }

        serverSocket.close();
    }
}