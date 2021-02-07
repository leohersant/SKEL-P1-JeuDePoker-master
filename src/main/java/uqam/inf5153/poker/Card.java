package uqam.inf5153.poker;

import java.util.Objects;

public class Card {

    private Value value;
    private Shape shape;

    public Card() {
    }

    public Card(Value value, Shape shape) {
        this.value = value;
        this.shape = shape;
    }

    public Value getValue() {
        return value;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getValue() == card.getValue() && getShape() == card.getShape();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getShape());
    }
}
