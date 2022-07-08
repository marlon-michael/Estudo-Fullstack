package classes;

public enum EMenu {
    SAIR(0, "Sair"),
    ADICIONAR_ITEM(1, "Adicionar item a estante"),
    BUSCAR_ITEM(2, "Buscar item"),
    REMOVER_ITEM(3, "Remover item"),
    MOSTRAR_ITEM(4, "Mostrar item"),
    ADICIONAR_ESTANTE(5, "Adicionar Estante");

    private int opcao;
    private String descricao;

    EMenu(int opcao, String descricao){
        this.opcao = opcao;
        this.descricao = descricao;
    }

    public static EMenu getByOpcao(int escolha){
        for (EMenu menu: EMenu.values()){
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
