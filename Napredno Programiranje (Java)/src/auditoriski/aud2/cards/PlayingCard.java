package auditoriski.aud2.cards;

import java.util.Objects;

public class PlayingCard {
    private int rank;
    private CardType type;

    public PlayingCard(int rank, CardType type) {
        this.rank = rank;
        this.type = type;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCard that = (PlayingCard) o;
        return rank == that.rank && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, type);
    }

    @Override
    public String toString() {
        return String.format("%d %s", rank, type.name());
    }
}
