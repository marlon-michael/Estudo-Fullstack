package classes.itens;

import java.util.ArrayList;
import java.util.Scanner;
import classes.Avaliacao.Avaliação;

public abstract class Item {
    private String titulo;
    private String genero;
    private double valor;
    private ArrayList<Avaliação> avaliações = new ArrayList<>();

    public void avaliar(){
        Scanner read = new Scanner(System.in);
        Avaliação avaliação = new Avaliação();

        System.out.println("--------------------------");
        System.out.print("Digite seu nome: ");
        avaliação.setNome(read.nextLine());
        System.out.println();

        System.out.print("Digite sua avaliação (0 - 10): ");
        avaliação.setAvaliação(read.nextDouble());
        System.out.println();

        System.out.print("Comente sobre a obra: ");
        read.nextLine();
        avaliação.setFeedback(read.nextLine());
        System.out.println("--------------------------");

        System.out.println("Avaliação feita em: "+avaliação.setTimeToNow());
        System.out.println("--------------------------");

        this.getAvaliações().add(avaliação);
    }

    public double getAvaliaçãoTotal(){
        return this.avaliações.stream().mapToDouble(Avaliação::getAvaliação).sum()/getAvaliações().size();
    }

    public abstract void montarDetalhes(Scanner read);
    public abstract void mostrarDetalhes();

    //GETTERS AND SETTERS
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
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public ArrayList<Avaliação> getAvaliações() {
        return avaliações;
    }
    public void setAvaliações(ArrayList<Avaliação> avaliações) {
        this.avaliações = avaliações;
    }
}
