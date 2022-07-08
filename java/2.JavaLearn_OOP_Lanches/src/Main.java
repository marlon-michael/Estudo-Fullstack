import classes.EMneu;
import classes.cliente.Cliente;
import classes.lanches.*;
import classes.pedidos.Pedido;

import java.util.Scanner;

public class Main {
    public static Scanner read = new Scanner(System.in);
    public static Lanche lanche;

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        System.out.println("Bem vindo a Lanchonete da serragem");
        System.out.print("Escreva seu nome/apelido: ");
        cliente.setNome(read.nextLine());
        System.out.println("asdasd");

        for (int i =0; i < 10; i++){
            cliente.getPedido().addLanche(montarLanche());

            if (i == 9) break;

            System.out.println("-----------------------------------");
            System.out.println("Digite \"MAIS\" para pedir mais um");
            System.out.println("Digite \"SATISFEITO\" para finalizar pedido");
            System.out.print("Opção: ");
            if (read.next().equalsIgnoreCase("SATISFEITO")) break;
        }
        System.out.println("-----------------------------------\n");
        System.out.println("Comanda de "+cliente.getNome());
        System.out.println();
        cliente.getPedido().mostrarComanda();
    }

    private static Lanche montarLanche(){
        for (EMneu item: EMneu.values()){
            System.out.println("("+item.getNum()+") - "+item.getNome());
        }
        System.out.print("Faça o pedido: ");
        EMneu pedidoLanche = (EMneu) read.nextInt();

        switch(pedidoLanche){
            case XSALADA -> lanche = new XSalada();
            case XBURGUER -> lanche = new XBurguer();
            case MISTOQUENTE -> lanche = new MistoQuente();
            case HOTDOG -> lanche = new HotDog();
            case MINIPIZZA -> lanche = new MiniPizza();
            case PIZZA -> lanche = new Pizza();
            default -> System.err.println(" 🔺 Escolha uma opção válida! 🔺 ");
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
                System.out.print("Informe o adicional / \"N\": ");
                read.nextLine();
                adicional = read.nextLine();
                if (!adicional.equalsIgnoreCase("n")) {
                    System.out.print("Informe o valor: ");
                    ((Sanduiche) lanche).addAdicionais(adicional, read.nextDouble());
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

        return lanche;
    }
}
