import java.io.*;
import java.net.*;
import java.util.*;

class mensagens{
    public void mandaMensagem(String vizinho){
        try{
            String[] partes = vizinho.split(":");
            int porta = Integer.parseInt(partes[1]);
            //para a aplicação funcionar em mais de um computador, 
            //é nescessário mudar localhost para o partes[0], com ip válido.
            Socket socket = new Socket("localhost", porta);
            System.out.println("conectado a " + vizinho);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("mansagem");
            String message = in.readLine();
            System.out.println("Recebendo mensagem: " + message);
            in.close();
            out.close();
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}