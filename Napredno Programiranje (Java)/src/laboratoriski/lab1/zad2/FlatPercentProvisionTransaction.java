package laboratoriski.lab1.zad2;

import java.util.Objects;

public class FlatPercentProvisionTransaction extends Transaction {
    private int centsPerDolar;
    public FlatPercentProvisionTransaction(long fromId, long toId, String amount, int centsPerDolar) {
        super(fromId, toId, "FlatPercent", amount);
        this.centsPerDolar = centsPerDolar;
    }

    public int getCentsPerDolar() {
        return centsPerDolar;
    }

    @Override
    public double getProvision(){
        return (centsPerDolar/100.0)*(int)(getAmountInDouble());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FlatPercentProvisionTransaction that = (FlatPercentProvisionTransaction) o;
        return centsPerDolar == that.centsPerDolar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), centsPerDolar);
    }
}
