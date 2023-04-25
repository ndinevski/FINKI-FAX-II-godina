import java.util.*;
import java.util.concurrent.Semaphore;

public class CountThree {

    public static int NUM_RUNS = 100;
    /**
     * Promenlivata koja treba da go sodrzi brojot na pojavuvanja na elementot 3
     */
    static int count = 0;
    /**
     * TODO: definirajte gi potrebnite elementi za sinhronizacija
     */
    static Semaphore semaphore;
    public void init() {
        semaphore = new Semaphore(1);
    }

    static class Counter extends Thread {

        public void count(int[] data) throws InterruptedException {
//            semaphore.acquire();
//            count = (int) Arrays.stream(data).filter(number -> number==3).count();
//            semaphore.release();
            for (int num : data) {
                semaphore.acquire();
                if (num == 3) {
                    count++;
                }
                semaphore.release();
            }

        }
        private int[] data;

        public Counter(int[] data) {
            this.data = data;
        }

        @Override
        public void run() {
            try {
                count(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            CountThree environment = new CountThree();
            environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {

        init();

        HashSet<Thread> threads = new HashSet<Thread>();
        Scanner s = new Scanner(System.in);
        int total=s.nextInt();

        for (int i = 0; i  < NUM_RUNS; i++) {
            int[] data = new int[total];
            for (int j = 0; j < total; j++) {
                data[j] = s.nextInt();
            }
            Counter c = new Counter(data);
            threads.add(c);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println(count);

    }
}
