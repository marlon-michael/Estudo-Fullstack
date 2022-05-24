package classes;

public class Lanche {
    public String nome_lanche = "Lanche";
    public String[] ingredientes = new String[10];
    public double valor = 20.0;
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
        System.out.println(" - - - "+this.nome_lanche+" - - -");
        System.out.print("Ingredientes: ");
        for (String ingrediente : this.ingredientes) {
            if (ingrediente != null) System.out.print(ingrediente + "  ");
        }
        System.out.println();
        System.out.println("Valor: RS"+this.valor);
        System.out.println();
    }
}
