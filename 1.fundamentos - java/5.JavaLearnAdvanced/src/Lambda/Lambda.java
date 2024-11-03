package Lambda;

import java.util.ArrayList;

public class Lambda {
    
    // lambdas devem possuir uma interface para que possa ser atribuidas a uma variavel
    interface SumLambda {
        int sum(int n1, int n2);
    }
    public static void main(String[] args) {
        // atribuição de uma lambda a uma váriavel com tipo InnerLambda (interface)
        SumLambda sumLambda = (n1, n2) -> n1 + n2;
        System.out.println(sumLambda.sum(7, 3));

        ArrayList list = new ArrayList<String>();
        ArrayList upperList = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        // adiciona elementos em upper case para nova lista utilizando
        list.forEach(element -> upperList.add(element.toString().toUpperCase()));
        // passa a função println como argumento, para que cada elemento sirva de argumento para a função em questão
        list.forEach(System.out::println); // method reference
        System.out.println(upperList.toString());
    }
}
