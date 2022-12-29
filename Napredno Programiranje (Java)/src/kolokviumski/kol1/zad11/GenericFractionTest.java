package kolokviumski.kol1.zad11;

import java.util.Scanner;

class ZeroDenominatorException extends Exception{
    public ZeroDenominatorException(String mess) {
        super(mess);
    }
}

public class GenericFractionTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
            GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
            GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
            GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
        } catch(ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

}

// вашиот код овде

class GenericFraction<T extends Number, U extends Number>{
    T numerator;
    U denominator;

    public GenericFraction(T numerator, U denominator) throws ZeroDenominatorException {
        if(denominator.intValue()==0){
            throw new ZeroDenominatorException("Denominator cannot be zero");
        }
        this.numerator=numerator;
        this.denominator=denominator;
    }

    GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) throws ZeroDenominatorException {

        Double newNumerator = gf.denominator.doubleValue()*this.numerator.doubleValue()+this.denominator.doubleValue()*gf.numerator.doubleValue();
        Double newDenominator = gf.denominator.doubleValue()*this.denominator.doubleValue();

        int nzd = NZD(newNumerator.intValue(), newDenominator.intValue());
        newNumerator/=nzd;
        newDenominator/=nzd;
        return new GenericFraction<Double, Double>(newNumerator, newDenominator);
    }
    public int NZD(int a, int b){
        int nzd=1;
        for(int i=2;i<b;i++){
            if(a%i==0 && b%i==0){
                a/=i;
                b/=i;
                nzd*=i;
                i=1;
            }
        }
        return nzd;
    }


    public double toDouble(){
        return numerator.doubleValue()/denominator.doubleValue();
    }

    @Override
    public String toString() {
        Double newDenominator = denominator.doubleValue();
        Double newNumerator = numerator.doubleValue();
        int nzd = NZD(newNumerator.intValue(), newDenominator.intValue());
        newNumerator/=nzd;
        newDenominator/=nzd;
        return String.format("%.2f / %.2f", numerator.doubleValue(), denominator.doubleValue());
    }
}