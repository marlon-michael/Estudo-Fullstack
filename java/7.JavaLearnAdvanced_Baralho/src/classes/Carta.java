package classes;

public class Carta {
    private Naipe naipe;
    private ValorCarta valor;

    //GETTERS AND SETTERS
    public Naipe getNaipe() {
        return naipe;
    }
    public void setNaipe(Naipe naipe) {
        this.naipe = naipe;
    }
    public ValorCarta getValor() {
        return valor;
    }
    public void setValor(ValorCarta valor) {
        this.valor = valor;
    }

    public String toString(){
        return getValor()+" de "+getNaipe();
    }
}
