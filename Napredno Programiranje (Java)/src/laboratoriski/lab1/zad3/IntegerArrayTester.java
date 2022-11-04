//Да се напише класа која чува низа на цели броеви IntegerArray. Класата треба да е immutable. Тоа значи дека,
// откако еднаш ќе се инстанцира да не може да се менува состојбата на објектот, односно да не може да се менуваат
// податоците зачувани во него и да не може да се наследува од неа final. За потребите на класата треба да се
// имплементираат следните public методи:
//IntegerArray(int a[]) - конструктор кој прима низа од цели броеви
//length():int - метод кој ја враќа должината на низата
//getElementAt(int i):int - го враќа елементот на позиција i, може да претпоставите дека индекост i секогаш ќе има
// вредност во интервалот [0,length()-1]
//sum():int - метод кој ја враќа сумата на сите елемeнти во низата
//average():double - метод кој ја враќа средната вредност на елементите во низата - аритметичка средина
//getSorted():IntegerArray- враќа нов објект од истата класа кој ги содржи истите вредности од тековниот објект,
// но сортирани во растечки редослед
//concat(IntegerArray ia):IntegerArray - враќа нов објект од истата класа во кој се содржат сите елементи од this
// објектот и по нив сите елементи од ia објектот притоа запазувајќи го нивниот редослед
//toString():String - враќа текстуална репрезентација на објектот каде елементите се одделени со запиркa и едно
// празно место после запирката и на почетокот и крајот на стрингот има средни загради. Пример за низа која ги
// содржи боревите 2,1 и 4 враќа "[2, 1, 4]".
//Покрај класата IntegerArray треба да напишете дополнително уште една класа која ќе служи за вчитување на низа
// од цели броеви од влезен тек на податоци. Оваа класа треба да се вика ArrayReader и во неа треба да имате еден
// public static метод за вчитување на низа од цели броеви од InputStream.
//readIntegerArray(InputStream input):IntegerArray - вчитува низа од цели броеви од input зададена во следниот
// формат: Во првата линија има еден цел борј кој кажува колку елементи има во низата, а во наредниот ред се
// дадени елементите на низата одделени со едно или повеќе празни места. Помош, искористете ја класата
// java.util.Scanner.
//Секогаш кога работите со низи во Java можете да искористите дел од методите во класата Arrays. За да пристапите
// до класата најпрвин треба да ја импортирате со следнава наредба
//import java.util.Arrays;
//Во продолжение се дадени дел од методите кои можат да ви помогнат. За тоа како работат консултираје ја нивната
// документација.
//copyOf(int[] original, int newLength)
//equals(int[] a, int[] a2)
//sort(int[] a)


package laboratoriski.lab1.zad3;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class IntegerArrayTester {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        IntegerArray ia = null;
        switch (s) {
            case "testSimpleMethods":
                ia = new IntegerArray(generateRandomArray(scanner.nextInt()));
                testSimpleMethods(ia);
                break;
            case "testConcat":
                testConcat(scanner);
                break;
            case "testEquals":
                testEquals(scanner);
                break;
            case "testSorting":
                testSorting(scanner);
                break;
            case "testReading":
                testReading(new ByteArrayInputStream(scanner.nextLine().getBytes()));
                break;
            case "testImmutability":
                int a[] = generateRandomArray(scanner.nextInt());
                ia = new IntegerArray(a);
                testSimpleMethods(ia);
                testSimpleMethods(ia);
                IntegerArray sorted_ia = ia.getSorted();
                testSimpleMethods(ia);
                testSimpleMethods(sorted_ia);
                sorted_ia.getSorted();
                testSimpleMethods(sorted_ia);
                testSimpleMethods(ia);
                a[0] += 2;
                testSimpleMethods(ia);
                try {
                    ia = ArrayReader.readIntegerArray(new ByteArrayInputStream(integerArrayToString(ia).getBytes()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                testSimpleMethods(ia);
                break;
        }
        scanner.close();
    }

    static void testReading(InputStream in) {
        IntegerArray read = null;
        try {
            read = ArrayReader.readIntegerArray(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(read);
    }

    static void testSorting(Scanner scanner) {
        int[] a = readArray(scanner);
        IntegerArray ia = new IntegerArray(a);
        System.out.println(ia.getSorted());
    }

    static void testEquals(Scanner scanner) {
        int[] a = readArray(scanner);
        int[] b = readArray(scanner);
        int[] c = readArray(scanner);
        IntegerArray ia = new IntegerArray(a);
        IntegerArray ib = new IntegerArray(b);
        IntegerArray ic = new IntegerArray(c);
        System.out.println(ia.equals(ib));
        System.out.println(ia.equals(ic));
        System.out.println(ib.equals(ic));
    }

    static void testConcat(Scanner scanner) {
        int[] a = readArray(scanner);
        int[] b = readArray(scanner);
        IntegerArray array1 = new IntegerArray(a);
        IntegerArray array2 = new IntegerArray(b);
        IntegerArray concatenated = array1.concat(array2);
        System.out.println(concatenated);
    }

    static void testSimpleMethods(IntegerArray ia) {
        System.out.print(integerArrayToString(ia));
        System.out.println(ia);
        System.out.println(ia.sum());
        System.out.printf("%.2f\n", ia.average());
    }


    static String integerArrayToString(IntegerArray ia) {
        StringBuilder sb = new StringBuilder();
        sb.append(ia.length()).append('\n');
        for (int i = 0; i < ia.length(); ++i)
            sb.append(ia.getElementAt(i)).append(' ');
        sb.append('\n');
        return sb.toString();
    }

    static int[] readArray(Scanner scanner) {
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = scanner.nextInt();
        }
        return a;
    }


    static int[] generateRandomArray(int k) {
        Random rnd = new Random(k);
        int n = rnd.nextInt(8) + 2;
        int a[] = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = rnd.nextInt(20) - 5;
        }
        return a;
    }

}


final class IntegerArray{
    private int [] a;

    public IntegerArray(int a[]) {
        this.a=a;
    }

    public int[] getA() {
        return a;
    }

    public int length(){
        return a.length;
    }

    public int getElementAt(int i){
        return a[i];
    }

    public int sum(){
        int suma=0;
        for(int i=0;i<length();i++){
            suma+=a[i];
        }
        return suma;
    }

    public double average(){
        return (double)sum()/length();
    }

    public IntegerArray getSorted(){
        int [] b = new int[a.length];
        System.arraycopy(this.getA(),0,b,0,this.length());
        Arrays.sort(b);
        IntegerArray newObj = new IntegerArray(b);
        return newObj;
    }

    public IntegerArray concat(IntegerArray ia){
        int [] newArray = new int[ia.length()+this.length()];
        System.arraycopy(this.getA(),0,newArray,0,this.length());
        int j=0;
        for(int i=0;i<ia.length();i++){
            newArray[i+this.length()]=ia.getElementAt(j);
            j++;
        }
        IntegerArray newObj = new IntegerArray(newArray);
        return newObj;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0;i<length()-1;i++){
            sb.append(getElementAt(i)).append(", ");
        }
        sb.append(getElementAt(length()-1)).append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerArray that = (IntegerArray) o;
        return Arrays.equals(a, that.a);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(a);
    }
}
class ArrayReader {
    public static IntegerArray readIntegerArray(InputStream input) throws IOException{
        Scanner sc = new Scanner(input);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i=0; i<n; i++){
            array[i] = sc.nextInt();
        }
        sc.close();
        IntegerArray ia = new IntegerArray(array);
        return ia;
    }
}
