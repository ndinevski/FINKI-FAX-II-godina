//Даден е некој модифициран XML код. Модифицираниот XML код ги користи симболите '[' и ']', за отварање и затворање на таг, соодветно, наместо стандардните '
//' и '>'. Треба да се провери дали сите тагови во кодот се правилно вгнездени (дали кодот е валиден) т.е. дали секој отворен таг има соодветен затворен таг со
// истото име на соодветното место во кодот. За поедноставување, дадено е дека секој отворен таг мора да има свој затворен таг и дека таговите немаат атрибути.
//На влез е даден бројот на редови во кодот и самиот XML со секој таг во посебен ред, а на излез треба да се испечати 1 или 0 за валиден или невалиден код, соодветно.
//Објаснување: Во модифицираниот XML код секој отворен таг е во облик [imeNaTag], а соодветниот затворен таг е во облик [/imeNaTag].
//Пример за правилно вгнездени тагови во XML e:
//[tag1]
//[tag2]
//Podatok
//[/tag2]
//[/tag1]
//Пример за правилно вгнездени тагови во XML e:
//[tag1]
//[tag2]
//Podatok
//[/tag1]
//[/tag2]

package laboratoriski.lab5.zad2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class CheckXML {

    public static String extractWord(String word){
        int i;
        if(word.charAt(word.length()-1)!=']'){
            return "";
        }
        if(word.charAt(1)=='/'){
            i=2;
        }else{
            i=1;
        }
        String newWord = new String("");
        while(Character.isLetter(word.charAt(i))){
            newWord+=word.charAt(i);
            i++;
        }
        return newWord;
    }

    public static int xmlIsValid(String[] red){
        Stack<String> stack = new Stack<>();

        for(int i=0;i< red.length;i++){
            if(red[i].charAt(0)=='['){
                if(red[i].charAt(1)!='/') {
                    stack.push(extractWord(red[i]));
                }else if(stack.peek().equals(extractWord(red[i]))){
                        stack.pop();
                        continue;
                }else{
                    return 0;
                }
            }
        }
        return 1;
    }




    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        // Vasiot kod tuka
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni


        System.out.println(xmlIsValid(redovi));

        br.close();
    }
}
