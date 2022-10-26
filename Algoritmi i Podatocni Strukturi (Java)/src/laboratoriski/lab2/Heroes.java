//Во една игра со карти се користат специјални карти, т.ш. за секоја карта се важни податоците за: id на херојот на
// картата (int id), моќта на херојот (int power), како и бројот на напади кои тој може да ги направи (int numAttacks).
//Така, за секоја една карта може да се пресмета колку таа карта е значајна, според тоа колку штета може да нанесе
// херојот на картата, односно како производ на моќта на херојот и бројот на напади кои може да ги направи.
//Двајца пријатели решаваат да ја играат оваа игра, т.ш. во две еднострано поврзани листи се чуваат податоците за
// картите кои им се доделени. Во првата листа се чуваат картите доделени на првиот пријател, а додека пак во втората
// се чуваат податоците за картите доделени на вториот пријател. И двајцата играчи на почеток имаат точно 6 карти.
//На самиот почетокот на играта, правилата налагаат првиот играч (првиот пријател) да ја предаде својата најдобра
// карта на другиот играч, т.ш. кога вториот играч ќе ја земе картата истата треба да ја постави во средина на своите
// карти. Тоа значи дека потребно е од листата која ги чува картите на првиот пријател да се отстрани (избрише)
// најдобрата карта и таа карта да се додаде на средина на листата каде што се чуваат картите на вториот пријател.
//Влез: Во секој еден ред се дадени податоци за една карта, одделени со празно место, во формат id power numAttacks.
// Притоа, прво се дадени картите на првиот пријател, по што следуваат податоците за картите на вториот пријател.
//Излез: Во првиот ред id на сите карти на првиот пријател. Во вториот ред id на сите карти на вториот пријател.
//Внимавајте:
//1. Даден е целосниот код на структурата којашто треба да се користи. Дадена е и тест класата Heroes.java, со целосно
// имплементиран input и output. Потребно е да се менува само во рамки на void startHeroesGame(SLL<Card>
// firstFriendCards, SLL<Card> secondFriendCards) функцијата.
//2. Притоа, бришењето треба да биде имплементирано како бришење на цел јазол, а додавањето како додавање на цел
// јазол. Промените (бришење/додавање елемент) не треба да се однесуваат на информациите во самите јазли туку во
// промени на врските помеѓу јазлите.
//3. Не смее да менувате во main функцијата !


package laboratoriski.lab2;

import java.util.Scanner;

class Card {
    private int id;
    private int power;
    private int numAttacks;

    public Card(int id, int power, int numAttacks) {
        this.id = id;
        this.power = power;
        this.numAttacks = numAttacks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getNumAttacks() {
        return numAttacks;
    }

    public void setNumAttacks(int numAttacks) {
        this.numAttacks = numAttacks;
    }

    public int damage() {
        return power * numAttacks;
    }


    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

public class Heroes {

    //todo: implement function
    public static void startHeroesGame(SLL<Card> firstFriendCards, SLL<Card> secondFriendCards) {
        SLLNode<Card> current = firstFriendCards.getFirst();
        int maxDamage = current.element.damage();
        SLLNode<Card> maxCard= firstFriendCards.getFirst();
        while(current!=null){
            if(current.element.damage()>maxDamage){
                maxDamage = current.element.damage();
                maxCard = current;
            }
            current = current.succ;
        }
        secondFriendCards.insertAfter(maxCard.element, secondFriendCards.getFirst().succ.succ);
        firstFriendCards.delete(maxCard);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SLL<Card> firstFriendCards = new SLL<Card>();
        SLL<Card> secondFriendCards = new SLL<Card>();

        for (int i = 0; i < 6; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            firstFriendCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        for (int i = 0; i < 6; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            secondFriendCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        startHeroesGame(firstFriendCards, secondFriendCards);
        System.out.println(firstFriendCards.toString());
        System.out.println(secondFriendCards.toString());
    }
}
