package classes.lanches;

public abstract class Lanche {
    private String nome_lanche = "Lanche";
    private String[] ingredientes = new String[10];
    private double valor;

    public void addIngrediente(String ingrediente){
        for (int i = 0; i < this.ingredientes.length; i++) {
            if (this.ingredientes[i] == null){
                this.ingredientes[i] = ingrediente;
                return;
            }
        }
        System.out.println("Lista de ingredientes cheia: max 10");
    }


    //GETTERS AND SETTERS
    public String getNomeLanche(){
        return this.nome_lanche;
    }
    public void setNomeLanche(String nome_lanche){
        this.nome_lanche = nome_lanche;
    }
    public double getValor(){
        return this.valor;
    }
    public void setValor(double valor){
        this.valor = valor;
    }
    public String[] getIngredientes(){
        return this.ingredientes;
    }
}
