package classes;

public class MiniPizza extends Lanche{
    private boolean borda_recheada = false;
    private String sabor;
    private String sabor_borda;

    public MiniPizza(){
        this.setNomeLanche("Mini Pizza");
        this.adicionar_ingrediente("molho de tomate");
        this.adicionar_ingrediente("massa de pizza");
    }

    public void setSabor(String sabor){
        this.setSabor(sabor);
        switch(sabor.toUpperCase()){
            case "4 QUEIJOS":
                this.adicionar_ingrediente("mussarela");
                this.adicionar_ingrediente("catupiry");
                this.adicionar_ingrediente("provolone");
                break;
            case "CALABRESA":
                this.adicionar_ingrediente("calabresa");
                break;
            case "FRANGO COM CATUPIRY":
                this.adicionar_ingrediente("frango");
                this.adicionar_ingrediente("catupiry");
                break;
            case "MARGUERITA":
                this.adicionar_ingrediente("manjericão");
                this.adicionar_ingrediente("tomate");
                break;
            case "PORTUGUESA":
                this.adicionar_ingrediente("presunto");
                this.adicionar_ingrediente("cebola");
                this.adicionar_ingrediente("ovo");
                break;
        }
    }

    @Override
    public void mostrar(){
        if (this.isBordaRecheada()){
            System.out.println("Borda Recheada Sabor: " + this.getSaborBorda());
        }
        else System.out.println("Borda Fina");
        super.mostrar();
    }

    //GETTERS AND SETTERS
    public String getSaborBorda(){
        return this.sabor_borda;
    }
    public void setSaborBorda(String sabor_borda){
        this.sabor_borda = sabor_borda;
    }
    public boolean isBordaRecheada(){
        return this.borda_recheada;
    }
    public void setBordaRecheada(boolean borda_recheada){
        this.borda_recheada = borda_recheada;
    }
    public String getSabor(){
        return this.sabor;
    }
}
