import classes.Carta;
import classes.Naipe;
import classes.ValorCarta;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Carta> baralho = new ArrayList<>();

        for (Naipe naipe: Naipe.values()){
            for (ValorCarta valorCarta: ValorCarta.values()){
                Carta carta = new Carta();
                carta.setNaipe(naipe);
                carta.setValor(valorCarta);
                baralho.add(carta);
            }
        }

        for (Carta carta: baralho){
            System.out.println(carta);
        }
    }
}
