import java.io.*;
import java.net.*;
import java.util.*;

class relogio {
    int relogio = 0;
    public int getRelogio(){
        return this.relogio;
    }
    public void incrementaRelogio(){
        this.relogio = this.relogio+1;
        System.out.println("=> Atualizando relogio para " + this.relogio);
    }
}