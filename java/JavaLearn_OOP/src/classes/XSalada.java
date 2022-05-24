package classes;

public class XSalada extends XBurguer {
    public XSalada(){
        this.nome_lanche = "X-Salada";
        this.adicionar_ingrediente("alface");
        this.adicionar_ingrediente("tomate");
        this.adicionar_ingrediente("milho");
        this.adicionar_ingrediente("picles");
    }
}
