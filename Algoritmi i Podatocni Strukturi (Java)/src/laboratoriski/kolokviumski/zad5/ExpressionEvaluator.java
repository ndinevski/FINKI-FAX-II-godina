//Да се напише алгоритам кој ќе пресметува (евалуира) математички израз составен од броеви и операциите за собирање (+) и множење (*).
//Забелешка: Операцијата множење има предност пред операцијата собирање.

package laboratoriski.kolokviumski.zad5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression){
        // Vasiot kod tuka
        char[] expr = expression.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int tmp,i=0;
        stack.push(0);
        while(i<expr.length){
            if(Character.isDigit(expr[i])){
                stack.push(0);
                int j=0;
                while((i+j)<expr.length && Character.isDigit(expr[i+j])){
                    stack.push(stack.pop()*10 + Integer.parseInt(String.valueOf(expr[i+j])));
                    j++;
                }
                i+=j;
                continue;
            }
            if(expr[i]=='*'){
                int j=0;
                stack.push(0);
                while((i+1+j)<expr.length  && (Character.isDigit(expr[i+1+j]))){
                    stack.push(stack.pop()*10 + Integer.parseInt(String.valueOf(expr[i+1+j])));
                    j++;
                }
                stack.push(stack.pop()* stack.pop());
                i+=1+j;
                continue;
            }
            if(expr[i]=='+'){
                tmp= stack.pop();
                tmp += stack.pop();
                stack.push(tmp);
                i++;
                continue;
            }
        }
        if(stack.size()==1){
            return stack.pop();
        }else{
            return stack.pop()+stack.pop();
        }


    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}