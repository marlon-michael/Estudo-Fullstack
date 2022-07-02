package classes.itens;

import java.util.Scanner;

public class Livro extends Item{
    private String autor;
    private int quantidadePaginas;
    private int anoPublicado;
    private int ediçao;

    @Override
    public void montarDetalhes(Scanner read) {
        System.out.print("Digite a edição do livro: ");
        this.setEdiçao(read.nextInt());
        System.out.println();

        System.out.print("Digite a quantidade de paginas: ");
        this.setQuantidadePaginas(read.nextInt());
        System.out.println();

        System.out.print("Digite o ano de publicação: ");
        this.setAnoPublicado(read.nextInt());
        System.out.println();

        System.out.print("Digite o autor do livro: ");
        read.nextLine();
        this.setAutor(read.nextLine());
        System.out.println();
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Edição: "+this.getEdiçao());
        System.out.println("Número de paginas: "+this.getQuantidadePaginas());
        System.out.println("Ano de publicação: "+this.getAnoPublicado());
        System.out.println("Autor: "+this.getAutor());
    }

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
