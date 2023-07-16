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
        int i = 0;
        for (AssentoVoo assento: this.getAssentos()){
            i++;
            if (assento.isOcupado()) System.out.print("[XX] ");
            else {
                if (assento.getCodigo().length()<2) System.out.print("[0"+assento.getCodigo()+"] ");
                else System.out.print("["+assento.getCodigo()+"] ");
            }

            if (assento.getClasse().equalsIgnoreCase("Luxo")) {
                if (i == 2) System.out.print("     ||      ");
                else if (i == 4){
                    System.out.println();
                    i = 0;
                }
            }
            else if (assento.getClasse().equalsIgnoreCase("Economico")){
                if (i == 3) System.out.print("|| ");
                else if (i == 6) {
                    System.out.println();
                    i = 0;
                }
            }
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
