package classes;

import java.util.ArrayList;

public class Aviao implements MeioDeTransporte {
    private ArrayList<AssentoVoo> assentos = new ArrayList<>();

    public Aviao(int linhasCadeiraLuxo, int linhasCadeiraEconomica){
        for (int i = 0; i < linhasCadeiraLuxo*4; i++){
            AssentoVoo assento = new AssentoVoo();
            assento.setClasse("Luxo");
            assento.setCodigo(""+(i+1));
            assentos.add(assento);
        }
        for (int i = 0; i < linhasCadeiraLuxo*6; i++){
            AssentoVoo assento = new AssentoVoo();
            assento.setClasse("Economico");
            assento.setCodigo(""+(i+1));
            assentos.add(assento);
        }
    }

    public boolean verificaOcupacao(String assento) {
        for (AssentoVoo assento_i: this.getAssentos()){
            if (assento_i.getCodigo().equalsIgnoreCase(assento)){
                return assento_i.isOcupado();
            }
        }
        System.out.println("\nAssento não existe\n");
        return false;
    }

    public int quantidadeLivre() {
        int count = 0;
        for (AssentoVoo assento_i: this.getAssentos()){
            if (!assento_i.isOcupado()) count += 1;
        }
        return count;
    }

    public void mostrarAssentos() {
        System.out.println("ASSENTOS:");
        for (AssentoVoo assento_i: this.getAssentos()){
            System.out.println("Classe: "+assento_i.getClasse()+" | Codigo: "+assento_i.getCodigo()+" | Ocupado: "+assento_i.isOcupado()+"  ");
        }
    }

    public AssentoVoo getAssento(String assento, String classe) {
        for (AssentoVoo assento_i: this.getAssentos()){
            if (assento_i.getCodigo().equalsIgnoreCase(assento) && assento_i.getClasse().equalsIgnoreCase(classe)){
                assento_i.ocupar();
                return assento_i;
            }
        }
        System.out.println("\nAssento não existe\n");
        return null;
    }

    public AssentoVoo getAssento(String assento) {
        for (AssentoVoo assento_i: this.getAssentos()){
            if (assento_i.getCodigo().equalsIgnoreCase(assento)){
                assento_i.ocupar();
                return assento_i;
            }
        }
        System.out.println("\nAssento não existe\n");
        return null;
    }

    //GETTERS AND SETTERS
    public ArrayList<AssentoVoo> getAssentos() {
        return assentos;
    }
    public void setAssentos(ArrayList<AssentoVoo> assentos) {
        this.assentos = assentos;
    }
}
