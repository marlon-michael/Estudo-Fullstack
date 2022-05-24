package classes;

public class MiniPizza extends Lanche{
    public MiniPizza(){
        this.nome_lanche = "Mini Pizza";
        this.adicionar_ingrediente("calabresa");
        this.adicionar_ingrediente("mussarela");
        this.adicionar_ingrediente("massa de tomate");
    }

}
