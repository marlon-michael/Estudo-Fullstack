package classes.lanches;

import java.util.HashMap;

public abstract class Sanduiche extends Lanche {
    private HashMap<String, Double> adicionais = new HashMap<>();

    @Override
    public double getValor(){
        double valorTotal = super.getValor();
        for (String adicional: getAdicionais().keySet()){
            valorTotal += getAdicionais().get(adicional);
        }
        return valorTotal;
    }

    //GETTERS AND SETTERS
    public void addAdicionais(String adicional, double valor){
        this.adicionais.put(adicional, valor) ;
    }
    public HashMap<String, Double> getAdicionais(){
        return this.adicionais;
    }
}
