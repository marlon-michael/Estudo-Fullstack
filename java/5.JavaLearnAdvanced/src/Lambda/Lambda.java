package Lambda;

import java.util.ArrayList; // import the ArrayList class
import java.util.stream.Collectors;

public class Lambda {
    public static void main(String args[]) {
        ArrayList<String> test = new ArrayList<>();
        test.add("asd");
        test.add("ert");
        test.add("wgad");
        test.add("zxcv");

        //convert to collection
        System.out.println(test.stream().filter(a -> a.contains("a")).collect(Collectors.toList()));

        //return first value
        String s = String.valueOf(test.stream().filter(a -> a.contains("a")).findFirst());
        System.out.println(s);

        //store returned values
        ArrayList<String> f = new ArrayList<>();
        test.stream().filter(a -> a.contains("a")).forEach(f::add);
        System.out.println(f);

        //print returned values
        test.stream().filter(a -> a.contains("a")).forEach(System.out::print);
    }
}