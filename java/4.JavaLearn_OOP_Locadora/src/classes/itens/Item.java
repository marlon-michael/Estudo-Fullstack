package classes.itens;

import java.util.ArrayList;
import java.util.Scanner;
import classes.avaliacao.Avaliação;

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

        this.getAvaliações().add(avaliação);
    }

    public double getAvaliaçãoTotal(){
        double total = 0;

        return this.avaliações.stream().mapToDouble(Avaliação::getAvaliação).sum()/getAvaliações().size();

        /*for (Avaliação avaliação: this.getAvaliações()){
            total += avaliação.getAvaliação();
        }
        return total/this.getAvaliações().size();
         */
    }

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
