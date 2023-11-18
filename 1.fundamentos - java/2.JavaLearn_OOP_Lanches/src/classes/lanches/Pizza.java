package classes.lanches;

public class Pizza extends MiniPizza{
    private String tamanho;

    public Pizza (){
        this.setNomeLanche("Pizza");
    }


    //GETTERS AND SETTERS
    public String getTamanho(){
        return this.tamanho;
    }
    public void setTamanho(String tamanho){
        this.tamanho = tamanho;
    }
}
