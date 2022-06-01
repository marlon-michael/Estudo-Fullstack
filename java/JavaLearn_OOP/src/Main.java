import classes.lanches.*;

import java.util.Scanner;

public class Main {
    public static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        montarLanche();
    }

    private static void montarLanche(){
        Lanche lanche;

        System.out.println("digite 1 para pedir um x-salada");
        System.out.println("digite 2 para pedir um x-burguer");
        System.out.println("digite 3 para pedir um hot dog");
        System.out.println("digite 4 para pedir um misto quente");
        System.out.println("digite 5 para pedir uma minipizza");
        System.out.println("digite 6 para pedir uma pizza");
        System.out.print("Faça o pedido: ");
        int pedido = read.nextInt();

        if (pedido == 1) lanche = new XSalada();
        else if (pedido == 2) lanche = new XBurguer();
        else if (pedido == 3) lanche = new HotDog();
        else if (pedido == 4) lanche = new MistoQuente();
        else if (pedido == 5) lanche = new MiniPizza();
        else if (pedido == 6) lanche = new Pizza();
        else {
            System.err.println(" 🔺 Escolha uma opção válida! 🔺 ");
            return;
        }
        System.out.println();

        if (lanche instanceof XBurguer){
            System.out.println("digite S para aberto");
            System.out.println("digite N para fechado");
            System.out.print("Informe se o lanche é aberto: ");
            String aberto = read.next();
            ((XBurguer) lanche).setAberto(aberto.equalsIgnoreCase("S"));
            System.out.println();
        }

        if (lanche instanceof Sanduiche) {
            String adicional = "s";
            System.out.println("gostaria de algum adicional?");
            System.out.println("digite o adicional ou \"N\" para pular esta etapa: ");
            while (!adicional.equalsIgnoreCase("n")) {
                System.out.print("adicional / \"N\": ");
                adicional = read.next();
                if (!adicional.equalsIgnoreCase("n")) {
                    ((Sanduiche) lanche).setAdicionais(adicional);
                }
            }
            System.out.println();
        }

        if (lanche instanceof MiniPizza){
            if (lanche instanceof Pizza){
                System.out.print("Digite o tamanho (XS/SM/MD/LG/XL): ");
                ((Pizza) lanche).setTamanho(read.next().toUpperCase());
                System.out.println();
            }
            System.out.println("1 - 4 queijos");
            System.out.println("2 - calabresa");
            System.out.println("3 - frango com catupiry");
            System.out.println("4 - marguerita");
            System.out.println("5 - portuguesa");
            System.out.print("Digite o sabor que deseja: ");
            int sabor_num = read.nextInt();
            switch (sabor_num){
                case 1: ((MiniPizza) lanche).setSabor("4 QUEIJOS");break;
                case 2: ((MiniPizza) lanche).setSabor("CALABRESA");break;
                case 3: ((MiniPizza) lanche).setSabor("FRANGO COM CATUPIRY");break;
                case 4: ((MiniPizza) lanche).setSabor("MARGUERITA");break;
                case 5: ((MiniPizza) lanche).setSabor("PORTUGUESA");break;
                default: System.out.println("Sabor não disponível");
            }
            System.out.println();

            System.out.print("Digite \"S\" para borda recheada ou \"N\" para sem borda: ");
            String sabor_borda = read.next();
            if (!sabor_borda.equalsIgnoreCase("n")){
                ((MiniPizza) lanche).setBordaRecheada(true);
                System.out.print("Escreva o sabor da borda: ");
                read.nextLine();
                ((MiniPizza) lanche).setSaborBorda(read.nextLine());
            }
            System.out.println();
        }

        System.out.print("Informe o valor do Lanche: R$ ");
        lanche.setValor(read.nextDouble());
        System.out.println();

        lanche.mostrar();
        System.out.println();
    }
}
