import java.util.Scanner;

public class Exe3_Loops {
    /*
    # Loops
    -----------------------
    for (i=0;i<n;i++){code}
    -------------------------
    for (int num: nums){code}
    -----------------------
    while(expression){code}
    -------------------------
    do{code}while(expression)
     */
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        System.out.print("EX III: Digite o número do exercicio: ");
        int exe = read.nextInt();
        System.out.println();

        if (exe == 1){
            System.out.println("TABUADA");
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
            System.out.println("CONTADOR DE ESPAÇOS EM BRANCO");
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
            System.out.println("DESENHADOR DE MATRIZ");
            System.out.print("Digite o numero de colunas da matriz: ");
            int num_coluna = read.nextInt();
            System.out.print("Digite o numero de linhas da matriz: ");
            int num_linha = read.nextInt();

            System.out.println();
            for (int lin = 0; lin < num_linha; lin++){
                for (int col = 0; col < num_coluna; col++){
                    System.out.print("#");
                }
                System.out.println();
            }

        }

        else if (exe == 4){
            System.out.println("SOMADOR DE NÚMEROS");
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

        else if (exe == 5){
            System.out.println("FATORIAL");
            System.out.print("Digite um numero: ");
            int num = read.nextInt();
            int fatorial = 1;

            for (int i = num; i > 1; i--){
                fatorial *= i;
            }

            System.out.println();
            System.out.printf("O fatorial de %d é %d\n", num, fatorial);

        }

        else if (exe == 6){
            boolean logado = false;
            String pin_correto = "91352";
            System.out.println("O pin deve ter 5 digitos. Você tem 3 tentativas");
            for (int chances = 2; chances > -1; chances--){
                System.out.print("Digite o pin: ");
                String pin = read.next();
                if(pin.length() != 5) System.out.printf("O pin deve ter 5 digitos. %d tentativas restantes\n", chances);
                else if(pin.equals(pin_correto)){
                    logado = true;
                    break;
                }
                else System.out.printf("Senha incorreta. %d tentativas restantes\n", chances);
            }
            if (logado) System.out.println("Senha correta. Logando");
            else System.out.println("Número de tentativas excedido, tente novamente mais tarde.");
        }

        else if (exe == 7){
            System.out.print("Digite um numero: ");
            int num = read.nextInt();
            int soma = 0;

            for (int i = 0; i < num+1; i++){
                soma+=i;
            }
            System.out.println();
            System.out.println("A soma de todos os antecessores de "+num+" é "+soma);
        }

        else if (exe == 8){
            System.out.print("Digite a quantidade de valores na lista: ");
            int len_lista = read.nextInt();
            int[] lista = new int[len_lista];
            for (int i = 0; i < len_lista; i++){
                System.out.print("Digite "+(i+1)+"º valor: ");
                lista[i] = read.nextInt();
            }
            len_lista = read.nextInt();
            int[] lista_remover = new int[len_lista];
            for (int i = 0; i < len_lista; i++){
                System.out.print("Digite "+(i+1)+"º valor a remover: ");
                lista_remover[i] = read.nextInt();
            }
            System.out.println();
            System.out.println("Os números que sobraram na lista foram: ");
            check_loop: for(int i = 0; i < len_lista; i++){
                for (int j = 0; j < len_lista; j++){
                    if (lista[i] == lista_remover[j]) {
                        continue check_loop;
                    }
                }
            }
        }
        
        else if (exe == 0){
            //encontrar letras proibidas em array de palavras
            String[] palavras = {"abc", "def", "ghi"};
            char letraProibida = 'e';
            boolean encontrouLetra = false;
            palavra_loop: for (String palavra: palavras){
                for (int i = 0; i < palavra.length(); i++){
                    if (palavra.charAt(i) == letraProibida) {
                        encontrouLetra = true;
                        break palavra_loop;
                    }
                }
            }
            if (encontrouLetra) System.out.println("Letra proibida entre as palavras");
            else System.out.println("Nenhuma letra proibida entre as palavras");


            //external function forbidenLetter used
            if (forbidenLetter(new String[]{"abc", "def", "ghi"}, 'e')){
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
