package classes;

import classes.itens.Item;

import java.util.ArrayList;

public class Estante{
    private int capacidadeMax;
    private ArrayList<Item> itens = new ArrayList<>();

    public Estante(int capacidadeMax){
        this.setCapacidadeMax(capacidadeMax);
    }

    public boolean isEstanteCheia(){
        return this.getQuantidadeItens() == this.getCapacidadeMax();
    }

    public int getQuantidadeItens(){
        return this.itens.size();
    }

    public Item buscarItem(String titulo){
        return this.getItens().stream().filter(
                i->i.getTitulo().toLowerCase().contains(titulo.toLowerCase())
        ).findFirst().orElse(null);

        /*for (Item item: this.getItens()){
            if (item.getTitulo().toLowerCase().contains(titulo.toLowerCase())) return item;
        }
        return null;*/
    }

    public boolean addItem(Item item){
        if (isEstanteCheia()) return false;
        this.itens.add(item);
        return true;
    }

    public Item removerItem(int posição) {
        return this.getItens().remove(posição);
    }

    //GETTERS AND SETTERS
    public int getCapacidadeMax() {
        return capacidadeMax;
    }
    public void setCapacidadeMax(int capacidadeMax) {
        this.capacidadeMax = capacidadeMax;
    }
    public ArrayList<Item> getItens() {
        return itens;
    }
    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }
}
