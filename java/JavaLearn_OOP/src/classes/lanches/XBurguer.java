package classes.lanches;

public class XBurguer extends Sanduiche {
    private boolean aberto = false;

    public XBurguer(){
        this.setNomeLanche("X-Burguer");
        this.adicionar_ingrediente("queijo");
        this.adicionar_ingrediente("presunto");
        this.adicionar_ingrediente("hamburguer");
        this.adicionar_ingrediente("pão brioche");
    }

    @Override
    public void mostrar(){
        super.mostrar();
        if (this.isAberto()) System.out.println("Lanche Aberto");
        else System.out.println("Lanche Fechado");
    }

    //GETTERS AND SETTERS
    public boolean isAberto(){
        return this.aberto;
    }
    public void setAberto(boolean aberto){
        this.aberto = aberto;
    }
}
