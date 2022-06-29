package classes;

public enum ValorCarta {
    AS("As"),
    DOIS("Dois"),
    TRES("Tres"),
    QUATRO("Quatro"),
    CINCO("Cinco"),
    SEIS("Seis"),
    SETE("Sete"),
    OITO("Oito"),
    NOVE("Nove"),
    DEZ("Dez"),
    VALETE("Valete"),
    RAINHA("Rainha"),
    REI("Rei");

    private final String valorCarta;

    ValorCarta(String valorCarta){
        this.valorCarta = valorCarta;
    }

    public String getValorCarta(){
        return this.valorCarta;
    }
}
