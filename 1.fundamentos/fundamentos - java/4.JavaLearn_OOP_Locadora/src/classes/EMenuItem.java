package classes;

public enum EMenuItem {
    VOLTAR(0, "Voltar"),
    ADICIONAR_ITEM(1, "Ver avaliações"),
    BUSCAR_ITEM(2, "Avaliar");

    private int opcao;
    private String descricao;

    EMenuItem(int opcao, String descricao){
        this.opcao = opcao;
        this.descricao = descricao;
    }

    public static EMenuItem getByOpcao(int escolha){
        for (EMenuItem menu: EMenuItem.values()){
            if (menu.getOpcao() == escolha) return menu;
        }
        return null;
    }

    public int getOpcao() {
        return opcao;
    }

    public void setOpcao(int opcao) {
        this.opcao = opcao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
