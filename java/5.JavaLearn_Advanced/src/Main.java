import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Digite o indice do programa: ");
        int exe = read.nextInt();

        if (exe == 1){
            //add integer numbers into arraylist until read num be zero, then print array
            //ArrayList<Integer> array = new ArrayList<Integer>();
            ArrayList array = new ArrayList();
            while(true) {
                System.out.print("Digite um número inteiro ou [ 0 ] para finalizar: ");
                int num = read.nextInt();
                System.out.println();
                if (num == 0) break;
                array.add(num);
            }
            System.out.println("Números digitados:");
            for (int i = 0; i < array.size()-1; i++) {
                System.out.print(array.get(i) + " | ");
            }System.out.println(array.get(array.size()-1));
        }

    }
}
