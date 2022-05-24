import java.util.Scanner;

public class Exe2_Condicionais {
    /*
    # Operadores relacionais
    || or
    && and
    ^ xor - só funciona se as entradas forem diferentes

    # if else
    if (expression){
    code
    } else if (expression){
    code
    } else {code}

    # switch
    switch (expression / var){
    case 1:code;break;
    case 2:code;break;
    default: code;break;
    }

    # switch
    switch (var){
    case 'A','B','C': code;break;
    }
     */
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        System.out.print("EXE II: Digite o numéro do exercicio: ");
        int exe = read.nextInt();
        System.out.println();

        if (exe == 1){
            System.out.print("Digite um numero inteiro: ");
            int num = read.nextInt();

            System.out.println();
            if (num > 10){
                System.out.println("O valor digitado é maior que dez");
            }else{
                System.out.println("O valor digitado é menor ou igual a dez");
            }
        }

        else if (exe == 2){
            System.out.print("Digite um numero inteiro: ");
            int num = read.nextInt();

            if (num < 0){
                System.out.println("O numero digitado é negativo");
            }
            else{
                System.out.println("O numero digitado é positivo");
            }
        }
        else if (exe == 3){
            System.out.print("Digite a quantidade de maçãs desejadas: ");
            int num_apple = read.nextInt();
            double apple_price = 1.0;

            if (num_apple < 12){
                apple_price = 1.3;
            }

            System.out.println();
            System.out.printf("%d maçãs custam R$%.2f cada, valor total de R$%.2f\n", num_apple, apple_price, num_apple*apple_price);
        }
        else if (exe == 4){
            System.out.print("Digite o ano atual: ");
            int ano_atual = read.nextInt();
            System.out.print("Digite o ano em que nasceu: ");
            int ano_nasc = read.nextInt();

            System.out.println();
            if (ano_atual - ano_nasc < 16){
                System.out.println("Você não poderá votar neste ano!");
            }
            else{
                System.out.println("Você poderá votar este ano!");
            }
        }
        else if (exe == 5){
            System.out.print("Digite um numero: ");
            double num1 = read.nextDouble();
            System.out.print("Digite outro número: ");
            double num2 = read.nextDouble();

            System.out.println();
            if (num1 > num2){
                System.out.printf("O número %.2f é maior que %.2f\n", num1, num2);
            }
            else {
                System.out.printf("O número %.2f é maior que %.2f\n", num2, num1);
            }
        }
        else if (exe == 6){
            System.out.print("Digite um número: ");
            double num1 = read.nextDouble();
            System.out.println("Digite o segundo número: ");
            double num2 = read.nextDouble();
            System.out.println("Digite o terceiro número: ");
            double num3 = read.nextDouble();

            System.out.println();
            System.out.print("Os ordem dos numeros digitados é de: ");
            if (num1 > num2){
                if (num2 > num3){
                    System.out.print(num1);
                    System.out.print(num2);
                    System.out.print(num3);
                }
                else{
                    System.out.print(num1);
                    System.out.print(num3);
                    System.out.print(num2);
                }
            }
            else if (num2 > num1){
                if (num1 > num3){
                    System.out.print(num2);
                    System.out.print(num1);
                    System.out.print(num3);
                }
                else{
                    System.out.print(num2);
                    System.out.print(num3);
                    System.out.print(num1);
                }
            }
            else if (num3 > num1){
                if (num1 > num2){
                    System.out.print(num3);
                    System.out.print(num1);
                    System.out.print(num2);
                }
                else{
                    System.out.print(num3);
                    System.out.print(num2);
                    System.out.print(num1);
                }
            }
        }
        else if (exe == 7){
            System.out.print("Digite a o horario de inicio do jogo: ");
            int hora_inicio = read.nextInt();
            System.out.print("Digite o horario de fim do jogo: ");
            int hora_fim = read.nextInt();
            int tempo;

            System.out.println();
            if (hora_inicio > 24 || hora_fim > 24){
                System.out.println("Valor de horas digitado é superior a 24");
            }
            else if (hora_inicio < 0 || hora_fim < 0){
                System.out.println("Valor de horas digitado é inferior a 0");
            }

            if (hora_fim <= hora_inicio){
                tempo = 24 - hora_inicio + hora_fim;
            }
            else{
                tempo = hora_fim - hora_inicio;
            }

            if (tempo > 24){
                System.out.println("O temnpo de partida digitado excede o tempo maximo de 24h de jogo");
            }
            System.out.println(tempo);
        }
        else if (exe == 8){
            System.out.print("Digite o número de horas trabalhadas: ");
            int horas_trabalhadas = read.nextInt();
            System.out.print("Digite o valor do valor por hora trabalhada:");
            double valor_hora = read.nextDouble();
            double salario = horas_trabalhadas * valor_hora;

            if (horas_trabalhadas > 160){
                int horas_extra = horas_trabalhadas - 160;
                int horas_exped = horas_trabalhadas - horas_extra;
                salario = horas_exped * valor_hora + horas_extra * (valor_hora * 1.5);
            }

            System.out.println();
            System.out.printf("O salário por número de horas trabalhadas é de: R$%.2f\n", salario);
        }
        else if (exe == 9){
            System.out.print("Digite seu nome: ");
            String nome = read.next();
            System.out.print("Digite seu sexo (M para masculino ou F para feminino): ");
            read.nextLine();//limpar bugger ( \n da linha anterior)
            String sexo = read.nextLine();
            System.out.print("Digite sua altura (usando virgula para separa casas decimais): ");
            double altura = read.nextDouble();
            double peso_ideal = 0.0;

            System.out.println();
            switch(sexo.toUpperCase()){
                case "M":
                    peso_ideal = (72.7 * altura) - 58;
                    break;
                case "F":
                    peso_ideal = (62.1 * altura) - 44.7;
                    break;
                default:
                    System.out.println("Sexo não definido");
                    break;
            }
            System.out.printf("%s, o peso ideal para sua medida é de: %.3f\n",nome, peso_ideal);
        }
        else if (exe == 10){
            System.out.print("Digite o salário fixo: ");
            double salario_fixo = read.nextDouble();
            System.out.print("Digite o valor total de vendas: ");
            double valor_vendas = read.nextDouble();
            double salario;

            System.out.println();
            if (valor_vendas > 1500){
                salario = salario_fixo + (valor_vendas * 0.05);
            }
            else{
                salario = salario_fixo + (valor_vendas * 0.03);
            }
            System.out.printf("O salário final do funcionário é de: R$%.2f\n", salario);
        }
        else if (exe == 11){
            //System.out.print("Digite o numero da conta: ");
            //String num_conta = read.next();
            System.out.print("Digite o saldo: ");
            double saldo = read.nextDouble();
            System.out.print("Digite o debito: ");
            double debito = read.nextDouble();
            System.out.print("Digite o credito: ");
            double credito = read.nextDouble();
            double saldo_atual = saldo - debito + credito;

            System.out.println();
            if (saldo_atual < 0){
                System.out.println("Saldo negativo");
            }
            else{
                System.out.println("Saldo positivo");
            }
        }

