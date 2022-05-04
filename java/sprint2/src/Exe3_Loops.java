import java.util.Scanner;

public class Exe3_Loops {
    /*
    loops
    -----------------------
    for (i=0;i<n;i++){code}

     */
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        System.out.print("EX III: Digite o número do exercicio: ");
        int exe = read.nextInt();
        System.out.println();

        if (exe == 1){
            int tabuada = 1;

            while(tabuada != 0){
                System.out.print("Digite a tabuada que deseja: ");
                tabuada = read.nextInt();

                System.out.println();
                System.out.println("Tabuada de "+tabuada);
                System.out.println();
                for (int i = 1; i < 11; i++){
                    System.out.printf("%d x %d = %d\n", tabuada, i, tabuada * i);
                }
            }
        }

        else if (exe == 2){
            System.out.print("Digite o número de palavras que deseja escrever: ");
            int num_palavras = read.nextInt();
            String[] palavras = new String [num_palavras];
            for (int i = 0; i < num_palavras; i++){
                System.out.printf("Digite a %dª paravra: ", i+1);
                palavras[i] = read.next();
            }
            System.out.println();
            System.out.print("As palavras digitadas foram: ");
            for (int i = 0; i < num_palavras; i++){
                System.out.printf(palavras[i]);
                if (i+1 < palavras.length){
                    System.out.printf(", ");
                }
            }
            System.out.println(".");
        }
        
        else if (exe == 3){
            String[] palavras = {"abc", "def", "ghi"};
            char letraProibida = 'a';
            boolean encontrouLetra = false;
            for (String palavra: palavras){
                for (int i = 0; i < palavra.length(); i++){
                    if (palavra.charAt(i) == letraProibida) encontrouLetra = true;
                    if (encontrouLetra) break;
                }if (encontrouLetra) break;
            }
            if (encontrouLetra) System.out.println("Letra proibida entre as palavras");
            else System.out.println("Nenhuma letra proibida entre as palavras");


            //external function forbidenLetter used
            if (forbidenLetter(new String[]{"abc", "def", "ghi"}, 'a')){
                System.out.println("Letra proibida entre as palavras");
            }
            else {
                System.out.println("Nenhuma letra proibida entre as palavras");
            }
        }
    }


    public static boolean forbidenLetter(String[] palavras, char letraProibida){
        for (String palavra: palavras){
            for (int i = 0; i < palavra.length(); i++){
                if (palavra.charAt(i) == letraProibida){
                    return true;
                }
            }
        }
        return false;
    }
}
