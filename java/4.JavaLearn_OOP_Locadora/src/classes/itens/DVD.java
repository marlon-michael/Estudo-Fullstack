package classes.itens;

import java.util.Scanner;

public class DVD extends Item{
    private String diretor;
    private double duraçao;
    private int anoLançado;

    @Override
    public void montarDetalhes(Scanner read) {
        System.out.print("Digite a duração: ");
        this.setDuraçao(read.nextDouble());
        System.out.println();

        System.out.print("Digite o ano de lançamento: ");
        this.setAnoLançado(read.nextInt());
        System.out.println();

        System.out.print("Digite o diretor do filme: ");
        read.nextLine();
        this.setDiretor(read.nextLine());
        System.out.println();
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Duração: "+this.getDuraçao());
        System.out.println("Ano de lançamento: "+this.getAnoLançado());
        System.out.println("Diretor: "+this.getDiretor());
    }

    //GETTERS AND SETTERS
    public String getDiretor() {
        return diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    public double getDuraçao() {
        return duraçao;
    }
    public void setDuraçao(double duraçao) {
        this.duraçao = duraçao;
    }
    public int getAnoLançado() {
        return anoLançado;
    }
    public void setAnoLançado(int anoLançado) {
        this.anoLançado = anoLançado;
    }
}
