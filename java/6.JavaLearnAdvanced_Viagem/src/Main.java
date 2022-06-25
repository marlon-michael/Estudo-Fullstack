import classes.Aviao;
import classes.Onibus;

public class Main {
    public static void main(String[] args) {
        Onibus ombinus = new Onibus(2);

        ombinus.getAssento("1");
        ombinus.mostrarAssentos();
    }
}
