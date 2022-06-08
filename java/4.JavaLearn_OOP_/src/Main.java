import classes.itens.DVD;

public class Main {
    public static void main(String[] args) {

        DVD dvd = new DVD();
        dvd.avaliar();
        dvd.avaliar();
        System.out.println(dvd.getAvaliaçãoTotal());
    }
}
