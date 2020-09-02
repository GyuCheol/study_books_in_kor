import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {


    public static void main(String[] args) throws InterruptedException {
        AtomicInteger i1 = new AtomicInteger(0);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                i1.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                i1.incrementAndGet();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i1);


    }

}
