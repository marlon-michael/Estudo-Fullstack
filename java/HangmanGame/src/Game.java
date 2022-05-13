import java.util.Arrays;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String letra;
        String palavra;

        boolean saindo = false;
        int erros = 0;
        String[] corpo = {" O \n", "/", "|", "\\\n", "/ ", "\\\n"};
        String vitima = " | \n";
        String letra_usada = "";
        String menssagem = "";

        System.out.print("Digite a palavra da forca: ");
        palavra = read.next().toLowerCase();
        for(int i = 0; i < 99; i++){
            System.out.println();
        }

        String[] letra_certa = new String[palavra.length()];
        Arrays.fill(letra_certa, "_");

        while(true){
            System.out.println(" º - - Jogo da Forca - ");
            System.out.println(vitima);
            System.out.println();

            for (String s : letra_certa) {
                System.out.print(s + " ");
            }
            System.out.println();
            System.out.println("Letras já usadas: "+letra_usada);
            System.out.print(menssagem);
            menssagem = "";
            if(saindo){
                System.out.println();
                break;
            }
            System.out.print("Digite uma letra: ");
            letra = read.next().toLowerCase();

            if(letra.length() > 1){
                menssagem = " * Você deve digitar apenas uma letra por vez! *\n";
                continue;
            }
            if(letra_usada.contains(letra.toUpperCase())){
                menssagem = " * Você já digitou esta letra. Tenta outra. *\n";
                continue;
            }
            if(palavra.contains(letra)){
                for(int l = 0; l < palavra.length(); l++){
                    if(palavra.substring(l, l+1).equals(letra)) letra_certa[l] = palavra.substring(l, l+1);
                }
            }
            else{
                vitima = vitima.concat(corpo[erros]);
                erros++;
            }
            letra_usada = letra_usada.concat(letra).toUpperCase();

            boolean contem = false;
            for (String l : letra_certa) {
                if (l.equals("_")) {
                    contem = true;
                    break;
                }
            }

            System.out.println();
            if(!contem){
                menssagem = "Parabéns! A palavra certa era: " + palavra;
                saindo = true;
            }
            if(erros > 5){
                menssagem = "Opa. Parece que você perdeu a cabeça com esse jogo.\n"+
                "A palavra que você buscava era: "+palavra;
                saindo = true;
            }
            System.out.println("\n\n");
        }
    }
}
