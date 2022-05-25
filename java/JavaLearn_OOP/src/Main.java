import classes.*;

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
        Lanche lanche;

        if (pedido == 1) lanche = new XSalada();
        else if (pedido == 2) lanche = new XBurguer();
        else if (pedido == 3) lanche = new HotDog();
        else if (pedido == 4) lanche = new MistoQuente();
        else if (pedido == 5){
            lanche = new MiniPizza();
            ((MiniPizza) lanche).borda_recheada = true;

            if (((MiniPizza) lanche).borda_recheada){
                System.out.print("Escreva o sabor da borda: ");
                read.nextLine();
                ((MiniPizza) lanche).sabor_borda = read.nextLine();
            }
        }
        else {
            System.err.println(" 🔺 Escolha uma opção válida! 🔺 ");
            return;
        }
        System.out.println();

        if (pedido == 1 || pedido == 2){
            System.out.println("digite S para aberto");
            System.out.println("digite N para fechado");
            System.out.print("Informe se o lanche é aberto: ");
            String aberto = read.next();
            ((XBurguer) lanche).aberto = aberto.equalsIgnoreCase("S");
        }

        System.out.print("Informe o valor do Lanche: R$ ");
        lanche.valor = read.nextDouble();

        lanche.mostrar();
    }
}
