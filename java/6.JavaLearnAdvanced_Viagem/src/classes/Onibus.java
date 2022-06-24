package classes;

import java.util.ArrayList;

public class Onibus implements MeioDeTransporte {
    private ArrayList<ArrayList<Assento>> assentos;

    @Override
    public boolean verificaOcupacao(String assento) {
        return false;
    }
    @Override
    public int quantidadeLivre() {
        return 0;
    }
    @Override
    public void mostrarAssentos() {
    }
    @Override
    public Assento getAssento(String assento) {
        return null;
    }

    public ArrayList<ArrayList<Assento>> getAssentos() {
        return assentos;
    }
    public void setAssentos(ArrayList<ArrayList<Assento>> assentos) {
        this.assentos = assentos;
    }
}
