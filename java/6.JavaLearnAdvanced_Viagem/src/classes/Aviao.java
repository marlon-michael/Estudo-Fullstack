package classes;

import java.util.ArrayList;

public class Aviao implements MeioDeTransporte {
    private ArrayList<ArrayList<AssentoVoo>> assentos;

    @Override
    public boolean verificaOcupacao(String assento) {
        for (ArrayList<AssentoVoo> assento_i: this.getAssentos()){
            for (AssentoVoo assento_j: assento_i){
                if (assento_j.getCodigo().equalsIgnoreCase(assento)){
                    return assento_j.isOcupado();
                }

            }
        }
        System.out.println("\nAssento não existe\n");
        return false;
    }
    @Override
    public int quantidadeLivre() {
        int count = 0;
        for (ArrayList<AssentoVoo> assento_i: this.getAssentos()){
            count += assento_i.size();
        }
        return count;
    }
    @Override
    public void mostrarAssentos() {
        System.out.println("ASSENTOS:");
        for (ArrayList<AssentoVoo> assento_i: this.getAssentos()){
            for (AssentoVoo assento_j: assento_i){
                System.out.println("Classe: "+assento_j.getClasse()+" | Codigo: "+assento_j.getCodigo()+" | Ocupado: "+assento_j.isOcupado()+"  ");
            }
            System.out.println();
        }
    }
    @Override
    public Assento getAssento(String assento) {
        for (ArrayList<AssentoVoo> assento_i: this.getAssentos()){
            for (AssentoVoo assento_j: assento_i){
                if (assento_j.getCodigo().equalsIgnoreCase(assento)){
                    assento_j.ocupar();
                    return assento_j;
                }
            }
        }
        
        return null;
    }

    public ArrayList<ArrayList<AssentoVoo>> getAssentos() {
        return assentos;
    }
    public void setAssentos(ArrayList<ArrayList<AssentoVoo>> assentos) {
        this.assentos = assentos;
    }
}
