package classes;

import classes.itens.Item;

public class Estante{
    private int capacidadeMax;
    private Item[] itens;

    public Estante(int capacidadeMax){
        this.setCapacidadeMax(capacidadeMax);
        this.setItens(new Item[capacidadeMax]);
    }

    public boolean estanteCheia(){
        if (quantidadeItens() == this.getCapacidadeMax()) return true;
        return false;
    }

    public int quantidadeItens(){
        int contador = 0;
        for (Item item: this.getItens()){
            if (item != null) contador++;
        }
        return contador;
    }

    public Item buscarItem(String titulo){
        for (Item item: this.getItens()){
            if (item.getTitulo().equalsIgnoreCase(titulo)) return item;
        }
        return null;
    }

    public boolean addItem(Item item){
        for (int i = 0; i < this.getCapacidadeMax(); i++){
            if (this.getItens()[i] == null) {
                this.getItens()[i] = item;
                return true;
            }
        }
        return false;
    }

    public Item removerItem(int posição) {
        if (posição > this.getCapacidadeMax()-1 || posição < 0) {
            System.err.println("Index out of range");
            return null;
        }
        this.getItens()[posição] = null;
        return null;
    }

    //GETTERS AND SETTERS
    public int getCapacidadeMax() {
        return capacidadeMax;
    }
    public void setCapacidadeMax(int capacidadeMax) {
        this.capacidadeMax = capacidadeMax;
    }
    public Item[] getItens() {
        return itens;
    }
    public void setItens(Item[] itens) {
        this.itens = itens;
    }
}
