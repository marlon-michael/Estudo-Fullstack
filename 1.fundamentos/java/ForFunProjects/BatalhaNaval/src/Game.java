import java.io.IOException;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner read = new Scanner(System.in);
        //int [][] quadro = new int[8][8];
        int [][] quadro = {
                {1,0,0,0,0,0,0,0},
                {1,0,0,0,0,1,1,0},
                {0,0,0,1,0,0,0,0},
                {1,0,0,1,0,0,0,0},
                {0,0,0,1,1,1,1,1},
                {0,0,0,0,0,0,0,0},
                {1,1,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };
        String [][] tela = {
                {" ","1","2","3","4","5","6","7","8"},
                {"1","~","~","~","~","~","~","~","~"},
                {"2","~","~","~","~","~","~","~","~"},
                {"3","~","~","~","~","~","~","~","~"},
                {"4","~","~","~","~","~","~","~","~"},
                {"5","~","~","~","~","~","~","~","~"},
                {"6","~","~","~","~","~","~","~","~"},
                {"7","~","~","~","~","~","~","~","~"},
                {"8","~","~","~","~","~","~","~","~"},
        };
        int erros = 0;
        int acertos = 0;
        int maximo_pontuacao = 15;
        int maximo_erro = 10;
        String notification = "";

        System.out.println("- - - Batalha Naval - - -");
        System.out.println();

        while (erros < maximo_erro && acertos < maximo_pontuacao){

            escreve_tela(tela, notification);

            System.out.print("Digite nº da linha a atacar: ");
            int lin= read.nextInt();
            System.out.print("Digite nº da coluna a atacar: ");
            int col= read.nextInt();

            if (filtra_entrada(lin, col)) continue;

            // Processamento do ataque
            if (quadro[lin-1][col-1] == 2){
                System.out.println("Posição ja atacada. Digite outra posição");
            }
            else if (quadro[lin-1][col-1] == 1){
                quadro[lin-1][col-1] = 2;
                tela[lin][col] = "#";
                acertos++;
                notification = "\nAcertamos uma enbarcação\n";
            }
            else{
                quadro[lin-1][col-1] = 2;
                tela[lin][col] = "O";
                erros++;
                notification = "\nErramos, mas não podemos desistir\n";
            }

            limpa_tela();
        }
        //mensagem de fim de jogo
        if (acertos == maximo_pontuacao){
            System.out.println("Párabens capitão. Você afundou todas a embarcações inimigas!");
        }
        else if (erros == maximo_erro){
            System.out.println("Má noticia capitão. Parece que estamos afundando!");
        }
    }

    public static void escreve_tela(String[][] tela, String notification){
        //recebe matriz bidimensional e escreve na tela
        for (String[] pl : tela) {
            for (String pc : pl) {
                System.out.print(" "+pc);
            }
            System.out.println();
        }
        System.out.printf(notification);
        notification = "";
    }
    public static boolean filtra_entrada(int lin,int col){
        //checa se posição digitada é valida
        if (lin < 1 || lin > 8){
            System.out.println("Número da linha muito baixo ou muito alto. Tente novamente");
            return true;
        }
        else if (col < 1 || col > 8){
            System.out.println("Número da coluna muito baixo ou muito alto. Tente novamente");
            return true;
        }
        return false;
    }

    public static void limpa_tela() throws IOException, InterruptedException {
        System.out.println("\n\n\n");
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else Runtime.getRuntime().exec("clear");
    }
}
