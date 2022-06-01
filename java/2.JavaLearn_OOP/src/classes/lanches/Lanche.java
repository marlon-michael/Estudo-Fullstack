package classes.lanches;

public abstract class Lanche {
    private String nome_lanche = "Lanche";
    private String[] ingredientes = new String[10];
    private double valor;

    public void adicionar_ingrediente(String ingrediente){
        for (int i = 0; i < this.ingredientes.length; i++) {
            if (this.ingredientes[i] == null){
                this.ingredientes[i] = ingrediente;
                return;
            }
        }
        System.out.println("Lista de ingredientes cheia: max 10");
    }

    public void mostrar(){
        if (this instanceof MiniPizza) System.out.println(" - - - "+this.getNomeLanche()+" - "+((MiniPizza) this).getSabor()+" - - -");
        else System.out.println(" - - - "+this.getNomeLanche()+" - - -");
        System.out.print("Ingredientes: ");
        for (String ingrediente : this.getIngredientes()) {
            if (ingrediente != null) System.out.print(ingrediente + "  ");
        }
        System.out.println();
        System.out.println("Valor: RS"+this.getValor());
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
