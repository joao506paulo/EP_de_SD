import java.io.*;
import java.net.*;
import java.util.*;

class eachare {

    
    public static void main (String [] args) throws IOException {
        //recebe os argumentos
        String endereco;
        String vizinhos;
        String diretorio;

        endereco = args[0];
        vizinhos = args[1];
        diretorio = args[2];
        comandos opcoes = new comandos();
        mensagens mensagem = new mensagens();
        relogio r = new relogio();
        List<Vizinho> lista_de_vizinhos = new ArrayList<Vizinho>();
        File folder = new File(diretorio); //abre a pasta a ser compartilhada
        //abre um socket na porta especificada no argumento
        String[] endereco_separado = endereco.split(":");
        int porta = Integer.parseInt(endereco_separado[1]);
        ServerSocket serverSocket = new ServerSocket(porta);
        System.out.println(porta);
        
        //abre o arquivo com a lista de vizinhos
        try (BufferedReader br = new BufferedReader(new FileReader(vizinhos))){
            String linha; //tenho que colocar essa informação em um vetor
            
            //esse é o comando1, mas falta adicionar a opção de mandar mensagem para um peer
            while((linha = br.readLine()) != null){
                lista_de_vizinhos.add(new Vizinho(linha));
                System.out.println("Adicionando novo peer: " + linha + " status OFFILINE");
            }
            
            //opcoes.comando1(lista_de_vizinhos);
        } catch (IOException e){
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        
        
        //colocar isso em um método separado por exemplo comando3(File folder)
        File[] arquivos = folder.listFiles();
        //tenho que guardar essa informação
        //isso é o comando 3
        //opcoes.comando3(arquivos);
        List<Socket> lista_de_clientes = new ArrayList<Socket>();
        new Thread(() -> menu(lista_de_vizinhos, folder, opcoes, serverSocket, lista_de_clientes, mensagem, endereco, r)).start();

        //recebe conecções
        while(true){
            try{
                Socket clientSocket = serverSocket.accept();
                lista_de_clientes.add(clientSocket);
                new Thread(() -> handleConnection(clientSocket, r, lista_de_vizinhos, mensagem, endereco)).start();
            } catch (SocketException e){
                System.out.println("Servidor encerrado");
                break;
            }
        }
        
    }
    private static void menu (List<Vizinho> lista_vizinhos, File diretorio, comandos opcoes, ServerSocket serverSocket, List<Socket> lista_clientes, mensagens mensagem, String endereco, relogio r){
        boolean continuar = true;
        Scanner sc = new Scanner (System.in);
        while(continuar){
        
            System.out.println("Escolha um comando:");
            System.out.println("\t [1] Listar peers");
            System.out.println("\t [2] Obter peers");
            System.out.println("\t [3] Listar arquivos locais");
            System.out.println("\t [4] Buscar arquivos");
            System.out.println("\t [5] Exibir estatísticas");
            System.out.println("\t [6] Alterar tamanho de chunk");
            System.out.println("\t [9] Sair");

            
            int comando = sc.nextInt();
            System.out.println("Opção escolhida: " + comando);
            
        
            if(comando == 1){
                opcoes.comando1(lista_vizinhos, mensagem, endereco, r);
            } else if (comando == 2) {
                opcoes.comando2(lista_vizinhos, mensagem, endereco, r);
            } else if (comando == 3) {
                opcoes.comando3(diretorio.listFiles());
            } else if (comando == 4) {
                opcoes.comando4();
            } else if (comando == 5) {
                opcoes.comando5();
            } else if (comando == 6) {
                opcoes.comando6();
            } else if (comando == 9) {
                continuar = false;
                try{
                    opcoes.comando9(serverSocket, lista_clientes,lista_vizinhos, mensagem, endereco, r);    
                } catch (IOException e) {
                    System.err.println("Problema ao fechar servidor. " + e.getMessage());
                }
                
            } else {
                System.out.println("Comando inválido");
            }
       }
       sc.close();
       
    }


    private static void handleConnection (Socket clientSocket, relogio r, List<Vizinho> lista, mensagens m, String endereco) {
        try(
            //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
        ){
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                System.out.println("Mensagem recebida:" + inputLine);
                r.incrementaRelogio();
                String[] partes = inputLine.split(" ");
                if(partes[2].equals("HELLO")){
                    System.out.println("Atualizando peer " + partes[0] + " status ONLINE");
                    for(Vizinho v : lista){
                        if(v.getEndereco().equals(partes[0])){
                            v.setEstado("ONLINE");
                        }
                    }
                }
                if(partes[2].equals("BYE")){
                    System.out.println("Atualizando peer " + partes[0] + " status OFFLINE");
                    boolean achou = false;
                    for(Vizinho v : lista){
                        if(v.getEndereco().equals(partes[0])){
                            v.setEstado("OFFLINE");
                            achou = true;
                        }
                    }
                    if(!achou){
                        Vizinho v = new Vizinho(partes[0]);
                        v.setEstado("ONLINE");
                        lista.add(v);

                    }
                }
                if(partes[2].equals("GET_PEERS")){
                    boolean achou = false;
                    for(Vizinho v : lista){
                        if(v.getEndereco().equals(partes[0])){
                            v.setEstado("ONLINE");
                            achou = true;
                        }
                    }
                    if(!achou){
                        Vizinho v = new Vizinho(partes[0]);
                        v.setEstado("ONLINE");
                        lista.add(v);
                    }
                    for(Vizinho v : lista){
                        if(v.getEndereco().equals(partes[0])){
                            String lista_de_vizinhos = " ";
                            for(Vizinho vi : lista){
                                if(!vi.getEndereco().equals(v.getEndereco())){
                                    lista_de_vizinhos = lista_de_vizinhos + " " +vi.getEndereco()+":"+vi.getEstado()+":0";
                                }
                            }
                            m.mandaMensagem(v, endereco, r, "PEER_LIST " + (lista.size()-1) + lista_de_vizinhos);
                        }
                    }

                } 
        }
     } catch (IOException e){
                e.printStackTrace();

        } 
    } 
}
