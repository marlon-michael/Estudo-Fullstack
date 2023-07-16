package TryCatch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatch {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        Integer num = null;
        while (num == null){
            try{
                System.out.print("Digite: ");
                num = read.nextInt();
            }
            catch(InputMismatchException erro){
                System.err.println("Tipo de dado informado está incorreto, necessário informar INTEIRO");
                read.next();
            }
            catch(Exception erro){
                erro.printStackTrace();
                throw new RuntimeException("Erro desconhecido, tente novamente seguindas as regras informadas");
            }
            finally{
                if (num != null) System.out.println("Número digitado: "+num);
            }

            //testes
            assert (num != null): "variavel num precisa ter um valor";//precisa ser diferente de null

        }
    }
}
