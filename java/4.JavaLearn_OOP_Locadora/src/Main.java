import classes.Avaliacao.Avaliação;
import classes.Estante;
import classes.itens.Item;
import classes.itens.DVD;
import classes.itens.Livro;

import java.util.Scanner;


public class Main {
    public static Scanner read = new Scanner(System.in);
    public static Estante estante = new Estante(5);

    public static void main(String[] args) {

        running: while (true){
            System.out.println("=============");
            System.out.println("MENU Locadora");
            System.out.println("=============");
            System.out.println("1 - Adicionar obra");
            System.out.println("2 - Listar obras");
            System.out.println("3 - Perquisar obra");
            System.out.println("4 - Excluir obra");
            System.out.println("0 - FECHAR MENU");
            System.out.print(" Digite sua opção>");
            int option = read.nextInt();
            System.out.println("================================");

            switch (option){
                case 1:
                    adicionaNaEstante();
                    break;
                case 2:
                    listarObras();
                    break;
                case 3:
                    System.out.print("Pesquisar por título: ");
                    Item item = estante.buscarItem(read.nextLine());
                    apresentaItem(item);
                    System.out.println();
                    System.out.println("1 - Avaliar obra");
                    System.out.println("2 - Ver avaliações");
                    System.out.println("3 - retornar ao menu");
                    System.out.print("Digite a opção: ");
                    int option2 = read.nextInt();
                    if (option2 == 1){
                        item.avaliar();
                    }
                    else if (option2 == 2){
                        for (Avaliação avaliação: item.getAvaliações()){
                            if (avaliação != null){
                                System.out.println("Nome/Apelido: "+avaliação.getNome());
                                System.out.println("Nota: "+avaliação.getAvaliação());
                                System.out.println("Feedback: "+avaliação.getFeedback());
                                System.out.println("----------------------------------");
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.print("Escluir por posição: ");
                    estante.removerItem(read.nextInt());
                    System.out.println();
                    break;
                default:
                    System.out.println("Finalizando Aplicação");
                    break running;
            }
        }

    }

    public static void apresentaItem(Item item){
        for (int i = 0; i < estante.getCapacidadeMax(); i++){
            if (item == estante.getItens()[i]){
                System.out.println("Id da obra: "+ i+1);
            }
        }
        System.out.println("Titulo: "+item.getTitulo());
        System.out.println("Gênero: "+item.getGenero());
        if (item instanceof DVD) {
            DVD filme = (DVD)item;
            System.out.println("Duração: "+filme.getDuraçao());
            System.out.println("Ano de lançamento: "+filme.getAnoLançado());
            System.out.println("Diretor: "+filme.getDiretor());
        }
        else if (item instanceof Livro) {
            Livro livro = (Livro)item;
            System.out.println("Editora: "+livro.getEdiçao());
            System.out.println("Número de paginas: "+livro.getQuantidadePaginas());
            System.out.println("Ano de publicação: "+livro.getAnoPublicado());
            System.out.println("Autor: "+livro.getAutor());
        }
        System.out.printf("Nota: %.1f\n",item.getAvaliaçãoTotal());
        System.out.println("Valor: R$ "+item.getValor());
        System.out.println("---------------------------");
    }

    public static void listarObras(){
        System.out.println("- - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Quantidade de itens na estante: "+estante.getQuantidadeItens());
        System.out.println("- - - - - - - - - - - - - - - - - - - - -");
        for (int i = 0; i < estante.getCapacidadeMax(); i++){
            System.out.println("Posição da obra na lista: " + i+1);
            apresentaItem(estante.getItens()[i]);
        }
    }

    public static void adicionaNaEstante(){
        while(!estante.isEstanteCheia()) {
            Item createdItem = criaItem();
            if (createdItem == null) continue;
            estante.addItem(createdItem);

            listarObras();
        }
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

        if (item instanceof DVD){
            DVD filme = (DVD)item;
            System.out.print("Digite a duração: ");
            filme.setDuraçao(read.nextDouble());
            System.out.println();

            System.out.print("Digite o ano de lançamento: ");
            filme.setAnoLançado(read.nextInt());
            System.out.println();

            System.out.print("Digite o diretor do filme: ");
            read.nextLine();
            filme.setDiretor(read.nextLine());
            System.out.println();
        }
        else if (item instanceof Livro) {
            Livro livro = (Livro)item;
            System.out.print("Digite a edição do livro: ");
            livro.setEdiçao(read.nextInt());
            System.out.println();

            System.out.print("Digite a quantidade de paginas: ");
            livro.setQuantidadePaginas(read.nextInt());
            System.out.println();

            System.out.print("Digite o ano de publicação: ");
            livro.setAnoPublicado(read.nextInt());
            System.out.println();

            System.out.print("Digite o autor do livro: ");
            read.nextLine();
            livro.setAutor(read.nextLine());
            System.out.println();
        }

        System.out.print("Digite o valor: R$ ");
        item.setValor(read.nextDouble());
        System.out.println();


        return item;
    }
}
