package classes;

public enum Naipe {
    ESPADAS("Naipe"), PAUS("Paus"), COPAS("Copas"), OURO("Ouro");

    private final String naipe;

    Naipe(String naipe){
        this.naipe = naipe;
    }

    public String getNaipe(){
        return this.naipe;
    }
}
