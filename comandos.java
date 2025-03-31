import java.io.*;
import java.net.*;
import java.util.*;


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
        int comando = sc.nextInt();
        System.out.println("Opção escolhida: " + comando);
        //colocar ifs e mandar mensagem com a classe mensagens
        int j;
        for(j=0; j <= i; j++){
            if(comando == 0){
                break;
            }
            if(comando == j){
                m.mandaMensagem(lista.get(j-1), endereco, r, "HELLO"); //é j ou j+-1?
            }
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
    public void comando9 (ServerSocket serverSocket, List<Socket> clientes, List<Vizinho> lista, mensagens m, String endereco, relogio r) throws IOException{
        //verificar os recursos abertos para fechar
        for(Vizinho v: lista){
            if(v.getEstado().equals("ONLINE")){
                m.mandaMensagem(v, endereco, r, "BYE");
            }
        }
        for(Socket cliente : clientes){
            //mandar a mensagem tipo bye
            cliente.close();
        }

        serverSocket.close();
    }
}