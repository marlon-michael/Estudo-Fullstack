package classes.itens;

public class Livro extends Item{
    private String autor;
    private int quantidadePaginas;
    private int anoPublicado;
    private int ediçao;

    //GETTERS AND SETTERS
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getQuantidadePaginas() {
        return quantidadePaginas;
    }
    public void setQuantidadePaginas(int quantidadePaginas) {
        this.quantidadePaginas = quantidadePaginas;
    }
    public int getAnoPublicado() {
        return anoPublicado;
    }
    public void setAnoPublicado(int anoPublicado) {
        this.anoPublicado = anoPublicado;
    }
    public int getEdiçao() {
        return ediçao;
    }
    public void setEdiçao(int ediçao) {
        this.ediçao = ediçao;
    }
}
