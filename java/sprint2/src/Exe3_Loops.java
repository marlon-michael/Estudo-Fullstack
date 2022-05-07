import java.util.Scanner;

public class Exe3_Loops {
    /*
    # Loops
    -----------------------
    for (i=0;i<n;i++){code}
    ----------------
    while(expression){code}
    ------------------
    do{code}while(expression)
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
            for (int i = 0; i < num_palavras-1; i++){
                System.out.printf(palavras[i]+", ");
            }
            System.out.println(palavras[num_palavras-1]+".");
        }

        else if (exe == 3){
            System.out.print("Digite o numero de colunas da matriz: ");
            int num_coluna = read.nextInt();
            System.out.print("Digite o numero de linhas da matriz: ");
            int num_linha = read.nextInt();
            String[][] matriz = new String[num_linha][num_coluna];

            System.out.println();
            for (String[] linha: matriz){
                for (String celula: linha){
                    celula = "#";
                    System.out.print(celula);
                }
                System.out.println();
            }

        }

        else if (exe == 4){
            System.out.print("Digite a quantiade de numeros que quer somar: ");
            int len = read.nextInt();
            int soma = 0;

            for (int i = 0; i < len; i++){
                System.out.print("Digite os numeros inteiros a serem somados: ");
                soma+= read.nextInt();
            }
            System.out.println();
            System.out.println("O valor total dos numeros somados é: "+soma);
        }
        
        else if (exe == 0){
            //encontrar letras proibidas em array de palavras
            String[] palavras = {"abc", "def", "ghi"};
            char letraProibida = 'a';
            boolean encontrouLetra = false;
            for (String palavra: palavras){
                for (int i = 0; i < palavra.length(); i++){
                    if (palavra.charAt(i) == letraProibida) {
                        encontrouLetra = true;
                        break;
                    }
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
                if (palavra.charAt(i) == letraProibida) return true;
            }
        }
        return false;
    }
}
