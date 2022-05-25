package classes;

public class MiniPizza extends Lanche{
    public boolean borda_recheada = false;
    public String sabor_borda;
    public MiniPizza(){
        this.nome_lanche = "Mini Pizza";
        this.adicionar_ingrediente("calabresa");
        this.adicionar_ingrediente("mussarela");
        this.adicionar_ingrediente("massa de tomate");
    }

    @Override
    public void mostrar(){
        if (this.borda_recheada){
            System.out.println("Borda Recheada Sabor: " + this.sabor_borda);
        }
        else System.out.println("Borda Fina");
        super.mostrar();
    }
}
