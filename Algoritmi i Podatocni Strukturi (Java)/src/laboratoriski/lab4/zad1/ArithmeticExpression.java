//Даден е некој аритметички израз. Аритметичкиот израз е во облик (A+B) или (A-B) каде што А и B истовремено се други аритметички изрази или цифри од 0-9.
// Потребно е да го евалуирате дадениот израз.
//
//Име на класата (Java): ArithmeticExpression


package laboratoriski.lab4.zad1;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

import static java.lang.Character.isDigit;


public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    public static char[] toPostFix(char c[]){

        Stack<Character> stack = new Stack<>();
        String nova = new String();

        for(int i=0;i<c.length;i++){
            if(c[i]=='('){
                stack.push(c[i]);
            }else if(isDigit(c[i])){
                nova+=c[i];
            }else if(c[i]==')'){
                nova += stack.pop();
                stack.pop();
            }else{
                stack.push(c[i]);
            }
        }
        return nova.toCharArray();
    }



    static int presmetaj(char k[], int l, int r) {
        //(A +B) + (C-D) -> A,B,+,C,D,+,-
        //AB+CD-+
        Stack<Integer> result = new Stack<>();

        char [] c = new char[k.length];
        c=toPostFix(k);

        int a,b;
        for(int i=0;i<c.length;i++){
            if(c[i]=='+'){
                b=result.pop();
                a=result.pop();
                result.push(a+b);
            }else if(c[i]=='-'){
                b=result.pop();
                a=result.pop();
                result.push(a-b);
            }
            if(isDigit(c[i])){
                result.push(Integer.parseInt(String.valueOf(c[i])));
            }
        }

        return result.pop();

    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}

