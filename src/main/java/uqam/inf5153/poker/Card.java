package uqam.inf5153.poker;

import java.util.Objects;

public class Card {

    private Value value;
    private Color color;

    public Card() {
    }

    public Card(Value value, Color color) {
        this.value = value;
        this.color = color;
    }

    public Value getValue() {
        return value;
    }
    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getValue() == card.getValue() && getColor() == card.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getColor());
    }

    @Override
    public String toString() {
        return (this.value.toString() + this.color.toString());
    }
}
