import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

class OSMidterm {
    public static Semaphore sem = new Semaphore(1);
    //TODO: Initialize the semaphores you need
    public static void main(String[] args) {
        //STARTING CODE, DON'T MAKE CHANGES
        //-----------------------------------------------------------------------------------------
        String final_text="Bravo!!! Ja resi zadacata :)";
        int m=10, n=100;
        Object[][] data = new Object[m][n];
        Random rand = new Random();
        int k=0;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                int random = rand.nextInt(100);
                if(random%2==0 & k<final_text.length()) {
                    data[i][j] = final_text.charAt(k);
                    k++;
                } else {
                    data[i][j] = rand.nextInt(100);
                }
            }
        }
        DataMatrix matrix = new DataMatrix(m,n, data);
        StatisticsResource statisticsResource = new StatisticsResource();
        //-----------------------------------------------------------------------------------------

        //ONLY TESTING CODE, SO YOU CAN TAKE A LOOK OF THE OUTPUT YOU NEED TO GET AT THE END
        //YOU CAN COMMENT OR DELETE IT AFTER YOU WRITE THE CODE USING THREADS
        //-----------------------------------------------------------------------------------------
//        Concatenation concatenation = new Concatenation(matrix, statisticsResource);
//
//        concatenation.concatenate();
//
//        statisticsResource.printString();
        //-----------------------------------------------------------------------------------------
        List<Thread> threads = new ArrayList<>();

        //TODO: Run the threads from the Concatenation class
        for (int i = 0; i < m; i++) {
            threads.add(new Concatenation(matrix, statisticsResource, i));
        }
        for (Thread thread : threads) {
            thread.start();
        }

        //TODO: Wait 10seconds for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //TODO: Print the string you get, call function printString()
        statisticsResource.printString();

        //TODO: Check if some thread is still alive, if so kill it and print "Possible deadlock"
        for (Thread thread : threads) {
            if(!thread.isAlive()){
                thread.interrupt();
            }
        }

    }

    // TODO: Make the Concatenation Class  a Thread Class
    static class Concatenation extends Thread{

        private DataMatrix matrix;
        private StatisticsResource statisticsResource;
        private int row;

        public Concatenation(DataMatrix matrix, StatisticsResource statisticsResource, int row) {
            this.matrix = matrix;
            this.row = row;
            this.statisticsResource = statisticsResource;
        }

        public void concatenate_by_row() throws InterruptedException {
            //TODO: Implement this function
            // add  arguments in the function if needed

            for (int j=0;j<this.matrix.getN();j++) {
                sem.acquire();
                if (this.matrix.isString(row,j)) {
                    this.statisticsResource.concatenateString(this.matrix.getEl(row,j).toString());
                }
                sem.release();
            }


        }
        public void execute() throws InterruptedException {
            //TODO: call the concatenate_by_row() function
            concatenate_by_row();
        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //-------------------------------------------------------------------------
    //YOU ARE NOT CHANGING THE CODE BELOW
    static class DataMatrix {

        private int m,n;
        private Object[][] data;

        public DataMatrix(int m, int n, Object[][] data) {
            this.m = m;
            this.n = n;
            this.data = data;
        }

        public int getM() {
            return m;
        }

        public int getN() {
            return n;
        }

        public Object[][] getData() {
            return data;
        }

        public Object getEl(int i, int j) {
            return data[i][j];
        }

        public Object[] getRow(int pos) {
            return this.data[pos];
        }

        public Object[] getColumn(int pos) {
            Object[] result = new Object[m];
            for (int i=0;i<m;i++) {
                result[i]=data[i][pos];
            }
            return result;
        }

        public boolean isString(int i, int j) {
            return this.data[i][j] instanceof Character;
        }


    }

    static class StatisticsResource {

        private String concatenatedString;

        public StatisticsResource() {
            this.concatenatedString = "";
        }

        //function for String concatenation
        public void concatenateString(String new_character) {
            concatenatedString+=new_character;
        }

        //function for printing the concatenated string
        public void printString() {
            System.out.println("Here is the phrase from the matrix: " + concatenatedString);
        }

    }



}


