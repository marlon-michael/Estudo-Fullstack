package classes.itens;

public class DVD extends Item{
    private String diretor;
    private double duraçao;
    private int anoLançado;

    //GETTERS AND SETTERS
    public String getDiretor() {
        return diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    public double getDuraçao() {
        return duraçao;
    }
    public void setDuraçao(double duraçao) {
        this.duraçao = duraçao;
    }
    public int getAnoLançado() {
        return anoLançado;
    }
    public void setAnoLançado(int anoLançado) {
        this.anoLançado = anoLançado;
    }
}
