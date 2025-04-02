import java.io.*;
import java.net.*;
import java.util.*;

//Essa classe possui métodos para atender a cada um dos comandos disponíveis para o usuário
class comandos {
    public void comando1 (List<Vizinho> lista, mensagens m, String endereco, relogio r){
        int i = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("[0] voltar para o menu anterior");
        for(Vizinho vizinho : lista){
            System.out.print("[" + i +"]");
            vizinho.imprime();
            System.out.print("\n");
            i++;
        }
        System.out.print(">");
        int comando = sc.nextInt();
        int j;
        for(j=0; j <= i; j++){
            if(comando == 0){
                break;
            }
            if(comando == j){
                m.mandaMensagem(lista.get(j-1), endereco, r, "HELLO"); 
            }
        }
    }
    public void comando2 (List<Vizinho> lista, mensagens m, String endereco, relogio r){
        for(Vizinho v : lista){
            m.mandaMensagem(v, endereco, r, "GET_PEERS");
        }  
    }
    public void comando3 (File[] arquivos){
        if (arquivos != null){
            for(File arquivo : arquivos){
                if(arquivo.isFile()){
                    System.out.println("   " + arquivo.getName());
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
    public void comando9 (ServerSocket serverSocket, List<Socket> clientes, List<Vizinho> lista, mensagens m, String endereco, relogio r) throws IOException{
        //manda a mensagem tipo bye
        for(Vizinho v: lista){
            if(v.getEstado().equals("ONLINE")){
                m.mandaMensagem(v, endereco, r, "BYE");
            }
        }
        //fecha os recursos abertos
        for(Socket cliente : clientes){           
            cliente.close();
        }
        serverSocket.close();
    }
}