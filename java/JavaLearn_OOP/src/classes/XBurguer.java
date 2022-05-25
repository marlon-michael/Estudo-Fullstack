package classes;

public class XBurguer extends Lanche {
    public boolean aberto = false;

    public XBurguer(){
        this.nome_lanche = "X-Burguer";
        this.adicionar_ingrediente("queijo");
        this.adicionar_ingrediente("presunto");
        this.adicionar_ingrediente("hamburguer");
        this.adicionar_ingrediente("pão brioche");
    }

    @Override
    public void mostrar(){
        if (this.aberto) System.out.println("Lanche Aberto");
        else System.out.println("Lanche Fechado");
        super.mostrar();
    }
}
