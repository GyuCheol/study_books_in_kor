import java.util.Random;

public class PhilosopherProblem {

    private static class Chopstick {

    }

    private static class Philosopher {
        private int id;
        private Chopstick left;
        private Chopstick right;

        public Philosopher(int id, Chopstick left, Chopstick right) {
            this.left = left;
            this.right = right;
            this.id = id;
        }

        public void think() {
            System.out.println(String.format("Philosopher(%d) is thinking...", this.id));
        }

        public void eat() {
            System.out.println(String.format("Philosopher(%d) is eating...", this.id));
        }

    }

    public static void main(String[] args) throws InterruptedException {
        int people = 5;
        Random rand = new Random();
        Chopstick[] chopsticks = new Chopstick[people + 1];
        Philosopher[] philosophers = new Philosopher[people];
        Thread[] threads = new Thread[people];

        for (int i = 0; i < people + 1; i++) {
            chopsticks[i] = new Chopstick();
        }

        for (int i = 0; i < people; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % 6]);
            Philosopher p = philosophers[i];

            threads[i] = new Thread(() -> {

                try {
                    while (true) {
                        p.think();
                        Thread.sleep(50);
                        synchronized (p.left) {
                            synchronized (p.right) {
                                Thread.sleep(1000);
                                p.eat();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            threads[i].start();
        }

        for (int i = 0; i < people; i++) {
            threads[i].join();
        }

    }

}
