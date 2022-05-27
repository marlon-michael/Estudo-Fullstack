package classes;

public abstract class Sanduiche extends Lanche {
    public String[] adicionais = new String[10];

    public void add_adicional(String adicional){
        for (int i = 0; i < this.adicionais.length; i++) {
            if (this.adicionais[i] != null){
                this.adicionais[i] = adicional;
                return;
            }
        }
    }

    @Override
    public void mostrar(){
        super.mostrar();
        if (this.adicionais[0] != null) {
            System.out.println("ADICIONAIS: ");
            for (String adicional : this.adicionais) {
                if (adicional != null) {
                    System.out.print(adicional + "  ");
                }
            }
        }
    }
}
