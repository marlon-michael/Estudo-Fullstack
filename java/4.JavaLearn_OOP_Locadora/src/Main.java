import classes.EMenu;
import classes.EMenuItem;
import classes.Loja;
import classes.avaliacao.Avaliação;
import classes.Estante;
import classes.itens.Item;
import classes.itens.DVD;
import classes.itens.Livro;

import java.util.Scanner;


public class Main {
    public static Scanner read = new Scanner(System.in);
    public static Loja loja = new Loja();
    public static Estante estante = new Estante();

    public static void main(String[] args) {
        //todo Adicionar estante
        //todo Perguntar em que estante adicionar o item cirado
        //todo Procurar item em cada uma das estantes
        //todo Perguntar de qual estante remover
        //todo Mostrar de todas as estantes

        running: while (true){
            System.out.println("=============");
            System.out.println("MENU Locadora");
            System.out.println("=============");
            for (EMenu menu: EMenu.values()){
                System.out.println(menu.getOpcao() +" - "+ menu.getDescricao());
            }
            EMenu option = EMenu.getByOpcao(read.nextInt());
            System.out.println("================================");

            switch (option){
                case ADICIONAR_ITEM:
                    adicionaNaEstante();
                    break;
                case MOSTRAR_ITEM:
                    listarObras();
                    break;
                case BUSCAR_ITEM:
                    System.out.print("Pesquisar por título: ");
                    Item item = estante.buscarItem(read.nextLine());
                    apresentaItem(item);
                    System.out.println();
                    for (EMenuItem menu: EMenuItem.values()){
                        System.out.println(menu.getOpcao()+" - "+menu.getDescricao());
                    }
                    System.out.print("Digite a opção: ");
                    EMenuItem option2 = EMenuItem.getByOpcao(read.nextInt());

                    switch (option2){
                        case BUSCAR_ITEM:
                            item.avaliar();
                            break;
                        case ADICIONAR_ITEM:
                            for (Avaliação avaliação: item.getAvaliações()){
                                if (avaliação != null){
                                    System.out.println("Nome/Apelido: "+avaliação.getNome());
                                    System.out.println("Nota: "+avaliação.getAvaliação());
                                    System.out.println("Feedback: "+avaliação.getFeedback());
                                    System.out.println("----------------------------------");
                                }
                            }
                            break;
                        case VOLTAR: break;
                    }
                    break;
                case REMOVER_ITEM:
                    System.out.print("Excluir por posição: ");
                    estante.removerItem(read.nextInt());
                    System.out.println();
                    break;
                case SAIR:
                    System.out.println("Finalizando Aplicação");
                    break running;
            }
        }

    }

    public static void apresentaItem(Item item){
        for (int i = 0; i < estante.getItens().size(); i++){
            if (item == estante.getItens().get(i)){
                System.out.println("Id da obra: "+ i+1);
            }
        }
        System.out.println("Titulo: "+item.getTitulo());
        System.out.println("Gênero: "+item.getGenero());
        item.mostrarDetalhes();
        System.out.printf("Nota: %.1f\n",item.getAvaliaçãoTotal());
        System.out.println("Valor: R$ "+item.getValor());
        System.out.println("---------------------------");
    }

    public static void listarObras(){
        System.out.println("- - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Quantidade de itens na estante: "+estante.getQuantidadeItens());
        System.out.println("- - - - - - - - - - - - - - - - - - - - -");
        for (int i = 0; i < estante.getItens().size(); i++){
            System.out.println("Posição da obra na lista: " + i+1);
            apresentaItem(estante.getItens().get(i));
        }
    }

    public static void adicionaNaEstante(){
        Item createdItem = criaItem();
        if (createdItem == null) return;
        estante.addItem(createdItem);

        listarObras();
    }

    public static Item criaItem() {
        Item item;
        System.out.println("1 - DVD");
        System.out.println("2 - Livro");
        System.out.print("Digite o número do item que deseja: ");
        int option = read.nextInt();
        System.out.println();

        switch (option){
            case 1:
                item = new DVD();
                break;
            case 2:
                item = new Livro();
                break;
            default:
                System.err.println("------------------------------------------------");
                System.err.println("Opção inválida. Escolha uma das opções da lista");
                System.err.println("------------------------------------------------");
                return null;
        }

        read.nextLine();
        System.out.print("Digite o nome da obra");
        item.setTitulo(read.nextLine());
        System.out.println();

        System.out.print("Digite o gênero: ");
        read.nextLine();
        item.setGenero(read.nextLine());
        System.out.println();

        item.montarDetalhes(read);

        System.out.print("Digite o valor: R$ ");
        item.setValor(read.nextDouble());
        System.out.println();


        return item;
    }
}
