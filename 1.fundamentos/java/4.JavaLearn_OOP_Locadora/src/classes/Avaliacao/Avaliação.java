package classes.Avaliacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Avaliação {
    private double avaliação;
    private String nome;
    private String feedback;
    private LocalDateTime time;

    public String setTimeToNow(){
        this.setTime(LocalDateTime.now());
        return this.getTime();
    }

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
    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return time.format(formatter);
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
