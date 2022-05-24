package classes;

public class MistoQuente extends Lanche{
    public MistoQuente(){
        this.nome_lanche = "Misto Quente";
        this.adicionar_ingrediente("queijo");
        this.adicionar_ingrediente("presunto");
        this.adicionar_ingrediente("pão de sanduiche");
    }
}
