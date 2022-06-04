package classes;

public class ListaTarefas {
    private int tamanhoLista;
    private String nomeLista;
    private Tarefa[] tarefas;

    public ListaTarefas(int tamanhoLista){
        this.setTamanhoLista(tamanhoLista);
        this.setTarefas(new Tarefa[tamanhoLista]);
    }

    public boolean addTarefa(Tarefa tarefa) {
        for (int i = 0; i < this.tarefas.length; i++) {
            if (this.tarefas[i] == null) {
                this.tarefas[i] = tarefa;
                return true;
            }
        }
        return false;
    }

    public Tarefa buscarTarefa(int posicao){
        if (posicao > this.getTamanhoLista()-1 || posicao < 0) {System.out.println("ERROR: Index out of range");return null;}
        return this.getTarefas()[posicao];
    }

    public boolean alterarOrdemTarefa(Tarefa tarefa, int ordem){
        tarefa.setOrdem(ordem);
        return false;
    }

    //GETTERS AND SETTERS
    public int getTamanhoLista() {
        return tamanhoLista;
    }
    public void setTamanhoLista(int tamanhoLista) {
        this.tamanhoLista = tamanhoLista;
    }
    public String getNomeLista() {
        return nomeLista;
    }
    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }
    public Tarefa[] getTarefas() {
        return tarefas;
    }
    public void setTarefas(Tarefa[] tarefas) {
        this.tarefas = tarefas;
    }
}
