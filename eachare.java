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
        comandos c = new comandos();
        
        //abre um socket na porta especificada no argumento
        String[] endereco_separado = endereco.split(":");
        int porta = Integer.parseInt(endereco_separado[1]);
        //ServerSocket ServerSocket = new ServerSocket(porta);
        System.out.println(porta);

        //abre o arquivo com a lista de vizinhos
        try (BufferedReader br = new BufferedReader(new FileReader(vizinhos))){
            String linha; //tenho que colocar essa informação em um vetor
            List<Vizinho> lista_de_vizinhos = new ArrayList<Vizinho>();
            //esse é o comando1, mas falta adicionar a opção de mandar mensagem para um peer
            while((linha = br.readLine()) != null){
                lista_de_vizinhos.add(new Vizinho(linha));
                System.out.println(linha);
            }
            
            c.comando1(lista_de_vizinhos);
        } catch (IOException e){
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        //abre a pasta a ser compartilhada
        File folder = new File(diretorio);
        //colocar isso em um método separado por exemplo comando3(File folder)
        File[] arquivos = folder.listFiles();
        //tenho que guardar essa informação
        //isso é o comando 3
        c.comando3(arquivos);

        //recebe conecções
/*/        while(true){
            Socket clientSocket = ServerSocket.accept();
            new Thread(() -> handleConnection(clientSocket)).start();
        }*/
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
