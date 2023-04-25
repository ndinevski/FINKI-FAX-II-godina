public class TwoThreads {

    public static class ThreadAB implements Runnable {
        String a, b;
        public ThreadAB(String a, String b){
            this.a=a;
            this.b=b;
        }
        public void run() {
            System.out.println(a);
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadAB("A","B")).start();
        new Thread(new ThreadAB("1","2")).start();
    }

}
