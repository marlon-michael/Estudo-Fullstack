package classes.itens;

import java.util.Scanner;
import classes.Avaliacao.Avaliação;

public abstract class Item {
    private String titulo;
    private String genero;
    private double valor;
    private Avaliação[] avaliações = new Avaliação[30];

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

        for (int i = 0; i < this.getAvaliações().length; i++){
            if (this.getAvaliações()[i] == null){
                this.getAvaliações()[i] = avaliação;
                break;
            }
        }
    }

    public double getAvaliaçãoTotal(){
        double total = 0;
        int contador = 0;
        for (Avaliação avaliação: this.getAvaliações()){
            if (avaliação != null){
                total += avaliação.getAvaliação();
                contador++;
            }
        }
        return total/contador;
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
    public Avaliação[] getAvaliações() {
        return avaliações;
    }
    public void setAvaliações(Avaliação[] avaliações) {
        this.avaliações = avaliações;
    }
}
