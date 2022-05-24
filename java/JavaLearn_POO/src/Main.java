import classes.HotDog;
import classes.MistoQuente;
import classes.XBurguer;
import classes.XSalada;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        System.out.println("digite 1 para pedir um x-salada");
        System.out.println("digite 2 para pedir um x-burguer");
        System.out.println("digite 3 para pedir um hot dog");
        System.out.println("digite 4 para pedir um misto quente");
        System.out.print("Faça o pedido: ");
        int pedido = read.nextInt();

        if (pedido == 1){
            XSalada lanche = new XSalada();

            System.out.print("Informe o valor do Lanche: R$ ");
            lanche.valor = read.nextDouble();

            System.out.println("digite S para aberto");
            System.out.println("digite N para fechado");
            System.out.print("Informe se o lanche é aberto: ");
            String aberto = read.next();
            lanche.aberto = aberto.equalsIgnoreCase("S");

            lanche.mostrar();
        }
        else if (pedido == 2){
            XBurguer lanche = new XBurguer();

            System.out.print("Informe o valor do Lanche: R$ ");
            lanche.valor = read.nextDouble();

            System.out.println("digite S para aberto");
            System.out.println("digite N para fechado");
            System.out.print("Informe se o lanche é aberto: ");
            String aberto = read.next();
            lanche.aberto = aberto.equalsIgnoreCase("S");

            lanche.mostrar();
        }

        else if (pedido == 3){
            HotDog lanche = new HotDog();

            System.out.print("Informe o valor do Lanche: R$ ");
            lanche.valor = read.nextDouble();

            lanche.mostrar();
        }

        else if (pedido == 4){
            MistoQuente lanche = new MistoQuente();

            System.out.print("Informe o valor do Lanche: R$ ");
            lanche.valor = read.nextDouble();

            lanche.mostrar();
        }

        else {
            System.err.println(" 🔺 Escolha uma opção válida! 🔺 ");
        }
    }
}
