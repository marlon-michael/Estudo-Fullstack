package classes;

public interface MeioDeTransporte {

    boolean verificaOcupacao(String assento);

    int quantidadeLivre();

    void mostrarAssentos();

    Assento getAssento(String assento);
}
