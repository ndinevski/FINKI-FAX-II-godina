package laboratoriski.lab1.zad2;

import java.util.Objects;
import java.util.Random;

public class Account {
    private String name;
    private Long id;
    private String balance;

    public Account() {
    }

    public Account(String name, String balance) {
        this.name = name;
        this.balance = balance;
        Random ran = new Random();
        id = ran.nextLong();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getBalance() {
        return balance;
    }

    public double getBalanceInDouble(){
        return Double.parseDouble(getBalance().substring(0,balance.length()-1));
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\nBalance: ").append(balance).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(name, account.name) && Objects.equals(id, account.id) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, balance);
    }
}
