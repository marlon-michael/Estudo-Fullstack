import classes.Aviao;
import classes.Onibus;

public class Main {
    public static void main(String[] args) {
        Onibus ombinus = new Onibus(3);
        Aviao aviao = new Aviao(2,3);

        aviao.getAssento("1");
        aviao.mostrarAssentos();

        System.out.println("------------");

        ombinus.getAssento("1");
        ombinus.mostrarAssentos();
    }
}
