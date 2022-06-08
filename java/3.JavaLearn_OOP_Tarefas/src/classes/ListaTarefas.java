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
        if (tarefa.getOrdem() < 0 || tarefa.getOrdem() >= this.getTamanhoLista()) return false;

        for (int i = 0; i < this.tarefas.length; i++) {
            if (this.getTarefas()[i] == null) {
                this.getTarefas()[i] = tarefa;
                return true;
            }
            else if (this.getTarefas()[i].getOrdem() == tarefa.getOrdem()) break;
        }
        return false;
    }

    public Tarefa buscarTarefa(int ordem){
        for (Tarefa tarefa: this.getTarefas()){
            if (tarefa != null && tarefa.getOrdem() == ordem) return tarefa;
        }
        return null;
    }

    public boolean alterarOrdemTarefa(int antigaOrdem, int novaOrdem){
        Tarefa tarefaAntiga = buscarTarefa(antigaOrdem);
        Tarefa tarefaNova = buscarTarefa(novaOrdem);

        if (tarefaAntiga == null || tarefaNova == null) return false;

        tarefaAntiga.setOrdem(novaOrdem);
        tarefaNova.setOrdem(antigaOrdem);

        return true;
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
