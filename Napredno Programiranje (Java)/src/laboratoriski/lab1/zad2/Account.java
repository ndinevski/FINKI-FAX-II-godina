//Треба да се креира апликација за банка која ќе управуваа со сметките на повеќе корисниците и ќе врши трансакции помеѓу нив. Банката работи само со долари и притоа сите суми на пари се претставуваат како стрингови со знакот $ на крај, и точка помеѓу бројот на центи и бројот на долари без празни места. Бројот на центи треба да се состои од две цифри без разлика на износот.
//Пример:
//10 долари  10.00$
//15 долари и 50 центи 15.50$
//За потребите на ваквата апликација треба да се напишат класите Account,Transaction и Bank. Класата Account претставува една сметка на еден корисник и треба да ги чува следните податоци:
//Име на корисникот,
//единствен идентификационен број (long)
//тековното салдо на сметката.
//Оваа класа исто така треба да ги имплементира и следниве методи
//Account(String name, String balance) – конструктор со параметри (id-то треба да го генерирате сами со помош на класата java.util.Random)
//getBalance():String
//getName():String
//getId():long
//setBalance(String balance)
//toString():String – враќа стринг во следниот формат, \n означува нов ред
//Name:Andrej Gajduk\n Balance:20.00$\n
//Класата Transaction претставува трансакција (префрлување пари од една на друга сметка), од страна на банката за што честопати се наплаќа провизија. За почеток треба да се напише класата Transaction со податочни членови за идентификационите броеви на две сметки, едната од која се одземаат парите и друга на која се додаваат парите, текстуален опис и износ на трансакцијата.
//За оваа класа треба да ги имплементирате методите:
//Transaction(long fromId, long toId, Stirng description, String amount) – конструктор со параметри
//getAmount():String
//getFromId():long
//getToId():long
//Оваа класа треба да е immutable, а можете и да ја направите и апстрактна бидејќи не е наменета директно да се користи туку само како основна класа за изведување на други класи.
//Како што споменавме претходно банката наплаќа провизија за одредени трансакции. Има два типа на провизија, фискна сума и процент. Кај фиксна сума за било која трансакција без разлика на износот на трансакцијата се наплаќа исто провизија (пример 10$). Кај процент за секој еден долар од трансакцијата банката наплаќа одреден процент провизија (на пример 5%, или 5 центи на секој долар – процентите секогаш се целобројни и провизија се наплаќа само на цели долари).
//За да се прави разлика меѓу различните типови на провизија, треба да напишете уште две класи кои ќе наследуваат од Transaction кои треба да ги именувате FlatAmountProvisionTransaction и FlatPercentProvisionTransaction.
//
//Првата класа FlatAmountProvisionTransaction треба да содржи соодветен конструктор
//
//FlatAmountProvisionTransaction(long fromId, long toId,String amount, String flatProvision) кој го иницијализира полето за опис на "FlatAmount" и соодветен get метод
//getFlatAmount():String
//Слично и класата FlatPercentProvisionTransaction треба да има соодветен конструктор
//FlatPercentProvisionTransaction (long fromId, long toId, String amount, int centsPerDolar) кој го иницијализира полето за опис на "FlatPercent" и соодветен get метод
//getPercent():int
//Исто така треба да се преоптовари equals(Object o):boolean методот и за двете класи.
//За крај треба да ја имплементирате класата Bank која ги чува сметките од своите корисници и дополнително врши трансакции. Класата освен сметките на своите корисници, треба да ги чува и сопственото име и вкупната сума на трансфери како и вкупната наплатена провизија од страна на банката за сите трансакции.
//Класата Bank треба да ги нуди следните методи:
//Bank(String name, Account accounts[]) – конструктор со соодветните параметри (направете сопствена копија на низата од сметки)
//makeTransaction(Transaction t):boolean – врши проверка дали корисникот ги има потребните средства на сметка и дали и двете сметки на кои се однесува трансакцијата се нависитина во банката и ако и двата услови се исполнето ја извршува трансакцијата и враќа true, во спротивно враќа false
//totalTransfers():String – ја дава вкупната сума на пари кои се префрлени во сите трансакции до сега
//totalProvision():String – ја дава вкупната провизија наплатена од банката за сите извршени трансакции до сега
//toString():String - го враќа името на банката во посебна линија во формат
//Name:Banka na RM\n \n по што следат податоците за сите корисници.
//Провизијата се наплаќа така што на основната сума на трансакцијата се додава вредноста не провизијата и таа сума се одзема од првата сметка.
//За сите класи да се напишат соодветни equals и hashCode методи.
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
