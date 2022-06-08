package classes;

import java.util.UUID;

public class Tarefa {
    private String uuid;
    private String nome;
    private String descricao;
    private boolean completa;
    private int ordem;
    private CheckListItem[] checklistItem;

    public Tarefa(){
        this.setUUID(UUID.randomUUID().toString());
    }

    public void completar(){
        this.setCompleta(true);
    }

    public boolean temCheckList(){
        return this.getChecklistItem() != null;
    }

    public void criarCheckList(int tamanho){
        this.setChecklistItem(new CheckListItem[tamanho]);
    }

    public boolean addCheckListItem(CheckListItem item){
        for (int i = 0; i < this.getChecklistItem().length; i++) {
            if (this.getChecklistItem()[i] == null) {
                this.getChecklistItem()[i] = item;
                return true;
            }
        }
        return false;
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
    public CheckListItem[] getChecklistItem() {
        return checklistItem;
    }
    public void setChecklistItem(CheckListItem[] checklistItem) {
        this.checklistItem = checklistItem;
    }
//    public void addChecklist(CheckListItem checklist) {
//        for (int i = 0; i < this.checklist.length; i++) {
//            if (this.checklist[i] == null) {
//                this.checklist[i] = checklist;
//                return;
//            }
//        }
//    }
}
