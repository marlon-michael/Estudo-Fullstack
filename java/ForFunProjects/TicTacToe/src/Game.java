import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String[] grid = {"1","2","3","4","5","6","7","8","9"};
        String player = "X";
        String notification = "";
        boolean playing = true;
        boolean win = false;
        int time = 1;

        while(playing){
            System.out.println(" - - - JOGO DA VELHA - - -");

            int pointer = 0;
            for(int line_ptr = 0; line_ptr < 3; line_ptr++){
                System.out.print("        ");
                for(int row_ptr = 0; row_ptr < 3; row_ptr++) {
                    System.out.print(" " + grid[pointer] + " ");
                    pointer++;
                }
                System.out.println();
            }

            System.out.println("\n"+notification);
            notification = "";
            System.out.println(" Vez do jogador "+player);
            System.out.print(" Digite a posição: ");

            int pos = read.nextInt();
            if(pos < 1 || pos > 9){
                notification = " * Posição alta/baixa demais * ";
                continue;
            }
            if(grid[pos-1].equals("X") || grid[pos-1].equals("O")){
                notification = " * Posição "+pos+" ja utilizada * ";
                continue;
            }
            grid[pos-1] = player;

            int line_ptr = 0;
            while(line_ptr < 3){
                if (grid[line_ptr].equals(player) && grid[line_ptr+1].equals(player) && grid[line_ptr+2].equals(player)){
                    win = true;
                    break;
                }
                line_ptr += 3;
            }
            int row_ptr = 0;
            while (row_ptr < 3){
                if (grid[row_ptr].equals(player) && grid[row_ptr + 3].equals(player) && grid[row_ptr + 6].equals(player)) {
                    win = true;
                    break;
                }
                row_ptr += 1;
            }
            if(grid[0].equals(player) && grid[4].equals(player) && grid[8].equals(player)) {
                win = true;
            } else if(grid[2].equals(player) && grid[4].equals(player) && grid[6].equals(player)){
                win = true;
            }

            if (win){
                System.out.printf("\n Jogador %s ganhou o jogo \n", player);
                playing = false;
            }

            time++;
            if(time > 9 && !win){
                System.out.println("\n Velha. Ninguem ganhou! ");
                playing = false;
            }

            if (player.equals("X")) player = "O";
            else player = "X";

            System.out.println("--------------------------");
        }
        System.out.println(" Obrigado por jogar! ");
    }
}
