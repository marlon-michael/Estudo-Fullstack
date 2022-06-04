package classes.lanches;

public class MiniPizza extends Lanche{
    private boolean borda_recheada = false;
    private String sabor;
    private String sabor_borda;

    public MiniPizza(){
        this.setNomeLanche("Mini Pizza");
        this.addIngrediente("molho de tomate");
        this.addIngrediente("massa de pizza");
    }

    public void setSabor(String sabor){
        this.sabor = sabor;
        switch(sabor.toUpperCase()){
            case "4 QUEIJOS":
                this.addIngrediente("mussarela");
                this.addIngrediente("catupiry");
                this.addIngrediente("provolone");
                break;
            case "CALABRESA":
                this.addIngrediente("calabresa");
                break;
            case "FRANGO COM CATUPIRY":
                this.addIngrediente("frango");
                this.addIngrediente("catupiry");
                break;
            case "MARGUERITA":
                this.addIngrediente("manjericão");
                this.addIngrediente("tomate");
                break;
            case "PORTUGUESA":
                this.addIngrediente("presunto");
                this.addIngrediente("cebola");
                this.addIngrediente("ovo");
                break;
        }
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
