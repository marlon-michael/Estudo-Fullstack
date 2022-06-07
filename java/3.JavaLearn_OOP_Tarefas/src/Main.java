import classes.Tarefa;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        Tarefa tarefa = new Tarefa();

        System.out.print("Digite o nome da tarefa: ");
        read.nextLine();
        tarefa.setNome(read.nextLine());
        System.out.println();

        System.out.print("Digite a descrição da tarefa");
        read.nextLine();
        tarefa.setDescricao(read.nextLine());
        System.out.println();

        System.out.println("UUID: " + tarefa.getUUID());
        System.out.println("Nome: "  + tarefa.getNome());
        System.out.println("Descrição: " + tarefa.getDescricao());
    }
}
