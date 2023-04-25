import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Vinegar {



    public static void main(String[] args) throws InterruptedException {

        HashSet<Thread> threads = new HashSet<>();

        for (int i = 0; i < 20; i++) {

            threads.add(new C());

            threads.add(new H());

            threads.add(new H());

            threads.add(new O());

        }

        // run all threads in background

        for (Thread thread : threads) {
            thread.start();
        }

        // after all of them are started, wait each of them to finish for maximum 2_000 ms
        for (Thread thread : threads) {
            thread.join(2000);
        }

        // for each thread, terminate it if it is not finished
        for (Thread thread : threads) {
            if(!thread.isAlive()){
                System.out.println("Possible deadlock!");
                return;
            }
        }

        System.out.println("Process finished.");
    }


    public static Semaphore c_semaphore = new Semaphore(2);
    public static Semaphore h_semaphore = new Semaphore(4);
    public static Semaphore o_semaphore = new Semaphore(2);
    public static Semaphore canBond = new Semaphore(0);
    public static Semaphore canExit = new Semaphore(0);
    public static Lock lock = new ReentrantLock();
    public static int counter_total = 0;


    static class C extends Thread {

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void execute() throws InterruptedException {

            // at most 2 atoms should print this in parallel
            c_semaphore.acquire();
            System.out.println("C here.");
            lock.lock();
            counter_total++;
            if(counter_total == 8){
                canBond.release(8);
            }
            // after all atoms are present, they should start with the bonding process together
            lock.unlock();
            canBond.acquire();
            System.out.println("Molecule bonding.");

            Thread.sleep(100);// this represent the bonding process

            System.out.println("C done.");

            // only one atom should print the next line, representing that the molecule is created
            lock.lock();
            counter_total--;
            if(counter_total == 0){
                canExit.release(8);
                System.out.println("Molecule created.");
            }
            lock.unlock();
            canExit.acquire();
            c_semaphore.release();
        }

    }



    static class H extends Thread  {
        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        public void execute() throws InterruptedException {

            h_semaphore.acquire();
            System.out.println("H here.");
            lock.lock();
            counter_total++;
            if(counter_total == 8){
                canBond.release(8);
            }
            // after all atoms are present, they should start with the bonding process together
            lock.unlock();
            canBond.acquire();
            System.out.println("Molecule bonding.");

            Thread.sleep(100);// this represent the bonding process

            System.out.println("H done.");

            // only one atom should print the next line, representing that the molecule is created
            lock.lock();
            counter_total--;
            if(counter_total == 0){
                canExit.release(8);
                System.out.println("Molecule created.");
            }
            lock.unlock();
            canExit.acquire();
            h_semaphore.release();

        }

    }



    static class O extends Thread  {

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void execute() throws InterruptedException {

            // at most 2 atoms should print this in parallel
            o_semaphore.acquire();
            System.out.println("O here.");
            lock.lock();
            counter_total++;
            if(counter_total == 8){
                canBond.release(8);
            }
            // after all atoms are present, they should start with the bonding process together
            lock.unlock();
            canBond.acquire();

            System.out.println("Molecule bonding.");

            Thread.sleep(100);// this represent the bonding process

            System.out.println("O done.");

            // only one atom should print the next line, representing that the molecule is created

            lock.lock();
            counter_total--;
            if(counter_total == 0){
                canExit.release(8);
                System.out.println("Molecule created.");
            }
            lock.unlock();
            canExit.acquire();
            o_semaphore.release();

        }

    }



}

