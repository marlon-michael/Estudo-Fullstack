package classes;

public class Carta {
    private Naipe naipe;
    private ValorCarta valor;
    private int valorReal;

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

    public int getValorReal() {
        return valorReal;
    }

    public void setValorReal(int valorReal) {
        this.valorReal = valorReal;
    }

    public String toString(){
        return getValor()+" de "+getNaipe();
    }
}
