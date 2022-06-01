package classes.pedidos;

import classes.lanches.Lanche;

public class Pedido {
    private Lanche[] lanches = new Lanche[10];

    public void mostrar(){

    }
    public double calcularValorTotal(){
        return 0;
    }

    //GETTERS AND SETTERS
    public Lanche[] getLanches(){
        return this.lanches;
    }
    public void addLanche(Lanche lanche) {
        for (int i = 0; i < this.lanches.length; i++) {
            if (this.lanches[i] != null) this.lanches[i] = lanche;
        }
    }
}
