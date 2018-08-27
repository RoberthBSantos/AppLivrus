package roberth.com.applivrus.models;

/**
 * Created by Roberth Santos on 17/03/2018.
 */

public class PDF {
    String nome,caminho;
    int paginas;


    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}
