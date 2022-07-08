package classes;

public enum EMneu {
    XSALADA(1, "X-Salada"),
    XBURGUER(2, "X-Burguer"),
    MISTOQUENTE(3, "Misto Quente"),
    HOTDOG(4, "Hot Dog"),
    MINIPIZZA(5, "Mini Pizza"),
    PIZZA(6, "Pizza");

    final int num;
    final String nome;

    EMneu(int num, String nome){
        this.num = num;
        this.nome = nome;
    }

    public getByValor(){
        
    }

    //GETTERS AND SETTERS
    public int getNum() {
        return num;
    }

    public String getNome() {
        return nome;
    }
}
