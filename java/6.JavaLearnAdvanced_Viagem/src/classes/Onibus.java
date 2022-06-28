package classes;

import java.util.ArrayList;

public class Onibus implements MeioDeTransporte {
    private ArrayList<AssentoOnibus> assentos = new ArrayList<>();
    boolean leito = false;

    public Onibus(int linhasCadeira){
        for (int i = 0; i < linhasCadeira*4; i++){
            AssentoOnibus assento = new AssentoOnibus();
            assento.setLugar(""+(i+1));
            assentos.add(assento);
        }
    }

    @Override
    public boolean verificaOcupacao(String assento) {
        for (AssentoOnibus assento_i: this.getAssentos()){
            if (assento_i.getLugar().equalsIgnoreCase(assento)){
                return assento_i.isOcupado();
            }
        }
        System.out.println("\nAssento não existe\n");
        return false;
    }

    @Override
    public int quantidadeLivre() {
        int count = 0;
        for (AssentoOnibus assento_i: this.getAssentos()){
            if (!assento_i.isOcupado()) count += 1;
        }
        return count;
    }

    @Override
    public void mostrarAssentos() {
        /*System.out.println("ASSENTOS:");
        for (AssentoOnibus assento_i: this.getAssentos()){
            System.out.println("Lugar: "+assento_i.getLugar()+" | Ocupado: "+assento_i.isOcupado()+"  ");
        }*/

        int i = 0;
        for (AssentoOnibus assento: this.getAssentos()){
            i++;
            if (assento.isOcupado()) System.out.print("[XX] ");
            else {
                if (assento.getLugar().length()<2) System.out.print("[0"+assento.getLugar()+"] ");
                else System.out.print("["+assento.getLugar()+"] ");
            }

            if (i == 2) System.out.print("|| ");
            else if (i == 4) {
                System.out.println();
                i = 0;
            }
        }
    }

    @Override
    public AssentoOnibus getAssento(String assento) {
        for (AssentoOnibus assento_i: this.getAssentos()){
            if (assento_i.getLugar().equalsIgnoreCase(assento)){
                assento_i.ocupar();
                return assento_i;
            }
        }
        System.out.println("\nAssento não existe\n");
        return null;
    }

    //GETTERS AND SETTERS
    public ArrayList<AssentoOnibus> getAssentos() {
        return this.assentos;
    }
    public void setAssentos(ArrayList<AssentoOnibus> assentos) {
        this.assentos = assentos;
    }
    public boolean isLeito() {
        return this.leito;
    }
    public void setLeito(boolean leito) {
        this.leito = leito;
    }
}
