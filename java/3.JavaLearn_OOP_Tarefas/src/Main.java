import classes.CheckListItem;
import classes.ListaTarefas;
import classes.Tarefa;
import java.util.Scanner;

public class Main {
    public static Scanner read = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.print("Digite a quantidades de tarefas na lista: ");
        ListaTarefas listaTarefas = new ListaTarefas(read.nextInt());
        System.out.println();

        System.out.print("Digite o nome da lista de tarefas: ");
        read.nextLine();
        listaTarefas.setNomeLista(read.nextLine());
        System.out.println();

        while (true) {
            Tarefa tarefa = criarTarefa();
            if (!listaTarefas.addTarefa(tarefa)){
                System.err.println("Impossivel adicionar tarefa!");
                break;
            }
            System.out.print("Deseja adicionar mais tarefas? (S/N): ");
            String option = read.next();
            if (option.equalsIgnoreCase("N")) break;
        }

    }

    public static Tarefa criarTarefa(){
        Tarefa tarefa = new Tarefa();

        System.out.print("Digite o nome da tarefa: ");
        tarefa.setNome(read.nextLine());
        System.out.println();

        System.out.print("Digite a descrição da tarefa: ");
        tarefa.setDescricao(read.nextLine());
        System.out.println();

        System.out.print("Digite a ordem da tarefa: ");
        tarefa.setOrdem(read.nextInt());
        System.out.println();

        System.out.print("A tarefa possui checklist? (S/N): ");
        String hasCheckList = read.next();
        System.out.println();

        if (hasCheckList.equalsIgnoreCase("S")){
            System.out.print("Informe o tamanho da checklist: ");
            tarefa.criarCheckList(read.nextInt());
            System.out.println();
            while (true){
                CheckListItem item = new CheckListItem();
                System.out.print("Digite o nome do item: ");
                read.nextLine();
                item.setNome(read.nextLine());
                System.out.println();

                System.out.print("Digite a descrição do item: ");
                item.setDescricao(read.nextLine());
                System.out.println();

                tarefa.addCheckListItem(item);

                System.out.print("Deseja adicionar mais itens? (S/N): ");
                String moreItens = read.next();
                System.out.println();

                if (moreItens.equalsIgnoreCase("N")) break;
            }
        }

        return tarefa;

    }
}
