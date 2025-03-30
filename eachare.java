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
        List<Vizinho> lista_de_vizinhos = new ArrayList<Vizinho>();
        File folder = new File(diretorio); //abre a pasta a ser compartilhada
        //abre um socket na porta especificada no argumento
        String[] endereco_separado = endereco.split(":");
        int porta = Integer.parseInt(endereco_separado[1]);
        //ServerSocket ServerSocket = new ServerSocket(porta);
        System.out.println(porta);

        //abre o arquivo com a lista de vizinhos
        try (BufferedReader br = new BufferedReader(new FileReader(vizinhos))){
            String linha; //tenho que colocar essa informação em um vetor
            
            //esse é o comando1, mas falta adicionar a opção de mandar mensagem para um peer
            while((linha = br.readLine()) != null){
                lista_de_vizinhos.add(new Vizinho(linha));
                System.out.println("Adicionando novo peer: " + linha + " status OFFILINE");
            }
            
            opcoes.comando1(lista_de_vizinhos);
        } catch (IOException e){
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        
        
        //colocar isso em um método separado por exemplo comando3(File folder)
        File[] arquivos = folder.listFiles();
        //tenho que guardar essa informação
        //isso é o comando 3
        opcoes.comando3(arquivos);
        new Thread(() -> menu(lista_de_vizinhos, folder, opcoes)).start();

        //recebe conecções
/*/        while(true){
            Socket clientSocket = ServerSocket.accept();
            new Thread(() -> handleConnection(clientSocket)).start();
        }*/
    }
    private static void menu (List<Vizinho> lista, File diretorio, comandos opcoes){
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
                opcoes.comando1(lista);
            } else if (comando == 2) {
                //opcoes.comando2();
            } else if (comando == 3) {
                opcoes.comando3(diretorio.listFiles());
            } else if (comando == 4) {
                //opcoes.comando4();
            } else if (comando == 5) {
                //opcoes.comando5();
            } else if (comando == 6) {
                //opcoes.comando6();
            } else if (comando == 9) {
                continuar = false;
                //opcoes.comando9();
            } else {
                System.out.println("Comando inválido");
            }
       }
       sc.close();
    }


  /*   private static void handleConnection (Socket clientSocket) {
        try(
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferredReader in = new BufferredReader (new InputStreamReder(clientSocket.getInputStream()));
        ){
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                System.out.println("Mensagem recebida:" + inputLine);
            
        }
     } catch (IOException e){
                e.printStackTrace();

        } 
    } */
}
