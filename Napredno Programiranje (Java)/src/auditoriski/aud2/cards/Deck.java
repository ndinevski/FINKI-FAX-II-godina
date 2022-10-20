package auditoriski.aud2.cards;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Deck {
    private PlayingCard[] cards;
    private boolean[] isDealt;
    private int dealtTotal;

    public Deck() {
        cards = new PlayingCard[52];
        isDealt = new boolean[52];
        dealtTotal = 0;

        for(int i=0;i<CardType.values().length;i++){
            for(int j=0;j<13;j++){
                cards[i*13+j] = new PlayingCard(j+1, CardType.values()[i]);
            }
        }
    }

    public PlayingCard[] getCards() {
        return cards;
    }

    public void setCards(PlayingCard[] cards) {
        this.cards = cards;
    }

    public boolean[] getIsDealt() {
        return isDealt;
    }

    public void setIsDealt(boolean[] isDealt) {
        this.isDealt = isDealt;
    }

    public int getDealtTotal() {
        return dealtTotal;
    }

    public void setDealtTotal(int dealtTotal) {
        this.dealtTotal = dealtTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return dealtTotal == deck.dealtTotal && Arrays.equals(cards, deck.cards) && Arrays.equals(isDealt, deck.isDealt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dealtTotal);
        result = 31 * result + Arrays.hashCode(cards);
        result = 31 * result + Arrays.hashCode(isDealt);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(PlayingCard card : cards){
            stringBuilder.append(card.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public boolean hasCardsLeft(){
        return (cards.length - dealtTotal) > 0;
    }

    public PlayingCard dealCard(){
        if(hasCardsLeft()) return null;

        int card = (int) (Math.random() * 52);

        if(!isDealt[card]){
            isDealt[card] = true;
            dealtTotal++;
            return cards[card];
        }

        return dealCard();
    }


    public PlayingCard[] shuffle() {
        List<PlayingCard> playingCardList = Arrays.asList(cards);
        Collections.shuffle(playingCardList);
        return cards;
    }

    public static void main(String[] args) {

    }


}
