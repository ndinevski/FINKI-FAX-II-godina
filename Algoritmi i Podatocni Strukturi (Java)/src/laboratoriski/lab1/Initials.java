//За дадено име и презиме на личност кои се внесуваат од стандарден влез, да се испечатат иницијалите за таа личност.
// На влез во првиот ред се дава број на личности за кои ќе се внесуваат соодветото име и презиме. Во наредните линии
// се внесуваат имињата и презимињата одделени со празно место.

package laboratoriski.lab1;

import java.util.Scanner;

import static java.lang.Character.toUpperCase;

public class Initials {
    static void printInitials(String name) {
        name.toUpperCase();
        char firstLetter = name.charAt(0);
        System.out.printf("%c", toUpperCase(firstLetter));
        for(int i=0;i<name.length();i++){
            int compareOneTwo = Character.compare(name.charAt(i), ' ');
            if(compareOneTwo==0){
                System.out.printf("%c", toUpperCase(name.charAt(i+1)));
            }
        }
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int n=input.nextInt();
        String name;
        input.nextLine();

        for(int i=0; i<n; i++){
            name = input.nextLine();
            printInitials(name);
            System.out.println();
        }
    }
}
