package classes;

public class Pizza extends MiniPizza{
    private String tamanho;

    public Pizza (){
        this.setNomeLanche("Pizza");
    }

    public void mostrar(){
        super.mostrar();
        System.out.println("Tamanho: "+this.getTamanho());
    }

    //GETTERS AND SETTERS
    public String getTamanho(){
        return this.tamanho;
    }
    public void setTamanho(String tamanho){
        this.tamanho = tamanho;
    }
}
