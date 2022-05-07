import java.util.Scanner;

public class Exe1_Tipos_IO {
    /*
    # Tipos de variaveis e declaração
    int numero = 1; numero inteiro
    double numero2 = 1.5; numero decimal
    String texto = "text"; literal, texto
    char letra = 'a'; caraactere único
    boolean ativo = true/false; - lógico
    String[] lista = {"l","i","s","t","a"}; new String[5];
    int [][] vetor = {{0,1},{2,3},{4,5}}; new int[3][2];

    # String methods
    texto.charAt(0); primeira letra
    texto.length(); tamanho da string
    texto.equals("texto"); compara em igualdade
    texto.toUpperCase(); torna string caixa alta
    texto.substring(1,3); "texto" -> "ext"

    # Saida de texto via console
    System.out.print("texto"+numero); Escreve
    System.out.println("texto"+numero); Escreve e pula linha
    System.out.prinf("texto %d, %.2f, %s", numero, numero2, texto); Escreve e formata variaveis sem quebrar linha

    # Entrada de texto via console
    import java.util.Scanner;
    Scanner read = new Scanner(System.in);
    read.next(String); / read.nextDouble(double);
     */

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        System.out.print("EXE I: Digite o número do exercicio: ");
        int exe = read.nextInt();

        if(exe ==1) {
            int A = 10;
            int B = 20;
            int C = A;
            A = B;
            B = C;

            System.out.println("O valor de A e B invertidos são: " + A + " e " + B);
        }

        else if(exe ==2) {
            System.out.print("Digite um numero inteiro: ");
            int valor = read.nextInt();

            System.out.println();
            System.out.println("O valor antecessor de " + valor + " é " + (--valor));
        }

        else if(exe ==3) {
            System.out.println("Use virgula para ponto flutuante");
            System.out.print("Digite a base do retangulo: ");
            double base = read.nextDouble();
            System.out.print("Digite a altura do retangulo: ");
            double altura = read.nextDouble();
            double area = base * altura;
            double perimetro = 2 * (base + altura);

            System.out.println();
            System.out.printf("A area do retangulo informado é de: %.2f e seu perimetro: %.2f \n", area, perimetro);
        }

        else if(exe ==4) {
            System.out.println("Digite seu tempo de vida em:");
            System.out.print("Anos: ");
            int anos = read.nextInt();
            System.out.print("Meses: ");
            int meses = read.nextInt();
            System.out.print("Dias: ");
            int dias = read.nextInt();

            int dias_vividos = 0;
            dias_vividos += anos * 365;
            dias_vividos += meses * 30;
            dias_vividos += dias;

            System.out.println();
            System.out.println("A sua idade em dias foram de: " + dias_vividos);

        }

        else if(exe ==5) {
            System.out.print("Escreva o número total de eleitores: ");
            int num_eleitor = read.nextInt();
            System.out.print("Escreva o numero de votos nulos: ");
            int num_nulo = read.nextInt();
            System.out.print("Escreva o numero de votos em branco: ");
            int num_branco = read.nextInt();

            System.out.println();
            System.out.println("Dos " + num_eleitor + " eleitores");
            double percem_nulo = num_nulo * 100.0 / num_eleitor;
            double percem_branco = num_branco * 100.0 / num_eleitor;

            System.out.println(percem_nulo + "% dos votos são nulos");
            System.out.println(percem_branco + "% dos votos são brancos");
            System.out.println("E um total de " + (100 - percem_nulo) + "% dos votos são validos");
        }

        else if(exe ==6) {
            System.out.print("Digite o salário do funcionário: ");
            double salario = read.nextDouble();
            System.out.print("Digite o percentual de reajuste: ");
            double percent = read.nextDouble();

            System.out.println();
            System.out.printf("O salário de R$%.2f com um reajuste de %.2f%% equivale a R$%.2f", salario, percent, (salario + salario * (percent / 100)));
        }

        else if(exe ==7) {
            double distribuidor_percen = 28;
            double imposto_percem = 45;
            System.out.print("Escreva o custo de fabrica deste veículo: ");
            double custo = read.nextDouble();

            double custo_final = custo + (custo * distribuidor_percen / 100) + (custo * imposto_percem / 100);

            System.out.println();
            System.out.printf("O custo do veículo com imposto é de: R$%.2f", custo_final);
        }

        else if(exe ==8) {
            System.out.println("Digite o salário fixo do vendedor: ");
            double salario = read.nextDouble();
            System.out.println("Digite o valor da comissão por venda: ");
            double comissao = read.nextDouble();
            System.out.println("Digite o número de carros vendidos: ");
            double num_vendido = read.nextDouble();
            System.out.println("Digite o valor total das vendas: ");
            double valor_vendas = read.nextDouble();

            double salario_final = salario + (comissao * num_vendido) + (valor_vendas * 0.05);

            System.out.println();
            System.out.printf("O salário final do vendedor é de R$%.2f", salario_final);
        }

        else if (exe == 9){
            System.out.print("Digite a temperatura em Fahrenheit: ");
            double fah = read.nextDouble();

            System.out.println();
            System.out.println("A temperatura digitade em celsius é de: "+ ((fah-32)/9 * 5));
        }

        else if (exe == 10){
            System.out.print("Digite a primeira nota do aluno: ");
            double nota1 = read.nextDouble();
            System.out.print("Digite a segunda nota do aluno: ");
            double nota2 = read.nextDouble();
            System.out.print("Digite a terceira nota do aluno: ");
            double nota3 = read.nextDouble();

            System.out.println();
            System.out.printf("A média final do aluno é %.1f\n", (nota1*2 + nota2*3 + nota3 * 5)/10 );
        }

        else if (exe == 11){
            String texto = "Did you know about the aclaimed MMO FFIXV?";
            int ocurrances = 0;

            for(int i = 0; i < texto.length(); i++){
                if (texto.toUpperCase().charAt(i) == 'A'){
                    ocurrances++;
                }
            }
            System.out.println(ocurrances+" foram encontradas da letra A");
        }

        else if (exe == 12){
            int quantidade = 0;
            System.out.print("Digite uma frase: ");
            read.nextLine(); // cleaning buffer
            String frase = read.nextLine();
            for(int i = 0; i < frase.length(); i++){
                if (frase.charAt(i) == ' ') quantidade++;
            }
            System.out.println();
            System.out.println("A quantidade de espaços em branco na frase digitade é de: "+quantidade);
        }
    }
}