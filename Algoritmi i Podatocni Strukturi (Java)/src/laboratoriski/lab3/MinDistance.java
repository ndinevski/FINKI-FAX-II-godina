package laboratoriski.lab3;

import java.util.Scanner;

public class MinDistance {

    public static float minimalDistance(float points[][]) {
        float minDistance=100000;
        float distance;
        for(int i=0;i<points.length;i++){
            for(int j=i+1;j< points.length;j++){
                distance = (float) Math.sqrt(Math.pow(Math.abs(points[i][0]-points[j][0]),2) + Math.pow(Math.abs(points[i][1]-points[j][1]), 2));
                if(distance < minDistance){
                    minDistance = distance;
                }
            }
        }
        return minDistance;
    }
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();

        float points[][] = new float[N][2];

        for(int i=0;i<N;i++) {
            points[i][0] = input.nextFloat();
            points[i][1] = input.nextFloat();
        }

        System.out.printf("%.2f\n", minimalDistance(points));
    }
}
