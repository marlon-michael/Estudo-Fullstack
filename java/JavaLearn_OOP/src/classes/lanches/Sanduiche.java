package classes.lanches;

public abstract class Sanduiche extends Lanche {
    private String[] adicionais = new String[10];

    public void setAdicionais(String adicional){
        for (int i = 0; i < this.adicionais.length; i++) {
            if (this.adicionais[i] == null){
                this.adicionais[i] = adicional;
                return;
            }
        }
        System.out.println("Lista de adicionais cheia: max 10");
    }

    @Override
    public void mostrar(){
        super.mostrar();
        if (this.adicionais[0] != null) {
            System.out.print("ADICIONAIS: ");
            for (String adicional : this.adicionais) {
                if (adicional != null) {
                    System.out.print(adicional + "  ");
                }
            }
            System.out.println();
        }
    }

    //GETTERS AND SETTERS
    public String[] getAdicionais(){
        return this.adicionais;
    }
}
