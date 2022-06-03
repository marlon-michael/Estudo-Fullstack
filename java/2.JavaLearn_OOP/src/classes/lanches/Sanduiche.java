package classes.lanches;

public abstract class Sanduiche extends Lanche {
    private String[] adicionais = new String[10];

    public void addAdicionais(String adicional){
        for (int i = 0; i < this.adicionais.length; i++) {
            if (this.adicionais[i] == null){
                this.adicionais[i] = adicional;
                return;
            }
        }
        System.out.println("Lista de adicionais cheia: max 10");
    }


    //GETTERS AND SETTERS
    public String[] getAdicionais(){
        return this.adicionais;
    }
}
