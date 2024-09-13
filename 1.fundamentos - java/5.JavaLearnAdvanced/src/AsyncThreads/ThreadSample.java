
public class ThreadSample {
    public static void main(String[] args) throws Exception{
        Thread thread_a1 = new Thread(new MyThread("A1"));
        Thread thread_a2 = new Thread(new MyThread("A2"));
        thread_a1.start();
        thread_a2.start();
        // bloqueando execução até o fim da execução da Thread
        thread_a1.join();
        thread_a2.join();
    }
}

class MyThread implements Runnable{
    public String name;
    MyThread(String name){
        this.name = name;
    }
    public void run(){
        System.out.println("iniciando thread "+this.name);
        try {Thread.sleep(2000);}
        catch (Exception e) {e.printStackTrace();}
        System.out.println("terminando thread "+this.name);
    }
}