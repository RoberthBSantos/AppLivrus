package roberth.com.applivrus.models;

import com.orm.SugarRecord;

/**
 * Created by Roberth Santos on 17/02/2018.
 */

public class Livro extends SugarRecord {

    private String titulo;
    private String genero;
    private int paginas;
    private int ano;
    private String autor;
    private String estado;
    private int paginaAtual;
    private int avaliacao;
    private String comentario;
    private Usuario usuario;
    private String caminho;



    public Livro() {

    }

    public Livro(String titulo, String genero, int paginas, int ano, String autor, String estado, int paginaAtual, int avaliacao, String comentario, Usuario usuario, String caminho) {
        this.titulo = titulo;
        this.genero = genero;
        this.paginas = paginas;
        this.ano = ano;
        this.autor = autor;
        this.estado = estado;
        this.paginaAtual = paginaAtual;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
        this.usuario = usuario;
        this.caminho = caminho;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}





