package laboratoriski.lab1.zad2;

import java.util.Arrays;
import java.util.Objects;

public class Bank {
    private String name;
    private Account [] accounts;
    private double transferTotal;
    private double provisionTotal;
    int fromAccountIndex, toAccountIndex;
    double currentAmount;

    public Bank() {
    }

    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.accounts = accounts;
        this.transferTotal = 0.0;
        this.provisionTotal = 0.0;
    }

    public String getName() {
        return name;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public boolean makeTransaction(Transaction t){
        int flag=0;
        for(int i=0;i<accounts.length;i++){
            if (accounts[i].getId().equals(t.getFromId())) {
                fromAccountIndex = i;
                flag++;
            }
            if (accounts[i].getId().equals(t.getToId())) {
                toAccountIndex = i;
                flag++;
            }
        }

        if(flag != 2){//ako ne se pronajdeni dva akaunti
            return false;
        }
//
//        if(t.getAmountInDouble() > Double.parseDouble(accounts[fromAccountIndex].getBalance()){
//            return false;
//        }
        currentAmount = accounts[fromAccountIndex].getBalanceInDouble();
        if(t.getAmountInDouble() + t.getProvision() > currentAmount){
            return false;
        }
        currentAmount -= t.getAmountInDouble() + t.getProvision();
        accounts[fromAccountIndex].setBalance(String.format("%.2f$", currentAmount));
        accounts[toAccountIndex].setBalance(String.format("%.2f$",accounts[toAccountIndex].getBalanceInDouble() + t.getAmountInDouble()));
        provisionTotal += t.getProvision();
        transferTotal += t.getAmountInDouble();
        return true;
    }

    public String totalTransfers(){
        return String.format("%.2f$",transferTotal);
    }
    public String totalProvision(){
        return String.format("%.2f$", provisionTotal);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n\n");
        for(int i=0;i<accounts.length;i++){
            sb.append(accounts[i].toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Double.compare(bank.transferTotal, transferTotal) == 0 && Double.compare(bank.provisionTotal, provisionTotal) == 0 && fromAccountIndex == bank.fromAccountIndex && toAccountIndex == bank.toAccountIndex && Double.compare(bank.currentAmount, currentAmount) == 0 && Objects.equals(name, bank.name) && Arrays.equals(accounts, bank.accounts);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, transferTotal, provisionTotal, fromAccountIndex, toAccountIndex, currentAmount);
        result = 31 * result + Arrays.hashCode(accounts);
        return result;
    }
}
