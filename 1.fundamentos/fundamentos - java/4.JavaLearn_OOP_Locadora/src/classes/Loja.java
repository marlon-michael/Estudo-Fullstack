package classes;

import java.util.HashMap;

public class Loja {
    private HashMap<String, Estante> estantes = new HashMap<>();

    //GETTERS AND SETTERS
    public HashMap<String, Estante> getEstantes() {
        return estantes;
    }

    public void setEstantes(HashMap<String, Estante> estantes) {
        this.estantes = estantes;
    }
}