        else if (exe == 12){
            System.out.print("Digite a quantidade do produto em estoque: ");
            int quantidade_estoque = read.nextInt();
            System.out.print("Digite a quantidade maxima desse item: ");
            int quantidade_maximo = read.nextInt();
            System.out.print("Digite a quantidade minima desse item");
            int quantidade_minimo = read.nextInt();

            int quantidade_media = (quantidade_maximo + quantidade_minimo)/2;
            if (quantidade_estoque < quantidade_media){
                System.out.println("É necessário efetuar compra.");
            }
            else{
                System.out.println("Não é necessário efetuar compra");
            }
        }
        else if (exe == 13){
            String abreviacao = "X";
            String classe = "Indefinida";

            while(abreviacao.length() != 3){
                System.out.print("Digite a abreviação da classe (3 LETRAS): ");
                abreviacao = read.next();
            }

            switch (abreviacao.toUpperCase()) {
                case "SCH", "AST", "WHM", "SAG" -> classe = "Healer";
                case "PLD", "WAR", "DKN", "GNB" -> classe = "Tank";
                case "MNR", "BFM", "DRG", "RDM", "SMN", "BLM", "DNC", "RPR", "SMR", "NJA" -> classe = "DPS";
            }
            System.out.println();
            System.out.println("A classe é: "+classe);
        }
    }
}
