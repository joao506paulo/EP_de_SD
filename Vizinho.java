
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
    public void imprime (){
        System.out.print(endereco + " " + estado);
    }
}
