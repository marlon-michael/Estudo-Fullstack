package classes.avaliacao;

public class Avaliação {
    private double avaliação;
    private String nome;
    private String feedback;

    //GETTERS AND SETTERS
    public double getAvaliação() {
        return avaliação;
    }
    public void setAvaliação(double classificação) {
        this.avaliação = classificação;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
