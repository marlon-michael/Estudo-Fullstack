package classes;

import java.util.UUID;

public class Tarefa {
    private String uuid;
    private String nome;
    private String descricao;
    private boolean completa;
    private int ordem;

    public Tarefa(){
        this.setUUID(UUID.randomUUID().toString());
    }

    public void completar(){
        this.setCompleta(true);
    }

    //GETTERS AND SETTERS
    public String getUUID(){
        return this.uuid;
    }
    public void setUUID(String uuid) {
        this.uuid = uuid;
    }
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getDescricao(){
        return this.descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public boolean isCompleta(){
        return this.completa;
    }
    public void setCompleta(boolean completa){
        this.completa = completa;
    }
    public int getOrdem(){
        return this.ordem;
    }
    public void setOrdem(int ordem){
        this.ordem = ordem;
    }
}
