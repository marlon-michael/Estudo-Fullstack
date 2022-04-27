import java.util.Scanner;

public class Main {
    /*
    int - numero inteiro
    double - numero decimal
    String - literal, texto ""
    char - caraactere único ''
    boolean - lógico, true / false
     */

    public static void main(String[] args) {
        exe04();
    }

    public static void exe01() {
        int A = 10;
        int B = 20;
        int C = A;
        A = B;
        B = C;

        System.out.println("O valor de A e B invertidos são: "+A+" e "+B);
    }

    public static void exe02() {
        Scanner read = new Scanner(System.in);

        System.out.print("Digite um numero inteiro: ");
        int valor = read.nextInt();

        System.out.println();
        System.out.println("O valor antecessor de " + valor + " é " + (--valor));
    }

    public static void exe03() {
        Scanner read = new Scanner(System.in);

        System.out.print("Digite a base do retangulo");
        double base = read.nextDouble();
        System.out.print("Digite a altura do retangulo");
        double altura = read.nextDouble();
        double area = base*altura;
        double perimetro = 2*(base+altura);

        System.out.println();
        System.out.println("A area do retangulo informado é de: "+area+" e seu perimetro: "+perimetro);
    }

    public static void exe04() {
        Scanner read = new Scanner(System.in);

        System.out.println("Digite seu tempo de vida em:");
        System.out.print("Anos: ");
        int anos = read.nextInt();
        System.out.print("Meses: ");
        int meses = read.nextInt();
        System.out.print("Dias: ");
        int dias = read.nextInt();

        int dias_vividos = 0;
        dias_vividos += anos * 365;
        dias_vividos += meses * 30;
        dias_vividos += dias;

        System.out.println();
        System.out.println("A sua idade em dias foram de: "+dias_vividos);

    }

    public static void exe05() {
        Scanner read = new Scanner(System.in);

        System.out.print("");
    }
}
