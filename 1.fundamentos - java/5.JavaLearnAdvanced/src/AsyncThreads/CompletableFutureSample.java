import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureSample {
    public static void main(String[] args) {
        // Completable sem retorno bloqueante
        CompletableFuture completable_A = CompletableFuture.runAsync(() -> {
            System.out.println("inicio imediato completable sem retorno");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("fim completable sem retorno");
        });
        System.out.println("inicio A");
        completable_A.join(); // aguarda fim da execução para comtinuar programa
        System.out.println("fim A");

        // Completable com retorno bloqueante
        CompletableFuture<String> completable_B = CompletableFuture.supplyAsync(() -> {
            System.out.println("inicio imediato completable com retorno");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "resultado completable";
        });
        System.out.println("inicio B");
        try {
            String result = completable_B.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("fim B");

        // Multiplos Completables
        CompletableFuture completable_C1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("inicio imediato completable C1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "resultado C1";
        });
        CompletableFuture completable_C2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("inicio imediato completable C2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "resultado C2";
        });

        // adiciona completables ao grupo
        CompletableFuture completables = CompletableFuture.allOf(completable_C1, completable_C2);
        // executa após o retorno de todos os completables
        System.out.println("Completables: ");
        try {
            completables.thenRun(() -> {
                try {
                    System.out.println("Nome: " + completable_C1.get() + ", Sobrenome: " + completable_C2.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // my own Completable
        MyCompletable myCompletable = new MyCompletable();
        System.out.println("minha completable");
        System.out.println(myCompletable.run().get());
    }


}

class MyCompletable {
    public CompletableFuture completableFuture;

    public MyCompletable run() {
        this.completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("inicio minha completable");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "retorno da minha completable";
        });
        return this;
    }

    public Object get() {
        try {
            return this.completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return completableFuture;
    }
}
