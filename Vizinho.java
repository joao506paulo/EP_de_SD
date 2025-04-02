//Essa classe é usada para agrupar as informações de endereço e status dos vizinhos
class Vizinho {
    String endereco;
    String estado;
    public Vizinho (String endereco){
        this.endereco = endereco;
        this.estado = "OFFLINE";
    }
    public String getEndereco (){
        return this.endereco;
    }
    public String getEstado (){
        return this.estado;
    }
    public void setEstado (String estado){
        this.estado = estado;
    }
    //facilita a impressão das informações do peer
    public void imprime (){
        System.out.print(endereco + " " + estado);
    }
}
