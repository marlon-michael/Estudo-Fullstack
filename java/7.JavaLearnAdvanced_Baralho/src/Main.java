import classes.Baralho;
import classes.Carta;
import classes.Naipe;
import classes.ValorCarta;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Baralho b = new Baralho();
        System.out.println(b.searchCard( Naipe.ESPADAS, 1));

    }
}
