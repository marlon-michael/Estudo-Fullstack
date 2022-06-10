package classes;

import classes.itens.Item;

public class Estante{
    private int capacidadeMax;
    private Item[] itens;

    public Estante(int capacidadeMax){
        this.setCapacidadeMax(capacidadeMax);
        this.setItens(new Item[capacidadeMax]);
    }

    public boolean isEstanteCheia(){
        return this.getQuantidadeItens() == this.getCapacidadeMax();
    }

    public int getQuantidadeItens(){
        int contador = 0;
        for (Item item: this.getItens()){
            if (item != null) contador++;
        }
        return contador;
    }

    public Item buscarItem(String titulo){
        for (Item item: this.getItens()){
            //check if item != null is necessary
            if (item != null) {
                if (item.getTitulo().toLowerCase().contains(titulo.toLowerCase())) return item;
            }
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
        Item item = this.getItens()[posição];
        this.getItens()[posição] = null;
        return item;
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
