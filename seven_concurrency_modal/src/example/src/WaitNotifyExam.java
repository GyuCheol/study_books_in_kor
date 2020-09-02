public class WaitNotifyExam {

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                synchronized (lock) {
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        synchronized (lock) {
            lock.wait();
        }

        System.out.println("A");

    }

}
