package classes.lanches;

public class XBurguer extends Sanduiche {
    private boolean aberto = false;

    public XBurguer(){
        this.setNomeLanche("X-Burguer");
        this.addIngrediente("queijo");
        this.addIngrediente("presunto");
        this.addIngrediente("hamburguer");
        this.addIngrediente("pão brioche");
    }


    //GETTERS AND SETTERS
    public boolean isAberto(){
        return this.aberto;
    }
    public void setAberto(boolean aberto){
        this.aberto = aberto;
    }
}
