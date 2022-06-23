package classes.pedidos;

import classes.lanches.*;

import java.util.HashMap;

public class Pedido {
    private Lanche[] lanches = new Lanche[10];

    public void mostrarComanda() {
        for (Lanche lanche: this.getLanches()) {
            if (lanche == null) continue;

            //Apresenta Lanche
            if (lanche instanceof MiniPizza) {
                MiniPizza lancheEspecial = (MiniPizza)lanche;
                System.out.println(" - - - " + lancheEspecial.getNomeLanche() + " - " + lancheEspecial.getSabor() + " - - -");
            } else {
                System.out.println(" - - - " + lanche.getNomeLanche() + " - - -");
            }
            //sanduiche
            if (lanche instanceof Sanduiche) {
                //xburguer / xsalada
                if (lanche instanceof XBurguer) {
                    XBurguer lancheEspecial = (XBurguer)lanche;
                    if (lancheEspecial.isAberto()) System.out.println("Lanche Aberto");
                    else System.out.println("Lanche Fechado");
                }

                Sanduiche lancheEspecial = (Sanduiche)lanche;
                System.out.print("ADICIONAIS: ");
                for (String adicional: lancheEspecial.getAdicionais().keySet()) {
                    System.out.print(adicional.toUpperCase() + "   ");
                }
                System.out.println();
            }
            //minipizza / pizza
            if (lanche instanceof MiniPizza) {
                //pizza
                if (lanche instanceof Pizza) {
                    Pizza lancheEspecial = (Pizza)lanche;
                    System.out.println("Tamanho: " + lancheEspecial.getTamanho());
                    System.out.println();
                }

                MiniPizza lancheEspecial = (MiniPizza)lanche;
                if (lancheEspecial.isBordaRecheada()) {
                    System.out.println("Borda Recheada Sabor: " + lancheEspecial.getSaborBorda());
                } else System.out.println("Borda Fina");
                System.out.println();

            }
            //mostra ingredientes
            System.out.print("Ingredientes: ");
            for (String ingrediente: lanche.getIngredientes()) {
                if (ingrediente != null) System.out.print(ingrediente + "  ");
            }
            System.out.println();

            System.out.printf("Valor: R$ %.2f\n", lanche.getValor());
            System.out.println("-----------------------------------------");
        }
        System.out.printf("Valor total da comanda é de R$ %.2f\n", this.calcularValorTotal());
    }

    public double calcularValorTotal() {
        double valor = 0;
        for (Lanche lanche: this.getLanches()) {
            if (lanche == null) continue;
            valor += lanche.getValor();
        }
        return valor;
    }

    //GETTERS AND SETTERS
    public Lanche[] getLanches() {
        return this.lanches;
    }

    public void addLanche(Lanche lanche) {
        for (int i = 0; i < this.lanches.length; i++) {
            if (this.lanches[i] == null) {
                this.lanches[i] = lanche;
                return;
            }
        }
    }
}
