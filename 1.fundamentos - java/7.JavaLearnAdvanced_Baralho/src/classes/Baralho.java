package classes;

import java.util.ArrayList;
import java.util.Collections;

public class Baralho {

    private ArrayList<Carta> baralho = new ArrayList<>();

    public Baralho(){
        for (Naipe naipe: Naipe.values()){
            int valorReal = 1;
            for (ValorCarta valorCarta: ValorCarta.values()){
                Carta carta = new Carta();
                carta.setNaipe(naipe);
                carta.setValor(valorCarta);
                carta.setValorReal(valorReal);
                this.baralho.add(carta);
                valorReal++;
            }
        }
        shuffle();
    }

    public void shuffle(){
        Collections.shuffle(this.baralho);
    }

    public Carta getFromTop(){
        return this.baralho.remove(this.baralho.size()-1);
    }

    public Carta getFromBottom(){
        return this.baralho.remove(0);
    }

    public Carta searchCard(Naipe naipe, ValorCarta valor){
        return this.baralho.stream().filter(
                c -> c.getNaipe().equals(naipe) && c.getValor().equals(valor)
        ).findFirst().orElse(null);
    }

    public Carta searchCard(Naipe naipe, int  valorReal){
        return this.baralho.stream().filter(
                c -> c.getNaipe().equals(naipe) && c.getValorReal() == (valorReal)
        ).findFirst().orElse(null);
    }

    //GETTERS AND SETTERS
    public ArrayList<Carta> getBaralho() {
        return baralho;
    }

    public void setBaralho(ArrayList<Carta> baralho) {
        this.baralho = baralho;
    }
}
