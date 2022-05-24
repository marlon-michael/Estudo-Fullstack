package classes;

public class HotDog extends Lanche{
    public HotDog(){
        this.nome_lanche = "Hot Dog";
        this.adicionar_ingrediente("pure de batata");
        this.adicionar_ingrediente("batata palha");
        this.adicionar_ingrediente("milho");
        this.adicionar_ingrediente("salcicha");
        this.adicionar_ingrediente("pão");
    }
}
